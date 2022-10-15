import java.text.Normalizer;

public class NormalizationFilter extends FilterDec {
    public NormalizationFilter(FilterInterface reader) {
        super(reader);
    }
    @Override
    public String next() {
        String allText = "";
        String text = "";
        while (super.hasNext()) {
            text  = Normalizer.normalize(super.next(), Normalizer.Form.NFKD);
            text = text.replaceAll("[^\\p{ASCII}]", "");
            text = text.replaceAll("[.!?\\-,]", "");
            allText += text;
        }
        return allText;
    }

}