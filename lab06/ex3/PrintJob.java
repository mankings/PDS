public class PrintJob implements Runnable {
    private static int nextJobId = 0;
    private int jobId;
    private Document doc;

    public PrintJob(Document doc) {
        this.jobId = this.nextJobId;
        this.nextJobId++;
        this.doc = doc;
    }

    public int getJobId() {
        return this.jobId;
    }

    public Document getDoc() {
        return this.doc;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.err.println(e.getStackTrace());
        }
    }

}