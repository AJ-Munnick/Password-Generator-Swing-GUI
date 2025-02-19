import java.util.ArrayList;
import java.util.List; 

public class PasswordHistory {
    private List<String> passwordHistory; 

    public PasswordHistory() {
        this.passwordHistory = new ArrayList<>(); 
    }

    public void addPassword(String password) {
        try {
            String encryptedPassword = Encryption.encrypt(password); 
            passwordHistory.add(encryptedPassword);
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    public List<String> getEncryptedPasswordHistory() {
        return passwordHistory; 
    }

    public List<String> getDecryptedPasswordHistory() {
        List<String> decryptedHistory = new ArrayList<>(); 
        for(String encryptedPassword : passwordHistory) {
            try {
                String decryptedPassword = Encryption.decrypt(encryptedPassword); 
                decryptedHistory.add(decryptedPassword); 
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return decryptedHistory;
    }
}