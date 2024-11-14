package br.com.catolica.restaurante.Model;

public class Mesa {
    private int numero;
    private boolean disponivel;

    public Mesa(int numero) {
        this.numero = numero;
        this.disponivel = true;
    }

    public void reservar() {
        disponivel = false;
        System.out.println("Mesa " + numero + " reservada.");
    }

    public void liberar() {
        disponivel = true;
        System.out.println("Mesa " + numero + " liberada.");
    }

    public boolean isDisponivel() {
        return disponivel;
    }
}