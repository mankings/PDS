import java.io.*;
import java.util.*;

public class WSSolver {
    public static void main(String[] args) {
        String iFileName = "";
        int gridSize = 0;
        String oFileName = "";

        // há 2 parâmetros obrigatórios
        // -i -> input file name
        // -s -> gridSize
        if (args.length < 4) {
            System.err.println("Too few arguments.");
            System.exit(1);
        } else if (args.length > 6) {
            System.err.println("Too many arguments.");
            System.exit(1);
        }

        boolean iOption = false;
        boolean sOption = false;
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-i":
                    if (!args[i + 1].contains(".txt")) {
                        System.err.println("Input file must be a .txt file.");
                        System.exit(1);
                    }
                    iOption = true;
                    iFileName = args[++i];
                    break;
                case "-s":
                    if (!args[i + 1].matches("[0-9]+")) {
                        System.err.println("Invalid grid size. Provide an integer.");
                        System.exit(1);
                    }
                    sOption = true;
                    gridSize = Integer.parseInt(args[++i]);
                    break;
                case "-o":
                    if (!args[i + 1].contains(".txt")) {
                        System.err.println("Output file must be a .txt file.");
                        System.exit(1);
                    }
                    oFileName = args[++i];
                    break;
                default:
                    System.err.println("Unrecognized option:" + args[i]);
                    System.exit(1);
            }
        }

        if (!(iOption && sOption)) {
            System.err.println("-i and -s parameters are necessary.");
            System.exit(1);
        }

        Generator generator = new Generator();
        char[][] populatedPuzzle = null;
        if (generator.readFile(iFileName)) { // if file was read sucessfully
            generator.createPuzzle(gridSize);
            populatedPuzzle = generator.populatePuzzle(gridSize);
        } else {
            System.err.println("Error: file couldn't be read.");
            System.exit(1);
        }

        if (oFileName.isEmpty()) {
            // dar print na consola
            for (int i = 0; i < gridSize; i++) {
                for (int j = 0; j < gridSize; j++) {
                    System.out.print(populatedPuzzle[i][j]);
                }
                System.out.println();
            }

            ArrayList<String> words = generator.getWords();
            int count = 0;
            for (String word : words) {
                System.out.print(word + " ");
                count++;
                if (count % 8 == 0) // 8 palavras por linha
                    System.out.println();
            }
        } else {
            // escrever no ficheiro
            PrintStream fileStream = null;
            try {
                fileStream = new PrintStream(new File(oFileName));
            } catch (FileNotFoundException e) {
                System.out.println("Error: Output file not found.");
                System.exit(1);
            }

            System.setOut(fileStream);

            for (int i = 0; i < gridSize; i++) {
                for (int j = 0; j < gridSize; j++) {
                    System.out.print(populatedPuzzle[i][j]);
                }
                System.out.println();
            }

            ArrayList<String> words = generator.getWords();
            int count = 0;
            for (String word : words) {
                System.out.print(word + " ");
                count++;
                if (count % 8 == 0) // 8 palavras por linha
                    System.out.println();
            }
        }

        LinkedHashMap<String[], int[]> map; //map com os resultados (nomes, posicoes)
        char[][] table = null; //futura tabela com os carateres
        String[] words = null; //futura lista de palavras

        HashMap<char[][], String[]> info = read(iFileName); //info ira ter a tabela no ficheiro, e um array com as palavras para procurar

        for (char[][] i : info.keySet()) {table = i;} //guardando as informacoes nas vars certas
        for (String[] i : info.values()) {words = i;} //eu sei que isto é estupido mas n estou a  ver outra maneira, depois crio um tipo de dados

        printTable(table);
        printWords(words);


        map = bruteForceSolver(table, words); //solver

        printResults(map);
    }
    /////////////////////////
    public static LinkedHashMap<String[], int[]> bruteForceSolver(char[][] table, String[] wordsUnsorted){
        LinkedHashMap<String[], int[]> foundWords = new LinkedHashMap<>(); //resultados
        char[][] filterTable = new char[table.length][table.length]; //tabela para ser impress
        boolean notSubstring = false;

        String[] words = wordsUnsorted.clone();
        Arrays.sort(words, Comparator.comparingInt(String::length).reversed());

        for (char[] row: filterTable) //inicializar toda a tabela com -
            Arrays.fill(row, '-');


        for (String word: words) { //para cada palavra
            char[] wordChars = word.toUpperCase().toCharArray(); //dar uppercase à palavra

            for (int i=0; i<table.length; i++) {
                for (int j=0; j<table[i].length; j++) {
                    if (wordChars[0] == table[i][j]) { //se a primeira letra da palavra corresponde à letra na tabela
                        //left
                        for (int index=0;index < wordChars.length; index++) { //index para percorrer pela palavra
                            if ( ((j - index) < 0) || (wordChars[index] != table[i][j - index]) ) break;
                            //^caso ultrapasse os limites da tabela [((j - index) < 0)] ou o carater for diferente, break
                            else if (index == (wordChars.length - 1)) {
                                //^caso seja o ultimo carater
                                for (int help=0;help < wordChars.length; help++) { //percorre a tabela filtro na mesma direcao, e copia a palavra para essa posição
                                    if (filterTable[i][j - help] != wordChars[help]) notSubstring = true;
                                    if (notSubstring) filterTable[i][j - help] = table[i][j - help]; //se n perceberes manda msg
                                }
                                if (notSubstring) {
                                    foundWords.put(new String[] {word, "Left"}, new int[] {i, j});
                                    notSubstring = false;
                                    break;
                                }
                            }
                        }

                        //right
                        for (int index=0;index < wordChars.length; index++) {
                            if ( ((j + index) > (table.length - 1)) || (wordChars[index] != table[i][j+index]) ) break;
                            else if (index == (wordChars.length - 1)) {
                                for (int help=0;help < wordChars.length; help++) {
                                    if (filterTable[i][j + help] != wordChars[help]) notSubstring = true;
                                    if (notSubstring) filterTable[i][j + help] = table[i][j + help];
                                }
                                if (notSubstring) {
                                    foundWords.put(new String[] {word, "Right"}, new int[] {i, j});
                                    notSubstring = false;
                                    break;
                                }
                            }
                        }

                        //up
                        for (int index=0;index < wordChars.length; index++) {
                            if ( ((i - index) < 0) || (wordChars[index] != table[i - index][j]) ) break;
                            else if (index == (wordChars.length - 1)) {
                                for (int help=0;help < wordChars.length; help++) {
                                    if (filterTable[i - help][j] != wordChars[help]) notSubstring = true;
                                    if (notSubstring) filterTable[i - help][j] = table[i - help][j];
                                }
                                if (notSubstring) {
                                    foundWords.put(new String[] {word, "Up"}, new int[] {i, j});
                                    notSubstring = false;
                                    break;
                                }
                            }
                        }

                        //down
                        for (int index=0;index < wordChars.length; index++) {
                            if ( ((i + index) > (table.length - 1)) || (wordChars[index] != table[i + index][j]) ) break;
                            else if (index == (wordChars.length - 1)) {
                                for (int help=0;help < wordChars.length; help++) {
                                    if (filterTable[i + help][j] != wordChars[help]) notSubstring = true;
                                    if (notSubstring) filterTable[i + help][j] = table[i + help][j];
                                }
                                if (notSubstring) {
                                    foundWords.put(new String[] {word, "Down"}, new int[] {i, j});
                                    notSubstring = false;
                                    break;
                                }
                            }
                        }

                        //upLeft
                        for (int index=0;index < wordChars.length; index++) {
                            if ( ((i - index) < 0) || ((j - index) < 0) || (wordChars[index] != table[i - index][j - index]) ) break;
                            else if (index == (wordChars.length - 1)) {
                                for (int help=0;help < wordChars.length; help++) {
                                    if (filterTable[i - help][j - help] != wordChars[help]) notSubstring = true;
                                    if (notSubstring) filterTable[i - help][j - help] = table[i - help][j - help];
                                }
                                if (notSubstring) {
                                    foundWords.put(new String[] {word, "UpLeft"}, new int[] {i, j});
                                    notSubstring = false;
                                    break;
                                }
                            }
                        }

                        //upRight
                        for (int index=0;index < wordChars.length; index++) {
                            if ( ((i - index) < 0) || ((j + index) > (table.length - 1)) || (wordChars[index] != table[i - index][j + index]) ) break;
                            else if (index == (wordChars.length - 1)) {
                                for (int help=0;help < wordChars.length; help++) {
                                    if (filterTable[i - help][j + help] != wordChars[help]) notSubstring = true;
                                    if (notSubstring) filterTable[i - help][j + help] = table[i - help][j + help];
                                }
                                if (notSubstring) {
                                    foundWords.put(new String[] {word, "UpRight"}, new int[] {i, j});
                                    notSubstring = false;
                                    break;
                                }
                            }
                        }

                        //downLeft
                        for (int index=0;index < wordChars.length; index++) {
                            if ( ((i + index) > (table.length - 1)) || ((j - index) < 0) || (wordChars[index] != table[i + index][j - index]) ) break;
                            else if (index == (wordChars.length - 1)) {
                                for (int help=0;help < wordChars.length; help++) {
                                    if (filterTable[i + help][j - help] != wordChars[help]) notSubstring = true;
                                    if (notSubstring) filterTable[i + help][j - help] = table[i + help][j - help];
                                }
                                if (notSubstring) {
                                    foundWords.put(new String[] {word, "DownLeft"}, new int[] {i, j});
                                    notSubstring = false;
                                    break;
                                }
                            }
                        }

                        //downRight
                        for (int index=0;index < wordChars.length; index++) {
                            if ( ((i + index) > (table.length - 1)) || ((j + index) > (table.length - 1)) || (wordChars[index] != table[i + index][j + index]) ) break;
                            else if (index == (wordChars.length - 1)) {
                                for (int help=0;help < wordChars.length; help++) {
                                    if (filterTable[i + help][j + help] != wordChars[help]) notSubstring = true;
                                    if (notSubstring) filterTable[i + help][j + help] = table[i + help][j + help];
                                }
                                if (notSubstring) {
                                    foundWords.put(new String[] {word, "DownRight"}, new int[] {i, j});
                                    notSubstring = false;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        LinkedHashMap<String[], int[]> sortedWordsMap = new LinkedHashMap<>();
        while (foundWords.keySet().size() != 0) {

            for (String word : wordsUnsorted){
                for (Map.Entry<String[], int[]> set : foundWords.entrySet()) {
                    if (set.getKey()[0].equals(word)){
                        sortedWordsMap.put(set.getKey(), set.getValue());
                        break;
                    }
                }
                for (Map.Entry<String[], int[]> set : sortedWordsMap.entrySet())
                foundWords.remove(set.getKey());
            }
        }
        printTable(filterTable); //print the table
        return sortedWordsMap;
    }
    /////////////////////////

    public static HashMap<char[][], String[]> read(String fileName) {
        int width = 0; //largura da tabela
        char[][] table = null; //futura tabela
        String[] wordList = null; //futuras palavras
        HashMap<char[][], String[]> info = new HashMap<>(); //maneira de as devolver
        File text = new File(fileName);
        int lineCounter = 0;
        try {
            Scanner scanner = new Scanner(text);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (lineCounter == 0) {
                    width = line.length(); //na primeira linha, guardar o seu comprimeiro (para verificar que é quadrada)
                    table = new char[width][width]; //crio uma tabela com esse comprimeiro
                }

                lineCounter++;

                if ( (lineCounter >= lineCount(fileName)) && (line.matches("[a-zA-Z ,;]+")) && !(line.matches("[A-Z ,;]+")) ){
                    //^significa que é a ultima linha do ficheiro && apenas contem aqueles carateres && nao é so maiusculas
                    wordList = new String[(line.split("[ ;,]").length)];
                    wordList= line.split("[ ;,]"); //guarda entao as palavras
                    break;
                } else if (!(lineCounter <= width)) { // quando now é quadrado
                    System.err.println("An error has ocurred!");
                    System.exit(0);
                } else if ( (line.matches("[A-Z]+")) && (line.length() == width) ) { //a linha apenas tem maiusculas e o mesmo comprimento
                    for (int index = 0; index < width; index++) {
                        table[lineCounter - 1][index] = line.charAt(index);
                    }
                }
                else { //quando acontece merda
                    System.err.println("An error has ocurred!");
                    System.exit(0);
                }
                //falta verificar que a tabela n é aior em y que em x acho (lineCounter== width)
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Invalid file name!");
            System.exit(0);
        }

        info.put(table, wordList); //guarda as ceanas

        return info;
    }

    public static int lineCount(String filename) { //conta as linhas num ficheiro
        int count = 0;

        try {
            File file = new File(filename);

            Scanner sc = new Scanner(file);

            while(sc.hasNextLine()) {
                sc.nextLine();
                count++;
            }
            sc.close();
            return count;
        } catch (Exception e) {
            e.getStackTrace();
            return 0;
        }
    }

    public static void printTable(char[][] table) {
        for (int r = 0; r < table.length; r++) {       //for loop for row iteration.
            for (int c = 0; c < table[r].length; c++) {   //for loop for column iteration.
                System.out.print(table[r][c] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printWords(String[] words) {
        for(int i=0;i<words.length;i++) {   //length is the property of the array
            System.out.printf("%s; ",words[i]);
        }
        System.out.println("\n");
    }

    public static void printResults(LinkedHashMap<String[], int[]> res) {
        for (Map.Entry<String[], int[]> set :
                res.entrySet()) {
            System.out.println(Arrays.toString(set.getKey()) + " -> "
                    + Arrays.toString(set.getValue()));
        }
    }

    public static boolean checkSubstring(char[] currentWord, String word){
        StringBuilder revWord = new StringBuilder();
        word = word.toUpperCase();
        revWord = new StringBuilder(word);
        revWord.reverse();

        String newWord = String.valueOf(currentWord).toUpperCase();
        System.out.printf("%s %s %s\n", newWord, word, revWord);
        if (word.contains(newWord) && !word.equals(newWord) || revWord.toString().contains(newWord) && !revWord.toString().equals(newWord)) {
            System.out.println("lwjqnfjkwebfkjwb");
        }
        return false;
    }
    /////////////////////////
}
