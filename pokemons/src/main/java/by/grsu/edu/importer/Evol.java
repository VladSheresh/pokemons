package by.grsu.edu.importer;

import java.util.List;

public class Evol {
    private Species species;
    private List<Evolves_to> evolves_to;

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public List<Evolves_to> getEvolves_to() {
        return evolves_to;
    }

    public void setEvolves_to(List<Evolves_to> evolves_to) {
        this.evolves_to = evolves_to;
    }
}
