/*
@author Hannan Saleem
The purpose of this program is to connect to a database and randomly generate
2000 records of data. Necessary dependencies are included in the /lib folder.
*/

import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.lang.Math;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;




public class App {

    /*Read database credentials from external file*/
    public List<String> configReader(){
        List<String> credentials = new ArrayList<String>();
        try{
            JSONParser parser = new JSONParser();
        
            Object obj = parser.parse(new FileReader("..\\config.json")); 
       
            JSONObject jsonObject = (JSONObject) obj;
        
            String user = jsonObject.get("user").toString();        
            String passw = jsonObject.get("passw").toString();
            
            credentials.add(user);
            credentials.add(passw);


        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        } catch (IOException e){
            System.out.println(e.getMessage());
        } catch (ParseException e){
            System.out.println(e.getMessage());
        }

        return(credentials);
    }


    /*Connect to the postgres db*/
    public Connection connect(List<String> dbcred) {
        /*database credentials, along with jdbc string*/
        String url = "jdbc:postgresql://localhost/titanic";
        String user = dbcred.get(0);
        String password = dbcred.get(1);
        
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
 
        return conn;
    }
 

    /*Insert rows into passengers table*/
    public void insertRecord(Connection conn){
        String query = "INSERT INTO passengers " 
                + "(survived, pclass, name, sex, age, siblings_spouses, parents_children, fare)"
                + " VALUES(?,?,?,?,?,?,?,?)";

        try {
            /*create prepared statement*/
            PreparedStatement stmt = conn.prepareStatement(query);
            
            /*create random object*/
            Random ran = new Random();
            String[] sexes = {"male", "female"};

            /*inserting 2000 rows into database*/
            int row = 0;
            while (row < 2000){
                //id field auto-increments, no need to include it
                stmt.setInt(1, randomRecordsInt(ran,0,1)); //survived
                stmt.setInt(2, randomRecordsInt(ran,1,3)); //pclass
                stmt.setString(3, randomRecordsString());  //name
                stmt.setString(4, randomRecordsSexes(ran, sexes)); //sex
                stmt.setInt(5, randomRecordsInt(ran,1,90)); //age
                stmt.setInt(6, randomRecordsInt(ran,0,10)); //siblings_spouses
                stmt.setInt(7, randomRecordsInt(ran,0,10)); //parents_children
                stmt.setDouble(8, randomRecordsDouble(ran,1,1000)); //fare

                stmt.addBatch();
                row++;
            }
            
            stmt.executeBatch();
            System.out.println(row + " record(s) inserted successfully");

            conn.close();
            System.out.println("Connection closed");

        } catch (SQLException e){
            System.out.println(e.getMessage());

        } 

    }


    /*Generate random int in specified range*/
    public int randomRecordsInt(Random ran, int minVal, int maxVal){
        int num = minVal + ran.nextInt((maxVal-minVal) + 1);
        return(num);
    }

    /*Generate random double in specified range*/
    public double randomRecordsDouble(Random ran, double minVal, double maxVal){
        double dub = minVal + ran.nextDouble()*(maxVal - minVal);
        return(dub);
    }


    /*Randomly choose between male or female*/
    public String randomRecordsSexes(Random ran, String[] sexes){
        int i = ran.nextInt(sexes.length);
        String ranString = (sexes[i]);
        return(ranString);
    }

    /*Generate random alphanumeric string*/
    public String randomRecordsString(){
        String st = UUID.randomUUID().toString().replaceAll("-", ""); 
        return(st);
    }
   

    /*Entry point of application*/
    public static void main(String[] args) {
        App app = new App();
        
        List<String> dbcred = app.configReader();
        Connection conn = app.connect(dbcred);

        app.insertRecord(conn);
    }
}