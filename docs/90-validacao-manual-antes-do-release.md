# Validação manual antes do release

Este checklist deve ser executado pelo professor antes do merge da branch de preparação e antes da criação de uma versão de release do template.

A validação deve ser feita a partir de uma cópia limpa do repositório, sem reaproveitar diretórios de build ou ambientes virtuais antigos.

## 1. Conferência geral

```bash
git status
git diff --check
```

Confirme que:

- não existem arquivos temporários ou artefatos de build versionados;
- `lab.json` permanece como modelo a ser preenchido;
- as operações avaliadas continuam sem implementação;
- não foram introduzidas funções prontas que resolvam os algoritmos dos laboratórios;
- os três projetos expõem as mesmas operações e parâmetros.

## 2. C++ — MSYS2 UCRT64

Dentro de `cpp/`:

```bash
cmake --list-presets
cmake --preset ucrt64-debug
cmake --build --preset ucrt64-debug
ctest --preset ucrt64-debug
./build/ucrt64-debug/pdi_lab.exe --version
./build/ucrt64-debug/pdi_lab.exe --help
```

Também teste a configuração Release:

```bash
cmake --preset ucrt64-release
cmake --build --preset ucrt64-release
```

Verifique se o OpenCV é encontrado corretamente e se não há warnings inesperados introduzidos pelo template.

## 3. C++ — Unix-like

Em Linux ou outro ambiente Unix-like compatível:

```bash
cmake --preset unix-debug
cmake --build --preset unix-debug
ctest --preset unix-debug
./build/unix-debug/pdi_lab --version
./build/unix-debug/pdi_lab --help
```

## 4. Java

Dentro de `java/`:

```bash
mvn clean test
mvn package
java -jar target/pdi-lab.jar --version
java -jar target/pdi-lab.jar --help
```

Confirme que a dependência OpenCV é carregada sem configuração manual adicional na máquina utilizada para o teste.

## 5. Python

Dentro de `python/`:

```bash
python -m venv .venv
```

Ative o ambiente e execute:

```bash
python -m pip install --upgrade pip
python -m pip install -r requirements.txt
python -m pip install -e .
python -m pytest
python -m pdi_lab --version
python -m pdi_lab --help
```

## 6. Validação comum da CLI

Execute casos equivalentes nas três linguagens.

### Quantização sem parâmetro

Deve falhar informando a ausência de `--levels`:

```text
--operation quantize --input images/input/test.png --output images/output/q.png
```

### Quantização inválida

Deve rejeitar:

```text
--levels 3
```

### Limiar inválido

Deve rejeitar:

```text
--threshold 300
```

### Contraste inválido

Deve rejeitar:

```text
--alpha 0
```

### Convolução sem kernel

Deve indicar que `--kernel` é obrigatório.

### Estratégia de borda inválida

Deve rejeitar qualquer valor diferente de:

```text
copy
replicate
```

### Filtro de média

Deve aceitar `--size 3` e `--size 5` e rejeitar outros tamanhos no contrato atual.

## 7. Infraestrutura de arquivos

Valide separadamente:

- leitura de uma imagem existente;
- diagnóstico de imagem inexistente;
- criação automática de subdiretório de saída;
- gravação de uma imagem de teste por uma chamada de infraestrutura;
- leitura de `kernels/identity_3x3.txt`;
- rejeição de kernel vazio, par e não quadrado;
- geração de CSV com cabeçalho `intensity,count`;
- geração de JSON válido.

## 8. Imagens-base

Antes do release, copie o conjunto público de imagens para `images/input/` das três variantes ou gere o ZIP final de modo que cada linguagem receba o mesmo conjunto.

Confirme a presença de, pelo menos:

```text
m1_color_2x2.png
m1_color_scene_256.png
m1_gray_ramp_256.png
m1_gray_steps_16levels.png
m1_gray_scene_256.png
m1_constant_128_64.png
m1_impulse_33.png
m1_step_vertical_128.png
m1_step_horizontal_128.png
m1_shapes_border_256.png
m1_checkerboard_256.png
m1_gray_5x5.png
```

## 9. Verificação pedagógica final

Abra o ZIP como se fosse estudante e verifique se, sem conhecer o repositório original, é possível descobrir:

1. qual pasta escolher;
2. como preparar o ambiente;
3. como construir ou instalar;
4. como executar os testes do template;
5. como consultar `--help`;
6. onde implementar os algoritmos;
7. onde estão as imagens de entrada;
8. onde salvar os resultados;
9. como preencher `REPORT.md`, `AI_USAGE.md` e `lab.json`.

## 10. Condição para merge e release

O merge só deve ocorrer quando as três variantes passarem pelos testes de infraestrutura e apresentarem comportamento externo equivalente.

Depois do merge, repetir uma validação mínima a partir da branch de destino antes de criar a tag/release.
