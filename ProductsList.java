import java.io.*;
import java.util.ArrayList;

public class ProductsList {

    private final ArrayList<Category> categories;

    private final UserInterface userInterface;

    private final String productFile = "productList";

    private final String shoppingListFile = "shoppingList";

    ProductsList() {
        categories = new ArrayList<>();
        userInterface = new UserInterface();
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    private void addCategory(Category category) {
        categories.add(category);
    }

    public Category findCategory(String categoryName) {
        for (Category i : categories) {
            if (i.getCategoryName().equalsIgnoreCase(categoryName)) {
                return i;
            }
        }
        return null;
    }

    public Product findProduct(String productName) {
        Product product;
        for (Category i : categories) {
            if ((product = i.findProductInCategory(productName)) != null) {
                return product;
            }
        }
        return null;
    }

    public void removeAllProducts() {
        for (Category i : categories) {
            i.removeAllProductsFromCategory();
        }
    }

    public boolean isListEmpty() {
        for(Category i: categories) {
            if(!(i.isEmpty())){
                return false;
            }
        }
        return true;
    }


    public void run() {

        try {
            loadProducts();
            loadShoppingList();
        } catch (IOException e) {
            return;
        }

        int userInput = 0;
        while (userInput != 8) {
            userInterface.displayOperationList();
            try {
                userInput = userInterface.getUserDecision();
                runOperation(userInput);
            } catch (IOException e) {
                userInterface.IOExceptionPrompt();
                return;
            }
        }
    }

    public void runOperation(int operationCode) throws IOException {
        switch (operationCode) {
            case 1:
                userInterface.productAdditionOperation(this);
                break;
            case 2:
                userInterface.displayShoppingList(categories);
                break;
            case 3:
                userInterface.displayShoppingListCategory(this);
                break;
            case 4:
                userInterface.listClearedPrompt();
                removeAllProducts();
                break;
            case 5:
                userInterface.removeProductsFromCategoryOperation(this);
                break;
            case 6:
                userInterface.removeProductFromShoppingList(this);
                break;
            case 7:
                saveListToFile();
                break;
            case 8:
                userInterface.exitPrompt();
                break;
            default:
                userInterface.invalidOperationPrompt();
                break;
        }
    }

    public void loadProducts() throws FileNotFoundException, IOException{
        String string;
        Category category = null;
        try (BufferedReader br = new BufferedReader(new FileReader(productFile.concat(".txt")))) {
            while ((string = br.readLine()) != null) {
                if (string.charAt(0) == '#') {
                    category = new Category(string.substring(1));
                    addCategory(category);
                } else {
                    if (category != null) {
                        category.addProduct(new Product(string));
                    }
                }
            }
        } catch (FileNotFoundException p) {
            System.out.println("File does not exist");
            throw p;
        } catch (IOException e) {
            userInterface.IOExceptionPrompt();
            throw e;
        }
    }

    public void saveListToFile() throws IOException {
        try (FileWriter fileWriter = new FileWriter(shoppingListFile.concat(".txt"))) {
            for (Category i : categories) {
                if (!(i.isEmpty())) {
                    fileWriter.write("#".concat(i.getCategoryName().concat("\r\n")));
                    for (Product j : i.getProducts()) {
                        if (j.isOnTheList()) {
                            for(int k = 0; k < j.getQuantity(); ++k){
                                fileWriter.write(j.getProductName().concat("\r\n"));
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Couldn't create file.");
            throw new IOException();
        }
        userInterface.listSavedPrompt();
    }

    public int validateShoppingList() {
        try (BufferedReader br = new BufferedReader(new FileReader(shoppingListFile.concat(".txt")))) {
            String string;
            while ((string = br.readLine()) != null) {
                if (string.charAt(0) == '#') {
                    if (findCategory(string.substring(1)) == null) {
                        return 3;
                    }
                } else {
                    if (findProduct(string) == null) {
                        return 3;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            return 1;
        } catch (IOException p) {
            return 2;
        }
        return 0;
    }

    public void loadShoppingList() throws IOException {
        if (validateShoppingList() != 0) {
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(shoppingListFile.concat(".txt")))) {
            String string;
            while ((string = br.readLine()) != null) {
                if (string.charAt(0) != '#') {
                    findProduct(string).addProductToList();

                }
            }
        } catch (FileNotFoundException p) {
            return;
        } catch (IOException e) {
            userInterface.IOExceptionPrompt();
            throw new IOException();
        }
    }
}

