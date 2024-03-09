package org.assignment.board.component;

public abstract class RelocationComponent extends Component{

    private final ComponentType type;

    private final int currentLocation;

    private final int toLocation;

    public RelocationComponent(int currentLocation, int toLocation){
        this.type = ComponentType.RELOCATION;
        this.currentLocation = currentLocation;
        this.toLocation = toLocation;
    }

    public ComponentType getType() {
        return type;
    }

    public int getCurrentLocation() {
        return currentLocation;
    }

    public int getToLocation() {
        return toLocation;
    }
}
