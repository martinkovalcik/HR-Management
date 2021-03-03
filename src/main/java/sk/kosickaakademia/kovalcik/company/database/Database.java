package sk.kosickaakademia.kovalcik.company.database;

import sk.kosickaakademia.kovalcik.company.entity.User;
import sk.kosickaakademia.kovalcik.company.log.Log;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class Database {
    Log log = new Log();
    private final String INSERTQUERY="INSERT INTO user (fname, lname, age, gender)" + " VALUES (?,?,?,?)";

    public Connection getConnection(){
        try {
            Properties props = new Properties();
            InputStream loader = getClass().getClassLoader().getResourceAsStream("db.properties");
            props.load(loader);
            String url = props.getProperty("url");
            String username=props.getProperty("username");
            String password= props.getProperty("password");
            log.print("Connection success!");

            Connection connection = DriverManager.getConnection(url,username,password);
        }catch (Exception e){
            e.printStackTrace();
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

    public boolean insertNewUser(User user){
        Connection con = getConnection();
        if (con!=null){
            try {
                PreparedStatement ps = con.prepareStatement(INSERTQUERY);
                ps.setString(1,user.getFname());
                ps.setString(1,user.getLname());
                ps.setInt(1,user.getAge());
                ps.setInt(1,user.getGender().getValue());
            }catch (SQLException exception){
                log.error(exception.toString());
            }
        }
        return false;

    }
}
