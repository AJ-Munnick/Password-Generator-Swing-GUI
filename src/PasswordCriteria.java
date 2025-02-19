public class PasswordCriteria {
    private boolean includeUppercase; 
    private boolean includeLowercase; 
    private boolean includeNumbers;
    private boolean includeSymbols;

    // Constructor to intialize the fields 
    public PasswordCriteria(boolean includeUppercase, boolean includeLowercase, boolean includeNumbers, boolean includeSymbols) {
        this.includeUppercase = includeUppercase; 
        this.includeLowercase = includeLowercase; 
        this.includeNumbers = includeNumbers; 
        this.includeSymbols = includeSymbols; 
    }

    // Getters and setters for each field 
    public boolean isIncludeUppercase() {
        return includeUppercase;
    }

    public void setIncludeUppercase(boolean includeUppercase) {
        this.includeUppercase = includeUppercase;
    }

    public boolean isIncludeLowercase() {
        return includeLowercase;
    }

    public void setIncludeLowercase(boolean includeLowercase) {
        this.includeLowercase = includeLowercase;
    }

    public boolean isIncludeNumbers() {
        return includeNumbers;
    }

    public void setIncludeNumbers(boolean includeNumbers) {
        this.includeNumbers = includeNumbers;
    }

    public boolean isIncludeSymbols() {
        return includeSymbols;
    }

    public void setIncludeSymbols(boolean includeSymbols) {
        this.includeSymbols = includeSymbols;
    }
}
