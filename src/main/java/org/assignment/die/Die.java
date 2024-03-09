package org.assignment.die;

import java.util.Random;

public class Die extends ValueGenerator{
    private final int minValue;
    private final int maxValue;
    private final Random random;

    public Die(int minValue, int maxValue) {
        if (minValue >= maxValue) {
            throw new IllegalArgumentException("Minimum value must be less than maximum value");
        }
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.random = new Random();
    }

    public int getMinValue() {
        return minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    @Override
    public int roll() {
        return random.nextInt(maxValue - minValue + 1) + minValue;
    }
}
