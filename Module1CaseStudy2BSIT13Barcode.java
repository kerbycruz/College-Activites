import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Module1CaseStudy2BSIT13Barcode {

    static String[] ITEM_CODES = {"A", "B", "C", "D", "E"};
    static String[] ITEM_DESCRIPTIONS = {"3-in-1 coffee", "Cup Noodles", "Laundry soap", "Bottled water - 1 Liter", "Bottled water - 500 ml"};
    static String[] ITEM_DESCRIPTIONS_WITH_CODES = {"|A| 3-in-1 coffee", "|B| Cup Noodles", "|C| Laundry soap", "|D| Bottled water - 1 Liter", "|E| Bottled water - 500 ml"};
    static double[] ITEM_PRICES = {10.00, 20.00, 25.00, 20.00, 12.00};
    static int[] itemQuantity = {100, 36, 15, 8, 24};

    static ArrayList<String> itemList = new ArrayList<>();
    static ArrayList<Integer> quantityList = new ArrayList<>();
    static ArrayList<Double> totalPriceList = new ArrayList<>();

    static int totalQuantity = 0;
    static double totalCost = 0;

    public static void main(String[] args) throws Exception {
        // JFrame Initialization
        JFrame mainFrame = new JFrame();
        JFrame buyFrame = new JFrame();
        JFrame paymentFrame = new JFrame();
        JFrame inventoryFrame = new JFrame();

        // JButton Initialization
        JButton buyButton = new JButton("Buy");
        JButton paymentButton = new JButton("Payment");
        JButton inventoryButton = new JButton("Inventory");
        JButton exitButton = new JButton("Exit");
        JButton buyConfirmButton = new JButton("Confirm");
        JButton buyCancelButton = new JButton("Cancel");
        JButton paymentConfirmButton = new JButton("Confirm");

        // JPanel Initialization
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 15, 0));
        JPanel itemPanel = new JPanel(new GridLayout(6, 3, 40, -15));
        JPanel cartPanel = new JPanel(new GridLayout(0, 3, 10, 10));
        JPanel summaryPanel = new JPanel();
        JPanel inventoryPanel = new JPanel();

        // JComboBox Initialization
        JComboBox<String> buyItemList = new JComboBox<>(ITEM_DESCRIPTIONS_WITH_CODES);

        // JTextField Initialization
        JTextField buyTextField = new JTextField();

        // JLabel Initialization
        JLabel buyQuantity = new JLabel("Quantity:");

        // mainFrame.add
        mainFrame.add(buttonPanel);
        mainFrame.add(itemPanel);

        mainFrame(mainFrame, buttonPanel, itemPanel, buyButton, paymentButton, inventoryButton, exitButton);
        mainFrameAction(mainFrame, buyFrame, paymentFrame, inventoryFrame, buyTextField, buyButton, paymentButton, inventoryButton, exitButton, cartPanel, summaryPanel);
        buyFrame(mainFrame, buyFrame, buyQuantity, buyTextField, buyItemList, buyConfirmButton, buyCancelButton, cartPanel, summaryPanel);
        buyFrameAction(buyFrame, paymentFrame, cartPanel, inventoryPanel, buyTextField, buyItemList, buyConfirmButton, buyCancelButton, summaryPanel);
        paymentFrame(mainFrame, paymentFrame, cartPanel, summaryPanel, paymentConfirmButton);
        paymentFrameAction(paymentFrame, paymentConfirmButton);
        mainFrame.setVisible(true);
    }

    public static void mainFrame(JFrame mainFrame, JPanel buttonPanel, JPanel itemPanel, JButton buyButton, JButton paymentButton, JButton inventoryButton, JButton exitButton) {
        // buttonPanel Settings
        buttonPanel.setBounds(65, 280, 400, 50);
        buttonPanel.add(buyButton);
        buttonPanel.add(paymentButton);
        buttonPanel.add(inventoryButton);
        buttonPanel.add(exitButton);

        // mainFrame Settings
        mainFrame.setTitle("Java Mini Store");
        mainFrame.setLayout(null);
        mainFrame.setSize(550, 400);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(false);

        // itemPanel Settings
        itemPanel.setBounds(75, 10, 460, 250);
        itemPanel.add(new JLabel("Item Code"));
        itemPanel.add(new JLabel("Item Description"));
        itemPanel.add(new JLabel("Price"));
        for (int i = 0; i < ITEM_CODES.length; i++) {
            itemPanel.add(new JLabel(ITEM_CODES[i]));
            itemPanel.add(new JLabel(ITEM_DESCRIPTIONS[i]));
            itemPanel.add(new JLabel(String.format("₱%.2f", ITEM_PRICES[i])));
        }
    }

    public static void buyFrame(JFrame mainFrame, JFrame buyFrame, JLabel buyQuantity, JTextField buyTextField, JComboBox<String> buyItemList, JButton buyConfirmButton, JButton buyCancelButton, JPanel cartPanel, JPanel summaryPanel) {
        // buyFrame Settings
        buyFrame.setSize(450, 80);
        buyFrame.setTitle("Buy Menu");
        buyFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        buyFrame.setLayout(null);
        buyFrame.setLocationRelativeTo(null);
        buyFrame.setResizable(false);

        // buyButtonPanel Settings
        buyQuantity.setBounds(5, 5, 60, 30);
        buyTextField.setBounds(65, 5, 25, 30);
        buyItemList.setBounds(100, 5, 140, 30);
        buyConfirmButton.setBounds(250, 5, 80, 30);
        buyCancelButton.setBounds(345, 5, 80, 30);

        // buyFrame.add
        buyFrame.add(buyQuantity);
        buyFrame.add(buyTextField);
        buyFrame.add(buyItemList);
        buyFrame.add(buyConfirmButton);
        buyFrame.add(buyCancelButton);
    }

    public static void mainFrameAction(JFrame mainFrame, JFrame buyFrame, JFrame paymentFrame, JFrame inventoryFrame, JTextField buyTextField, JButton buyButton, JButton paymentButton, JButton inventoryButton, JButton exitButton, JPanel cartPanel, JPanel summaryPanel) {
        // buyButton Settings
        buyButton.addActionListener(e -> {
            buyFrame.setVisible(true);
            buyTextField.setText(""); // always set textField to "" or empty.
        });

        // paymentButton Settings
        paymentButton.addActionListener(e -> {
            if (itemList.isEmpty()) {
                JOptionPane.showMessageDialog(mainFrame, "Empty shopping cart.");
                return;
            }
            updatePaymentFrame(cartPanel, summaryPanel);
            paymentFrame.setVisible(true);
        });

        // inventoryButton Settings
        inventoryButton.addActionListener(e -> {
            inventoryFrame(inventoryFrame);
            inventoryFrame.setVisible(true);
        });

        // exitButton Settings
        exitButton.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(mainFrame, "Leaving so soon?\nClick 'No' to stay or 'Yes' to exit.", "Confirmation Message", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
    }

    public static void buyFrameAction(JFrame buyFrame, JFrame paymentFrame, JPanel cartPanel, JPanel inventoryPanel, JTextField buyTextField, JComboBox<String> buyItemList, JButton buyConfirmButton, JButton buyCancelButton, JPanel summaryPanel) throws Exception {
        buyConfirmButton.addActionListener(e -> {
            try {
                int index = buyItemList.getSelectedIndex();
                int quantity = Integer.parseInt(buyTextField.getText());

                if (quantity <= 0) {
                    throw new NumberFormatException();
                } else if (quantity > itemQuantity[index]) {
                    JOptionPane.showMessageDialog(buyFrame, "Product Code " + ITEM_CODES[index] + " out of stock.");
                    return;
                }

                double itemTotalCost = ITEM_PRICES[index] * quantity;
                totalQuantity += quantity;
                totalCost += itemTotalCost;
                itemList.add(ITEM_DESCRIPTIONS[index]);
                quantityList.add(quantity);
                totalPriceList.add(itemTotalCost);
                itemQuantity[index] -= quantity;

                JOptionPane.showMessageDialog(buyFrame, "Item: " + ITEM_DESCRIPTIONS_WITH_CODES[index] + "\nQuantity: " + quantity + "\nTotal: ₱" + itemTotalCost + "\n\nAdded Successfully!");

                updatePaymentFrame(cartPanel, summaryPanel);
            } catch (NumberFormatException NFE) {
                JOptionPane.showMessageDialog(buyFrame, "Invalid quantity entered. Please enter a valid number.");
            }
        });

        buyCancelButton.addActionListener(e -> {
            buyFrame.setVisible(false);
        });
    }

    public static void paymentFrame(JFrame mainFrame, JFrame paymentFrame, JPanel cartPanel, JPanel summaryPanel, JButton paymentConfirmButton) {
        // paymentFrame Settings
        paymentFrame.setSize(500, 450);
        paymentFrame.setTitle("Payment Menu");
        paymentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        paymentFrame.setLayout(null);
        paymentFrame.setLocationRelativeTo(null);
        paymentFrame.setResizable(false);

        paymentFrame.add(cartPanel, BorderLayout.NORTH);
        paymentFrame.add(summaryPanel);
        paymentConfirmButton.setBounds(380, 350, 80, 40);
        paymentFrame.add(paymentConfirmButton);
    }

    public static void updatePaymentFrame(JPanel cartPanel, JPanel summaryPanel) {
        cartPanel.removeAll();
        cartPanel.setBounds(70, 10, 420, 300);
        cartPanel.setLayout(new GridLayout(itemList.size() + 1, 3, 15, 10));
        cartPanel.add(new JLabel("Items Bought:"));
        cartPanel.add(new JLabel("Quantity"));
        cartPanel.add(new JLabel("Total Cost"));

        for (int i = 0; i < itemList.size(); i++) {
            cartPanel.add(new JLabel(itemList.get(i)));
            cartPanel.add(new JLabel(String.valueOf(quantityList.get(i))));
            cartPanel.add(new JLabel(String.format("%.2f", totalPriceList.get(i))));
        }

        summaryPanel.removeAll();
        summaryPanel.setBounds(20, 330, 450, 90);
        summaryPanel.setLayout(new GridLayout(2, 2, 10, -20));
        summaryPanel.add(new JLabel("Items Bought: " + totalQuantity));
        summaryPanel.add(new JLabel("Total Bill: ₱" + String.format("%.2f", totalCost)));
        summaryPanel.add(new JLabel("\"Thank you for shopping!\""));

        cartPanel.revalidate();
        cartPanel.repaint();
        summaryPanel.revalidate();
        summaryPanel.repaint();
    }

    public static void paymentFrameAction(JFrame paymentFrame, JButton paymentConfirmButton) {
        paymentConfirmButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(paymentFrame, "Thank You So Much for Your Order! I Hope You Enjoy Your New Purchase!");
            itemList.clear();
            quantityList.clear();
            totalPriceList.clear();
            paymentFrame.setVisible(false);
        });
    }

    public static void inventoryFrame(JFrame inventoryFrame) {
        JPanel inventoryPanel = new JPanel();
        inventoryFrame.getContentPane().removeAll();

        inventoryFrame.setSize(350, 220);
        inventoryFrame.setTitle("Inventory");
        inventoryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        inventoryFrame.setLayout(new FlowLayout());
        inventoryFrame.setLocationRelativeTo(null);
        inventoryFrame.setResizable(false);

        inventoryPanel.setBounds(30, 10, 350, 180);
        inventoryPanel.setLayout(new GridLayout(6, 2, 10, 10));
        inventoryPanel.add(new JLabel("Product Code"));
        inventoryPanel.add(new JLabel("Total No. of items in Store"));
        for (int index = 0; index < ITEM_DESCRIPTIONS.length; index++) {
            inventoryPanel.add(new JLabel(ITEM_CODES[index]));
            inventoryPanel.add(new JLabel(String.valueOf(itemQuantity[index])));
        }
        inventoryPanel.revalidate();
        inventoryPanel.repaint();

        inventoryFrame.add(inventoryPanel);
        inventoryFrame.revalidate();
        inventoryFrame.repaint();
    }
}
