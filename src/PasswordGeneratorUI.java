import javax.swing.*; 
import java.awt.*; 
import java.util.List;

public class PasswordGeneratorUI extends JFrame {
    
    private JTextField lengthField; 
    private JTextArea passwordArea; 
    private JProgressBar strengthBar; 
    private JCheckBox includeUppercase; 
    private JCheckBox includeLowercase; 
    private JCheckBox includeNumbers; 
    private JCheckBox includeSymbols; 
    private PasswordHistory passwordHistory; 

    public PasswordGeneratorUI() {
        setTitle("Password Generator"); 
        setSize(600, 400); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setLocationRelativeTo(null); 

        passwordHistory = new PasswordHistory();

        JTabbedPane tabbedPane = new JTabbedPane(); 
        tabbedPane.addTab("Generate", createGeneratePanel());  
        tabbedPane.addTab("History", createHistoryPanel()); 

        add(tabbedPane); 
    }

        private JPanel createGeneratePanel() {
            JPanel generatePanel = new JPanel(new BorderLayout()); 

            JPanel inputPanel = new JPanel(new GridBagLayout()); 
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            // Password length label and text field
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 1; 
            inputPanel.add(new JLabel("Password Length: "), gbc);
            
            gbc.gridx = 1;
            lengthField = new JTextField(20); 
            inputPanel.add(lengthField, gbc); 

            // Character set label 
            gbc.gridx = 0; 
            gbc.gridy = 1; 
            gbc.gridwidth = 2; 
            inputPanel.add(new JLabel("Character Set: "), gbc);
            gbc.gridwidth = 1; // Reset gridwidth

            // Checkboxes in a 2x2 grid
            includeUppercase = new JCheckBox("Include Uppercase Letters"); 
            includeLowercase = new JCheckBox("Include Lowercase Letters");
            includeNumbers = new JCheckBox("Include Numbers"); 
            includeSymbols = new JCheckBox("Include Symbols"); 
            
            gbc.gridx = 0;
            gbc.gridy = 2; 
            inputPanel.add(includeUppercase, gbc);
            
            gbc.gridx = 1;
            inputPanel.add(includeLowercase, gbc);

            gbc.gridx = 0;
            gbc.gridy = 3;
            inputPanel.add(includeNumbers, gbc);

            gbc.gridx = 1;
            inputPanel.add(includeSymbols, gbc);

            // Generate Button
            gbc.gridx = 0;
            gbc.gridy = 4; 
            gbc.gridwidth = 2; 
            JButton generateButton = new JButton("Generate"); 
            generateButton.addActionListener(e -> generatePassword()); 
            inputPanel.add(generateButton, gbc);
            
            // Password strength label and progress bar
            gbc.gridy = 5; 
            gbc.gridwidth = 2; 
            inputPanel.add(new JLabel("Password Strength:"), gbc);

            gbc.gridy = 6;
            strengthBar = new JProgressBar(0, 100); 
            strengthBar.setStringPainted(true); 
            inputPanel.add(strengthBar, gbc); 

            generatePanel.add(inputPanel, BorderLayout.NORTH); 

            passwordArea = new JTextArea(); 
            passwordArea.setEditable(false); 
            generatePanel.add(new JScrollPane(passwordArea), BorderLayout.CENTER); 

            return generatePanel;
        }

        private JPanel createHistoryPanel() {
            JPanel historyPanel = new JPanel(new BorderLayout());
            JTextArea historyArea = new JTextArea();
            historyArea.setEditable(false);
            
            JButton refreshButton = new JButton("Refresh"); 
            refreshButton.addActionListener(e -> refreshHistory(historyArea));

            historyPanel.add(new JScrollPane(historyArea), BorderLayout.CENTER);
            historyPanel.add(refreshButton, BorderLayout.SOUTH);

            return historyPanel; 
        }

        private void updateStrengthBarColor(int strength) {
            if (strength < 50) {
                strengthBar.setForeground(Color.RED);
                strengthBar.setString("Weak"); 
            } else if (strength < 75) {
                strengthBar.setForeground(Color.YELLOW); 
                strengthBar.setString("Medium"); 
            } else {
                strengthBar.setForeground(Color.GREEN); 
                strengthBar.setString("Strong");
            }
        }

        private void generatePassword() {
            try {
                int length = Integer.parseInt(lengthField.getText());
                PasswordCriteria criteria = new PasswordCriteria(includeUppercase.isSelected(), includeLowercase.isSelected(), includeNumbers.isSelected(), includeSymbols.isSelected());

                String generatedPassword = PasswordGenerator.generatePassword(length, criteria.isIncludeUppercase(), criteria.isIncludeLowercase(), criteria.isIncludeNumbers(), criteria.isIncludeSymbols());

                // Update password area 
                passwordArea.setText(generatedPassword); 

                // Add password to history
                passwordHistory.addPassword(generatedPassword); 

                // Update strength bar using PasswordChecker
                int strength = PasswordChecker.calculateStrength(generatedPassword);
                strengthBar.setValue(strength);
                updateStrengthBarColor(strength);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number for password length", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        }

        private void refreshHistory(JTextArea historyArea) {
            List<String> history = passwordHistory.getDecryptedPasswordHistory(); 
            StringBuilder historyText = new StringBuilder();
            for(String password : history) {
                historyText.append(password).append("\n"); 
            }
            
            historyArea.setText(historyText.toString()); 
        }

        public static void launch() {
            SwingUtilities.invokeLater(() -> {
                PasswordGeneratorUI frame = new PasswordGeneratorUI();
                frame.setVisible(true);
            });
        }

    }