package model.Entity;

import java.io.Serializable;
import java.util.Objects;

public class Missao implements Serializable {
    private Long id;
    private String nome;
    private int dificuldade;
    private String participantes;
    private Guilda guilda;
    private String equipeRecomendada;
    private String resultado;

    public Missao() {
    }

    public Missao(String nome, int dificuldade, String participantes, Guilda guilda, String equipeRecomendada, String resultado) {
        this.nome = nome;
        this.dificuldade = dificuldade;
        this.participantes = participantes;
        this.guilda = guilda;
        this.equipeRecomendada = equipeRecomendada;
        this.resultado = resultado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(int dificuldade) {
        this.dificuldade = dificuldade;
    }

    public String getParticipantes() {
        return participantes;
    }

    public void setParticipantes(String participantes) {
        this.participantes = participantes;
    }

    public Guilda getGuilda() {
        return guilda;
    }

    public void setGuilda(Guilda guilda) {
        this.guilda = guilda;
    }

    public String getEquipeRecomendada() {
        return equipeRecomendada;
    }

    public void setEquipeRecomendada(String equipeRecomendada) {
        this.equipeRecomendada = equipeRecomendada;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
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
