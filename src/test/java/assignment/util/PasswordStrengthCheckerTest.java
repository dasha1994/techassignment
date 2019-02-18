package assignment.util;

import org.junit.*;


public class PasswordStrengthCheckerTest {
    private PasswordStrengthChecker passwordStrengthChecker;

    private static final String WEAK_PASSWORD = "Weak";
    private static final String MEDIUM_PASSWORD = "Medium";
    private static final String STRONG_PASSWORD = "Strong";

    @Before
    public void init() {
        passwordStrengthChecker = new PasswordStrengthCheckerImpl();
    }

    @Test
    public void testGetPasswordStrength() {
        Assert.assertEquals("Password is weak", WEAK_PASSWORD, passwordStrengthChecker.getStrength(""));
        Assert.assertEquals("Password is too short", MEDIUM_PASSWORD, passwordStrengthChecker.getStrength("11111"));
        Assert.assertEquals("Password is strong enough", STRONG_PASSWORD, passwordStrengthChecker.getStrength("qweASD123!@#"));
    }

}
