f = open("inputs.txt", "r")

line = f.readline() #reading file line by line
rowdata = [int(x) for x in line.split(' ')]