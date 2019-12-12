package co.simplo.starwars.repository;

import co.simplo.starwars.model.Planet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanetRepository extends JpaRepository<Planet,Integer> {
}
