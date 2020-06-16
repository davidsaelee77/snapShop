/*
 *  pcmatrix module
 *  Primary module providing control flow for the pcMatrix program
 *
 *  Producer consumer bounded buffer program to produce random matrices in parallel
 *  and consume them while searching for valid pairs for matrix multiplication.
 *  Matrix multiplication requires the first matrix column count equal the
 *  second matrix row count.
 *
 *  A matrix is consumed from the bounded buffer.  Then matrices are consumed
 *  from the bounded buffer, one at a time, until an eligible matrix for multiplication
 *  is found.
 *
 *  Totals are tracked using the ProdConsStats Struct for:
 *  - the total number of matrices multiplied (multtotal from consumer threads)
 *  - the total number of matrices produced (matrixtotal from producer threads)
 *  - the total number of matrices consumed (matrixtotal from consumer threads)
 *  - the sum of all elements of all matrices produced and consumed (sumtotal from producer and consumer threads)
 *
 *  Correct programs will produce and consume the same number of matrices, and
 *  report the same sum for all matrix elements produced and consumed.
 *
 *  Each thread produces a total sum of the value of
 *  randomly generated elements.  Producer sum and consumer sum must match.
 *
 *  University of Washington, Tacoma
 *  TCSS 422 - Operating Systems
 *  Winter 2020
 */

#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <assert.h>
#include <time.h>
#include "matrix.h"
#include "counter.h"
#include "prodcons.h"
#include "pcmatrix.h"

int main (int argc, char * argv[])
{


  int numw = NUMWORK;
  if (argc == 1)
  {
    BOUNDED_BUFFER_SIZE = MAX;
    NUMBER_OF_MATRICES = LOOPS;
    MATRIX_MODE = DEFAULT_MATRIX_MODE;
    printf("USING DEFAULTS: worker_threads=%d bounded_buffer_size=%d matricies=%d matrix_mode=%d \n",numw,BOUNDED_BUFFER_SIZE,NUMBER_OF_MATRICES,MATRIX_MODE);
  }
  else
  {
    if (argc == 2)
    {
      numw=atoi(argv[1]);
      BOUNDED_BUFFER_SIZE = MAX;
      NUMBER_OF_MATRICES=LOOPS;
      MATRIX_MODE=DEFAULT_MATRIX_MODE;
    }
    if (argc == 3)
    {
      numw = atoi(argv[1]);
      BOUNDED_BUFFER_SIZE = atoi(argv[2]);
      NUMBER_OF_MATRICES = LOOPS;
      MATRIX_MODE = DEFAULT_MATRIX_MODE;
    }
    if (argc == 4)
    {
      numw = atoi(argv[1]);
      BOUNDED_BUFFER_SIZE = atoi(argv[2]);
      NUMBER_OF_MATRICES = atoi(argv[3]);
      MATRIX_MODE = DEFAULT_MATRIX_MODE;
    }
    if (argc == 5)
    {
      numw = atoi(argv[1]);
      BOUNDED_BUFFER_SIZE = atoi(argv[2]);
      NUMBER_OF_MATRICES = atoi(argv[3]);
      MATRIX_MODE = atoi(argv[4]);
    }
    printf("USING: worker_threads=%d bounded_buffer_size=%d matricies=%d matrix_mode=%d\n",numw,BOUNDED_BUFFER_SIZE,NUMBER_OF_MATRICES,MATRIX_MODE);
  }

  bigmatrix = malloc(sizeof(Matrix*) * BOUNDED_BUFFER_SIZE);
  extern struct prodcons *matElements; 
  extern struct __counters_t *total;
  extern struct __counter_t *multTotal; 
  
  matElements = malloc(sizeof(struct prodcons)); 

  total = malloc(sizeof(struct __counters_t)); 
  total -> prod = malloc(sizeof(struct __counter_t));
  total -> cons = malloc(sizeof(struct __counter_t));
  multTotal = malloc(sizeof(struct __counter_t));
  init_cnt(total -> prod);
  init_cnt(total -> cons);	
  init_cnt(multTotal);

  time_t t;
  // Seed the random number generator with the system time
  srand((unsigned) time(&t));

  pthread_t *pr = malloc(numw*sizeof(pthread_t));
  pthread_t *co = malloc(numw*sizeof(pthread_t));
  //pthread_t pr; 
  //pthread_t co; 

  int number = LOOPS;	
 
  pthread_create(&pr[NUMWORK], NULL, prod_worker, number); 
  pthread_create(&co[NUMWORK], NULL, cons_worker, number); 

  printf("Producing %d matrices in mode %d.\n",NUMBER_OF_MATRICES,MATRIX_MODE);
  printf("Using a shared buffer of size=%d\n", BOUNDED_BUFFER_SIZE);
  printf("With %d producer and consumer thread(s).\n",numw);
  printf("\n");
   

  int prs = 0;
  int cos = 0;
  int prodtot = 0;
  int constot = 0;
  int consmul = 0;

  pthread_join(pr[NUMWORK], (void*)&prs);
  pthread_join(co[NUMWORK], (void*)&cos);

  // consume ProdConsStats from producer and consumer threads
  // add up total matrix stats in prs, cos, prodtot, constot, consmul
 
  prodtot = get_cnt(total -> prod);
  constot = get_cnt(total -> cons); 
  consmul = get_cnt(multTotal); 
  printf("Sum of Matrix elements --> Produced=%d = Consumed=%d\n",prs,cos);
  printf("Matrices produced=%d consumed=%d multiplied=%d\n",prodtot,constot,consmul);

  return 0;
}
