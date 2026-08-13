from __future__ import annotations
import sys
from . import __version__, CONTRACT_VERSION, exit_code
from .cli import parse_cli, version_requested
from .contract import validate_contract
from .operations import run_operation
def main(argv=None):
    try:
        o=parse_cli(argv)
        if version_requested(o):
            print(f"pdi_lab {__version__} contract={CONTRACT_VERSION}"); return 0
        v=validate_contract(o)
        if not v.ok:
            print(f"Erro: {v.message}",file=sys.stderr); return v.code
        return run_operation(o)
    except (ValueError,OSError) as e:
        print(f"Erro: {e}",file=sys.stderr); return exit_code.GENERAL_ERROR
if __name__=="__main__": raise SystemExit(main())
