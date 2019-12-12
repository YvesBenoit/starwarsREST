package co.simplo.starwars.model;

import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "planets")
public class Planet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "planet_generator",
            sequenceName = "planet_id_seq",
            allocationSize = 1)
    private Integer id;
    @Column(nullable = false, name = "planet_name", unique = true)
    private String name;
    @Column()
    private Integer rotation_period;
    @Column()
    private Integer orbital_period;
    @Column()
    private Integer diameter;
    @Column()
    private BigDecimal gravity;
    @Column()
    private Integer surface_water;
    @Column()
    private Long population;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "planet_terrains",
            joinColumns = @JoinColumn(name = "planet_idx"),
            inverseJoinColumns = @JoinColumn(name = "terrain_idx"))
    private Set<Terrain> planetTerrains = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "planet_climates",
            joinColumns = @JoinColumn(name = "planet_idx"),
            inverseJoinColumns = @JoinColumn(name = "climate_idx"))
    private Set<Climate> planetClimates = new HashSet<>();

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

    public Integer getRotation_period() {
        return rotation_period;
    }

    public void setRotation_period(Integer rotation_period) {
        this.rotation_period = rotation_period;
    }

    public Integer getOrbital_period() {
        return orbital_period;
    }

    public void setOrbital_period(Integer orbital_period) {
        this.orbital_period = orbital_period;
    }

    public Integer getDiameter() {
        return diameter;
    }

    public void setDiameter(Integer diameter) {
        this.diameter = diameter;
    }

    public BigDecimal getGravity() {
        return gravity;
    }

    public void setGravity(BigDecimal gravity) {
        this.gravity = gravity.setScale(2);
    }

    public Integer getSurface_water() {
        return surface_water;
    }

    public void setSurface_water(Integer surface_water) {
        this.surface_water = surface_water;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    public Set<Terrain> getPlanetTerrains() {
        return planetTerrains;
    }

    public void setPlanetTerrains(Set<Terrain> planetTerrains) {
        this.planetTerrains = planetTerrains;
    }

    public Set<Climate> getPlanetClimates() {
        return planetClimates;
    }

    public void setPlanetClimates(Set<Climate> planetClimates) {
        this.planetClimates = planetClimates;
    }
}
