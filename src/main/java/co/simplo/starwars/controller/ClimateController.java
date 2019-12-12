package co.simplo.starwars.controller;

import co.simplo.starwars.model.Climate;
import co.simplo.starwars.repository.ClimateRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/starwars/climates")
public class ClimateController {
    private ClimateRepository climateRepository;

    public ClimateController(ClimateRepository climateRepository) {
        this.climateRepository = climateRepository;
    }

    // Get the list of climates
    @GetMapping("/liste")
    public ResponseEntity<List<Climate>> getClimates() {
        List<Climate> climates = climateRepository.findAll();
        for (Climate res : climates) {
            System.out.println(res);
        }
        return ResponseEntity.ok(climates);
    }

    // Create climate
    @PostMapping
    public ResponseEntity<Climate> create(@RequestBody Climate climate) {
        try {
            return ResponseEntity.ok(climateRepository.save(climate));
        } catch (Exception e) {
            return ResponseEntity.badRequest().header("Climate is not valid ! ").build();
        }
    }

    // Read climate
    @GetMapping("/{paramDeRecherche}")
    public ResponseEntity<Climate> read(@PathVariable int paramDeRecherche) {
        return ResponseEntity.ok(climateRepository.findById(paramDeRecherche).get());
    }


    // Update climate
    @PutMapping("/{paramDeRecherche}")
    public ResponseEntity<?> update(@PathVariable int paramDeRecherche, @RequestBody Climate climate) {
        try {
            Climate climateRecupered  = climateRepository.findById(paramDeRecherche).get();  // juste pour verifier l'existence en base
            climate.setId(paramDeRecherche);
            return ResponseEntity.ok(climateRepository.save(climate));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("ClimateId to update is not valid ! ");
        }
    }

    // Delete climate
    @DeleteMapping("/{paramDeRecherche}")
    public ResponseEntity<?> delete(@PathVariable int paramDeRecherche) {
        //    res = climate.isPresent() ? climate.get() : null;
        //  res = climate.orElse(null);
        try {
            Climate climateToDelete  = climateRepository.findById(paramDeRecherche).get();  // juste pour verifier l'existence en base
            climateRepository.delete(climateToDelete);
            return ResponseEntity.badRequest().body("ClimateId to delete is deleted Well done you're so great ! ");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("ClimateId to delete is not valid ! ");
        }
    }
}
