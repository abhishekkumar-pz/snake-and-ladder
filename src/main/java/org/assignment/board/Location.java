package org.assignment.board;

import org.assignment.board.component.Component;
import org.assignment.board.component.ComponentType;
import org.assignment.player.Player;

public class Location {

    private Component boardObject;

    private Player player;

    public Location(){
        this.boardObject = null;
        this.player = null;
    }

    public Component getBoardObject() {
        return boardObject;
    }

    public void setBoardObject(Component boardObject) {
        this.boardObject = boardObject;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public String toString() {
        String boardObjectString = boardObject != null ? (boardObject.getType() == ComponentType.TURN ? "O : T" : "O : R") : "";
        String playerString = player != null ? " , P : " + player.getName().charAt(0) : "";

        // Concatenate boardObjectString and playerString
        String result = boardObjectString + playerString;

        // Ensure the result is exactly 10 characters long
        if (result.length() < 10) {
            result = String.format("%-10s", result);
        } else if (result.length() > 10) {
            result = result.substring(0, 10);
        }

        return result;
    }
}
