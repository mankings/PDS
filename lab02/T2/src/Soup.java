import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;
import java.io.File;

public class Soup {
    private char[][] matrix;
    private char[][] sol;
    private int size;
    private ArrayList<String> wordList;
    private HashMap<String, ArrayList<Integer>> map;
    private HashMap<Integer, String> conv;

    public Soup(File f) throws Exception {
        ReadSoup(f);
        map = new HashMap<>();  // to store word respective position in the grid
        conv = new HashMap<>(); // for conversion from integer to string
        conv.put(0, "RIGHT");
        conv.put(1, "UP");
        conv.put(2, "LEFT");
        conv.put(3, "DOWN");
        conv.put(4, "DOWNRIGHT");
        conv.put(5, "DOWNLEFT");
        conv.put(6, "UPLEFT");
        conv.put(7, "UPRIGHT");
    }

    public Soup(File wordFile, int n) throws Exception {

        if (n > 40)
            throw new Exception("Soup max size is 40!");

        size = n;
        ReadWordlist(wordFile);
    }

    private void ReadSoup(File f) throws Exception {
        int iter = 0;
        wordList = new ArrayList<>();

        Scanner input = new Scanner(f);

        String line = input.nextLine();

        if (line.isBlank())
            throw new Exception("File must not contain empty lines!");

        int n = line.length();

        size = n;
        matrix = new char[n][n];
        sol = new char[n][n];

        System.arraycopy(line.toCharArray(), 0, matrix[iter], 0, size);
        System.arraycopy(".".repeat(size).toCharArray(), 0, sol[iter++], 0, size);

        while (input.hasNextLine()) {
            line = input.nextLine();

            if (line.isBlank())
                throw new Exception("File must not contain empty lines!");

            if (!line.matches("[A-Z]+")) {
                for (String word : line.split("[ ;,]+")) {
                    if (!word.matches("[a-zA-Z]+"))
                        throw new Exception("Only alphabetic characters allowed!");
                    if (wordList.contains(word))
                        System.out.printf("Duplicated word '%s' will be ignored!\n", word);
                    else wordList.add(word);
                }
            }
            else {
                n = line.length();

                if (n != size || iter > size - 1)
                    throw new Exception("Soup is a squared matrix!");

                if (n > 40)
                    throw new Exception("Soup max size is 40!");

                System.arraycopy(line.toCharArray(), 0, matrix[iter], 0, size);
                System.arraycopy(".".repeat(size).toCharArray(), 0, sol[iter++], 0, size);
            }
        }

        input.close();
    }

    private void ReadWordlist(File f) throws Exception {
        wordList = new ArrayList<>();

        Scanner input = new Scanner(f);

        while (input.hasNextLine()) {
            String line = input.nextLine();
            if (!line.matches("[A-Z]+")) {
                for (String word : line.split("[ ;,]+")) {
                    if (!word.matches("[a-zA-Z]+"))
                        throw new Exception("Only alphabetic characters allowed!");
                    if (wordList.contains(word))
                        System.out.printf("Duplicated word '%s' will be ignored!\n", word);
                    else wordList.add(word);
                }
            } else throw new Exception("word must not be all uppercase letters!");
        }

        input.close();
    }

    public void PrintSoup() {
        System.out.println("\nSOUP: ");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++)
                System.out.printf("%c  ", matrix[i][j]);
            System.out.println();
        }
    }

    public void PrintWordList() {
        System.out.println("\nWORDS: ");
        for (String s : wordList) {
            System.out.println(s);
        }
    }

    private void PrintWordData() {
        System.out.printf("%dx%d grid\n\n", size, size);
        System.out.println("RESULTS: ");
        for (String key : wordList) {
            if (map.containsKey(key)) {
                ArrayList<Integer> word_data = map.get(key);
                int line = word_data.get(0);
                int col = word_data.get(1);
                int dir = word_data.get(2);
                String s = line + "," + col;
                System.out.printf("%-16s %10d %10s %10s\n", key, key.length(), s, conv.get(dir));
            } else System.out.printf("Error: '%s' not found!\n", key);
        }
    }

    public void PrintSol(String solFile) throws FileNotFoundException {

        if (solFile ==  null)
            solFile = "solution.txt";

        PrintStream console = System.out;
        PrintStream stream = new PrintStream(solFile);
        System.setOut(stream);

        PrintWordData();
        System.out.println();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++)
                System.out.printf("%c  ", (sol[i][j] == 'X') ? matrix[i][j] : sol[i][j]);
            System.out.println();
        }

        System.setOut(console);
    }

    public void PrintGeneratedSoup(String genFile) throws FileNotFoundException {

        if (genFile ==  null)
            genFile = "soup.txt";

        PrintStream console = System.out;
        PrintStream stream = new PrintStream(genFile);
        System.setOut(stream);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++)
                System.out.printf("%c", matrix[i][j]);
            System.out.println();
        }

        for (String s : wordList)
            System.out.println(s);

        System.setOut(console);
    }


    public void GenerateSoup() throws Exception {
        matrix = new char[size][size];
        int ntry;

        System.out.printf("Creating %dx%d grid, with the words...", size, size);
        PrintWordList();

        for (String word : wordList) {
            ntry = 0;
            while (!generateRandomPos(word) && ntry++ < 500); // generate random position for each word
            if (ntry == 500) throw new Exception("Error: unable to insert '" + word + "'!\n");
            // if ntry breaks the maximum limit of 500, we assume soup is impossible to create and show error
        }

        Random rand = new Random();
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                if (matrix[i][j] == '\0')
                    matrix[i][j] = (char) (rand.nextInt(90-65+1) + 65);
        // fill with random uppercase letters all empty characters in the grid
    }

    public void SolveSoup() {
        ArrayList<String> backup = new ArrayList<>(wordList);
        backup.sort(Comparator.comparingInt(String::length).reversed());

        // sort by length (bigger to smaller)
        // and solve in that order (to check for
        // smaller words included in bigger ones
        // giving false positives)

        for (String word : backup) {
            if (getHorizontal(word)) continue;
            getDiagonal13(word);

        }

        // transpose matrix to find vertical and 2-4 diagonals
        rotateR();
        for (String word : backup)
            if (!map.containsKey(word)) {
                if (getVertical(word)) continue;
                getDiagonal24(word);
            }
        rotateL(); // return grid to its original state
    }

    private boolean getHorizontal(String word) {
        return getParallel(word, 0);
    }

    private boolean getVertical(String word) {
        return getParallel(word, 1);
    }

    // Diagonals by quadrant

    private boolean getDiagonal24(String word) {
        return getDiagonal(word, 0);
    }

    private boolean getDiagonal13(String word) {
        return getDiagonal(word, 1);
    }

    private boolean getParallel(String word, int opt) {
        int idx; // stores column where word is found in current grid
        for (int j = 0; j < size; j++) { // for each line
            if ((idx = String.valueOf(matrix[j]).indexOf(word.toUpperCase())) != -1) { // if word is found
                ArrayList<Integer> word_data = new ArrayList<>(); // array to store word info
                int line, col, dir;
                if (opt == 0) { // RIGHT
                    line = j + 1;
                    col = idx + 1;
                    dir = 0;
                }                                              // convert pos in current grid
                else { // UP                                   // to real pos and store it
                    line = size - idx;
                    col = j + 1;
                    dir = 1;
                }
                word_data.add(line);
                word_data.add(col);
                word_data.add(dir);
                if (markSol(--line, --col, dir, word.length())) {  // mark word in solution grid
                    map.put(word, word_data);                      // make sure solution is not inside bigger word
                    return true;                                   // word found
                }
            }
            if ((idx = reverse(String.valueOf(matrix[j])).indexOf(word.toUpperCase())) != -1) { // if reverse word found
                ArrayList<Integer> word_data = new ArrayList<>(); // array to store word info
                int line, col, dir;
                if (opt == 0) { // LEFT
                    line = j + 1;
                    col = size - idx;
                    dir = 2;
                }                                               // convert pos in current grid
                else { // DOWN                                  // to real pos and store it
                    line = idx + 1;
                    col = j + 1;
                    dir = 3;
                }
                word_data.add(line);
                word_data.add(col);
                word_data.add(dir);
                if (markSol(--line, --col, dir, word.length())) {  // mark word in solution grid
                    map.put(word, word_data);                      // make sure solution is not inside bigger word
                    return true;                                   // word found
                }
            }
        }
        return false;
    }

    private boolean getDiagonal(String word, int opt) {
        String key = word.toUpperCase();             // word is checked in uppercase
        int[] val;
        if ((val = getDiagonalMatch(key)) != null) { // check if there is a match
            int i = val[0];
            int j = val[1];          // return position in current grid
            ArrayList<Integer> word_data = new ArrayList<>(); // array to store word info
            int line, col, dir;
            if (opt == 0) { // DOWNRIGHT
                line = size - j;
                col = i + 1;
                dir = 4;
            }                                 // convert pos in current grid
            else { // DOWNLEFT                // to real pos and store it
                line = i + 1;
                col = j + 1;
                dir = 5;
            }
            word_data.add(line);
            word_data.add(col);
            word_data.add(dir);
            if (markSol(--line, --col, dir, word.length())) { // mark word in solution grid
                map.put(word, word_data);                     // make sure solution is not inside bigger word
                return true;                                  // word found
            }
        }
        key = reverse(key);                              // word is reversed
        if ((val = getDiagonalMatch(key)) != null) {     // check if there is a match
            int i = val[0];
            int j = val[1];              // return position in current grid
            ArrayList<Integer> word_data = new ArrayList<>();  // array to store word info
            int line, col, dir;
            if (opt == 0) { // UPLEFT
                line = size - (j - word.length() + 1);
                col = i + word.length();
                dir = 6;
            }                                             // convert pos in current grid
            else { //UPRIGHT                              // to real pos and store it
                line = i + word.length();
                col = j + 1 - word.length() + 1;
                dir = 7;
            }
            word_data.add(line);
            word_data.add(col);
            word_data.add(dir);
            if (markSol(--line, --col, dir, word.length())) { // mark word in solution grid
                map.put(word, word_data);                     // make sure solution is not inside bigger word
                return true;                                  // word found
            }
        }
        return false;
    }

    // method to find word in diagonal
    private int[] getDiagonalMatch(String word) {
        int k;
        for (int line = 0; line < size; line++) {
            for (int col = 0; col < size; col++) {
                if (matrix[line][col] == word.charAt(0)) { // if first letter of word is found
                    for(k = 0; k < word.length(); k++)                                                         // check neighbour letter
                        if(line + k > size - 1 || col - k < 0 || matrix[line + k][col - k] != word.charAt(k))  // if not a match break
                            break;                                                                             // else continue checking
                                                                                                               // neighbours of next letter
                    if (k == word.length())                     // check if k letters (word) are a match
                        return new int[] {line, col};           // and return pos
                }
            }
        }
        return null;
    }

    private boolean markSol(int line, int col, int dir, int len) {
        int matches = 0;

        // if positions are all marked
        // if matches equals the word length
        // that means that word is included in other words
        // and return false else return true

        switch (dir) {
            case 0:
                for (int j = col; j < col + len; j++)
                    if (sol[line][j] == 'X') matches++;
                    else sol[line][j] = 'X';
                break;
            case 1:
                for (int i = line; i > line - len; i--)
                    if (sol[i][col] == 'X') matches++;
                    else sol[i][col] = 'X';
                break;
            case 2:
                for (int j = col; j > col - len; j--)
                    if (sol[line][j] == 'X') matches++;
                    else sol[line][j] = 'X';
                break;
            case 3:
                for (int i = line; i < line + len; i++)
                    if (sol[i][col] == 'X') matches++;
                    else sol[i][col] = 'X';
                break;
            case 4:
                for (int i = line, j = col; i < line + len; i++, j++)
                    if (sol[i][j] == 'X') matches++;
                    else sol[i][j] = 'X';
                break;
            case 5:
                for (int i = line, j = col; i < line + len; i++, j--)
                    if (sol[i][j] == 'X') matches++;
                    else sol[i][j] = 'X';
                break;
            case 6:
                for (int i = line, j = col; i > line - len; i--, j--)
                    if (sol[i][j] == 'X') matches++;
                    else sol[i][j] = 'X';
                break;
            case 7:
                for (int i = line, j = col; i > line - len; i--, j++)
                    if (sol[i][j] == 'X') matches++;
                    else sol[i][j] = 'X';
                break;
        }

        return matches != len;
    }

    private boolean generateRandomPos(String word) {

        Random rand = new Random();
        boolean avail = true; // check for position available
        int i, j, line = 0, col = 0, ntry = 0, matches;

        // ntry - so we can try to generate position using different setting
        // once the limit of 10 is passed

        // matches - to prevent for smaller word to be included in bigger word

        int dir = rand.nextInt(4); // randomize direction (Horizontal, Vertical, Diagonal13, Diagonal24)
        int rev = rand.nextInt(2); // randomize string reversing (right -> left ...)
        if (rev == 1) word = reverse(word);

        word = word.toUpperCase();

        switch (dir) {
            case 0: // HORIZONTAL

                while (true) {

                    if (ntry == 10) {  // if limit is passed
                        avail = false; // no position available and break
                        break;         // the while loop
                    }

                    matches = 0; // initialize matches to 0
                    line = rand.nextInt(size); // randomize line
                    col = rand.nextInt(size - word.length() + 1); // randomize column

                    for (j = col; j < word.length() + col; j++)
                        if (matrix[line][j] != '\0') // if position is empty
                            if (matrix[line][j] != word.charAt(j - col)) // and character is different
                                break; // break ( start again)
                            else matches++; // if character is equal matches goes up by one

                    if (j == word.length() + col && matches != word.length()) break; // if available position is found
                    ntry++;                                                          // then break the while loop
                }                                                                    // aviail remains true, else increment
                                                                                     // number of tries
                if (avail)
                    for (j = col; j < word.length() + col; j++)            // if avail remains true, meaning we found
                        matrix[line][j] = word.charAt(j - col);            // a good position then we can add word to grid

                break;

            case 1: // VERTICAL (works the same way)
                while (true) {
                    if (ntry == 10) {
                        avail = false;
                        break;
                    }
                    matches = 0;
                    line = rand.nextInt(size - word.length() + 1);
                    col = rand.nextInt(size);
                    for (i = line; i < word.length() + line; i++)
                        if (matrix[i][col] != '\0')
                            if(matrix[i][col] != word.charAt(i - line))
                                break;
                            else matches++;
                    if (i == word.length() + line && matches != word.length()) break;
                    ntry++;
                }
                if (avail)
                    for (i = line; i < word.length() + line; i++)
                        matrix[i][col] = word.charAt(i - line);
                break;

            case 2: // DIAGONAL 24 (works the same way)
                while (true) {
                    if (ntry == 10) {
                        avail = false;
                        break;
                    }
                    matches = 0;
                    line = rand.nextInt(size - word.length() + 1);
                    col = rand.nextInt(size - word.length() + 1);
                    for (i = line, j = col; i < line + word.length(); i++, j++)
                        if (matrix[i][j] != '\0')
                            if(matrix[i][j] != word.charAt(i - line))
                                break;
                            else matches++;
                    if (i == word.length() + line && matches != word.length()) break;
                    ntry++;
                }
                if (avail)
                    for (i = line, j = col; i < line + word.length(); i++, j++)
                        matrix[i][j] = word.charAt(i - line);
                break;
            case 3: // DIAGONAL 13 (works the same way)
                while (true) {
                    if (ntry == 10) {
                        avail = false;
                        break;
                    }
                    matches = 0;
                    line = rand.nextInt(size - word.length() + 1);
                    col = size - rand.nextInt(size - word.length() + 1) - 1;
                    for (i = line, j = col; i < line + word.length(); i++, j--)
                        if (matrix[i][j] != '\0')
                            if (matrix[i][j] != word.charAt(i - line))
                                break;
                            else matches++;
                    if (i == word.length() + line && matches != word.length()) break;
                    ntry++;
                }
                if (avail)
                    for (i = line, j = col; i < line + word.length(); i++, j--)
                        matrix[i][j] = word.charAt(i - line);
                break;
        }
        return ntry != 10; // returns false if loop breaks because no position was available
                           // with those settings else return true
    }


    private String reverse(String input) {
        char[] word = input.toCharArray();
        int low = 0, high = word.length -1;
        while (low < high) {
            char temp = word[low];
            word[low] = word[high];
            word[high] = temp;
            low++;
            high--;
        }
        return new String(word);
    }

    private void rotateR() {
        for (int i = 0; i < size; i++) {
            for (int j = i; j < size; j++) {
                char temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        //then we reverse the elements of each row
        for (int i = 0; i < size; i++) {
            //logic to reverse each row i.e 1D Array.
            int low = 0, high = size - 1;
            while (low < high) {
                char temp = matrix[i][low];
                matrix[i][low] = matrix[i][high];
                matrix[i][high] = temp;
                low++;
                high--;
            }
        }
    }

    private void rotateL() {
        for (int i = 0; i < size; i++) {
            for (int j = i; j < size; j++) {
                char temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for (int i = 0; i < size; i++) {
            //logic to swap columns
            int low = 0, high = size - 1;
            while (low < high) {
                char temp = matrix[low][i];
                matrix[low][i] = matrix[high][i];
                matrix[high][i] = temp;
                low++;
                high--;
            }
        }
    }
}