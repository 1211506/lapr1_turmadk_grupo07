import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Projeto {

    static final SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
    static final DateFormat format2 = new SimpleDateFormat("EEEE");

    public static void main(String[] args) throws FileNotFoundException, ParseException {

        int numeroDeLinhas = ContarLinhasDoFicheiro() - 1;

        Date[] listaDeDatas = new Date[numeroDeLinhas];
        listaDeDatas = PreencherListaDeDatas(listaDeDatas);
        int[] listaDeCasosNaoInfetados = new int[numeroDeLinhas];
        listaDeCasosNaoInfetados = PreencherValoresDosDados(1, listaDeCasosNaoInfetados);
        int[] listaDeInfetados = new int[numeroDeLinhas];
        listaDeInfetados = PreencherValoresDosDados(2, listaDeInfetados);
        int[] listaDeHospitalizados = new int[numeroDeLinhas];
        listaDeHospitalizados = PreencherValoresDosDados(3, listaDeHospitalizados);
        int[] listaDeInternadosUCI = new int[numeroDeLinhas];
        listaDeInternadosUCI = PreencherValoresDosDados(4, listaDeInternadosUCI);
        int[] listaDeMortes = new int[numeroDeLinhas];
        listaDeMortes = PreencherValoresDosDados(5, listaDeMortes);

        //por num modulo???
        if (args.length == 6 || args.length == 8) { // verificar se é um numero válido de argumentos
            for (int i = 0; i < args.length; i = i + 2) {
                switch (args[i]) {
                    case "-r":
                        String resolucaoTemporal = args[i + 1];
                        break;
                    case "-di":
                        String dataInicial = args[i + 1];
                        break;
                    case "-df":
                        String dataFinal = args[i + 1];
                        break;
                }
            }

            ContarLinhasDoFicheiro();

            if (VerificarExtensaoFicheiro(args[args.length - 2]).equals("csv")) {
                String ficheiroEntrada = args[args.length - 2];
            } else System.out.println("Ficheiro de entrada inválido");

            if (VerificarExtensaoFicheiro(args[args.length - 1]).equals("txt")) {
                String ficheiroSaida = args[args.length - 1];

            } else System.out.println("Ficheiro de saida inválido");

        } else System.out.println("Quantidade de argumentos inválida");

        String segundaFeiraString = "2022-01-10";
        Date segundaFeiraData = formato.parse(segundaFeiraString);
        System.out.println(format2.format(segundaFeiraData));
    }

    public static int[] PreencherValoresDosDados(int tipoDeDado, int[] listaDeDados) throws FileNotFoundException {

        int count = 0;
        String splitBy = ",";
        Scanner fin = new Scanner(new File("exemploRegistoNumerosCovid19.csv"));
        fin.nextLine();
        while (fin.hasNextLine()) {
            String line = fin.nextLine();
            String[] dados = line.split(splitBy);
            listaDeDados[count] = Integer.parseInt(dados[tipoDeDado]);
            count++;
        }
        fin.close();

        return listaDeDados;
    }

    public static Date[] PreencherListaDeDatas(Date[] listaDeDatas) throws FileNotFoundException, ParseException {
        int count = 0;
        String splitBy = ",";
        Scanner fin = new Scanner(new File("exemploRegistoNumerosCovid19.csv"));
        fin.nextLine();
        while (fin.hasNextLine()) {
            String line = fin.nextLine();
            String[] dados = line.split(splitBy);
            listaDeDatas[count] = formato.parse(dados[0]);
            count++;

        }

        fin.close();
        return listaDeDatas;
    }

        public static int ProcurarValorNoTempo(String data, int [] listaDeValores, String [] listaDeDatas){
        int posiçãoDaData = 0;
        for (int i = 0; i < listaDeDatas.length; i++) {
            if (listaDeDatas[i].equals(data)){
                posiçãoDaData = i;
            }
        }

        int valor = listaDeValores[posiçãoDaData];

        return valor;
    }


    public static int ContarLinhasDoFicheiro() throws FileNotFoundException {

        Scanner fin = new Scanner(new File("exemploRegistoNumerosCovid19.csv"));
        int counter = 0;

        while (fin.hasNextLine()) {
            counter++;
            fin.nextLine();
        }
        fin.close();
        return counter;
    }

    public static String VerificarExtensaoFicheiro(String nomeFicheiro) {
        String ext = "";

        int i = nomeFicheiro.lastIndexOf('.');
        if (i > 0) {
            ext = nomeFicheiro.substring(i + 1);
        }

        return ext;
    }


}
