package sk.kosickaakademia.kovalcik.company.database;

import sk.kosickaakademia.kovalcik.company.log.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    String url = "jdbc:mysql://itsovy.sk:3306/world_x";
    String username="mysqluser";
    String password="Kosice2021!";


    Log log = new Log();
    public Connection getConnection(){
        try {
            Connection con= DriverManager.getConnection(url,username,password);
            log.print("Connection successfull");
            return con;
        }catch (SQLException ex){
            log.error(ex.toString());
        }
        return null;
    }
    public void closeConnection(Connection con){
        if (con!=null){
            try {
                con.close();
                log.print("Connection closed");
            } catch (SQLException throwables) {
                log.error(throwables.toString());
            }
        }
    }
}
