#include "pdi/cli.hpp"
#include "pdi/contract.hpp"
#include "pdi/operations.hpp"
#include <cassert>
int main() {
    char a0[]="pdi_lab", a1[]="--operation", a2[]="negative", a3[]="--input", a4[]="in.png", a5[]="--output", a6[]="out.png";
    char* argv[]={a0,a1,a2,a3,a4,a5,a6};
    const auto o=pdi::parse_cli(7,argv);
    assert(o.operation=="negative");
    assert(pdi::is_known_operation(o.operation));
    assert(!pdi::is_known_operation("x"));
    assert(pdi::validate_contract(o));
    return 0;
}
