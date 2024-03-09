package org.assignment.board.component.impl;

import org.assignment.board.component.TurnComponent;

public class Mine extends TurnComponent {
    public static final String NAME = "MINE";
    public Mine(int currentLocation, int turnPenalty) {
        super(currentLocation, turnPenalty);
    }

    @Override
    public int getNextIndex(int currentIndex) {
        return currentIndex;
    }

    @Override
    public String action() {
        return String.format("Found MINE at %d. Moving to %d and penalty of turn %d", getCurrentLocation(), getCurrentLocation(), this.getTurnPenalty());
    }

    @Override
    public String getName() {
        return NAME;
    }
}
