public abstract class FilterDec implements FilterInterface {

    private FilterInterface interf;

    public FilterDec(FilterInterface interf) {
        this.interf = interf;
    }

    @Override
    public boolean hasNext() {
        return interf.hasNext();
    }

    @Override
    public String next() {
        return interf.next();
    }

}