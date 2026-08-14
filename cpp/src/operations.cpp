#include "pdi/operations.hpp"

#include <array>
#include <iostream>
#include <string_view>

namespace pdi {
namespace {

// Lista comum às três linguagens. As atividades complementares/opcionais
// não fazem parte do contrato mínimo do template.
constexpr std::array<std::string_view, 18> operations {
    // M1.1 — representação, canais e níveis de cinza
    "inspect",
    "copy",
    "channel_b",
    "channel_g",
    "channel_r",
    "grayscale_average",
    "grayscale_weighted",
    "quantize",

    // M1.2 — transformações de intensidade
    "brightness",
    "contrast",
    "negative",
    "threshold",
    "histogram",

    // M1.3 — convolução e filtragem espacial
    "convolution",
    "mean_filter",
    "weighted_mean",
    "laplacian",
    "sobel"
};

} // namespace

bool is_known_operation(std::string_view operation)
{
    for (const auto candidate : operations) {
        if (candidate == operation) {
            return true;
        }
    }
    return false;
}

ExitCode run_operation(const CliOptions& options)
{
    // Este é deliberadamente um stub.
    //
    // A infraestrutura anterior a este ponto já cuidou da sintaxe básica da
    // CLI e dos parâmetros gerais. O estudante deve substituir o despacho
    // deste stub por funções/classes que implementem as operações solicitadas.
    //
    // Não implemente os algoritmos diretamente em main.cpp. Mantenha o código
    // de Processamento de Imagens separado da infraestrutura da aplicação.
    std::cerr
        << "Operacao '" << options.operation
        << "' reconhecida, mas ainda nao implementada no projeto-base.\n";

    return ExitCode::general_error;
}

} // namespace pdi
