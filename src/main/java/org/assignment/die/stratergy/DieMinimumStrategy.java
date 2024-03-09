package org.assignment.die.stratergy;

import org.assignment.die.Die;

import java.util.List;

public class DieMinimumStrategy implements DieStrategy{

    private final List<Die> dies;

    public DieMinimumStrategy(List<Die> dies){
        if (dies == null) {
            throw new IllegalArgumentException("Dice list cannot be null");
        }
        this.dies = dies;
    }
    @Override
    public int roll() {
        int minValue = 0;
        for(Die die: dies){
            minValue = Math.max(minValue, die.roll());
        }

        return minValue;
    }

    @Override
    public int getMinValue() {
        int min = Integer.MAX_VALUE;
        for(Die die: dies){
            min = Math.min(min, die.getMinValue());
        }
        return min;
    }

    @Override
    public int getMaxValue() {
        int max= Integer.MIN_VALUE;
        for(Die die: dies){
            max = Math.max(max, die.getMaxValue());
        }
        return max;
    }
}
