import java.io.*;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class gameStart {
    public static int[] countLineAndColumn() throws IOException {
        //Scanner in = new Scanner(new File("./GameStart.csv"));

        BufferedReader in = new BufferedReader(new java.io.FileReader("./GameStart.csv"));

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

        in.close();

        int arrayLineColumn[] = new int[2];
        arrayLineColumn[0] = line;
        arrayLineColumn[1] = words;

        return arrayLineColumn;

    }

    public static String[][] createMatrix(int[] arrayLineColumn) throws FileNotFoundException {

        //variables
        String linha;
        String[] linhaDividida;
        String[][] matrix = new String[arrayLineColumn[0]][arrayLineColumn[1]];
        int row = 0;


        //open file
        File fileContent = new File("./GameStart.csv");
        Scanner scannerFile = new Scanner(fileContent);

        //next line
        //scannerFile.nextLine();


        while (scannerFile.hasNextLine()) {
            linha = scannerFile.nextLine();
            linhaDividida = linha.split(";");


            for (int i = 0; i < linhaDividida.length; i++) {
                //row só mudará de valor depois que acabar o for
                matrix[row][i] = linhaDividida[i];
            }
            row++;
        }

        scannerFile.close();
        return matrix;
    }

    public static void printMatrix(String[][] matrix, int[] arrayLineColumn) throws FileNotFoundException {
        //imprimir matriz
        for (int l = 0; l < arrayLineColumn[0]; l++) {

            for (int c = 0; c < arrayLineColumn[1]; c++) {
                System.out.print(" " + matrix[l][c] + " ");
            }
            System.out.println(" ");
        }
    }

    public static double calculateTotal(String[][] matrix, int[] arrayLineColumn) throws FileNotFoundException {
        double total = 0;

        for (int i = 1; i < arrayLineColumn[0]; i++) {
            if (matrix[i][8] != null) {
                total += Double.parseDouble(matrix[i][8]);
            }
        }

        return total;
    }

    public static double profitGames(String[][] matrix, int[] arrayLineColumn) throws FileNotFoundException {
        double total = 0;
        String game;

        for (int i = 1; i < arrayLineColumn[0]; i++) {
            if (matrix[i][8] != null) {
                total += Double.parseDouble(matrix[i][8]) * 0.10;
            }
        }

        return total;
    }

    public static void dataClient(String[][] matrix, int[] arrayLineColumn) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);

        String idClient;
        System.out.println("Digite o id do cliente que deseja pesquisar: ");
        idClient = input.next();

        for (int i = 1; i < arrayLineColumn[0]; i++) {
            if (matrix[i][1] != null) {
                if (matrix[i][1].equals(idClient)) {
                    System.out.println(" Nome do Cliente: " + matrix[i][2] + " \n Contato: " + matrix[i][3] + "  \n Email: " + matrix[i][4]);
                    return;
                }
            }
        }


    }

    public static void moreExpensiveGame(String[][] matrix, int[] arrayLineColumn) throws FileNotFoundException {

        double expensiveGame = Double.parseDouble(matrix[1][8]);
        String clientName = "";

        for (int i = 1; i < arrayLineColumn[0]; i++) {
            if (matrix[i][8] != null) {
                if (Double.parseDouble(matrix[i][8]) > expensiveGame) {
                    expensiveGame = Double.parseDouble(matrix[i][8]);
                    clientName = matrix[i][2];

                }
            }
        }
        System.out.println("Jogo mais caro: " + expensiveGame + "\n Nome do Cliente: " + clientName);


    }

    public static void gameTitle(String[][] matrix, int[] arrayLineColumn) throws FileNotFoundException {
        String names [] = new String[arrayLineColumn[0]];
        int count =0;
        boolean hasName = false;

        for (int i = 1; i < arrayLineColumn[0]; i++) { //percorre a matriz
            for(int n=0; n< names.length; n++){ //percorre o array de nomes salvos
                if(names[n] != null && names[n].equals(matrix[i][7])){
                    hasName = true;
                }
            }
            if (matrix[i][7] != null && hasName==false) { //não existe esse nome ainda no array de names
                names[count] = matrix[i][7]; //depois que inclui o nome no array fazemos ++ para a proxima posição
                count++;
                System.out.println(matrix[i][7]);

            }
            hasName=false; //tem que mudar para false para a proxima rodada do for

        }



    }

    public static void dataBookPublisher(String[][] matrix, int[] arrayLineColumn) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);

        String bookPublisher;
        System.out.println("Digite o nome da editora que deseja pesquisar: ");
        bookPublisher = input.next();

        for (int i = 1; i < arrayLineColumn[0]; i++) {
            if (matrix[i][5] != null) {
                if (matrix[i][5].equals(bookPublisher)) {
                    System.out.println(" *****" + matrix[i][5] + " *****" + " \n Categoria: " + matrix[i][6] + "  \n Jogos: " + matrix[i][7]);
                }
            }
        }


    }



    public static void menu() throws IOException {
        Scanner input = new Scanner(System.in);

        int option, optionMenu;
        String password;

        int[] resultLineColumn = countLineAndColumn();
        String[][] matrix = createMatrix(resultLineColumn);
        do {
            System.out.println("Escolha uma opção: 1 - ADMIN | 2 -CLIENTE  | 3 -FINALIZAR PROGRAMA");
            option = input.nextInt();


            if (option == 1) {

                System.out.println("Digite a sua password ");
                password = input.next();

                if (password.equals("123456789")) {
                    do {
                        System.out.println("Menu: ");
                        System.out.println("1. Imprimir ficheiro \n 2. Vendas Totais  \n 3. Lucro  \n 4. Dados do cliente \n 5. Jogo mais caros e clientes que compraram \n6.Sair ");
                        optionMenu = input.nextInt();


                        switch (optionMenu) {
                            case 1:
                                System.out.println("Imprimir ficheiro");
                                printMatrix(matrix, resultLineColumn);
                                break;
                            case 2:

                                System.out.println("Vendas Totais: \n " + calculateTotal(matrix, resultLineColumn));

                                break;
                            case 3:
                                System.out.println(" Lucro : \n " + profitGames(matrix, resultLineColumn));
                                break;
                            case 4:
                                System.out.println("Dados do cliente: ");
                                dataClient(matrix, resultLineColumn);
                                break;
                            case 5:
                                System.out.println("Jogo mais caro e clientes que compraram");
                                moreExpensiveGame(matrix, resultLineColumn);
                                break;
                            case 6:
                                System.out.println("sair");

                                break;
                            default:
                                System.out.println("opção inválida");
                                break;
                        }
                    } while (optionMenu != 6);
                } else{
                    do {
                        System.out.println("Password incorreta");
                        System.out.println("Digite a sua password ");
                        password = input.next();
                    } while (!password.equals("123456789"));
                }

            } else if (option == 2) {
                do {
                    System.out.println("Menu: ");
                    System.out.println("1. Títulos de jogos \n 2. Editora \n 3.Sair");
                    optionMenu = input.nextInt();

                    switch (optionMenu) {
                        case 1:
                            System.out.println("Títulos de jogos: ");
                            gameTitle(matrix, resultLineColumn);
                            break;
                        case 2:
                            System.out.println(" Editora");
                            dataBookPublisher(matrix, resultLineColumn);
                            break;
                        case 3:
                            System.out.println("Sair");
                            break;
                        default:
                            System.out.println("opção inválida");
                            break;
                    }
                } while (optionMenu != 3);
            } else if (option == 3) {
                System.out.println("O programa será finalizado");
            } else {
                System.out.println("opção inválida");
            }
        } while (option != 3);

    }

    public static void main(String[] args) throws IOException {

        menu();
    }
}

