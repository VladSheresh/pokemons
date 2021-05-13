package by.grsu.edu.data;

import by.grsu.edu.entity.PokemonDTO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class JsonDataImporter {
public List<PokemonDTO> convertJsonToPokenons() {
;
    /*try {
        FileReader reader = new FileReader("c:\\exer4-courses.json");

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

        // получение строки из объекта
        List<PokemonDTO> pokemonDTO = (PokemonDTO) jsonObject.get("firstname");
        System.out.println("The first name is: " + firstName);
    } catch (Exception e) {

    }*/

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

    return null;




}

}
