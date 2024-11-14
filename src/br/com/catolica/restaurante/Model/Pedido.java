// Pedido.java
package br.com.catolica.restaurante.Model;

import br.com.catolica.restaurante.Contract.IPedidoInterface;
import br.com.catolica.restaurante.Enum.StatusPedido;
import br.com.catolica.restaurante.Model.abstratas.ItemCardapio;

import java.util.List;

public class Pedido implements IPedidoInterface {
    private List<ItemCardapio> itens;
    private StatusPedido status;

    public Pedido(List<ItemCardapio> itens) {
        this.itens = itens;
        this.status = StatusPedido.REALIZADO;  // Status inicial do pedido
    }

    @Override
    public void realizarPedido() {
        status = StatusPedido.EM_PREPARO;
        System.out.println("Pedido está em preparo.");
    }

    @Override
    public void cancelarPedido() {
        status = StatusPedido.CANCELADO;
        System.out.println("Pedido cancelado.");
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void exibirPedido() {
        System.out.println("Status: " + status);
        itens.forEach(ItemCardapio::exibirInfo);
    }
}
