# Versao minima do CMake usada pelo projeto-base.
cmake_minimum_required(VERSION 3.24)

# Declara o projeto C++ e sua versao.
# A versao identifica o template fornecido pela disciplina.
project(pdi_lab VERSION 0.2.0 LANGUAGES CXX)

# O projeto utiliza C++20 de forma portavel.
# REQUIRED impede que o CMake escolha silenciosamente um padrao mais antigo.
# EXTENSIONS OFF evita extensoes especificas de um compilador.
set(CMAKE_CXX_STANDARD 20)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
set(CMAKE_CXX_EXTENSIONS OFF)

# Gera compile_commands.json, util para editores e ferramentas de analise.
set(CMAKE_EXPORT_COMPILE_COMMANDS ON)

# Habilita a infraestrutura de testes do CMake por meio do CTest.
include(CTest)

# Centraliza a descoberta do OpenCV, inclusive para instalacoes portateis.
include(cmake/PdiOpenCV.cmake)

# A biblioteca pdi_core contem a infraestrutura comum da aplicacao.
# Os algoritmos dos laboratorios permanecem sem implementacao no template.
add_library(pdi_core
    src/cli.cpp
    src/contract.cpp
    src/image_io.cpp
    src/operations.cpp
)

# Alias para tornar a dependencia mais explicita nos demais alvos.
add_library(pdi::core ALIAS pdi_core)

# Disponibiliza os headers de include/ para quem utilizar pdi_core.
target_include_directories(
    pdi_core
    PUBLIC ${CMAKE_CURRENT_SOURCE_DIR}/include
)

# Liga a infraestrutura do projeto aos modulos do OpenCV encontrados.
target_link_libraries(pdi_core PUBLIC ${OpenCV_LIBS})

# Warnings ajudam a detectar construcoes suspeitas durante a compilacao.
# Eles nao sao convertidos em erro para nao bloquear o desenvolvimento.
if(MSVC)
    target_compile_options(pdi_core PRIVATE /W4 /permissive-)
else()
    target_compile_options(
        pdi_core
        PRIVATE
            -Wall
            -Wextra
            -Wpedantic
            -Wshadow
            -Wconversion
            -Wsign-conversion
    )
endif()

# Executavel principal usado pela interface de linha de comando.
add_executable(pdi_lab src/main.cpp)
target_link_libraries(pdi_lab PRIVATE pdi::core)

# Os testes de infraestrutura sao construidos quando BUILD_TESTING=ON.
if(BUILD_TESTING)
    add_executable(pdi_smoke_test tests/smoke_test.cpp)
    target_link_libraries(pdi_smoke_test PRIVATE pdi::core)
    add_test(NAME pdi_smoke_test COMMAND pdi_smoke_test)
endif()
