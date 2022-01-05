import java.io.*;
import java.util.Scanner;

public class Projeto {

    public static void main(String[] args) throws Exception {

        lerFicheiroCSV();

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

    }
}
