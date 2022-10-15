public class TermFilter extends FilterDec {
    private int wordsRead;
    private String[] paragraphWords = null;

    public TermFilter(FilterInterface reader) {
        super(reader);
        this.wordsRead = 0;
    }

    public boolean hasNext() {
        if (super.hasNext()) {
            return true;
        } else if (this.paragraphWords != null && this.wordsRead < this.paragraphWords.length) {
            return true;
        } else {
            return false;
        }
    }

    public String next() {
        if (!this.hasNext()) {
            return null;
        }

        if (this.paragraphWords != null && this.wordsRead < this.paragraphWords.length) {
            this.wordsRead++;
            return this.paragraphWords[this.wordsRead - 1];
        } else if (super.hasNext()) {
            this.paragraphWords = super.next().split(" ");
            this.wordsRead = 1;
            return this.paragraphWords[0];
        } else {
            return null;
        }
    }
}