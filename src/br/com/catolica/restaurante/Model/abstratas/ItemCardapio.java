// ItemCardapio.java
package br.com.catolica.restaurante.Model.abstratas;

import br.com.catolica.restaurante.Model.Ingrediente;
import java.util.List;

public abstract class ItemCardapio {
    protected String nome;
    protected double preco;
    protected List<Ingrediente> ingredientes;

    public ItemCardapio(String nome, double preco, List<Ingrediente> ingredientes) {
        this.nome = nome;
        this.preco = preco;
        this.ingredientes = ingredientes;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public abstract void exibirInfo();
}
