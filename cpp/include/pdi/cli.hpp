#pragma once
#include <optional>
#include <string>
#include <unordered_map>
namespace pdi {
struct CliOptions {
    bool help = false;
    bool version = false;
    std::string operation;
    std::optional<std::string> input;
    std::optional<std::string> output;
    std::unordered_map<std::string, std::string> parameters;
};
CliOptions parse_cli(int argc, char** argv);
void print_help();
void print_version();
}
