package com.janitri.JanitriApplication.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.janitri.JanitriApplication.models.Patient;
import com.janitri.JanitriApplication.models.User;
import com.janitri.JanitriApplication.services.PatientService;
import com.janitri.JanitriApplication.services.UserService;
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PatientService patientService;  // Inject PatientService

    @GetMapping("/register")
    public String showRegistrationPage() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        User registeredUser = userService.registerUser(user);
        model.addAttribute("user", registeredUser);
        return "registrationSuccess";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String email, @RequestParam String password, Model model) {
        boolean isValid = userService.validateUser(email, password);
        if (isValid) {
            model.addAttribute("user", userService.findByEmail(email));  // Fetch user by email (String)
            return "loginSuccess";
        } else {
            model.addAttribute("errorMessage", "Invalid credentials.");
            return "loginFailure";
        }
    }

    @GetMapping("/patients")
    public String showPatientsList(@RequestParam Long userId, Model model) {
        List<Patient> patients = patientService.getPatientsByUserId(userId);  // Fetch patients by userId
        model.addAttribute("patients", patients);
        return "patientsList";
    }
}
