package by.grsu.edu.service;

import by.grsu.edu.importer.Importer;
import by.grsu.edu.importer.dto.PokemonDTO;
import by.grsu.edu.model.Pagination;
import by.grsu.edu.model.Pokemon;
import by.grsu.edu.model.PokemonType;
import by.grsu.edu.repository.PokemonTypeRepository;
import by.grsu.edu.repository.PokemonsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class PokemonsService {

    @Autowired
    private PokemonsRepository pokemonsRepository;

    @Autowired
    private PokemonTypeRepository pokemonTypeRepository;

    @Autowired
    private Importer importer;

    public void loadPokemons() throws IOException {
        List<PokemonDTO> listPokemonDTO = importer.getListPokemonDTO();
        Map<String, PokemonType> collect = listPokemonDTO.stream().flatMap(pokemonDTO -> pokemonDTO.getTypes().stream())
                .distinct().map(type -> {
                    PokemonType pokemonType = new PokemonType();
                    pokemonType.setName(type);
                    return pokemonType;
                }).peek(pokemonTypeRepository::create)
                .collect(Collectors.toMap(PokemonType::getName, pokemonType -> pokemonType));

        listPokemonDTO.forEach(pokemonDTO -> {
                    Pokemon pokemon = new Pokemon();
                    pokemon.setName(pokemonDTO.getName());
                    pokemon.setPngImageUrl(pokemonDTO.getPngImageUrl());
                    pokemon.setSvgImageUrl(pokemonDTO.getSvgImageUrl());
                    pokemon.setEvolution(pokemonDTO.getEvolution());

                    pokemon.setHp(pokemonDTO.getStats().get("hp"));
                    pokemon.setAttack((pokemonDTO.getStats().get("attack")));
                    pokemon.setDefense(pokemonDTO.getStats().get("defense"));
                    pokemon.setSpeed(pokemonDTO.getStats().get("speed"));
                    pokemon.setSpecialAttack(pokemonDTO.getStats().get("special-attack"));
                    pokemon.setSpecialDefense(pokemonDTO.getStats().get("special-defense"));

                    pokemon.setTypes(pokemonDTO.getTypes().stream().map(collect::get).collect(Collectors.toList()));

                    pokemonsRepository.create(pokemon);
                }

                );
    }
@Transactional(propagation = Propagation.REQUIRES_NEW)
    public void clearData(){
        pokemonsRepository.clearData();
    }

    public List<Pokemon> getPokemons(Pagination pagination) {
        return pokemonsRepository.getPokemons(pagination);
    }

    public Pokemon getPokemon(String pokemonId) {
        return pokemonsRepository.getPokemon(pokemonId);
    }

    public List<String> getPokemonNames() {
        return pokemonsRepository.getPokemonNames();
    }

    public List<Pokemon> contextSearchPokemons(String pokemonId){
        return pokemonsRepository.contextSearchPokemons(pokemonId);
    }

    public Map<String, Integer> getPokemonStats(String pokemonId){
        return pokemonsRepository.getPokemonStats(pokemonId);
    }
}
