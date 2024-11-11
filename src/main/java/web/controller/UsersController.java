package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.dao.UserDaoImpl;
import web.model.User;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserDaoImpl userDAO;

    @Autowired
    public UsersController(UserDaoImpl userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping("")
    public String showUsers(Model model) {
        model.addAttribute("users", userDAO.getAllUsers());
        return "users";
    }

    @GetMapping("/add_user")
    public String formForAdd(Model model) {
        model.addAttribute("user", new User());
        return "addUser";
    }

    @PostMapping("/add_user")
    public String addUser(@ModelAttribute("user") User user) {
        userDAO.addUser(user);
        return "redirect:/";
    }

    @GetMapping("/edit_user")
    public String formForEdit(@RequestParam(name = "id") Integer id, Model model) {
        model.addAttribute("user", userDAO.getUserById(id));
        return "editUser";
    }

    @PostMapping("/edit_user")
    public String editUser(@ModelAttribute("user") User user) {
        userDAO.updateUser(user);
        return "redirect:/";
    }

    @GetMapping("/delete_user")
    public String deleteUser(@RequestParam(name = "id") Integer id) {
        userDAO.removeUserById(id);
        return "redirect:/";
    }
}
