package gson;

public class WorkResponse {
    private String title;
    private int publication;

    public WorkResponse(WorkWithPath wwp) {
        this.title = wwp.getTitle();
        this.publication = wwp.getPublication();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublication() {
        return publication;
    }

    public void setPublication(int publication) {
        this.publication = publication;
    }
}
