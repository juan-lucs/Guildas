package model.Entity;

import java.io.Serializable;
import java.util.Objects;


public class Jogador implements Comparable<Jogador>, Serializable {
    private long id;
    private String nome;
    private int idade;
    private String posicao;
    private Time time;

    public Jogador() {
    }

    public Jogador(String nome, int idade, String posicao) {
        this.posicao = posicao;
        this.nome = nome;
        this.idade = idade;
    }

    public String getPosicao() {
        return posicao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Jogador jogador = (Jogador) o;
        return getId() == jogador.getId() && Objects.equals(getNome(), jogador.getNome()) && Objects.equals(getPosicao(), jogador.getPosicao());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getPosicao());
    }

    // Ordenação: por nome
    @Override
    public int compareTo(Jogador outro) {
        return this.nome.compareTo(outro.nome);
    }

}
