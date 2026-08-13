# Projetos-base — Laboratórios M1 — PDI 2026-02 — v2

Este pacote contém três projetos-base equivalentes:

- `cpp/` — C++20 + CMake + OpenCV;
- `java/` — Java 17+ + Maven + OpenPnP OpenCV;
- `python/` — Python 3.10+ + venv + OpenCV headless + pytest.

Todos preservam o mesmo contrato externo de execução e não implementam as operações avaliadas.

## Refinamentos desta versão

- validação uniforme dos argumentos básicos;
- `--version` nas três linguagens;
- mensagens de erro e códigos de saída consistentes;
- suporte explícito a OpenCV portátil no projeto C++ por `PDI_OPENCV_DIR`;
- preset `unix-debug` em C++;
- dependências Python orientadas a ambiente sem GUI;
- testes de contrato adicionais;
- documentação para Windows/MSYS2 UCRT64 e Unix-like.

Consulte o `README.md` da linguagem escolhida.
