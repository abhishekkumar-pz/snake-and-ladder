package org.assignment;

import org.assignment.game.Game;
import org.assignment.game.GenericSnakeAndLadderGame;
import org.assignment.util.ConfigParser;
import org.assignment.util.Global;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        start();
    }

    public static void start() {
        Global global;
        try {
            global = ConfigParser.parseConfig();
            System.out.println("Global configuration:");
            System.out.println(global);
        } catch (Exception e) {
            System.err.println("Error: Failed to parse configuration. Exiting.");
            throw new RuntimeException(e);
        }
        try (Scanner scanner = new Scanner(System.in)) {
            boolean renewGame;
            do {
                // We can define different game types like CustomSnakeAndLadder and can define factory to get the type of game from config.
                // For simplicity, I directly used GenericSnakeAndLadder to check for provided input
                Game game = new GenericSnakeAndLadderGame();
                try {
                    game.init(global, scanner);
                } catch (Exception e) {
                    System.err.println("Error: Failed to initialize the game. Exiting.");
                    throw new RuntimeException(e);
                }

                System.out.println("Do you want to update the input configuration and replay? (Y/N)");
                String response = scanner.nextLine().trim();
                renewGame = response.equalsIgnoreCase("Y");
            } while (renewGame);
        } catch (Exception e) {
            System.err.println("Error occurred during game execution. Exiting.");
            e.printStackTrace();
        }
    }
}