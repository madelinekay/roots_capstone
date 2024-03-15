package com.Undis.Madeline.SzotoSzoves_CaseStudy.controller;

import com.Undis.Madeline.SzotoSzoves_CaseStudy.dto.UserDTO;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.User;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserAuthController {
    private UserService userService;
    @Autowired
    public UserAuthController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/index")
    public String home() {
        return "index";
    }

    @GetMapping("/flashcard")
    public String login() {
        return "flashcard";
    }
//this prepares registration for user with DTO
    @GetMapping("/signup")
    public String showRegistrationForm(Model model) {
        UserDTO user = new UserDTO();
        model.addAttribute("user", user);
        return "signup";
    }

    // handler method to handle student registration from submit request
    @PostMapping("/signup/save")
    public String registration(@Valid @ModelAttribute("user") UserDTO userDTO, BindingResult result, Model model) {
        User existingUser = userService.findUserByEmail(userDTO.getEmail());
//        TODO verify if this message will be injected into thymeleaf
        if(existingUser != null){
            result.rejectValue("email", null, "There is already an account registered with the same email");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", userDTO);

            return "/signup";
        }
        System.out.println(userDTO);
        userService.saveUser(userDTO);
        return "redirect:/signup?success";
    }
}

