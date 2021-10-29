package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//Unit tests for Person class
public class PersonTest {
    private Person ps;

    @BeforeEach
    public void setup(){

        ps = new Person("FName1", "Sname1", "Friend"
                , "Student", 20);
    }

    @Test
    public void testConstructor(){
        assertEquals("FName1",ps.getFirstName());
        assertEquals("Sname1",ps.getSecondName());
        assertEquals("Friend",ps.getRelation());
        assertEquals("Student",ps.getOccupation());
        assertEquals(20,ps.getAge());

    }

    @Test
    public void testSetFirstName(){
        ps.setFirstName("FirstName");
        assertEquals("FirstName",ps.getFirstName());
    }

    @Test
    public void testSetSecondName(){
        ps.setSecondName("SecondName");
        assertEquals("SecondName",ps.getSecondName());
    }

    @Test
    public void testSetRelationship(){
        ps.setRelationship("Boss");
        assertEquals("Boss",ps.getRelation());
    }

    @Test
    public void testSetOccupation(){
        ps.setOccupation("Professor");
        assertEquals("Professor",ps.getOccupation());
    }

    @Test
    public void testSetAge(){
        ps.setAge(21);
        assertEquals(21,ps.getAge());
    }

    @Test
    public void testToString(){
        assertEquals(""
                + ps.getRelation() + "----------\n"
                + String.format("%-12s", "ID:") + ps.getId() + "\n"
                + String.format("%-12s", "Name:") + ps.getFirstName() + " " + ps.getSecondName() + "\n"
                + String.format("%-12s", "Age:") + ps.getAge() + "\n"
                + String.format("%-12s", "Occupation:") + ps.getOccupation() + "\n", ps.toString());
    }


}
