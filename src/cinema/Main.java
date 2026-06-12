package cinema;

/**
 * Classe Main - Ponto de entrada da aplicação CineCampus
 * 
 * Responsável por inicializar o totem de autoatendimento
 * e iniciar o loop principal do sistema.
 */
public class Main {
    public static void main(String[] args) {
        // Cria uma nova instância do totem
        Totem totem = new Totem();
        
        // Inicia o sistema
        totem.run();
    }
}
