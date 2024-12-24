## LDTS - Monopoly
This project draws inspiration from the classic Monopoly board game, aiming to recreate its core mechanics in a simplified, text-based environment. The goal is to accumulate the most wealth and bankrupt other players. Players move around the board, with the purpose of buying properties to increase their rent income from other players.

This project was developed by **Ana Castanheiros** (*up202305060*@fe.up.pt), **Luís Silva** (*up*@fe.up.pt) e **Miguel Sousa** (*up*@fe.up.pt).

### IMPLEMENTED FEATURES
- **Start Menu** - The game starts with a user-friendly menu where players can choose to start a new game, view the rules, or exit. Navigation is intuitive, using arrow keys to highlight options and the Enter key to select.

- **Rolling the Dice** - Players roll the dice at the beginning of their turn to determine how many cells will their pawn move.

- **Buying Properties** - When a player lands on an unowned property, they can purchase it. If they choose to buy, the property is added to their portfolio, and the corresponding cost is deducted from their balance.

- **Paying Rent** - When landing on an opponent’s property, players must pay rent based on the property's development.

- **Property Development/Buying houses and hotels** - Players can purchase houses or hotels to develop their owned properties. The more houses or a hotel on the property, the higher the rent charged to opponents who land on these properties. A player can buy up to five houses, the fifth being a hotel.

- **Special cells** - The Monopoly board features special cells that introduce unique gameplay mechanics:

  - **Start cell** - A player that lands on this cell is rewarded with a bonus;
  
  - **Go to jail cell** - Landing here sends the player directly to jail, skipping the rest of the board;
  
  - **Jail cell** - To exit the jail cell, players must either wait three turns or have the luck to roll doubles three times in a row. During this time, two dice will be displayed for each roll instead of one;
  
  - **Parking Spot** - A neutral cell where nothing happens.
 
### Separating Game Logic, Interface, and Control 
**Problem in Context**
As the game's complexity increased, it became apparent that organizing the code in a clear and modular way was essential for maintaining scalability and readability. The responsibilities of managing game data, handling user input, and updating the user interface were becoming intertwined, leading to tightly coupled components. For instance, changes to the user interface could inadvertently affect the game logic, and adding new features often required modifying multiple parts of the codebase. Such an approach risked violating the Single Responsibility Principle (SRP), as individual components were taking on multiple roles instead of focusing on a single, well-defined task.

**The Pattern**
For us the solution was to apply the **MVC** architectural pattern. This pattern separates the responsibilities of the system into three distinct components:

  - Model: Manages the game state and logic, including the rules and mechanics of Monopoly.
  - View: Handles the display and presentation of information to the user.
  - Controller: Processes user input and communicates with the Model and View to update the game.

The MVC pattern was chosen because it enforces a clear separation of concerns, making the codebase more modular, reusable, and easier to test. It also allows changes to one component without affecting the others.

**Implementation**
The project's main source directory is organized into three distinct subdirectories, each corresponding to one of the elements of the MVC architecture.

uml

**Consequences**
The use of the MVC architecture in the current design provides the following benefits:

  - Clear Separation of Concerns: Game logic, user interface, and input handling are managed independently, reducing coupling and improving maintainability.
  - Ease of Testing: The separation of concerns allows each component to be tested independently.
  - Scalability: Adding new features is more straightforward as each component has one responsibility.

However, the pattern also introduces some complexity by requiring additional components and layers, which can increase the number of classes and interfaces to manage.
    
### HANDLING PLAYER COMMANDS IN THE CLI

**Problem in Context**

Initially, handling player input in the Monopoly game involved using scattered conditional logic to map commands to their respective actions. This approach quickly became unmanageable as new commands were introduced, violating the Open-Closed Principle since existing code had to be modified to add new functionality. Additionally, the tight coupling between the input-handling logic and game mechanics reduced code maintainability and scalability.

For example, parsing the buy -property command and executing the corresponding logic required changes across multiple files, making the system prone to errors and difficult to extend.

**The Pattern**

We applied the **Command** pattern to solve this problem. This pattern encapsulates each request as an object, decoupling the input handling from the execution of the game logic. By doing so, the pattern makes it easier to add new commands, reuse existing logic, and ensure that changes to one part of the system do not propagate unintended side effects.

The following figure shows the Command pattern’s role in the global structure of our game:

uml

This pattern is essentially applied in the following classes included in this folder:

- [CLI Manager] [here](https://github.com/FEUP-LDTS-2024/project-t03g04/tree/cd158d0e163c5a25c546b3715f24cc59f5b96949/src/main/java/Controller/CLI_Manager)

**Consequences**

The application of the Command pattern provides the following benefits:

  - Extensibility: Adding new commands requires only creating a new class without modifying existing code, adhering to the Open-Closed Principle.
  - Decoupling: The CLI logic is independent of the game mechanics, making the system more modular and easier to maintain.
  - Command history: The commands can be stored for future use.

Some liabilities include:

  -Increased Complexity: The pattern introduces more classes and abstractions, which can add complexity to the codebase.

### CREATING SPECIAL CELLS

**Problem in Context**

In the early implementation of our Monopoly game, creating different types of cells required hardcoding their initialization logic in the main game setup. This approach introduced tight coupling between the game setup logic and the instantiation of specific cell types, violating the Open-Closed Principle.

Each time a new cell type was added, we needed to modify the game setup code, which increased the complexity and the risk of introducing bugs. Moreover, duplicating logic for creating similar objects like cells with specific behaviors led to code redundancy.

**The Pattern**

To address these issues, we decided to apply **Factory Method** Pattern. This pattern provides an interface for creating objects but allows subclasses to decide which object to instantiate.

**Implementation**
The following figure illustrates the Factory Method pattern’s implementation:

uml

These classes can be found in the following folder:
- [Cell Manager] [here](https://github.com/FEUP-LDTS-2024/project-t03g04/tree/cd158d0e163c5a25c546b3715f24cc59f5b96949/src/main/java/Model/Cell_Manager)

**Consequences**

The use of the Factory Method Pattern in the current design essentially allows to follow the **SOLID principles**:

  - Extensibility - New cell types can be added by creating a new subclass of Cell and extending the factory method, without altering the existing setup code;
  - Polymorphism and Encapsulation - Each cell type encapsulates its specific behavior, allowing the game to interact with all cells through the Cell interface.
  - Reduction in Code Duplication - Shared initialization logic is centralized in the factory, reducing redundancy.
    
The drawback is the added complexity of managing a factory class and multiple subclasses, but this is outweighed by the improved maintainability and scalability.







  
