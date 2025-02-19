public class PasswordChecker {
    public static boolean isStrong(String password) {
        return calculateStrength(password) >=75;
    }

    public static int calculateStrength(String password) {
        int strength = 0;
        if (password.length() >= 8) strength += 25;
        if (password.chars().anyMatch(Character::isUpperCase)) strength += 25;
        if (password.chars().anyMatch(Character::isLowerCase)) strength += 25;
        if (password.chars().anyMatch(Character::isDigit)) strength += 15;
        if (password.chars().anyMatch(ch -> "!@#$%^&*()_-+=<>?".indexOf(ch) >= 0)) strength += 10;
        return strength;
    }
}
