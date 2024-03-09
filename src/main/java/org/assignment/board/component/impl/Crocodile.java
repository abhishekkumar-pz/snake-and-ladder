package org.assignment.board.component.impl;

import org.assignment.board.component.RelocationComponent;

public class Crocodile extends RelocationComponent {

    public static final String NAME = "CROCODILE";
    private static final int RELOCATION_MOVE = -5;

    public Crocodile(int currentLocation) {
        super(validateCurrentLocation(currentLocation), currentLocation + RELOCATION_MOVE);
    }

    private static int validateCurrentLocation(int currentLocation) {
        if (currentLocation + RELOCATION_MOVE <= 0) {
            throw new IllegalArgumentException("Crocodile location should be greater than " + Math.abs(RELOCATION_MOVE) + ". Current is "  + currentLocation + " .");
        }
        return currentLocation;
    }
    @Override
    public int getNextIndex(int currentIndex) {
        return getToLocation();
    }

    @Override
    public int getTurnPenalty() {
        return 0;
    }

    @Override
    public String action() {
        return String.format("Found CROCODILE at %d. Moving to %d and penalty of turn %d", getCurrentLocation(), getToLocation(), getTurnPenalty());
    }

    @Override
    public String getName() {
        return NAME;
    }
}
