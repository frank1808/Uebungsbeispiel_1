import java.util.List;

public class Order extends Record implements IDeepCopy
{

private List<IProduct> products = new List<IProduct> ( );//?
private Table table;
private OrderState currentState;

public Order ( long identifier, Table table, List<IProduct> products )
{
        super( identifier );
        this.table = table;
        this.products = products;//?
        this.currentState = OrderState.OPEN;
}

public List<IProduct> getProducts ( )
{
        List<IProduct> ret = new List<IProduct> ( );//?

        for ( produkt : this.products )
                ret.add ( produkt.deepCopy ( ) );

        return ret;
}

public boolean setState ( OrderState newStatus )
{
        if ( this.OrderState == OPEN )
        {
                this.OrderState = newStatus;
                return true;
        }

        return true;
}

public OrderState getState ( )
{
        return this.currentState;
}

public boolean isCancelled ( )
{
        return ( this.currentState == OrderState.CANCELLED );
}

public boolean isPaid()
{
        return ( this.currentState == OrderState.PAID );
}

public Table getTable ( )
{
        return this.table;
}

public Order deepCopy ( )
{
        return new Order ( this.getIdentifier(), this.table, this.getProducts ( ) ); //moze ovako .getidentifier()

}

public boolean equals ( Object obj )
{
        if ( obj == null || !(obj.instanceOf ( Order ) ) )
                return false;

        if ( this.currentState  != obj.currentState || !( this.table.equals ( obj.getTable ( ) ) ) ) //ovde obj mora sa getTable a ne sa Table
                return false;

        List<IProduct> objProducts = obj.getProducts ( ); //um nicht immer wieder deepCopy von obj.products zu erstellen

        if ( this.products == null && objProducts == null )
                return true;

        if ( this.products == null || objProducts == null )
                return false;

        if ( this.products.size ( ) != objProducts.size ( ) )
                return false;

        for ( Product produkt : this.products )
                if ( !( objProducts.contains ( produkt ) ) )
                        return false;

        return true;
}

}
