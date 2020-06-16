//PLEASE USER LINUX VM TO RUN THIS CODE.

/*
David Saelee
TCSS 380
PROJECT 1
*/

//Library imports and header import.
#include "pr1.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>


//Client code use to initialize memory, keep track of indices, and take user input.
int main(void) {

	int fileCount = 0;
	int storageSpace;
	printf("Enter the size of your storage device: ");
	scanf(" %d", &storageSpace);

	int blockSize;
	printf("Enter the size of each block: ");
	scanf(" %d", &blockSize);

	//Allocates and initializes memory for the directory table.
	Data *directoryTable;
	directoryTable = calloc((storageSpace / blockSize), sizeof(Data));

	//array of rows in the block table
	int row = (storageSpace / blockSize); 

	// double pointer array that points to the value in the block table.
	int **blockTable = malloc(row * sizeof(int*)); 

	int k = 0;
	
	for (k = 0; k < row; k++) {
		// Allocates memory and creates the block table rows.
		blockTable[k] = malloc(2 * sizeof(int)); 
		blockTable[k][0] = 0;
		blockTable[k][1] = 0;
	}
	//User interface options.
	int select = 0;
	int done = 0;
	while (!done) {
		printf("Do you want to: \n");
		printf("Add a file? Enter 1 \n");
		printf("Delete a file? Enter 2 \n");
		printf("Print values? Enter 3 \n");
		printf("Quit? Enter 4 \n");
		printf(" ");
		scanf("%d", &select);
		
		//Calls add method.
		if (select == 1) {

			int result = add(directoryTable, blockTable, row, blockSize, fileCount);
			//If file was added successfully then increment file count.
			if (result == 1) {
				fileCount++;
			}
		//Calls delete method.	
		} else if (select == 2) {
			//If file was deleted successfully then deincrement file count.
			fileCount = fileCount - delete(directoryTable, blockTable, fileCount, row);

		//Calls print method.
		} else if (select == 3) {

			print(directoryTable, blockTable, fileCount, row);
		//Quits program
		} else {

			done = 4;
		}
	}
	//Frees allocated memory for the blockTable and directoryTable.
	int i;	
	for(i = 0; i < row; i++) {
		free(blockTable[i]);
	}	
	free(blockTable);
	free(directoryTable);

	return 0;
}
