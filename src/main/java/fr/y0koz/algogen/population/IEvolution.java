package fr.y0koz.algogen.population;

import fr.y0koz.algogen.population.Population;

public interface IEvolution<T> {

    public Population<T> reproduire(Population<T> pop, double ratio);

}
