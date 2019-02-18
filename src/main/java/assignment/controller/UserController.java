package assignment.controller;


import assignment.model.User;
import assignment.service.SecurityService;
import assignment.service.UserService;
import assignment.util.PasswordStrengthChecker;
import assignment.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private PasswordStrengthChecker passwordStrengthChecker;

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "signup";
        }

        userService.save(userForm);
        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());
        return "redirect:/helloworld";

    }
    @RequestMapping(value = "/checkStrength", method = RequestMethod.GET, produces = { "text/html; charset=UTF-8" })
    public @ResponseBody
    String checkStrength(@RequestParam String password) {
        return passwordStrengthChecker.getStrength(password);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @RequestMapping(value = {"/", "/helloworld"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "helloworld";
    }
}
