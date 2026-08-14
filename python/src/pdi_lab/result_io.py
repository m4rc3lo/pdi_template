from __future__ import annotations

import csv
import json
from pathlib import Path
from typing import Mapping, Sequence


def ensure_parent_directory(path: str | Path) -> Path:
    output = Path(path)
    output.parent.mkdir(parents=True, exist_ok=True)
    return output


def write_histogram_csv(path: str | Path, histogram: Sequence[int]) -> None:
    """Salva 256 contadores já calculados pelo estudante."""

    if len(histogram) != 256:
        raise ValueError("O histograma deve possuir 256 posicoes.")

    output = ensure_parent_directory(path)
    with output.open("w", newline="", encoding="utf-8") as file:
        writer = csv.writer(file)
        writer.writerow(["intensity", "count"])
        for intensity, count in enumerate(histogram):
            writer.writerow([intensity, int(count)])


def write_json_object(path: str | Path, values: Mapping[str, object]) -> None:
    """Serializa metadados simples sem interferir no algoritmo avaliado."""

    output = ensure_parent_directory(path)
    with output.open("w", encoding="utf-8") as file:
        json.dump(values, file, ensure_ascii=False, indent=2)
        file.write("\n")
