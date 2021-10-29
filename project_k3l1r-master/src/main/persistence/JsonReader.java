package persistence;

import model.MyNet;
import model.Person;
import org.json.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads MyNet from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads MyNet from file and returns it;
    // throws IOException if an error occurs reading data from file
    public MyNet read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMyNet(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses MyNet from JSON object and returns it
    private MyNet parseMyNet(JSONObject jsonObject) {
        MyNet mn = new MyNet();
        addPeople(mn, jsonObject);
        return mn;
    }

    // MODIFIES: mn
    // EFFECTS: parses people from JSON object and adds them to MyNet
    private void addPeople(MyNet mn, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("People");
        for (Object json : jsonArray) {
            JSONObject nextPerson = (JSONObject) json;
            addPerson(mn, nextPerson);
        }
    }

    // MODIFIES: mn
    // EFFECTS: parses person from JSON object and adds it to MyNet
    private void addPerson(MyNet mn, JSONObject jsonObject) {
        int id = jsonObject.getInt("ID");
        String firstName = jsonObject.getString("FirstName");
        String secondName = jsonObject.getString("SecondName");
        String occupation = jsonObject.getString("Occupation");
        int age = jsonObject.getInt("Age");
        String relationship = jsonObject.getString("Relationship");
        Person ps = new Person(firstName,secondName,relationship,occupation,age,id);
        mn.addPerson(ps);
    }
}
