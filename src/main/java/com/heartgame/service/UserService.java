package com.heartgame.service;

import com.heartgame.model.User;
import java.util.ArrayList;
import java.util.List;

/**
 * UserService handles all user-related operations (Virtual Identity).
 */
public class UserService {
    private List<User> users = new ArrayList<>();

    /**
     * Registers a new user.
     * @param username Unique username for the user.
     * @return The newly created User object.
     */
    public User registerUser(String username) {
        User newUser = new User(username);
        users.add(newUser);
        return newUser;
    }

    /**
     * Finds a user by username.
     * @param username The username to search for.
     * @return The User object if found, null otherwise.
     */
    public User findUser(String username) {
        return users.stream().filter(user -> user.getUsername().equals(username)).findFirst().orElse(null);
    }

    /**
     * Returns all users in the system.
     * @return List of users.
     */
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }
}