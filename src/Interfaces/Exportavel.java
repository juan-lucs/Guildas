package Interfaces;

public interface Exportavel {
    // Método abstrato — cada classe implementa do seu jeito
    String getDados();

    // Default method — já vem pronto, mas pode ser sobrescrito se quiser
    default void exportar() {
        System.out.println("=== EXPORTANDO ===");
        System.out.println(getDados()); // chama o método que a classe implementou
        System.out.println("==================");
    }
}
