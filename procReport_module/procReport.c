#include <linux/kernel.h>
#include <linux/module.h>
#include <linux/init.h>
#include <linux/sched/signal.h>
#include <linux/sched.h>
#include <linux/proc_fs.h>
#include <linux/uaccess.h>
#include <linux/seq_file.h>
#include <linux/fs.h>
#include <linux/mm.h>

struct task_struct* task;

//Code provided by professor in project specifications.
//Converting virtual address to physicial address
unsigned long virt2phys(
   struct mm_struct* mm,
   unsigned long vpage)
{
  pgd_t* pgd;
  p4d_t* p4d;
  pud_t* pud;
  pmd_t* pmd;
  pte_t* pte;
  
  struct page* page;
  pgd = pgd_offset(mm,vpage);
  if (pgd_none(*pgd) || pgd_bad(*pgd))
    return 0;
  p4d = p4d_offset(pgd, vpage); 
  if (p4d_none(*p4d) || p4d_bad(*p4d))
    return 0;
  pud = pud_offset(p4d, vpage); 
  if (pud_none(*pud) || pud_bad(*pud))
    return 0;
  pmd = pmd_offset(pud, vpage); 
  if (pmd_none(*pmd) || pmd_bad(*pmd))
    return 0;
  if (!(pte = pte_offset_map(pmd, vpage)))
    return 0;
  if (!(page = pte_page(*pte)))
    return 0;
  unsigned long physical_page_addr = page_to_phys(page);
  pte_unmap(pte);
  //handle unmapped page
  if (physical_page_addr == 70368744173568)
    return 0;
  return physical_page_addr;
}

static int my_proc_show(struct seq_file* m, void* v)
{
  //printk will print to /var/log/syslog
  //seq_printf will print to the proc file /proc/proc_report
  unsigned long total_contig_pages = 0;
  unsigned long total_noncontig_pages = 0;
  unsigned long total_total_pages = 0;
  printk("PROCESS REPORT:\n");
  seq_printf(m, "PROCESS REPORT:\n");
  printk("proc_id,proc_name,contig_pages,noncontig_pages,total_pages");
  seq_printf(m, "proc_id,proc_name,contig_pages,noncontig_pages,total_pages");

  //iterate over each process
  for_each_process(task)
  {
    if(task->pid > 650)
    {
      unsigned long contig_pages = 0;
      unsigned long noncontig_pages = 0;
      unsigned long total_pages = 0;
      struct vm_area_struct *vma = 0;
      unsigned long vpage;
      if (task->mm && task->mm->mmap)
      {
	//obtain physical address of a memory page
	unsigned long prev_physical_page_addr;
	bool first_page = true;
        for (vma = task->mm->mmap; vma; vma = vma->vm_next)
	{
	  for (vpage = vma->vm_start; 
	       vpage < vma->vm_end;
	       vpage += PAGE_SIZE)
	  {
	    unsigned long physical_page_addr = virt2phys(task->mm, vpage);
	    //check if the current and  previous physical pages are
	    //contiguous or not
	    if (!first_page)
	    {
              if (prev_physical_page_addr + PAGE_SIZE == physical_page_addr)
		contig_pages++;  
	      else
		noncontig_pages++;
	    }
	    else
	    {
	      noncontig_pages++;
	      first_page = false;
	    }
	    total_pages++;
	    prev_physical_page_addr = physical_page_addr;
	  }
	}
      }
      total_contig_pages += contig_pages;
      total_noncontig_pages += noncontig_pages;
      total_total_pages += total_pages;
      //print the output for this particular process
      printk("\n%d,%s,%ld,%ld,%ld", task->pid, task->comm,contig_pages,noncontig_pages,total_pages);
      seq_printf(m, "\n%d,%s,%ld,%ld,%ld", task->pid, task->comm,contig_pages,noncontig_pages,total_pages);
    }
  }
  //print the overall data
  printk("\nTOTALS,,%ld,%ld,%ld\n", total_contig_pages,total_noncontig_pages,total_total_pages);
  seq_printf(m, "\nTOTALS,,%ld,%ld,%ld\n", total_contig_pages,total_noncontig_pages,total_total_pages);

  return 0;
}

static int my_proc_open(
  struct inode* inode,
  struct file* file)
{
  //single_open will print the entire output from
  //my_proc_show function in one go.  (Prints the entirety of the log file).
  return single_open(file, my_proc_show, NULL);
}

//standard kernel attributes
static struct file_operations my_fops={
  .owner = THIS_MODULE,
  .open = my_proc_open, // declaring function when user opens file
  .release = single_release,
  .read = seq_read,
  .llseek = seq_lseek,
};


static int proc_init(void)
{
  //this function is called when module is installed
  //It creates /proc/proc_report file
  struct proc_dir_entry *proc_file;
  proc_file = proc_create("proc_report", 0666, NULL, &my_fops); //my_fops is called when user opens or reads files
  if(proc_file == NULL)
    return -ENOMEM; //ERROR HANDLING when program can't create file (usually due to memory issues)

  return 0;
}

static void proc_cleanup(void)
{
  //this will be called during module removal
  remove_proc_entry("proc_report", NULL);
}
//When insmod is called to create file.
module_init(proc_init);
//When rmmod is called to remove file.
module_exit(proc_cleanup);

MODULE_LICENSE("GPL"); /* Get rid of taint message by delaring code as GPL */
MODULE_AUTHOR("David Saelee & Patrick Moy"); /* Authors of module */
MODULE_DESCRIPTION("OUTPUT CONTIGOUS AND NON-CONTIGUOUS PAGES INFORMATION OF ALL PROCESSES WITH PID > 650"); /* What this modules does */

