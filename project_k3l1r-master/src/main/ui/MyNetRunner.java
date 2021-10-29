package ui;

import model.MyNet;
import model.Person;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class MyNetRunner {
    private static final String JSON_STORE = "./data/mynet.json";
    private Scanner in;
    private MyNet mn;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    //EFFECTS: Initialize database and run MyNet application
    public MyNetRunner() {
        mn = new MyNet();
        in = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

    }


    public void saveMyNet() throws FileNotFoundException {
        try {
            jsonWriter.open();
            jsonWriter.write(mn);
            jsonWriter.close();
            System.out.println("Saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Unable to write to file: " + JSON_STORE);


        }
    }

    public void loadMynNet() throws FileNotFoundException {
        try {
            mn = jsonReader.read();
            System.out.println("Loaded MyNet from " + JSON_STORE);
        } catch (IOException e) {
            throw new FileNotFoundException();
        }
    }
    //REQUIRES: Age is integer type
    //          Age >= 0;
    //          Each strings has a non-zero length
    //MODIFIES: this
    //EFFECTS:  initialize person

    public void addPerson(String firstName, String secondName, String relationship, String occupation, int age) {

        Person ps = new Person(firstName, secondName, relationship, occupation, age, creatUniqueId());
        mn.addPerson(ps);

    }

    public int creatUniqueId() {
        int id = 0;
        boolean isUnique = false;
        do {
            id++;
            isUnique = true;
            for (Person ps : mn.getPeople()) {
                if (ps.getId() == id) {
                    isUnique = false;
                }
            }
        } while (!isUnique);
        return id;
    }


    //REQUIRES: select is positive integer type
    public Person findPerson(int id) {

        boolean found = false;

        try {
            Person ps = mn.showBySomething(0, id).get(0);
            return ps;
        } catch (Exception e) {
            //System.out.println("Not found... ");
            return null;
        }
    }


    //REQUIRES: occupation has a non-zero length
    //EFFECT: return all the person corresponding to the occupation
    public List<Person> viewByOccupation(String occupation) {

        List<Person> myPeople = mn.showBySomething(1, occupation);
        if (myPeople.isEmpty()) {
            System.out.println("Occupation: " + occupation + " not found");
        } else {
            return myPeople;
        }
        return null;
    }

    //REQUIRES: relationship has a non-zero length
    //EFFECT: return all the person corresponding to the relationship
    public List<Person> viewByRelationship(String relationship) {

        List<Person> myPeople = mn.showBySomething(2, relationship);
        if (myPeople.isEmpty()) {
            System.out.println("Relationship: " + relationship + " not found");

        } else {
            return myPeople;
        }
        return null;
    }

    //REQUIRES: age is integer type;
    //          age>=0
    //EFFECT: return all the person corresponding to the age
    public List<Person> viewByAge(int age) {

        List<Person> myPeople = mn.showBySomething(3, age);
        if (myPeople.isEmpty()) {
            System.out.println("Age: " + age + " not found");

        } else {
            return myPeople;
        }
        return null;
    }


    //REQUIRES: id is integer type;choice is
    //          either "y" or "n"
    //MODIFIES: this
    //EFFECTS: remove the corresponding person;otherwise
    //         print a message
    public void removePerson(int id) {
        List<Person> person = mn.showBySomething(0, id);
        if (!person.isEmpty()) {
            try {
                mn.remove(id);
            } catch (Exception e) {
                System.out.println("Failed to remove the person");
            }
            System.out.println("Successfully removed !");
        } else {
            System.out.println("Not found...");
        }


    }


    //EFFECTS: displays all the person user added
    public List<Person> showPeople() {
        List<Person> people = new ArrayList<>();
        if (mn.size() != 0) {
            for (Person ps : mn.getPeople()) {
                people.add(ps);
            }
        }
        return people;
    }

    //EFFECT: Returns all the relations
    public Set<String> getRelations() {
        return mn.getRelations();
    }

    //EFFECT: Returns all the age
    public Set<Integer> getAges() {
        return mn.getAges();
    }

    //EFFECT: Returns all the occupation
    public Set<String> getOccupations() {
        return mn.getOccupations();
    }

}
