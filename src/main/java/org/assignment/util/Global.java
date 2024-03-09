package org.assignment.util;

public class Global {
    private final int boardSize;
    private final int playerCount;
    private final String playerSelectionStrategy;
    private final int snakeCount;
    private final int ladderCount;
    private final int dieCount;
    private final String dieStrategy;
    private final int dieMinValue;
    private final int dieMaxValue;

    public Global(int playerCount, String playerSelectionStrategy, int boardSize,
                  int snakeCount, int ladderCount, int dieCount, String dieStrategy,
                  int dieMinValue, int dieMaxValue) {
        this.playerCount= playerCount;
        this.boardSize = boardSize;
        this.dieCount = dieCount;
        this.dieStrategy = dieStrategy;
        this.dieMinValue = dieMinValue;
        this.dieMaxValue = dieMaxValue;
        this.playerSelectionStrategy= playerSelectionStrategy;
        this.ladderCount = ladderCount;
        this.snakeCount = snakeCount;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public int getDieCount() {
        return dieCount;
    }

    public String getDieStrategy() {
        return dieStrategy;
    }

    public int getDieMinValue() {
        return dieMinValue;
    }

    public int getDieMaxValue() {
        return dieMaxValue;
    }

    public String getPlayerSelectionStrategy() {
        return playerSelectionStrategy;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public int getSnakeCount() {
        return snakeCount;
    }

    public int getLadderCount() {
        return ladderCount;
    }

    @Override
    public String toString() {
        return "Global{" +
                "boardSize=" + boardSize +
                ", playerCount=" + playerCount +
                ", playerSelectionStrategy='" + playerSelectionStrategy + '\'' +
                ", snakeCount=" + snakeCount +
                ", ladderCount=" + ladderCount +
                ", dieCount=" + dieCount +
                ", dieStrategy='" + dieStrategy + '\'' +
                ", dieMinValue=" + dieMinValue +
                ", dieMaxValue=" + dieMaxValue +
                '}';
    }
}
