from __future__ import annotations

import sys

from . import exit_code
from .cli import CliOptions


# A lista é equivalente às variantes C++ e Java. Atividades complementares
# dos roteiros não são transformadas em requisitos do contrato mínimo.
KNOWN_OPERATIONS = {
    # M1.1 — representação, canais e níveis de cinza
    "inspect",
    "copy",
    "channel_b",
    "channel_g",
    "channel_r",
    "grayscale_average",
    "grayscale_weighted",
    "quantize",

    # M1.2 — transformações de intensidade
    "brightness",
    "contrast",
    "negative",
    "threshold",
    "histogram",

    # M1.3 — convolução e filtragem espacial
    "convolution",
    "mean_filter",
    "weighted_mean",
    "laplacian",
    "sobel",
}


def is_known_operation(operation: str) -> bool:
    """Retorna True somente para as operações previstas no contrato comum."""

    return operation in KNOWN_OPERATIONS


def run_operation(options: CliOptions) -> int:
    """Ponto de entrada deliberadamente incompleto para os algoritmos avaliados."""

    # A infraestrutura anterior a esta função já cuidou da CLI, presença dos
    # arquivos e validação dos parâmetros gerais. A partir daqui começa a parte
    # avaliada do laboratório.
    #
    # TODO(aluno) M1.1: implementar inspect, copy, channel_b, channel_g,
    # channel_r, grayscale_average, grayscale_weighted e quantize.
    #
    # TODO(aluno) M1.2: implementar brightness, contrast, negative, threshold
    # e histogram. result_io.py apenas serializa o vetor de 256 contadores;
    # a contagem dos pixels deve ser implementada pelo estudante.
    #
    # TODO(aluno) M1.3: implementar convolution, mean_filter, weighted_mean,
    # laplacian e sobel. kernel.py apenas lê e valida o kernel; aplicar a
    # convolução continua sendo responsabilidade do estudante.
    #
    # Organize os algoritmos em funções ou módulos auxiliares e deixe esta
    # função responsável pelo despacho. Não coloque os algoritmos em
    # __main__.py e não substitua os percursos manuais exigidos no roteiro por
    # chamadas prontas equivalentes do OpenCV.

    print(
        f"Operacao '{options.operation}' reconhecida, "
        "mas ainda nao implementada no projeto-base.",
        file=sys.stderr,
    )
    return exit_code.GENERAL_ERROR
