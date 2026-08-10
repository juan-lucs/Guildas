package model.dao;

import model.Entity.Jogador;
import model.Entity.Time;

import java.util.List;

public interface JogadorDao {
    void insert(Jogador arg);
    void update(Jogador arg);
    Jogador findById(Integer id);
    List<Jogador> findAllOnATeam(Time time);
}
