import getopt.Getopt;

import java.io.File;

public class WSGenerator {
    public static void main(String[] args) throws Exception {
        int c;
        String wordList_file = null;
        String generatedSoup_file = null;
        int grid_size = 0;
        //
        Getopt g = new Getopt("WSGenerator", args, "-:i:s:t:h;");
        g.setOpterr(false); // We'll do our own error handling
        //
        while ((c = g.getopt()) != -1)
            switch (c)
            {
                case 'i':
                    wordList_file = g.getOptarg();
                    break;
                //
                case 's':
                    grid_size = Integer.parseInt(g.getOptarg());
                    break;
                //
                case 't':
                    generatedSoup_file = g.getOptarg();
                    break;
                //
                case 'h':
                    System.out.println("$ java WSGenerator -i <wordList.txt> -s <grid_size> | -t <generatedSoup.txt>");
                    System.out.println("If option '-t' is not defined default file for output is soup.txt");
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

        assert wordList_file != null;
        File wordFile = new File(wordList_file);

        boolean result;
        result = wordFile.exists();
        if (result)
            System.out.println(wordFile.getAbsolutePath());

        Soup mySoup = new Soup(wordFile, grid_size);
        mySoup.GenerateSoup();
        mySoup.PrintGeneratedSoup(generatedSoup_file);
    }

}
