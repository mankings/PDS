public class CapitalizationFilter extends FilterDec {
    public CapitalizationFilter(FilterInterface reader) {
        super(reader);
    }
    @Override
    public String next() {
        String allText = "";
        while (super.hasNext()) {
            allText += super.next().toLowerCase();
        }
        return allText;
    }

}