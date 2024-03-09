package org.assignment.die.stratergy;

import org.assignment.die.Die;

import java.util.List;

public class DieSummationStrategy implements DieStrategy{

    private final List<Die> dies;

    public DieSummationStrategy(List<Die> dies){
        if (dies == null) {
            throw new IllegalArgumentException("Dice list cannot be null");
        }
        this.dies = dies;
    }

    @Override
    public int roll() {
        int moveValue = 0;
        for(Die die : dies){
            moveValue += die.roll();
        }

        return moveValue;
    }

    @Override
    public int getMinValue() {
        int min = 0;
        for(Die die: dies){
            min += die.getMinValue();
        }

        return min;
    }

    @Override
    public int getMaxValue() {
        int max = 0;
        for(Die die: dies){
            max += die.getMaxValue();
        }

        return max;
    }
}
