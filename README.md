# Sagrada Board Game Implementation

## Implemented Features

The following features have been implemented:
- **Socket Connection**: Communication between client and server via sockets.
- **Complete Game Rules**: Full implementation of Sagrada rules, including all Tool Cards and Public Objective Cards.
- **Full GUI Client**: A graphical user interface with sound effects.
- **JSON Loading**: Configuration and game data (Tool Cards, Public Objective Cards, Game Boards, Client/Server settings) are loaded from JSON files.
- **Client Settings Persistence**: Ability to save client settings to a file.
- **Vector Graphics**: Use of vector graphics for rendering the game board, Tool Cards, and Public Objective Cards.

## Design Choices

- **GUI Tool Cards**: To make the use of certain Tool Cards more intuitive in the GUI, some specific inputs (like row and column) are entered via dedicated text fields.
- **Communication Protocol**: Communication between Client and Server uses string-based messages. This allows sending objects as JSON along with additional metadata. Three message formats were established: `Action Message`, `JSON Message`, and `Control Message`.
- **Audio**: Background music and sound effects were added to notify players of game changes (e.g., a new round starting).
- **GUI Stages**: Game phases from the official manual were divided into different "Stages" for the GUI implementation.
- **UML Diagrams**: In the Client-side UML, classes strictly related to the GUI do not detail all methods and attributes to maintain clarity and readability.
- **Logging**: Console prints were kept in the code to notify all steps performed by the Client and Server, both for debugging and user information.

## Limitations

- **GUI Updates**: Tool Cards that move already placed dice may not update the game graphics instantly, although the move is correctly registered on both the Client and Server.
- **Reconnection**: If a player loses connection and reconnects, their view might not update immediately, though invalid moves are still prevented.
- **Javadoc**: The Javadoc was uploaded separately and can be found at: [Mega link (Javadoc)](https://mega.nz/#!A1kSxKzS!LlGtp0Z2AoFKuPulxRiH-N9gqQPoVTccvHQHPHIYCv8)

## Build and Run Instructions

### Prerequisites
- Java 8 (JDK 1.8)
- Maven

### Build
To build the project and generate the JAR file, run the following command in the project root:
```bash
mvn clean package
```
The generated JAR file will be located in the `target` directory.

### Running the Server
To start the server, you can use the following command:
```bash
java -cp target/VideogiocoSagrada-1.0-SNAPSHOT.jar Progetto_Ing_Sw.com.server.Controller.LaunchServer [port]
```
If no port is specified, it defaults to the one in `src/main/java/Progetto_Ing_Sw/com/server/Settings/ServerSettings.json` (or 1024 if the file is missing).

### Running the Client
To start the client (GUI), use:
```bash
java -cp target/VideogiocoSagrada-1.0-SNAPSHOT.jar Progetto_Ing_Sw.com.client.Sagrada
```

---
*Note: Due to size constraints (the full JAR is ~70MB and GitHub's limit is 25MB), the pre-built JAR can be found here: [Mega link (JAR)](https://mega.nz/#!o8UhHCyJ!NzeC60KcKJv9HoV_OwPrqHOfEhEw6b7X9DWU_T5wMv4)*