Last login: Fri Nov 29 18:05:16 on ttys000

The default interactive shell is now zsh.
To update your account to use zsh, please run `chsh -s /bin/zsh`.
For more details, please visit https://support.apple.com/kb/HT208050.
OFFICE COMP:~ david$ erl
Erlang/OTP 22 [erts-10.5.3] [source] [64-bit] [smp:4:4] [ds:4:4:10] [async-threads:1] [hipe] [dtrace]

Eshell V10.5.3  (abort with ^G)
Erlang/OTP 22 [erts-10.5] [64-bit] [smp:4:4] [ds:4:4:10] [async-threads:1]

1> cd("c:/Users/OFFICE COMP/Desktop/pr2").
c:/Users/OFFICE COMP/Desktop/pr2
ok
2> c(pr2).
{ok,pr2}
3> pr2:myMain(). 
Enter the size of your storage device: 40960
Enter the size of each block: 4096
Do you want to: 
Add a file? Enter 1 
Delete a file? Enter 2 
Print values? Enter 3 
Quit? Enter 4 1
Adding - enter filename: file1.txt
Adding - enter file size: 12000
Do you want to: 
Add a file? Enter 1 
Delete a file? Enter 2 
Print values? Enter 3 
Quit? Enter 4 3

Printing-
--------------------------------------------
Directory Table: 
Filename        Size    Start   Length  
file1.txt               12000    1       3
--------------------------------------------
Block Table: 
Block number    Size used       Fragmened 
1                4096           0
2                4096           0
3                3808           288
Do you want to: 
Add a file? Enter 1 
Delete a file? Enter 2 
Print values? Enter 3 
Quit? Enter 4 1
Adding - enter filename: file2.txt
Adding - enter file size: 8000
Do you want to: 
Add a file? Enter 1 
Delete a file? Enter 2 
Print values? Enter 3 
Quit? Enter 4 3

Printing-
--------------------------------------------
Directory Table: 
Filename        Size    Start   Length  
file1.txt               12000    1       3
file2.txt               8000     4       2
--------------------------------------------
Block Table: 
Block number    Size used       Fragmened 
1                4096           0
2                4096           0
3                3808           288
4                4096           0
5                3904           192
Do you want to: 
Add a file? Enter 1 
Delete a file? Enter 2 
Print values? Enter 3 
Quit? Enter 4 1
Adding - enter filename: file3.txt
Adding - enter file size: 6000
Do you want to: 
Add a file? Enter 1 
Delete a file? Enter 2 
Print values? Enter 3 
Quit? Enter 4 3

Printing-
--------------------------------------------
Directory Table: 
Filename        Size    Start   Length  
file1.txt               12000    1       3
file2.txt               8000     4       2
file3.txt               6000     6       2
--------------------------------------------
Block Table: 
Block number    Size used       Fragmened 
1                4096           0
2                4096           0
3                3808           288
4                4096           0
5                3904           192
6                4096           0
7                1904           2192
Do you want to: 
Add a file? Enter 1 
Delete a file? Enter 2 
Print values? Enter 3 
Quit? Enter 4 1
Adding - enter filename: file4.txt
Adding - enter file size: 80000
No room to add file.
Do you want to: 
Add a file? Enter 1 
Delete a file? Enter 2 
Print values? Enter 3 
Quit? Enter 4 3

Printing-
--------------------------------------------
Directory Table: 
Filename        Size    Start   Length  
file1.txt               12000    1       3
file2.txt               8000     4       2
file3.txt               6000     6       2
--------------------------------------------
Block Table: 
Block number    Size used       Fragmened 
1                4096           0
2                4096           0
3                3808           288
4                4096           0
5                3904           192
6                4096           0
7                1904           2192
Do you want to: 
Add a file? Enter 1 
Delete a file? Enter 2 
Print values? Enter 3 
Quit? Enter 4 2
Deleting - enter filename: file2.txt
Do you want to: 
Add a file? Enter 1 
Delete a file? Enter 2 
Print values? Enter 3 
Quit? Enter 4 3

Printing-
--------------------------------------------
Directory Table: 
Filename        Size    Start   Length  
file1.txt               12000    1       3
file3.txt               6000     6       2
--------------------------------------------
Block Table: 
Block number    Size used       Fragmened 
1                4096           0
2                4096           0
3                3808           288
6                4096           0
7                1904           2192
Do you want to: 
Add a file? Enter 1 
Delete a file? Enter 2 
Print values? Enter 3 
Quit? Enter 4 2
Deleting - enter filename: file1.txt
Do you want to: 
Add a file? Enter 1 
Delete a file? Enter 2 
Print values? Enter 3 
Quit? Enter 4 3

Printing-
--------------------------------------------
Directory Table: 
Filename        Size    Start   Length  
file3.txt               6000     6       2
--------------------------------------------
Block Table: 
Block number    Size used       Fragmened 
6                4096           0
7                1904           2192
Do you want to: 
Add a file? Enter 1 
Delete a file? Enter 2 
Print values? Enter 3 
Quit? Enter 4 1
Adding - enter filename: file5
Adding - enter file size: 20000
Do you want to: 
Add a file? Enter 1 
Delete a file? Enter 2 
Print values? Enter 3 
Quit? Enter 4 3

Printing-
--------------------------------------------
Directory Table: 
Filename        Size    Start   Length  
file5           20000    1       5
file3.txt               6000     6       2
--------------------------------------------
Block Table: 
Block number    Size used       Fragmened 
1                4096           0
2                4096           0
3                4096           0
4                4096           0
5                3616           480
6                4096           0
7                1904           2192
Do you want to: 
Add a file? Enter 1 
Delete a file? Enter 2 
Print values? Enter 3 
Quit? Enter 4 1
Adding - enter filename: file6.txt
Adding - enter file size: 5000
Do you want to: 
Add a file? Enter 1 
Delete a file? Enter 2 
Print values? Enter 3 
Quit? Enter 4 3

Printing-
--------------------------------------------
Directory Table: 
Filename        Size    Start   Length  
file5           20000    1       5
file3.txt               6000     6       2
file6.txt               5000     8       2
--------------------------------------------
Block Table: 
Block number    Size used       Fragmened 
1                4096           0
2                4096           0
3                4096           0
4                4096           0
5                3616           480
6                4096           0
7                1904           2192
8                4096           0
9                904            3192
Do you want to: 
Add a file? Enter 1 
Delete a file? Enter 2 
Print values? Enter 3 
Quit? Enter 4 2
Deleting - enter filename: file5
Do you want to: 
Add a file? Enter 1 
Delete a file? Enter 2 
Print values? Enter 3 
Quit? Enter 4 3

Printing-
--------------------------------------------
Directory Table: 
Filename        Size    Start   Length  
file3.txt               6000     6       2
file6.txt               5000     8       2
--------------------------------------------
Block Table: 
Block number    Size used       Fragmened 
6                4096           0
7                1904           2192
8                4096           0
9                904            3192
Do you want to: 
Add a file? Enter 1 
Delete a file? Enter 2 
Print values? Enter 3 
Quit? Enter 4 2
Deleting - enter filename: file3.txt
Do you want to: 
Add a file? Enter 1 
Delete a file? Enter 2 
Print values? Enter 3 
Quit? Enter 4 3

Printing-
--------------------------------------------
Directory Table: 
Filename        Size    Start   Length  
file6.txt               5000     8       2
--------------------------------------------
Block Table: 
Block number    Size used       Fragmened 
8                4096           0
9                904            3192
Do you want to: 
Add a file? Enter 1 
Delete a file? Enter 2 
Print values? Enter 3 
Quit? Enter 4 2
Deleting - enter filename: file6.txt
Do you want to: 
Add a file? Enter 1 
Delete a file? Enter 2 
Print values? Enter 3 
Quit? Enter 4 3

Printing-
--------------------------------------------
Directory Table: 
Filename        Size    Start   Length  
--------------------------------------------
Block Table: 
Block number    Size used       Fragmened 
Do you want to: 
Add a file? Enter 1 
Delete a file? Enter 2 
Print values? Enter 3 
Quit? Enter 4 4
** exception exit: normal