package cinema;

/**
 * Classe Bilheteria - Controla as vendas e arrecadação do totem
 * 
 * Responsabilidades:
 * - Registrar cada ingresso vendido
 * - Calcular o total de vendas em reais
 * - Contar quantidade de ingressos vendidos
 * - Gerar relatório de vendas
 */
public class Bilheteria {
    
    // Constante para tamanho máximo de ingressos
    private static final int CAPACIDADE_MAXIMA = 100;
    
    // Atributos
    private Ingresso[] ingressos; // Vetor para armazenar ingressos vendidos
    private int quantidade; // Quantidade de ingressos vendidos
    private double totalVendido; // Total em reais
    
    /**
     * Construtor - Inicializa a bilheteria vazia
     */
    public Bilheteria() {
        this.ingressos = new Ingresso[CAPACIDADE_MAXIMA];
        this.quantidade = 0;
        this.totalVendido = 0.0;
    }
    
    /**
     * Adiciona um ingresso à bilheteria
     * @param ingresso o ingresso a ser adicionado
     * @return true se foi adicionado com sucesso, false se está cheia
     */
    public boolean adicionarVenda(Ingresso ingresso) {
        if (this.quantidade >= CAPACIDADE_MAXIMA) {
            return false;
        }
        
        this.ingressos[this.quantidade] = ingresso;
        this.quantidade++;
        this.totalVendido += ingresso.getPreco();
        
        return true;
    }
    
    /**
     * Retorna o total vendido em reais
     */
    public double getTotalVendido() {
        return this.totalVendido;
    }
    
    /**
     * Retorna a quantidade de ingressos vendidos
     */
    public int getQuantidadeVendida() {
        return this.quantidade;
    }
    
    /**
     * Retorna um ingresso específico pelo índice
     */
    public Ingresso getIngresso(int indice) {
        if (indice >= 0 && indice < this.quantidade) {
            return this.ingressos[indice];
        }
        return null;
    }
    
    /**
     * Exibe o resumo de vendas no console
     */
    public void obterResumo() {
        System.out.println("\n====== RESUMO DE VENDAS DO TOTEM ======");
        System.out.println("Total de ingressos vendidos: " + this.quantidade);
        System.out.println("Faturamento total: R$ " + String.format("%.2f", this.totalVendido));
        System.out.println();
        
        if (this.quantidade == 0) {
            System.out.println("Nenhum ingresso foi vendido ainda.");
        } else {
            System.out.println("Ingressos vendidos:");
            for (int i = 0; i < this.quantidade; i++) {
                System.out.println("  " + (i + 1) + ". " + this.ingressos[i].toString());
            }
        }
        
        System.out.println("========================================\n");
    }
}
