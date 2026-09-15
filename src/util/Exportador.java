package util;

import exeption.ErroNaExportacao;

import model.Entity.Guilda;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class Exportador {

    public static void exportarRanking(List<Guilda> ranking) throws ErroNaExportacao {
        // FileWriter abre/cria o arquivo
        // BufferedWriter envolve o FileWriter para escrever com eficiência
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("ranking.txt"))) {

            String dataHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")); //pega a data que o arquivo foi criado, e o timeformatter formata para a nossa data do br

            bw.write("=== RANKING DO TORNEIO ===");
            bw.newLine();
            bw.write("Gerado em: " + dataHora);
            bw.newLine();
            bw.write("==========================");
            bw.newLine();

            int posicao = 1;
            for (var entry : ranking) {
                bw.write(posicao++ + "º " + entry.getNome() + " - " + entry.getReputacao() + " reputação");
                bw.newLine();
            }

            bw.write("==========================");
            bw.newLine();
            bw.write("Total dse times: " + ranking.size());
         // try-with-resources fecha o BufferedWriter automaticamente, mesmo se der erro
        } catch (IOException e) {
            throw new ErroNaExportacao(e.getMessage());
     }
    }
}

