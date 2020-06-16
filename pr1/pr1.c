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


//Add method that takes 4 arguments and returns a integers.
//@*Values directory table array pointer.
//@**blockTable block table array double pointer.
//@blockSisze user inputted block size specificaiton.
//@fileCount count of directory table insertion/deletes.
int add(Data *directoryTable, int **blockTable, int row, int blockSize, int fileCount) {

	char input[20];
	printf("Adding - enter filename: ");
	scanf("%s", input);
	

	int sizeInput = 0;
	printf("Adding - enter file size:  ");
	scanf("%d", &sizeInput);
	
	//Determines how many blocks needed for new entry.
	int numberOfBlocksNeeded = (sizeInput / blockSize); 

	//At least 1 block needed if there is a remainder.
	if ((sizeInput % blockSize) != 0) { 
		numberOfBlocksNeeded++;
	}
	int i = 0;
	int count;
	for (i = 0; i < row; i++) {
		//Finds first rows where block is empty.
		if (*(blockTable[i]) == 0) {
			count = 1;
			//Keep tally of how many empty rows are available.
			while (count + i < row && *blockTable[count + i] == 0) {
				count++;
			}
			//If there are enough rows then fill in data.
			if (count >= numberOfBlocksNeeded) {

				//Adds information into directory table.
				int startingIndex = i;
				strcpy(directoryTable[fileCount].filename, input);
				directoryTable[fileCount].size = sizeInput;
				directoryTable[fileCount].start = startingIndex;
				directoryTable[fileCount].length = numberOfBlocksNeeded;

				int k = 0;
				int fileRemaining = sizeInput;
				//Starts at index where there is enough room to fill blocks.
				for (k = startingIndex;
						k < startingIndex + numberOfBlocksNeeded; k++) {
					//Fills first column with full amount of blockSize.
					if (fileRemaining >= blockSize) {

						blockTable[k][0] = blockSize;
						blockTable[k][1] = 0;
						fileRemaining = fileRemaining - blockSize;
					//If block is full then remainder of memory is placed in
					//second column.
					} else {

						blockTable[k][0] = fileRemaining;
						blockTable[k][1] = blockSize - fileRemaining;

					}

				}
				//Returns true if an insertion was successful.
				return 1;

			}

		}

	}
	//Prints statement and returns false if there is not enough room to insert.
	printf("Add unsuccessful.  Not enough memory to store file. \n");
	return 0;

}

//Delete method that removes directory table entry and blockTable entry.
//@*Values directory table array pointer.
//@**blockTable block table array double pointer.
//@fileCount count of directory table insertion/deletes.
//@row the index of the blockTable.
int delete(Data *directoryTable, int **blockTable, int fileCount, int row) {

	char *fileName[20];
	printf("Deleting - enter filename: ");
	scanf("%s", &fileName);

	int i = 0;
	//Loops through the directory table array.
	for (i = 0; i < fileCount; i++) {
		//Compares directory table enteries with user inputted specficiation.
		if (strcmp(fileName, directoryTable[i].filename) == 0) {

			//Initializes the starting index in loop to remove blockTable values.
			int startingIdx = directoryTable[i].start;

			int j;
			//I didn't know how to free memory in the middle of a fixed 2D array so 
			//I just replaced the values with 0.
			//Loops through the blockTable and replaces values with 0.
			for (j = 0; j < directoryTable[i].length; j++) {
				blockTable[startingIdx + j][0] = 0;
				blockTable[startingIdx + j][1] = 0;

			}

			//Shifts directory table elements to fill vacant index.
			for (j = i; j < fileCount - 1; j++) {

				directoryTable[j] = directoryTable[j + 1];

			}
			//Returns true if delete was successful.
			return 1;
		}

	}
	//Returns false if delete was unsuccessful.
	printf("File does not exist.  Please try again \n");
	return 0;
}

//Prints the contents of the blockTable and directory table.
//@*Values directory table array pointer.
//@**blockTable block table array double pointer.
//@fileCount count of directory table insertion/deletes.
//@row the index of the blockTable.
void print(Data *directoryTable, int **blockTable, int fileCount, int row) {

	int i;
	//Prints line across screen.
	printf("Printing -\n");
	for (i = 0; i < 60; i++) {
		printf("-");
	}
	printf("\n");
	printf("Directory table: \n");
	printf("Filename	Size	Start	Length\n");
	//Loops through the directory table and prints the contents.
	for (i = 0; i < fileCount; i++) {
		printf(" %s\t\t", directoryTable[i].filename);
		printf(" %d\t", directoryTable[i].size);
		printf(" %d\t", directoryTable[i].start);
		printf(" %d\t", directoryTable[i].length);
		printf("\n");
	}

	//Prints line across screen.
	for (i = 0; i < 60; i++) {
		printf("-");
	}
	printf("\n");
	printf("Block table: \n");
	printf("Block number	Size used	Fragmented\n");

	//Loops through the blockTable and prints the contents.
	for (i = 0; i < row; i++) {
		printf(" %d\t\t", i);
		printf((" %d\t\t"), blockTable[i][0]);
		printf((" %d\t"), blockTable[i][1]);
		printf("\n");
	}
	
	//Prints line across screen.
	for (i = 0; i < 60; i++) {
		printf("-");
	}
	printf("\n");

}

