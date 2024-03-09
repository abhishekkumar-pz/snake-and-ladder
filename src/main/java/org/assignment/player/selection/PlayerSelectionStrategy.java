package org.assignment.player.selection;

import org.assignment.player.Player;

public interface PlayerSelectionStrategy {

    Player getCurrentPlayer();

    void nextPlayer();
}
