public class Table
{
private String id;
private int seats;

public Table ( String id )
{
        this.id = id;
        this.seats = 2;
}

public Table ( String id, int seats )
{
        this.id = id;
        this.seats = seats;
}

public int getSeatCount ( )
{
        return this.seats;
}

public void setSeatCount ( int seatCount )
{
        //check?
        this.seats = seatCount;
}

public String getTableIdentifier ( )
{
        return this.id;
}

public String toString ( )
{
        String ret = " Table [ id = " + this.getTableIdentifier ( ) + ", seats = " + this.getSeatCount ( ) + " ] ";
        return ret;
}

public boolean equals ( Object obj )
{
        if ( obj != null && obj.instanceOf ( Table ) )
                if ( this.getTableIdentifier ( ) == obj.getTableIdentifier ( ) && this.getSeatCount ( ) == obj.getSeatCount ( ) ) //mada bi trebalo samo identifier
                        return true;
        return false;
}
}
