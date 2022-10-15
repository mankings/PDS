import java.io.FileNotFoundException;

public class ex2 {
    public static void main(String[] args) throws FileNotFoundException {
        FilterInterface reader = new TermFilter(
                new VowelFilter(
                        new CapitalizationFilter(
                                new NormalizationFilter(
                                        new TextReader("text.txt")
                                )
                        )
                )
        );
        while (reader.hasNext()) {
            System.out.println(reader.next());
        }

    }
}