package co.simplo.starwars.repository;

import co.simplo.starwars.model.Terrain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TerrainRepository extends JpaRepository<Terrain,Integer> {
}
