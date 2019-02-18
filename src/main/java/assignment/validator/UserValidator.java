package assignment.validator;

import assignment.model.User;
import assignment.service.UserService;
import assignment.util.PasswordStrengthChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component
public class UserValidator implements Validator {

    private static final String STRONG_PASSWORD = "STRONG";
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordStrengthChecker passwordStrengthChecker;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");

        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (!passwordStrengthChecker.getStrength(user.getPassword()).equalsIgnoreCase(STRONG_PASSWORD)) {
            errors.rejectValue("password", "Strength.userForm.password");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }
    }
}
