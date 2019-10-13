public class Record implements IRecord
{

private long id;

public Record ( long identifier )
{
        this.id = identifier;
}

public long getIdentifier ( )
{
        return this.id;
}

}
