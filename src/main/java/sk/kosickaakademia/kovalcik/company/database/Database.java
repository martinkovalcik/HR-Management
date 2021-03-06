package sk.kosickaakademia.kovalcik.company.database;

import sk.kosickaakademia.kovalcik.company.entity.User;
import sk.kosickaakademia.kovalcik.company.log.Log;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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

    public List<User> getMales(){
        String sql = "SELECT * FROM user WHERE gender=0";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            return executeSelect(ps);
        }catch (Exception ex){
            log.error(ex.toString());
        }
        return null;
    }

    public List<User> getFemales(){
        String sql = "SELECT * FROM user WHERE gender=1";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            return executeSelect(ps);
        }catch (Exception ex){
            log.error(ex.toString());
        }
        return null;
    }

    public List<User> getUsersByAge(int from,int to){
        if (to<from){
            return null;
        }
        try {
            String sql = "SELECT * FROM user WHERE age >= ? AND age <= ?";
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setInt(1,from);
            ps.setInt(2, to);
            return executeSelect(ps);
        }
        catch(Exception ex){
            log.error(ex.toString());
        }
        return null;
    }

    private List<User> executeSelect(PreparedStatement ps) throws SQLException {
        ResultSet rs =  ps.executeQuery();
        List<User> list = new ArrayList<>();
        int count = 0;
        while(rs.next()){
            count ++;
            String fname = rs.getString("fname");
            String lname = rs.getString("lname");
            int age = rs.getInt("age");
            int id = rs.getInt("id");
            int gender = rs.getInt("gender");
            User u=new User(id,fname,lname,age,gender);
            list.add(u);
        }
        System.out.println("Number of records: "+ count);
        return list;
    }

    public List<User> getAllUsers(){

        String sql = "SELECT * FROM user";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            return executeSelect(ps);
        }catch(Exception ex){
            log.error(ex.toString());
        }
        return null;

    }

    public User getUserById(int id){

        String sql = "SELECT * FROM user WHERE id = ?";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setInt(1,id);
            List<User> list = executeSelect(ps);
            if(list.isEmpty())
                return null;
            else
                list.get(0);
        }catch(Exception ex){
            log.error(ex.toString());
        }
        return null;

    }
}
