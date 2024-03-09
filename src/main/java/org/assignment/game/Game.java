package org.assignment.game;

import org.assignment.util.Global;

import javax.naming.ConfigurationException;
import java.util.Scanner;

public abstract class Game {

    public abstract void init(Global global, Scanner scanner) throws ConfigurationException;

    public abstract void start(Scanner scanner);
}
