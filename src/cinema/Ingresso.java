package cinema;

/**
 * Classe Ingresso - Representa um ingresso vendido
 */
public class Ingresso {
    private int fila;
    private int poltrona;
    private String tipo;
    private double preco;
    
    public Ingresso(int fila, int poltrona, String tipo, double preco) {
        this.fila = fila;
        this.poltrona = poltrona;
        this.tipo = tipo;
        this.preco = preco;
    }
    
    public int getFila() {
        return this.fila;
    }
    
    public int getPoltrona() {
        return this.poltrona;
    }
    
    public String getTipo() {
        return this.tipo;
    }
    
    public double getPreco() {
        return this.preco;
    }
}
