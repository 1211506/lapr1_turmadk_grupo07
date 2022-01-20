import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class Projeto {

    static final SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
    static final SimpleDateFormat formato3 = new SimpleDateFormat("dd-MM-yyyy");
    static final DateFormat formato2 = new SimpleDateFormat("EEEE");
    static final  Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws FileNotFoundException, ParseException {

        String segundaFeiraString = "2022-01-10";
        Date segundaFeiraData = formato.parse(segundaFeiraString);
        String mensagemSegundaFeiraReferencia = formato2.format(segundaFeiraData);


        int indexInicio = 0;
        int indexFinal = 0;
        int indexInicio1 = 0;
        int indexFinal1 = 0;
        int indexInicio2 = 0;
        int indexFinal2 = 0;
        String resolucaoTemporal = "-1";
        String dataInicial = "";
        String dataFinal = "";
        String dataInicial1 = "";
        String dataFinal1 = "";
        String dataInicial2 = "";
        String dataFinal2 = "";
        String ficheiroEntrada = "";
        String ficheiroSaida = "";


        //por num modulo???
        if (args.length >= 6) { // verificar se é um numero válido de argumentos
            for (int i = 0; i < args.length; i = i + 2) {
                switch (args[i]) {
                    case "-r": {
                        resolucaoTemporal = args[i + 1];
                        break;
                    }
                    case "-di": {
                        dataInicial = args[i + 1];
                        break;
                    }
                    case "-df": {
                        dataFinal = args[i + 1];
                        break;
                    }
                    case "-di1": {
                        dataInicial1 = args[i + 1];
                        break;
                    }
                    case "-df1": {
                        dataFinal1 = args[i + 1];
                        break;
                    }
                    case "-di2": {
                        dataInicial2 = args[i + 1];
                        break;
                    }
                    case "-df2": {
                        dataFinal2 = args[i + 1];
                    }
                }
            }


            if (VerificarExtensãoFicheiro(args[args.length - 2]).equals("csv")) {
                ficheiroEntrada = args[args.length - 2];
            } else System.out.println("Ficheiro de entrada inválido");

            if (VerificarExtensãoFicheiro(args[args.length - 1]).equals("txt")) {
                ficheiroSaida = args[args.length - 1];

            } else System.out.println("Ficheiro de saida inválido");

        } else System.out.println("Quantidade de argumentos inválida");

        //MostrarValoresDoDia("2020-04-05", listaDeInfetados, listaDeHospitalizados, listaDeInternadosUCI, listaDeMortes, listaDeDatas);



        if (args.length < 1){
            System.out.println("Introduza o nome do ficheiro");
            ficheiroEntrada = sc.nextLine();
        }

        int numeroDeLinhas = ContarLinhasDoFicheiro(ficheiroEntrada) - 1;

        Date[] listaDeDatas = new Date[numeroDeLinhas];
        listaDeDatas = PreencherListaDeDatas(ficheiroEntrada, listaDeDatas);
        int[] listaDeCasosNaoInfetados = new int[numeroDeLinhas];
        listaDeCasosNaoInfetados = PreencherValoresDosDadosPrimeiraFase(ficheiroEntrada, 1, listaDeCasosNaoInfetados);
        int[] listaDeInfetados = new int[numeroDeLinhas];
        listaDeInfetados = PreencherValoresDosDadosPrimeiraFase(ficheiroEntrada, 2, listaDeInfetados);
        int[] listaDeHospitalizados = new int[numeroDeLinhas];
        listaDeHospitalizados = PreencherValoresDosDadosPrimeiraFase(ficheiroEntrada, 3, listaDeHospitalizados);
        int[] listaDeInternadosUCI = new int[numeroDeLinhas];
        listaDeInternadosUCI = PreencherValoresDosDadosPrimeiraFase(ficheiroEntrada, 4, listaDeInternadosUCI);
        int[] listaDeMortes = new int[numeroDeLinhas];
        listaDeMortes = PreencherValoresDosDadosPrimeiraFase(ficheiroEntrada, 5, listaDeMortes);




        if(args.length > 0) {
            indexInicio = ProcurarPosicaoData(dataInicial, listaDeDatas);
            indexFinal = ProcurarPosicaoData(dataFinal, listaDeDatas);
            indexInicio1 = ProcurarPosicaoData(dataInicial1, listaDeDatas);
            indexFinal1 = ProcurarPosicaoData(dataFinal1, listaDeDatas);
            indexInicio2 = ProcurarPosicaoData(dataInicial2, listaDeDatas);
            indexFinal2 = ProcurarPosicaoData(dataFinal2, listaDeDatas);
        }

        if (args.length < 1) {
            System.out.println("Para modo diario introduza 0, para modo semanal introduza 1, para modo mensal introduza 2 ");
            System.out.println("Se quiser sair introduza o comando sair");
            String comando = "";
            while (!(comando.equals("sair"))) {
                System.out.println("1) visualizar o número total de infetados.");
                System.out.println("2) comparar o número de casos");
                comando = sc.nextLine();
                switch (comando){
                    case "1":{
                        System.out.println("Introduza a data inicial na forma dd-MM-aaaa");
                        dataInicial = sc.nextLine();
                        indexInicio = ProcurarPosicaoData(dataInicial, listaDeDatas);
                        System.out.println("Introduza a data final na forma dd-MM-aaaa");
                        dataFinal = sc.nextLine();
                        indexFinal = ProcurarPosicaoData(dataFinal, listaDeDatas);
                        System.out.println("Para modo diario introduza 0, para modo semanal introduza 1, para modo mensal introduza 2 ");
                        comando = sc.nextLine();
                        switch (comando) {
                            case "0": {
                                MostrarValoresDoDia(listaDeInfetados, listaDeHospitalizados, listaDeInternadosUCI, listaDeMortes, listaDeDatas, indexFinal, indexInicio);
                                MostrarNovosCasosDiarios(listaDeInfetados, listaDeHospitalizados, listaDeInternadosUCI, listaDeMortes, listaDeDatas, indexFinal, indexInicio);
                                System.out.println();
                                break;
                            }
                            case "1": {
                                ModoSemanalMostrarDados(indexFinal, indexInicio, mensagemSegundaFeiraReferencia, listaDeDatas, listaDeInfetados, listaDeHospitalizados, listaDeInternadosUCI, listaDeMortes);
                                break;
                            }
                            case "2": {
                                int indexPrimeiroDiaMes = ProcurarPrimeiroDiaMes(indexInicio, indexFinal, listaDeDatas);
                                int indexUltimoDiaMes = ProcurarUltimoDiaMes(indexInicio, indexFinal, listaDeDatas);
                                int qtMeses = (CalcularQuantidadeDeMesesParaAvaliar(indexPrimeiroDiaMes, indexUltimoDiaMes, listaDeDatas));
                                if (qtMeses > 1) {
                                    ModoMensalMostrarDados(indexPrimeiroDiaMes, indexUltimoDiaMes, qtMeses, listaDeDatas, listaDeInfetados, listaDeHospitalizados, listaDeInternadosUCI, listaDeMortes);
                                } else System.out.println("Impossivel fazer a comparação mensal, especifique um intervalo de datas maior");
                                break;
                            }
                        }
                        break;
                    }
                    case "2":{
                        System.out.println("Introduza a data inicial do primeiro periodo temporal na forma dd-MM-aaaa");
                        dataInicial1 = sc.nextLine();
                        indexInicio1 = ProcurarPosicaoData(dataInicial1, listaDeDatas);
                        System.out.println("Introduza a data final do primeiro periodo temporar na forma dd-MM-aaaa");
                        dataFinal1 = sc.nextLine();
                        indexFinal1 = ProcurarPosicaoData(dataFinal1, listaDeDatas);
                        System.out.println("Introduza a data inicial do segundo periodo temporal na forma dd-MM-aaaa");
                        dataInicial2 = sc.nextLine();
                        indexInicio2 = ProcurarPosicaoData(dataInicial2, listaDeDatas);
                        System.out.println("Introduza a data final do primeiro periodo temporar na forma dd-MM-aaaa");
                        dataFinal2 = sc.nextLine();
                        indexFinal2 = ProcurarPosicaoData(dataFinal2, listaDeDatas);
                        double[] medias = new double[3];
                        double[] desvioPadrao = new double[3];
                        System.out.println();
                        System.out.println("infetados");
                        CompararPeriodosDeTempo(indexInicio1, indexFinal1, indexInicio2, indexFinal2, listaDeInfetados);
                        medias = MostrarMedias(indexInicio1,indexFinal1,indexInicio2,indexFinal2,listaDeInfetados);
                        desvioPadrao = CalcularDesvioPadrao(medias,listaDeInfetados,indexInicio1,indexInicio2);
                        System.out.printf("medias: %.4f     %.4f     %.4f\n",medias[0],medias[1],medias[2]);
                        System.out.printf("Desvio padrão: %.4f     %.4f     %.4f\n",desvioPadrao[0],desvioPadrao[1],desvioPadrao[2]);
                        System.out.println();
                        System.out.println("hospitalizados");
                        CompararPeriodosDeTempo(indexInicio1, indexFinal1, indexInicio2, indexFinal2, listaDeHospitalizados);
                        medias = MostrarMedias(indexInicio1,indexFinal1,indexInicio2,indexFinal2,listaDeHospitalizados);
                        desvioPadrao = CalcularDesvioPadrao(medias,listaDeHospitalizados,indexInicio1,indexInicio2);
                        System.out.printf("medias: %.4f     %.4f     %.4f\n",medias[0],medias[1],medias[2]);
                        System.out.printf("Desvio padrão: %.4f     %.4f     %.4f\n",desvioPadrao[0],desvioPadrao[1],desvioPadrao[2]);
                        System.out.println();
                        System.out.println("internados na UCI");
                        CompararPeriodosDeTempo(indexInicio1, indexFinal1, indexInicio2, indexFinal2, listaDeInternadosUCI);
                        medias = MostrarMedias(indexInicio1,indexFinal1,indexInicio2,indexFinal2,listaDeInternadosUCI);
                        desvioPadrao = CalcularDesvioPadrao(medias,listaDeInternadosUCI,indexInicio1,indexInicio2);
                        System.out.printf("medias: %.4f     %.4f     %.4f\n",medias[0],medias[1],medias[2]);
                        System.out.printf("Desvio padrão: %.4f     %.4f     %.4f\n",desvioPadrao[0],desvioPadrao[1],desvioPadrao[2]);
                        System.out.println();
                        System.out.println("obitos");
                        CompararPeriodosDeTempo(indexInicio1, indexFinal1, indexInicio2, indexFinal2, listaDeMortes);
                        medias = MostrarMedias(indexInicio1,indexFinal1,indexInicio2,indexFinal2,listaDeMortes);
                        desvioPadrao = CalcularDesvioPadrao(medias,listaDeMortes,indexInicio1,indexInicio2);
                        System.out.printf("medias: %.4f     %.4f     %.4f\n",medias[0],medias[1],medias[2]);
                        System.out.printf("Desvio padrão: %.4f     %.4f     %.4f\n",desvioPadrao[0],desvioPadrao[1],desvioPadrao[2]);
                        System.out.println();
                    }
                }
            }
        }


        int modoTemporal = Integer.parseInt(resolucaoTemporal);;

//ANALISE SEMANAL
        if (modoTemporal == 1) {
            ModoSemanalMostrarDados(indexFinal, indexInicio, mensagemSegundaFeiraReferencia, listaDeDatas, listaDeInfetados, listaDeHospitalizados, listaDeInternadosUCI, listaDeMortes);
        }

// FIM DA ANALISE SEMANAL


// ANALISE DIARIA
        if (modoTemporal == 0) {
            MostrarValoresDoDia(listaDeInfetados, listaDeHospitalizados, listaDeInternadosUCI, listaDeMortes, listaDeDatas, indexFinal, indexInicio);
            MostrarNovosCasosDiarios(listaDeInfetados, listaDeHospitalizados, listaDeInternadosUCI, listaDeMortes, listaDeDatas, indexFinal, indexInicio);
        }

        // FIM ANALISE DIARIA

        //ANALISE MENSAL
        if (modoTemporal == 2) {
            int indexPrimeiroDiaMes = ProcurarPrimeiroDiaMes(indexInicio, indexFinal, listaDeDatas);
            int indexUltimoDiaMes = ProcurarUltimoDiaMes(indexInicio, indexFinal, listaDeDatas);
            int qtMeses = (CalcularQuantidadeDeMesesParaAvaliar(indexPrimeiroDiaMes, indexUltimoDiaMes, listaDeDatas));
            if (qtMeses > 1) {
                ModoMensalMostrarDados(indexPrimeiroDiaMes, indexUltimoDiaMes, qtMeses, listaDeDatas, listaDeInfetados, listaDeHospitalizados, listaDeInternadosUCI, listaDeMortes);
            } else System.out.println("Impossivel fazer a comparação mensal, especifique um intervalo de datas maior");

        }
        // FIM ANALISE MENSAL


        //COMPARAÇÕES 2.2
        if (args.length > 0) {
            double[] medias = new double[3];
            double[] desvioPadrao = new double[3];
            System.out.println();
            System.out.println("infetados");
            CompararPeriodosDeTempo(indexInicio1, indexFinal1, indexInicio2, indexFinal2, listaDeInfetados);
            medias = MostrarMedias(indexInicio1,indexFinal1,indexInicio2,indexFinal2,listaDeInfetados);
            desvioPadrao = CalcularDesvioPadrao(medias,listaDeInfetados,indexInicio1,indexInicio2);
            System.out.printf("medias: %.4f     %.4f     %.4f\n",medias[0],medias[1],medias[2]);
            System.out.printf("Desvio padrão: %.4f     %.4f     %.4f\n",desvioPadrao[0],desvioPadrao[1],desvioPadrao[2]);
            System.out.println();
            System.out.println("hospitalizados");
            CompararPeriodosDeTempo(indexInicio1, indexFinal1, indexInicio2, indexFinal2, listaDeHospitalizados);
            medias = MostrarMedias(indexInicio1,indexFinal1,indexInicio2,indexFinal2,listaDeHospitalizados);
            desvioPadrao = CalcularDesvioPadrao(medias,listaDeHospitalizados,indexInicio1,indexInicio2);
            System.out.printf("medias: %.4f     %.4f     %.4f\n",medias[0],medias[1],medias[2]);
            System.out.printf("Desvio padrão: %.4f     %.4f     %.4f\n",desvioPadrao[0],desvioPadrao[1],desvioPadrao[2]);
            System.out.println();
            System.out.println("internados na UCI");
            CompararPeriodosDeTempo(indexInicio1, indexFinal1, indexInicio2, indexFinal2, listaDeInternadosUCI);
            medias = MostrarMedias(indexInicio1,indexFinal1,indexInicio2,indexFinal2,listaDeInternadosUCI);
            desvioPadrao = CalcularDesvioPadrao(medias,listaDeInternadosUCI,indexInicio1,indexInicio2);
            System.out.printf("medias: %.4f     %.4f     %.4f\n",medias[0],medias[1],medias[2]);
            System.out.printf("Desvio padrão: %.4f     %.4f     %.4f\n",desvioPadrao[0],desvioPadrao[1],desvioPadrao[2]);
            System.out.println();
            System.out.println("obitos");
            CompararPeriodosDeTempo(indexInicio1, indexFinal1, indexInicio2, indexFinal2, listaDeMortes);
            medias = MostrarMedias(indexInicio1,indexFinal1,indexInicio2,indexFinal2,listaDeMortes);
            desvioPadrao = CalcularDesvioPadrao(medias,listaDeMortes,indexInicio1,indexInicio2);
            System.out.printf("medias: %.4f     %.4f     %.4f\n",medias[0],medias[1],medias[2]);
            System.out.printf("Desvio padrão: %.4f     %.4f     %.4f\n",desvioPadrao[0],desvioPadrao[1],desvioPadrao[2]);
            System.out.println();

        }

        //FIM COMPARAÇÕES 2.2

    }


    public static int[] PreencherValoresDosDadosPrimeiraFase(String ficheiroEntrada, int tipoDeDado, int[] listaDeDados) throws FileNotFoundException {

        int count = 0;
        String splitBy = ",";
        Scanner fin = new Scanner(new File(ficheiroEntrada));
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

    public static Date[] PreencherListaDeDatas(String ficheiroEntrada, Date[] listaDeDatas) throws FileNotFoundException, ParseException {
        int count = 0;
        String splitBy = ",";
        Scanner fin = new Scanner(new File(ficheiroEntrada));
        fin.nextLine();
        while (fin.hasNextLine()) {
            String line = fin.nextLine();
            String[] dados = line.split(splitBy);

            if (dados[0].split("-")[0].length() != 4) {
                listaDeDatas[count] = formato3.parse(dados[0]);

            } else {
                listaDeDatas[count] = formato.parse(dados[0]);
            }
            count++;

        }

        fin.close();
        return listaDeDatas;
    }

    public static int ProcurarPosicaoData (String data, Date [] listaDeDatas) throws ParseException {
        int posiçãoDaData = -1;
        String [] valoresData = data.split("-");
        if (valoresData[0].length() != 4){
            for (int i = 0; i < listaDeDatas.length; i++) {
                if (listaDeDatas[i].equals(formato3.parse(data))){
                    posiçãoDaData = i;
                }
            }
        } else {
            for (int i = 0; i < listaDeDatas.length; i++) {
                if (listaDeDatas[i].equals(formato.parse(data))){
                    posiçãoDaData = i;
                }
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


    public static int ContarLinhasDoFicheiro(String ficheiroEntrada) throws FileNotFoundException {

        Scanner fin = new Scanner(new File(ficheiroEntrada));
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
                int[] numeroTotalDeObitos = SomarDadosDaSemanaObitos(primeiraSegundaFeiraIndex, indexFinal, listaDeMortes);

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
        while (!(mensagemSegundaFeiraReferencia.equals(formato2.format(listaDeDatas[indexInicial]))) && indexInicial <= indexFinal ){
            indexInicial++;
        }
        if (indexFinal - indexInicial >= 6){
            return indexInicial;
        }
        return -1;
    }

    public static int[] SomarDadosDaSemana (int primeiraSegundaFeiraIndex,int indexFinal, int[] dados){
        int[] somas = new int[((indexFinal - primeiraSegundaFeiraIndex)/ 7)];
        for (int i = 0; i < somas.length; i++) {
            int soma = 0;
            soma = dados[primeiraSegundaFeiraIndex + 6] - dados[primeiraSegundaFeiraIndex];
            somas[i] = soma;
            primeiraSegundaFeiraIndex = primeiraSegundaFeiraIndex + 7;
        }

        return somas;

    }

    public static int[] SomarDadosDaSemanaObitos(int primeiraSegundaFeiraIndex, int indexFinal, int[] dados){

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

    public static void MostrarValoresDoDia (int [] listaDeInfetados, int [] listaDeHospitalizados, int [] listaDeInternadosUCI, int [] listaDeMortes, Date [] listaDeDatas,int indexFinal, int indexInicio) throws ParseException {

        for (int i = indexInicio; i < indexFinal + 1; i++) {
            System.out.println("No dia " + formato.format(listaDeDatas[i])  + " foram registados " + listaDeInfetados[i] + " infetados, " + listaDeHospitalizados[i] + " hospitalizados, " + listaDeInternadosUCI[i] + " internados em UCI e " + listaDeMortes[i] + " óbitos.");
        }
    }

    public static void MostrarNovosCasosDiarios(int [] listaDeInfetados, int [] listaDeHospitalizados, int [] listaDeInternadosUCI, int [] listaDeMortes, Date [] listaDeDatas, int indexFinal, int indexInicio){
        for (int i = indexInicio; i < indexFinal; i++) {
            System.out.println("Entre os dias "+formato.format(listaDeDatas[i])+" e "+formato.format(listaDeDatas[i + 1])+" houve ");
            System.out.print((listaDeInfetados[i + 1]-listaDeInfetados[i])+ " infetados, " + (listaDeHospitalizados[i + 1]-listaDeHospitalizados[i])+ " hospitalizados, ");
            System.out.println((listaDeInternadosUCI[i + 1]- listaDeInternadosUCI[i])+" internados na UCI, "+ (listaDeMortes[i+1]-listaDeMortes[i])+" obitos.");
        }
    }
    //FIM DA ANALISE DIARIA

    // ANALISE MENSAL
    public static int ProcurarPrimeiroDiaMes(int indexInicial, int indexFinal, Date[] listaDeDatas) {
        String[] data = formato.format(listaDeDatas[indexInicial]).split("-");
        while (!data[2].equals("01") && indexInicial < indexFinal) {
            indexInicial++;
            data = formato.format(listaDeDatas[indexInicial]).split("-");
        }


        return indexInicial;
    }

    public static int ProcurarUltimoDiaMes(int indexInicial, int indexFinal, Date[] listaDeDatas) {
        DateTimeFormatter formato4 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String data2 = formato.format(listaDeDatas[indexFinal]);
        LocalDate ultimoDiaMes = LocalDate.parse(data2, DateTimeFormatter.ofPattern("yyyy-M-d"));
        ultimoDiaMes = ultimoDiaMes.withDayOfMonth(ultimoDiaMes.getMonth().length(ultimoDiaMes.isLeapYear()));
        String ultimoDiaMes1 = ultimoDiaMes.format(formato4);
        String[] data = formato.format(listaDeDatas[indexFinal]).split("-");
        String[] data1 = formato.format(listaDeDatas[indexFinal]).split("-");
        if (!(ultimoDiaMes1.equals(data2))){
            while (data1[1].equals(data[1]) && indexFinal > indexInicial) {
                indexFinal--;
                data1 = formato.format(listaDeDatas[indexFinal]).split("-");
            }
        }

        return indexFinal;
    }

    public static int CalcularQuantidadeDeMesesParaAvaliar(int indexPrimeiroDiaMes, int indexFinal, Date[] listaDeDatas) {
        String[] data = formato.format(listaDeDatas[indexPrimeiroDiaMes]).split("-");
        String mes = data[1];
        int qtMeses = 1;
        while (indexPrimeiroDiaMes != indexFinal) {
            indexPrimeiroDiaMes++;
            data = formato.format(listaDeDatas[indexPrimeiroDiaMes]).split("-");
            if (!data[1].equals(mes)) {
                qtMeses++;
            }
            mes = data[1];
        }
        return qtMeses;
    }

    public static int SomarDadosObitos(int indexInicial, int indexFinal, int[] dados, Date[] listaDeDatas) {
        int soma = 0;
        String[] data = formato.format(listaDeDatas[indexInicial]).split("-");
        String mes = data[1];
        while (data[1].equals(mes) && indexInicial < indexFinal) {
            soma = soma + dados[indexInicial];
            indexInicial++;
            data = formato.format(listaDeDatas[indexInicial]).split("-");
        }
        return soma;
    }

    public static int SomarDadosMes(int indexInicial, int indexFinal, int[] dados, Date[] listaDeDatas) {
        int indexInicialOriginal = indexInicial;
        String[] data = formato.format(listaDeDatas[indexInicial]).split("-");
        String mes = data[1];
        while (data[1].equals(mes) && indexInicial < indexFinal) {
            indexInicial++;
            data = formato.format(listaDeDatas[indexInicial]).split("-");
        }
        int variacao = dados[indexInicial] - dados[indexInicialOriginal];
        return variacao;
    }



    public static void ModoMensalMostrarDados(int indexInicio, int indexFinal, int qtMeses, Date[] listaDeDatas, int[] listaDeInfetados, int[] listaDeHospitalizados, int[] listaDeInternadosUCI, int[] listaDeMortes) {
        int[] listaDeDados = new int[4];
        int[] listaDeDados1 = new int[4];
        for (int i = 1; i < qtMeses; i++) {
            String[] data = formato.format(listaDeDatas[indexInicio]).split("-");
            String mes = data[1];
            listaDeDados[0] = SomarDadosMes(indexInicio, indexFinal, listaDeInfetados, listaDeDatas);
            listaDeDados[1] = SomarDadosMes(indexInicio, indexFinal, listaDeHospitalizados, listaDeDatas);
            listaDeDados[2] = SomarDadosMes(indexInicio, indexFinal, listaDeInternadosUCI, listaDeDatas);
            listaDeDados[3] = SomarDadosObitos(indexInicio, indexFinal, listaDeMortes, listaDeDatas);
            while (data[1].equals(mes) && indexInicio <= indexFinal) {
                indexInicio++;
                data = formato.format(listaDeDatas[indexInicio]).split("-");
            }

            String[] data2 = formato.format(listaDeDatas[indexInicio]).split("-");
            String mes2 = data2[1];
            listaDeDados1[0] = SomarDadosMes(indexInicio, indexFinal, listaDeInfetados, listaDeDatas);
            listaDeDados1[1] = SomarDadosMes(indexInicio, indexFinal, listaDeHospitalizados, listaDeDatas);
            listaDeDados1[2] = SomarDadosMes(indexInicio, indexFinal, listaDeInternadosUCI, listaDeDatas);
            listaDeDados1[3] = SomarDadosObitos(indexInicio, indexFinal, listaDeMortes, listaDeDatas);

            int diferencaInfetados = listaDeDados1[0] - listaDeDados[0];
            int diferencaHospitalizados = listaDeDados1[1] - listaDeDados[1];
            int diferencaUCI = listaDeDados1[2] - listaDeDados[2];
            int diferencaObitos = listaDeDados1[3] - listaDeDados[3];


            System.out.println("Entre os meses " + mes + " e " + mes2 + " houve uma alteração de " + diferencaInfetados + " infetados");
            System.out.println("Entre os meses " + mes + " e " + mes2 + " houve uma alteração de " + diferencaHospitalizados + " hospitalizados");
            System.out.println("Entre os meses " + mes + " e " + mes2 + " houve uma alteração de " + diferencaUCI + " hospitalizados em UCI");
            System.out.println("Entre os meses " + mes + " e " + mes2 + " houve uma alteração de " + diferencaObitos + " óbitos");
            System.out.println();

        }
    }



    // FIM ANALISE MENSAL POR ACABAR

    public static void CompararPeriodosDeTempo(int indexInicio1,int indexFinal1,int indexInicio2,int indexFinal2,int[] arrayDeDados){

        while (indexFinal1 > indexInicio1 && indexFinal2 > indexInicio2){

            System.out.print(arrayDeDados[indexInicio1]+ "     ");
            System.out.print(arrayDeDados[indexInicio2]+ "     ");
            System.out.println((arrayDeDados[indexInicio2]) - (arrayDeDados[indexInicio1]));
            indexInicio1++;
            indexInicio2++;

        }

    }
    public static double[] MostrarMedias (int indexInicio1,int indexFinal1,int indexInicio2,int indexFinal2,int[] arrayDeDados){

        double media1 = 0;
        double media2 = 0;
        double media3 = 0;
        double count = 0;

        while (indexFinal1 > indexInicio1 && indexFinal2 > indexInicio2){

            media1 = media1 + arrayDeDados[indexInicio1];
            media2 = media2 + arrayDeDados[indexInicio2];
            int diferençaDeValores = arrayDeDados[indexInicio2] - arrayDeDados[indexInicio1];
            if (diferençaDeValores < 0){
                diferençaDeValores = diferençaDeValores * - 1;
            }
            media3 = media3 + diferençaDeValores;
            indexInicio1++;
            indexInicio2++;
            count++;
        }

        media1 = media1 / count;
        media2 = media2 / count;
        media3 = media3 / count;


        double[] mediasECounter = {media1,media2,media3,count};

        System.out.println();

        return mediasECounter;

    }

    public static double[] CalcularDesvioPadrao(double[] medias, int[] dados, int indexInicio1, int indexInicio2){

        double[] variacao=CalcularVariacao(medias,dados,indexInicio1,indexInicio2);
        double[] desvioPadrao = new double[3];

        for (int i = 0; i < desvioPadrao.length; i++) {
            desvioPadrao[i] = Math.sqrt(variacao[i]);
        }

        return desvioPadrao;

    }

    public static double[] CalcularVariacao(double[] medias, int[] dados, int indexInicio1, int indexInicio2){


        double[] variacao = new double[3];
        double[] desvio = new double[(int)medias[3]];
        double somaVariacao = 0;
        int index1Aux = indexInicio1;
        int index2Aux = indexInicio2;


        for (int i = 0; i < desvio.length ; i++) {
            desvio[i] = medias[0] - dados[indexInicio1];
            indexInicio1++;
        }

        for (int i = 0; i < desvio.length; i++) {
            somaVariacao = (desvio[i] * desvio[i]) + somaVariacao;
        }

        variacao[0] = somaVariacao/desvio.length;
        somaVariacao = 0;

        for (int i = 0; i < desvio.length ; i++) {
            desvio[i] = medias[1] - dados[indexInicio2];
            indexInicio2++;
        }

        for (int i = 0; i < desvio.length; i++) {
            somaVariacao = (desvio[i] * desvio[i]) + somaVariacao;
        }

        variacao[1] = somaVariacao/ desvio.length;
        somaVariacao = 0;


        for (int i = 0; i < desvio.length ; i++) {
            desvio[i] = medias[2] - (dados[index2Aux] - dados[index1Aux]);
            index2Aux++;
            index1Aux++;
        }



        for (int i = 0; i < desvio.length; i++) {
            somaVariacao = (desvio[i] * desvio[i]) + somaVariacao;
        }

        variacao[2] = somaVariacao/ desvio.length;

        return variacao;
    }
}