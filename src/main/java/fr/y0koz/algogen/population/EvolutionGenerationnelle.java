package fr.y0koz.algogen.population;

import fr.y0koz.algogen.individu.IIndividu;

public class EvolutionGenerationnelle<T> implements IEvolution<T> {

    private IndividuSelecteur<T> selecteur;

    public EvolutionGenerationnelle(IndividuSelecteur<T> selecteur) {
        this.selecteur = selecteur;
    }

    @Override
    public Population<T> reproduire(Population<T> pop, double ratio) {
        Population<T> newPopu = new Population<T>();
        int nbBestIndiv = Math.round((((float) pop.size() / 100) * 20));
        int i;
        for (i = 0; i < nbBestIndiv; i++) {
            newPopu.add(pop.get(i).clone());
        }
        for (; i < pop.size(); i++) {
            IIndividu<T> parent1 = selecteur.getRandom(newPopu);
            IIndividu<T> parent2 = selecteur.getRandom(newPopu);
            newPopu.add(parent1.croiser(parent2));
        }
        newPopu.muter(ratio);
        return newPopu;
    }

}
