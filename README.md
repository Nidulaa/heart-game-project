# Heart Game Project

A moderately complex Java game using the Heart API that demonstrates four key Computer Science themes for academic assessment.

## 🎮 About The Game

This is a card-based mathematical puzzle game where players solve equations using playing cards fetched from the Heart API. The game features multiple modes, user profiles, achievements, and persistent statistics.

## 📚 Four Themes Demonstration

### 1. Software Design Principles (Low Coupling & High Cohesion)

**High Cohesion:**
- Each class has a single, well-defined responsibility
- `User` class: Only manages user data
- `HeartAPIService`: Only handles API communication
- `UserService`: Only manages user operations
- `GameEngine`: Only contains game logic

**Low Coupling:**
- Layers communicate through interfaces
- Model layer is independent of UI
- Services don't depend on each other directly
- Easy to modify one component without affecting others

**Package Structure:**
```
com.heartgame/
├── model/          # Data models (User, Card, Achievement, GameSession)
├── service/        # Business logic (HeartAPIService, UserService, GameEngine)
├── controller/     # Game flow control
├── ui/             # User interface components
└── util/           # Utility classes (FileManager, EventManager)
```

### 2. Event-Driven Programming

**Event System:**
- Custom event system using Observer pattern
- `GameEventListener` interface for event handling
- Events: CardSelected, GameWon, GameLost, AchievementUnlocked, TimerTick

**Implementation:**
- Card buttons trigger selection events
- Timer generates tick events for timed mode
- Score changes trigger update events
- Achievement system listens for game events

### 3. Interoperability

**Multiple Integration Points:**
- **HTTP Communication**: Fetches card data from Heart API (random.org)
- **JSON Parsing**: Uses Gson to parse API responses
- **File I/O**: Saves/loads user profiles in JSON format
- **Cross-platform**: File operations work on Windows, Mac, Linux

**Data Flow:**
```
Heart API → JSON Response → Java Objects → User Data → JSON File Storage
```

### 4. Virtual Identity

**User Profile System:**
- User registration and login
- Persistent user profiles with:
  - Username (unique identifier)
  - Total games played
  - Games won/lost
  - Best time and best score
  - Achievement collection
  - Registration date

**Features:**
- Data persists across sessions
- Achievement tracking and unlocking
- Personal statistics and history
- Multiple users supported

## 🎯 Game Modes

1. **Classic Mode**: Solve the puzzle at your own pace
2. **Timed Mode**: Race against the clock (60 seconds)
3. **Streak Mode**: See how many puzzles you can solve in a row

## 🏆 Achievements

- **First Win**: Complete your first game
- **Speed Demon**: Win in under 30 seconds
- **Perfect Score**: Get maximum score
- **Dedicated**: Play 10 games
- **Master**: Win 5 games in a row

## 🚀 How to Run

### Prerequisites
- Java 11 or higher
- Maven 3.6+

### Build and Run
```bash
# Clone the repository
git clone https://github.com/Nidulaa/heart-game-project.git
cd heart-game-project

# Build with Maven
mvn clean install

# Run the application
mvn exec:java -Dexec.mainClass="com.heartgame.HeartGameApplication"

# Or run the JAR
java -jar target/heart-game-1.0.0.jar
```

### Using Eclipse
1. Import as "Existing Maven Project"
2. Right-click on project → Run As → Java Application
3. Select `HeartGameApplication` as the main class

## 📖 How to Play

1. **Login/Register**: Create a new account or login with existing credentials
2. **Select Mode**: Choose from Classic, Timed, or Streak mode
3. **Solve Puzzle**: The game shows cards and a target number. Click cards to select them and solve the equation
4. **Submit**: Click "Submit Answer" to check your solution
5. **Track Progress**: View your statistics and achievements in your profile

## 🎓 For Video Discussion

### Key Points to Cover:

**Software Design:**
- Show the package structure and explain separation of concerns
- Demonstrate how changing UI doesn't affect business logic
- Explain how each class has one responsibility

**Event-Driven:**
- Show the event flow when a card is clicked
- Demonstrate timer events in timed mode
- Show achievement unlock events

**Interoperability:**
- Show API call to Heart API and JSON response
- Demonstrate user data being saved to file
- Show how data persists between sessions

**Virtual Identity:**
- Login as different users to show separate profiles
- Show achievement collection and statistics
- Demonstrate data persistence

## 📁 Project Structure

```
heart-game-project/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── heartgame/
│                   ├── HeartGameApplication.java
│                   ├── model/
│                   │   ├── User.java
│                   │   ├── Card.java
│                   │   ├── Achievement.java
│                   │   └── GameSession.java
│                   ├── service/
│                   │   ├── HeartAPIService.java
│                   │   ├── UserService.java
│                   │   └── GameEngine.java
│                   ├── controller/
│                   │   └── GameController.java
│                   ├── ui/
│                   │   ├── LoginFrame.java
│                   │   ├── MainMenuFrame.java
│                   │   ├── GameFrame.java
│                   │   └── ProfileFrame.java
│                   └── util/
│                       ├── FileManager.java
│                       ├── EventManager.java
│                       └── GameEvent.java
├── pom.xml
├── .gitignore
└── README.md
```

## 🔧 Technologies Used

- **Java 11**: Main programming language
- **Swing**: GUI framework
- **Maven**: Build and dependency management
- **Gson**: JSON parsing library
- **Heart API**: Card generation service

## 📝 License

This is an academic project for coursework submission.

## 👤 Author

Nidulaa - University of Bedfordshire Student

---

**Note**: This project is designed to demonstrate the four key themes required for the Comparative Integrated Systems assignment. Each theme is clearly implemented and can be easily explained in the accompanying video presentation.