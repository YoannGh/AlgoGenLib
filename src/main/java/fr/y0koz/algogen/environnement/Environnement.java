package fr.y0koz.algogen.environnement;

import fr.y0koz.algogen.individu.IIndividu;

public interface Environnement<T> {

    /**
     * Evalue la fitness d'un individu dans l'environnement
     *
     * @param individu
     *            L'individu dont la fitness est à évaluer
     * @return La fitness de l'individu dans l'environnment
     */
    double eval(IIndividu<T> individu);

}
