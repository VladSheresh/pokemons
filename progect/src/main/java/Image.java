import com.google.gson.annotations.SerializedName;

public class Image {
    @SerializedName("official-artwork")
    private PokemonImage  pokemonImage;
    @SerializedName("dream_world")
    private PokemonImageSvg pokemonImageSvg;

    public PokemonImageSvg getPokemonImageSvg() {
        return pokemonImageSvg;
    }

    public void setPokemonImageSvg(PokemonImageSvg pokemonImageSvg) {
        this.pokemonImageSvg = pokemonImageSvg;
    }

    public PokemonImage getPokemonImage() {
        return pokemonImage;
    }

    public void setPokemonImage(PokemonImage pokemonImage) {
        this.pokemonImage = pokemonImage;
    }

}


