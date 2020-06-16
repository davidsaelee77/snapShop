% @author David Saelee
% @program name Erlang PR2
% @date 11/29/2019
% @doc @todo This program mirrors the behavior of the C program we were tasked
% to build for project 1.  The program will construct two tables that will take user
% input to either add or remove enteries from these tables.

% Project module name
-module(pr2).

% ====================================================================
% API functions
% ====================================================================
% Function export list
-export([myMain/0, printMenu/3, add/3, delete/1, print/1]).


% ====================================================================
% Internal functions
% ====================================================================


%Main function used to initialize tables, take user input
%and to call the menu.
myMain() ->
	
	%Initializes two empty list.
	BlockTable = [],
	DirectoryTable= [],	
	
	%Takes user input to define the size of blocktable
	{ok, [StorageSpace]} = io:fread("Enter the size of your storage device: ", "~d"),
	{ok, [BlockSize]} = io:fread("Enter the size of each block: ", "~d"),
	
	% Assumes StorageSpace is evenly divisible by BlockSize
	printMenu({BlockTable, DirectoryTable}, BlockSize, StorageSpace div BlockSize).


%Takes user input to call desired function.
%@{BlockTable, DirectoryTable} takes an empty tuple
%composed of two list arguments.
%@BlockSize user specified block size.
%TotalBlocks size of the block table
printMenu(Tables, BlockSize, TotalBlocks) ->
	
	%User inputted selction
	{ok, [X]} = io:fread("Do you want to: \nAdd a file? Enter 1 \nDelete a file? Enter 2 \nPrint values? Enter 3 \nQuit? Enter 4 ", "~d"),
	
	%Recursive printMenu call to perform function and return value.
	%%QUIT (#4) IS PROBABLY WRONG HERE BUT TERMINATES THE PROGRAM
	case X of
		1 -> printMenu(add(Tables, BlockSize, TotalBlocks), BlockSize, TotalBlocks);
		2 -> printMenu(delete(Tables), BlockSize, TotalBlocks);
		3 -> printMenu(print(Tables), BlockSize, TotalBlocks);
		4 -> exit(normal)
	
	end. 



%Add elements to the directory table and block table
%the user specfies.
%@{BlockTable, DirectoryTable} takes an empty tuple
%composed of two list arguments.
%@BlockSize user specified block size.
%TotalBlocks size of the block table
add({BlockTable, DirectoryTable}, BlockSize, TotalBlocks) -> 
	
	%Takes user specified input to add to directory table and block table
	{ok, [FileName]} = io:fread("Adding - enter filename: ", "~s"),
	{ok, [FileSize]} = io:fread("Adding - enter file size: ", "~d"),
	
	%At least one block needed else defines blocks needed.
	BlocksNeededInTable = if
							  FileSize rem BlockSize =/= 0 -> FileSize div BlockSize + 1;
							  true -> FileSize div BlockSize
						  end,
	
	% Returns the right-most available index in the list that will hold BlocksNeededInTable, 
	% keeping track of the previous index examined and the best result so far
	NextAvailableIndex = fun I(GivenBlockTable, PrevIdx, Res) ->
								  case GivenBlockTable of
									  [] -> if
												% new file can be placed at the beginning of the list
												PrevIdx > BlocksNeededInTable -> 1; 
												% return the best result we've found
												true -> Res 
											end;
									  
									  [{X, _, _} | Tail] -> if
																% we've found availability, keep looking
																X + BlocksNeededInTable < PrevIdx -> I(Tail, X, X + 1); 
																% not enough room before this element, keep looking
																true -> I(Tail, X, Res) 
															end
								  end
						 end,
	%If there is an empty list then takes first index
	%If list not empty then searches for next available slot
	StartingIndex = case BlockTable of
						[] -> 1;
						[{LastIdx, _, _} | Tl] -> NextAvailableIndex(Tl, LastIdx, LastIdx + 1)
					end,
	
	% Adds the new file to the front of the GivenBlockTable
	AddToFront = fun G(RemainingFileSize, Idx, GivenBlockTable) ->
						  if
							  RemainingFileSize =< BlockSize -> [{Idx, RemainingFileSize, BlockSize - RemainingFileSize} | GivenBlockTable];
							  true -> G(RemainingFileSize - BlockSize, Idx + 1, [{Idx, BlockSize, 0} | GivenBlockTable])
						  end
				 end,
	
	% Iterates through the GivenBlockTable until it finds the correct index, then calls
	% AddToFront to insert the new file at that index
	AddBlocks = fun F(GivenBlockTable) -> case GivenBlockTable of
											  [] -> AddToFront(FileSize, StartingIndex, GivenBlockTable);
											  [{X, Used, Frag} | Tail] ->					 
												  if
													  X < StartingIndex -> AddToFront(FileSize, StartingIndex, GivenBlockTable);
													  true -> [{X, Used, Frag} | F(Tail)]
												  end
										  end
				end,
	
	% Inserts the new file in the DirectoryTable at the correct location for its StartingIndex
	AddToDirectory = fun H(GivenDirectoryTable) -> case GivenDirectoryTable of
													   [] -> [{FileName, FileSize, StartingIndex, BlocksNeededInTable}];
													   [{Name, Size, Idx, Blocks} | Tail] -> if
																								 Idx < StartingIndex -> [{FileName, FileSize, StartingIndex, BlocksNeededInTable} | GivenDirectoryTable];
																								 true -> [{Name, Size, Idx, Blocks} | H(Tail)]
																							 end
												   end
					 end,
	
	if
		StartingIndex + BlocksNeededInTable - 1 > TotalBlocks -> 
			io:fwrite("No room to add file.\n"),
			{BlockTable, DirectoryTable};
		true ->
			
			%Returns a new block table from user inputted values.
			NewBlockTable = AddBlocks(BlockTable), 
			
			%Returns a new directory table with user specified inputs.
			NewDirectoryTable = AddToDirectory(DirectoryTable),
			
			%Returns a tuple of the modified block table and directory table after addition.
			{NewBlockTable, NewDirectoryTable}
	end.


%Deletes enteries from the directotry table
%and block table specified by user.
%@{BlockTable, DirectoryTable} takes an empty tuple
%composed of two list arguments.
delete({BlockTable, DirectoryTable}) ->
	
	%Takes user inputted filename
	{ok, [TargetName]} = io:fread("Deleting - enter filename: ", "~s"),
	
	%Returns the tail and tacks on the front of the directory list after removal of the entry
	RemoveFromDirectory = fun F(GivenDirectoryTable) -> 
								   case GivenDirectoryTable of
									   % List is empty so name not found.
									   [] -> {-1, 0, []};
									   
									   [{FileName, FileSize, Index, NumBlocks} | Tail] ->
										   if
											   % Compares directory file against user input
											   FileName =:= TargetName -> {Index, NumBlocks, Tail}; 
											   true ->
												   {FoundIdx, FoundNumBlocks, NewTail} = F(Tail),
												   % Tack on beginning of the list.
												   NewList = [{FileName, FileSize, Index, NumBlocks} | NewTail],
												   {FoundIdx, FoundNumBlocks, NewList}
										   end
								   end 
						  end,
	%Returns the tail and tacks on the front of the block list after removal of the span of slots
	RemoveFromBlockTable = fun G(TargetStart, GivenNumBlocks, GivenBlockTable) ->							
									case GivenBlockTable of
										%Returns empty list if list is empty.
										[] -> [];
										
										[{BlockNumber, BlockCapacity, Fragmentation} | Tail] ->
											if
												%Loops through the list until index number runs through
												%the span of slots then returns the remainder of list.
												GivenNumBlocks == 0 -> GivenBlockTable;
												BlockNumber == (TargetStart + GivenNumBlocks - 1) -> G(TargetStart, GivenNumBlocks - 1, Tail);
												true ->
													%Tack on the beginning of the list
													NewTail = G(TargetStart, GivenNumBlocks, Tail),
													[{BlockNumber, BlockCapacity, Fragmentation} | NewTail]
											end
									end
						   end,
	
	
	
	{Index, NumBlocks, NewDirectoryTable} = RemoveFromDirectory(DirectoryTable),
	NewBlockTable = RemoveFromBlockTable(Index, NumBlocks, BlockTable),
	
	%Returns a tuple of the modified block table and directory table after deletion.
	{NewBlockTable, NewDirectoryTable}.


%Prints the contents of the directory table
%and the block table.
%@{BlockTable, DirectoryTable} takes an empty tuple
%composed of two list arguments.
print({BlockTable, DirectoryTable}) -> 
	
	%Formated print of the directory table.
	DirectoryString = directoryAsString(DirectoryTable),
	io:fwrite("~nPrinting-~n", []),
	io:fwrite("--------------------------------------------~n", []),
	io:fwrite("Directory Table: \nFilename \tSize \tStart \tLength  ~s~n", [DirectoryString]),
	
	%Formatted print of the block table.
	io:fwrite("--------------------------------------------~n", []),
	BlockString = blockAsString(BlockTable),	
	io:fwrite("Block Table: \nBlock number \tSize used \tFragmened ~s~n", [BlockString]),
	
	{BlockTable, DirectoryTable}.

%Stores the list of block table enteries in string format.
%Converted all integer elements to strings in order to concatenate and
%return as a combined string to be printed.
blockAsString(BlockTable) ->
	
	BlockTableEntry = fun ({BlockIndex, BlockSize, Fragmentation}) ->
							   "\n" ++ integer_to_list(BlockIndex) ++ "\t\t " ++ integer_to_list(BlockSize) ++ " \t\t" ++
								   integer_to_list(Fragmentation) end,
	
	case BlockTable of
		[] -> "";
		[Head | []] -> BlockTableEntry(Head);
		[Head | Tail] -> blockAsString(Tail) ++ BlockTableEntry(Head)
	end.

%Stores the list of directory table enteries in string format
%Converted all integer elements to strings in order to concatenate and
%return as a combined string to be printed.
directoryAsString(DirectoryTable) -> 
	
	DirectoryTableEntry = fun({FileName, FileSize, Index, Length}) ->
								  "\n" ++ FileName ++ " \t\t" ++ integer_to_list(FileSize) ++ "\t " ++
									  integer_to_list(Index) ++ "\t " ++ integer_to_list(Length) end,
	
	case DirectoryTable of
		[] -> "";
		[Head | []] -> DirectoryTableEntry(Head);
		[Head | Tail] -> directoryAsString(Tail) ++ DirectoryTableEntry(Head)
	end.
