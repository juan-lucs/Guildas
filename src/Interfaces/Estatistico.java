package Interfaces;

public interface Estatistico {
    String getTotalPartidas();

    default void getResumo() {
        System.out.println("Total das partidas: " + getTotalPartidas());
    }
}
