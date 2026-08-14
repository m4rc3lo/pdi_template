from __future__ import annotations

from dataclasses import dataclass
from pathlib import Path


@dataclass(frozen=True)
class Kernel:
    """Kernel quadrado de dimensão ímpar já validado pela infraestrutura."""

    size: int
    values: tuple[float, ...]

    def __post_init__(self) -> None:
        if self.size <= 0 or self.size % 2 == 0:
            raise ValueError("O kernel deve ter dimensao impar e maior que zero.")
        if len(self.values) != self.size * self.size:
            raise ValueError("Quantidade de coeficientes invalida para o kernel.")

    @property
    def radius(self) -> int:
        return self.size // 2

    def at(self, row: int, col: int) -> float:
        if row < 0 or col < 0 or row >= self.size or col >= self.size:
            raise IndexError("Indice fora dos limites do kernel.")
        return self.values[row * self.size + col]


def read_kernel(path: str | Path) -> Kernel:
    """Lê o arquivo; aplicar a convolução continua sendo tarefa do estudante."""

    rows: list[list[float]] = []
    for raw_line in Path(path).read_text(encoding="utf-8").splitlines():
        line = raw_line.strip()
        if not line or line.startswith("#"):
            continue
        try:
            rows.append([float(token) for token in line.split()])
        except ValueError as error:
            raise ValueError(f"Coeficiente invalido no kernel: {line}") from error

    if not rows:
        raise ValueError("O arquivo de kernel esta vazio.")

    size = len(rows[0])
    if size == 0 or len(rows) != size or size % 2 == 0:
        raise ValueError("O kernel deve ser quadrado e possuir dimensao impar.")
    if any(len(row) != size for row in rows):
        raise ValueError("Todas as linhas do kernel devem ter o mesmo tamanho.")

    values = tuple(value for row in rows for value in row)
    return Kernel(size=size, values=values)
