package org.assignment.board.component.impl;

import org.assignment.board.component.RelocationComponent;

public class Snake extends RelocationComponent {

    private static final String NAME = "SNAKE";
    public Snake(int currentLocation, int toLocation) {
        super(validateCurrentLocation(currentLocation, toLocation), toLocation);
    }

    private static int validateCurrentLocation(int currentLocation, int toLocation) {
        if (toLocation >= currentLocation) {
            throw new IllegalArgumentException("The 'toLocation' must be greater than the 'currentLocation'.");
        }
        return currentLocation;
    }

    @Override
    public int getNextIndex(int currentIndex) {
        if(currentIndex == this.getCurrentLocation()){
            return this.getToLocation();
        }
        return currentIndex;
    }

    @Override
    public int getTurnPenalty() {
        return 0;
    }

    @Override
    public String action() {
        return String.format("Found SNAKE at %d. Moving to %d and penalty of turn %d", getCurrentLocation(), getToLocation(), getTurnPenalty());
    }

    @Override
    public String getName() {
        return NAME;
    }
}
