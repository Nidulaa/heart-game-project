package com.heartgame.service;

import com.heartgame.model.Card;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 * HeartAPIService handles API communications with the Heart API.
 */
public class HeartAPIService {
    private static final String[] SUITS = {"hearts", "diamonds", "clubs", "spades"};
    private static final String[] RANKS = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    private Random random;

    public HeartAPIService() {
        this.random = new Random();
    }

    /**
     * Generates a random card.
     * @return A Card object with random suit and rank.
     */
    public Card generateRandomCard() {
        String suit = SUITS[random.nextInt(SUITS.length)];
        String rank = RANKS[random.nextInt(RANKS.length)];
        return new Card(suit, rank);
    }

    /**
     * Generates a list of random cards.
     * @param count Number of cards to generate.
     * @return List of randomly generated Card objects.
     */
    public List<Card> generateRandomCards(int count) {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            cards.add(generateRandomCard());
        }
        return cards;
    }
}