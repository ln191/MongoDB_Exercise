# MongoDB_Exercise
Working with mongo database

How to import the csv file data into mongo database.

ssh into your vagrant machine with the mongo database installed

First download your csv files

    wget http://cs.stanford.edu/people/alecmgo/trainingandtestdata.zip
    
Install unzip

    sudo apt-get install unzip
    
Unzip the zip file

    unzip trainingandtestdata.zip

Add a headline in the csv, this will be the colum names 

    sed -i '1s;^;polarity,id,date,query,user,text\n;' training.1600000.processed.noemoticon.csv

You need to convert this csv file if mongo gives you a error on you csv unicode format
this csv has a ISO-8859-1 format but data have symbols that need UTF-8 unicode format so we will convert

    iconv -f ISO-8859-1 -t utf-8 training.1600000.processed.noemoticon.csv > social_net_utf8.csv
    
Now import you new csv file with the utf-8 format into mongo

    mongoimport --drop --db social_net --collection tweets --type csv --headerline --file social_net_utf8.csv

This will create a database named social_net with a collection called tweets and import the data form the given csv file, 
with the headerline we add earlier.

Now you should have all the data from csv in your mongo database.
you can check by write

    mongo

Now you are in the mongodb console and can now write mongo queries

write 

    use social_net
    
to switch to that database, then write

    db.tweets.findOne()
  
and it would print out the first entry in tweets collection.


  
