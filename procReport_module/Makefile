obj-m += procReport.o

all:
	make -C /lib/modules/$(shell uname -r)/build M=$(PWD) modules

install:
	sudo insmod procReport.ko 

remove:
	sudo rmmod procReport.ko


