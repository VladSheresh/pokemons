package by.grsu.edu.service;

import by.grsu.edu.model.Pagination;
import by.grsu.edu.model.Pokemon;
import by.grsu.edu.repository.PokemonsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonsService {

    @Autowired
    private PokemonsRepository pokemonsRepository;

    public List<Pokemon> getPokemons(Pagination pagination) {
        return pokemonsRepository.getPokemons(pagination);
    }

    public Pokemon getPokemon(String pokemonId) {
        return pokemonsRepository.getPokemon(pokemonId);
    }
}
