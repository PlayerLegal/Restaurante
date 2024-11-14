package br.com.catolica.restaurante.Model.abstratas;

public abstract class Pessoa {
    protected String nome;

    public Pessoa(String nome) {
        this.nome = nome;
    }

    public abstract void exibirPerfil();
}