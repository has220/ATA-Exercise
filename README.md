Using jupyter to display data from a postgres database. 

Requirements for this assignment include the latest versions of:
- python 3 
- postgresql 
- jupyter 
- titanic.csv file (for importing data)

The purpose of this exercise is to store data in a postgres database, connect to the database, read some data from one of the tables, and output the results. The dataset used is a "titanic.csv" file which was taken from Stanfords CS109 lecture on probability and statistics for computer science (http://web.stanford.edu/class/archive/cs/cs109/cs109.1166/problem12.html). It contains a list of some (not all) of the passengers on the ship and some relevant information such as age, gender, how much they paid for a ticket, etc. 

In postgres, a "titanic" database was created along with the "passengers" table. To generate the data in postgres, the "titanic.csv" file was imported into the "passengers" table. The columns in the "passengers" table are mapped to the columns in the "titanic.csv" file (the column names have to be the same in both the .csv file and the table). 

UPDATE:
Included program to randomly generate 2000 records of data into the database. The .java file is located in the /app folder and the necessary dependencies are located in /lib.