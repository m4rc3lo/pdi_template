from __future__ import annotations
import argparse
from dataclasses import dataclass, field
@dataclass
class CliOptions:
    operation: str | None = None
    input: str | None = None
    output: str | None = None
    parameters: dict[str,str] = field(default_factory=dict)
def build_parser() -> argparse.ArgumentParser:
    p=argparse.ArgumentParser(prog="pdi_lab",description="Projeto-base dos laboratorios M1")
    p.add_argument("--version",action="store_true")
    for name in ["operation","input","output","value","levels","threshold","alpha","kernel","border"]:
        p.add_argument(f"--{name}")
    return p
def parse_cli(argv=None):
    d=vars(build_parser().parse_args(argv)); version=d.pop("version"); operation=d.pop("operation"); inp=d.pop("input"); out=d.pop("output")
    params={k:v for k,v in d.items() if v is not None}
    o=CliOptions(operation,inp,out,params); o.parameters["__version_flag__"]="1" if version else "0"; return o
def version_requested(o:CliOptions)->bool: return o.parameters.pop("__version_flag__", "0")=="1"
