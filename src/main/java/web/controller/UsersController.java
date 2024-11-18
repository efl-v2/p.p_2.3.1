package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String showUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/add_user")
    public String formForAdd(Model model) {
        model.addAttribute("user", new User());
        return "addUser";
    }

    @PostMapping("/add_user")
    public String addUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addUser";
        } else {
            userService.addUser(user);
            return "redirect:/users";
        }
    }

    @GetMapping("/edit_user")
    public String formForEdit(@RequestParam(name = "id") Integer id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "editUser";
    }

    @PostMapping("/edit_user")
    public String editUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "editUser";
        } else {
            User existingUser = userService.getUserById(user.getId());
            existingUser.setName(user.getName());
            existingUser.setPassword(user.getPassword());
            userService.updateUser(existingUser);
            return "redirect:/users";
        }
    }

    @PostMapping("/delete_user")
    public String deleteUser(@RequestParam(name = "id") Integer id) {
        userService.removeUserById(id);
        return "redirect:/users";
    }
}
