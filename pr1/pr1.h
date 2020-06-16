/*
David Saelee
TCSS 380
PROJECT 1
*/


#ifndef PR1_H
#define PR1_H

//Structure used to define contents in the directory table.
struct dataObject {
	char filename[20];
	int size;
	int start;
	int length;
};

//Typedef to rename structure object to Data.
typedef struct dataObject Data;

//Method prototypes and their argurments.
int add(Data *directoryTable, int **blockTable, int row, int blockSize, int fileCount);
int delete(Data *directoryTable, int **blockTable, int fileCount, int row);
void print(Data *directoryTable, int **blockTable, int fileCount, int row);


#endif
