package modelo;

public class Tablero {
    private Celda[][] celdas;
    private int tamanio;

    public Tablero(int tamanio) {
        this.tamanio = tamanio;
        this.celdas = new Celda[tamanio][tamanio];

        // Inicializa todas las celdas como muertas inicialmente
        for (int i = 0; i < tamanio; i++) {
            for (int j = 0; j < tamanio; j++) {
                celdas[i][j] = new Celda(false);
            }
        }
    }

    public void cargarEstadoInicial(boolean[][] estadoInicial) {
        for (int i = 0; i < tamanio; i++) {
            for (int j = 0; j < tamanio; j++) {
                celdas[i][j] = new Celda(estadoInicial[i][j]);
            }
        }
        establecerVecinos();
    }

    private void establecerVecinos() {
        for (int i = 0; i < tamanio; i++) {
            for (int j = 0; j < tamanio; j++) {
                Celda[] vecinos = new Celda[8];
                int contador = 0;
                for (int k = -1; k <= 1; k++) {
                    for (int l = -1; l <= 1; l++) {
                        if (k == 0 && l == 0) continue; // Salta la propia celda
                        int fila = (i + k + tamanio) % tamanio;
                        int columna = (j + l + tamanio) % tamanio;
                        vecinos[contador++] = celdas[fila][columna];
                    }
                }
                celdas[i][j].setVecinos(vecinos);
            }
        }
    }

    public void mostrarEstado() {
        for (int i = 0; i < tamanio; i++) {
            for (int j = 0; j < tamanio; j++) {
                System.out.print(celdas[i][j].estaViva() ? "1" : "0");
            }
            System.out.println();
        }
    }


    public void simularGeneracion() {
        for (int i = 0; i < tamanio; i++) {
            for (int j = 0; j < tamanio; j++) {
                new Thread(celdas[i][j]).start();
            }
        }
    }
}
