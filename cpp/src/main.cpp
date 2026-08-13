#include "pdi/cli.hpp"
#include "pdi/contract.hpp"
#include "pdi/exit_code.hpp"
#include "pdi/operations.hpp"
#include <exception>
#include <iostream>
int main(int argc, char** argv) {
    try {
        const auto o = pdi::parse_cli(argc, argv);
        if (o.help) { pdi::print_help(); return 0; }
        if (o.version) { pdi::print_version(); return 0; }
        const auto validation = pdi::validate_contract(o);
        if (!validation) { std::cerr << "Erro: " << validation.message << '\n'; return static_cast<int>(validation.code); }
        return static_cast<int>(pdi::run_operation(o));
    } catch (const std::invalid_argument& e) {
        std::cerr << "Erro de argumentos: " << e.what() << '\n'; return 2;
    } catch (const std::exception& e) {
        std::cerr << "Erro: " << e.what() << '\n'; return 1;
    }
}
