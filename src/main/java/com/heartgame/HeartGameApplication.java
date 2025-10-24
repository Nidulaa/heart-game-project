package com.heartgame;

import com.heartgame.ui.LoginFrame;
import javax.swing.SwingUtilities;

/**
 * Main application entry point for Heart Game.
 * 
 * This class demonstrates the four themes:
 * 1. Software Design Principles - Clean separation of concerns
 * 2. Event-Driven Programming - Swing event dispatch thread
 * 3. Interoperability - Integrates API, JSON, and File I/O
 * 4. Virtual Identity - User authentication system
 */
public class HeartGameApplication {
    
    public static void main(String[] args) {
        // Use Swing's event dispatch thread (Event-Driven)
        SwingUtilities.invokeLater(() -> {
            // Start with login screen (Virtual Identity)
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
        });
    }
}