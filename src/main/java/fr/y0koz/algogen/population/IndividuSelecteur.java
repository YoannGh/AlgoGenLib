package fr.y0koz.algogen.population;

import fr.y0koz.algogen.individu.IIndividu;
import fr.y0koz.algogen.population.Population;

public interface IndividuSelecteur<T> {

    public IIndividu<T> getRandom(Population<T> pop);

}
