package Controller.CLI_Manager;

import Controller.Cell_Manager.CellManager;
import Controller.DynamicViewController;
import Controller.TurnController;
import Model.Cell_Manager.*;
import Model.Player.Player;
import View.DisplayView.Display;
import View.DisplayView.DynamicView;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CLI {
    private final CommandLogger logger = new CommandLogger();
    private final Display display;
    private final Map<String, Command> commands;
    private final Screen screen;
    private final Player player;

    private boolean diceRolled = false;
    private int consecutiveRolls = 0;
    private boolean canBuy = false;
    private boolean canBuyHouse = false;
    boolean isproperty = false;

    private Cell curcell;

    private final CellManager cellCTRL;
    private final DynamicViewController dynaCTRL;


    public CLI(Player player, Display display, Screen screen, CellManager cellCTRL, DynamicViewController dynaCTRL) {
        this.display = display;
        this.screen = screen;
        this.player = player;

        this.cellCTRL = cellCTRL;
        this.dynaCTRL = dynaCTRL;

        //Initialize all the commands
        this.commands = new HashMap<>();
        commands.put("buy -property", new BuyPropertyCommand(player, cellCTRL.getCell()));
        commands.put("roll", new RollDiceCommand(player));
        commands.put("buy -house", new BuyHouseCommand(player, cellCTRL.getCell()));
        commands.put("GoJail", new GoJailCommand(player, cellCTRL.getCell()));
    }

    void handleOnCell() throws IOException, InterruptedException {
        Cell cell = cellCTRL.getCell();
        canBuyHouse = false;

        int index = player.position_index;
        curcell = cell.getKey(index);

        dynaCTRL.updateVariables(player, curcell, cell.getCells().get(curcell));

        if (curcell instanceof Property){
            isproperty = true;
            if (cell.getCells().get(curcell) != null && cell.getCells().get(curcell) != player){
                canBuy = false;
                Player playerP = cell.getCells().get(curcell);
                PayRentCommand pay = new PayRentCommand(player, (Property) cellCTRL.getCell().getKey(index));
                pay.execute();
                logger.logCommand(pay);
                display.showMessage(" ".repeat(34), 1, 18);
                display.showMessage(pay.getDescription(), 1,18);

                ReceiveRentCommand receive = new ReceiveRentCommand((Property) cellCTRL.getCell().getKey(index), playerP);
                receive.execute();

            } else if (cell.getCells().get(curcell) == player) {
                canBuyHouse = true;

            } else if (cell.getCells().get(curcell) == null){
                System.out.println(cell.getCells().get(curcell));
                canBuy = true;
            }
        } else if (curcell instanceof GoToJail) {
            processInput("GoJail");
            isproperty = false;

        } else if (curcell instanceof Start start) {
            GetStartMoneyCommand receive = new GetStartMoneyCommand(player, start);
            receive.execute();
            logger.logCommand(receive);
            display.showMessage(receive.getDescription(), 1,18);
            isproperty = false;

        } else if (curcell instanceof ParkingSpot) {
            isproperty = true;
        }
    }

    public void run() throws IOException {
        dynaCTRL.updatePlayer(player);

        display.showMessage("Player Info:", 1, 30);
        display.showMessage("Turn: " + player.getName(), 32, 30);
        display.showMessage("Board Info:", 1, 37);


        if (player.jailturns == 3){
            player.setState(false);
            player.jailturns = 0;
        }

        while (true) {
            String command = display.getInput(screen);
            if (command == null) continue;
            try {
                switch (command.toLowerCase()) {

                    case "turn -end":
                        if (diceRolled){
                            diceRolled = false;
                            consecutiveRolls = 0;
                            display.showMessage("Turn ended", 1, 18);
                            if (player.getState()){
                                player.jailturns++;
                            }
                            return;
                        } else {
                            display.showMessage("You need to roll dice first", 1, 18);
                            break;
                        }

                    case "buy -property":
                        if (diceRolled && canBuy) {
                            processInput("buy -property");
                            dynaCTRL.updatePlayer(player);
                            dynaCTRL.updateVariables(player, null, player);
                            canBuy = false;
                            canBuyHouse = true;
                        } else if (!diceRolled && canBuy){
                            display.showMessage("You need to roll the dice before you can buy a property", 1, 18);
                        } else {
                            if (!isproperty){
                                display.showMessage("This cell is not a property", 1 , 18);
                            } else {
                                display.showMessage("This property already has a owner", 1, 18);
                            }
                        }
                        break;

                    case "roll":
                        if (!diceRolled&& !player.getState()) { processInput("roll"); diceRolled = true; handleOnCell();}
                        else if (consecutiveRolls < 3 && player.getState()) {processInput("roll"); consecutiveRolls++;}
                        else if (consecutiveRolls == 3 ) {diceRolled =true; display.showMessage("You already rolled the dice 3 times", 1, 18);}
                        else { display.showMessage("You already rolled the dice", 1, 18);}

                        break;

                    case "buy -house":
                        if (diceRolled && canBuyHouse) {
                            processInput("buy -house");
                            dynaCTRL.updatePlayer(player);
                            dynaCTRL.updateVariables(null, curcell, null);
                        } else {
                            display.showMessage("You need to roll the dice before you can buy a property", 1, 18);
                        }
                        break;

                    case "player -properties":
                        int x = 1;
                        int y = 18;
                        display.showMessage("List of properties:", x, y); // Title
                        int i = 1;
                        if (player.getProperties() != null) {
                            for (Property property : player.getProperties()) {
                                display.showMessage(
                                        " - Name: " + property.getName() +
                                                ", Rent: " + property.getRent() +
                                                ", Houses: " + property.getHouseNumber(),
                                        x, y + i
                                );
                                i++;
                            }
                        } else {
                            display.showMessage(" - No properties yet", 1, 19);
                        }
                        break;

                    case "commands":
                        display.showMessage("Commands:", 1, 17);
                        display.showMessage("roll                       rolls the dice", 1, 18);
                        display.showMessage("buy -property              buys property", 1, 20);
                        display.showMessage("buy -house                 buys house", 1, 21);
                        display.showMessage("player -log                checks player log", 1, 23);
                        display.showMessage("player -properties         lists properties", 1, 24);
                        display.showMessage("turn -end                  ends turn ", 1, 26);
                        break;

                    case "player -log":
                        logger.displayLog(display, 1, 18);
                        break;

                    default:
                        display.showMessage("Unknown command: " + command, 1, 18);
                        break;
                }
            } catch (Exception e) {
                display.showMessage(e.getMessage(), 1, 18);
            }
            screen.refresh();
        }

    }


    private void processInput(String input) throws InterruptedException, IOException {
        Command command = commands.get(input);

        if (command != null) {
            command.execute();
            logger.logCommand(command);
            display.showMessage(command.getDescription(), 1, 18);
        } else {
            display.showMessage("Invalid command. Try again.", 1, 18);
        }
    }
}
