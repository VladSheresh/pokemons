package by.grsu.edu.model;

import javax.persistence.*;

@Entity
@Table(name = "type")
public class PokemonType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "type_gen")
    @SequenceGenerator(name="type_gen", sequenceName="type_sequence", allocationSize = 1)
    private Long id;

    @Column(name = "name_type")
    private String name;

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
}
