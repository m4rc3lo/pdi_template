#include "pdi/contract.hpp"
#include "pdi/kernel.hpp"
#include "pdi/parameters.hpp"
#include "pdi/result_io.hpp"

#include <array>
#include <cassert>
#include <filesystem>
#include <fstream>
#include <string>

int main()
{
    namespace fs = std::filesystem;

    // Validação de parâmetros tipados e regras do contrato.
    pdi::CliOptions quantize;
    quantize.operation = "quantize";
    quantize.input = "in.png";
    quantize.output = "out.png";
    quantize.parameters["levels"] = "8";
    assert(pdi::validate_contract(quantize));
    assert(pdi::parameter_as_int(quantize, "levels") == 8);

    quantize.parameters["levels"] = "3";
    assert(!pdi::validate_contract(quantize));

    pdi::CliOptions convolution;
    convolution.operation = "convolution";
    convolution.input = "in.png";
    convolution.output = "out.png";
    convolution.parameters["kernel"] = "kernel.txt";
    convolution.parameters["border"] = "replicate";
    assert(pdi::validate_contract(convolution));
    assert(pdi::parameter_as_border(convolution) == pdi::BorderStrategy::replicate);

    // Arquivos temporários criados dentro de build/ pelo runner do teste.
    const fs::path root = fs::current_path() / "pdi_test_tmp";
    fs::remove_all(root);
    fs::create_directories(root);

    const fs::path kernel_path = root / "identity.txt";
    {
        std::ofstream kernel_file(kernel_path);
        kernel_file << "0 0 0\n0 1 0\n0 0 0\n";
    }

    const auto kernel = pdi::read_kernel(kernel_path);
    assert(kernel.size() == 3);
    assert(kernel.radius() == 1);
    assert(kernel.at(1, 1) == 1.0);

    std::array<std::uint64_t, 256> histogram{};
    histogram[0] = 2;
    histogram[255] = 1;
    const fs::path csv_path = root / "nested" / "histogram.csv";
    pdi::write_histogram_csv(csv_path, histogram);
    assert(fs::exists(csv_path));

    const fs::path json_path = root / "nested" / "metadata.json";
    pdi::write_json_object(json_path, {{"operation", "test"}, {"status", "ok"}});
    assert(fs::exists(json_path));

    fs::remove_all(root);
    return 0;
}
