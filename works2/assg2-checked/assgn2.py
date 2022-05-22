import numpy as np
import random
import sys

#### QI.1 ####
arrayQ1 = np.array([])

for i in range(1,10):
    for j in range(1,10):
        arrayQ1 = np.append(arrayQ1, i*j)   #arrayQ1 filling with i*j 
arrayQ1 = arrayQ1.reshape(9,9)              #form of arrayQ1 is 1D, we convert 2D reshape 

print("#### QI.1 ####")
print()
print(arrayQ1)
print()


#### QI.2 ####
arrayQ2 = np.array([])

for i in range(1,5):
    for j in range(1,5):
        arrayQ2 = np.append(arrayQ2, random.randint(5,35))  #arrayQ2 filling with random integer
arrayQ2 = arrayQ2.reshape(4,4)              #converting 2D

print("#### QI.2 ####")
print()
print(arrayQ2)
print()


#### QI.3 ####

arrayOnesQ3 = np.ones((2,3))        #making array with ones. [2 rows, 3 columns]
arrayZerosQ3 = np.zeros((3,3))      #making array with zeros.[3 rows, 3 columns]
arrayValueQ3 = np.full((2,5), 7)    #making array with given (seven). [2 rows, 5 columns]

print()
print("#### QI.3 ####")
print()
print(arrayOnesQ3)
print()
print(arrayZerosQ3)
print()
print(arrayValueQ3)

#### QI.4 ####

arrayIncrQ4 = np.arange(2,20,2)         #increment two by two until 20 and appending to array
arrayIncrQ4 = arrayIncrQ4.reshape(3,3)  #converting 2D array

arrayDecrQ4 = np.arange(9,0,-1)         #decrement one by one until 0 from 10 and appending to array
arrayDecrQ4 = arrayDecrQ4.reshape(3,3)  #converting 2D array

arrayMultQ4 = np.array([])

for i in range(3):
    for j in range(3):
        arrayMultQ4 = np.append(arrayMultQ4, arrayIncrQ4[i][j]*arrayDecrQ4[i][j]) #multipling the previous two series and appending to new array 

arrayMultQ4 = arrayMultQ4.reshape(3,3) #converting 2D array

print()
print("#### Q1.4 ####")
print()
print(arrayIncrQ4)
print()
print(arrayDecrQ4)
print()
print(arrayMultQ4)




#### QI.5 ####

print()
print("#### Q1.5 ####")
print()


arrayQ5 = np.array([])

for i in range(1,16):
        arrayQ5 = np.append(arrayQ5, i)    #creating a new array with integers[1-15]

arrayQ5 = arrayQ5.reshape(3,5) #converting 2D

print("Input Array:")
print(arrayQ5)
print()

print("a-)")
print(arrayQ5[2])   #printing row 2
print()

arrayColumnQ5 = np.array([])
arrayColumnQ5 = np.append(arrayColumnQ5, arrayQ5[:, [4]]) #appending to new array with condition

print("b-)")
print(arrayColumnQ5)
print()

arrayColumnsQ5 = np.array([])
arrayColumnsQ5 = arrayQ5[:, [0,1]] #appending to new array with condition

print("c-)")
print(arrayColumnsQ5)
print()

arrayColumnssQ5 = np.array([])
arrayColumnssQ5 = arrayQ5[:, [2,3,4]] #appending to new array with condition

print("d-)")
print(arrayColumnssQ5)
print()

arraySelectedElementQ5 = 0;
arraySelectedElementQ5 = arrayQ5[1, 4] #selecting element  

print("e-)")
print(arraySelectedElementQ5)
print()


arraySelectedElementsQ5 = np.array([])
for i in range(1,3):
    for j in range(0, 6, 2):
        arraySelectedElementsQ5 = np.append(arraySelectedElementsQ5, arrayQ5[[i], [j]]) #Selecting all elements from rows 1 and 2 that are in columns 0, 2 and 4.

arraySelectedElementsQ5 = arraySelectedElementsQ5.reshape(2,3) #converting 2D

print("f-)")
print(arraySelectedElementsQ5)
print()

#### QI.6 ####

arrayQ6 = [[10,10,90,90],
[40,40,60,60],
[55,55,65,65],
[10,30,60,90]]


print("#### Q1.6 ####")
print()
print(np.amin(arrayQ6, 1), np.amax(arrayQ6, 1), np.mean(arrayQ6, 1)) 
print()
print(np.std(arrayQ6, 1))


#### QII ####

def readArray(numberOfRows, numberOfColumns, file):
    array = []  # areate an empty list
    for row in range(numberOfRows):
        array.append([])      #add an empty new row
        line = file.readline() #taking file line by line
        rowdata = [int(x) for x in line.split(' ')] #converting list to line
        for column in range(numberOfColumns):
            array[row].append(rowdata[column]) #filling array with fetched data
    return array


def printArray(array):
    for row in range(len(array)):
        for column in range(len(array[row])):
            print(format(array[row][column], "5d"), end=" ") #printing current data
        print()  # print a new line


def fillArrayRandomly(numberOfRows, numberOfColumns):
    array = []  # create an empty list
    for row in range(numberOfRows):
        array.append([])  # add an empty new row
        for column in range(numberOfColumns):
            array[row].append(random.randint(0, 99)) #filling array randomly
    return array

def generateZeroArray(numberOfRows, numberOfColumns):
    array = [[0 for i in range(numberOfRows)] for j in range(numberOfColumns)] #filling with 0  
    return array

def addArray(A, B):
    C = generateZeroArray(len(A), len(A[0])) #creating empty array for sum and fill givens
    for row in range(len(A)):
        for column in range(len(A[row])):
            C[row][column] = A[row][column] + B[row][column] #filling new array

    return C

def subtractArray(A,B):
    C = generateZeroArray(len(A), len(A[0]))  #creating empty array for subtract and fill givens
    for row in range(len(A)):
        for column in range(len(A[row])):
            C[row][column] = A[row][column] - B[row][column] #filling new array
    return C

def maxOfElements(A):
    max_val=A[0][0] #taking first element of given array for compare 
    for i in range(len(A)):
        for j in range(len(A[i])):
            max_val=max(max_val,A[i][j]) #compare max_val with element of array and result is assign to max_val
    return max_val #loops ending when max_val is found and it returns

def transpose(A):
    B=generateZeroArray(len(A),len(A[0]))  #creating empty array
    for i in range(len(A)):
        for j in range(len(A[i])):
            B[i][j]=A[j][i]     #taking transpose current value and assing to new array
    return B

def multiplyArray(A, B):
    C=generateZeroArray(len(A),len(B[0])) #creating empty array
    for i in range(len(A)):
        for j in range(len(B[0])):
            for k in range(len(B)):
                C[i][j] += A[i][k] * B[k][j]
    return C





print()
print("-------------------------------------------------------")
print()
print("#### Q2 ####")

print("\nReading data from inputs.txt file in current directory\n")
f = open("inputs.txt", "r")

line = f.readline()

numberOfRows, numberOfColumns = [int(x) for x in line.split(' ')] #taking rows and columns size
A = readArray(numberOfRows, numberOfColumns, f) #reading 2D array from file and assing to A
print()
print(" **** Matrix A **** ")
printArray(A)


line = f.readline()
numberOfRows, numberOfColumns = [int(x) for x in line.split(' ')] #taking rows and columns size
B = readArray(numberOfRows, numberOfColumns, f) #reading 2D array from file and assing to B
print()
print(" **** Matrix B **** ")
printArray(B)

line = f.readline()
numberOfRows, numberOfColumns = [int(x) for x in line.split(' ')] #taking rows and columns size
C = readArray(numberOfRows, numberOfColumns, f) #reading 2D array from file and assing to C
print()
print(" **** Matrix C **** ")
printArray(C)

# Generate 4x4 Array from random numbers.
D = fillArrayRandomly(numberOfRows, numberOfColumns)
print()
print(" **** Matrix D **** ")
printArray(D)

# Compute S = (A+B) * Transpose(C) + D - A
print("\n *** Computing S = (A+B) * Transpose(C) + D) - A *** \n")

# T1 = A + B
T1 = addArray(A, B)
print()
print(" **** Matrix T1 = (A+B) ****")
printArray(T1)

# T2 = Transpose(C)
T2= transpose(C)
print()
print("**** Matrix T2 = Transpose(C) ****")
printArray(T2)

# T3 = ( A + B )* transpose( C )
T3=multiplyArray(T1,T2)
print()
print("**** Matrix T3 =(A+B) * transpose(C) ****")
printArray(T3)

# T4 =( A + B )* transpose( C ) + D
T4=addArray(T3,D)
print()
print("**** Matrix T4 =(A+B) * transpose(C)+ D ****")
printArray(T4)

# S = ( A + B )* transpose( C ) + D -A
S=subtractArray(T4,A)
print()
print("**** Matrix S =(A+B) * transpose(C) + D - A ****")
printArray(S)
max_val=maxOfElements(S)
print("Maximum Element in S = {} ".format(max_val))
