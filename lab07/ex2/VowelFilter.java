public class VowelFilter extends FilterDec {
    VowelFilter(FilterInterface reader) {
        super(reader);
    }
    @Override
    public String next() {
        String allText = "";
        while (super.hasNext()) {
            allText += super.next().replaceAll("[aeiouAEIOU]", "");
        }
        return allText;
    }

}