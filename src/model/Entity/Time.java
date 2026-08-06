package model.Entity;

import enums.Modalidade;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Time implements Serializable {
    private Long id;
    private String nome;
    private Modalidade modalidade;
    private Long timeId;

    public Time() {
    }
    public Time(Long id, String nome, Modalidade modalidade) {
        this.id = id;
        this.nome = nome;
        this.modalidade = modalidade;
    }
    public Time(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public void setJogador(Jogador jogador) {
        this.jogadores.add(jogador);
    }

    public Modalidade getModalidade() {
        return modalidade;
    }

    public void setModalidade(Modalidade modalidade) {
        this.modalidade = modalidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTime(Jogador jogador) {
        jogadores.add(jogador);
    }

    public Set<Jogador> getJogadores() {
        return new HashSet<>(jogadores); // CRIA UMA COPIA O SET E ENVIA, PARA PROTEÇÃO E BOA PRATICA
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Time time = (Time) o;
        return Objects.equals(getNome(), time.getNome());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getNome());
    }
}