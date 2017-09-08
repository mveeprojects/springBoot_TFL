package TubeLineStatus.POJOs.TFLResponsePOJOs;

public class Crowding
{
    private String $type;

    public String get$type ()
    {
        return $type;
    }

    public void set$type (String $type)
    {
        this.$type = $type;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [$type = "+$type+"]";
    }
}