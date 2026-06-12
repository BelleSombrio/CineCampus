package cinema;

/**
 * Classe Bilheteria - Controla as vendas
 */
public class Bilheteria {
    private Ingresso[] ingressos;
    private int quantidade;
    private double total;
    
    public Bilheteria() {
        this.ingressos = new Ingresso[100];
        this.quantidade = 0;
        this.total = 0.0;
    }
    
    public void adicionar(Ingresso ingresso) {
        this.ingressos[this.quantidade] = ingresso;
        this.quantidade++;
        this.total += ingresso.getPreco();
    }
    
    public double getTotal() {
        return this.total;
    }
    
    public int getQuantidade() {
        return this.quantidade;
    }
    
    public void exibir() {
        System.out.println("\n=== RESUMO DE VENDAS ===");
        System.out.println("Ingressos: " + this.quantidade);
        System.out.printf("Total: R$ %.2f\n", this.total);
        
        if (this.quantidade > 0) {
            System.out.println("\nIngressos vendidos:");
            for (int i = 0; i < this.quantidade; i++) {
                Ingresso ing = this.ingressos[i];
                System.out.printf("  %d. Fila %d, Poltrona %d - %s (R$ %.2f)\n",
                    i + 1, ing.getFila(), ing.getPoltrona(), ing.getTipo(), ing.getPreco());
            }
        }
        System.out.println("========================\n");
    }
}
