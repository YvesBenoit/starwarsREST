package co.simplo.starwars.controller;

import co.simplo.starwars.model.Planet;
import co.simplo.starwars.repository.PlanetRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/starwars/planets")
public class PlanetController {
    private PlanetRepository planetRepository;

    public PlanetController(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

    // Get the list of planets
    @GetMapping("/liste")
    public ResponseEntity<List<Planet>> getPlanets() {
        List<Planet> planets = planetRepository.findAll();
        for (Planet res : planets) {
            System.out.println(res);
        }
        return ResponseEntity.ok(planets);
    }

    // Create planet
    @PostMapping/*("/create")*/
    public ResponseEntity<Planet> create(@RequestBody Planet planet) {
        try {
            return ResponseEntity.ok(planetRepository.save(planet));
        } catch (Exception e) {
            return ResponseEntity.badRequest().header("Planet is not valid ! ").build();
        }
    }

    // Read planet
    @GetMapping("/{paramDeRecherche}")
    public ResponseEntity<Planet> read(@PathVariable int paramDeRecherche) {
        return ResponseEntity.ok(planetRepository.findById(paramDeRecherche).get());
    }


    // Update planet
    @PutMapping("/{paramDeRecherche}")
    public ResponseEntity<?> update(@PathVariable int paramDeRecherche, @RequestBody Planet planet) {
        try {
            Planet planetRecupered  = planetRepository.findById(paramDeRecherche).get();  // juste pour verifier l'existence en base
            planet.setId(paramDeRecherche);
            return ResponseEntity.ok(planetRepository.save(planet));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("PlanetId to update is not valid ! ");
        }
    }

    // Delete planet
    @DeleteMapping("/{paramDeRecherche}")
    public ResponseEntity<?> delete(@PathVariable int paramDeRecherche) {
        //    res = planet.isPresent() ? planet.get() : null;
        //  res = planet.orElse(null);

      //  if(planetRepository.existsById(paramDeRecherche))  variante pour tester l'existence avant delete
        
        try {
            Planet planetToDelete  = planetRepository.findById(paramDeRecherche).get();  // juste pour verifier l'existence en base
            planetRepository.delete(planetToDelete);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("PlanetId to delete is not valid ! ");
        }
    }
}
