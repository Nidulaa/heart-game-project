"""
Heart Game - Main Entry Point

This file serves as the entry point for the Heart Game.
Demonstrates integration of all four themes.
"""

import sys
from game.game_engine import GameEngine
from game.player_profile import PlayerProfile
from game.ui_controller import UIController
from game.event_system import EventSystem
from game.api_handler import APIHandler
import json


def load_config():
    """Load configuration file - demonstrates INTEROPERABILITY"""
    try:
        with open('config.json', 'r') as f:
            return json.load(f)
    except FileNotFoundError:
        print("Config file not found, using defaults")
        return {
            "api_url": "https://www.random.org/integers/",
            "default_lives": 3,
            "points_per_correct": 10
        }


def main():
    """Main game loop"""
    print("=" * 50)
    print("Welcome to Heart Game!")
    print("=" * 50)
    
    # Load configuration - INTEROPERABILITY
    config = load_config()
    
    # Get or create player - VIRTUAL IDENTITY
    username = input("\nEnter your username: ").strip()
    if not username:
        username = "Player1"
    
    player = PlayerProfile.load_or_create(username)
    print(f"\nWelcome back, {player.username}!")
    print(f"Games Played: {player.stats['games_played']}")
    print(f"High Score: {player.stats['high_score']}")
    
    # Initialize components - LOW COUPLING
    event_system = EventSystem()
    api_handler = APIHandler(config['api_url'])
    ui_controller = UIController(event_system)
    
    # Create game engine - HIGH COHESION (single responsibility)
    game = GameEngine(
        event_system=event_system,
        api_handler=api_handler,
        player=player,
        config=config
    )
    
    # Start game
    game.start()
    
    # Save player progress - VIRTUAL IDENTITY
    player.save_profile()
    print(f"\nProgress saved for {player.username}")
    print("Thanks for playing!")


if __name__ == "__main__":
    try:
        main()
    except KeyboardInterrupt:
        print("\n\nGame interrupted. Goodbye!")
        sys.exit(0)