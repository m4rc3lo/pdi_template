package br.univali.pdi;

import java.io.IOException;
import java.nio.file.Path;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

/** Infraestrutura comum de leitura e escrita de imagens. */
public final class ImageIO {
    private ImageIO() {}

    public static Mat read(String path) {
        OpenCvRuntime.ensureLoaded();
        Mat image = Imgcodecs.imread(path, Imgcodecs.IMREAD_UNCHANGED);
        if (image.empty()) {
            throw new IllegalArgumentException("Nao foi possivel abrir a imagem: " + path);
        }
        return image;
    }

    public static void write(String path, Mat image) {
        OpenCvRuntime.ensureLoaded();
        if (image == null || image.empty()) {
            throw new IllegalArgumentException("A imagem de saida esta vazia.");
        }

        try {
            ResultIO.ensureParentDirectory(Path.of(path));
        } catch (IOException error) {
            throw new IllegalStateException("Nao foi possivel criar o diretorio de saida: " + path, error);
        }

        if (!Imgcodecs.imwrite(path, image)) {
            throw new IllegalStateException("Nao foi possivel salvar a imagem: " + path);
        }
    }
}
