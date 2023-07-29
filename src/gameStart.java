import java.io.*;
import java.util.Scanner;

public class gameStart {
    public static int[] countLineAndColumn() throws IOException {
        //Scanner in = new Scanner(new File("./GameStart.csv"));

        BufferedReader in = new BufferedReader(new FileReader("./GameStart.csv"));

        //BufferedReader in = new BufferedReader(new java.io.FileReader(new File("./GameStart.csv")));

        String linha;
        int line = 0;
        int words = 0;

        while ((linha = in.readLine()) != null) {

            String[] itensDaLinha;

            line += 1;

            itensDaLinha = linha.split(";");
            words = itensDaLinha.length;
        }
        System.out.println(line);
        System.out.println(words);

        in.close();

        int arrayLineColumn[] = new int[2];
        arrayLineColumn[0] = line;
        arrayLineColumn[1] = words;

        return arrayLineColumn;

    }

    public static void createMatrix(int[] arrayLineColumn) throws FileNotFoundException {

        //variables
        String linha;
        String[] linhaDividida;
        String[][] matrix = new String[arrayLineColumn[0]][arrayLineColumn[1]];
        int row = 0;


        //open file
        File fileContent = new File("./GameStart.csv");
        Scanner scannerFile = new Scanner(fileContent);

        //next line
        scannerFile.nextLine();


        while (scannerFile.hasNextLine()) {
            linha = scannerFile.nextLine();
            linhaDividida = linha.split(";");


            for (int i = 0; i < linhaDividida.length; i++) {
                //row só mudará de valor depois que acabar o for
                matrix[row][i] = linhaDividida[i];
            }
            row++;
        }
        //imprimir matriz
        for (int l = 0; l < arrayLineColumn[0]; l++) {

            for (int c = 0; c < arrayLineColumn[1]; c++) {
                System.out.print(" " + matrix[l][c] + " ");
            }
            System.out.println(" ");
        }

        scannerFile.close();
    }

    public static void menu() throws FileNotFoundException{
        Scanner input = new Scanner(System.in);

        int option, optionMenu;
        String password;

        do{
        System.out.println("Escolha uma opção: 1 - ADMIN | 2 -CLIENTE  | 3 -FINALIZAR PROGRAMA");
        option = input.nextInt();
        if(option ==1 ) {
            System.out.println("Digite a sua password ");
           password = input.next();


    if(password.equals("123456789")){
    do{
        System.out.println("Menu: ");
        System.out.println("1. Imprimir ficheiro | 2. Vendas Totais | 3. Lucro | 4. Dados dos clientes | 5. Jogo mais caros e clientes que compraram 6.Sair ");
        optionMenu = input.nextInt();

        switch (optionMenu) {
            case 1:
                System.out.println("Imprimir ficheiro");
                break;
            case 2:
                System.out.println("Vendas Totais");
                break;
            case 3:
                System.out.println(" Lucro ");
                break;
            case 4:
                System.out.println("Dados dos clientes");
                break;
            case 5:
                System.out.println("Jogos mais caros e clientes que compraram");
                break;
            case 6:
                System.out.println("sair");

                break;
            default:
                System.out.println("opção inválida");
                break;
        }
    } while (optionMenu != 6);
}
else{
    do{
        System.out.println("Password incorreta");
        System.out.println("Digite a sua password ");
        password = input.next();
    } while (!password.equals("123456789"));
}


        }else if (option == 2) {
            do {
                System.out.println("Menu: ");
                System.out.println("1. Títulos de jogos | 2. Editora | 3.Sair");
                optionMenu = input.nextInt();

                switch (optionMenu) {
                    case 1:
                        System.out.println("Títulos de jogos");
                        break;
                    case 2:
                        System.out.println(" Editora");
                        break;
                    case 3:
                        System.out.println("Sair");
                        break;
                    default:
                        System.out.println("opção inválida");
                        break;
                }
            } while (optionMenu != 3);
        }else if (option == 3){
            System.out.println("O programa será finalizado");
        }
        else{
            System.out.println("opção inválida");
        }
        } while (option != 3);

        }

    public static void main(String[] args) throws IOException {
        int[] resultLineColumn = countLineAndColumn();
        createMatrix(resultLineColumn);
        menu();
    }
    }

