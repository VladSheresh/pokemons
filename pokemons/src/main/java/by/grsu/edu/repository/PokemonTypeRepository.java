package by.grsu.edu.repository;

import by.grsu.edu.model.Pokemon;
import by.grsu.edu.model.PokemonType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class PokemonTypeRepository {
    private final SessionFactory sessionFactory;

    public PokemonTypeRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void create(PokemonType pokemonType){
        sessionFactory.getCurrentSession().save(pokemonType);
   }
    @Transactional
    public PokemonType getWithName(String name){
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<PokemonType> query = criteriaBuilder.createQuery(PokemonType.class);
        Root<PokemonType> from = query.from(PokemonType.class);
        query.where(criteriaBuilder.equal(from.get("name"), name));
        return session.createQuery(query).getSingleResult();
//        String sql = "select from type where type.name_type like " + name;
//        return (PokemonType)sessionFactory.getCurrentSession().createSQLQuery(sql).getResultList().stream().findFirst().orElse(null);
    }
}
