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

        String segundaFeiraString = "2022-01-10";
        Date segundaFeiraData = formato.parse(segundaFeiraString);
        String mensagemSegundaFeiraReferencia = format2.format(segundaFeiraData);

        String resolucaoTemporal = "";
        String dataInicial = "";
        String dataFinal = "";
        int numeroDeLinhas = ContarLinhasDoFicheiro() - 1;

        Date[] listaDeDatas = new Date[numeroDeLinhas];
        listaDeDatas = PreencherListaDeDatas(listaDeDatas);
        int[] listaDeCasosNaoInfetados = new int[numeroDeLinhas];
        listaDeCasosNaoInfetados = PreencherValoresDosDadosPrimeiraFase(1, listaDeCasosNaoInfetados);
        int[] listaDeInfetados = new int[numeroDeLinhas];
        listaDeInfetados = PreencherValoresDosDadosPrimeiraFase(2, listaDeInfetados);
        int[] listaDeHospitalizados = new int[numeroDeLinhas];
        listaDeHospitalizados = PreencherValoresDosDadosPrimeiraFase(3, listaDeHospitalizados);
        int[] listaDeInternadosUCI = new int[numeroDeLinhas];
        listaDeInternadosUCI = PreencherValoresDosDadosPrimeiraFase(4, listaDeInternadosUCI);
        int[] listaDeMortes = new int[numeroDeLinhas];
        listaDeMortes = PreencherValoresDosDadosPrimeiraFase(5, listaDeMortes);

        //por num modulo???
        if (args.length == 6 || args.length == 8) { // verificar se é um numero válido de argumentos
            for (int i = 0; i < args.length; i = i + 2) {
                switch (args[i]) {
                    case "-r":
                        resolucaoTemporal = args[i + 1];
                        break;
                    case "-di":
                        dataInicial = args[i + 1];
                        break;
                    case "-df":
                        dataFinal = args[i + 1];
                        break;
                }
            }




            if (VerificarExtensãoFicheiro(args[args.length - 2]).equals("csv")) {
                String ficheiroEntrada = args[args.length - 2];
            } else System.out.println("Ficheiro de entrada inválido");

            if (VerificarExtensãoFicheiro(args[args.length - 1]).equals("txt")) {
                String ficheiroSaida = args[args.length - 1];

            } else System.out.println("Ficheiro de saida inválido");

        } else System.out.println("Quantidade de argumentos inválida");

         //MostrarValoresDoDia("2020-04-05", listaDeInfetados, listaDeHospitalizados, listaDeInternadosUCI, listaDeMortes, listaDeDatas);

        int modoTemporal = Integer.parseInt(resolucaoTemporal);
        int indexInicio = ProcurarPosicaoData(dataInicial, listaDeDatas);
        int indexFinal = ProcurarPosicaoData(dataFinal, listaDeDatas);


//ANALISE SEMANAL

        if (modoTemporal == 1 && indexFinal - indexInicio > 7) {
            int primeiraSegundaFeira = ProcurarPrimeiraSegundaFeira(indexInicio, indexFinal, mensagemSegundaFeiraReferencia, listaDeDatas);
            System.out.println(primeiraSegundaFeira);
            if (primeiraSegundaFeira == -1) {
                System.out.println("A partir da primeira segunda-feira não existem dias suficientes para a analise semanal");
            }else {
                    int[] numeroTotalInfetados = SomarDadosDaSemana(primeiraSegundaFeira, indexFinal, listaDeInfetados);
                    int[] numeroTotalDeHospitalizados = SomarDadosDaSemana(primeiraSegundaFeira, indexFinal, listaDeHospitalizados);
                    int[] numeroTotalDeInternadosNaUCI = SomarDadosDaSemana(primeiraSegundaFeira, indexFinal, listaDeInternadosUCI);
                    int[] numeroTotalDeObitos = SomarDadosDaSemana(primeiraSegundaFeira, indexFinal, listaDeMortes);

                for (int i = 0; i < (indexFinal - primeiraSegundaFeira )/7 ; i++) {
                    System.out.print("Na semana de " + format2.format(listaDeDatas[primeiraSegundaFeira + (7 * i)]) + " a " + format2.format(listaDeDatas[primeiraSegundaFeira + 7 + (7 * i)]));
                    System.out.println("houve: " + numeroTotalInfetados[i] + " infetados, " + numeroTotalDeHospitalizados[i] + " hospitalizados, " + numeroTotalDeInternadosNaUCI[i] + " internados na UCI, " + numeroTotalDeObitos[i] + " obitos.");
                }

            }

        }
        if (modoTemporal == 1 && indexFinal - indexInicio < 7){
            System.out.println("O periodo de tempo escolhido é muito curto para analise semanal.");
        }



// FIM DA ANALISE SEMANAL
    }

    public static int[] PreencherValoresDosDadosPrimeiraFase(int tipoDeDado, int[] listaDeDados) throws FileNotFoundException {

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

    public static int ProcurarPosicaoData (String data, Date [] listaDeDatas) throws ParseException {
        int posiçãoDaData = -1;
        for (int i = 0; i < listaDeDatas.length; i++) {
            if (listaDeDatas[i].equals(formato.parse(data))){
                posiçãoDaData = i;
            }
        }

        return posiçãoDaData;
    }
    public static int ProcurarValorDoDia (String data, int [] listaDeValores, Date [] listaDeDatas) throws ParseException {
        int valor;

        int posiçãoDaData = ProcurarPosicaoData(data, listaDeDatas);

        if (posiçãoDaData == -1) {
            valor = -1;
        }
        else valor = listaDeValores[posiçãoDaData];

        return valor;
    }

    public static void MostrarValoresDoDia (String data, int [] listaDeInfetados, int [] listaDeHospitalizados, int [] listaDeInternadosUCI, int [] listaDeMortes, Date [] listaDeDatas) throws ParseException {
        int posiçãoDaData = ProcurarPosicaoData(data, listaDeDatas);

        if (posiçãoDaData == -1){
            System.out.println("Data Inválida.");
        } else System.out.println("No dia " + data + " foram registados " + listaDeInfetados[posiçãoDaData] + " infetados, " + listaDeHospitalizados[posiçãoDaData] + " hospitalizados, " + listaDeInternadosUCI[posiçãoDaData] + " internados em UCI e " + listaDeMortes[posiçãoDaData] + " óbitos.");

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

    public static String VerificarExtensãoFicheiro(String nomeFicheiro) {
        String ext = "";

        int i = nomeFicheiro.lastIndexOf('.');
        if (i > 0) {
            ext = nomeFicheiro.substring(i + 1);
        }

        return ext;
    }

    public static int ProcurarPrimeiraSegundaFeira (int indexInicial,int indexFinal,String mensagemSegundaFeiraReferencia, Date[] listaDeDatas){
        while (!(mensagemSegundaFeiraReferencia.equals(format2.format(listaDeDatas[indexInicial]))) && indexInicial <= indexFinal ){
            indexInicial++;
        }
        if (indexFinal - indexInicial > 7){
            return indexInicial;
        }

        return -1;
    }

    public static int[] SomarDadosDaSemana(int primeiraSegundaFeira, int indexFinal, int[] dados){

        int[] somas = new int[(indexFinal - primeiraSegundaFeira)/ 7];
        for (int i = 0; i < (indexFinal - primeiraSegundaFeira )/7; i++) {
            int count = 0;
            int soma = 0;
            while (count < 7){
                soma = soma + dados[primeiraSegundaFeira];
                primeiraSegundaFeira++;
                count++;
            }

            somas[i] = soma;
        }

        return somas;
    }

}
