package sk.kosickaakademia.kovalcik.company.entity;


import sk.kosickaakademia.kovalcik.company.enumerator.Gender;

public class User {
    private int id;
    private String fname;
    private String lname;
    private int age;
    private Gender gender;

    public User(int id, String fname, String lname, int age, int gender) {
        this(fname,lname,age,gender); //volanie konstruktora so 4 parametrami (funguje to ako funkcia)
        this.id = id;
    }

    public User(String fname, String lname, int age, int gender) {
        this.fname = fname;
        this.lname = lname;
        this.age = age;
        this.gender = gender==0?Gender.MALE:gender==1?Gender.FEMALE:Gender.OTHER;
    }

    public int getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
}
