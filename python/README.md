# Projeto-base Python — Laboratórios M1 — v2

Base para Python 3.10+ com `venv`, OpenCV e `pytest`. As operações avaliadas permanecem como **stubs**.

## Ambiente virtual

```bash
python -m venv .venv
```

Windows/MSYS2 UCRT64:

```bash
source .venv/Scripts/activate
```

Linux/macOS:

```bash
source .venv/bin/activate
```

## Instalação

```bash
python -m pip install --upgrade pip
python -m pip install -r requirements.txt
python -m pip install -e .
```

A base usa `opencv-python-headless`, pois os laboratórios não dependem de `cv2.imshow` ou de uma GUI. O wheel já inclui os binários do OpenCV; não é necessário instalar OpenCV separadamente para Python.

## Máquina restrita ou offline

Os wheels podem ser baixados previamente em outra máquina compatível:

```bash
python -m pip download -r requirements.txt -d packages
```

Depois, na máquina restrita:

```bash
python -m pip install --no-index --find-links packages -r requirements.txt
python -m pip install -e .
```

## Testes e execução

```bash
python -m pytest
python -m pdi_lab --version
python -m pdi_lab --help
```

Para operações de imagem, `--input` é obrigatório. Para todas, exceto `inspect`, `--output` também é obrigatório.
