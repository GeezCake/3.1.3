package ru.kata.spring.boot_security.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    // ======= список пользователей =======
    @GetMapping
    public String allUsers(Model model) {
        List<User> users = userService.getAll();
        model.addAttribute("users", users);
        return "admin/users";          // ИМЕННО admin/users.html
    }

    // ======= форма добавления =======
    @GetMapping("/add")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        return "admin/user-form";      // admin/user-form.html
    }

    // ======= создание нового пользователя =======
    @PostMapping("/save")
    public String createUser(@ModelAttribute("user") User user) {

        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            user.setUsername(user.getEmail());
        }

        userService.create(user);
        return "redirect:/admin";
    }

    // ======= форма редактирования =======
    @GetMapping("/edit")
    public String editUser(@RequestParam("id") Long id, Model model) {
        User existing = userService.getById(id);
        model.addAttribute("user", existing);
        return "admin/user-form";
    }

    // ======= обновление пользователя =======
    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user) {

        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            user.setUsername(user.getEmail());
        }

        userService.update(user);
        return "redirect:/admin";
    }

    // ======= удаление пользователя =======
    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}




