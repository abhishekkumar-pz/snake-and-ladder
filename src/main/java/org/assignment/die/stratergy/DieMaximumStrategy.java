package org.assignment.die.stratergy;

import org.assignment.die.Die;

import java.util.List;

public class DieMaximumStrategy implements DieStrategy{

    private final List<Die> dies;

    public DieMaximumStrategy(List<Die> dies){
        if (dies == null) {
            throw new IllegalArgumentException("Dice list cannot be null");
        }
        this.dies = dies;
    }
    @Override
    public int roll() {
        int maxValue = 0;
        for(Die die: dies){
            maxValue = Math.max(maxValue, die.roll());
        }

        return maxValue;
    }

    @Override
    public int getMinValue() {
        int min = Integer.MIN_VALUE;
        for(Die die: dies){
            min = Math.max(min, die.getMinValue());
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
