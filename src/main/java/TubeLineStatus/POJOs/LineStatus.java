package TubeLineStatus.POJOs;

public class LineStatus {
    private String name;
    private String statusSeverityDescription;

    public LineStatus() {
    }

    public LineStatus(String name, String statusSeverityDescription) {
        this.name = name;
        this.statusSeverityDescription = statusSeverityDescription;
    }

    public String getName() {
        return name;
    }

    public String getStatusSeverityDescription() {
        return statusSeverityDescription;
    }
}
