package com.explorerquest.explorerquest.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class GeoapifyService {

    private final WebClient webClient;

    @Value("${geoapify.api.key}")
    private String apiKey;

    public GeoapifyService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.geoapify.com").build();
    }

    /**
     * Metodo per ottenere i punti di interesse di una città.
     *
     * @param city Nome della città per cui cercare i punti di interesse.
     * @return Risultati in formato JSON.
     */
    public String getPointsOfInterest(String city) {
        try {
            // Passo 1: Ottieni le coordinate della città tramite geocoding
            String geoCodingResponse = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/v1/geocode/search")
                            .queryParam("text", city)
                            .queryParam("apiKey", apiKey)
                            .queryParam("lang", "it") // Specifica la lingua italiana
                            .build())
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            // Estrai latitudine e longitudine
            double latitude = extractLatitude(geoCodingResponse);
            double longitude = extractLongitude(geoCodingResponse);

            // Passo 2: Usa le coordinate per cercare attrazioni turistiche
            return webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/v2/places")
                            .queryParam("categories", "tourism.sights") // Attrazioni turistiche
                            .queryParam("filter", "circle:" + longitude + "," + latitude + ",1000") // Raggio 1 km
                            .queryParam("limit", "10") // Limita il numero di risultati
                            .queryParam("apiKey", apiKey) // Chiave API
                            .queryParam("lang", "it") // Specifica la lingua italiana
                            .build())
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        } catch (Exception e) {
            throw new RuntimeException("Errore durante il recupero dei POI da Geoapify", e);
        }
    }

    /**
     * Metodo per estrarre la latitudine dalla risposta del geocoding.
     *
     * @param geoCodingResponse Risposta JSON del geocoding.
     * @return Latitudine estratta.
     */
    private double extractLatitude(String geoCodingResponse) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(geoCodingResponse);
            return rootNode.at("/features/0/geometry/coordinates/1").asDouble();
        } catch (Exception e) {
            throw new RuntimeException("Errore durante l'estrazione della latitudine", e);
        }
    }

    /**
     * Metodo per estrarre la longitudine dalla risposta del geocoding.
     *
     * @param geoCodingResponse Risposta JSON del geocoding.
     * @return Longitudine estratta.
     */
    private double extractLongitude(String geoCodingResponse) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(geoCodingResponse);
            return rootNode.at("/features/0/geometry/coordinates/0").asDouble();
        } catch (Exception e) {
            throw new RuntimeException("Errore durante l'estrazione della longitudine", e);
        }
    }
}
