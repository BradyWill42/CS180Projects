import javax.swing.*;

public class OrderFormGUI {
    public static void main(String[] args) {
        /** DO NOT CHANGE VALUES BELOW **/
        boolean hoodieInStock = true;
        boolean tshirtInStock = false;
        boolean longsleeveInStock = true;
        String item = "";
        int quantity = 0;
        String name = "";
        /** DO NOT CHANGE VALUES ABOVE **/

        int again;

        do {
            String[] options = {"Hoodie", "T-shirt", "Long sleeve"};
            do {
                item = (String) JOptionPane.showInputDialog(null, "Select item style ", "Order Form",
                    JOptionPane.PLAIN_MESSAGE, null, options, null);
                
                if (item == null || item.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Out of Stock", "Order Form",
                        JOptionPane.ERROR_MESSAGE);
                }
            } while (item == null || item.isEmpty());
            

            do {
                try {
                    quantity = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter quantity", "Order Form",
                        JOptionPane.QUESTION_MESSAGE));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Enter an Integer", "Order Form",
                        JOptionPane.ERROR_MESSAGE);
                }
                if (quantity < 1) {
                    JOptionPane.showMessageDialog(null, "Enter a number greater than 0", "Order Form",
                        JOptionPane.ERROR_MESSAGE);
                }
            } while (quantity < 1);
            

            do {
                name = JOptionPane.showInputDialog(null, "Enter your Name", "Order Form",
                    JOptionPane.QUESTION_MESSAGE);

                if (name == null || name.isEmpty() || !name.contains(" ")) {
                    JOptionPane.showMessageDialog(null, "Enter first and last name", "Order Form",
                        JOptionPane.ERROR_MESSAGE);
                }
            } while (name == null || name.isEmpty() || !name.contains(" "));


            


            /** Order Confirmation Message **/
            String resultMessage = "Name: " + name + "\nItem: " + item + "\nQuantity: " + quantity;
            JOptionPane.showMessageDialog(null, resultMessage, "Order Confirmation", JOptionPane.INFORMATION_MESSAGE);      
        
            again = JOptionPane.showConfirmDialog(null, "Would you like to place another order?", "Order Form", JOptionPane.YES_NO_OPTION);
        } while (again == 0);


    }
}
