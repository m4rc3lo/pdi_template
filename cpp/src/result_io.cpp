#include "pdi/result_io.hpp"

#include <fstream>
#include <iomanip>
#include <stdexcept>

namespace pdi {
namespace {

std::string escape_json(const std::string& text)
{
    std::string result;
    result.reserve(text.size());
    for (const char ch : text) {
        switch (ch) {
        case '"': result += "\\\""; break;
        case '\\': result += "\\\\"; break;
        case '\n': result += "\\n"; break;
        case '\r': result += "\\r"; break;
        case '\t': result += "\\t"; break;
        default: result += ch; break;
        }
    }
    return result;
}

} // namespace

void ensure_parent_directory(const std::filesystem::path& path)
{
    const auto parent = path.parent_path();
    if (!parent.empty()) {
        std::filesystem::create_directories(parent);
    }
}

void write_histogram_csv(
    const std::filesystem::path& path,
    const std::array<std::uint64_t, 256>& histogram)
{
    ensure_parent_directory(path);
    std::ofstream output(path);
    if (!output) {
        throw std::runtime_error("Nao foi possivel criar o CSV: " + path.string());
    }

    output << "intensity,count\n";
    for (std::size_t i = 0; i < histogram.size(); ++i) {
        output << i << ',' << histogram[i] << '\n';
    }
}

void write_json_object(const std::filesystem::path& path, const StringMap& values)
{
    ensure_parent_directory(path);
    std::ofstream output(path);
    if (!output) {
        throw std::runtime_error("Nao foi possivel criar o JSON: " + path.string());
    }

    output << "{\n";
    std::size_t index = 0;
    for (const auto& [key, value] : values) {
        output << "  \"" << escape_json(key) << "\": \"" << escape_json(value) << "\"";
        if (++index < values.size()) {
            output << ',';
        }
        output << '\n';
    }
    output << "}\n";
}

} // namespace pdi
