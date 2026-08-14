package br.univali.pdi;

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
            throw new PdiException(ExitCode.READ_ERROR, "Nao foi possivel abrir a imagem: " + path);
        }
        return image;
    }

    public static void write(String path, Mat image) {
        OpenCvRuntime.ensureLoaded();
        if (image == null || image.empty()) {
            throw new PdiException(ExitCode.WRITE_ERROR, "A imagem de saida esta vazia.");
        }

        ResultIO.ensureParentDirectory(Path.of(path));

        if (!Imgcodecs.imwrite(path, image)) {
            throw new PdiException(ExitCode.WRITE_ERROR, "Nao foi possivel salvar a imagem: " + path);
        }
    }
}
