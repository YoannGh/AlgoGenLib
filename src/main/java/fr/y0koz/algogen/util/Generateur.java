package fr.y0koz.algogen.util;

import java.util.Random;

public class Generateur {

    private static Generateur instance;
    private final Random generateur;

    private Generateur() {
        generateur = new Random();
    }

    public static Generateur getInstance() {
        if(instance == null)
            instance = new Generateur();
        return instance;
    }

    public int nextInt() {
        return generateur.nextInt();
    }

    public int nextInt(int bound) {
        return generateur.nextInt(bound);
    }

    public double nextDouble() {
        return generateur.nextDouble();
    }

    public boolean nextBoolean() {
        return generateur.nextBoolean();
    }

    public void setSeed(long seed) {
        generateur.setSeed(seed);
    }

}
