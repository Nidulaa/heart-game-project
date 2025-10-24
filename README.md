# Heart Game Project - Computer Science Assignment

## Overview
This Heart Game implementation demonstrates four key Computer Science concepts:
1. Software Design Principles (Low Coupling & High Cohesion)
2. Event-Driven Programming
3. Interoperability
4. Virtual Identity

## Game Description
A mathematical puzzle game that uses the Heart Game API to generate random numbers. Players solve addition problems, earn points, and progress through difficulty levels.

## Four Themes Demonstration

### 1. Software Design Principles: Low Coupling & High Cohesion

**High Cohesion:**
- `game_engine.py`: Focuses solely on game logic and rules
- `api_handler.py`: Only handles API communication
- `player_profile.py`: Manages player data exclusively
- `event_system.py`: Dedicated to event management

**Low Coupling:**
- Modules communicate through well-defined interfaces
- `game_engine.py` doesn't know about API implementation details
- `api_handler.py` is replaceable without affecting game logic
- Event system decouples components (observers don't know about publishers)

### 2. Event-Driven Programming

**Implementation:**
- Observer pattern in `event_system.py`
- Events: `game_start`, `correct_answer`, `wrong_answer`, `level_up`, `game_over`
- Multiple listeners can subscribe to events
- Asynchronous event handling
- UI updates triggered by events, not direct calls

**Benefits:**
- Loose coupling between components
- Extensible architecture
- Real-time updates

### 3. Interoperability

**Demonstrated through:**
- RESTful API integration (Heart Game API)
- JSON configuration files (`config.json`)
- Standard Python interfaces
- Cross-platform compatibility
- Logging for debugging across systems
- Data serialization for player profiles

**External Systems:**
- HTTP communication with random.org API
- File system for persistent storage
- Standard input/output for user interaction

### 4. Virtual Identity

**Features:**
- Player profile creation and management
- Username and unique player ID
- Statistics tracking (games played, win rate, high score)
- Session management
- Achievement system
- Persistent player data (JSON storage)
- Player history and progress tracking

## Installation

```bash
pip install -r requirements.txt
```

## Usage

```bash
python main.py
```

## Project Structure

```
heart-game-project/
├── main.py                 # Entry point
├── config.json             # Configuration
├── requirements.txt        # Dependencies
├── game/
│   ├── __init__.py
│   ├── game_engine.py      # Game logic (High Cohesion)
│   ├── api_handler.py      # API handler (Low Coupling)
│   ├── event_system.py     # Event-driven system
│   ├── player_profile.py   # Virtual identity
│   └── ui_controller.py    # User interface
└── README.md
```

## Code Explanation for Video

### Low Coupling Example
The `APIHandler` can be swapped with a mock version for testing without changing `GameEngine`:
```python
# GameEngine uses APIHandler through interface only
numbers = self.api_handler.get_random_numbers(count=2)
```

### High Cohesion Example
Each class has a single responsibility:
```python
# PlayerProfile only manages player data
# GameEngine only manages game logic
# EventSystem only handles events
```

### Event-Driven Example
```python
# Publisher
self.event_system.publish('correct_answer', {'score': score})

# Subscriber
self.event_system.subscribe('correct_answer', self.on_correct_answer)
```

### Virtual Identity Example
```python
# Player creation and persistence
player = PlayerProfile(username="John")
player.save_profile()
player.update_statistics(won=True, score=100)
```

## Author
Created for Comparative Integrated Systems Assignment

## License
MIT License