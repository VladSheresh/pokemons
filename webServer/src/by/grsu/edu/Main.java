package by.grsu.edu;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class Main {

    public static void main(String[] args) {
	// write your code here
        try {
            JSONParser parser = new JSONParser();;
            JSONArray a = (JSONArray) parser.parse(new FileReader("Pokemons.json"));

            for (Object o : a)
            {
                JSONObject person = (JSONObject) o;

                String name = (String) person.get("name");
                System.out.println(name);

                String city = (String) person.get("city");
                System.out.println(city);

                String job = (String) person.get("job");
                System.out.println(job);

                JSONArray cars = (JSONArray) person.get("cars");

                for (Object c : cars)
                {
                    System.out.println(c+"");
                }
            }
        } catch (Exception e) {

        }
    }
}
