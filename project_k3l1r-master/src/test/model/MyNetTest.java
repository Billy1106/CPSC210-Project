package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//Unit tests for MyNet class
class MyNetTest {

    private Person ps1, ps2, ps3, ps4;
    private MyNet mn;

    @BeforeEach
    public void setup() {
        mn = new MyNet();
        ps1 = new Person("FName1", "Sname1", "Friend"
                , "Student", 20);
        ps2 = new Person("FName2", "Sname2", "Friend"
                , "Student", 25);
        ps3 = new Person("FName3", "Sname3", "Friend"
                , "Student Advisor", 25);
        ps4 = new Person("FName4", "Sname4", "Professor"
                , "Professor", 60);

    }


    @Test
    public void testAddOnePerson() {
        mn.addPerson(ps1);

        assertEquals(1, mn.size());
        assertEquals(ps1.getId(), mn.getPeople().get(0).getId());
        assertEquals(ps1.getFirstName(), mn.getPeople().get(0).getFirstName());
        assertEquals(ps1.getSecondName(), mn.getPeople().get(0).getSecondName());
        assertEquals(ps1.getRelation(), mn.getPeople().get(0).getRelation());
        assertEquals(ps1.getOccupation(), mn.getPeople().get(0).getOccupation());
        assertEquals(ps1.getAge(), mn.getPeople().get(0).getAge());

        assertEquals(1, mn.getRelations().size());
        assertEquals(1, mn.getAges().size());
        assertEquals(1, mn.getOccupations().size());

        Iterator<String> it = mn.getRelations().iterator();
        assertEquals(ps1.getRelation(), it.next());

        Iterator<Integer> it2 = mn.getAges().iterator();
        assertEquals(ps1.getAge(), it2.next());

        Iterator<String> it3 = mn.getOccupations().iterator();
        assertEquals(ps1.getOccupation(), it3.next());
    }

    @Test
    public void testAddMultiplePerson() {
        mn.addPerson(ps1);
        mn.addPerson(ps4);

        assertEquals(2, mn.size());
        assertEquals(ps1.getId(), mn.getPeople().get(0).getId());
        assertEquals(ps1.getFirstName(), mn.getPeople().get(0).getFirstName());
        assertEquals(ps1.getSecondName(), mn.getPeople().get(0).getSecondName());
        assertEquals(ps1.getRelation(), mn.getPeople().get(0).getRelation());
        assertEquals(ps1.getOccupation(), mn.getPeople().get(0).getOccupation());
        assertEquals(ps1.getAge(), mn.getPeople().get(0).getAge());

        assertEquals(ps4.getId(), mn.getPeople().get(1).getId());
        assertEquals(ps4.getFirstName(), mn.getPeople().get(1).getFirstName());
        assertEquals(ps4.getSecondName(), mn.getPeople().get(1).getSecondName());
        assertEquals(ps4.getRelation(), mn.getPeople().get(1).getRelation());
        assertEquals(ps4.getOccupation(), mn.getPeople().get(1).getOccupation());
        assertEquals(ps4.getAge(), mn.getPeople().get(1).getAge());

        assertEquals(2, mn.getRelations().size());

        Iterator<String> it = mn.getRelations().iterator();
        assertEquals(ps1.getRelation(), it.next());
        assertEquals(ps4.getRelation(), it.next());

    }

    @Test
    public void testFindById() {
        mn.addPerson(ps1);
        assertEquals(ps1, mn.showBySomething(0, ps1.getId()).get(0));
    }

    @Test
    public void testIdNotFound() {
        mn.addPerson(ps1);
        assertTrue(mn.showBySomething(0, ps1.getId() + 1).isEmpty());
    }

    @Test
    public void testShowByOccupation() {
        mn.addPerson(ps1);
        mn.addPerson(ps2);
        mn.addPerson(ps3);
        mn.addPerson(ps4);
        List<Person> ls = mn.showBySomething(1, "Student");
        for (Person p : ls) {
            assertEquals("Student", p.getOccupation());
        }
    }

    @Test
    public void testOccupationNotFound() {
        mn.addPerson(ps1);
        mn.addPerson(ps2);
        mn.addPerson(ps3);
        mn.addPerson(ps4);
        List<Person> ls = mn.showBySomething(1, "Teaching Assistant");
        assertTrue(ls.isEmpty());
    }

    @Test
    public void testShowByRelationship() {
        mn.addPerson(ps1);
        mn.addPerson(ps2);
        mn.addPerson(ps3);
        mn.addPerson(ps4);
        List<Person> ls = mn.showBySomething(2, "Friend");
        for (Person p : ls) {
            assertEquals("Friend", p.getRelation());
        }
    }

    @Test
    public void testRelationshipNotFound() {
        mn.addPerson(ps1);
        mn.addPerson(ps2);
        mn.addPerson(ps3);
        mn.addPerson(ps4);
        List<Person> ls = mn.showBySomething(2, "Father");
        assertTrue(ls.isEmpty());
    }

    @Test
    public void testShowByAge() {
        mn.addPerson(ps1);
        mn.addPerson(ps2);
        mn.addPerson(ps3);
        mn.addPerson(ps4);
        List<Person> ls = mn.showBySomething(3, 25);
        for (Person p : ls) {
            assertEquals(25, p.getAge());
        }
    }

    @Test
    public void testAgeNotFound() {
        mn.addPerson(ps1);
        mn.addPerson(ps2);
        mn.addPerson(ps3);
        mn.addPerson(ps4);
        List<Person> ls = mn.showBySomething(3, 31);
        assertTrue(ls.isEmpty());
    }

    @Test
    public void testRemoveById() {
        mn.addPerson(ps1);
        mn.addPerson(ps2);
        mn.addPerson(ps3);
        mn.addPerson(ps4);
        try {
            mn.remove(ps2.getId());
        } catch (Exception e) {
            fail();
        }
        List<Person> ls = mn.getPeople();
        assertEquals(3, ls.size());
        for (Person p : ls) {
            assertNotEquals(2, p.getId());
        }
    }

    @Test
    public void testUnableRemoveById() {
        mn.addPerson(ps1);
        mn.addPerson(ps2);
        mn.addPerson(ps3);
        mn.addPerson(ps4);
        try{
            mn.remove(5);
            fail();
        } catch (Exception e) {
            //pass
        }
        List<Person> ls = mn.getPeople();
        assertEquals(4, ls.size());
    }

    @Test
    public void checkCriterionNotFound(){
        assertFalse(mn.checkCriterion(5,3,ps1));
    }


}