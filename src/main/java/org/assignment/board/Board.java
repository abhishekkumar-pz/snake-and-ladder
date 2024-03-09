package org.assignment.board;

import org.assignment.player.Player;

public abstract class Board {

    public abstract TurnResult move(Player player, int dieMove);
    public abstract void displayBoard();
}
