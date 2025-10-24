package com.heartgame.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.heartgame.model.Card;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Service for interacting with the Heart API.
 * 
 * This class demonstrates INTEROPERABILITY by:
 * 1. Making HTTP requests to external API
 * 2. Parsing JSON responses
 * 3. Converting external data to internal model objects
 * 
 * It also demonstrates LOW COUPLING - it only knows about Card objects,
 * not about Users, UI, or game logic.
 */
public class HeartAPIService {
    
    private static final String API_BASE_URL = "https://www.random.org/playing-cards/";
    private static final Random random = new Random();
    
    /**
     * Fetches cards from the Heart API.
     * 
     * @param numberOfCards Number of cards to fetch
     * @return List of Card objects
     * @throws IOException if API call fails
     */
    public List<Card> fetchCards(int numberOfCards) throws IOException {
        // Build API URL with parameters
        String urlString = API_BASE_URL + "?number=" + numberOfCards + "&format=json";
        
        // Make HTTP GET request (Interoperability - HTTP Communication)
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        
        int responseCode = connection.getResponseCode();
        
        if (responseCode == HttpURLConnection.HTTP_OK) {
            // Read response
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream())
            );
            StringBuilder response = new StringBuilder();
            String line;
            
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            
            // Parse JSON response (Interoperability - JSON Parsing)
            return parseCardsFromJson(response.toString());
        } else {
            throw new IOException("API request failed with response code: " + responseCode);
        }
    }
    
    /**
     * Parses JSON response from API into Card objects.
     * Demonstrates INTEROPERABILITY through JSON parsing.
     */
    private List<Card> parseCardsFromJson(String jsonString) {
        List<Card> cards = new ArrayList<>();
        
        try {
            JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();
            JsonArray cardsArray = jsonObject.getAsJsonArray("cards");
            
            for (JsonElement element : cardsArray) {
                JsonObject cardJson = element.getAsJsonObject();
                String suit = cardJson.get("suit").getAsString();
                String rank = cardJson.get("rank").getAsString();
                
                cards.add(new Card(suit, rank));
            }
        } catch (Exception e) {
            System.err.println("Error parsing JSON: " + e.getMessage());
            // Fallback to generating random cards locally
            cards = generateFallbackCards(5);
        }
        
        return cards;
    }
    
    /**
     * Generates a target number for the puzzle based on the cards.
     */
    public int generateTargetNumber(List<Card> cards) {
        if (cards.isEmpty()) {
            return 10;
        }
        
        // Generate a target that's achievable with the given cards
        int min = cards.stream().mapToInt(Card::getValue).min().orElse(1);
        int max = cards.stream().mapToInt(Card::getValue).sum();
        
        // Target should be between min and max
        return min + random.nextInt(Math.max(1, max - min));
    }
    
    /**
     * Fallback method to generate cards locally if API fails.
     * Ensures the game can still function (robustness).
     */
    private List<Card> generateFallbackCards(int count) {
        List<Card> cards = new ArrayList<>();
        String[] suits = {"hearts", "diamonds", "clubs", "spades"};
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        
        for (int i = 0; i < count; i++) {
            String suit = suits[random.nextInt(suits.length)];
            String rank = ranks[random.nextInt(ranks.length)];
            cards.add(new Card(suit, rank));
        }
        
        return cards;
    }
}