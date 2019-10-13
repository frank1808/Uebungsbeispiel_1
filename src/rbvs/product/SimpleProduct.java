package rbvs.product;
public class SimpleProduct extends Product
{
public SimpleProduct ( String name, float price )
{
        super( name, price);
}

public SimpleProduct deepCopy ( )
{
        //return new SimpleProduct ( super.getName( ), super.getPrice( ) );
        return new SimpleProduct ( this.getName( ), this.getPrice( ) );
}

}
