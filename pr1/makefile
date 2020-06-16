pr1.out: driver.o pr1.o
	gcc -o pr1.out driver.o pr1.o

driver.o: driver.c pr1.h
	gcc -c driver.c

pr1.o: pr1.c pr1.h
	gcc -c pr1.c
