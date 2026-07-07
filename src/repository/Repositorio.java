package repository;

import java.util.ArrayList;
import java.util.List;

public class Repositorio<T> {
    private List<T> itens = new ArrayList<>(); // Aqui eu uso um Generics T, assim posso criar uma lista de qualquer tipo, seja uma lista de jogadores que comporta nome, idade de posicao ou também uma lista de times com nomedotime e a modalidade.
    // Para eu usar do melhor jeito eu preciso fazer assim: times.adicionar(new Time("Flamengo", Modalidade.FUTEBOL));

    public void adicionar(T item) {
        itens.add(item);
    }

    public T buscarPorIndice(int i) {
        return itens.get(i);
    }

    public void remover(T item) {
        itens.remove(item);
    }

    public List<T> listarTodos() {
        return new ArrayList<>(itens);
    }
    public int tamanho() {
        itens.size();
        return itens.size();
    }
}
