package fr.y0koz.algogen.individu;

public abstract class AbstractIndividu<T> implements IIndividu<T>, Cloneable {

    /** fitness mesurant la qualité d'un individu */
    private double fitness = 0;

    private T valeurPropre;

    public AbstractIndividu(T valeurPropre) {
        this.valeurPropre = valeurPropre;
    }

    @Override
    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    @Override
    public double getFitness() {
        return fitness;
    }

    protected void setValeurPropre(T vp) {
        this.valeurPropre = vp;
    }

    /*
     * (non-Javadoc)
     *
     * @see pobj.algogen.IIndividu#getValeurPropre()
     */
    public T getValeurPropre() {
        return valeurPropre;
    }

    /**
     * Les individus sont comparés suivant leur valeur de fitness
     */
    @Override
    public int compareTo(IIndividu<T> o) {
        return Double.compare(o.getFitness(), this.getFitness());
    }

    @Override
    public abstract IIndividu<T> clone();

}
