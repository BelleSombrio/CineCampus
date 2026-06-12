package cinema;

import java.util.Random;

/**
 * Classe Sala - Gerencia a sessão de cinema com as poltronas
 * 
 * Responsabilidades:
 * - Criar e manter a matriz 10x10 de poltronas
 * - Ocupar poltronas com 15 vendas da internet no início
 * - Validar posições e disponibilidade
 * - Calcular ocupação e fornecer preços
 * - Exibir mapa visual da sala
 */
public class Sala {
    
    // Constantes
    private static final int FILAS = 10;
    private static final int POLTRONAS_POR_FILA = 10;
    private static final int POLTRONAS_INICIAIS = 15;
    
    // Atributos da sala
    private Poltrona[][] matriz; // Matriz 10x10 de poltronas
    private double[] precosPorFila; // Vetor com preço de cada fila
    private int totalOcupadas; // Contador de poltronas vendidas
    
    /**
     * Construtor - Inicializa a sala
     */
    public Sala() {
        this.matriz = new Poltrona[FILAS][POLTRONAS_POR_FILA];
        this.precosPorFila = new double[FILAS];
        this.totalOcupadas = 0;
        
        inicializar();
    }
    
    /**
     * Inicializa a sala:
     * 1. Cria todas as poltronas (livres)
     * 2. Define os preços por fila
     * 3. Ocupa 15 poltronas aleatoriamente (vendas da internet)
     */
    private void inicializar() {
        // Cria todas as poltronas (inicialmente livres)
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < POLTRONAS_POR_FILA; j++) {
                this.matriz[i][j] = new Poltrona();
            }
        }
        
        // Define preços por fila
        // Filas 0-1: R$ 15,00 (frente)
        for (int i = 0; i < 2; i++) {
            this.precosPorFila[i] = 15.0;
        }
        
        // Filas 2-7: R$ 25,00 (meio)
        for (int i = 2; i < 8; i++) {
            this.precosPorFila[i] = 25.0;
        }
        
        // Filas 8-9: R$ 35,00 (VIP)
        for (int i = 8; i < 10; i++) {
            this.precosPorFila[i] = 35.0;
        }
        
        // Ocupa 15 poltronas aleatoriamente
        ocuparAleatoriamente();
    }
    
    /**
     * Ocupa aleatoriamente 15 poltronas da sala
     * (vendas feitas pela internet antes do totem abrir)
     */
    private void ocuparAleatoriamente() {
        Random random = new Random();
        int ocupadas = 0;
        
        while (ocupadas < POLTRONAS_INICIAIS) {
            int fila = random.nextInt(FILAS);
            int poltrona = random.nextInt(POLTRONAS_POR_FILA);
            
            // Só ocupa se a poltrona estiver livre
            if (this.matriz[fila][poltrona].estaLivre()) {
                this.matriz[fila][poltrona].ocupar();
                this.totalOcupadas++;
                ocupadas++;
            }
        }
    }
    
    /**
     * Valida se uma posição (fila, poltrona) é válida e está livre
     * @param fila número da fila
     * @param poltrona número da poltrona
     * @return mensagem de validação ou null se válida
     */
    public String validarPoltrona(int fila, int poltrona) {
        // Verifica se fila está nos limites
        if (fila < 0 || fila >= FILAS) {
            return "Erro: Fila fora dos limites (0-9)";
        }
        
        // Verifica se poltrona está nos limites
        if (poltrona < 0 || poltrona >= POLTRONAS_POR_FILA) {
            return "Erro: Poltrona fora dos limites (0-9)";
        }
        
        // Verifica se a poltrona está livre
        if (this.matriz[fila][poltrona].estaOcupada()) {
            return "Erro: Poltrona já está vendida!";
        }
        
        // Tudo certo
        return null;
    }
    
    /**
     * Ocupa uma poltrona específica
     * @param fila número da fila
     * @param poltrona número da poltrona
     */
    public void ocuparPoltrona(int fila, int poltrona) {
        this.matriz[fila][poltrona].ocupar();
        this.totalOcupadas++;
    }
    
    /**
     * Retorna o preço base da fila
     * @param fila número da fila
     * @return preço da fila
     */
    public double precoFila(int fila) {
        return this.precosPorFila[fila];
    }
    
    /**
     * Retorna a classificação da fila (Frente, Meio ou VIP)
     * @param fila número da fila
     * @return classificação da fila
     */
    public String classificacaoFila(int fila) {
        if (fila < 2) {
            return "Frente";
        } else if (fila < 8) {
            return "Meio";
        } else {
            return "VIP";
        }
    }
    
    /**
     * Calcula a taxa de ocupação da sala (%)
     * @return percentual de ocupação
     */
    public double calcularOcupacao() {
        int totalPoltronas = FILAS * POLTRONAS_POR_FILA;
        return (this.totalOcupadas * 100.0) / totalPoltronas;
    }
    
    /**
     * Retorna o total de poltronas ocupadas
     */
    public int getTotalOcupadas() {
        return this.totalOcupadas;
    }
    
    /**
     * Verifica se a sala está completa (100% ocupação)
     */
    public boolean estaCompleta() {
        return this.totalOcupadas >= (FILAS * POLTRONAS_POR_FILA);
    }
    
    /**
     * Exibe o mapa visual da sala no console
     * "-" = poltrona livre
     * "#" = poltrona ocupada
     */
    public void exibirMapa() {
        System.out.println();
        System.out.println("   Tela do Cinema");
        System.out.println("    0 1 2 3 4 5 6 7 8 9");
        
        for (int i = 0; i < FILAS; i++) {
            System.out.print(" " + i + "  ");
            
            for (int j = 0; j < POLTRONAS_POR_FILA; j++) {
                if (this.matriz[i][j].estaLivre()) {
                    System.out.print("- ");
                } else {
                    System.out.print("# ");
                }
            }
            
            System.out.println();
        }
        
        System.out.println();
    }
}
