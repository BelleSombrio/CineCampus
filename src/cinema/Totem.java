package cinema;

import java.util.Scanner;

/**
 * Classe Totem - Orquestrador principal do sistema CineCampus
 * 
 * Responsabilidades:
 * - Exibir menu interativo
 * - Gerenciar fluxo de compra de ingressos
 * - Coordenar entre Sala e Bilheteria
 * - Manter loop principal da aplicação
 */
public class Totem {
    
    // Atributos
    private Sala sala;
    private Bilheteria bilheteria;
    private Scanner scanner;
    private boolean ativo;
    
    /**
     * Construtor - Inicializa o totem
     */
    public Totem() {
        this.sala = new Sala();
        this.bilheteria = new Bilheteria();
        this.scanner = new Scanner(System.in);
        this.ativo = true;
    }
    
    /**
     * Executa o loop principal do totem
     */
    public void run() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║   BEM-VINDO AO CINECAMPUS 🎬          ║");
        System.out.println("║  Totem de Autoatendimento de Cinema   ║");
        System.out.println("╚════════════════════════════════════════╝\n");
        
        while (this.ativo) {
            exibirCabecalho();
            exibirMenu();
            
            System.out.print("Opção: ");
            String entrada = this.scanner.nextLine().trim();
            
            procesarOpcao(entrada);
        }
    }
    
    /**
     * Exibe o cabeçalho com informações em tempo real
     */
    private void exibirCabecalho() {
        System.out.println("===== CineCampus - Totem de Autoatendimento =====");
        System.out.printf("Ingressos vendidos: %d | Bilheteria: R$ %.2f | Ocupacao: %.0f%%%n",
            this.bilheteria.getQuantidadeVendida(),
            this.bilheteria.getTotalVendido(),
            this.sala.calcularOcupacao());
        System.out.println("===================================================");
    }
    
    /**
     * Exibe o menu de opções
     */
    private void exibirMenu() {
        System.out.println("\nMenu:");
        System.out.println("  [1] Ver mapa da sala");
        System.out.println("  [2] Comprar ingresso");
        System.out.println("  [3] Ver resumo de vendas");
        System.out.println("  [0] Encerrar atendimento");
        System.out.println();
    }
    
    /**
     * Processa a opção selecionada pelo usuário
     */
    private void procesarOpcao(String opcao) {
        switch (opcao) {
            case "1":
                this.sala.exibirMapa();
                break;
            case "2":
                executarCompra();
                break;
            case "3":
                this.bilheteria.obterResumo();
                break;
            case "0":
                encerrar();
                break;
            default:
                System.out.println("\n⚠️  Opção inválida! Digite 0, 1, 2 ou 3.\n");
        }
    }
    
    /**
     * Executa o fluxo completo de compra de um ingresso
     */
    private void executarCompra() {
        // Verifica se a sala está cheia
        if (this.sala.estaCompleta()) {
            System.out.println("\n❌ Sessão completamente vendida! Desculpe.\n");
            return;
        }
        
        System.out.println("\n--- COMPRA DE INGRESSO ---\n");
        
        this.sala.exibirMapa();
        
        // Entrada: Fila
        System.out.print("Fila (0-9): ");
        int fila = lerInteiro();
        
        // Entrada: Poltrona
        System.out.print("Poltrona (0-9): ");
        int poltrona = lerInteiro();
        
        // Valida posição
        String erro = this.sala.validarPoltrona(fila, poltrona);
        if (erro != null) {
            System.out.println("\n❌ " + erro + "\n");
            return;
        }
        
        // Determina tipo de ingresso
        System.out.print("Tipo (1-Inteira / 2-Meia): ");
        int tipoOpcao = lerInteiro();
        
        String tipo;
        double preco;
        
        if (tipoOpcao == 1) {
            tipo = "Inteira";
            preco = this.sala.precoFila(fila);
        } else if (tipoOpcao == 2) {
            tipo = "Meia";
            preco = this.sala.precoFila(fila) / 2.0;
        } else {
            System.out.println("\n❌ Tipo inválido!\n");
            return;
        }
        
        // Exibe informações e pede confirmação
        String classificacao = this.sala.classificacaoFila(fila);
        System.out.println("\n--- RESUMO DA COMPRA ---");
        System.out.println("Fila: " + fila + " (" + classificacao + ")");
        System.out.println("Poltrona: " + poltrona);
        System.out.println("Tipo: " + tipo);
        System.out.printf("Preço: R$ %.2f\n", preco);
        System.out.print("Confirma a compra? (S/N): ");
        
        String confirmacao = this.scanner.nextLine().trim().toUpperCase();
        
        if (!confirmacao.equals("S")) {
            System.out.println("\n❌ Compra cancelada.\n");
            return;
        }
        
        // Realiza a compra
        Ingresso ingresso = new Ingresso(fila, poltrona, tipo, preco);
        this.sala.ocuparPoltrona(fila, poltrona);
        this.bilheteria.adicionarVenda(ingresso);
        
        System.out.println("\n✅ Compra realizada com sucesso!");
        System.out.println("🎬 Bom filme!\n");
    }
    
    /**
     * Exibe o resumo de vendas
     */
    private void exibirResumo() {
        this.bilheteria.obterResumo();
    }
    
    /**
     * Encerra o atendimento e classifica a sessão
     */
    private void encerrar() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║   ENCERRANDO SESSÃO...                 ║");
        System.out.println("╚════════════════════════════════════════╝\n");
        
        // Exibe resumo final
        double ocupacao = this.sala.calcularOcupacao();
        System.out.printf("Ocupação final: %.0f%%\n", ocupacao);
        System.out.printf("Ingressos vendidos: %d\n", this.bilheteria.getQuantidadeVendida());
        System.out.printf("Faturamento: R$ %.2f\n\n", this.bilheteria.getTotalVendido());
        
        // Classifica a sessão
        String classificacao;
        if (ocupacao >= 90) {
            classificacao = "🎉 Sessão Esgotada — Sucesso de Bilheteria!";
        } else if (ocupacao >= 70) {
            classificacao = "🎬 Casa Cheia";
        } else if (ocupacao >= 40) {
            classificacao = "🎭 Sessão Mediana";
        } else {
            classificacao = "📢 Sala Vazia — precisa divulgar mais";
        }
        
        System.out.println("Classificação: " + classificacao);
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║   Obrigado por usar o CineCampus!     ║");
        System.out.println("╚════════════════════════════════════════╝\n");
        
        this.ativo = false;
        this.scanner.close();
    }
    
    /**
     * Método auxiliar para ler um inteiro do usuário com tratamento de erro
     */
    private int lerInteiro() {
        try {
            String entrada = this.scanner.nextLine().trim();
            return Integer.parseInt(entrada);
        } catch (NumberFormatException e) {
            return -1; // Retorna -1 se não for um número válido
        }
    }
}
