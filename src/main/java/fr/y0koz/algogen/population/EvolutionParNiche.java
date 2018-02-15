package fr.y0koz.algogen.population;

import fr.y0koz.algogen.individu.IIndividu;

public class EvolutionParNiche<T> implements IEvolution<T> {

    private IndividuSelecteur<T> selecteur;

    public EvolutionParNiche(IndividuSelecteur<T> selecteur) {
        this.selecteur = selecteur;
    }

    @Override
    public Population<T> reproduire(Population<T> pop, double ratio) {
        Population<T> newPopu = new Population<T>();
        for (IIndividu<T> ind : pop) {
            newPopu.add(ind.clone());
        }
        newPopu.removeLast();
        IIndividu<T> fils = selecteur.getRandom(newPopu).croiser(
                selecteur.getRandom(newPopu));
        newPopu.add(fils);
        newPopu.muter(ratio);
        return newPopu;
    }

}
