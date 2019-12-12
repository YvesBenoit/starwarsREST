package co.simplo.starwars.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name= "terrains")
public class Terrain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name="terrain_generator",
            sequenceName = "terrain_id_seq",
            allocationSize = 1)
    private Integer id;
    @Column(nullable = false,name="terrain_name",unique=true)
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "planetTerrains")
    private Set<Planet>  planetSet = new HashSet<>();

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
