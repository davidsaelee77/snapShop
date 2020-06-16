#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/wait.h>
#include <signal.h>
#define MAXSTR 255

void INThandler(int); //Taken from website.

/****************************************************************************//**

  @author       David Saelee
  @date		2/14/19
  @file         MyShell.c
  
*******************************************************************************/

/**

DONOT change the existing function definitions. You can add functions, if necessary.

*/

/**

  @brief Fork a child to execute the command using execvp. The parent should wait for the child to terminate
  @param args Null terminated list of arguments (including program).
  @return returns 1, to continue execution and 0 to terminate the MyShell prompt.

 */


int execute(char **args) {

	int status = 0;
	int pid = 0;

	pid = fork();
	
	if(pid < 0) //Determines if child process is spawned.  Prints error message if child process is unsuccessful.
	{

		printf("Fork command has failed\n");
		free(args);
		args = NULL;
		exit(1);

	} 
	else if (pid == 0) //If pid == 0 the commands are executed in the child process but not in the parent process.
	{
		if(args[0] != NULL) { // Checks first element of array to determine if it is NULL.


			if(strcmp (args[0], "exit") == 0)  //Compares 'exit' to the element at the first array index.
			{				
				return 0;							
			}		

			else if (execvp (args[0], args) < 0) //Execute each command and if there is an error will print error message.
			{
			
				printf("Error executing command.\n");
				free(args);
				args = NULL;
				return 0;
			}

		
		}

	}
	else
	{
		while(wait (&status) != pid); // Places the parent process in wait until child process is terminated.			

	}

	free(args);

	if(args != NULL)  //This check is needed here because execvp does not recognize 'exit' as a command so will print an error in the child process before
			  //exiting program.  This ensures once the child process is terminated that the parent will catch 'exit' and terminate.
	{		

		if(strcmp (args[0], "exit") == 0) {		
			return 0;	
		}					
	}
	

	return 1;
}

/**
  @brief gets the input from the prompt and splits it into tokens. Prepares the arguments for execvp
  @return returns char** args to be used by execvp
 */

char **parse(void) {

	char input[MAXSTR];
	char *tokens = NULL;
	char** arr = NULL;

	fgets(input, sizeof(input), stdin); //Takes line of input from the user.
	
	tokens = strtok(input, " \n"); //Tokenizes each string by checking spaces and new line characters.

	if (tokens != NULL)  //Check to see if user types in valid input and only allocate memory if there is valid input.
	{	
		arr = realloc(arr, sizeof(char*) * (MAXSTR));
	}
	
	
	int i = 0;
	while(tokens != NULL) // Iterates through tokens and checks positions of deliminiters and adds tokenized words to arr array.
	{
		arr[i] = tokens;
		i++;
		tokens = strtok(NULL, " \n");
	}


	return arr;
}

/**
I took this code from this website: http://www.csl.mtu.edu/cs4411.ck/www/NOTES/signal/install.html.  I don't know if this is the correct behavior because Ctrl C on terminal kills my program so I can't really test if it's catching the Ctrl C in my shell.  A couple of print statement show the signal
transmits to this method but can not confirm what actually happens. 
*/
void INThandler(int sig) {

	signal(sig, SIG_IGN);
	exit(0);
}


/*
   @brief Main function should run infinitely until terminated manually using CTRL+C or typing in the exit command
   It should call the parse() and execute() functions
   @param argc Argument count.
   @param argv Argument vector.
   @return status code
 */

int main(int argc, char **argv) {

	int exeResult = 1; //Initialize to 1 in order to go into while loop.
	char **parseResult = NULL;

	signal(SIGINT, INThandler); //Taken from same website.
	while(exeResult)
	{
		printf("MyShell>");
		parseResult = parse(); //Calls parse method to handle input. 
		exeResult = execute(parseResult); //Calls execute method to run command.
	}

	return EXIT_SUCCESS;
}
