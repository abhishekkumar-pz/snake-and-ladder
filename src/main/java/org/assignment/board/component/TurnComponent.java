package org.assignment.board.component;

public abstract class TurnComponent extends Component{

    private final ComponentType type;

    private final int currentLocation;
    private final int turnPenalty;

    public TurnComponent(int currentLocation, int turnPenalty){
        this.type = ComponentType.TURN;
        this.currentLocation = currentLocation;
        this.turnPenalty = turnPenalty;
    }

    public ComponentType getType() {
        return type;
    }

    public int getTurnPenalty() {
        return turnPenalty;
    }

    public int getCurrentLocation() {
        return currentLocation;
    }
}
