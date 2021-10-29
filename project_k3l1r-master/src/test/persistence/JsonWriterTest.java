package persistence;

import model.MyNet;
import model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.
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
    void testWriterInvalidFile() {
        try {

            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(mn);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            mn = reader.read();
            assertEquals(0, mn.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
    @Test
    void testWriterGeneralMyNet() {
        try {

            mn.addPerson(ps1);
            mn.addPerson(ps2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralMyNet.json");
            writer.open();
            writer.write(mn);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralMyNet.json");
            mn = reader.read();


            assertEquals(2, mn.size());
            checkPerson("FName1", "Sname1", "Friend"
                    , "Student", 20,mn.getPeople().get(0));
            checkPerson("FName2", "Sname2", "Friend"
                    , "Student", 25,mn.getPeople().get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}