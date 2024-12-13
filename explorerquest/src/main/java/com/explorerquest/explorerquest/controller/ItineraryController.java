package com.explorerquest.explorerquest.controller;

import com.explorerquest.explorerquest.service.GeoapifyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/itineraries")
public class ItineraryController {

    private final GeoapifyService geoapifyService;

    public ItineraryController(GeoapifyService geoapifyService) {
        this.geoapifyService = geoapifyService;
    }

    /**
     * Endpoint per cercare punti di interesse in una città.
     *
     * @param city Il nome della città in cui cercare le attrazioni.
     * @return Lista di punti di interesse in formato JSON.
     */
    @GetMapping("/search")
    public ResponseEntity<?> searchPointsOfInterest(@RequestParam String city) {
        try {
            String response = geoapifyService.getPointsOfInterest(city);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Errore durante la ricerca delle attrazioni: " + e.getMessage());
        }
    }

    /**
     * Endpoint per ottenere un itinerario specifico per ID.
     *
     * @param id L'ID dell'itinerario da recuperare.
     * @return Itinerario corrispondente all'ID fornito.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getItineraryById(@PathVariable Long id) {
        // Implementa la logica per ottenere un itinerario specifico per ID.
        return ResponseEntity.ok("Funzionalità in sviluppo");
    }

    /**
     * Placeholder per aggiungere un nuovo itinerario.
     *
     * @param requestBody Dati relativi al nuovo itinerario.
     * @return Conferma dell'aggiunta dell'itinerario.
     */
    @PostMapping
    public ResponseEntity<?> createItinerary(@RequestBody String requestBody) {
        // Implementa la logica per creare un nuovo itinerario.
        return ResponseEntity.ok("Creazione itinerario in sviluppo");
    }

    /**
     * Placeholder per aggiornare un itinerario esistente.
     *
     * @param id          L'ID dell'itinerario da aggiornare.
     * @param requestBody Dati aggiornati dell'itinerario.
     * @return Conferma dell'aggiornamento.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateItinerary(@PathVariable Long id, @RequestBody String requestBody) {
        // Implementa la logica per aggiornare un itinerario esistente.
        return ResponseEntity.ok("Aggiornamento itinerario in sviluppo");
    }

    /**
     * Placeholder per eliminare un itinerario.
     *
     * @param id L'ID dell'itinerario da eliminare.
     * @return Conferma dell'eliminazione.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItinerary(@PathVariable Long id) {
        // Implementa la logica per eliminare un itinerario.
        return ResponseEntity.ok("Eliminazione itinerario in sviluppo");
    }
}
