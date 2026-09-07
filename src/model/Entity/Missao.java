package model.Entity;

import enums.resultadoMissao;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

public class Missao implements Serializable {
    private int id;
    private String nome;
    private int dificuldade;
    private Map<String, Aventureiro> participantes;
    private Guilda guilda;

    private resultadoMissao resultado;

    public Missao() {
    }

    public Missao(String nome, int dificuldade, Map<String, Aventureiro> participantes, Guilda guilda, resultadoMissao resultado) {
        this.nome = nome;
        this.dificuldade = dificuldade;
        this.participantes = participantes;
        this.guilda = guilda;
        this.resultado = resultado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Collection<Aventureiro> getParticipantes() {
        return participantes.values();
    }

    public void setParticipantes(Map<String, Aventureiro> participantes) {
        this.participantes = participantes;
    }

    public int getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(int dificuldade) {
        this.dificuldade = dificuldade;
    }

    public Guilda getGuilda() {
        return guilda;
    }

    public void setGuilda(Guilda guilda) {
        this.guilda = guilda;
    }

    public resultadoMissao getResultado() {
        return resultado;
    }

    public void setResultado(resultadoMissao resultado) {
        this.resultado = resultado;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Missao missao = (Missao) o;
        return Objects.equals(getId(), missao.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
