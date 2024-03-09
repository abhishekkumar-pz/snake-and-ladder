package org.assignment.board.component;

import java.util.Map;

public class BoardComponents {

    Map<Integer, Integer> snakes;

    Map<Integer, Integer> ladders;

    Map<Integer, Integer> crocodiles;

    Map<Integer, Integer> mines;

    public BoardComponents(Map<Integer, Integer> snakes, Map<Integer, Integer> ladders, Map<Integer, Integer> crocodiles, Map<Integer, Integer> mines) {
        this.snakes = snakes;
        this.ladders = ladders;
        this.crocodiles = crocodiles;
        this.mines = mines;
    }

    public Map<Integer, Integer> getSnakes() {
        return snakes;
    }

    public Map<Integer, Integer> getLadders() {
        return ladders;
    }

    public Map<Integer, Integer> getCrocodiles() {
        return crocodiles;
    }

    public Map<Integer, Integer> getMines() {
        return mines;
    }
}
