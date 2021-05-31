package by.grsu.edu.importer;

import by.grsu.edu.importer.dto.EvolutionDTO;
import by.grsu.edu.importer.dto.PokemonDTO;
import com.google.gson.Gson;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Importet {
    private List<PokemonDTO> listPokemonDTO = new ArrayList<>();

    public List<PokemonDTO> getListPokemonDTO() throws IOException {
        String pokemonsAsString = get("https://pokeapi.co/api/v2/pokemon/");
        Gson gson = new Gson();
        Pokemons pokemons = gson.fromJson(pokemonsAsString, Pokemons.class);

        List<Pokemon> allPokemons = new ArrayList<>();



        for (int i = 0; i < pokemons.getPokemonList().size(); i++) {
            String s = get(pokemons.getPokemonList().get(i).getUrl());
            Pokemon pokemon = gson.fromJson(s, Pokemon.class);
            allPokemons.add(pokemon);
        }



        //String s = get(pokemons.getPokemonList().get(1).getUrl());
        //Pokemon pokemon = gson.fromJson(s, Pokemon.class);
        System.out.println(pokemons.getCount());
        while(pokemons.getNext() != null){

            pokemonsAsString = get(pokemons.getNext());
            pokemons = gson.fromJson(pokemonsAsString, Pokemons.class);

            for (int i = 0; i < pokemons.getPokemonList().size(); i++) {
                String s = get(pokemons.getPokemonList().get(i).getUrl());
                Pokemon pokemon = gson.fromJson(s, Pokemon.class);
                allPokemons.add(pokemon);
            }

        }

        List<Evolution> allEvolutions = new ArrayList<>();

        String evolutionAsString = get("https://pokeapi.co/api/v2/evolution-chain/");
        Evolutions evolutions = gson.fromJson(evolutionAsString, Evolutions.class);

        for (int i = 0; i < evolutions.getEvolutionList().size(); i++) {
            String s = get(evolutions.getEvolutionList().get(i).getUrl());
            Evolution evolution = gson.fromJson(s, Evolution.class);
            allEvolutions.add(evolution);
        }

        while(evolutions.getNext() != null){

            evolutionAsString = get(evolutions.getNext());
            evolutions = gson.fromJson(evolutionAsString, Evolutions.class);

            for (int i = 0; i < evolutions.getEvolutionList().size(); i++) {
                String s = get(evolutions.getEvolutionList().get(i).getUrl());
                Evolution evolution = gson.fromJson(s, Evolution.class);
                allEvolutions.add(evolution);
            }

        }
        List<EvolutionDTO> listEvolutionDTO = new ArrayList<>();

        for(Evolution evolution : allEvolutions) {
            EvolutionDTO evolutionDTO = new EvolutionDTO();
            List<String> strEvolution = new ArrayList<>();
            strEvolution.add(evolution.getEvol().getSpecies().getName());
            for (Evolves_to evolves_to : evolution.getEvol().getEvolves_to()) {
                strEvolution.add(evolves_to.getSpecies().getName());
                evolves_to.getEvolves_to().forEach(x -> strEvolution.add(x.getSpecies().getName()));
            }
            evolutionDTO.setEvolutions(strEvolution);
            //listEvolutionDTO.add(new EvolutionDTO().setEvolutions(strEvolution));
            listEvolutionDTO.add(evolutionDTO);
        }



        for(Pokemon pokemon : allPokemons){
            PokemonDTO pokemonDTO = new PokemonDTO();

            pokemonDTO.setName(pokemon.getName());

            List<String> types = new ArrayList<>();
            pokemon.getTypes().forEach(x -> types.add(x.getTypeName().getName()));
            pokemonDTO.setTypes(types);

            pokemonDTO.setPngImageUrl(pokemon.getSprites().getImage().getPokemonImage().getFront_default());
            pokemonDTO.setSvgImageUrl(pokemon.getSprites().getImage().getPokemonImageSvg().getFront_default());

            Map<String, Integer> stats = new HashMap<>();
            pokemon.getStats().forEach(x ->  stats.put(x.getStatName().getName(), x.getBase_stat()));
            pokemonDTO.setStats(stats);

            listPokemonDTO.add(pokemonDTO);
        }

        for(EvolutionDTO evolutionDTO : listEvolutionDTO){
            List<String> strEvolutions = evolutionDTO.getEvolutions();
            for(int i = 0; i < strEvolutions.size(); i++){
                PokemonDTO PokemonLast = getPokemonDTO(strEvolutions.get(i));
                if(i+1 < strEvolutions.size() && PokemonLast != null) {
                    PokemonLast.setEvolution(strEvolutions.get(i+1));
                }
                else{
                    //PokemonLast.setEvolution(null);
                }
                //System.out.println(listPokemonDTO.get(i).getName());
            }
            System.out.println(listPokemonDTO.get(0).getName());
        }

        return listPokemonDTO;
    }

    private String get(String url) throws IOException {
        return Jsoup.connect(url).ignoreContentType(true).execute().body();
    }

    private   PokemonDTO getPokemonDTO(String pokemonName){
        for(PokemonDTO pokemonDTO : listPokemonDTO) {
            if(pokemonDTO.getName().equals(pokemonName)){
                return pokemonDTO;
            }
        }
        return null;
    }
}



