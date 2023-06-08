import java.util.ArrayList;

public class Category {
    private final String categoryName;

    private final ArrayList<Product> products;

    Category(String cName) {
        categoryName = cName;
        products = new ArrayList<>();
    }

    public boolean addProduct(Product product) {
        return products.add(product);
    }

    public String getCategoryName() {
        return categoryName;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public boolean isEmpty() {
        for (Product i : products) {
            if (i.getQuantity() != 0) {
                return false;
            }
        }
        return true;
    }

    public Product findProductInCategory(String productName) {
        for (Product i : products) {
            if (i.getProductName().equalsIgnoreCase(productName)) {
                return i;
            }
        }
        return null;
    }

    public void removeAllProductsFromCategory() {
        for (Product i : products) {
            i.removeAllProductsOfThisType();
        }
    }
}
