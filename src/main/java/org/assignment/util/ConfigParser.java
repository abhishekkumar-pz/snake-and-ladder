package org.assignment.util;

import org.yaml.snakeyaml.Yaml;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

public class ConfigParser {

    private static final String CONFIG_PATH = "src/main/resources/config.yml";

    public static Global parseConfig() {
        try(FileInputStream fis = new FileInputStream(CONFIG_PATH);){
            Yaml yaml = new Yaml();
            Map<String, Object> data = yaml.load(fis);

            Map<String, Object> game = (Map<String, Object>) data.get("game");

            // Parse player configuration
            Map<String, Object> player = (Map<String, Object>) game.get("player");

            int playerCount = (int) player.get("count");
            Map<String, Object> playerSelection = (Map<String, Object>) player.get("selection");
            String playerSelectionStrategy = (String) playerSelection.get("strategy");

            // Parse board configuration
            Map<String, Object> board = (Map<String, Object>) game.get("board");
            int boardSize = (int) board.get("size");

            // Parse snakes and ladders counts
            Map<String, Object> snakes = (Map<String, Object>) board.get("snakes");
            int snakeCount = (int) snakes.get("count");

            Map<String, Object> ladders = (Map<String, Object>) board.get("ladder");
            int ladderCount = (int) ladders.get("count");

            // Parse die configuration
            Map<String, Object> die = (Map<String, Object>) game.get("die");
            int dieCount = (int) die.get("count");
            String dieStrategy = (String) die.get("strategy");
            Map<String, Object> value = (Map<String, Object>) die.get("value");
            int dieMinValue = (int) value.get("min");
            int dieMaxValue = (int) value.get("max");

            // Create and return Global object
            return new Global(playerCount, playerSelectionStrategy, boardSize, snakeCount, ladderCount,
                    dieCount, dieStrategy, dieMinValue, dieMaxValue);

        } catch (IOException e) {
            return null;
        }
    }
}
