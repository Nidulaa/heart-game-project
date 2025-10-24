package com.heartgame.service;

import com.heartgame.model.GameSession;
import java.util.ArrayList;
import java.util.List;

/**
 * GameEngine handles the main game logic.
 */
public class GameEngine {
    private List<GameSession> sessions;

    public GameEngine() {
        this.sessions = new ArrayList<>();
    }

    /**
     * Starts a new game session.
     * @param username The user playing the game.
     * @param gameMode The mode of the game.
     * @param targetNumber The target number for the game.
     * @param cards List of cards used in the game.
     * @return A new GameSession object.
     */
    public GameSession startNewSession(String username, String gameMode, int targetNumber, List<Card> cards) {
        GameSession session = new GameSession(username, gameMode, targetNumber, cards);
        sessions.add(session);
        return session;
    }

    /**
     * Ends a game session.
     * @param session The game session to end.
     * @param won Indicates if the game was won.
     * @param score The score achieved in the game.
     */
    public void endSession(GameSession session, boolean won, int score) {
        session.endSession(won, score);
    }
}