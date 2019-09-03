package thymeleaf;

public class Content {

    private String subject;

    public Content(String subject) {
        super();
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Content [subject=" + subject + "]";
    }
}

