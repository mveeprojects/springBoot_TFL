package TubeLineStatus.POJOs.TFLResponsePOJOs;

public class LineStatuses
{
    private String id;

    private String created;

    private String[] validityPeriods;

    private String $type;

    private String statusSeverity;

    private String statusSeverityDescription;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getCreated ()
    {
        return created;
    }

    public void setCreated (String created)
    {
        this.created = created;
    }

    public String[] getValidityPeriods ()
    {
        return validityPeriods;
    }

    public void setValidityPeriods (String[] validityPeriods)
    {
        this.validityPeriods = validityPeriods;
    }

    public String get$type ()
    {
        return $type;
    }

    public void set$type (String $type)
    {
        this.$type = $type;
    }

    public String getStatusSeverity ()
    {
        return statusSeverity;
    }

    public void setStatusSeverity (String statusSeverity)
    {
        this.statusSeverity = statusSeverity;
    }

    public String getStatusSeverityDescription ()
    {
        return statusSeverityDescription;
    }

    public void setStatusSeverityDescription (String statusSeverityDescription)
    {
        this.statusSeverityDescription = statusSeverityDescription;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", created = "+created+", validityPeriods = "+validityPeriods+", $type = "+$type+", statusSeverity = "+statusSeverity+", statusSeverityDescription = "+statusSeverityDescription+"]";
    }
}
