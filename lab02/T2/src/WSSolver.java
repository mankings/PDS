import getopt.Getopt;

import java.io.File;

public class WSSolver {
    public static void main(String[] args) throws Exception {
        int c;
        String soup_file = null;
        String solution_file = null;
        //
        Getopt g = new Getopt("WSSolver", args, "-:i:t:h;");
        g.setOpterr(false); // We'll do our own error handling
        //
        while ((c = g.getopt()) != -1)
            switch (c)
            {
                case 'i':
                    soup_file = g.getOptarg();
                    break;
                //
                case 't':
                    solution_file = g.getOptarg();
                    break;
                //
                case 'h':
                    System.out.println("$ java WSSolver -i <soup.txt> | -t <solution.txt>");
                    System.out.println("If option '-t' is not defined default file for output is solution.txt");
                    System.exit(0);
                    break;
                //
                case ':':
                    System.out.println("Argument missing for option " +
                            (char)g.getOptopt());
                    System.exit(0);
                    break;
                //
                case '?':
                    System.out.println("The option '" + (char)g.getOptopt() +
                            "' is not valid");
                    System.exit(0);
                    break;
                //
                default:
                    System.out.println("getopt() returned " + c);
                    break;
            }

        assert soup_file != null;
        File file = new File(soup_file);

        boolean result;
        result = file.exists();
        if (result)
            System.out.println(file.getAbsolutePath());

        Soup mySoup = new Soup(file);
        mySoup.PrintSoup();
        mySoup.PrintWordList();
        mySoup.SolveSoup();
        mySoup.PrintSol(solution_file);
    }

}
