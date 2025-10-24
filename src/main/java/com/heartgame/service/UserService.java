package com.heartgame.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.heartgame.model.Achievement;
import com.heartgame.model.User;
import com.heartgame.util.FileManager;
import com.heartgame.util.LocalDateTimeAdapter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service for managing users (Virtual Identity).
 * 
 * This class demonstrates:
 * 1. LOW COUPLING - doesn't depend on UI or game logic
 * 2. HIGH COHESION - only handles user-related operations
 * 3. INTEROPERABILITY - saves/loads user data from JSON files
 * 4. VIRTUAL IDENTITY - manages user profiles and authentication
 */
public class UserService {
    
    private static final String USERS_FILE = "users.json";
    private Map<String, User> users;
    private Gson gson;
    private List<Achievement> availableAchievements;
    
    public UserService() {
        // Initialize Gson with custom adapter for LocalDateTime (Interoperability)
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .setPrettyPrinting()
                .create();
        
        this.users = new HashMap<>();
        this.availableAchievements = initializeAchievements();
        loadUsers();
    }
    
    /**
     * Initialize available achievements (Virtual Identity).
     */
    private List<Achievement> initializeAchievements() {
        List<Achievement> achievements = new ArrayList<>();
        
        achievements.add(new Achievement(
            "FIRST_WIN",
            "First Victory",
            "Win your first game",
            "🏆"
        ));
        
        achievements.add(new Achievement(
            "SPEED_DEMON",
            "Speed Demon",
            "Win a game in under 30 seconds",
            "⚡"
        ));
        
        achievements.add(new Achievement(
            "PERFECT_SCORE",
            "Perfect Score",
            "Achieve a score of 1000 or more",
            "⭐"
        ));
        
        achievements.add(new Achievement(
            "DEDICATED",
            "Dedicated Player",
            "Play 10 games",
            "🎮"
        ));
        
        achievements.add(new Achievement(
            "STREAK_MASTER",
            "Streak Master",
            "Win 5 games in a row",
            "🔥"
        ));
        
        achievements.add(new Achievement(
            "VETERAN",
            "Veteran",
            "Play 50 games",
            "🎖️"
        ));
        
        return achievements;
    }
    
    /**
     * Registers a new user (Virtual Identity).
     */
    public boolean registerUser(String username) {
        if (username == null || username.trim().isEmpty()) {
            return false;
        }
        
        if (users.containsKey(username)) {
            return false; // User already exists
        }
        
        User newUser = new User(username);
        users.put(username, newUser);
        saveUsers();
        
        return true;
    }
    
    /**
     * Authenticates a user (Virtual Identity).
     */
    public User login(String username) {
        return users.get(username);
    }
    
    /**
     * Checks if a username exists.
     */
    public boolean userExists(String username) {
        return users.containsKey(username);
    }
    
    /**
     * Updates user data and saves to file.
     */
    public void updateUser(User user) {
        if (user != null) {
            users.put(user.getUsername(), user);
            saveUsers();
        }
    }
    
    /**
     * Checks and unlocks achievements for a user.
     * Returns list of newly unlocked achievements.
     */
    public List<Achievement> checkAndUnlockAchievements(User user) {
        List<Achievement> newlyUnlocked = new ArrayList<>();
        
        // First Win
        if (user.getGamesWon() >= 1 && !user.hasAchievement("FIRST_WIN")) {
            user.unlockAchievement("FIRST_WIN");
            newlyUnlocked.add(getAchievementById("FIRST_WIN"));
        }
        
        // Speed Demon
        if (user.getBestTimeSeconds() <= 30 && !user.hasAchievement("SPEED_DEMON")) {
            user.unlockAchievement("SPEED_DEMON");
            newlyUnlocked.add(getAchievementById("SPEED_DEMON"));
        }
        
        // Perfect Score
        if (user.getBestScore() >= 1000 && !user.hasAchievement("PERFECT_SCORE")) {
            user.unlockAchievement("PERFECT_SCORE");
            newlyUnlocked.add(getAchievementById("PERFECT_SCORE"));
        }
        
        // Dedicated
        if (user.getTotalGamesPlayed() >= 10 && !user.hasAchievement("DEDICATED")) {
            user.unlockAchievement("DEDICATED");
            newlyUnlocked.add(getAchievementById("DEDICATED"));
        }
        
        // Streak Master
        if (user.getBestStreak() >= 5 && !user.hasAchievement("STREAK_MASTER")) {
            user.unlockAchievement("STREAK_MASTER");
            newlyUnlocked.add(getAchievementById("STREAK_MASTER"));
        }
        
        // Veteran
        if (user.getTotalGamesPlayed() >= 50 && !user.hasAchievement("VETERAN")) {
            user.unlockAchievement("VETERAN");
            newlyUnlocked.add(getAchievementById("VETERAN"));
        }
        
        if (!newlyUnlocked.isEmpty()) {
            updateUser(user);
        }
        
        return newlyUnlocked;
    }
    
    private Achievement getAchievementById(String id) {
        return availableAchievements.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    
    public List<Achievement> getAvailableAchievements() {
        return new ArrayList<>(availableAchievements);
    }
    
    /**
     * Loads users from JSON file (Interoperability - File I/O).
     */
    private void loadUsers() {
        try {
            String json = FileManager.readFile(USERS_FILE);
            Type type = new TypeToken<HashMap<String, User>>(){}.getType();
            Map<String, User> loadedUsers = gson.fromJson(json, type);
            
            if (loadedUsers != null) {
                this.users = loadedUsers;
            }
        } catch (IOException e) {
            System.out.println("No existing users file found. Starting fresh.");
            this.users = new HashMap<>();
        }
    }
    
    /**
     * Saves users to JSON file (Interoperability - File I/O).
     */
    private void saveUsers() {
        try {
            String json = gson.toJson(users);
            FileManager.writeFile(USERS_FILE, json);
        } catch (IOException e) {
            System.err.println("Error saving users: " + e.getMessage());
        }
    }
    
    /**
     * Gets all users (for leaderboard, etc.)
     */
    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }
}