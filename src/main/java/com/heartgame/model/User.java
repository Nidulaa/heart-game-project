package com.heartgame.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user in the system (Virtual Identity).
 */
public class User {
    
    private String username;
    private int totalGamesPlayed;
    private int gamesWon;
    private int gamesLost;
    private int bestScore;
    private long bestTimeSeconds;
    private int currentStreak;
    private int bestStreak;
    private List<String> unlockedAchievements;
    private LocalDateTime registrationDate;
    private LocalDateTime lastLogin;
    
    public User(String username) {
        this.username = username;
        this.totalGamesPlayed = 0;
        this.gamesWon = 0;
        this.gamesLost = 0;
        this.bestScore = 0;
        this.bestTimeSeconds = Long.MAX_VALUE;
        this.currentStreak = 0;
        this.bestStreak = 0;
        this.unlockedAchievements = new ArrayList<>();
        this.registrationDate = LocalDateTime.now();
        this.lastLogin = LocalDateTime.now();
    }
    
    public void updateStats(boolean won, int score, long timeSeconds) {
        totalGamesPlayed++;
        
        if (won) {
            gamesWon++;
            currentStreak++;
            
            if (currentStreak > bestStreak) {
                bestStreak = currentStreak;
            }
            
            if (score > bestScore) {
                bestScore = score;
            }
            
            if (timeSeconds < bestTimeSeconds) {
                bestTimeSeconds = timeSeconds;
            }
        } else {
            gamesLost++;
            currentStreak = 0;
        }
        
        lastLogin = LocalDateTime.now();
    }
    
    public void unlockAchievement(String achievementId) {
        if (!unlockedAchievements.contains(achievementId)) {
            unlockedAchievements.add(achievementId);
        }
    }
    
    public boolean hasAchievement(String achievementId) {
        return unlockedAchievements.contains(achievementId);
    }
    
    public double getWinRate() {
        if (totalGamesPlayed == 0) {
            return 0.0;
        }
        return (double) gamesWon / totalGamesPlayed * 100.0;
    }
    
    public String getUsername() { return username; }
    public int getTotalGamesPlayed() { return totalGamesPlayed; }
    public int getGamesWon() { return gamesWon; }
    public int getGamesLost() { return gamesLost; }
    public int getBestScore() { return bestScore; }
    public long getBestTimeSeconds() { return bestTimeSeconds; }
    public int getCurrentStreak() { return currentStreak; }
    public int getBestStreak() { return bestStreak; }
    public List<String> getUnlockedAchievements() { return new ArrayList<>(unlockedAchievements); }
    public LocalDateTime getRegistrationDate() { return registrationDate; }
    public LocalDateTime getLastLogin() { return lastLogin; }
    public void setLastLogin(LocalDateTime lastLogin) { this.lastLogin = lastLogin; }
}