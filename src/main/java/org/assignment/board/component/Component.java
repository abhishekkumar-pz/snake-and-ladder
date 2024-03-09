package org.assignment.board.component;

import org.assignment.player.PlayerState;

public abstract class Component {

    public abstract int getNextIndex(int currentIndex);

    public abstract int getTurnPenalty();

    public abstract String action();

    public abstract ComponentType getType();

    public abstract String getName();
}
