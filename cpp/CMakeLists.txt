cmake_minimum_required(VERSION 3.24)
project(pdi_lab VERSION 0.2.0 LANGUAGES CXX)

set(CMAKE_CXX_STANDARD 20)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
set(CMAKE_CXX_EXTENSIONS OFF)
set(CMAKE_EXPORT_COMPILE_COMMANDS ON)

include(CTest)
include(cmake/PdiOpenCV.cmake)

add_library(pdi_core
    src/cli.cpp
    src/contract.cpp
    src/image_io.cpp
    src/operations.cpp
)
add_library(pdi::core ALIAS pdi_core)

target_include_directories(pdi_core PUBLIC ${CMAKE_CURRENT_SOURCE_DIR}/include)
target_link_libraries(pdi_core PUBLIC ${OpenCV_LIBS})

if(MSVC)
    target_compile_options(pdi_core PRIVATE /W4 /permissive-)
else()
    target_compile_options(pdi_core PRIVATE
        -Wall -Wextra -Wpedantic -Wshadow -Wconversion -Wsign-conversion
    )
endif()

add_executable(pdi_lab src/main.cpp)
target_link_libraries(pdi_lab PRIVATE pdi::core)

if(BUILD_TESTING)
    add_executable(pdi_smoke_test tests/smoke_test.cpp)
    target_link_libraries(pdi_smoke_test PRIVATE pdi::core)
    add_test(NAME pdi_smoke_test COMMAND pdi_smoke_test)
endif()
