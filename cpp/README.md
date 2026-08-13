# Projeto-base C++ — Laboratórios M1 — v2

Base mínima com C++20, CMake, OpenCV, CLI padronizada e testes. As operações avaliadas permanecem como **stubs**.

## Windows — MSYS2 UCRT64

Com OpenCV instalado no ambiente:

```bash
cmake --preset ucrt64-debug
cmake --build --preset ucrt64-debug
ctest --preset ucrt64-debug
./build/ucrt64-debug/pdi_lab.exe --version
```

## OpenCV portátil

Em uma máquina sem permissão para instalar OpenCV no sistema, extraia uma distribuição **compatível com o mesmo toolchain UCRT64/GCC** em uma pasta do usuário e informe o diretório que contém `OpenCVConfig.cmake`:

```bash
export PDI_OPENCV_DIR="$HOME/pdi-tools/opencv/lib/cmake/opencv4"
cmake --preset ucrt64-debug
```

Ou diretamente:

```bash
cmake --preset ucrt64-debug \
  -DPDI_OPENCV_DIR="$HOME/pdi-tools/opencv/lib/cmake/opencv4"
```

Não misture binários C++ compilados para MSVC com o executável GCC/UCRT64 da disciplina.

## Unix-like

```bash
cmake --preset unix-debug
cmake --build --preset unix-debug
ctest --preset unix-debug
./build/unix-debug/pdi_lab --help
```

O mesmo `PDI_OPENCV_DIR` pode apontar para uma instalação local em `$HOME`.

## Contrato mínimo

Para operações de imagem, `--input` é obrigatório. Para todas as operações, exceto `inspect`, `--output` também é obrigatório. O projeto-base valida apenas o contrato externo; a implementação do algoritmo continua sendo responsabilidade do estudante.

## Onde implementar

Comece em `src/operations.cpp` e crie classes/funções adicionais. Não concentre a solução em `main.cpp`.
