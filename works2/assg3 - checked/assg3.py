import numpy as np
import pandas as pd
import matplotlib.pyplot as plt



#### Q1 ####"
#a
print("Q1 a-)\n")
s = pd.Series([7,11,13,17])
print(s)
print()



#b
print("Q1 b-)\n")
s1 = pd.Series(100.0,index=[0,1,2,3,4]) #creating 5 size array and filling it 100's
print(s1)
print()

#c
print("Q1 c-)\n")
s2 = pd.Series(np.random.randint(0, 100, size = 20)) #creating randomly array
s2.describe()
print(s2)
print()


#d
print("Q1 d-)\n")
temperatures = pd.Series([98.6,98.9,100.2,97.9],index=['Julie','Charlie','Sam','Andrea']) #creating a serie and filling floats with keys
print(temperatures)
print()


#e
s3 = pd.Series({'Julie':98.6 ,'Charlie':98.9,'Sam':100.2,'Andrea':97.9}) 



#### Q2 ####

#a)
#definig the dictionary
D={
    'Maxine':[23,54,67],
    'James':[23,56,89],
    'Amanda':[56,43,24]
}

temparatures=pd.DataFrame(D) #creating dataFrame

print("Q2 a-)\n")
print(temparatures)
print()

#b)
temparatures=pd.DataFrame(D,index=["Morning","Afternoon","Evening"])
print("Q2 b-)\n")

print(temparatures)
print()

#c)
print("Q2 c-)\n")
c=temparatures[['Maxine']] #selecting the maxine column
print(c)
print()

#d)

print("Q2 d-)\n")
d=temparatures.loc[["Morning"],:] #selecting the row Morning using the loc[] method
print(d)
print()

#e)
print("Q2 e-)\n")
e=temparatures.loc[["Morning","Evening"],:] #selecting the rows for Morning and Evening using the loc[] method
print(e)
print()

#f)

print("Q2 f-)\n")
f=temparatures[["Amanda","Maxine"]] #selecting column Amanda and row Maxine
print(f)
print()

#g)
#selecting the data under columns Amanda,Maxine and rows Morning,Afternoon
print("Q2 g-)\n")
g=temparatures[["Amanda","Maxine"]].loc[["Morning","Afternoon"]]
print(g)
print()

#h)
print("Q2 h-)\n")
h=temparatures.describe()
print(h)
print()

#i)
print("Q2 i-)\n")
i=temparatures.transpose() #creating the transpose of the temparatures
print(i)
print()

#j)
print("Q2 j-)\n")
j=temparatures.reindex(sorted(temparatures.columns),axis=1) #sorting temperatures so that its column names are in alphabetical order.
print(j)
print()


#### Q3 ####
pd.set_option('display.max_rows', 500)
pd.set_option('display.max_columns', 500)


## Reading CSV Files ##
employees = pd.read_csv(r"EMPLOYEES.csv")

departments = pd.read_csv(r"DEPARTMENTS.csv")

job_history = pd.read_csv(r"JOB_HISTORY.csv")

jobs = pd.read_csv(r"JOBS.csv")

countries = pd.read_csv(r"COUNTRIES.csv")

regions = pd.read_csv(r"REGIONS.csv")

locations = pd.read_csv(r"LOCATIONS.csv")
## End Of Reading ##

#a
print("Q3 a-)\n")
print(departments)
print()


#b
## printing number of records of all tables
print("Q3 b-)\n")
print("### Number Of All Records ###\n")
print("Employees.csv : %d" % len(employees))

print("departments.csv : %d" % len(departments))

print("job_history.csv : %d" % len(job_history))

print("jobs.csv : %d" % len(jobs))

print("countries.csv : %d" % len(countries))

print("regions.csv : %d" % len(regions))

print("locations.csv : %d" % len(locations))
print()


#c
print("Q3 c-)\n")

result = employees.sort_values('first_name', ascending=False) #sorting by first_name 

print("First Name      Last Name       Salary    Department Id:")
for index, row in result.iterrows(): #checking all rows
    if(int(str(row['salary']).ljust(9)) > 10000): #if condition is true, printing the row
        print(row['first_name'].ljust(15),row['last_name'].ljust(15),str(row['salary']).ljust(9),row['department_id']) #I used ljust for align columns

print()


#d
values = {'commission_pct': 0}
result.fillna(value=values, inplace=True) #NaN values converting to 0

#e
## printing who work in departments with ids 30, 50 or 80.
print("Q3 e-)\n")
print("First Name      Last Name       Salary    Department Id:")
for index, row in result.iterrows(): #checking all rows
    if(row['department_id'] in [30,50,80]): #if value of deparment_id column in current row is in list, print it
        print(row['first_name'].ljust(15),row['last_name'].ljust(15),str(row['salary']).ljust(9),row['department_id']) #I used ljust for align columns

print()

#f
empt_dept = pd.merge(employees, departments, on=["department_id"]) #merging employees and departments by department_id

#g
#finding min max mean salaries

print("Q3 g-)\n")
department_names = empt_dept['department_name'].unique() #taking unique values of department_name for check names
df = pd.DataFrame()
department_names = np.sort(department_names) #sorting
print("Salaries")
print("Department Name      min     max     mean")
for names in department_names:
    df = empt_dept.loc[(empt_dept['department_name'] == names)] #getting values ​​containing variable names
    al = df['salary'] #getting only salary column
    print(names.ljust(20) ,str(np.min(al)).ljust(7), str(np.max(al)).ljust(7), str(np.mean(al)).ljust(7)) #printing min max mean values of current namess
    space = "           "

print()

#h
emp_loc = pd.merge(locations, empt_dept, on=["location_id"])
country_names = emp_loc['country_id'].unique() #taking unique values in country_id
country_names = np.sort(country_names)          #sorting

city_names = emp_loc['city'].unique() #taking unique values in city
city_names = np.sort(city_names)    #sorting

data2 = {'country_id':[], 'city':[],'0, 5000':[], '5000, 10000':[], '10000, 15000':[], '15000, 25000':[]} #creating new dict, we use this for template
result = pd.DataFrame(data2) #creating dataframe by data2 template
temp = pd.DataFrame(data2)  #creating dataframe by data2 template

for cnames in city_names:
    sd = emp_loc.loc[(emp_loc['city'] == cnames)] #getting values containing variable cnames
    salaries = sd['salary'] #taking only salary colum

    #creating empty numpy arrays for filling values
    lowSalary = np.array([])
    midSalary = np.array([])
    highSalary = np.array([])
    skySalary = np.array([])

    #creating variables for keep current value
    lowMean = 0
    midMean = 0
    highMean = 0
    skyMean = 0

    ## values are adding to arrays by conditions
    for x in salaries:
        if(0 <= x <= 5000):
           lowSalary = np.append(lowSalary, x)
        elif(5000 < x <= 10000):
            midSalary = np.append(midSalary, x)
        elif(10000< x <= 15000):
            highSalary = np.append(highSalary, x)
        elif(15000 < x <= 20500):
            skySalary = np.append(skySalary, x)

    ## calculating mean values array by array
    if not lowSalary.size == 0:
        lowMean = lowSalary.mean()
    if not midSalary.size == 0:
        midMean = midSalary.mean()
    if not highSalary.size == 0:
        highMean = highSalary.mean()
    if not skySalary.size == 0:
        skyMean = skySalary.mean()

    #taking country ids
    country = emp_loc.loc[(emp_loc['city'] == cnames)]
    country = country['country_id'].unique()

    #creating dict with values
    data = {'country_id':[country[0]], 'city':[cnames],'0, 5000':[lowMean], '5000, 10000':[midMean], '10000, 15000':[highMean], '15000, 25000':[skyMean]}
    
    #dict convert to dataframe
    temp = pd.DataFrame(data)

    #result dataframe is concatenating with temp dataframe
    result = pd.concat([result, temp])

#result sorting by country id
result = result.sort_values(by=['country_id'])

print("Q3 h-)\n")
print(result.to_string(index=False))
print()


#### Q4 ####
#fetching csv files from urls
covid_data= pd.read_csv('https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_daily_reports/05-10-2022.csv')
covid_series= pd.read_csv('https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv')

#printing tables
#print(covid_data)
#print(covid_series)



#a
print("Q4 a-)\n")
print("First 5 rows in covid_data")
print(covid_data.head())

print("\nFirst 5 rows in covid_series")
print(covid_series.head())
print()


#b

values = {'Active': 0, 'Confirmed':0, 'Deaths':0, 'Recovered':0}
covid_data.fillna(value=values, inplace=True) #NaN values converting to 0
covid_data['Active'] = covid_data['Confirmed'] - covid_data['Deaths'] - covid_data['Recovered'] #calculating active column with confirmed, deaths and recovered

resultB = covid_data.groupby('Country_Region')['Confirmed', 'Deaths', 'Recovered', 'Active'].sum().reset_index() #grouping
resultB = resultB.sort_values(by='Active',ascending=False) #sorting

print("Q4 b-)\n")
print(resultB)
print()



#c
resultC = covid_data.groupby('Country_Region').max().sort_values(by='Confirmed', ascending=False) #grouping and sorting
pd.set_option('display.max_column', None)

## calculate death confirmed ratio
Death_Confirmed_Ratio = []
for index, row in resultC.iterrows():
    deaths = row['Deaths'] #taking deaths value in current row
    confirmed = row['Confirmed'] #taking confirmed value in current row
    if not confirmed == 0: #we check if confirmed equal to 0, if it is 0 it will undefined
        Death_Confirmed_Ratio.append(deaths/confirmed*100)
    else:
        Death_Confirmed_Ratio.append(0)


resultC['Death_Confirmed_Ratio'] = Death_Confirmed_Ratio #creating new column name as Death_Confirmed_Ratio and list which we calculate and fill adding to that column
resultC = resultC.loc[resultC['Confirmed'] > 1000].sort_values(by='Death_Confirmed_Ratio', ascending = False) #sorting by condition

resultC = resultC[['Last_Update', 'Confirmed', 'Deaths', 'Recovered', 'Active', 'Death_Confirmed_Ratio']] #We select the columns that the dataframe will contain

print("Q4 c-)\n")
print(resultC)
print()



#d
top10Countries = resultB.sort_values(by='Confirmed', ascending = False)[:10] #getting top 10 countries by confirmed column
top10CountriesNames = top10Countries['Country_Region'] #getting their names

top10CountryDatas = covid_series.loc[covid_series['Country/Region'].isin(top10CountriesNames)] #fetching rows by country name
top10CountryDatas = top10CountryDatas.groupby('Country/Region').sum() #grouping and summing
pd.set_option('display.max_rows', None)

index_no = top10CountryDatas.columns.get_loc("3/11/20") #we calculating index of given date (first covid seen in turkey)

top10CountryDatasFromDate = top10CountryDatas.iloc[:10, index_no:-1] #adding data from the given date to the new dataframe

print("Q4 d-)\n")
print(top10CountryDatasFromDate) #printing it

top10CountryDatasFromDate.T.plot(figsize=(15,15),lw=8) #plotting by dataframe
plt.show()
