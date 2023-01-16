package sg.edu.nus.iss;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     * @throws IOException
     */
    
    public static void main(String[] args) throws IOException{
        String dirPath = "C:\\Users\\vince\\Root\\day3_workshop\\";
        String fileName = "";
        File newdDirectory = new File(dirPath);
        if(newdDirectory.exists()){
            System.out.println("directory already exists.");
        } else {
            newdDirectory.mkdir();
        }
        System.out.println("Welcome to my shopping cart!");

        List<String> cartItems = new ArrayList<String>();
        Console con = System.console();
        String input = "";
        while(!input.equals("quit")){
            input = con.readLine("what do you want to perform? (Type 'quit' to exit program.)");

            switch(input){
                case "help":
                    displayMessage("'list' to show a list of items in shopping cart");
                    displayMessage("'login' to access your shopping cart");
                    displayMessage("'add' to add items into your shopping cart");
                    displayMessage("'delete <item #>' delete item from your shopping cart");
                    displayMessage("'quit' to exit this program");
                break;
                case "list":
                    if(cartItems.size() > 0){
                        for(String item:cartItems){
                            System.out.printf("%d: %s\n",cartItems.indexOf(item)+1, item);
                        }
                    }else {
                        displayMessage("Your cart is empty.");
                    }
                break;
                case "users":
                break;
                default:
            }

            String strValue = "";
            if (input.startsWith("add")) {
                input = input.replace(',', ' ');
                Scanner scanner = new Scanner(input.substring(4));
                while (scanner.hasNext()) {
                    strValue = scanner.next();
                    if (!cartItems.contains(strValue)) {
                        cartItems.add(strValue);
                        System.out.printf("%d: added %s to cart.\n", cartItems.indexOf(strValue) + 1, strValue);
                    }
                }

            }

            if(input.startsWith("delete")){
                // Scanner scanner = new Scanner(input.substring(6));
                // while (scanner.hasNext()){
                //     int listItemIndex = Integer.parseInt(strValue);

                //     if(listItemIndex < cartItems.size()){
                //         cartItems.remove(listItemIndex);
                //     }else{
                //         displayMessage("incorrect item index.");
                //     }
                // }
                cartItems = deleteCartItem(cartItems, input);
            }

            if(input.startsWith("login")){
                input = input.replace(',', ' ');
                Scanner scanner = new Scanner(input.substring(6));

                while(scanner.hasNext()){
                    fileName = scanner.next();
                }

                File loginFile = new File(dirPath + File.separator + fileName + ".txt");
                boolean isCreated = loginFile.createNewFile();

                if(isCreated){
                    displayMessage("New file created sucessfully" + loginFile.getCanonicalFile());
                }else{
                    displayMessage("File already exists!");
                }

            }
        }
    }

    public static void displayMessage(String message){
        System.out.println(message);
    }

    public static List<String> deleteCartItem(List<String> cartItems, String input){
        String strValue = "";
        Scanner scanner = new Scanner(input.substring(6));
        strValue = scanner.next();
        while (scanner.hasNext()){
            int listItemIndex = Integer.parseInt(strValue);

            if(listItemIndex < cartItems.size()){
                cartItems.remove(listItemIndex+1);
            }else{
                displayMessage("incorrect item index.");
            }
        }

        return cartItems;

    }
}
