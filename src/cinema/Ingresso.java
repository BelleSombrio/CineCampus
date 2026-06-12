package cinema;

/**
 * Classe Ingresso - Representa um ingresso vendido
 * 
 * Armazena todas as informações de uma venda:
 * - Localização (fila e poltrona)
 * - Tipo (inteira ou meia-entrada)
 * - Preço final
 */
public class Ingresso {
    
    // Atributos privados do ingresso
    private int fila;
    private int poltrona;
    private String tipo; // "Inteira" ou "Meia"
    private double preco;
    
    /**
     * Construtor do Ingresso
     * @param fila número da fila (0-9)
     * @param poltrona número da poltrona (0-9)
     * @param tipo tipo de ingresso ("Inteira" ou "Meia")
     * @param preco preço final do ingresso
     */
    public Ingresso(int fila, int poltrona, String tipo, double preco) {
        this.fila = fila;
        this.poltrona = poltrona;
        this.tipo = tipo;
        this.preco = preco;
    }
    
    // ==================== GETTERS ====================
    
    /**
     * Retorna a fila do ingresso
     */
    public int getFila() {
        return this.fila;
    }
    
    /**
     * Retorna a poltrona do ingresso
     */
    public int getPoltrona() {
        return this.poltrona;
    }
    
    /**
     * Retorna o tipo de ingresso
     */
    public String getTipo() {
        return this.tipo;
    }
    
    /**
     * Retorna o preço do ingresso
     */
    public double getPreco() {
        return this.preco;
    }
    
    /**
     * Retorna uma representação em string do ingresso
     */
    @Override
    public String toString() {
        return "Fila " + this.fila + ", Poltrona " + this.poltrona + 
               " - " + this.tipo + " (R$ " + String.format("%.2f", this.preco) + ")";
    }
}
