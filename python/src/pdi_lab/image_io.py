from pathlib import Path
import cv2
import numpy as np
def read_image(path:str)->np.ndarray:
    image=cv2.imread(path,cv2.IMREAD_UNCHANGED)
    if image is None: raise ValueError(f"Nao foi possivel abrir a imagem: {path}")
    return image
def write_image(path:str,image:np.ndarray)->None:
    if image is None or image.size==0: raise ValueError("A imagem de saida esta vazia.")
    if not cv2.imwrite(str(Path(path)),image): raise OSError(f"Nao foi possivel salvar a imagem: {path}")
