Using jupyter to display data from a postgres database. 

Requirements for this assignment include:
- python 3 
- postgresql 
- jupyter 
- titanic.csv file (for importing data)

The purpose of this exercise is to store some data in a postgres database, connect to the database, read some data from one of the tables, and output the results. The dataset used is a "titanic.csv" file which was taken from Stanfords CS109 lecture on probability and statistics for computer science (http://web.stanford.edu/class/archive/cs/cs109/cs109.1166/problem12.html). It contains a list of some (not all) of the passengers on the ship and some relevant information such as age, gender, how much they paid for a ticket, etc. 

To generate the data in postgres, a "titanic" database was created and inside this database the "passengers" table was created. The columns in the "passengers" table are mapped to the columns in the "titanic.csv" file, so the column names have to be the same in both the .csv file and the table. This is a fast and easy way to insert a large amount of data into a table. The rest of the assignment is explained through the jupyter notebook included in this repository.