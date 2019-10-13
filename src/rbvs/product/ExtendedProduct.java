package rbvs.product;
public class ExtendedProduct extends SimpleProduct {
    private ExtendedProduct savedState;

    public ExtendedProduct(String name, float price) {
        super(name, price);
    }

    public ExtendedProduct(ExtendedProduct product) {
        super(product.getName(), product.getPrice());
    }

    public void setName(String name) {
        this.savedState = this.deepCopy();
        super.setName(name);
    }

    public void setPrice(float price) throws IllegalArgumentException {
        this.savedState = this.deepCopy();
        super.setPrice(price);
    }

    public String toString() {
        return super.toString();
    }

    public boolean undo() {
        if (this.savedState == null || this.equals(this.savedState))
            return false;
        else {
            this.setName(this.savedState.getName());
            this.setPrice(this.savedState.getPrice());
            return true;
        }
    }

    public ExtendedProduct deepCopy() {
        //savedState soll null sein
        return new ExtendedProduct(this.getName(), this.getPrice());
    }

}
