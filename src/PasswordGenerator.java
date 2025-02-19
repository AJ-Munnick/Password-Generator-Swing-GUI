import java.security.SecureRandom; 

public class PasswordGenerator {
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SYMBOLS = "!@#$%^&*()_-+=<>?";

    public static String generatePassword(int length, boolean includeUppercase, boolean includeLowercase, boolean includeNumbers, boolean includeSymbols) {
        StringBuilder characterSet = new StringBuilder(); 
        if(includeUppercase) characterSet.append(UPPERCASE); 
        if(includeLowercase) characterSet.append(LOWERCASE);
        if(includeNumbers) characterSet.append(DIGITS);
        if(includeSymbols) characterSet.append(SYMBOLS);

        if (characterSet.length() == 0) {
            throw new IllegalArgumentException("Must include at least one character set");
        }

        SecureRandom random = new SecureRandom(); 
        StringBuilder password = new StringBuilder(length);

        while(password.length() < length) {
            char nextChar = characterSet.charAt(random.nextInt(characterSet.length()));

            // Ensure characters do not repeat more than twice consecutively
            if (password.length() >= 2 && nextChar == password.charAt(password.length() -1) && nextChar == password.charAt(password.length() -2)) {
                continue; 
            }
            password.append(nextChar);
        }
        // for(int i = 0; i < length; ++i) {
        //     password.append(characterSet.charAt(random.nextInt(characterSet.length())));
        // }

        return password.toString();
    }
}
