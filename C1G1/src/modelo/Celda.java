package modelo;

public class Celda implements Runnable {
    private boolean vivo;
    private final Object lock = new Object();
    private boolean proximoEstado;
    private Celda[] vecinos;

    public Celda(boolean estadoInicial) {
        this.vivo = estadoInicial;
    }

    public void setVecinos(Celda[] vecinos) {
        this.vecinos = vecinos;
    }

    public boolean estaViva(){

        int vecinosVivos = 0;
        for (Celda vecino : vecinos) {
            if (vecino.vivo) {
                vecinosVivos++;
            }
        }
        // Una celda está viva si tiene entre 1 y 3 vecinos vivos.
        return vivo && vecinosVivos >= 1 && vecinosVivos <= 3;
    }

    private void calcularProximoEstado() {
        int vecinosVivos = 0;
        for (Celda vecino : vecinos) {
            if (vecino.vivo) {
                vecinosVivos++;
            }
        }
        // Una celda está viva en el próximo turno si tiene exactamente 2 o 3 vecinos vivos.
        proximoEstado = vecinosVivos == 3 || (vivo && vecinosVivos == 2);
    }

    public void actualizarEstado() {
        synchronized (lock) {
            vivo = proximoEstado;
        }
    }

    @Override
    public void run() {
        while (true) {
            calcularProximoEstado();

            try {
                Thread.sleep(100); // Simula el tiempo de espera para la siguiente generación
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            actualizarEstado();
        }
    }
}

