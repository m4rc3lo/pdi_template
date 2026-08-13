from pdi_lab.cli import parse_cli
from pdi_lab.contract import validate_contract
from pdi_lab.operations import is_known_operation

def test_parses_and_validates_known_operation():
    o=parse_cli(["--operation","negative","--input","in.png","--output","out.png"])
    assert o.operation=="negative"
    assert is_known_operation(o.operation)
    assert validate_contract(o).ok
    assert not is_known_operation("x")

def test_output_is_optional_only_for_inspect():
    inspect=parse_cli(["--operation","inspect","--input","in.png"])
    negative=parse_cli(["--operation","negative","--input","in.png"])
    assert validate_contract(inspect).ok
    assert not validate_contract(negative).ok
