package sk.kosickaakademia.kovalcik.company.enumerator;

public enum Gender {
    MALE(0),FEMALE(1),OTHER(2);
    private int value;
    Gender(int value){
        this.value=value;
    }
    public int getValue(){
        return value;
    }
}
