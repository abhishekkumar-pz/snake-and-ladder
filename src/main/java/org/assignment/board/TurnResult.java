package org.assignment.board;

public class TurnResult {
    private TurnResultType result;
    private int from;
    private int to;
    private String componentResult;

    public TurnResult(TurnResultType turnResultType, int from, int to, String componentResult) {
        this.result = turnResultType;
        this.from = from;
        this.to = to;
        this.componentResult = componentResult;
    }

    public TurnResultType getResult() {
        return result;
    }

    public void setResult(TurnResultType result) {
        this.result = result;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public String getComponentType() {
        return componentResult;
    }

    public void setComponentType(String componentResult) {
        this.componentResult = componentResult;
    }
}
