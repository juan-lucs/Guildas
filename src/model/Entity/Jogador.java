package model.Entity;

import java.io.Serializable;
import java.util.Objects;


public class Jogador implements Comparable<Jogador>, Serializable {
    private long id;
    private String nome;
    private int idade;
    private String posicao;


    private Time time;

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

    public void setPosicao(String posição) {
        this.posicao = posição;
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
        return Objects.equals(getNome(), jogador.getNome()) && Objects.equals(getPosição(), jogador.getPosição());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNome(), getPosição());
    }

    public Jogador(String posição, String nome, int idade) {
        this.posicao = posição;
        this.nome = nome;
        this.idade = idade;
    }

    // Ordenação: por nome
    @Override
    public int compareTo(Jogador outro) {
        return this.nome.compareTo(outro.nome);
    }

}
