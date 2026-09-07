package model.Entity;

import java.io.Serializable;
import java.util.*;

public class Guilda implements Serializable {
    private int id;
    private String nome;
    private int level;
    private AvtrMestre mestre;
    private int reputacao;
    private Map<String, Aventureiro> aventureiros = new HashMap<>();

    public Guilda() {
    }

    public Guilda(int id, String nome, int level) {
        this.id = id;
        this.nome = nome;
        this.level = level;
    }

    public Guilda(String nome, int level) {
        this.nome = nome;
        this.level = level;
    }

    public Guilda(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public void adicionarAventureiro(Aventureiro aventureiro) {
        this.aventureiros.put(aventureiro.getNome(), aventureiro);
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

    public Set<String> getAventureiros() {
        return aventureiros.keySet(); // CRIA UMA COPIA O SET E ENVIA, PARA PROTEÇÃO E BOA PRATICA
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAventureiros(Map<String, Aventureiro> aventureiros) {
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
