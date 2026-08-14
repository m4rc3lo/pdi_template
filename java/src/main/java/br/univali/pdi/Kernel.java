package br.univali.pdi;

/** Representação validada de um kernel quadrado de dimensão ímpar. */
public record Kernel(int size, double[] values) {
    public Kernel {
        if (size <= 0 || size % 2 == 0) {
            throw new IllegalArgumentException("O kernel deve ter dimensao impar e maior que zero.");
        }
        if (values == null || values.length != size * size) {
            throw new IllegalArgumentException("Quantidade de coeficientes invalida para o kernel.");
        }
        values = values.clone();
    }

    public int radius() {
        return size / 2;
    }

    public double at(int row, int col) {
        if (row < 0 || col < 0 || row >= size || col >= size) {
            throw new IndexOutOfBoundsException("Indice fora dos limites do kernel.");
        }
        return values[row * size + col];
    }

    @Override
    public double[] values() {
        return values.clone();
    }
}
