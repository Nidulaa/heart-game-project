package com.heartgame.service;

import com.heartgame.model.Card;
import com.heartgame.model.GameSession;
import com.heartgame.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Core game logic engine.
 * 
 * This class demonstrates:
 * 1. HIGH COHESION - only contains game logic, no UI or I/O
 * 2. LOW COUPLING - works with model objects, independent of services
 */
public class GameEngine {
    
    private GameSession currentSession;
    private List<Card> selectedCards;
    private String currentOperation;
    
    public GameEngine() {
        this.selectedCards = new ArrayList<>();
        this.currentOperation = "+";
    }
    
    /**
     * Starts a new game session.
     */
    public void startGame(User user, String gameMode, int targetNumber, List<Card> cards) {
        this.currentSession = new GameSession(user.getUsername(), gameMode, targetNumber, cards);
        this.selectedCards.clear();
    }
    
    /**
     * Toggles card selection.
     */
    public void toggleCardSelection(Card card) {
        if (card.isSelected()) {
            card.setSelected(false);
            selectedCards.remove(card);
        } else {
            card.setSelected(true);
            selectedCards.add(card);
        }
    }
    
    /**
     * Sets the current mathematical operation.
     */
    public void setOperation(String operation) {
        this.currentOperation = operation;
    }
    
    /**
     * Calculates the result of selected cards with current operation.
     */
    public int calculateResult() {
        if (selectedCards.isEmpty()) {
            return 0;
        }
        
        int result = selectedCards.get(0).getValue();
        
        for (int i = 1; i < selectedCards.size(); i++) {
            int value = selectedCards.get(i).getValue();
            
            switch (currentOperation) {
                case "+":
                    result += value;
                    break;
                case "-":
                    result -= value;
                    break;
                case "*":
                    result *= value;
                    break;
                case "/":
                    if (value != 0) {
                        result /= value;
                    }
                    break;
            }
        }
        
        return result;
    }
    
    /**
     * Checks if the player's answer is correct.
     */
    public boolean checkAnswer() {
        int result = calculateResult();
        return result == currentSession.getTargetNumber();
    }
    
    /**
     * Calculates score based on performance.
     */
    public int calculateScore(long timeSeconds, String gameMode) {
        int baseScore = 100;
        
        // Difficulty multiplier based on target number
        int targetNumber = currentSession.getTargetNumber();
        int difficultyMultiplier = Math.max(1, targetNumber / 10);
        
        // Time bonus (faster = higher score)
        int timeBonus = Math.max(0, 300 - (int) timeSeconds * 5);
        
        // Mode multiplier
        int modeMultiplier = 1;
        switch (gameMode) {
            case "Timed":
                modeMultiplier = 2;
                break;
            case "Streak":
                modeMultiplier = 3;
                break;
        }
        
        // Card usage bonus (using more cards = higher score)
        int cardBonus = selectedCards.size() * 20;
        
        return (baseScore + timeBonus + cardBonus) * difficultyMultiplier * modeMultiplier;
    }
    
    /**
     * Ends the current game session.
     */
    public void endGame(boolean won, int score) {
        if (currentSession != null) {
            currentSession.endSession(won, score);
        }
    }
    
    /**
     * Validates if enough cards are selected.
     */
    public boolean hasValidSelection() {
        return selectedCards.size() >= 2;
    }
    
    // Getters
    public GameSession getCurrentSession() {
        return currentSession;
    }
    
    public List<Card> getSelectedCards() {
        return new ArrayList<>(selectedCards);
    }
    
    public String getCurrentOperation() {
        return currentOperation;
    }
    
    public void clearSelection() {
        for (Card card : selectedCards) {
            card.setSelected(false);
        }
        selectedCards.clear();
    }
}