package view;

import controller.ProdutoController;
import model.Produto;
import java.util.List;
import java.util.Scanner;

public class MainView {
    private final ProdutoController controller = new ProdutoController();

    public void iniciar() {
        Scanner sc = new Scanner(System.in);
        int opc;
        do {
            System.out.println("\n=== MENU PRODUTOS ===");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Excluir");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");
            opc = sc.nextInt(); sc.nextLine();

            switch (opc) {
                case 1 -> {
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Preço: ");
                    double preco = sc.nextDouble(); sc.nextLine();
                    System.out.print("Categoria: ");
                    String cat = sc.nextLine();
                    controller.cadastrarProduto(new Produto(nome, preco, cat));
                }
                case 2 -> {
                    List<Produto> produtos = controller.listarProdutos();
                    produtos.forEach(System.out::println);
                }
                case 3 -> {
                    System.out.print("ID do produto: ");
                    String id = sc.nextLine();
                    System.out.print("Novo nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Novo preço: ");
                    double preco = sc.nextDouble(); sc.nextLine();
                    System.out.print("Nova categoria: ");
                    String cat = sc.nextLine();
                    controller.atualizarProduto(id, new Produto(nome, preco, cat));
                }
                case 4 -> {
                    System.out.print("ID do produto: ");
                    String id = sc.nextLine();
                    controller.deletarProduto(id);
                }
            }
        } while (opc != 0);
        sc.close();
    }
}
