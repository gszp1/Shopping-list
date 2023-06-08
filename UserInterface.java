import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class UserInterface {

    private final PrintWriter printWriter;

    private final BufferedReader bufferedReader;
    UserInterface() {
        printWriter = new PrintWriter(System.out, true);
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public int getUserDecision() throws IOException {
        int userDecision = 8;
        String userInput;
        boolean inputCorrect = false;

        inputPrompt();
        do{
            userInput = bufferedReader.readLine();
            try {
                userDecision = Integer.parseInt(userInput);
                if(!(inputCorrect = isAllowed(userDecision))) {
                    invalidOperationInputPrompt();
                }
            } catch(NumberFormatException e) {
                invalidOperationInputPrompt();
            }
        } while(!inputCorrect);

        return userDecision;
    }

    private boolean isAllowed(int operation) {
        int [] operations = {1, 2, 3, 4, 5, 6, 7, 8};

        for(int i : operations) {
            if(i == operation) {
                return true;
            }
        }
        return false;
    }

    public void productAdditionOperation(ProductsList productsList)
            throws IOException{
        displayCategories(productsList.getCategories());
        printWriter.println("Choose category: ");
        Category chosenCategory;

        String userInput = bufferedReader.readLine();
        if((chosenCategory = productsList.findCategory(userInput)) == null) {
            printWriter.println("No such category.");
            return;
        }

        displayProducts(chosenCategory);
        printWriter.println("Choose product: ");
        userInput = bufferedReader.readLine();
        Product chosenProduct;
        if((chosenProduct = chosenCategory.findProductInCategory(userInput)) == null) {
            printWriter.println("No such product.");
        } else {
            chosenProduct.addProductToList();
        }
    }

    public void removeProductsFromCategoryOperation(ProductsList products)
            throws IOException{
        if(products.isListEmpty()) {
            emptyListPrompt();
            return;
        }
        displayCategories(products.getCategories());
        printWriter.println("Choose category: ");
        Category chosenCategory;

        String userInput = bufferedReader.readLine();
        if((chosenCategory = products.findCategory(userInput)) == null) {
            printWriter.println("No such category.");
            return;
        }

        chosenCategory.removeAllProductsFromCategory();
        printWriter.println("Removed all products from category: " + chosenCategory.getCategoryName());
    }

    public void removeProductFromShoppingList(ProductsList products)
            throws IOException {
        boolean onTheList = true;

        printWriter.println("Available categories:");
        for(Category i: products.getCategories()) {
            if(!(i.isEmpty())) {
                onTheList = false;
                printWriter.println("- " + i.getCategoryName());
            }
        }
        if(onTheList) {
            emptyListPrompt();
            return;
        }

        printWriter.println("Choose category:");
        String userInput = bufferedReader.readLine();
        Category chosenCategory;
        if((chosenCategory = products.findCategory(userInput)) == null) {
            printWriter.println("No such category.");
            return;
        }
        if(chosenCategory.isEmpty()){
            printWriter.println("No products in chosen category");
            return;
        }

        printWriter.println("Products on the list:");
        for(Product i: chosenCategory.getProducts()){
            if(i.isOnTheList()) {
                printWriter.println("- " + i.getProductName());
            }
        }
        printWriter.println("Choose product:");
        Product chosenProduct = null;
        userInput = bufferedReader.readLine();
        for(Product i: chosenCategory.getProducts()) {
            if(i.getProductName().equalsIgnoreCase(userInput)) {
                chosenProduct = i;
            }
        }
        if(chosenProduct == null) {
            printWriter.println("No such product.");
            return;
        }
        if(!chosenProduct.isOnTheList()){
            printWriter.println("Product is not on the list.");
            return;
        }
        chosenProduct.removeProductFromList();
    }

    public void displayOperationList() {
        printWriter.println(
                "[1] Add product to shopping list.\n" +
                        "[2] Display shopping list content.\n" +
                        "[3] Display shopping list content from selected category.\n" +
                        "[4] Clear shopping list.\n" +
                        "[5] Remove all products from selected category\n" +
                        "[6] Remove selected product from shopping list.\n" +
                        "[7] Write shopping list to file.\n" +
                        "[8] Exit"
        );
    }

    public void displayCategories(ArrayList<Category> categoryArrayList) {
        printWriter.println("Available categories:");
        for (Category category : categoryArrayList) {
            printWriter.println("- " + category.getCategoryName());
        }
    }

    public void displayProducts(Category category) {
        printWriter.println("Available products in " + category.getCategoryName() + " category: ");
        ArrayList<Product> products = category.getProducts();
        for(Product i: products) {
            printWriter.println("- " + i.getProductName());
        }
    }

    public void displayShoppingList(ArrayList<Category> categories) {
        boolean productsInList = false;

        for(Category i: categories) {
            if(!i.isEmpty()) {
                productsInList = true;
                printWriter.println("#" + i.getCategoryName());
                for(Product j: i.getProducts()) {
                    if(j.getQuantity() != 0) {
                        for(int k = 0; k < j.getQuantity(); ++k){
                            printWriter.println("- " + j.getProductName());
                        }
                    }
                }
            }
        }
        if(!productsInList) {
            emptyListPrompt();
        }
    }

    public void displayShoppingListCategory(ProductsList products) throws IOException{
        displayCategories(products.getCategories());
        printWriter.println("Choose Category:");

        String userInput = bufferedReader.readLine();
        Category chosenCategory;
        if((chosenCategory = products.findCategory(userInput)) == null) {
            printWriter.println("No such category.");
            return;
        }
        if(chosenCategory.isEmpty()){
            printWriter.println("No products in chosen category");
            return;
        }

        boolean productsInCategory = false;
        for(Product i: chosenCategory.getProducts()) {
            if(i.getQuantity() != 0) {
                productsInCategory = true;
                for(int j = 0; j < i.getQuantity(); ++j) {
                    printWriter.println("- " + i.getProductName());
                }
            }
        }
        if(!productsInCategory) {
            printWriter.println("Category is empty");
        }
    }

    public void listClearedPrompt() {
        printWriter.println("Shopping list cleared.");
    }
    public void inputPrompt() {
        printWriter.println("Choose operation: ");
    }

    public void invalidOperationInputPrompt() {
        printWriter.println("Chosen operation is invalid, please try again: ");
    }

    public void exitPrompt() {
        printWriter.println("Exiting the program.");
    }

    public void invalidOperationPrompt() {
        printWriter.println("Invalid operation.");
    }

    public void IOExceptionPrompt() {
        printWriter.println("Input-output error detected, exiting the program.");
    }

    public void emptyListPrompt() {
        printWriter.println("List is empty.");
    }

    public void listSavedPrompt() {
        printWriter.println("List saved.");
    }
}
