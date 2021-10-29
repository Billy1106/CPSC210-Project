package persistence;


import model.MyNet;
import model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonReaderTest extends JsonTest {
    private Person ps1, ps2;
    private MyNet mn;

    @BeforeEach
    public void setup() {
        mn = new MyNet();
        ps1 = new Person("FName1", "Sname1", "Friend"
                , "Student", 20);
        ps2 = new Person("FName2", "Sname2", "Friend"
                , "Student", 25);

    }

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            MyNet mn = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyMyNet() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyMyNet.json");
        try {
            mn = reader.read();
            assertEquals(0, mn.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralMyNet() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralMyNet.json");
        try {
            mn = reader.read();

            assertEquals(2, mn.size());
            checkPerson("FName1", "Sname1", "Friend"
                    , "Student", 20,mn.getPeople().get(0));
            checkPerson("FName2", "Sname2", "Friend"
                    , "Student", 25,mn.getPeople().get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}