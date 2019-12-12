package co.simplo.starwars.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name= "climates")
public class Climate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name="climate_generator",
            sequenceName = "climate_id_seq",
            allocationSize = 1)
    private Integer id;
    @Column(nullable = false,name="climate_name",unique=true)
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "planetClimates")
    private Set<Planet> planetSet = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Planet> getPlanetSet() {
        return planetSet;
    }

    public void setPlanetSet(Set<Planet> planetSet) {
        this.planetSet = planetSet;
    }
}
