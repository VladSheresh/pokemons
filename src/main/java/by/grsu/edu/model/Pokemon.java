package by.grsu.edu.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "poke_gen")
    @SequenceGenerator(name="poke_gen", sequenceName="pokemon_sequence", allocationSize = 1)
    private Long id;
    @Column
    private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "pokemon_type",
            joinColumns = @JoinColumn(name = "pokemon_id"),
            inverseJoinColumns = @JoinColumn(name = "type_id"))
    private List<PokemonType> types;
    @Column(name = "image_png")
    private String pngImageUrl;
    @Column(name = "image_svg")
    private String svgImageUrl;
    @Column
    private Integer hp;
    @Column
    private Integer attack;
    @Column
    private Integer defense;
    @Column
    private Integer speed;
    @Column(name = "special_attack")
    private Integer specialAttack;
    @Column(name = "special_defense")
    private Integer specialDefense;
    @Transient
    private Map<String, Integer> stats;
    @Column
    private String  evolution;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PokemonType> getTypes() {
        return types;
    }

    public void setTypes(List<PokemonType> types) {
        this.types = types;
    }

    public String getPngImageUrl() {
        return pngImageUrl;
    }

    public void setPngImageUrl(String pngImageUrl) {
        this.pngImageUrl = pngImageUrl;
    }

    public String getSvgImageUrl() {
        return svgImageUrl;
    }

    public void setSvgImageUrl(String svgImageUrl) {
        this.svgImageUrl = svgImageUrl;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public Integer getAttack() {
        return attack;
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    public Integer getDefense() {
        return defense;
    }

    public void setDefense(Integer defense) {
        this.defense = defense;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getSpecialAttack() {
        return specialAttack;
    }

    public void setSpecialAttack(Integer specialAttack) {
        this.specialAttack = specialAttack;
    }

    public Integer getSpecialDefense() {
        return specialDefense;
    }

    public void setSpecialDefense(Integer specialDefense) {
        this.specialDefense = specialDefense;
    }

    public Map<String, Integer> getStats() {
        return stats;
    }

    public void setStats(Map<String, Integer> stats) {
        this.stats = stats;
    }

    public String getEvolution() {
        return evolution;
    }

    public void setEvolution(String evolution) {
        this.evolution = evolution;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pokemon that = (Pokemon) o;
        return name.equals(that.name);
    }
}
