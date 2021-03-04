package sk.kosickaakademia.kovalcik.company;

import sk.kosickaakademia.kovalcik.company.database.Database;
import sk.kosickaakademia.kovalcik.company.entity.User;
import sk.kosickaakademia.kovalcik.company.enumerator.Gender;

import java.util.List;

public class App 
{
    public static void main( String[] args )
    {
        Database db=new Database();
        List<User> list = db.getUsersByAge(10,35);
        for(User u:list)
            System.out.println(u.toString());
    }
}
