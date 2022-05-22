import pandas as pd
import numpy as np


#### Q1 ####
#a
s = pd.Series([7,11,13,17])
print(s)


#b
s1 = pd.Series(100.0,index=[0,1,2,3,4]) #creating 5 size array and filling it 100's
print(s1)

#c
s2 = pd.Series(np.random.randint(0, 100, size = 20)) #creating randomly array
print(s2)
s2.describe()

#d
temperatures = pd.Series([98.6,98.9,100.2,97.9],index=['Julie','Charlie','Sam','Andrea']) #creating a serie and filling floats with keys
print(temperatures)

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

print("a):")
print(temparatures)
print()

#b)
temparatures=pd.DataFrame(D,index=["Morning","Afternoon","Evening"])
print("b):")

print(temparatures)
print()

#c)
print("c):")
c=temparatures[['Maxine']] #selecting the maxine column
print(c)
print()

#d)

print("d):")
d=temparatures.loc[["Morning"],:] #selecting the row Morning using the loc[] method
print(d)
print()

#e)
print("e):")
e=temparatures.loc[["Morning","Evening"],:] #selecting the rows for Morning and Evening using the loc[] method
print(e)
print()

#f)

print("f):")
f=temparatures[["Maxine"]].loc[["Amanda"],:] #selecting column Amanda and row Maxine
print(f)
print()

#g)
#selecting the data under columns Amanda,Maxine and rows Morning,Afternoon
print("g):")
g=temparatures[["Amanda","Maxine"]].loc[["Morning","Afternoon"]]
print(g)
print()

#h)
#Demonstrating the describe() method
print("h):")
h=temparatures.describe()
print(h)
print()

#i)
#Creating the transpose of the dataframe
print("i):")
i=temparatures.transpose()
print(i)
print()

#j)
#sorting the columns is nothing but reordering the columns we can use reorder() method and
#sorted() method top sort and reorder the columns
print("j):")
j=temparatures.reindex(sorted(temparatures.columns),axis=1)
print(j)
print()


#### Q3 ####
pd.set_option('display.max_rows', 500)
pd.set_option('display.max_columns', 500)


employees = pd.read_csv(r"EMPLOYEES.csv")

departments = pd.read_csv(r"DEPARTMENTS.csv")

job_history = pd.read_csv(r"JOB_HISTORY.csv")

jobs = pd.read_csv(r"JOBS.csv")

countries = pd.read_csv(r"COUNTRIES.csv")

regions = pd.read_csv(r"REGIONS.csv")

locations = pd.read_csv(r"LOCATIONS.csv")

#a
#print(departments)

#b
allRecordsCount = len(employees.index) + len(departments.index) + len(job_history.index) + len(jobs.index) + len(countries.index) + len(regions.index) + len(locations.index)
#print(allRecordsCount)

#c
result = employees.sort_values('first_name', ascending=False)

print("First Name      Last Name       Salary    Department Id:")
for index, row in result.iterrows():
    if(int(str(row['salary']).ljust(9)) > 10000):
        print(row['first_name'].ljust(15),row['last_name'].ljust(15),str(row['salary']).ljust(9),row['department_id'])


#d
values = {'commission_pct': 0}
result.fillna(value=values, inplace=True)
#print(result)

#e
print("First Name      Last Name       Salary    Department Id:")
for index, row in result.iterrows():
    if(row['department_id'] in [30,50,80]):
        print(row['first_name'].ljust(15),row['last_name'].ljust(15),str(row['salary']).ljust(9),row['department_id'])

#f
empt_dept = pd.merge(employees, departments, on=["department_id"])
#print(empt_dept)


#g
#print(empt_dept.agg({'salary': ['min', 'max', 'mean', 'median']}))
department_names = empt_dept['department_name'].unique()
df = pd.DataFrame()
department_names = np.sort(department_names)
print("Salaries")
print("Department Name      min     max     mean")
for names in department_names:
    df = empt_dept.loc[(empt_dept['department_name'] == names)]
    #print(df['salary'])
    al = df['salary']
    print(names.ljust(20) ,str(np.min(al)).ljust(7), str(np.max(al)).ljust(7), str(np.mean(al)).ljust(7))
    space = "           "
print(employees)
#print(empt_dept.groupby('country_id'))

#h
emp_loc = pd.merge(locations, empt_dept, on=["location_id"])
country_names = emp_loc['country_id'].unique()
country_names = np.sort(country_names)

city_names = emp_loc['city'].unique()
city_names = np.sort(city_names)

data2 = {'country_id':[], 'city':[],'0, 5000':[], '5000, 10000':[], '10000, 15000':[], '15000, 25000':[]}
result = pd.DataFrame(data2)
temp = pd.DataFrame(data2)

for cnames in city_names:
    sd = emp_loc.loc[(emp_loc['city'] == cnames)]
    salaries = sd['salary']

    lowSalary = np.array([])
    midSalary = np.array([])
    highSalary = np.array([])
    skySalary = np.array([])

    lowMean = 0
    midMean = 0
    highMean = 0
    skyMean = 0

    for x in salaries:
        if(0 <= x <= 5000):
           lowSalary = np.append(lowSalary, x)
        elif(5000 < x <= 10000):
            midSalary = np.append(midSalary, x)
        elif(10000< x <= 15000):
            highSalary = np.append(highSalary, x)
        elif(15000 < x <= 20500):
            skySalary = np.append(skySalary, x)

    if not lowSalary.size == 0:
        lowMean = lowSalary.mean()
    if not midSalary.size == 0:
        midMean = midSalary.mean()
    if not highSalary.size == 0:
        highMean = highSalary.mean()
    if not skySalary.size == 0:
        skyMean = skySalary.mean()

    country = emp_loc.loc[(emp_loc['city'] == cnames)]
    country = country['country_id'].unique()

    data = {'country_id':[country[0]], 'city':[cnames],'0, 5000':[lowMean], '5000, 10000':[midMean], '10000, 15000':[highMean], '15000, 25000':[skyMean]}
    temp = pd.DataFrame(data)

    result = pd.concat([result, temp])

result = result.sort_values(by=['country_id'])

print(result.to_string(index=False))

#### Q4 ####
covid_data= pd.read_csv('https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_daily_reports/05-10-2022.csv')
covid_series= pd.read_csv('https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv')

print(covid_data)
print(covid_series)



#a
print("First 5 rows in covid_data")
print(covid_data.head())

print("\nFirst 5 rows in covid_series")
print(covid_series.head())

#b
covid_data['Active'] = covid_data['Confirmed'] - covid_data['Deaths'] - covid_data['Recovered']
resultB = covid_data.groupby('Country_Region')['Confirmed', 'Deaths', 'Recovered', 'Active'].sum().reset_index()
print(resultB)

#c
covid_data = pd.read_csv('https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_daily_reports/05-10-2022.csv', usecols = ['Last_Update', 'Country_Region', 'Confirmed', 'Deaths', 'Recovered', 'Active'])
resultC = covid_data.groupby('Country_Region').max().sort_values(by='Confirmed', ascending=False)
pd.set_option('display.max_column', None)

Death_Confirmed_Ratio = []
for index, row in resultC.iterrows():
    deaths = row['Deaths']
    confirmed = row['Confirmed']
    if not confirmed == 0:
        Death_Confirmed_Ratio.append(deaths/confirmed*100)
    else:
        Death_Confirmed_Ratio.append(0)


resultC['Death_Confirmed_Ratio'] = Death_Confirmed_Ratio
resultC = resultC.loc[resultC['Confirmed'] > 1000].sort_values(by='Death_Confirmed_Ratio', ascending = False)
print(resultC)


#d
resultB = resultB.sort_values(by='Confirmed', ascending = False)[:10]
print(resultB)

#resultD = covid_series.sort_values(by = 'Confirmed', ascending = False)
top10Countries = resultB['Country_Region']
resultB = resultB.sort_values(by='Confirmed', ascending = False)[:10]
print(resultB)

before = covid_series.loc[covid_series['Country/Region'].isin(top10Countries)]
before = before.groupby('Country/Region').sum()
pd.set_option('display.max_rows', None)

index_no = before.columns.get_loc("3/11/20")

after = before.iloc[:10, index_no:-1]
print(after)

import matplotlib.pyplot as plt

after.T.plot(figsize=(15,15),lw=8)
plt.show()
