from __future__ import annotations

from pathlib import Path

import cv2
import numpy as np

from .result_io import ensure_parent_directory


def read_image(path: str) -> np.ndarray:
    """Lê a imagem preservando canais e profundidade do arquivo."""

    image = cv2.imread(path, cv2.IMREAD_UNCHANGED)
    if image is None:
        raise ValueError(f"Nao foi possivel abrir a imagem: {path}")
    return image


def write_image(path: str, image: np.ndarray) -> None:
    """Salva a imagem e cria automaticamente os diretórios-pai."""

    if image is None or image.size == 0:
        raise ValueError("A imagem de saida esta vazia.")

    output = ensure_parent_directory(path)
    if not cv2.imwrite(str(output), image):
        raise OSError(f"Nao foi possivel salvar a imagem: {path}")
