

//orgimport org.codehaus.jettison.json.JSONArray;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseToJson {
    public static ResultSet RetrieveData() throws Exception {
        //Registering the Driver
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        //Getting the connection
        String mysqlUrl = "jdbc:mysql://localhost/concretepage";
        Connection con = DriverManager.getConnection(mysqlUrl, "root", "");
        System.out.println("Connection established......");
        //Creating the Statement
        Statement stmt = con.createStatement();
        //Retrieving the records
        ResultSet rs = stmt.executeQuery("Select * from articles");
        return rs;
    }
    public static void main(String args[]) throws Exception {
        //Creating a JSONObject object
        JSONObject jsonObject = new JSONObject();
        //Creating a json array
        JSONArray array = new JSONArray();
        ResultSet rs = RetrieveData();
        //Inserting ResutlSet data into the json object
        while(rs.next()) {
            JSONObject record = new JSONObject();
            //Inserting key-value pairs into the json object
            record.put("ID", rs.getInt("article_id"));
            record.put("Title", rs.getString("title"));
            record.put("Category", rs.getString("category"));

            array.add(record);
        }
        jsonObject.put("artciles", array);
        try {
//            FileWriter file = new FileWriter("/home/mursalghori/Desktop/file.json");
            FileWriter file1 = new FileWriter("/Users/mursalghori/Desktop/file1.json");
            file1.write(jsonObject.toJSONString());
            file1.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("JSON file created......");
    }
}
