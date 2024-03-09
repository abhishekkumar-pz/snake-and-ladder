package org.assignment.die.stratergy.factory;

import org.assignment.die.Die;
import org.assignment.die.stratergy.DieMaximumStrategy;
import org.assignment.die.stratergy.DieMinimumStrategy;
import org.assignment.die.stratergy.DieStrategy;
import org.assignment.die.stratergy.DieSummationStrategy;

import java.util.List;

public class DieStrategyFactory {
    public static DieStrategy getStrategy(String strategyName, List<Die> dies) {
        return switch (strategyName) {
            case "minimum" -> new DieMinimumStrategy(dies);
            case "maximum" -> new DieMaximumStrategy(dies);
            case "summation" -> new DieSummationStrategy(dies);
            default -> new DieSummationStrategy(dies);
        };
    }
}
