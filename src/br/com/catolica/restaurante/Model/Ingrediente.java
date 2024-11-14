package br.com.catolica.restaurante.Model;

import br.com.catolica.restaurante.Enum.TipoIngrediente;

public class Ingrediente {
    private String nome;
    private TipoIngrediente tipo;

    public Ingrediente(String nome, TipoIngrediente tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public TipoIngrediente getTipo() {
        return tipo;
    }
}