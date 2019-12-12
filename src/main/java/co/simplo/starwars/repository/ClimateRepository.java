package co.simplo.starwars.repository;

import co.simplo.starwars.model.Climate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClimateRepository extends JpaRepository<Climate,Integer> {
}
