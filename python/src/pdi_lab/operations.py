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
    return operation in KNOWN_OPERATIONS


def run_operation(options: CliOptions) -> int:
    """Stub intencional: os algoritmos avaliados começam a partir daqui."""

    # Crie funções ou módulos adicionais para as implementações e mantenha
    # __main__.py dedicado à coordenação da aplicação.
    print(
        f"Operacao '{options.operation}' reconhecida, "
        "mas ainda nao implementada no projeto-base.",
        file=sys.stderr,
    )
    return exit_code.GENERAL_ERROR
