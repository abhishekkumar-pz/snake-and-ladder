package org.assignment.player.selection;

import org.assignment.player.Player;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class RoundRobinSelectionStrategy implements PlayerSelectionStrategy{

    private final Queue<Player> playerQueue;
    public RoundRobinSelectionStrategy(Map<Player, Integer> players){
        playerQueue = new LinkedList<>();
        for(Map.Entry<Player, Integer> playerEntry: players.entrySet()){
            playerQueue.add(playerEntry.getKey());
        }
    }


    @Override
    public Player getCurrentPlayer() {
        return playerQueue.peek();
    }

    @Override
    public void nextPlayer() {
        Player currentPlayer = playerQueue.poll();
        playerQueue.add(currentPlayer);
    }
}
