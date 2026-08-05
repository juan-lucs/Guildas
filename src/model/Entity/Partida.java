package model.Entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Partida implements Serializable {
    private Long Id;
    private Time time1;
    private Time time2;
    private LocalDate dataPartida;
    private Map<Time, Integer> placar = new HashMap<>(); // placar de UMA partida

    public Partida(Time time1, Time time2, LocalDate dataPartida) {
        this.time1 = time1;
        this.time2 = time2;
        this.dataPartida = dataPartida;
    }
    public LocalDate getDataPartida() {
        return dataPartida;
    }

    public void setDataPartida(LocalDate dataPartida) {
        this.dataPartida = dataPartida;
    }

    public Time getTime1() {
        return time1;
    }

    public void setTime1(Time time1) {
        this.time1 = time1;
    }

    public Time getTime2() {
        return time2;
    }

    public void setTime2(Time time2) {
        this.time2 = time2;
    }

    public void setPlacar(Time nome, int pontos) {
        placar.put(nome, pontos);
    }
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
    public Map<Time, Integer> getPlacar() {
        return new HashMap<>(placar);
    }
}


