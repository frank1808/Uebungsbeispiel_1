package rbvs;
import rbvs.product.IProduct;

public class DuplicateProductException extends Exception
{

private IProduct product;
private static final long serialVersionUID = 1L;

public DuplicateProductException ( IProduct product )
{
        this.product = product;
}

public String getMessage ( )
{
        String ret = "Product " + this.product.toString ( ) + " is a duplicate!";//null?
        return ret;
}


}
