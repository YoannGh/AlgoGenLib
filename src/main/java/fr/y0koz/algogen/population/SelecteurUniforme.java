package fr.y0koz.algogen.population;

import fr.y0koz.algogen.individu.IIndividu;
import fr.y0koz.algogen.util.Generateur;

public class SelecteurUniforme<T> implements IndividuSelecteur<T> {

    @Override
    public IIndividu<T> getRandom(Population<T> pop) {
        return pop.get(Generateur.getInstance().nextInt(pop.size()));
    }

}
