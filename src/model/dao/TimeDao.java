package model.dao;

import enums.Modalidade;
import exeption.JogadorDuplicadoException;
import exeption.TimeNaoEncontradoException;
import model.Entity.Jogador;
import model.Entity.Time;

import java.util.List;

public interface TimeDao {
    void insert(String nometime, Modalidade modalidade);
    void updatePontos(Time arg);
    Time findByNome(String nome) throws TimeNaoEncontradoException;
    List<Time> findAll();
    void pesquisarJogador(Time t, Jogador j) throws JogadorDuplicadoException;
    List<String> findAllNomes();
}

