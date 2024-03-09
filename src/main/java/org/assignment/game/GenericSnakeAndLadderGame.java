package org.assignment.game;

import org.assignment.board.Board;
import org.assignment.board.TurnResult;
import org.assignment.board.TurnResultType;
import org.assignment.board.component.BoardComponents;
import org.assignment.board.impl.GenericBoard;
import org.assignment.die.manager.GenericDiceManager;
import org.assignment.die.stratergy.factory.DieStrategyFactory;
import org.assignment.player.Player;
import org.assignment.player.selection.PlayerSelectionStrategy;
import org.assignment.player.selection.RoundRobinSelectionStrategy;
import org.assignment.util.Global;
import org.assignment.die.Die;

import javax.naming.ConfigurationException;
import java.util.*;

public class GenericSnakeAndLadderGame extends Game {

    private static final String SPACE = "    ";
    private Board board;

    private Map<Integer, Integer> snakes;

    private Map<Integer, Integer> ladders;

    private Map<Integer, Integer> crocodiles;

    private Map<Integer, Integer> mines;

    private Map<Player, Integer> players;

    private PlayerSelectionStrategy playerSelectionStrategy;

    private GenericDiceManager dieManager;

    public void init(Global global, Scanner scanner) throws ConfigurationException {
        System.out.println("Welcome to Generic SNAKE AND LADDER.");

        initSnakeLadderAndPlayer(scanner);
        initDieManager(global);
        initPlayerStrategy(global);

        boolean replay = false;

        do{
            initBoard(global);
            start(scanner);
            System.out.println("Please write 'Y' to replay the game with existing input config.");
            String response = scanner.nextLine();
            replay = response.equalsIgnoreCase("Y");
        }while (replay);
    }

    public void start(Scanner scanner){
        displayStartGameMessage();
        boolean ended = false;
        boolean manualDieValue = false;
        Player currentPlayer;
        System.out.println(scanner.nextLine());
        while(!ended){
            currentPlayer = playerSelectionStrategy.getCurrentPlayer();
            displayPlayerTurnMessage(currentPlayer);

            // Comment this piece of code and uncomment the commented code to run game automatically
//            System.out.println("Want to add dice value manually please type 'Y'.");
//            String response = scanner.nextLine();
//            manualDieValue = response.equalsIgnoreCase("Y");
//            int dieValue = -1;
//            if(manualDieValue){
//                while(manualDieValue){
//                    System.out.println("Enter dice value");
//                    dieValue = scanner.nextInt();
//                    if(dieManager.validManualInput(dieValue)){
//                        manualDieValue = false;
//                    }else{
//                        System.out.println("Die value is not valid. Please put correct value. Value can [ " + dieManager.getMinValue() + " : " + dieManager.getMaxValue() + " ]");
//                    }
//                }
//            }else{
//                dieValue = dieManager.rollDice();
//
//                System.out.println(SPACE + "Rolled the dice and got " + dieValue);
//            }

            // ----------------------------
            int dieValue = dieManager.rollDice();

            // ---------------------------------

            System.out.println(SPACE + "Rolled the dice and got " + dieValue);

            TurnResult result = board.move(currentPlayer, dieValue);
//            board.displayBoard();
            if(result.getResult().equals(TurnResultType.WIN)){
                displayWinnerMessage(currentPlayer);
                ended = true;
            }else{
                playerSelectionStrategy.nextPlayer();
            }
        }
    }

    public void initSnakeLadderAndPlayer(Scanner scanner){
        displayInputFormatMessage();
        int totalSnakes = scanner.nextInt();
        Map<Integer, Integer> snakes = new HashMap<>();
        for (int i = 0; i < totalSnakes; i++) {
            int head = scanner.nextInt();
            int tail = scanner.nextInt();
            snakes.put(head, tail);
        }

        // Read the number of ladders
        int totalLadders = scanner.nextInt();
        Map<Integer, Integer> ladders = new HashMap<>();
        for (int i = 0; i < totalLadders; i++) {
            int bottom = scanner.nextInt();
            int top = scanner.nextInt();
            ladders.put(bottom, top);
        }

        // Read the number of crocodile
        int totalCrocodile = scanner.nextInt();
        Map<Integer, Integer> crocodiles = new HashMap<>();
        for (int i = 0; i < totalCrocodile; i++) {
            int head = scanner.nextInt();
            crocodiles.put(head, 0);
        }
        this.crocodiles = crocodiles;
//        this.crocodiles = Collections.emptyMap();

        // Read the number of mines
        int totalMines = scanner.nextInt();
        Map<Integer, Integer> mines = new HashMap<>();
        for (int i = 0; i < totalMines; i++) {
            int head = scanner.nextInt();
            int penalty = scanner.nextInt();
            mines.put(head, penalty);
        }
        this.mines = mines;
//        this.mines = Collections.emptyMap();

        // Read the number of players and their starting locations
        int totalPlayers = scanner.nextInt();
        Map<Player, Integer> players = new HashMap<>();
        for (int i = 0; i < totalPlayers; i++) {
            String name = scanner.next();
            int position = scanner.nextInt();
            players.put(new Player(name), position);
        }
        this.snakes = snakes;
        this.ladders = ladders;
        this.players = players;
    }

    public void initDieManager(Global global){
        int minDieValue = global.getDieMinValue();
        int maxDieValue = global.getDieMaxValue();
        int dieCount = global.getDieCount();
        List<Die> dies = new ArrayList<>();
        for(int i = 0; i< dieCount; i++){
            dies.add(new Die(minDieValue, maxDieValue));
        }
        this.dieManager = new GenericDiceManager(DieStrategyFactory.getStrategy(global.getDieStrategy(), dies));
    }

    public void initPlayerStrategy(Global global) throws ConfigurationException {
        if(Objects.equals(global.getPlayerSelectionStrategy(), "round-robin")){
            this.playerSelectionStrategy = new RoundRobinSelectionStrategy(this.players);
        }else{
            throw new ConfigurationException("Invalid player selection strategy: " + global.getPlayerSelectionStrategy());
        }
    }

    public void initBoard(Global global){
        int boardSize = global.getBoardSize();
        this.board = new GenericBoard(boardSize, new BoardComponents(snakes, ladders, crocodiles, mines), players);
    }

    private void displayInputFormatMessage(){
        System.out.println("Please provide Snakes, Ladder and Player Information.");
    }

    private void displayStartGameMessage(){
        System.out.println("Starting Game.");
        System.out.println("SNAKE AND LADDER");
    }

    private void displayPlayerTurnMessage(Player player){
        System.out.println("Player: " + player.getName() + " turn.");
    }

    private void displayWinnerMessage(Player player){
        System.out.println("Player:  " + player.getName() + " won the game.!!!!!!");
    }
}
