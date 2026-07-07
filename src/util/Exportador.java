package util;

import Model.Time;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class Exportador {

    public static void exportarRanking(List<Map.Entry<Time, Integer>> ranking) throws IOException {
        // FileWriter abre/cria o arquivo
        // BufferedWriter envolve o FileWriter para escrever com eficiência
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("ranking.txt"))) {

            String dataHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));

            bw.write("=== RANKING DO TORNEIO ===");
            bw.newLine();
            bw.write("Gerado em: " + dataHora);
            bw.newLine();
            bw.write("==========================");
            bw.newLine();

            int posicao = 1;
            for (Map.Entry<Time, Integer> entry : ranking) {
                bw.write(posicao++ + "º " + entry.getKey().getNome() + " - " + entry.getValue() + " pontos");
                bw.newLine();
            }

            bw.write("==========================");
            bw.newLine();
            bw.write("Total dse times: " + ranking.size());
        }
        // try-with-resources fecha o BufferedWriter automaticamente, mesmo se der erro
    }
}
