package com.heartgame.model;

public class Card {
    private String suit;
    private String rank;
    private boolean selected;
    
    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
        this.selected = false;
    }
    
    public int getValue() {
        switch (rank) {
            case "A": return 1;
            case "J": return 11;
            case "Q": return 12;
            case "K": return 13;
            default:
                try {
                    return Integer.parseInt(rank);
                } catch (NumberFormatException e) {
                    return 0;
                }
        }
    }
    
    public String getDisplayName() {
        return rank + " of " + suit;
    }
    
    public String getSuitEmoji() {
        switch (suit.toLowerCase()) {
            case "hearts": return "♥";
            case "diamonds": return "♦";
            case "clubs": return "♣";
            case "spades": return "♠";
            default: return "";
        }
    }
    
    public String getSuit() { return suit; }
    public String getRank() { return rank; }
    public boolean isSelected() { return selected; }
    public void setSelected(boolean selected) { this.selected = selected; }
    
    @Override
    public String toString() {
        return getDisplayName();
    }
}