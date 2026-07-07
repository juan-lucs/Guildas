package Model;

import java.util.Objects;

public class Jogador implements Comparable<Jogador> {
    private String nome;
    private int idade;
    private String posição;

    public String getPosição() {
        return posição;
    }

    public void setPosição(String posição) {
        this.posição = posição;
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
        this.posição = posição;
        this.nome = nome;
        this.idade = idade;
    }

    // Ordenação: por nome alfabético
    @Override
    public int compareTo(Jogador outro) {
        return this.nome.compareTo(outro.nome);
    }

}
