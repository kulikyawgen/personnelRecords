package com.t.ms.kulik.diplom.controller;

import com.t.ms.kulik.diplom.domain.Skills;
import com.t.ms.kulik.diplom.domain.Staff;
import com.t.ms.kulik.diplom.repo.SkillsRepo;
import com.t.ms.kulik.diplom.repo.StaffRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    private StaffRepo staffRepo;
    @Autowired
    private SkillsRepo skillsRepo;
    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping()
    public String getAllStaff(Model model) {
        Iterable<Staff> employees = staffRepo.findAll();
        model.addAttribute("employees", employees);
        return "staff";
    }

    @GetMapping("/edit/{staff}")
    public String userEdit(@PathVariable Staff staff, Model model) {
        model.addAttribute("staff", staff);
        return "staffEdit";
    }

    @PostMapping("/edit/{staff}")
    public String userEdit(@PathVariable Staff staff, @RequestParam String name, @RequestParam String surname) {
        staff.setName(name);
        staff.setSurname(surname);
        staffRepo.save(staff);
        return "redirect:/staff";
    }


    @GetMapping("/delete/{staff}")
    public String userDelete(@PathVariable Staff staff) {
        staffRepo.delete(staff);
        return "redirect:/staff";
    }

    @PostMapping("/add")
    public String userAdd(@ModelAttribute("staff") Staff staff,
                          @RequestParam(value = "file") MultipartFile file) throws IOException {
        Staff newEmployee = new Staff();
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuid = UUID.randomUUID().toString();
            String resFilename = uuid + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resFilename));
            newEmployee.setFilename(resFilename);
        }
        newEmployee.setName(staff.getName());
        newEmployee.setSurname(staff.getSurname());
        newEmployee.setSkills(staff.getSkills());
        System.out.println(staff.getSkills());
        staffRepo.save(newEmployee);
        return "redirect:/staff";
    }

    @GetMapping("/add")
    public String userAdd(Model model) {
        Iterable<Skills> skills = skillsRepo.findAll();
        model.addAttribute("skills", skills);
        return "addEmployee";
    }
}
