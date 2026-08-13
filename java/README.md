# Projeto-base Java — Laboratórios M1 — v2

Base Maven para Java 17+ com CLI padronizada, testes e OpenCV desktop empacotado pela dependência `org.openpnp:opencv`.

## Por que esta dependência

O artefato oficial atual `org.opencv:opencv` distribuído no Maven Central é publicado como AAR, orientado ao ecossistema Android. Para o projeto desktop da disciplina, esta base mantém `org.openpnp:opencv:4.9.0-0`, que empacota bindings Java, bibliotecas nativas e loader para múltiplas plataformas.

## Windows/MSYS2 UCRT64 e Unix-like

Desde que `java` e `mvn` estejam disponíveis no `PATH`, os comandos são os mesmos:

```bash
mvn test
mvn package
java -jar target/pdi-lab.jar --version
```

Maven também pode ser usado sem instalação administrativa: a distribuição binária pode ser extraída em uma pasta do usuário e seu diretório `bin` adicionado temporariamente ao `PATH`.

## Contrato mínimo

Para operações de imagem, `--input` é obrigatório. Para todas, exceto `inspect`, `--output` também é obrigatório.

## Onde implementar

Comece em `src/main/java/br/univali/pdi/Operations.java` e crie classes adicionais por responsabilidade.
