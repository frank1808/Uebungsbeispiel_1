public abstract class Product implements IProduct
{
private String name;
private float price;

public Product ( String name )
{
        this.initialize ( name, 0 );
}

public Product ( String name, float price )
{
        this.initialize ( name, price );
}

private void initialize ( String name, float price )
{

        if ( name == null )
                this.name = "";
        else
                this.name = name;

        this.price = price;
}

public String getName ( )
{
        return this.name;
}

public void setName ( String name )
{
        if ( name == null )
                this.name = "";
        else
                this.name = name;
}

public float getPrice ( )
{
        return this.price;
}

public void setPrice ( float price ) throws IllegalArgumentException
{
        if ( price < 0 )
                throw IllegalArgumentException;
        else
                this.price = price;
}

public String toString ( )
{
        String ret = " Product [ name = " + this.getName + ", price = " + this.getPrice() " ] ";
}

public final boolean equals ( Object obj )
{
        if ( obj == null || !(obj.instanceof(Product)) ) return false;
        return this.getName( ).equals( obj.getName( ) );
}

}
