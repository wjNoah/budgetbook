package com.example.budgetbook.controller;

import com.example.budgetbook.dto.RegistrationForm;
import com.example.budgetbook.model.AppUser;
import com.example.budgetbook.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("registrationForm", new RegistrationForm());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("registrationForm") RegistrationForm form,
                            BindingResult bindingResult) {
        if (form.getPassword() != null && !form.getPassword().equals(form.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "password.mismatch", "Passwords do not match");
        }

        if (form.getEmail() != null && userRepository.existsByEmail(form.getEmail())) {
            bindingResult.rejectValue("email", "email.exists", "An account with this email already exists");
        }

        if (bindingResult.hasErrors()) {
            return "register";
        }

        AppUser appUser = new AppUser(form.getEmail(), passwordEncoder.encode(form.getPassword()));
        userRepository.save(appUser);

        return "redirect:/login?registered";
    }
}
