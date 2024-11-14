package br.com.catolica.restaurante.Model;

import br.com.catolica.restaurante.Model.abstratas.Pessoa;

public class Cliente extends Pessoa {
    public Cliente(String nome) {
        super(nome);
    }

    @Override
    public void exibirPerfil() {
        System.out.println("Cliente: " + nome);
    }
}