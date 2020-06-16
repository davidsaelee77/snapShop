/*
 *  prodcons module
 *  Producer Consumer module
 *
 *  Implements routines for the producer consumer module based on
 *  chapter 30, section 2 of Operating Systems: Three Easy Pieces
 *
 *  University of Washington, Tacoma
 *  TCSS 422 - Operating Systems
 *  Winter 2020
 */

// Include only libraries for this module
#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include "counter.h"
#include "matrix.h"
#include "pcmatrix.h"
#include "prodcons.h"


// Define Locks and Condition variables here

pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER; // initialize lock
pthread_cond_t fill = PTHREAD_COND_INITIALIZER; // initialize conditional
pthread_cond_t empty = PTHREAD_COND_INITIALIZER;
struct __counters_t *total;
struct prodcons *matElements; 
struct __counter_t *multTotal;


// Producer consumer data structures
int count = 0;

// Bounded buffer put() get()
int put(Matrix * value) {
	
	if (count < MAX) {
		//Add matrix to end of bounded buffer	
		bigmatrix[count] = value;
		count++;	
		return 0;	
	}
	return 1; 
}

Matrix * get() {
	
	if (count > 0) {

		//retreieves a matrix from other end
		Matrix *m = bigmatrix[0];
		for (int i = 1; i < count; ++i) {
			bigmatrix[i - 1] = bigmatrix[i];
		}
		bigmatrix[count - 1] = NULL;
    		return m;
	}
	return NULL;
}

int fillb = 0;
int emptyb = 0;
// Matrix PRODUCER worker thread
void *prod_worker(void *arg) {
	

	int *prodCounter = malloc(sizeof(int));
	*prodCounter = 0;
	int i;
	pthread_mutex_lock(&mutex);
	int loops = ((int*)arg);
   	emptyb = 1;
	for (i = 0; i < loops; i++) { 
		Matrix *bm = GenMatrixRandom();
		put(bm);
		(*prodCounter)+= SumMatrix(bm);							
		increment_cnt(total -> prod);
	}
	while(!fillb)
	pthread_cond_wait(&fill, &mutex);
	pthread_cond_signal(&empty);
	pthread_mutex_unlock(&mutex);
	return *prodCounter; 
}


// Matrix CONSUMER worker thread
void *cons_worker(void *arg) {
	

	int *consCounter = malloc(sizeof(int));
	*consCounter = 0; 
	pthread_mutex_lock(&mutex);
	fillb = 1;
    	int loops = ((int*)arg);
	Matrix *m1 = NULL;
	Matrix *m2 = NULL;
	Matrix *m3 = NULL; 
	int i = 0;
	for (i = 0; i < loops; i++) {

		m1 = get();			
		increment_cnt(total->cons);	
		m2 = get();
		increment_cnt(total->cons); 
		i++;
		if (m1 == NULL) {
			m1 = get();
		} else {
			*consCounter += SumMatrix(m1); 
		}
		if (m2 == NULL) {
			m2 = get();
		} else {
			*consCounter += SumMatrix(m2);
		} 
		if (m1 != NULL && m2 != NULL) {
			m3 = MatrixMultiply(m1, m2);
			if (m3 != NULL) {
				increment_cnt(multTotal);
				DisplayMatrix(m3, stdout);
				FreeMatrix(m3);
			}
		}
		
		if (m2 != NULL) {
			FreeMatrix(m2);
		}
		if (m1 != NULL) {	
			FreeMatrix(m1);
		}
	}
	
	pthread_cond_signal(&fill);
	
    while(!emptyb)
    pthread_cond_wait(&empty, &mutex);
    pthread_mutex_unlock(&mutex);


    return *consCounter;
}
