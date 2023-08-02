import java.io.*;
import java.util.Scanner;


public class gameStart {
    public static int[] countLineAndColumn() throws IOException {
        int line = 0;
        int words = 0;

        BufferedReader objReader = null;
        try {
            String strCurrentLine;

            objReader = new BufferedReader(new FileReader("./GameStart.csv"));

            while ((strCurrentLine = objReader.readLine()) != null) {
                String[] itensDaLinha;

                line += 1;

                itensDaLinha = strCurrentLine.split(",");
                words = itensDaLinha.length;
            }



        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {
                if (objReader != null)
                    objReader.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
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
            linhaDividida = linha.split(",");


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

    public static double profitGames(double gain) throws FileNotFoundException {
        double total =0;
     total= (gain * 0.10);

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
                    System.out.println("************************************CLIENTE*********************************");
                    System.out.println(" Nome do Cliente: " + matrix[i][2] + " \n Contato: " + matrix[i][3] + "  \n Email: " + matrix[i][4]);
                    System.out.println("******************************************************************++*********");
                    return;
                }
            }
        }


    }

    public static void moreExpensiveGame(String[][] matrix, int[] arrayLineColumn) throws FileNotFoundException {

        double expensiveGame = Double.parseDouble(matrix[1][8]);
        String clientName = "";
        double game = 0;

        for (int i = 1; i < arrayLineColumn[0]; i++) {
            if (matrix[i][8] != null) {
                if (Double.parseDouble(matrix[i][8]) > expensiveGame) {
                    expensiveGame = Double.parseDouble(matrix[i][8]);
                }

            }
        }

        for (int i = 1; i < arrayLineColumn[0]; i++) {
            if (Double.parseDouble(matrix[i][8]) == expensiveGame) {
                clientName = matrix[i][2];
                System.out.println( "Nome do Cliente: " + clientName);
            }
        }

        System.out.println("Jogo mais caro: " + expensiveGame );



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

        String categories [] = new String[arrayLineColumn[0]];
        String bookPublisher;
        boolean hasCategory=false;
        int count =0;

        System.out.println("Digite o nome da editora que deseja pesquisar: ");
        bookPublisher = input.next();

        //separar as categorias
        for (int i = 1; i < arrayLineColumn[0]; i++) {
            for(int c=0; c< categories.length; c++){ //percorre o array de categorias salvas
                if(categories[c] != null && categories[c].equals(matrix[i][6])){
                   hasCategory=true;
                }
            }

            if(matrix[i][6] != null && hasCategory==false){
                categories[count] = matrix[i][6];
                count++;
                //System.out.println(matrix[i][6]);

            }

            hasCategory = false;



        //criar um array para cada categoria e armazenar os jogos dentre dele - NÃO CONSEGUI
       /* String categoryName [];
        int countName =0;
        for(int l=0; l< categories.length; l++){
           categoryName= new String[]{categories[l]};
            countName++;
           if(categoryName.equals(l)){
               categoryName =  matrix[i][7];
           }
            System.out.println(categories[l]);
        }*/

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

                while (!password.equals("123456789")){
                    System.out.println("Password incorreta");
                    System.out.println("Digite a sua password ");
                    password = input.next();
                }

                if (password.equals("123456789")) {
                    do {
                        System.out.println("Menu: ");
                        System.out.println("1. Imprimir ficheiro \n 2. Vendas Totais  \n 3. Lucro  \n 4. Dados do cliente \n 5. Jogo mais caros e clientes que compraram \n6.Voltar ao menu anterior ");
                        optionMenu = input.nextInt();


                        switch (optionMenu) {
                            case 1:
                                System.out.println("Imprimir ficheiro");
                                printMatrix(matrix, resultLineColumn);
                                break;
                            case 2:
                                System.out.println("***********************VENDAS TOTAIS***************************");
                                System.out.println("Quantidade de vendas:" + (resultLineColumn[0] -1 )+"\n " +  "Vendas Totais: \n " + calculateTotal(matrix, resultLineColumn));
                                System.out.println("*********************************************************************");

                                break;
                            case 3:
                                double gain = calculateTotal(matrix, resultLineColumn);
                                System.out.println("***********************LUCRO*******************");
                                System.out.println(" Lucro : \n " + profitGames(gain));
                                System.out.println("************************************************");
                                break;
                            case 4:

                                dataClient(matrix, resultLineColumn);
                                break;
                            case 5:
                                System.out.println("***************JOGO MAIS CARO E CLIENTES QUE COMPRARAM***************");
                                moreExpensiveGame(matrix, resultLineColumn);
                                System.out.println("*********************************************************************");
                            case 6:
                                System.out.println("Voltar ao menu anterior");

                                break;
                            default:
                                System.out.println("opção inválida");
                                break;
                        }
                    } while (optionMenu != 6);
                }

            } else if (option == 2) {
                do {
                    System.out.println("Menu: ");
                    System.out.println("1. Títulos de jogos \n 2. Editora \n 3.Voltar ao menu anterior");
                    optionMenu = input.nextInt();

                    switch (optionMenu) {
                        case 1:
                            System.out.println("***************TITULO DO JOGO***************");
                            gameTitle(matrix, resultLineColumn);
                            System.out.println("********************************************");
                            break;
                        case 2:
                            System.out.println(" Editora");
                            dataBookPublisher(matrix, resultLineColumn);
                            break;
                        case 3:
                            System.out.println("Voltar ao menu anterior");
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

