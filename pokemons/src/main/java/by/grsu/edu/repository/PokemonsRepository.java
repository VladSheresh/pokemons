package by.grsu.edu.repository;

import by.grsu.edu.data.JsonDataImporter;
import by.grsu.edu.model.Pagination;
import by.grsu.edu.model.Pokemon;
import by.grsu.edu.model.PokemonType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class PokemonsRepository {

    private final SessionFactory sessionFactory;

    public PokemonsRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void create(Pokemon pokemon) {
        sessionFactory.getCurrentSession().save(pokemon);
    }

    public List<Pokemon> getPokemons(Pagination pagination) {
        String evolution = pagination.getIdPokemonEvolution();
        if (StringUtils.hasText(evolution)) {
            Deque<Pokemon> result = new LinkedList<>();
            Pokemon initialPokemon = getPokemon(evolution);
            Pokemon pokemon = initialPokemon;
            result.add(pokemon);
            while (StringUtils.hasText(pokemon.getEvolution())) {
                pokemon = getPokemon(pokemon.getEvolution());
                result.add(pokemon);
            }
            pokemon = initialPokemon;
            do {
                pokemon = getPreviousEvolutionPokemon(pokemon.getName());
                if (pokemon != null) {
                    result.addFirst(pokemon);
                }
            } while (pokemon != null);
            List<Pokemon> extendedEvolution = getExtendedEvolution(result.stream().map(Pokemon::getName).collect(Collectors.toList()));
            result.addAll(extendedEvolution);
            return new ArrayList<>(result);
        }
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Pokemon> query = criteriaBuilder.createQuery(Pokemon.class);
        Root<Pokemon> from = query.from(Pokemon.class);

        List<Predicate> predicates = new ArrayList<>();
        if (StringUtils.hasText(pagination.getIdPokemon())) {
            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(from.get("name")),
                    "%" + pagination.getIdPokemon().toLowerCase() + "%")
            );
        }

        if (StringUtils.hasText(pagination.getPokemonType())) {
            Subquery<Long> subquery = query.subquery(Long.class);
            Root<Pokemon> subqFrom = subquery.from(Pokemon.class);
            subquery.select(subqFrom.get("id")).distinct(true);
            Join<Pokemon, PokemonType> types = subqFrom.join("types");
            subquery.where(criteriaBuilder.like(types.get("name"), "%" + pagination.getPokemonType() + "%"));

            predicates.add(criteriaBuilder.in(
                    from.get("id")).value(subquery));
        }

        query.where(predicates.toArray(new Predicate[0]));
        int skip = (pagination.getPage() - 1) * pagination.getPageSize();
        List<Pokemon> resultList = session.createQuery(query).setFirstResult(skip).setMaxResults(pagination.getPageSize()).getResultList();

        return resultList;
    }

    private List<Pokemon> getExtendedEvolution(List<String> currentNames) {
        String lastEvolutionName = currentNames.get(currentNames.size() - 1);
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Pokemon> query = criteriaBuilder.createQuery(Pokemon.class);
        Root<Pokemon> from = query.from(Pokemon.class);

        query.where(
                criteriaBuilder.and(
                        criteriaBuilder.like(from.get("name"), "%" + lastEvolutionName + "%"),
                        criteriaBuilder.not(criteriaBuilder.in(from.get("name")).value(currentNames)))
        );
        return session.createQuery(query).getResultList();
    }

    public Pokemon getPokemon(String pokemonId) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Pokemon> query = criteriaBuilder.createQuery(Pokemon.class);
        Root<Pokemon> from = query.from(Pokemon.class);
        query.where(criteriaBuilder.equal(from.get("name"), pokemonId));
        return session.createQuery(query).getSingleResult();
    }

    public Pokemon getPreviousEvolutionPokemon(String pokemonName) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Pokemon> query = criteriaBuilder.createQuery(Pokemon.class);
        Root<Pokemon> from = query.from(Pokemon.class);
        query.where(criteriaBuilder.equal(from.get("evolution"), pokemonName));
        return session.createQuery(query).getResultList().stream().findFirst().orElse(null);
    }

    public List<String> getPokemonNames() {

        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<String> query = criteriaBuilder.createQuery(String.class);
        Root<Pokemon> from = query.from(Pokemon.class);
        query.select(from.get("name"));
        return session.createQuery(query).getResultList();
    }

    public Map<String, Integer> getPokemonStats(String pokemonId) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Pokemon> query = criteriaBuilder.createQuery(Pokemon.class);
        Root<Pokemon> from = query.from(Pokemon.class);
        query.where(criteriaBuilder.equal(from.get("name"), pokemonId));
        Pokemon pokemon = session.createQuery(query).getSingleResult();
        Map<String, Integer> pokemonStats = new HashMap<>();
        pokemonStats.put("hp", pokemon.getHp());
        pokemonStats.put("attack", pokemon.getAttack());
        pokemonStats.put("defense", pokemon.getDefense());
        pokemonStats.put("speed", pokemon.getSpeed());
        pokemonStats.put("special-attack", pokemon.getSpecialAttack());
        pokemonStats.put("special-defense", pokemon.getSpecialDefense());
        return pokemonStats;
    }

    public List<Pokemon> contextSearchPokemons(String pokemonId) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Pokemon> query = criteriaBuilder.createQuery(Pokemon.class);
        Root<Pokemon> from = query.from(Pokemon.class);
        query.where(criteriaBuilder.like(criteriaBuilder.lower(from.get("name")), "%" + pokemonId.toLowerCase() + "%"));
        return session.createQuery(query).getResultList();
    }

    public void createPokemon(Pokemon pokemon){
        Session session = sessionFactory.getCurrentSession();
        String sql = "INSERT INTO pokemon (url, name, attack, defense) VALUES (" + pokemon.getName() + ", "+ pokemon.getName() +", "+pokemon.getAttack()+", " + pokemon.getDefense()+ ")";
        session.createSQLQuery(sql).executeUpdate();

    }

    public void deletePokemon(Long id){
        Session session = sessionFactory.getCurrentSession();
        String sql1 = "delete from pokemon_type where pokemon_id =" + id;
        String sql = "delete from pokemon where pokemon.id =" + id;
        session.createSQLQuery(sql1).executeUpdate();
        session.createSQLQuery(sql).executeUpdate();

    }

    public void clearData(){
        Session session = sessionFactory.getCurrentSession();
        session.createSQLQuery("delete from pokemon_type").executeUpdate();
        session.createSQLQuery("delete from pokemon").executeUpdate();
        session.createSQLQuery("delete from type").executeUpdate();

    }
}
