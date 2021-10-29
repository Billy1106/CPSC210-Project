package persistence;

import model.Person;
import model.MyNet;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkPerson(String firstName, String secondName, String relationship,String occupation,int age,Person p) {
        assertEquals(firstName,p.getFirstName());
        assertEquals(secondName,p.getSecondName());
        assertEquals(relationship,p.getRelation());
        assertEquals(occupation,p.getOccupation());
        assertEquals(age,p.getAge());

    }
}
