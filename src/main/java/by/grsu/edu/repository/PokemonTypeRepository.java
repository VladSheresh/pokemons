package by.grsu.edu.repository;

import by.grsu.edu.model.PokemonType;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class PokemonTypeRepository {
    private final SessionFactory sessionFactory;

    public PokemonTypeRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void create(PokemonType pokemonType){
        sessionFactory.getCurrentSession().save(pokemonType);
   }
}
