package Model;

import enums.Modalidade;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Time {
    private String nome;
    private Modalidade modalidade;
    private Set<Jogador> jogadores = new HashSet<>(); // USEI SET PARA NAO REPETIR OS JOGADORES

    public Time(String nome, Modalidade modalidade) {
        this.nome = nome;
        this.modalidade = modalidade;
    } // CONSTRUTOR PADRÃO

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