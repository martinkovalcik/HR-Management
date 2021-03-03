package sk.kosickaakademia.kovalcik.company;

import sk.kosickaakademia.kovalcik.company.database.Database;
import sk.kosickaakademia.kovalcik.company.entity.User;
import sk.kosickaakademia.kovalcik.company.enumerator.Gender;

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
        db.getConnection();
        db.insertNewUser(new User("Peter","Bondra",30, Gender.MALE.getValue()));
    }
}
