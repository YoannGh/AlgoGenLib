package fr.y0koz.algogen.individu;

public interface IIndividu<T> extends Comparable<IIndividu<T>> {

    /**
     * Modifie l'attribut fitness de l'individu
     *
     * @param fitness
     *            la nouvelle fitness de l'individu
     */
    public abstract void setFitness(double fitness);

    /**
     * Obtient la valeur propre d'un individu
     *
     * @return L'objet caracterisant la valeur propre de l'individu
     */
    public abstract T getValeurPropre();

    /**
     * Obtient la fitness d'un individu
     *
     * @return La fitness de l'individu
     */
    public abstract double getFitness();

    /**
     * La mutation d'un individu est faite en lui assignant une nouvelle valeur
     * propre aléatoire
     */
    public abstract void muter();

    /**
     * Créé un nouvel individu à partir de deux individus existants. Le nouvel
     * individu est créé avec une valeur propre égale à la moyenne de celle de
     * ses parents.
     *
     * @param autre
     *            L'individu représentant le second parent
     * @return Un nouvel individu fils combinant les caractéristiques de ses
     *         parents
     */
    public abstract IIndividu<T> croiser(IIndividu<T> autre);

    /**
     * Clone un individu
     *
     * @return Le clone de l'individu
     */
    public abstract IIndividu<T> clone();

}
