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

        int indexInicio = -1;
        int indexFinal = -1;
        int indexInicio1 = -1;
        int indexFinal1 = -1;
        int indexInicio2 = -1;
        int indexFinal2 = -1;
        boolean modoPrevisao = false;
        boolean existeArgumentoR = false;
        boolean existeArgumentoT = false;
        boolean ficheirosValidos = false;
        boolean dataInicialExiste = false;
        boolean dataFinalExiste = false;
        boolean dataInicialExiste1 = false;
        boolean dataFinalExiste1 = false;
        boolean dataInicialExiste2 = false;
        boolean dataFinalExiste2 = false;
        boolean argumentosValidos = false;
        String resolucaoTemporal = "-1";
        String dataInicial = "";
        String dataFinal = "";
        String dataInicial1 = "";
        String dataFinal1 = "";
        String dataInicial2 = "";
        String dataFinal2 = "";
        String ficheiroEntradaTotal = "";
        String ficheiroEntrada = "";
        String ficheiroSaida = "";
        String ficheiroMatriz = "";
        String dataPrevisao = "";


        /****************************************************************
         *                                                              *
         *                INICIO MODO NÃO INTERATIVO                    *
         *                                                              *
         ****************************************************************/

        if (args.length >= 5 && args.length <= 20) { // verificar se é um numero válido de argumentos
            for (int i = 0; i < args.length; i = i + 2) {
                switch (args[i]) {
                    case "-r": {
                        resolucaoTemporal = args[i + 1];
                        existeArgumentoR = true;
                        break;
                    }
                    case "-di": {
                        dataInicial = args[i + 1];
                        dataInicialExiste = true;
                        break;
                    }
                    case "-df": {
                        dataFinal = args[i + 1];
                        dataFinalExiste = true;
                        break;
                    }
                    case "-di1": {
                        dataInicial1 = args[i + 1];
                        dataInicialExiste1 = true;
                        break;
                    }
                    case "-df1": {
                        dataFinal1 = args[i + 1];
                        dataFinalExiste1 = true;
                        break;
                    }
                    case "-di2": {
                        dataInicial2 = args[i + 1];
                        dataInicialExiste2 = true;
                        break;
                    }
                    case "-df2": {
                        dataFinal2 = args[i + 1];
                        dataFinalExiste2 = true;
                        break;
                    }
                    case "-T": {
                        dataPrevisao = args[i + 1];
                        existeArgumentoT = true;
                    }

                }
            }

            if (existeArgumentoR == true && existeArgumentoT == false) {
                File ficheiroEntradaTemp = new File(args[args.length - 2]);
                File ficheiroSaidaTemp = new File(args[args.length - 1]);
                if (ficheiroEntradaTemp.exists() && ficheiroSaidaTemp.exists()) {
                    ficheiroEntrada = args[args.length - 2];
                    ficheiroSaida = args[args.length - 1];
                    ficheirosValidos = true;
                } else {
                    System.out.println("Os ficheiros introduzidos não foram encontrados.");
                }
                if (dataInicialExiste == true && dataFinalExiste == true && dataInicialExiste1 == true && dataFinalExiste1 == true && dataInicialExiste2 == true && dataFinalExiste2 == true){
                    argumentosValidos = true;
                } else {
                    System.out.println("Argumentos inválidos! Utilize um dos seguintes sintaxes:");
                    System.out.println();
                    System.out.println("java -jar nome_programa.jar -r X -di DD-MM-AAAA -df DD-MM-AAAA -di1 DD-MMAAAA -df1 DD-MM-AAAA -di2 DD-MM-AAAA -df2 DD-MM-AAAA -T DD-MM-AAAA registoNumeroTotalCovid19.csv registoNumerosAcumuladosCovid19.csv matrizTransicao.txt nome_ficheiro_saida.txt");
                    System.out.println("java -jar nome_programa.jar -r X -di DD-MM-AAAA -df DD-MMAAAA -di1 DD-MM-AAAA -df1 DD-MM-AAAA -di2 DD-MM-AAAA -df2 DD-MMAAAA registoNumerosAcumuladosCovid19.csv nome_ficheiro saida.txt");
                    System.out.println("java -jar nome_programa.jar -T DD-MM-AAAA registoNumerosTotalCovid19.csv matrizTransicao.csv nome_ficheiro_saida.txt");
                }
            }

            if (existeArgumentoR == false && existeArgumentoT == true) {
                File ficheiroEntradaTotalTemp = new File(args[args.length - 3]);
                File ficheiroSaidaTemp = new File(args[args.length - 1]);
                File ficheiroMatrizTemp = new File(args[args.length - 2]);
                if (ficheiroEntradaTotalTemp.exists() && ficheiroSaidaTemp.exists() && ficheiroMatrizTemp.exists()) {
                    ficheiroEntradaTotal = args[args.length - 3];
                    ficheiroSaida = args[args.length - 1];
                    ficheiroMatriz = args[args.length - 2];
                    ficheirosValidos = true;
                    modoPrevisao = true;
                } else {
                    System.out.println("Os ficheiros introduzidos não foram encontrados.");
                }
            }

            if (existeArgumentoR == true && existeArgumentoT == true) {
                File ficheiroEntradaTotalTemp = new File(args[args.length - 4]);
                File ficheiroEntradaTemp = new File(args[args.length - 3]);
                File ficheiroSaidaTemp = new File(args[args.length - 1]);
                File ficheiroMatrizTemp = new File(args[args.length - 2]);
                if (ficheiroEntradaTemp.exists() && ficheiroSaidaTemp.exists() && ficheiroMatrizTemp.exists() && ficheiroEntradaTotalTemp.exists()) {
                    ficheiroEntrada = args[args.length - 3];
                    ficheiroSaida = args[args.length - 1];
                    ficheiroMatriz = args[args.length - 2];
                    ficheiroEntradaTotal = args[args.length - 4];
                    ficheirosValidos = true;
                    modoPrevisao = true;
                } else {
                    System.out.println("Os ficheiros introduzidos não foram encontrados.");
                }
                if (dataInicialExiste == true && dataFinalExiste == true && dataInicialExiste1 == true && dataFinalExiste1 == true && dataInicialExiste2 == true && dataFinalExiste2 == true){
                    argumentosValidos = true;
                } else {
                    System.out.println("Argumentos inválidos! Utilize um dos seguintes sintaxes:");
                    System.out.println();
                    System.out.println("java -jar nome_programa.jar -r X -di DD-MM-AAAA -df DD-MM-AAAA -di1 DD-MMAAAA -df1 DD-MM-AAAA -di2 DD-MM-AAAA -df2 DD-MM-AAAA -T DD-MM-AAAA registoNumeroTotalCovid19.csv registoNumerosAcumuladosCovid19.csv matrizTransicao.txt nome_ficheiro_saida.txt");
                    System.out.println("java -jar nome_programa.jar -r X -di DD-MM-AAAA -df DD-MMAAAA -di1 DD-MM-AAAA -df1 DD-MM-AAAA -di2 DD-MM-AAAA -df2 DD-MMAAAA registoNumerosAcumuladosCovid19.csv nome_ficheiro saida.txt");
                    System.out.println("java -jar nome_programa.jar -T DD-MM-AAAA registoNumerosTotalCovid19.csv matrizTransicao.csv nome_ficheiro_saida.txt");
                }
            }

            if (ficheirosValidos == true && argumentosValidos == true) {
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


                indexInicio = ProcurarPosicaoData(dataInicial, listaDeDatas);
                indexFinal = ProcurarPosicaoData(dataFinal, listaDeDatas);
                indexInicio1 = ProcurarPosicaoData(dataInicial1, listaDeDatas);
                indexFinal1 = ProcurarPosicaoData(dataFinal1, listaDeDatas);
                indexInicio2 = ProcurarPosicaoData(dataInicial2, listaDeDatas);
                indexFinal2 = ProcurarPosicaoData(dataFinal2, listaDeDatas);


                int modoTemporal = Integer.parseInt(resolucaoTemporal);


                if (indexInicio != -1 && indexFinal != -1) {

                    //ANALISE SEMANAL
                    if (modoTemporal == 1) {
                        if (listaDeDatas[indexInicio].before(listaDeDatas[indexFinal])) {
                            ModoSemanalMostrarDados(indexFinal, indexInicio, mensagemSegundaFeiraReferencia, listaDeDatas, listaDeInfetados, listaDeHospitalizados, listaDeInternadosUCI, listaDeMortes);
                        } else {
                            System.out.println("O intervalo de datas que foi introduzido é inválido.");
                        }
                    }


                    // FIM DA ANALISE SEMANAL


                    // ANALISE DIARIA
                    if (modoTemporal == 0) {
                        if (listaDeDatas[indexInicio].before(listaDeDatas[indexFinal])) {
                            MostrarValoresDoDia(listaDeInfetados, listaDeHospitalizados, listaDeInternadosUCI, listaDeMortes, listaDeDatas, indexFinal, indexInicio);
                            MostrarNovosCasosDiarios(listaDeInfetados, listaDeHospitalizados, listaDeInternadosUCI, listaDeMortes, listaDeDatas, indexFinal, indexInicio);
                        } else {
                            System.out.println("O intervalo de datas que foi introduzido é inválido.");
                        }
                    }



                    // FIM ANALISE DIARIA

                    //ANALISE MENSAL
                    if (modoTemporal == 2) {
                        if (listaDeDatas[indexInicio].before(listaDeDatas[indexFinal])) {
                            int indexPrimeiroDiaMes = ProcurarPrimeiroDiaMes(indexInicio, indexFinal, listaDeDatas);
                            int indexUltimoDiaMes = ProcurarUltimoDiaMes(indexInicio, indexFinal, listaDeDatas);
                            int qtMeses = (CalcularQuantidadeDeMesesParaAvaliar(indexPrimeiroDiaMes, indexUltimoDiaMes, listaDeDatas));
                            if (qtMeses > 1) {
                                ModoMensalMostrarDados(indexPrimeiroDiaMes, indexUltimoDiaMes, qtMeses, listaDeDatas, listaDeInfetados, listaDeHospitalizados, listaDeInternadosUCI, listaDeMortes);
                            } else
                                System.out.println("Impossivel fazer a comparação mensal, especifique um intervalo de datas maior");
                        } else {
                            System.out.println("O intervalo de datas que foi introduzido é inválido.");
                        }


                    }
                    // FIM ANALISE MENSAL

                    //COMPARAÇÕES 2.2
                    double[] medias = new double[3];
                    double[] desvioPadrao = new double[3];
                    System.out.println();
                    System.out.println("infetados");
                    CompararPeriodosDeTempo(indexInicio1, indexFinal1, indexInicio2, indexFinal2, listaDeInfetados);
                    medias = MostrarMedias(indexInicio1, indexFinal1, indexInicio2, indexFinal2, listaDeInfetados);
                    desvioPadrao = CalcularDesvioPadrao(medias, listaDeInfetados, indexInicio1, indexInicio2);
                    System.out.printf("medias: %.4f     %.4f     %.4f\n", medias[0], medias[1], medias[2]);
                    System.out.printf("Desvio padrão: %.4f     %.4f     %.4f\n", desvioPadrao[0], desvioPadrao[1], desvioPadrao[2]);
                    System.out.println();
                    System.out.println("hospitalizados");
                    CompararPeriodosDeTempo(indexInicio1, indexFinal1, indexInicio2, indexFinal2, listaDeHospitalizados);
                    medias = MostrarMedias(indexInicio1, indexFinal1, indexInicio2, indexFinal2, listaDeHospitalizados);
                    desvioPadrao = CalcularDesvioPadrao(medias, listaDeHospitalizados, indexInicio1, indexInicio2);
                    System.out.printf("medias: %.4f     %.4f     %.4f\n", medias[0], medias[1], medias[2]);
                    System.out.printf("Desvio padrão: %.4f     %.4f     %.4f\n", desvioPadrao[0], desvioPadrao[1], desvioPadrao[2]);
                    System.out.println();
                    System.out.println("internados na UCI");
                    CompararPeriodosDeTempo(indexInicio1, indexFinal1, indexInicio2, indexFinal2, listaDeInternadosUCI);
                    medias = MostrarMedias(indexInicio1, indexFinal1, indexInicio2, indexFinal2, listaDeInternadosUCI);
                    desvioPadrao = CalcularDesvioPadrao(medias, listaDeInternadosUCI, indexInicio1, indexInicio2);
                    System.out.printf("medias: %.4f     %.4f     %.4f\n", medias[0], medias[1], medias[2]);
                    System.out.printf("Desvio padrão: %.4f     %.4f     %.4f\n", desvioPadrao[0], desvioPadrao[1], desvioPadrao[2]);
                    System.out.println();
                    System.out.println("obitos");
                    CompararPeriodosDeTempo(indexInicio1, indexFinal1, indexInicio2, indexFinal2, listaDeMortes);
                    medias = MostrarMedias(indexInicio1, indexFinal1, indexInicio2, indexFinal2, listaDeMortes);
                    desvioPadrao = CalcularDesvioPadrao(medias, listaDeMortes, indexInicio1, indexInicio2);
                    System.out.printf("medias: %.4f     %.4f     %.4f\n", medias[0], medias[1], medias[2]);
                    System.out.printf("Desvio padrão: %.4f     %.4f     %.4f\n", desvioPadrao[0], desvioPadrao[1], desvioPadrao[2]);
                    System.out.println();

                } else {
                    System.out.println("As datas introduzidas não se encontram no ficheiro especificado.");
                    System.out.println();
                }
                //FIM COMPARAÇÕES 2.2


            }

            //INICIO PREVISOES 2.3

            if (modoPrevisao == true){

                double [][] matrizProbalidades = LerFicheiroMatriz(ficheiroMatriz);
                int numeroDeLinhas = ContarLinhasDoFicheiro(ficheiroEntradaTotal) - 1;
                Date[] listaDeDatas = new Date[numeroDeLinhas];
                listaDeDatas = PreencherListaDeDatas(ficheiroEntradaTotal, listaDeDatas);
                int[] listaDeCasosNaoInfetados = new int[numeroDeLinhas];
                listaDeCasosNaoInfetados = PreencherValoresDosDadosPrimeiraFase(ficheiroEntradaTotal, 1, listaDeCasosNaoInfetados);
                int[] listaDeInfetados = new int[numeroDeLinhas];
                listaDeInfetados = PreencherValoresDosDadosPrimeiraFase(ficheiroEntradaTotal, 2, listaDeInfetados);
                int[] listaDeHospitalizados = new int[numeroDeLinhas];
                listaDeHospitalizados = PreencherValoresDosDadosPrimeiraFase(ficheiroEntradaTotal, 3, listaDeHospitalizados);
                int[] listaDeInternadosUCI = new int[numeroDeLinhas];
                listaDeInternadosUCI = PreencherValoresDosDadosPrimeiraFase(ficheiroEntradaTotal, 4, listaDeInternadosUCI);
                int[] listaDeMortes = new int[numeroDeLinhas];
                listaDeMortes = PreencherValoresDosDadosPrimeiraFase(ficheiroEntradaTotal, 5, listaDeMortes);

                int indexDataMaisProxima = ProcurarDataMaisProxima(dataPrevisao, listaDeDatas);



            }



            //FIM PREVISOES 2.3
            /****************************************************************
             *                                                              *
             *                   FIM MODO NÃO INTERATIVO                    *
             *                                                              *
             ****************************************************************/

        } else {
            System.out.println("Modo interativo iniciado.");


            /****************************************************************
             *                                                              *
             *                    INICIO MODO INTERATIVO                    *
             *                                                              *
             ****************************************************************/



            if (args.length < 1) {
                String nomeDoFicheiroDeOutput = "ficheiroDeOutput.csv";
                PrintWriter pw = new PrintWriter(nomeDoFicheiroDeOutput);
                String comando = "";
                System.out.println("Introduza o nome do ficheiro");
                ficheiroEntrada = sc.nextLine();
                File ficheiroEntradaTemp = new File(ficheiroEntrada);
                while (!(ficheiroEntradaTemp.exists())) {
                    System.out.println("O ficheiro introduzido não é valido, insira um ficheiro valido para começar, ou escreva sair se quiser sair do programa");
                    ficheiroEntrada = sc.nextLine();
                    if (ficheiroEntrada.equals("sair")) {
                        break;
                    }
                    ficheiroEntradaTemp = new File(ficheiroEntrada);

                }
                if (ficheiroEntradaTemp.exists()) {
                    while (!(comando.equals("sair"))) {

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

                        System.out.println("Se quiser sair introduza o comando sair");
                        System.out.println("Introduza o número da alínea que quer usar");
                        System.out.println("1) visualizar o número total de infetados.");
                        System.out.println("2) comparar o número de casos");
                        System.out.println("3) mudar de ficheiro de entrada");
                        comando = sc.nextLine();
                        switch (comando) {
                            case "1": {

                                System.out.println("Introduza a data inicial na forma dd-MM-aaaa");
                                dataInicial = sc.nextLine();

                                while (ValidarDatasInseridas(dataInicial, listaDeDatas) == false && !(dataInicial.equals("sair"))) {
                                    System.out.println("A data inseirda não é valida, ou não pertence ao ficheiro de entrada, insira uma nova data, ou escreva sair se quiser sair do programa");
                                    dataInicial = sc.nextLine();
                                }
                                if (!(dataInicial.equals("sair"))) {
                                    System.out.println("Introduza a data final na forma dd-MM-aaaa");
                                    dataFinal = sc.nextLine();

                                    while (ValidarDatasInseridas(dataFinal, listaDeDatas) == false && !(dataFinal.equals("sair"))) {
                                        System.out.println("A data inseirda não é valida, ou não pertence ao ficheiro de entrada, insira uma nova data, ou escreva sair se quiser sair do programa");
                                        dataFinal = sc.nextLine();
                                    }
                                    if (!(dataInicial.equals("sair")) && !(dataFinal.equals("sair"))) {

                                        indexInicio = ProcurarPosicaoData(dataInicial, listaDeDatas);
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
                                                System.out.println();
                                                break;
                                            }
                                            case "2": {
                                                int indexPrimeiroDiaMes = ProcurarPrimeiroDiaMes(indexInicio, indexFinal, listaDeDatas);
                                                int indexUltimoDiaMes = ProcurarUltimoDiaMes(indexInicio, indexFinal, listaDeDatas);
                                                int qtMeses = (CalcularQuantidadeDeMesesParaAvaliar(indexPrimeiroDiaMes, indexUltimoDiaMes, listaDeDatas));
                                                if (qtMeses > 1) {
                                                    ModoMensalMostrarDados(indexPrimeiroDiaMes, indexUltimoDiaMes, qtMeses, listaDeDatas, listaDeInfetados, listaDeHospitalizados, listaDeInternadosUCI, listaDeMortes);
                                                } else
                                                    System.out.println("Impossivel fazer a comparação mensal, especifique um intervalo de datas maior");
                                                break;
                                            }
                                        }
                                    }
                                }
                                break;
                            }
                            case "2": {
                                System.out.println("Introduza a data inicial do primeiro periodo temporal na forma dd-MM-aaaa");
                                dataInicial1 = sc.nextLine();

                                while (ValidarDatasInseridas(dataInicial1,listaDeDatas)==false && !(dataInicial1.equals("sair"))){
                                    System.out.println("A data inseirda não é valida, ou não pertence ao ficheiro de entrada, insira uma nova data, ou escreva sair se quiser sair do programa");
                                    dataInicial1= sc.nextLine();
                                }
                                if (!(dataInicial1.equals("sair"))) {
                                    System.out.println("Introduza a data final do primeiro periodo temporal na forma dd-MM-aaaa");
                                    dataFinal1 = sc.nextLine();

                                    while (ValidarDatasInseridas(dataFinal1,listaDeDatas)==false && !(dataFinal1.equals("sair"))){
                                        System.out.println("A data inseirda não é valida, ou não pertence ao ficheiro de entrada, insira uma nova data, ou escreva sair se quiser sair do programa");
                                        dataFinal1 = sc.nextLine();
                                    }
                                    if (!(dataInicial1.equals("sair")) && !(dataFinal1.equals("sair"))) {
                                        System.out.println("Introduza a data inicial do segundo periodo temporal na forma dd-MM-aaaa");
                                        dataInicial2 = sc.nextLine();

                                        while (ValidarDatasInseridas(dataInicial2,listaDeDatas)==false && !(dataInicial2.equals("sair"))){
                                            System.out.println("A data inseirda não é valida, ou não pertence ao ficheiro de entrada, insira uma nova data, ou escreva sair se quiser sair do programa");
                                            dataInicial2 = sc.nextLine();
                                        }
                                        if (!(dataInicial1.equals("sair")) && !(dataFinal1.equals("sair")) && !(dataInicial2.equals("sair"))){
                                        System.out.println("Introduza a data final do primeiro periodo temporar na forma dd-MM-aaaa");
                                        dataFinal2 = sc.nextLine();

                                            while (ValidarDatasInseridas(dataFinal2,listaDeDatas)==false && !(dataFinal2.equals("sair"))){
                                                System.out.println("A data inseirda não é valida, ou não pertence ao ficheiro de entrada, insira uma nova data, ou escreva sair se quiser sair do programa");
                                                dataFinal2 = sc.nextLine();
                                            }
                                            if (!(dataInicial1.equals("sair")) && !(dataFinal1.equals("sair")) && !(dataInicial2.equals("sair")) && !(dataFinal2.equals("sair"))){

                                        indexInicio1 = ProcurarPosicaoData(dataInicial1, listaDeDatas);
                                        indexFinal1 = ProcurarPosicaoData(dataFinal1, listaDeDatas);
                                        indexInicio2 = ProcurarPosicaoData(dataInicial2, listaDeDatas);
                                        indexFinal2 = ProcurarPosicaoData(dataFinal2, listaDeDatas);
                                        double[] medias = new double[3];
                                        double[] desvioPadrao = new double[3];
                                        System.out.println();
                                        System.out.println("infetados");
                                        CompararPeriodosDeTempo(indexInicio1, indexFinal1, indexInicio2, indexFinal2, listaDeInfetados);
                                        medias = MostrarMedias(indexInicio1, indexFinal1, indexInicio2, indexFinal2, listaDeInfetados);
                                        desvioPadrao = CalcularDesvioPadrao(medias, listaDeInfetados, indexInicio1, indexInicio2);
                                        System.out.printf("medias: %.4f     %.4f     %.4f\n", medias[0], medias[1], medias[2]);
                                        System.out.printf("Desvio padrão: %.4f     %.4f     %.4f\n", desvioPadrao[0], desvioPadrao[1], desvioPadrao[2]);
                                        System.out.println();
                                        System.out.println("hospitalizados");
                                        CompararPeriodosDeTempo(indexInicio1, indexFinal1, indexInicio2, indexFinal2, listaDeHospitalizados);
                                        medias = MostrarMedias(indexInicio1, indexFinal1, indexInicio2, indexFinal2, listaDeHospitalizados);
                                        desvioPadrao = CalcularDesvioPadrao(medias, listaDeHospitalizados, indexInicio1, indexInicio2);
                                        System.out.printf("medias: %.4f     %.4f     %.4f\n", medias[0], medias[1], medias[2]);
                                        System.out.printf("Desvio padrão: %.4f     %.4f     %.4f\n", desvioPadrao[0], desvioPadrao[1], desvioPadrao[2]);
                                        System.out.println();
                                        System.out.println("internados na UCI");
                                        CompararPeriodosDeTempo(indexInicio1, indexFinal1, indexInicio2, indexFinal2, listaDeInternadosUCI);
                                        medias = MostrarMedias(indexInicio1, indexFinal1, indexInicio2, indexFinal2, listaDeInternadosUCI);
                                        desvioPadrao = CalcularDesvioPadrao(medias, listaDeInternadosUCI, indexInicio1, indexInicio2);
                                        System.out.printf("medias: %.4f     %.4f     %.4f\n", medias[0], medias[1], medias[2]);
                                        System.out.printf("Desvio padrão: %.4f     %.4f     %.4f\n", desvioPadrao[0], desvioPadrao[1], desvioPadrao[2]);
                                        System.out.println();
                                        System.out.println("obitos");
                                        CompararPeriodosDeTempo(indexInicio1, indexFinal1, indexInicio2, indexFinal2, listaDeMortes);
                                        medias = MostrarMedias(indexInicio1, indexFinal1, indexInicio2, indexFinal2, listaDeMortes);
                                        desvioPadrao = CalcularDesvioPadrao(medias, listaDeMortes, indexInicio1, indexInicio2);
                                        System.out.printf("medias: %.4f     %.4f     %.4f\n", medias[0], medias[1], medias[2]);
                                        System.out.printf("Desvio padrão: %.4f     %.4f     %.4f\n", desvioPadrao[0], desvioPadrao[1], desvioPadrao[2]);
                                        System.out.println();
                                        System.out.println("Quer gravar os resultados num ficheiro?");
                                        comando = sc.nextLine();
                                        switch (comando) {
                                            case "sim": {
                                                // String nomeDoFicheiroDeOutput = "";
                                                System.out.println("Introduza o nome do ficheiro de output");
                                                //  nomeDoFicheiroDeOutput = sc.nextLine();

                                                pw.println("infetados");
                                                CompararPeriodosDeTempoParaFicheiro(indexInicio1, indexFinal1, indexInicio2, indexFinal2, listaDeInfetados, listaDeDatas, pw);
                                                medias = MostrarMedias(indexInicio1, indexFinal1, indexInicio2, indexFinal2, listaDeHospitalizados);
                                                desvioPadrao = CalcularDesvioPadrao(medias, listaDeHospitalizados, indexInicio1, indexInicio2);
                                                pw.printf("medias: %.4f     %.4f     %.4f\n", medias[0], medias[1], medias[2]);
                                                pw.printf("Desvio padrão: %.4f     %.4f     %.4f\n", desvioPadrao[0], desvioPadrao[1], desvioPadrao[2]);
                                                pw.println();
                                                pw.println("hospitalizados");
                                                CompararPeriodosDeTempoParaFicheiro(indexInicio1, indexFinal1, indexInicio2, indexFinal2, listaDeHospitalizados, listaDeDatas, pw);
                                                medias = MostrarMedias(indexInicio1, indexFinal1, indexInicio2, indexFinal2, listaDeHospitalizados);
                                                desvioPadrao = CalcularDesvioPadrao(medias, listaDeHospitalizados, indexInicio1, indexInicio2);
                                                pw.printf("medias: %.4f     %.4f     %.4f\n", medias[0], medias[1], medias[2]);
                                                pw.printf("Desvio padrão: %.4f     %.4f     %.4f\n", desvioPadrao[0], desvioPadrao[1], desvioPadrao[2]);
                                                pw.println();
                                                pw.println("internados na UCI");
                                                CompararPeriodosDeTempoParaFicheiro(indexInicio1, indexFinal1, indexInicio2, indexFinal2, listaDeInternadosUCI, listaDeDatas, pw);
                                                medias = MostrarMedias(indexInicio1, indexFinal1, indexInicio2, indexFinal2, listaDeInternadosUCI);
                                                desvioPadrao = CalcularDesvioPadrao(medias, listaDeInternadosUCI, indexInicio1, indexInicio2);
                                                pw.printf("medias: %.4f     %.4f     %.4f\n", medias[0], medias[1], medias[2]);
                                                pw.printf("Desvio padrão: %.4f     %.4f     %.4f\n", desvioPadrao[0], desvioPadrao[1], desvioPadrao[2]);
                                                pw.println();
                                                pw.println("obitos");
                                                CompararPeriodosDeTempoParaFicheiro(indexInicio1, indexFinal1, indexInicio2, indexFinal2, listaDeMortes, listaDeDatas, pw);
                                                medias = MostrarMedias(indexInicio1, indexFinal1, indexInicio2, indexFinal2, listaDeMortes);
                                                desvioPadrao = CalcularDesvioPadrao(medias, listaDeMortes, indexInicio1, indexInicio2);
                                                pw.printf("medias: %.4f     %.4f     %.4f\n", medias[0], medias[1], medias[2]);
                                                pw.printf("Desvio padrão: %.4f     %.4f     %.4f\n", desvioPadrao[0], desvioPadrao[1], desvioPadrao[2]);
                                                pw.println();

                                                break;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                break;
                            }
                            case "3": {
                                System.out.println("Introduza o nome de um novo ficheiro de entrada valido");
                                String ficheiroEntradaBackup = ficheiroEntrada;
                                ficheiroEntrada = sc.nextLine();
                                ficheiroEntradaTemp = new File(ficheiroEntrada);
                                while (!(ficheiroEntradaTemp.exists()) && !(ficheiroEntrada.equals("sair"))){
                                    System.out.println("O ficheiro introduzido não era valido, introduza um ficheiro valido, se quiser continuar a usar o ficheiro anterior escreva sair, ou escreva o nome deste");
                                    ficheiroEntrada = sc.nextLine();
                                    ficheiroEntradaTemp = new File(ficheiroEntrada);
                                }
                                if (ficheiroEntrada.equals("sair")){
                                    ficheiroEntrada = ficheiroEntradaBackup;
                                }
                                break;
                            }
                        }
                    }
                   pw.close();
                }else {
                    System.out.println("Saida por escolha do utilizador");
                }
            }
        }
        /***************************************************************
         *                                                              *
         *                     FIM MODO INTERATIVO                      *
         *                                                              *
         ****************************************************************/



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

    //ANALISE SEMANAL

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

    public static void CompararPeriodosDeTempoParaFicheiro (int indexInicio1,int indexFinal1,int indexInicio2,int indexFinal2,int[] arrayDeDados,Date[] listaDeDatas,PrintWriter pw) throws FileNotFoundException {


        while (indexFinal1 > indexInicio1 && indexFinal2 > indexInicio2) {

            pw.print(formato.format(listaDeDatas[indexInicio1])+ ": " + arrayDeDados[indexInicio1] + "     ");
            pw.print(formato.format(listaDeDatas[indexInicio2])+ ": " + arrayDeDados[indexInicio2] + "     ");
            pw.println(("Diferença entre dias"+ ": " + ((arrayDeDados[indexInicio2]) - (arrayDeDados[indexInicio1]))));
            indexInicio1++;
            indexInicio2++;
        }
    }

    //FIM 2.2

    //INICIO PREVISAO
    public static double[][] MultiplicarMatrizes(double[][] primeiraMatriz, double[][] segundaMatriz) {
        int qtLinhas1 = primeiraMatriz.length;
        int qtColunas1 = primeiraMatriz[0].length;
        int qtColunas2 = segundaMatriz[0].length;

        double[][] matrizResultante = new double[qtLinhas1][qtColunas2];

        for(int i = 0; i < qtLinhas1; i++) {
            for (int j = 0; j < qtColunas2; j++) {
                for (int k = 0; k < qtColunas1; k++) {
                    matrizResultante[i][j] += primeiraMatriz[i][k] * segundaMatriz[k][j];
                }
            }
        }
        return matrizResultante;
    }

    public static void MostrarValoresMatriz(double[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.println(i + " " + j + ": " + matriz[i][j]);
            }
        }
    }

    public static double[][] LerFicheiroMatriz(String ficheiroMatriz) throws FileNotFoundException {
        Scanner fin = new Scanner(new File(ficheiroMatriz));
        Scanner fin1 = new Scanner(new File(ficheiroMatriz));
        int qtLinhas = 0;
        int qtLinhasValidas = 0;
        String ultimaLinha = "";
        while (fin.hasNextLine()) {
            String linha = fin.nextLine();
            if (linha != ""){
                ultimaLinha = linha;
                qtLinhasValidas++;
            }
            qtLinhas++;
        }
        fin.close();

        int tamanhoMatriz = (int) Math.sqrt(qtLinhasValidas);
        double[][] matrizProbalidades = new double[tamanhoMatriz][tamanhoMatriz];

        while (fin1.hasNextLine()){
            String linha = fin1.nextLine();
            if (linha != ""){
                String[] valoresLinha = linha.split("=");
                int i = Integer.parseInt(valoresLinha[0].split("")[1]) - 1;
                int j = Integer.parseInt(valoresLinha[0].split("")[2]) - 1;
                double valor = Double.parseDouble(valoresLinha[1]);
                matrizProbalidades[i][j] = valor;
            }
        }

        fin1.close();

        return matrizProbalidades;
    }

    public static int ProcurarDataMaisProxima(String dataPrevisao, Date[] listaDeDatas) throws ParseException {
        int posicaoData = ProcurarPosicaoData(dataPrevisao, listaDeDatas);
        if (posicaoData == -1){
            posicaoData = listaDeDatas.length - 1;
        }

        return posicaoData;
    }

    public static boolean ValidarDatasInseridas(String data, Date[] listaDeDatas) throws ParseException {

        boolean resultado = false;
        for (int i = 0; i < listaDeDatas.length; i++) {
            if (data.equals(formato3.format(listaDeDatas[i]))){
                resultado = true;
            }
        }
        return resultado;
    }

}