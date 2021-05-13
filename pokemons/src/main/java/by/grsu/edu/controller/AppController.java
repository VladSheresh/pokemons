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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller("/")
public class AppController {
    private static final List<String> statsOrdered = Arrays.asList("hp", "");

    @Autowired
    private PokemonsService pokemonsService;

    // localhost/hello?page=2&count=20
    @RequestMapping(path = "hello", method = RequestMethod.GET)
    public ModelAndView hello(@RequestParam(name = "page", defaultValue = "1") Integer pageNumber,
                              @RequestParam(name = "count", defaultValue = "12") Integer countPerPage) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main");
        modelAndView.addObject("list", pokemonsService.getPokemons(Pagination.of(pageNumber, countPerPage)));
        int backPageNumber = pageNumber==1?1:pageNumber-1;
        modelAndView.addObject("back", "/hello?page=" + backPageNumber + "&count=" + countPerPage);
        modelAndView.addObject("next", "/hello?page=" + ++pageNumber + "&count=" + countPerPage);
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
        List<Pokemon> pokemonList = new ArrayList<>();
        pokemonList.add(firstPokemon);
        pokemonList.add(secondPokemon);
        firstPokemon.getStats().keySet().sort
        modelAndView.addObject("pokemons", pokemonList);
        return modelAndView;
    }
}
