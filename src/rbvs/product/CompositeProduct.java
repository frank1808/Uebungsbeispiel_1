import java.util.Collection;
import java.util.ArrayList;

public class CompositeProduct extends Product
{

private Collection <Product> containedProducts = new ArrayList <Product> ();
private float discount;

public CompositeProduct ( String name, float discountPercentage )
{
        super( name );

        if ( discountPercentage < 0 ) discountPercentage = 0;
        else if ( discountPercentage > 100 ) discountPercentage = 100;

        this.discount = discountPercentage;
}

public CompositeProduct ( String name, float discountPercentage, Collection<Product> products )
{
        this( name, discountPercentage );
        if ( products != null )
                //this.containedProducts.addAll ( products ); muss jedes Product ueberprueft werden?
                for ( Product produkt : products )
                        this.addProduct ( produkt );
}

public void addProduct ( Product product)
{
        if ( produkt != null )
                this.containedProducts.add ( produkt ); //this.getCOntainedProducts()?
}

public boolean removeProduct ( Product product )
{
        if ( !(this.containedProducts.isEmpty ( ) ) && this.containedProducts.contains ( product ) )
                for ( Product produkt : this.containedProducts ) //this.getCOntainedProducts()?
                        if ( product.equals ( produkt ) )
                                this.containedProducts.remove ( produkt ); // produkt und nicht product weil produkt eigentlich die containedProducts durchsucht
}

public Collection<Product> getProducts ( )
{
        return this.containedProducts ( );
}

public float getPrice ( )
{
        float price = 0;
        if ( !(this.containedProducts.isEmpty ( ) ) )
                for ( Product produkt : this.containedProducts )
                        price +=  produkt.getPrice ( );

        price = ( 100 - this.discountPercentage ) * price;

        return price;

}

public String toString ( )
{
        String ret =  " CompositeProduct [discount = " + this.discountPercentage + ", containedProducts = ";//ohne .toString();

        if ( !(this.containedProducts.isEmpty ( ) ) )
                for ( Product produkt : this.containedProducts )
                        ret +=  produkt.toString ( );

        ret +=  "]";

        return ret;
}

public CompositeProduct deepCopy ( )
{
        return new CompositeProduct ( this.getName ( ), this.discountPercentage, this.containedProducts );
}

}
