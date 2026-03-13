class CuentaBancaria {
    private int saldo;

    public CuentaBancaria(int saldoInicial) {
        this.saldo = saldoInicial;
    }

    public synchronized void retirar(String cliente, int monto) {
        System.out.println(cliente + " intenta retirar $" + monto);

        if (saldo >= monto) {
            System.out.println(cliente + " puede retirar. Saldo antes: $" + saldo);
            saldo -= monto;
            System.out.println(cliente + " retiró $" + monto + ". Saldo restante: $" + saldo);
        } else {
            System.out.println(cliente + " no pudo retirar. Saldo insuficiente: $" + saldo);
        }
    }

    public int getSaldo() {
        return saldo;
    }
}

class Cliente extends Thread {
    private CuentaBancaria cuenta;
    private String nombre;

    public Cliente(CuentaBancaria cuenta, String nombre) {
        this.cuenta = cuenta;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        cuenta.retirar(nombre, 400);
    }
}

public class Ejercicio2Concurrencia {
    public static void main(String[] args) {
        CuentaBancaria cuenta = new CuentaBancaria(1000);

        Cliente cliente1 = new Cliente(cuenta, "Cliente 1");
        Cliente cliente2 = new Cliente(cuenta, "Cliente 2");
        Cliente cliente3 = new Cliente(cuenta, "Cliente 3");

        cliente1.start();
        cliente2.start();
        cliente3.start();

        try {
            cliente1.join();
            cliente2.join();
            cliente3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Saldo final: $" + cuenta.getSaldo());
    }
}
