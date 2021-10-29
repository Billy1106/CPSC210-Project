package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

//Represents a list of people and a set of relations
public class MyNet {

    private List<Person> myPeople;//Declare List to store Person
    private Set<String> relations;//Declare Set to store different relations
    private Set<Integer> age;//Declare Set to store different age
    private Set<String> occupation;//Declare Set to store different occupation


    //EFFECT: create ArrayList to store
    //        person added; create LinkedHashSet
    //        to store relations,age,occupation

    public MyNet() {
        myPeople = new ArrayList();
        relations = new LinkedHashSet<>();
        age = new LinkedHashSet<>();
        occupation = new LinkedHashSet<>();
    }

    //EFFECT: Returns the size of the database
    public int size() {
        return myPeople.size();
    }

    //EFFECT: Returns all the person in the ArrayList
    public List<Person> getPeople() {
        return myPeople;
    }

    //EFFECT: Returns all the relations
    public Set<String> getRelations() {
        return relations;
    }

    //EFFECT: Returns all the age
    public Set<Integer> getAges() {
        return age;
    }

    //EFFECT: Returns all the occupation
    public Set<String> getOccupations() {
        return occupation;
    }

    //MODIFIES: this
    //EFFECT:  add person to the list;
    //         add relation to the set
    public void addPerson(Person person) {
        myPeople.add(person);
        relations.add(person.getRelation());
        occupation.add(person.getOccupation());
        age.add(person.getAge());

    }

    //EFFECT: if a person with corresponding ID found in the database,
    //        then remove the person and returns true;otherwise returns false
    public void remove(int id) throws Exception {
        try {
            Person person = showBySomething(0,id).get(0);
            myPeople.remove(person);
        } catch (Exception e) {
            throw new Exception();
        }
//        if (!showBySomething(0,id).isEmpty()) {
//            myPeople.remove(showBySomething(0,id).get(0));
//            return true;
//        } else {
//            return false;
//        }
    }

    //REQUIRES: 0<= select <= 3
    //EFFECT: returns people in the database who meet the selected criterion
    public List<Person> showBySomething(int select,Object criterion) {
        List<Person> people = new ArrayList<>();
        for (Person md: myPeople) {
            if (checkCriterion(select,criterion,md)) {
                people.add(md);
            }
        }
        return people;
    }

    //REQUIRES: 0<= select <= 3
    //EFFECT: returns True if person meets the selected criterion;
    //        otherwise, returns false
    public boolean checkCriterion(int select,Object criterion, Person person) {
        String something = criterion.toString();
        switch (select) {
            case 0:
                return person.getId() == Integer.parseInt(something);
            case 1:
                return person.getOccupation().equals(something);
            case 2:
                return person.getRelation().equals(something);
            case 3:
                return person.getAge() == Integer.parseInt(something);
        }
        return false;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("People", myPeopleToJson());
        return json;
    }

    private JSONArray myPeopleToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Person ps : getPeople()) {
            jsonArray.put(ps.toJson());
        }
        return jsonArray;
    }


}
