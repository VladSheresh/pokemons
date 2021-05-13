package by.grsu.edu.data;

import by.grsu.edu.model.Pokemon;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

public class JsonDataImporter {

    public static List<Pokemon> convertJsonToPokenons(){
        try {
            JSONParser parser = new JSONParser();
            JSONArray a = (JSONArray) parser.parse(new FileReader("Pokemons.json"));
            Gson gson = new Gson();
            String pokemonsJson = String.valueOf(a);
            Type type = new TypeToken<List<Pokemon>>(){}.getType();
            return gson.fromJson(pokemonsJson, type);
        } catch (Exception e) {
          return null;
        }
    }

}
