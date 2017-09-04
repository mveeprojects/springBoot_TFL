package Hello;

public class TFLResponsePOJO {

    private final long id;
    private final String content;

    public TFLResponsePOJO(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}