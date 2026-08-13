#include "pdi/cli.hpp"
#include <iostream>
#include <stdexcept>
namespace pdi {
CliOptions parse_cli(int argc, char** argv) {
    CliOptions o;
    for (int i = 1; i < argc; ++i) {
        const std::string a = argv[i];
        if (a == "--help" || a == "-h") { o.help = true; continue; }
        if (a == "--version") { o.version = true; continue; }
        if (!a.starts_with("--")) throw std::invalid_argument("Argumento inesperado: " + a);
        if (i + 1 >= argc) throw std::invalid_argument("Falta valor para: " + a);
        const std::string v = argv[++i];
        const std::string k = a.substr(2);
        if (k == "operation") o.operation = v;
        else if (k == "input") o.input = v;
        else if (k == "output") o.output = v;
        else o.parameters[k] = v;
    }
    return o;
}
void print_help() {
    std::cout
      << "pdi_lab - projeto-base M1\n\n"
      << "Uso: pdi_lab --input <arquivo> --output <arquivo> --operation <operacao> [opcoes]\n"
      << "     pdi_lab --help\n"
      << "     pdi_lab --version\n\n"
      << "Operacoes: inspect, copy, channel_b, channel_g, channel_r, "
         "grayscale_average, grayscale_weighted, quantize, brightness, contrast, "
         "negative, threshold, histogram, convolution, mean_filter, weighted_mean, "
         "laplacian, sobel\n";
}
void print_version() { std::cout << "pdi_lab 0.2.0 contract=2\n"; }
}
