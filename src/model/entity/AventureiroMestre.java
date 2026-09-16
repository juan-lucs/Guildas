package model.entity;

import enums.Classes;

public class AventureiroMestre extends Aventureiro{
    // bah todos os poderes futuros do MestreAventureiro

    public AventureiroMestre(String nome, int nivel, Classes classe) {
        super(nome, nivel, classe);
    }

    public AventureiroMestre( String nome, int nivel, Classes classe, Guilda guilda) {
        super(nome, nivel, classe, guilda);
    }
}
