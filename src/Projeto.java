import java.io.*;
import java.util.Scanner;

public class Projeto {

    public static void main(String[] args) throws FileNotFoundException {

        //LerFicheiroCSV();
        int numeroDeLinhas = ContarLinhasDoFicheiro();

        String[] listaDeDatas = new String[numeroDeLinhas];
        int[] listaDeCasosNaoInfetados = new int[numeroDeLinhas];
        listaDeCasosNaoInfetados = PreencherValoresDosDadosPrimeiraFase(1,listaDeCasosNaoInfetados);
        int[] listaDeInfetados = new int[numeroDeLinhas];
        listaDeInfetados = PreencherValoresDosDadosPrimeiraFase(2,listaDeInfetados);
        int[] listaDeHospitalizados = new int[numeroDeLinhas];
        listaDeHospitalizados = PreencherValoresDosDadosPrimeiraFase(3,listaDeHospitalizados);
        int[] listaDeInternadosUCI = new int[numeroDeLinhas];
        listaDeInternadosUCI = PreencherValoresDosDadosPrimeiraFase(4,listaDeInternadosUCI);
        int[] listaDeMortes = new int[numeroDeLinhas];
        listaDeMortes = PreencherValoresDosDadosPrimeiraFase(5,listaDeMortes);

        //por num modulo???
        if (args.length == 6 || args.length == 8) { // verificar se é um numero válido de argumentos
            for (int i = 0; i < args.length; i = i + 2) {
                switch (args[i]){
                    case "-r":
                        String resolucaoTemporal = args[i+1];
                        break;
                    case "-di":
                        String dataInicial = args[i+1];
                        break;
                    case "-df":
                        String dataFinal = args[i+1];
                        break;
                }
            }

            //LerFicheiroCSV();
            ContarLinhasDoFicheiro();

            if (VerificarExtensãoFicheiro(args[args.length - 2]).equals("csv")){
                String ficheiroEntrada = args[args.length - 2];
            } else System.out.println("Ficheiro de entrada inválido");

            if (VerificarExtensãoFicheiro(args[args.length - 1]).equals("txt")){
                String ficheiroSaida = args[args.length - 1];

            } else System.out.println("Ficheiro de saida inválido");

        } else System.out.println("Quantidade de argumentos inválida");


    }
    public static int[] PreencherValoresDosDadosPrimeiraFase(int tipoDeDado, int[] listaDeDados) throws FileNotFoundException {

        int count = 0;
        String splitBy = ",";
        Scanner fin = new Scanner(new File("exemploRegistoNumerosCovid19.csv"));
        fin.nextLine();
        while (fin.hasNextLine()) {
            String line = fin.nextLine();
            String[] dados = line.split(splitBy);
            count++;
            PreencherValorDosDadosSegundaFase(tipoDeDado, dados, listaDeDados,count);
                }

        return listaDeDados;
    }
    public static void PreencherValorDosDadosSegundaFase(int tiposDeDados, String[] dados, int[] listaDeDados, int posicao){
        listaDeDados[posicao] = Integer.parseInt(dados[tiposDeDados]);
        System.out.println(listaDeDados[posicao]);
        }


    public static String[] PreencherListaDeDatas(int tipoDeDados,String[] dados, String[] listaDeDatas) {



        return dados;
    }

    public static int ContarLinhasDoFicheiro() throws FileNotFoundException {

        Scanner fin = new Scanner(new File("exemploRegistoNumerosCovid19.csv"));
        int counter = 0;

        while (fin.hasNextLine()){
            counter++;
            fin.nextLine();
        }
        fin.close();
        return counter;
    }

    public static String VerificarExtensãoFicheiro(String nomeFicheiro){
        String ext = "";

        int i = nomeFicheiro.lastIndexOf('.');
        if (i > 0) {
            ext = nomeFicheiro.substring(i+1);
        }

        return ext;
    }

   /* public static void LerFicheiroCSV() throws FileNotFoundException {

        String line = "";
        String splitBy = ",";

        try {

            BufferedReader br = new BufferedReader(new FileReader("exemploRegistoNumerosCovid19.csv"));
            while ((line = br.readLine()) != null) {
                String[] dados = line.split(splitBy);

                System.out.println("data: " + dados[0] + " infetados: " + dados[2]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
//teste
    }
*/}
