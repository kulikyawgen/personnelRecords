package com.t.ms.kulik.diplom.controller;

import com.t.ms.kulik.diplom.domain.Role;
import com.t.ms.kulik.diplom.domain.User;
import com.t.ms.kulik.diplom.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;
    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@Valid User user, BindingResult bindingResult, Model model){
        if (user.getPassword() != null &&
                !user.getPassword1().equals(user.getPassword())) {
            model.addAttribute("passwordError", "Password are different!");
        }
        if (bindingResult.hasErrors()) {
           /* Collector<FieldError, ?, Map<String, String>> fieldErrorMapCollector = Collectors.toMap(
                    fieldError -> fieldError.getField() + "Error",
                    FieldError::getDefaultMessage

            );
            Map<String, String> errorMap = bindingResult.getFieldErrors().stream().collect(fieldErrorMapCollector);
            model.mergeAttributes(errorMap);*/

            return "registration";
        }
        User userFromBD = userRepo.findByUsername(user.getUsername());
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);
        return "redirect:/";
    }
}
