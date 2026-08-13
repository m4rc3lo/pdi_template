#include "pdi/operations.hpp"
#include <array>
#include <iostream>
namespace pdi {
namespace { constexpr std::array<std::string_view,18> ops={"inspect","copy","channel_b","channel_g","channel_r","grayscale_average","grayscale_weighted","quantize","brightness","contrast","negative","threshold","histogram","convolution","mean_filter","weighted_mean","laplacian","sobel"}; }
bool is_known_operation(std::string_view operation){ for(auto x:ops) if(x==operation) return true; return false; }
ExitCode run_operation(const CliOptions& options){ std::cerr << "Operacao '"<<options.operation<<"' reconhecida, mas ainda nao implementada no projeto-base.\n"; return ExitCode::general_error; }
}
