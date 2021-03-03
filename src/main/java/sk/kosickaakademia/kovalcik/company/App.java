package sk.kosickaakademia.kovalcik.company;

import sk.kosickaakademia.kovalcik.company.database.Database;

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
        db.insertNewUser(new User("Peter,Bondra,39,Gender.MALE"))
    }
}
