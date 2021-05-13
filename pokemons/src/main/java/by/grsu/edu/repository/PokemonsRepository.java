package by.grsu.edu.repository;

import by.grsu.edu.data.JsonDataImporter;
import by.grsu.edu.model.Pagination;
import by.grsu.edu.model.Pokemon;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PokemonsRepository {
    private List<Pokemon> pokemons;

    public PokemonsRepository(){
        pokemons = JsonDataImporter.convertJsonToPokenons();
    }

    public List<Pokemon> getPokemons(Pagination pagination) {
        int skip = (pagination.getPage() - 1) * pagination.getPageSize();
        if(skip >= pokemons.size()){
            return new ArrayList<>();
        }
        return pokemons.stream().skip(skip).limit(pagination.getPageSize()).collect(Collectors.toList());
    }

    public void setPokemons(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }


    public Pokemon getPokemon(String pokemonId) {
      return pokemons.stream().filter(pokemon -> pokemon.getName().equals(pokemonId)).findFirst().orElse(null);
    }
}
