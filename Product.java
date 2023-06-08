public class Product {
    private final String productName;

    private int quantity;

    Product(String pName) {
        productName = pName;
        quantity = 0;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void addProductToList() {
        ++quantity;
    }

    public void removeProductFromList() {
        if (quantity > 0) {
            --quantity;
        }
    }

    public boolean isOnTheList() {
        return quantity != 0;
    }

    public void removeAllProductsOfThisType() {
        quantity = 0;
    }
}
