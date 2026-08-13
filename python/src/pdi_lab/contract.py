from __future__ import annotations
from dataclasses import dataclass
from . import exit_code
from .cli import CliOptions
from .operations import is_known_operation
@dataclass(frozen=True)
class ValidationResult:
    code:int=exit_code.SUCCESS
    message:str=""
    @property
    def ok(self)->bool:return self.code==exit_code.SUCCESS
def validate_contract(o:CliOptions)->ValidationResult:
    if not o.operation:return ValidationResult(exit_code.INVALID_ARGUMENTS,"Informe --operation.")
    if not is_known_operation(o.operation):return ValidationResult(exit_code.UNKNOWN_OPERATION,f"Operacao desconhecida: {o.operation}")
    if not o.input:return ValidationResult(exit_code.INVALID_ARGUMENTS,"Informe --input.")
    if o.operation!="inspect" and not o.output:return ValidationResult(exit_code.INVALID_ARGUMENTS,"Informe --output.")
    return ValidationResult()
