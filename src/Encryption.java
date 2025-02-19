import javax.crypto.Cipher; 
import javax.crypto.KeyGenerator; 
import javax.crypto.SecretKey; 
import java.util.Base64; 

public class Encryption {
    private static final String ALGORITHM = "AES"; 
    private static SecretKey key; 

    static {
        try {
            // generate a new secret key 
            KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM); 
            keyGen.init(128); // AES-128 
            key = keyGen.generateKey();
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }

    public static String encrypt(String password) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key); 
        byte[] encryptedData = cipher.doFinal(password.getBytes()); 
        return Base64.getEncoder().encodeToString(encryptedData);  
    }

    public static String decrypt(String encryptedPassword) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM); 
        cipher.init(Cipher.DECRYPT_MODE, key); 
        byte[] decryptedData = cipher.doFinal(Base64.getDecoder().decode(encryptedPassword)); 
        return new String(decryptedData); 
    }
}
