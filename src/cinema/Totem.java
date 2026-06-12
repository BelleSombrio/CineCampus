package cinema;

import java.util.Scanner;

/**
 * Classe Totem - Menu principal do sistema
 */
public class Totem {
    private Sala sala;
    private Bilheteria bilheteria;
    private Scanner scanner;
    
    public Totem() {
        this.sala = new Sala();
        this.bilheteria = new Bilheteria();
        this.scanner = new Scanner(System.in);
    }
    
    public void run() {
        System.out.println("\n===== CineCampus - Totem de Cinema =====\n");
        
        boolean ativo = true;
        while (ativo) {
            exibirMenu();
            System.out.print("Opcao: ");
            String op = this.scanner.nextLine().trim();
            
            switch (op) {
                case "1":
                    this.sala.exibirMapa();
                    break;
                case "2":
                    comprar();
                    break;
                case "3":
                    this.bilheteria.exibir();
                    break;
                case "0":
                    ativo = false;
                    encerrar();
                    break;
                default:
                    System.out.println("Opcao invalida!\n");
            }
        }
    }
    
    private void exibirMenu() {
        System.out.printf("Vendidos: %d | Bilheteria: R$ %.2f | Ocupacao: %.0f%%\n",
            this.bilheteria.getQuantidade(),
            this.bilheteria.getTotal(),
            this.sala.getOcupacao());
        System.out.println("\n[1] Ver mapa  [2] Comprar  [3] Resumo  [0] Sair\n");
    }
    
    private void comprar() {
        System.out.println("\n--- Compra de Ingresso ---\n");
        
        this.sala.exibirMapa();
        
        System.out.print("Fila (0-9): ");
        int fila = Integer.parseInt(this.scanner.nextLine());
        
        System.out.print("Poltrona (0-9): ");
        int poltrona = Integer.parseInt(this.scanner.nextLine());
        
        if (!this.sala.validar(fila, poltrona)) {
            System.out.println("Poltrona invalida ou ocupada!\n");
            return;
        }
        
        System.out.print("Tipo (1-Inteira / 2-Meia): ");
        int tipo = Integer.parseInt(this.scanner.nextLine());
        
        double preco = this.sala.getPreco(fila);
        String tipoStr;
        
        if (tipo == 1) {
            tipoStr = "Inteira";
        } else if (tipo == 2) {
            tipoStr = "Meia";
            preco = preco / 2.0;
        } else {
            System.out.println("Tipo invalido!\n");
            return;
        }
        
        System.out.printf("\nFila: %d | Poltrona: %d | %s | R$ %.2f\n", 
            fila, poltrona, tipoStr, preco);
        System.out.print("Confirma? (S/N): ");
        
        String conf = this.scanner.nextLine().trim().toUpperCase();
        if (!conf.equals("S")) {
            System.out.println("Cancelado!\n");
            return;
        }
        
        Ingresso ing = new Ingresso(fila, poltrona, tipoStr, preco);
        this.sala.ocupar(fila, poltrona);
        this.bilheteria.adicionar(ing);
        
        System.out.println("Compra realizada! Bom filme!\n");
    }
    
    private void encerrar() {
        double ocupacao = this.sala.getOcupacao();
        
        System.out.println("\n===== ENCERRANDO =====");
        System.out.printf("Ocupacao: %.0f%%\n", ocupacao);
        System.out.printf("Ingressos: %d | Total: R$ %.2f\n\n",
            this.bilheteria.getQuantidade(),
            this.bilheteria.getTotal());
        
        if (ocupacao >= 90) {
            System.out.println("Sessao Esgotada - Sucesso!");
        } else if (ocupacao >= 70) {
            System.out.println("Casa Cheia");
        } else if (ocupacao >= 40) {
            System.out.println("Sessao Mediana");
        } else {
            System.out.println("Sala Vazia - Precisa divulgar mais");
        }
        
        System.out.println("\nObrigado por usar o CineCampus!\n");
        this.scanner.close();
    }
}
