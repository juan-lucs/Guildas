package model.Entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Guilda implements Serializable {
    private Long id;
    private String nome;
    private int level;
    private AvtrMestre mestre;
    private int reputacao;
    private Set<Aventureiro> aventureiros = new HashSet<>();

    public Guilda() {
    }

    public Guilda(Long id, String nome, int level) {
        this.id = id;
        this.nome = nome;
        this.level = level;
    }

    public Guilda(String nome, int level) {
        this.nome = nome;
        this.level = level;
    }

    public Guilda(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public void setAventureiro(Aventureiro aventureiro) {
        this.aventureiros.add(aventureiro);
    }

    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public AvtrMestre getMestre() {
        return mestre;
    }

    public void setMestre(AvtrMestre mestre) {
        this.mestre = mestre;
    }

    public int getReputacao() {
        return reputacao;
    }

    public void setReputacao(int reputacao) {
        this.reputacao = reputacao;
    }

    public Set<Aventureiro> getAventureiros() {
        return new HashSet<>(aventureiros); // CRIA UMA COPIA O SET E ENVIA, PARA PROTEÇÃO E BOA PRATICA
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAventureiros(Set<Aventureiro> aventureiros) {
        this.aventureiros = aventureiros;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Guilda guilda = (Guilda) o;
        return Objects.equals(getNome(), guilda.getNome());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getNome());
    }

}
