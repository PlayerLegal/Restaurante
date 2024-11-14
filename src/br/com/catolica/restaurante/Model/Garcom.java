package br.com.catolica.restaurante.Model;

import br.com.catolica.restaurante.Model.abstratas.Pessoa;

public class Garcom extends Pessoa {
    public Garcom(String nome) {
        super(nome);
    }

    @Override
    public void exibirPerfil() {
        System.out.println("Garçom: " + nome);
    }
}