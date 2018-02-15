package fr.y0koz.algogen.population;

import fr.y0koz.algogen.individu.IIndividu;
import fr.y0koz.algogen.util.Generateur;

public class SelecteurParFitness<T> implements IndividuSelecteur<T> {

    @Override
    public IIndividu<T> getRandom(Population<T> pop) {
        double rand = Generateur.getInstance().nextDouble();
        double borneMin = 0;
        double sommeFitness = pop.getSommeFitnesses();

        for(IIndividu<T> ind : pop) {
            double fitnessRelative = ind.getFitness() / sommeFitness;
            if(rand >= borneMin && rand <= (borneMin + fitnessRelative)) {
                return ind;
            }
            borneMin = borneMin + fitnessRelative;
        }
        return null;
    }

}
