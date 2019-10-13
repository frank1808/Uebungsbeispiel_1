package rbvs;
import rbvs.product.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

public class Restaurant
{

private String name;
private List<Table> tables = new ArrayList<Table> ( );
private List<IProduct> productAssortment = new ArrayList<> ( );//mora Product a ne IProduct
private List<Order> orderHistory = new ArrayList<Order> ( );
private long uniqueOrderIdentifier = 0;

public Restaurant ( String name )
{
        this.name = name;
}

public String getName ( )
{
        return this.name;
}

public boolean createTable ( String tableIdentifier )
{
        if ( tableIdentifier == null )
                return false;

        //if ( this.tables != null )// ne treba jer uvek kreiramo List
        for ( Table table : this.tables )
                if ( table.getTableIdentifier ( ).equals ( tableIdentifier ) )
                        return false;

        this.tables.add ( new Table ( tableIdentifier ) );
        return true;
}

public List<String> getTableIdentifiers ( ) //return null wenn keine Tables?
{
        List<String> identifiers = new ArrayList<String>  ( );
        for ( Table table : this.tables )
                identifiers.add ( table.getTableIdentifier ( ) );

        return identifiers;
}

public Table getSpecificTable ( String identifier )
{
        if ( this.tables.isEmpty( ) )
                return null;

        for ( Table table : this.tables )
                if ( table.getTableIdentifier ( ).equals ( identifier ) )
                        return table;

         return null;
}

public boolean addProduct ( IProduct product ) throws DuplicateProductException
{
        if ( product == null )
                return false;

        //ne moze productAssortment.contains jer mora da se koristi evaluation preko equals?
        for ( IProduct produkt : this.productAssortment )
                if ( produkt.equals ( product ) )
                {
                        throw new DuplicateProductException ( produkt );
                        //return false;
                        //ovde ne treba return?
                }

        this.productAssortment.add ( (IProduct) product.deepCopy ( ) );
        return true;

}

public boolean addProduct ( Collection<IProduct> products ) throws DuplicateProductException
{
        if ( products == null )
                return false;

        if ( products.isEmpty ( ) ) //ovo sam namerno izdvojio
                return false; //valjda false treba da vrati?

        boolean ret = true;

        for ( IProduct produkt : products )
                if ( produkt != null && this.addProduct ( produkt ) == false )
                        ret = false;

        return ret;
}

public List<IProduct> getProducts()
{
        List<IProduct> products = new ArrayList<>  ( );

        for ( IProduct produkt : this.productAssortment )
                if ( produkt != null ) //da li ovo treba proveravati?
                        products.add ( (IProduct) produkt.deepCopy( ) );

        return products;
}

public boolean orderProductForTable ( Table table, IProduct product,  int count )
{
        if ( table == null || product == null )
                return false;

        if ( !( this.tables.contains ( table ) && this.productAssortment.contains ( product ) ) )
                return false;

        List<IProduct> products = new ArrayList<>  ( );
        while ( count > 0  )
        {
                products.add ( (IProduct) product.deepCopy ( ) );
                count--;
        }

        Order order = new Order ( generateUniqueIdentifier(), table, products );
        this.orderHistory.add ( order );
        return true;
}

public boolean containsProduct ( IProduct compareProduct )
{
        if ( this.productAssortment.contains ( compareProduct ) )
                return true;
        return false;

}

public IProduct findProduct ( String productName )
{
        for ( IProduct produkt : this.productAssortment )
                if ( produkt.getName().equals( productName ) )
                        return produkt; //welche referenz?
        return null;
}

private IProduct findProduct ( IProduct compareProduct )
{
        for ( IProduct produkt : this.productAssortment )
                if ( produkt.equals( compareProduct ) )
                        return produkt; //welche referenz?
        return null;
}

private long generateUniqueIdentifier()
{
        this.uniqueOrderIdentifier++;
        return this.uniqueOrderIdentifier;
}

public static List<IProduct> generateSimpleProducts ( )
{
        List<IProduct> simpleProducts = new ArrayList<> ( );// must import product.simpleProduct

        simpleProducts.add ( new SimpleProduct ( "sim1", 100 ) );
        simpleProducts.add ( new SimpleProduct ( "sim2", 200 ) );
        simpleProducts.add ( new SimpleProduct ( "sim3", 300 ) );
        simpleProducts.add ( new SimpleProduct ( "sim4", 400 ) );
        simpleProducts.add ( new SimpleProduct ( "sim5", 500 ) );

        return simpleProducts;
}

public static List<IProduct> generateCompositeProducts ( )
{

        //Kennnummer für Produkte: Tiefe-Vater-Ordnungsnummer, Preis Kennnummer*100

        SimpleProduct sim211 = new SimpleProduct ( "sim211", 2110 );
        SimpleProduct sim221 = new SimpleProduct ( "sim221", 2210 );
        SimpleProduct sim231 = new SimpleProduct ( "sim221", 2310 );
        SimpleProduct sim311 = new SimpleProduct ( "sim311", 3110 );
        SimpleProduct sim321 = new SimpleProduct ( "sim321", 3210 );
        SimpleProduct sim331 = new SimpleProduct ( "sim331", 3310 );

        ExtendedProduct ext211 = new ExtendedProduct ( "ext211", 2110 );
        ExtendedProduct ext221 = new ExtendedProduct ( "ext221", 2210 );
        ExtendedProduct ext231 = new ExtendedProduct ( "ext221", 2310 );
        ExtendedProduct ext311 = new ExtendedProduct ( "ext311", 3110 );
        ExtendedProduct ext321 = new ExtendedProduct ( "ext321", 3210 );
        ExtendedProduct ext331 = new ExtendedProduct ( "ext331", 3310 );
        ExtendedProduct ext312 = new ExtendedProduct ( "ext312", 3120 );
        ExtendedProduct ext322 = new ExtendedProduct ( "ext322", 3220 );
        ExtendedProduct ext332 = new ExtendedProduct ( "ext332", 3320 );

        //discountpercentage Tiefe * 10

        CompositeProduct com211 = new CompositeProduct ( "com211", 20, Arrays.asList ( sim311, ext311, ext312 ) );
        CompositeProduct com221 = new CompositeProduct ( "com221", 20, Arrays.asList ( sim321, ext321, ext322 ) );
        CompositeProduct com231 = new CompositeProduct ( "com231", 20, Arrays.asList ( sim331, ext331, ext332 ) );

        CompositeProduct com101 = new CompositeProduct ( "com101", 10, Arrays.asList ( sim211, ext211, com211 )  );
        CompositeProduct com102 = new CompositeProduct ( "com102", 10, Arrays.asList ( sim221, ext221, com221 )  );
        CompositeProduct com103 = new CompositeProduct ( "com103", 10, Arrays.asList ( sim231, ext231, com231 )  );

        return Arrays.asList ( com101, com102, com103 );
}

public static void main ( String[] args )
{
        Restaurant res = new Restaurant ( "Plachutta" );

        res.createTable ( "tab1" );
        res.createTable ( "tab1" );
        res.createTable ( "tab2" );
        res.createTable ( "tab3" );

        try
        {
                res.addProduct (  res.generateSimpleProducts() );
                res.addProduct ( res.generateCompositeProducts() );
        }
        catch ( DuplicateProductException dpe )
        {
                System.out.println ( dpe.getMessage() );
        }


        for ( IProduct produkt : res.getProducts() )
                System.out.println ( produkt.toString () );



        //dusanov test
}

}
