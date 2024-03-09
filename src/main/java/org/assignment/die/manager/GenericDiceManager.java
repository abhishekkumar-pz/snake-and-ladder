package org.assignment.die.manager;

import org.assignment.die.stratergy.DieStrategy;

public class GenericDiceManager extends ValueGeneratorManager{

    private final DieStrategy dieStrategy;
    public GenericDiceManager(DieStrategy dieStrategy){
        if (dieStrategy == null) {
            throw new IllegalArgumentException("Die strategy cannot be null");
        }
        this.dieStrategy = dieStrategy;
    }
    @Override
    public int rollDice() {
        return dieStrategy.roll();
    }

    public boolean validManualInput(int manualValue){
        return (manualValue >= dieStrategy.getMinValue() && manualValue <= dieStrategy.getMaxValue());
    }

    public int getMinValue(){
        return dieStrategy.getMinValue();
    }

    public int getMaxValue(){
        return dieStrategy.getMaxValue();
    }
}
