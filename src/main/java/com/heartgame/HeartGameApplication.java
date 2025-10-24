package com.heartgame;

import javax.swing.*;

public class HeartGameApplication {
    public static void main(String[] args) {
        // Initialize the game application
        SwingUtilities.invokeLater(() -> {
            // Start the main menu frame
            new MainMenuFrame().setVisible(true);
        });
    }
}