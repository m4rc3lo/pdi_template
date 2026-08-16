package br.univali.pdi;

/** Carrega uma única vez a biblioteca nativa do OpenCV usada pelo projeto Java. */
public final class OpenCvRuntime {
    private static boolean loaded = false;

    private OpenCvRuntime() {}

    public static synchronized void ensureLoaded() {
        if (!loaded) {
            nu.pattern.OpenCV.loadLocally();
            loaded = true;
        }
    }
}
