// RestauranteApp.java
package br.com.catolica.restaurante.Main;

import br.com.catolica.restaurante.Model.abstratas.ItemCardapio;
import br.com.catolica.restaurante.Enum.StatusPedido;
import br.com.catolica.restaurante.Enum.TipoIngrediente;
import br.com.catolica.restaurante.Model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RestauranteApp {
    private static List<Mesa> mesas = new ArrayList<>();
    private static List<Pedido> pedidos = new ArrayList<>();
    private static List<ItemCardapio> cardapio = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        inicializarCardapio();  // Método para adicionar itens ao cardápio no início do programa
        int opcao;

        do {
            System.out.println("\n--- Sistema de Controle de Restaurante ---");
            System.out.println("1. Reservar Mesa");
            System.out.println("2. Liberar Mesa");
            System.out.println("3. Fazer Pedido");
            System.out.println("4. Cancelar Pedido");
            System.out.println("5. Exibir Pedidos");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> reservarMesa(scanner);
                case 2 -> liberarMesa(scanner);
                case 3 -> fazerPedido(scanner);
                case 4 -> cancelarPedido(scanner);
                case 5 -> exibirPedidos();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
        scanner.close();
    }

    private static void reservarMesa(Scanner scanner) {
        System.out.print("Número da Mesa: ");
        int numero = scanner.nextInt();
        Mesa mesa = new Mesa(numero);
        mesa.reservar();
        mesas.add(mesa);
    }

    private static void liberarMesa(Scanner scanner) {
        System.out.print("Número da Mesa: ");
        int numero = scanner.nextInt();
        mesas.stream().filter(m -> m.isDisponivel()).findFirst().ifPresentOrElse(
                Mesa::liberar, () -> System.out.println("Mesa já está liberada.")
        );
    }

    private static void fazerPedido(Scanner scanner) {
        System.out.println("Selecione itens para o pedido:");
        List<ItemCardapio> itensSelecionados = new ArrayList<>();
        String continuar;

        do {
            exibirCardapio();
            System.out.print("Digite o número do item que deseja adicionar: ");
            int itemNumero = scanner.nextInt() - 1;
            scanner.nextLine();
            if (itemNumero >= 0 && itemNumero < cardapio.size()) {
                itensSelecionados.add(cardapio.get(itemNumero));
                System.out.println("Item adicionado ao pedido.");
            } else {
                System.out.println("Item inválido.");
            }
            System.out.print("Deseja adicionar outro item? (s/n): ");
            continuar = scanner.nextLine();
        } while (continuar.equalsIgnoreCase("s"));

        if (!itensSelecionados.isEmpty()) {
            Pedido novoPedido = new Pedido(itensSelecionados);
            pedidos.add(novoPedido);
            System.out.println("Pedido realizado com sucesso!");
        } else {
            System.out.println("Nenhum item adicionado ao pedido.");
        }
    }

    private static void cancelarPedido(Scanner scanner) {
        if (pedidos.isEmpty()) {
            System.out.println("Não há pedidos para cancelar.");
            return;
        }

        System.out.println("Selecione o pedido para cancelar:");
        for (int i = 0; i < pedidos.size(); i++) {
            System.out.println((i + 1) + ". Pedido " + (i + 1) + " - Status: " + pedidos.get(i).getStatus());
        }
        System.out.print("Digite o número do pedido para cancelar: ");
        int pedidoNumero = scanner.nextInt() - 1;
        scanner.nextLine();

        if (pedidoNumero >= 0 && pedidoNumero < pedidos.size()) {
            Pedido pedido = pedidos.get(pedidoNumero);
            if (pedido.getStatus() == StatusPedido.CANCELADO) {
                System.out.println("Este pedido já foi cancelado.");
            } else {
                pedido.cancelarPedido();
                System.out.println("Pedido cancelado com sucesso!");
            }
        } else {
            System.out.println("Número de pedido inválido.");
        }
    }

    private static void exibirPedidos() {
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido realizado.");
            return;
        }
        pedidos.forEach(Pedido::exibirPedido);
    }

    private static void exibirCardapio() {
        for (int i = 0; i < cardapio.size(); i++) {
            System.out.println((i + 1) + ". " + cardapio.get(i).getNome() + " - R$ " + cardapio.get(i).getPreco());
        }
    }

    private static void inicializarCardapio() {
        List<Ingrediente> ingredientesPizza = List.of(new Ingrediente("Queijo", TipoIngrediente.LATICINIO),
                new Ingrediente("Molho de tomate", TipoIngrediente.VERDURA));
        List<Ingrediente> ingredientesBurguer = List.of(new Ingrediente("Carne", TipoIngrediente.CARNE),
                new Ingrediente("Pão", TipoIngrediente.GRAO));

        cardapio.add(new ItemCardapio("Pizza", 30.0, ingredientesPizza) {
            @Override
            public void exibirInfo() {
                System.out.println("Pizza: Queijo e molho de tomate - R$30,00");
            }
        });

        cardapio.add(new ItemCardapio("Hambúrguer", 20.0, ingredientesBurguer) {
            @Override
            public void exibirInfo() {
                System.out.println("Hambúrguer: Carne e pão - R$20,00");
            }
        });
    }
}
