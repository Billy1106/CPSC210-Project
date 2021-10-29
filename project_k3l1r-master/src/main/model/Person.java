package model;

import org.json.JSONObject;

//Represents a person having id, name, occupation, relationship and age
public class Person {

    private static int nextId = 1; // tracks id of next Person added
    private int id; //Added person ID
    private String firstName; // First name of the person
    private String secondName; // Second name of the person
    private String relationship;// Relationship with the person
    private String occupation;// Occupation of the person
    private int age; // Age of the person

    //REQUIRE: age is non-negative integer;
    //         firstName,secondName,relationship
    //         and occupation have a non-zero length
    //MODIFIES: this
    //EFFECT: Set the fields to the corresponding parameters
    public Person(String firstName, String secondName, String relationship,String occupation,int age) {
        id = nextId++;
        this.firstName = firstName;
        this.secondName = secondName;
        this.relationship = relationship;
        this.occupation = occupation;
        this.age = age;
    }

    //REQUIRE: age and id are non-negative integer;
    //         firstName,secondName,relationship
    //         and occupation have a non-zero length
    //MODIFIES: this
    //EFFECT: Set the fields to the corresponding parameters when loaded
    public Person(String firstName, String secondName, String relationship,String occupation,int age,int id) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.relationship = relationship;
        this.occupation = occupation;
        this.age = age;
    }

    //getters
    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getRelation() {
        return relationship;
    }

    public String getOccupation() {
        return occupation;
    }

    public int getAge() {
        return age;
    }


    //setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("ID",getId());
        json.put("FirstName",getFirstName());
        json.put("SecondName",getSecondName());
        json.put("Age",getAge());
        json.put("Occupation",getOccupation());
        json.put("Relationship",getRelation());
        return json;
    }

    public String toString() {
        return ""
                + relationship + "----------\n"
                + String.format("%-12s", "ID:") + id + "\n"
                + String.format("%-12s", "Name:") + firstName + " " + secondName + "\n"
                + String.format("%-12s", "Age:") + age + "\n"
                + String.format("%-12s", "Occupation:") + occupation + "\n";
    }
}
