#include "pdi/contract.hpp"
#include "pdi/operations.hpp"
namespace pdi {
ValidationResult validate_contract(const CliOptions& o) {
    if (o.operation.empty()) return {ExitCode::invalid_arguments, "Informe --operation."};
    if (!is_known_operation(o.operation)) return {ExitCode::unknown_operation, "Operacao desconhecida: " + o.operation};
    if (!o.input.has_value()) return {ExitCode::invalid_arguments, "Informe --input."};
    if (o.operation != "inspect" && !o.output.has_value()) return {ExitCode::invalid_arguments, "Informe --output."};
    return {};
}
}
