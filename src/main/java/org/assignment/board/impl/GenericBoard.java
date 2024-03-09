package org.assignment.board.impl;

import org.assignment.board.Board;
import org.assignment.board.Location;
import org.assignment.board.TurnResult;
import org.assignment.board.TurnResultType;
import org.assignment.board.component.BoardComponents;
import org.assignment.board.component.Component;
import org.assignment.board.component.ComponentType;
import org.assignment.board.component.impl.Crocodile;
import org.assignment.board.component.impl.Ladder;
import org.assignment.board.component.impl.Mine;
import org.assignment.board.component.impl.Snake;
import org.assignment.player.Player;
import org.assignment.player.PlayerState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a generic board for a game.
 * The board consists of various locations with different components such as snakes, ladders, crocodiles, and mines.
 * Players move across the board based on the outcome of the dice roll and the effects of the components they encounter.
 */
public class GenericBoard extends Board {

    private static final int START_LOCATION = 1;
    private static final String SPACE = "        ";
    private final int boardSize;
    private final int maxLocation;
    private final Location[] locations;

    private final Map<Player, PlayerState> playerState;

    /**
     * Constructs a GenericBoard with the specified board size, components, and player positions.
     *
     * @param boardSize   The size of the board.
     * @param components  The components (snakes, ladders, crocodiles, mines) of the board.
     * @param players     The initial positions of the players on the board.
     */
    public GenericBoard(int boardSize, BoardComponents components, Map<Player, Integer> players){
        this.boardSize = boardSize;
        this.maxLocation = ((int)Math.pow(this.boardSize, 2));
        locations = new Location[maxLocation+1];
        for(int i=1;i<=maxLocation;i++){
            locations[i] = new Location();
        }
        playerState = new HashMap<>();
        for(Map.Entry<Player, Integer> playerEntry: players.entrySet()){
            playerState.put(playerEntry.getKey(), new PlayerState(getPlayerInitialPosition(playerEntry.getValue())));
        }

        validateAndUpdateBoardComponent(components);
    }

    /**
     * Validates the board components and updates the board accordingly.
     * Checks for valid component locations and cycles in the graph.
     *
     * @param components The components (snakes, ladders, crocodiles, mines) of the board.
     * @throws IllegalArgumentException If any component location is out of bounds or if cycles are detected.
     */
    private void validateAndUpdateBoardComponent(BoardComponents components){
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for(int i=0;i<=maxLocation;i++){
            graph.add(new ArrayList<>());
        }

        for(Map.Entry<Integer, Integer> snakeEntry: components.getSnakes().entrySet()){
            if(snakeEntry.getKey() > 1 && snakeEntry.getKey() <  maxLocation && snakeEntry.getValue() > 0 && snakeEntry.getValue() < maxLocation){
                locations[snakeEntry.getKey()].setBoardObject(new Snake(snakeEntry.getKey(), snakeEntry.getValue()));
                graph.get(snakeEntry.getKey()).add(snakeEntry.getValue());
            }else{
                throw new IllegalArgumentException("Snake location for head: " + snakeEntry.getKey() + " and tail: " + snakeEntry.getValue() + " is out of bound");
            }
        }

        for(Map.Entry<Integer, Integer> ladderEntry: components.getLadders().entrySet()){
            if(ladderEntry.getKey() > 1 && ladderEntry.getKey() <  maxLocation && ladderEntry.getValue() > 1 && ladderEntry.getValue() <= maxLocation){
                locations[ladderEntry.getKey()].setBoardObject(new Ladder(ladderEntry.getKey(), ladderEntry.getValue()));
                graph.get(ladderEntry.getKey()).add(ladderEntry.getValue());
            }else{
                throw new IllegalArgumentException("Ladder location for head: " + ladderEntry.getKey() + " and tail: " + ladderEntry.getValue() + " is out of bound");
            }
        }

        for(Map.Entry<Integer, Integer> crocodileEntry: components.getCrocodiles().entrySet()){
            if(crocodileEntry.getKey() > 1 && crocodileEntry.getKey() <  maxLocation && crocodileEntry.getValue() >= 0){
                Crocodile crocodile = new Crocodile(crocodileEntry.getKey());
                locations[crocodileEntry.getKey()].setBoardObject(crocodile);
                graph.get(crocodileEntry.getKey()).add(crocodile.getToLocation());
            }else{
                throw new IllegalArgumentException("Crocodile location for head: " + crocodileEntry.getKey() + " and tail: " + crocodileEntry.getValue() + " is out of bound");
            }
        }

        for(Map.Entry<Integer, Integer> mineEntry: components.getMines().entrySet()){
            if(mineEntry.getKey() > 1 && mineEntry.getKey() <  maxLocation && mineEntry.getValue() >= 0){
                locations[mineEntry.getKey()].setBoardObject(new Mine(mineEntry.getKey(), mineEntry.getValue()));
            }else{
                throw new IllegalArgumentException("Mine location for head: " + mineEntry.getKey() + " and penalty: " + mineEntry.getValue() + " is out of bound");
            }
        }

        // Check for cycles
        if (hasCycle(graph)) {
            throw new IllegalArgumentException("Cycles detected in the board components.");
        }
    }

    /**
     * Checks if the graph represented by the adjacency list contains cycles using DFS.
     *
     * @param graph The adjacency list representation of the graph.
     * @return True if cycles are detected, false otherwise.
     */
    private boolean hasCycle(ArrayList<ArrayList<Integer>> graph) {
        boolean[] visited = new boolean[maxLocation+1];
        boolean[] recStack = new boolean[maxLocation+1];
        for (int i = 1; i <= maxLocation; i++) {
            if (!visited[i]) {
                if (isCyclic(i, visited, recStack, graph)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Helper method to perform DFS and detect cycles in the graph.
     *
     * @param currentLocation The current location in the DFS traversal.
     * @param visited         Array to mark visited locations.
     * @param recStack        Array to track locations in the recursion stack.
     * @param graph           The adjacency list representation of the graph.
     * @return True if a cycle is detected, false otherwise.
     */
    private boolean isCyclic(int currentLocation, boolean[] visited, boolean[] recStack, ArrayList<ArrayList<Integer>> graph) {
        if (recStack[currentLocation]) {
            return true;
        }
        if (visited[currentLocation]) {
            return false;
        }

        visited[currentLocation] = true;
        recStack[currentLocation] = true;

        for (int adjacentLocationIndex : graph.get(currentLocation)) {
            if (isCyclic(adjacentLocationIndex, visited, recStack, graph)) {
                return true;
            }
        }

        recStack[currentLocation] = false;

        return false;
    }


    /**
     * Moves the specified player on the board based on the outcome of the dice roll.
     * Handles player movement, encounters with board components, and special actions.
     *
     * @param player  The player to move.
     * @param dieMove The number rolled on the dice, determining the movement distance.
     * @return The result of the player's turn, including the type of turn and any special actions.
     */
    @Override
    public TurnResult move(Player player, int dieMove){
        PlayerState currentPlayerState = playerState.get(player);
        int currentPlayerLocation = currentPlayerState.getPositionOnBoard();
        int futureLocationIndex = currentPlayerLocation + dieMove;
        int prevLocationIndex = currentPlayerLocation;

        int penalty = currentPlayerState.getPenalty();
        if(penalty > 0){
            currentPlayerState.setPenalty(currentPlayerState.getPenalty() - 1);
            System.out.println(SPACE + "Skipping turn due to penalty. Penalty left : " + currentPlayerState.getPenalty());
            return new TurnResult(TurnResultType.SKIP, currentPlayerLocation, currentPlayerLocation, null);
        }

        while (true) {
            System.out.printf(SPACE + "Moving player %s from : %d , to %d.%n", player.getName(), prevLocationIndex, futureLocationIndex);

            if (futureLocationIndex <= 0 || futureLocationIndex > maxLocation) {
                return new TurnResult(TurnResultType.SKIP, currentPlayerLocation, currentPlayerLocation, null);
            } else if (futureLocationIndex == maxLocation) {
                currentPlayerState.setPositionOnBoard(maxLocation);
                locations[maxLocation].setPlayer(player);
                return new TurnResult(TurnResultType.WIN, currentPlayerLocation, maxLocation, null);
            } else {
                if (locations[futureLocationIndex].getBoardObject() == null) {
                    if (futureLocationIndex != 1 && locations[futureLocationIndex].getPlayer() != null) {
                        Player targetPlayer = locations[futureLocationIndex].getPlayer();
                        if(!targetPlayer.equals(player)){
                            playerState.get(targetPlayer).setPositionOnBoard(START_LOCATION);
                            locations[START_LOCATION].setPlayer(targetPlayer);
                            locations[futureLocationIndex].setPlayer(null);
                            System.out.printf(SPACE + "%s is also at %d. Moving %s to 1.%n", targetPlayer.getName(), futureLocationIndex, targetPlayer.getName());
                        }
                    }
                    locations[currentPlayerState.getPositionOnBoard()].setPlayer(null);
                    currentPlayerState.setPositionOnBoard(futureLocationIndex);
                    locations[futureLocationIndex].setPlayer(player);
                    System.out.printf(SPACE + "Moved %s to %d.%n", player.getName(), futureLocationIndex);
                    return new TurnResult(TurnResultType.MOVE, currentPlayerLocation, futureLocationIndex, null);
                } else {
                    Component component = locations[futureLocationIndex].getBoardObject();
                    if(component.getType().equals(ComponentType.RELOCATION)){
                        int moveToLocation = component.getNextIndex(futureLocationIndex);
                        if (moveToLocation <= 0) {
                            currentPlayerState.setPositionOnBoard(START_LOCATION);
                            locations[START_LOCATION].setPlayer(player);
                            System.out.printf(SPACE + "%s moved outside the board so putting it on 1.%n", player.getName());
                            return new TurnResult(TurnResultType.MOVE, currentPlayerLocation, START_LOCATION, component.action());
                        } else if (moveToLocation > maxLocation) {
                            // Skip if it is going outside the board
                            System.out.printf(SPACE + "Skipping %s movement because it is going outside the board.%n", player.getName());
                            return new TurnResult(TurnResultType.SKIP, currentPlayerLocation, currentPlayerLocation, component.action());
                        } else {
                            // Update the future location index and continue the loop
                            System.out.println(SPACE + "Got " + component.getName() + " ,now moving from " + futureLocationIndex + " to " + moveToLocation);
                            prevLocationIndex = futureLocationIndex;
                            futureLocationIndex = moveToLocation;
                            continue;
                        }
                    }else{
                        System.out.println("Got " + component.getName() + ", adding penalty of " + component.getTurnPenalty() +" . You will not be able to take turn util penalty");
                        currentPlayerState.setPenalty(currentPlayerState.getPenalty() + component.getTurnPenalty());
                        return new TurnResult(TurnResultType.SKIP, currentPlayerLocation, currentPlayerLocation, component.action());
                    }
                }
            }
        }
    }

    /**
     * Displays the current state of the board, including player positions and board components.
     * This method is primarily used for debugging and visualization purposes.
     */
    public void displayBoard(){

        System.out.println("Board. Size: " + boardSize);

        for(int i =1; i < maxLocation; i++){
            System.out.print("[ " + String.format("%-3s",i) + " : " + locations[i] + " ] ,   ");

            if(((i) % boardSize) == 0){
                System.out.println();
            }
        }

    }

    private int getPlayerInitialPosition(Integer pos){
        if(pos == null || pos <= 0 || pos > maxLocation){
            return 1;
        }else{
            return pos;
        }
    }
}
