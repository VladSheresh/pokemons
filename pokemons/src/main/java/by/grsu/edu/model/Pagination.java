package by.grsu.edu.model;

public class Pagination {

    private final int page;
    private final int pageSize;
    private final String idPokemon;
    private final String idPokemonEvolution;
    private final String pokemonType;

    private Pagination(int page, int pageSize, String idPokemon, String idPokemonEvolution, String pokemonType) {
        this.page = page;
        this.pageSize = pageSize;
        this.idPokemon = idPokemon;
        this.idPokemonEvolution = idPokemonEvolution;
        this.pokemonType = pokemonType;
    }

    public static Pagination of(int page, int pageSize, String idPokemon, String idPokemonEvolution, String pokemonType){
        return new Pagination(page, pageSize, idPokemon, idPokemonEvolution, pokemonType);
    }

    public int getPage() {
        return page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public String getIdPokemon() {
        return idPokemon;
    }

    public String getIdPokemonEvolution() {
        return idPokemonEvolution;
    }
    public String getPokemonType() {
        return pokemonType;
    }
}
