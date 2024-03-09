package org.assignment.player;

public class PlayerState {

    private int positionOnBoard;
    private int penalty;

    public PlayerState(int positionOnBoard) {
        this.positionOnBoard = positionOnBoard;
        this.penalty = 0;
    }

    public int getPositionOnBoard() {
        return positionOnBoard;
    }

    public void setPositionOnBoard(int positionOnBoard) {
        this.positionOnBoard = positionOnBoard;
    }

    public int getPenalty() {
        return penalty;
    }

    public void setPenalty(int penalty) {
        this.penalty = penalty;
    }
}
