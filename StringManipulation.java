import javax.swing.*;
import java.awt.*;
public class StringManipulation {
    public static void main(String[] args) {
        JFrame frame = new JFrame("String Manipulation");
        frame.setSize(400,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        
        JLabel label = new JLabel("Enter a String:");
        label.setBounds(50, 50, 200, 25);
        
        JTextField fieldLabel = new JTextField();
        fieldLabel.setBounds(150, 50, 200, 25);
        
        JLabel toolkitLabel = new JLabel("String Toolkit");
        toolkitLabel.setBounds(50, 90, 200, 25);

        String[] toolkit = {"VOWELS TO *","CONSONANTS TO @","STRING REVERSE", "UPPERCASE", "LOWERCASE", "CHARACTER COUNT"};
        JComboBox toolkitComboBox = new JComboBox (toolkit);
        toolkitComboBox.setBounds(150, 90, 200, 25);

        JButton process = new JButton("Process");
        process.setBounds(150, 130, 200, 25);

        JLabel outputLabel = new JLabel("OUTPUT: ");
        outputLabel.setBounds(50, 170, 200, 25);

        JTextField result = new JTextField();
        result.setBounds(150, 170, 200, 25);
        result.setEditable(false);

        process.addActionListener(e -> {

            int toolkitIndex = toolkitComboBox.getSelectedIndex();
            String input = fieldLabel.getText();
            String output = "";

            switch(toolkitIndex){
                case 0 -> {
                    output = input.replaceAll("[aeiouAEIOU]", "*");
                    result.setText(output);
                }
                case 1 -> {
                    output = input.replaceAll("[bcdfghjklmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ]", "@");
                    result.setText(output);
                }
                case 2 -> {
                    StringBuilder sb = new StringBuilder(input);
                    sb.reverse();
                    result.setText(sb.toString());
                }
                case 3 ->{
                    result.setText(input.toUpperCase());
                }
                case 4 -> {
                    result.setText(input.toLowerCase());
                }
                case 5 -> {
                    int count = input.length();
                    for (int i = 0; i < input.length(); i++) {
                        if (Character.isWhitespace(input.charAt(i))) {
                            count -= 1;
                        }
                    }
                    result.setText(Integer.toString(count));
                }
            
            }
        });

        // FRAME ADD
        frame.add(label);
        frame.add(fieldLabel);
        frame.add(toolkitLabel);
        frame.add(toolkitComboBox);
        frame.add(process);
        frame.add(outputLabel);
        frame.add(result);
        frame.setVisible(true);

    }
}
