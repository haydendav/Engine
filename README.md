# Chess Engine

This is a simple command line chess engine game implemented in Java. The project allows users to play chess against an AI or another player in a console environment.

## Project Structure

- `src/main/java/com/chess/App.java`: Entry point of the chess engine. Handles user input and game flow.
- `src/main/java/com/chess/board/Board.java`: Represents the chessboard, initializes the board, displays the current state, and validates moves.
- `src/main/java/com/chess/pieces`: Contains classes for each chess piece (King, Queen, Rook, Bishop, Knight, Pawn) that define their movement logic.
- `src/main/java/com/chess/utils/Utils.java`: Utility methods for input validation and move formatting.
- `src/test/java/com/chess/AppTest.java`: Unit tests for the App class to ensure game logic functions correctly.

## Setup Instructions

1. Clone the repository:
   ```
   git clone <repository-url>
   ```

2. Navigate to the project directory:
   ```
   cd chess-engine
   ```

3. Build the project using Gradle:
   ```
   ./gradlew build
   ```

4. Run the application:
   ```
   java -cp build/libs/chess-engine.jar com.chess.App
   ```

## Game Rules

The game follows standard chess rules. Players take turns to move their pieces, with the objective of checkmating the opponent's king. Each type of piece has its own movement rules.

## Contributing

Feel free to submit issues or pull requests for improvements or bug fixes.