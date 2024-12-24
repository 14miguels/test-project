package Controller;

import Model.Cell_Manager.Cell;
import Model.Pawn;
import Model.Player.Player;
import Model.Cell_Manager.Property;

import java.io.IOException;

public class PlayerController {

    public static void rollDice(Player player, Pawn pawn) throws IOException {
        if (player.jailturns > 2) {
            player.setState(false);
        }
        if (player.getState()){
            DiceController.rollDices(true);
            if (DiceController.doubles) {
                player.setConsecutive_doubles(player.getConsecutive_doubles()+1);
                if (player.getConsecutive_doubles() == 3) {
                    System.out.println("3 consecutive doubles");
                    player.setState(false);
                }
            } else {
                player.setConsecutive_doubles(0);
            }
        } else {
            int steps = DiceController.rollDices(false);
            pawn.move(steps);
        }
    }

    public static void withdraw(Player player, int price) {
        player.account_balance -= price;
    }

    public static void deposit(Player player, int price) {
        player.account_balance += price;
    }

    public static void buyProperty(Player player, Property property) {
        if (player.account_balance >= property.price) {
            player.withdraw(property.price);
        } else{
            throw new IllegalStateException("You don't have enough money");
        }

        player.getProperties().add(property);
    }

    public static void buyHouse(Player player, Property property) throws IOException {
        if (player.account_balance >= property.getHousePrice()) {
            player.withdraw(property.getHousePrice());
        } else{
            throw new IllegalStateException("You don't have enough money");
        }

        property.acquireHouse();
    }
}