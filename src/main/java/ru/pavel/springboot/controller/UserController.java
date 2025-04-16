package ru.pavel.springboot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import ru.pavel.springboot.model.User;
import ru.pavel.springboot.service.UserService;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping()
    public String getAllUsers(Model model) {
        List<User> list = service.getAllUsers();
        model.addAttribute("list", list);
        return "userlist";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new";
        }
        service.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}")
    public String showUser(Model model, @PathVariable Long id){
        model.addAttribute("user",service.showUserById(id));
        return "show";
    }

    @GetMapping("/{id}/update")
    public String editUser(Model model,@PathVariable Long id){
        model.addAttribute("user",service.showUserById(id));
        return "update";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute ("user") @Valid User user, BindingResult bindingResult, @PathVariable Long id){
        if(bindingResult.hasErrors()){
            return "update";
        }
        service.updateUser(id,user);
        return "show";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id){
        service.removeUserById(id);
        return "redirect:/users";
    }
}
