import java.io.*;
import java.util.Scanner;

public class Projeto {

    public static void main(String[] args) throws FileNotFoundException {
        if (args.length == 6 || args.length == 8) { // verificar se é um numero válido de argumentos
            for (int i = 0; i < args.length; i = i + 2) {
                switch (args[i]){
                    case "-r":
                        String resoluçãoTemporal = args[i+1];
                        break;
                    case "-di":
                        String dataInicial = args[i+1];
                        break;
                    case "-df":
                        String dataFinal = args[i+1];
                        break;
                }
            }


            if (verificarExtensãoFicheiro(args[args.length - 2]).equals("csv")){
                String ficheiroEntrada = args[args.length - 2];
            } else System.out.println("Ficheiro de entrada inválido");

            if (verificarExtensãoFicheiro(args[args.length - 1]).equals("txt")){
                String ficheiroSaida = args[args.length - 1];

            } else System.out.println("Ficheiro de saida inválido");

        } else System.out.println("Quantidade de argumentos inválida");


    }
    public static String verificarExtensãoFicheiro (String nomeFicheiro){
        String ext = "";

        int i = nomeFicheiro.lastIndexOf('.');
        if (i > 0) {
            ext = nomeFicheiro.substring(i+1);
        }

        return ext;
    }

    public static void lerFicheiroCSV() throws FileNotFoundException {

        String line = "";
        String splitBy = ",";

        try {

            BufferedReader br = new BufferedReader(new FileReader("exemploRegistoNumerosCovid19.csv"));
            while ((line = br.readLine()) != null) {
                String[] data = line.split(splitBy);
                System.out.println("data: " + data[0] + " infetados: " + data[2]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
//teste
    }
}
