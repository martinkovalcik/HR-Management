package sk.kosickaakademia.kovalcik.company;

import sk.kosickaakademia.kovalcik.company.database.Database;
import sk.kosickaakademia.kovalcik.company.entity.User;
import sk.kosickaakademia.kovalcik.company.enumerator.Gender;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Database db=new Database();
        List<User> list = db.getUsersByAge(20,30);
        for(User u:list)
            System.out.println(u.toString());
    }
}
