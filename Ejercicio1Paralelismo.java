class SumadorParcial extends Thread {
    private long inicio;
    private long fin;
    private long sumaParcial;

    public SumadorParcial(long inicio, long fin) {
        this.inicio = inicio;
        this.fin = fin;
        this.sumaParcial = 0;
    }

    @Override
    public void run() {
        for (long i = inicio; i <= fin; i++) {
            sumaParcial += i;
        }
    }

    public long getSumaParcial() {
        return sumaParcial;
    }
}

public class Ejercicio1Paralelismo {
    public static void main(String[] args) {
        SumadorParcial hilo1 = new SumadorParcial(1, 250000);
        SumadorParcial hilo2 = new SumadorParcial(250001, 500000);
        SumadorParcial hilo3 = new SumadorParcial(500001, 750000);
        SumadorParcial hilo4 = new SumadorParcial(750001, 1000000);

        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();

        try {
            hilo1.join();
            hilo2.join();
            hilo3.join();
            hilo4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long sumaTotal = hilo1.getSumaParcial()
                       + hilo2.getSumaParcial()
                       + hilo3.getSumaParcial()
                       + hilo4.getSumaParcial();

        System.out.println("La suma total es: " + sumaTotal);
    }
}
