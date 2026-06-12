package cinema;

/**
 * Classe Poltrona - Representa uma poltrona individual da sala
 * 
 * Cada poltrona pode estar em dois estados:
 * - Livre: disponível para compra
 * - Ocupada: já vendida (pela internet ou pelo totem)
 */
public class Poltrona {
    
    // Atributo que indica se a poltrona está ocupada
    private boolean ocupada;
    
    /**
     * Construtor - Inicializa a poltrona como livre
     */
    public Poltrona() {
        this.ocupada = false;
    }
    
    /**
     * Marca a poltrona como ocupada
     */
    public void ocupar() {
        this.ocupada = true;
    }
    
    /**
     * Verifica se a poltrona está livre
     * @return true se está livre, false se está ocupada
     */
    public boolean estaLivre() {
        return !this.ocupada;
    }
    
    /**
     * Verifica se a poltrona está ocupada
     * @return true se está ocupada, false se está livre
     */
    public boolean estaOcupada() {
        return this.ocupada;
    }
}
