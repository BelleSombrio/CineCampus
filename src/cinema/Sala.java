package cinema;

import java.util.Random;

/**
 * Classe Sala - Gerencia a sessão com as poltronas
 */
public class Sala {
    private Poltrona[][] matriz;
    private double[] precos;
    private int totalOcupadas;
    
    public Sala() {
        this.matriz = new Poltrona[10][10];
        this.precos = new double[10];
        this.totalOcupadas = 0;
        inicializar();
    }
    
    private void inicializar() {
        // Cria todas as poltronas
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                this.matriz[i][j] = new Poltrona();
            }
        }
        
        // Define preços por fila
        for (int i = 0; i < 2; i++) {
            this.precos[i] = 15.0; // Frente
        }
        for (int i = 2; i < 8; i++) {
            this.precos[i] = 25.0; // Meio
        }
        for (int i = 8; i < 10; i++) {
            this.precos[i] = 35.0; // VIP
        }
        
        // Ocupa 15 poltronas aleatoriamente
        Random random = new Random();
        int ocupadas = 0;
        while (ocupadas < 15) {
            int f = random.nextInt(10);
            int p = random.nextInt(10);
            if (this.matriz[f][p].estaLivre()) {
                this.matriz[f][p].ocupar();
                this.totalOcupadas++;
                ocupadas++;
            }
        }
    }
    
    public boolean validar(int fila, int poltrona) {
        if (fila < 0 || fila > 9 || poltrona < 0 || poltrona > 9) {
            return false;
        }
        return this.matriz[fila][poltrona].estaLivre();
    }
    
    public void ocupar(int fila, int poltrona) {
        this.matriz[fila][poltrona].ocupar();
        this.totalOcupadas++;
    }
    
    public double getPreco(int fila) {
        return this.precos[fila];
    }
    
    public double getOcupacao() {
        return (this.totalOcupadas * 100.0) / 100.0;
    }
    
    public int getTotalOcupadas() {
        return this.totalOcupadas;
    }
    
    public void exibirMapa() {
        System.out.println("\n   Mapa da Sala");
        System.out.println("   0 1 2 3 4 5 6 7 8 9");
        for (int i = 0; i < 10; i++) {
            System.out.print(" " + i + "  ");
            for (int j = 0; j < 10; j++) {
                System.out.print(this.matriz[i][j].estaLivre() ? "- " : "# ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
