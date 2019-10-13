package rbvs;
import rbvs.product.IProduct;
import rbvs.record.IRecord;
import rbvs.record.Record;
import ict.basics.IDeepCopy;
import  rbvs.product.IProduct;


import java.util.ArrayList;
import java.util.List;

public class Order extends Record implements IDeepCopy
{

private List<IProduct> products = new ArrayList<>( );//?
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
        List<IProduct> ret = new ArrayList<> ( );//?

        for ( IProduct produkt : this.products )
                ret.add ( (IProduct) produkt.deepCopy ( ) );

        return ret;
}

public boolean setState ( OrderState newStatus )
{
        if ( this.currentState == OrderState.OPEN )
        {
                this.currentState = newStatus;
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
        if ( obj == null || !(obj instanceof Order ) )
                return false;

        if ( this.currentState  != ( (Order) obj ).currentState || !( this.table.equals ( ( (Order) obj ).getTable ( ) ) ) ) //ovde obj mora sa getTable a ne sa Table
                return false;

        List<IProduct> objProducts = ( (Order) obj ).getProducts ( ); //um nicht immer wieder deepCopy von obj.products zu erstellen

        if ( this.products == null && objProducts == null )
                return true;

        if ( this.products == null || objProducts == null )
                return false;

        if ( this.products.size ( ) != objProducts.size ( ) )
                return false;

        for ( IProduct produkt : this.products )
                if ( !( objProducts.contains ( produkt ) ) )
                        return false;

        return true;
}

}
