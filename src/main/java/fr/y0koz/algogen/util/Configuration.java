package fr.y0koz.algogen.util;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Configuration implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private static Configuration instance;
    private final Map<String, String> params;

    private Configuration() {
        params = new HashMap<>();
    }

    public static Configuration getInstance() {
        if(instance == null)
            instance = new Configuration();
        return instance;
    }

    public String getParameterValue(String param) {
        return params.get(param);
    }

    public void setParameterValue(String param, String value) {
        params.put(param, value);
    }

    public static Configuration createDefaultManualConfig() {
        Configuration cfg = new Configuration();
        cfg.setParameterValue(AlgoGenParameter.TAILLE_POP, "10");
        cfg.setParameterValue(AlgoGenParameter.NB_GENERATION, "10");
        cfg.setParameterValue(AlgoGenParameter.SEED, "240111995");
        cfg.setParameterValue(AlgoGenParameter.EVO_GENERATIONNELLE, "true");
        cfg.setParameterValue(AlgoGenParameter.SELECTION_UNIFORME, "true");
        return cfg;
    }

    /**
     * Import d'une configuation sauvée par la sérialisation.
     *
     * @param fileName
     *            : nom du fichier sauvé par la sérialisation
     * @throws IOException problème de lecture du fichier ou de son contenu
     */
    public static Configuration chargerConfiguration(String fileName) throws IOException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
        try {
            instance = (Configuration) ois.readObject();
            return instance;
        } catch (ClassCastException e) {
            throw new IOException(
                    "File doesnt contain a valid configuration.", e);
        } catch (ClassNotFoundException e) {
            throw new IOException(
                    "JVM does not know the type Configuration.", e);
        } finally {
            ois.close();
        }
    }

    /**
     * Export de la configuration par la sérialisation
     * @param le nom du fichier à charger (en convention {@link File})
     * @throws IOException En cas de problème d'écriture dans ce fichier.
     */
    public static void sauverConfiguration(String fileName, Configuration config)
            throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
        oos.writeObject(config);
        oos.close();
    }

}
