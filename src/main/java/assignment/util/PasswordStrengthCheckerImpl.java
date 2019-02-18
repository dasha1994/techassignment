package assignment.util;

import org.springframework.stereotype.Component;

@Component
public class PasswordStrengthCheckerImpl implements PasswordStrengthChecker {
    private static final int WEAK_STRENGTH = 1;
    private static final int MEDIUM_STRENGTH = 5;
    private static final int STRONG_STRENGTH = 7;

    private static final String WEAK_PASSWORD = "Weak";
    private static final String MEDIUM_PASSWORD = "Medium";
    private static final String STRONG_PASSWORD = "Strong";

    @Override
    public String getStrength(String password) {
        if (password.length() >= WEAK_STRENGTH & password.length() < MEDIUM_STRENGTH) {
            return WEAK_PASSWORD;
        } else if (password.length() >= MEDIUM_STRENGTH & password.length() < STRONG_STRENGTH) {
            return MEDIUM_PASSWORD;
        } else if (password.length() >= STRONG_STRENGTH) {
            return STRONG_PASSWORD;
        }
        return WEAK_PASSWORD;
    }
}
