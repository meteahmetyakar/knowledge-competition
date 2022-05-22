#include <stdio.h>
#include <stdlib.h>


typedef struct{ int *array; int currentsize;} dynamic_array;

dynamic_array read_from_file(char *filename, dynamic_array arr);

dynamic_array removeData(dynamic_array arr, int number);

void print_array(dynamic_array arr);

int *resize_array(int *array, int currentsize);

int main()
{
    dynamic_array arr;
    arr.currentsize = 5;
	arr.array = malloc(arr.currentsize * sizeof(int));

    arr = read_from_file("input.txt", arr);
    printf("Data in source file \n*******************\n");
    print_array(arr);

    printf("\n\nsize increased 1\n");
    arr.array = resize_array(arr.array, arr.currentsize);
    arr.currentsize += 1;
    print_array(arr);

    int num;
    printf("\n\nenter delete number : ");
    scanf("%d", &num);
    print_array(removeData(arr,num));


}

dynamic_array read_from_file(char *filename, dynamic_array arr)
{
    FILE* file;
    file = fopen(filename, "r");

    for(int i=0; i<arr.currentsize; i++)
    {
        fscanf(file, "%d ", (arr.array+i));
    }

    fclose(file);

    return arr;
}

void print_array(dynamic_array arr)
{
    for(int i=0; i<arr.currentsize; i++)
        printf("%d\n", *(arr.array+i));
}

dynamic_array removeData(dynamic_array arr, int number)
{
    int isFound=0;
    dynamic_array tempArray;
    tempArray.currentsize = arr.currentsize-1;
    int j=0, k=0;
    for(int i=0; i<arr.currentsize; i++)
    {
        if(*(arr.array+i) == number)
        {
            isFound = 1;
	        tempArray.array = calloc((tempArray.currentsize), sizeof(int));
            while(k<arr.currentsize)
            {
                if(k!=i)
                {
                    *(tempArray.array+j) = *(arr.array+k);
                    j++;
                }
                    k++;
            }
        }
    }

    if(isFound)
        return tempArray;
    else
        return arr;
}

int *resize_array(int *array, int currentsize)
{
    int *tempArray;
    int i=0;
	tempArray = calloc((currentsize+1), sizeof(int));
    for(i=0; i<currentsize; i++)
    {
        *(tempArray+i) = *(array+i);
    }

    return tempArray;
}
