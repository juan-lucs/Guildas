package model.Entity;

import enums.Classes;

public class AvtrMestre extends Aventureiro{
    // bah todos os poderes futuros do MestreAventureiro

    public AvtrMestre(String nome, int nivel, Classes classe) {
        super(nome, nivel, classe);
    }

    public AvtrMestre( String nome, int nivel, Classes classe, Guilda guilda) {
        super(nome, nivel, classe, guilda);
    }
}
