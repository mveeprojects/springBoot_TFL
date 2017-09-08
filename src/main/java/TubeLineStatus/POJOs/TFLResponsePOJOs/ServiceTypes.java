package TubeLineStatus.POJOs.TFLResponsePOJOs;

public class ServiceTypes
{
    private String name;

    private String $type;

    private String uri;

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String get$type ()
    {
        return $type;
    }

    public void set$type (String $type)
    {
        this.$type = $type;
    }

    public String getUri ()
    {
        return uri;
    }

    public void setUri (String uri)
    {
        this.uri = uri;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [name = "+name+", $type = "+$type+", uri = "+uri+"]";
    }
}