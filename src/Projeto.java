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
        if (modoTemporal == 1) {
            ModoSemanalMostrarDados(indexFinal, indexInicio, mensagemSegundaFeiraReferencia, listaDeDatas, listaDeInfetados, listaDeHospitalizados, listaDeInternadosUCI, listaDeMortes);
        }

// FIM DA ANALISE SEMANAL


// ANALISE DIARIA
//modos desnecessarios
        if (modoTemporal == 0){
            ModoDiario(indexFinal, indexInicio, listaDeDatas, listaDeInfetados, listaDeHospitalizados, listaDeInternadosUCI, listaDeMortes);
        }
    }
// FIM ANALISE DIARIA

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
    //ANALISE SEMANAL
//problemas com segunda a domingo, porem funciona de segunda a segunda

    public static void ModoSemanalMostrarDados(int indexFinal,int indexInicio,String mensagemSegundaFeiraReferencia,Date[] listaDeDatas,int[] listaDeInfetados,int[] listaDeHospitalizados,int[] listaDeInternadosUCI,int[] listaDeMortes) {
        if (indexFinal - indexInicio >= 7) {
            int primeiraSegundaFeiraIndex = ProcurarPrimeiraSegundaFeira(indexInicio, indexFinal, mensagemSegundaFeiraReferencia, listaDeDatas);
            if (primeiraSegundaFeiraIndex == -1) {
                System.out.println("A partir da primeira segunda-feira não existem dias suficientes para a analise semanal");
            } else {
                int[] numeroTotalInfetados = SomarDadosDaSemana(primeiraSegundaFeiraIndex, indexFinal, listaDeInfetados);
                int[] numeroTotalDeHospitalizados = SomarDadosDaSemana(primeiraSegundaFeiraIndex, indexFinal, listaDeHospitalizados);
                int[] numeroTotalDeInternadosNaUCI = SomarDadosDaSemana(primeiraSegundaFeiraIndex, indexFinal, listaDeInternadosUCI);
                int[] numeroTotalDeObitos = SomarDadosDaSemana(primeiraSegundaFeiraIndex, indexFinal, listaDeMortes);

                for (int i = 0; i < (indexFinal - primeiraSegundaFeiraIndex) / 7; i++) {
                    System.out.print("Na semana de " + (formato.format(listaDeDatas[primeiraSegundaFeiraIndex + (7 * i)]) + " a " + (formato.format(listaDeDatas[primeiraSegundaFeiraIndex + 7 + (7 * i) - 1]))));
                    System.out.println(" houve: " + numeroTotalInfetados[i] + " infetados, " + numeroTotalDeHospitalizados[i] + " hospitalizados, " + numeroTotalDeInternadosNaUCI[i] + " internados na UCI, " + numeroTotalDeObitos[i] + " obitos.");
                }
                System.out.println();
                if ((indexFinal - primeiraSegundaFeiraIndex)/ 7 > 1) {
                    for (int i = 0; i < ((indexFinal - primeiraSegundaFeiraIndex) / 7) - 1; i++) {
                        System.out.print("Entre as semanas de " + (formato.format(listaDeDatas[primeiraSegundaFeiraIndex + (7 * i)]) + " a " + (formato.format(listaDeDatas[primeiraSegundaFeiraIndex + 7 + (7 * i) - 1]))));
                        System.out.println(" e " + (formato.format(listaDeDatas[primeiraSegundaFeiraIndex + (7 * (i + 1))]) + " a " + (formato.format(listaDeDatas[primeiraSegundaFeiraIndex + 7 + (7 * (i + 1)) - 1]))));
                        System.out.println("houve " + (numeroTotalInfetados[i + 1] - numeroTotalInfetados[i]) + " infetados, " + (numeroTotalDeHospitalizados[i + 1] - numeroTotalInfetados[i]) + " hospitalizados, " + (numeroTotalDeInternadosNaUCI[i + 1] - numeroTotalDeInternadosNaUCI[i]) + " internados na UCI, " + (numeroTotalDeObitos[i + 1] - numeroTotalDeObitos[i]) + " obitos");
                    }
                }else {
                    System.out.println("Como só foi selecionada uma semana não é possivel fazer comparações");
                }

            }

        }
        if (indexFinal - indexInicio < 7) {
            System.out.println("O periodo de tempo escolhido é muito curto para analise semanal.");
        }
    }

    public static int ProcurarPrimeiraSegundaFeira (int indexInicial,int indexFinal,String mensagemSegundaFeiraReferencia, Date[] listaDeDatas){
        while (!(mensagemSegundaFeiraReferencia.equals(format2.format(listaDeDatas[indexInicial]))) && indexInicial <= indexFinal ){
            indexInicial++;
        }
        if (indexFinal - indexInicial >= 6){
            return indexInicial;
        }
        return -1;
    }

    public static int[] SomarDadosDaSemana(int primeiraSegundaFeiraIndex, int indexFinal, int[] dados){

        int[] somas = new int[((indexFinal - primeiraSegundaFeiraIndex)/ 7)];
        int numeroDeSemanas = ((indexFinal - primeiraSegundaFeiraIndex )/7);
        for (int i = 0; i < numeroDeSemanas; i++) {
            int soma = 0;
            for (int j = 0; j < 7; j++) {
                soma = soma + dados[primeiraSegundaFeiraIndex];
                primeiraSegundaFeiraIndex++;
            }
            somas[i] = soma;
        }
        return somas;
    }

    // FIM DA ANALISE SEMANAL

    //ANALISE DIARIA
    public static void ModoDiario(int indexFinal, int indexInicio, Date[] listaDeDatas, int[] listaDeInfetados, int[] listaDeHospitalizados, int[] listaDeInternadosUCI, int[] listaDeMortes) throws ParseException {
            MostrarValoresDoDia(listaDeInfetados,listaDeHospitalizados,listaDeInternadosUCI,listaDeMortes,listaDeDatas,indexFinal,indexInicio);
    }

    public static void MostrarValoresDoDia (int [] listaDeInfetados, int [] listaDeHospitalizados, int [] listaDeInternadosUCI, int [] listaDeMortes, Date [] listaDeDatas,int indexFinal, int indexInicio) throws ParseException {

        for (int i = indexInicio; i < indexFinal; i++) {
            System.out.println("No dia " + formato.format(listaDeDatas[i])  + " foram registados " + listaDeInfetados[i] + " infetados, " + listaDeHospitalizados[i] + " hospitalizados, " + listaDeInternadosUCI[i] + " internados em UCI e " + listaDeMortes[i] + " óbitos.");
        }
    }
    //FIM DA ANALISE DIARIA
}

