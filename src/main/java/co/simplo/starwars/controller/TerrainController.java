package co.simplo.starwars.controller;

import co.simplo.starwars.model.Terrain;
import co.simplo.starwars.repository.TerrainRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/starwars/terrains")
public class TerrainController {
    private TerrainRepository terrainRepository;

    public TerrainController(TerrainRepository terrainRepository) {
        this.terrainRepository = terrainRepository;
    }

    // Get the list of terrains
    @GetMapping("/liste")
    public ResponseEntity<List<Terrain>> getTerrains() {
        List<Terrain> terrains = terrainRepository.findAll();
        for (Terrain res : terrains) {
            System.out.println(res);
        }
        return ResponseEntity.ok(terrains);
    }

    // Create terrain
    @PostMapping/*("/create")*/
    public ResponseEntity<Terrain> create(@RequestBody Terrain terrain) {
        try {
            return ResponseEntity.ok(terrainRepository.save(terrain));
        } catch (Exception e) {
            return ResponseEntity.badRequest().header("Terrain is not valid ! ").build();
        }
    }

    // Read terrain
    @GetMapping("/{paramDeRecherche}")
    public ResponseEntity<Terrain> read(@PathVariable int paramDeRecherche) {
        return ResponseEntity.ok(terrainRepository.findById(paramDeRecherche).get());
    }


    // Update terrain
    @PutMapping("/{paramDeRecherche}")
    public ResponseEntity<?> update(@PathVariable int paramDeRecherche, @RequestBody Terrain terrain) {
        try {
            Terrain terrainRecupered  = terrainRepository.findById(paramDeRecherche).get();  // juste pour verifier l'existence en base
            terrain.setId(paramDeRecherche);
            return ResponseEntity.ok(terrainRepository.save(terrain));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("TerrainId to update is not valid ! ");
        }
    }

    // Delete terrain
    @DeleteMapping("/{paramDeRecherche}")
    public ResponseEntity<?> delete(@PathVariable int paramDeRecherche) {
        //    res = terrain.isPresent() ? terrain.get() : null;
        //  res = terrain.orElse(null);
        try {
            Terrain terrainToDelete  = terrainRepository.findById(paramDeRecherche).get();  // juste pour verifier l'existence en base
            terrainRepository.delete(terrainToDelete);
            return ResponseEntity.badRequest().body("TerrainId to delete is deleted Well done you're so great ! ");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("TerrainId to delete is not valid ! ");
        }
    }
}
