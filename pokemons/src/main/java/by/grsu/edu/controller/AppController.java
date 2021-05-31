package by.grsu.edu.controller;

import by.grsu.edu.data.JsonDataImporter;
import by.grsu.edu.model.Pagination;
import by.grsu.edu.model.Pokemon;
import by.grsu.edu.service.PokemonsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller("/")
public class AppController {
    private static final List<String> statsOrdered = Arrays.asList("hp", "attack", "defense", "speed", "special-attack", "special-defense");

    private static final List<String> typesOrdered = Arrays.asList("fire", "water", "ice", "ground", "grass", "dark", "psychic", "dragon");
   @Autowired
    private PokemonsService pokemonsService;

    // localhost/hello?page=2&count=20
    @RequestMapping(path = "hello", method = RequestMethod.GET)
    public ModelAndView hello(@RequestParam(name = "page", defaultValue = "1") Integer pageNumber,
                              @RequestParam(name = "count", defaultValue = "12") Integer countPerPage,
                              @RequestParam(name = "idPokemon", defaultValue = "") String pokemonId,
                              @RequestParam(name = "idEvolution", defaultValue = "") String idPokemonEvolution,
                              @RequestParam(name = "type", defaultValue = "") String pokemonType){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main");
        modelAndView.addObject("list", pokemonsService.getPokemons(Pagination.of(pageNumber, countPerPage, pokemonId, idPokemonEvolution, pokemonType)));
        int backPageNumber = pageNumber == 1 ? 1 : pageNumber - 1;
        int nextPageNumber = pageNumber > pokemonsService.getPokemonNames().size() / countPerPage ? pageNumber : ++pageNumber;
        modelAndView.addObject("back", "/hello?page=" + backPageNumber + "&count=" + countPerPage + "&idPokemon=" + pokemonId + "&idEvolution=" + idPokemonEvolution + "&type=" + pokemonType);
        modelAndView.addObject("next", "/hello?page=" + nextPageNumber + "&count=" + countPerPage + "&idPokemon=" + pokemonId + "&idEvolution=" + idPokemonEvolution + "&type=" + pokemonType);
        List<String> pokemonNames = pokemonsService.getPokemonNames();
        modelAndView.addObject("names", pokemonNames);
        modelAndView.addObject("types", typesOrdered);

        return modelAndView;
    }


    // localhost/cmp?first=2&second=20
    @RequestMapping(path = "cmp", method = RequestMethod.GET)
    public ModelAndView compare(@RequestParam(name = "first", required = false) String firstPokemonId,
                                @RequestParam(name = "second", required = false) String secondPokemonId) {
        Pokemon firstPokemon = pokemonsService.getPokemon(firstPokemonId);
        Pokemon secondPokemon = pokemonsService.getPokemon(secondPokemonId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("compare");
        modelAndView.addObject("first", firstPokemon);
        modelAndView.addObject("second", secondPokemon);
        modelAndView.addObject("firstStats", pokemonsService.getPokemonStats(firstPokemonId));
        modelAndView.addObject("secondStats", pokemonsService.getPokemonStats(secondPokemonId));
        modelAndView.addObject("stats", statsOrdered);
        List<String> pokemonNames = pokemonsService.getPokemonNames();
        modelAndView.addObject("names", pokemonNames);
        modelAndView.addObject("types", typesOrdered);
        return modelAndView;
    }

    @RequestMapping(path = "loadPokemons", method = RequestMethod.GET)
    public ModelAndView loadPokemons() throws IOException {
        String property = System.getProperty("updateDB", "false");
        if ("true".equals(property)) {
            System.setProperty("loaded.poks", "true");
            pokemonsService.clearData();
            pokemonsService.loadPokemons();
        }
            return new ModelAndView("redirect:/hello");
    }
}
