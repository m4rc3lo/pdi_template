# Validacao manual

Antes do merge e do release, valide C++, Java e Python localmente.

## C++

```bash
cd cpp
cmake --preset ucrt64-debug
cmake --build --preset ucrt64-debug
ctest --preset ucrt64-debug
./build/ucrt64-debug/pdi_lab.exe --help
./build/ucrt64-debug/pdi_lab.exe --version
```

## Java

```bash
cd java
mvn test
mvn package
java -jar target/pdi-lab.jar --help
java -jar target/pdi-lab.jar --version
```

## Python

```bash
cd python
python -m venv .venv
python -m pip install -r requirements.txt
python -m pip install -e .
python -m pytest
python -m pdi_lab --help
python -m pdi_lab --version
```

Confira tambem que as operacoes avaliadas continuam sem implementacao pronta e que as tres variantes preservam os mesmos nomes de operacoes, parametros e codigos de saida.
