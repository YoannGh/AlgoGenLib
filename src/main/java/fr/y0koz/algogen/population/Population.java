package fr.y0koz.algogen.population;

import fr.y0koz.algogen.environnement.Environnement;
import fr.y0koz.algogen.individu.IIndividu;
import fr.y0koz.algogen.util.AlgoGenParameter;
import fr.y0koz.algogen.util.Configuration;
import fr.y0koz.algogen.util.Generateur;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Classe representant une population d'individu
 */
public class Population<T> implements Iterable<IIndividu<T>> {

    /** Liste des individus de la population */
    private List<IIndividu<T>> individus;
    /** Strategie d'evolution */
    private IEvolution<T> evolution;

    /**
     * Construit une population d'individus quelconques
     */
    public Population() {
        individus = new ArrayList<IIndividu<T>>();
    }

    /**
     * Obtient le nombre d'individus de la population
     *
     * @return le nombre d'individus de la population
     */
    public int size() {
        return individus.size();
    }

    /**
     * Ajoute un individu a la population
     *
     * @param individu
     *            l'individu a ajouter a la population
     */
    public void add(IIndividu<T> individu) {
        individus.add(individu);
    }

    @Override
    public String toString() {
        String str = "";
        for (IIndividu<T> ind : individus)
            str += (ind + "\n");
        return str;
    }

    /**
     * Fait evoluer la population dans un Environnement, la probabilit� qu'un
     * individu mute est tir�e al�atoirement entre 5% et 15%
     *
     * @param cible
     *            l'environnment dans lequel faire evoluer la population
     * @return la nouvelle generation de cette population
     */
    public Population<T> evoluer(Environnement<T> cible) {
        this.evaluer(cible);
        double prob = 0.05 + (0.15 - 0.05) * Generateur.getInstance().nextDouble();
        Population<T> nouvelleGeneration = reproduire(this, prob);

        return nouvelleGeneration;
    }

    /**
     * Calcul et met a jour les fitness de tous les individus de la population
     *
     * @param cible
     *            l'environnment dans lequel la population doit etre evaluee
     */
    public void evaluer(Environnement<T> cible) {
        for (IIndividu<T> ind : individus) {
            ind.setFitness(cible.eval(ind));
        }
        Collections.sort(individus);
    }

    /**
     * Fait muter les individus de la population avec une certaine probabilite.
     * Le meilleur individu de la population (le premier) est systematiquement
     * epargne.
     *
     * @param prob
     *            La probabilite qu'un individu mute
     */
    void muter(double prob) {
        Collections.sort(individus);

        for (int i = 1; i < individus.size(); i++) {
            if ((Generateur.getInstance().nextDouble()) < prob)
                individus.get(i).muter();
        }
    }

    /**
     * Créé une nouvelle population à partir de la population courante. 20%
     * des nouveaux individus de la nouvelle population sont des copie des 20%
     * meilleurs individus de la population actuelle. 80% sont des croisement
     * entre des individus choisis aléatoirement parmi les 20% meilleurs de la
     * population actuelle.
     *
     * @return Une nouvelle population
     */
    private Population<T> reproduire(Population<T> pop, double ratio) {
        IndividuSelecteur<T> selecteur;
        if (Configuration.getInstance().getParameterValue(AlgoGenParameter.SELECTION_UNIFORME).equals("true"))
            selecteur = new SelecteurUniforme<>();
        else
            selecteur = new SelecteurParFitness<>();

        if (Configuration.getInstance().getParameterValue(AlgoGenParameter.EVO_GENERATIONNELLE).equals("true"))
            evolution = new EvolutionGenerationnelle<>(selecteur);
        else
            evolution = new EvolutionParNiche<>(selecteur);

        return evolution.reproduire(pop, ratio);
    }

    public IIndividu<T> getMeilleurIndividu() {
        Collections.sort(individus);
        return individus.get(0);
    }

    IIndividu<T> get(int index) {
        return individus.get(index);
    }

    double getSommeFitnesses() {
        double somme = 0;
        for (IIndividu<T> ind : individus)
            somme += ind.getFitness();
        return somme;
    }

    @Override
    public Iterator<IIndividu<T>> iterator() {
        return individus.iterator();
    }

    public void removeLast() {
        individus.remove(size() - 1);
    }

}
