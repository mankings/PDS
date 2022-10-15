import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TextReader implements FilterInterface {

    public Scanner sc;

    public TextReader(String fileName) throws FileNotFoundException {
        sc = new Scanner(new File(fileName));
    }

    @Override
    public boolean hasNext() {
        return sc.hasNext();
    }

    @Override
    public String next() {
        return sc.nextLine();
    }

}