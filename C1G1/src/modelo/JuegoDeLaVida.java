package modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class JuegoDeLaVida {

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduce la ruta al archivo de estado inicial:");
        String archivoEstadoInicial = scanner.nextLine();

        System.out.println("Introduce el número de generaciones a simular:");
        int numeroGeneraciones = scanner.nextInt();

        boolean[][] estadoInicial = cargarEstadoInicial(archivoEstadoInicial);
        Tablero tablero = new Tablero(estadoInicial.length);
        tablero.cargarEstadoInicial(estadoInicial);

        for (int i = 0; i < numeroGeneraciones; i++) {
            System.out.println("Generación " + (i + 1) + ":");
            tablero.simularGeneracion();
            Thread.sleep(1000); // Espera 1 segundo antes de la próxima generación
            tablero.mostrarEstado();
        }
    }

    private static boolean[][] cargarEstadoInicial(String archivo) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(archivo));
        String linea;
        int tamanio = Integer.parseInt(br.readLine());
        boolean[][] estadoInicial = new boolean[tamanio][tamanio];

        for (int i = 0; i < tamanio; i++) {
            linea = br.readLine();
            for (int j = 0; j < linea.length(); j++) {
                estadoInicial[i][j] = linea.charAt(j) == '1';
            }
        }

        br.close();
        return estadoInicial;
    }
}
