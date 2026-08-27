package model.Entity;

import enums.Classes;

import java.io.Serializable;
import java.util.Objects;


public class Aventureiro implements Comparable<Aventureiro>, Serializable {
    private Long id;
    private String nome;
    private int nivel;
    private Classes classe;
    private Guilda guilda;

    public Aventureiro() {
    }


    public Aventureiro(String nome, int nivel, Classes classe) {
        this.classe = classe;
        this.nome = nome;
        this.nivel = nivel;
    }

    public Aventureiro(String nome, int nivel, Classes classe, Guilda guilda) {
        this.nome = nome;
        this.nivel = nivel;
        this.classe = classe;
        this.guilda = guilda;
    }

    public Aventureiro(Long id, String nome, int nivel, Classes classe, Guilda guilda) {
        this.id = id;
        this.nome = nome;
        this.nivel = nivel;
        this.classe = classe;
        this.guilda = guilda;
    }

    public Classes getclasse() {
        return classe;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Guilda getGuilda() {
        return guilda;
    }

    public void setGuilda(Guilda guilda) {
        this.guilda = guilda;
    }

    public void setclasse(Classes classe) {
        this.classe = classe;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getnivel() {
        return nivel;
    }

    public void setnivel(int nivel) {
        this.nivel = nivel;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Aventureiro that = (Aventureiro) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    // Ordenação: por nome
    @Override
    public int compareTo(Aventureiro outro) {
        return this.nome.compareTo(outro.nome);
    }

}
