package com.t.ms.kulik.diplom.controller;

import com.t.ms.kulik.diplom.domain.Order;
import com.t.ms.kulik.diplom.domain.Staff;
import com.t.ms.kulik.diplom.domain.User;
import com.t.ms.kulik.diplom.domain.auto.Car;
import com.t.ms.kulik.diplom.domain.auto.Producer;
import com.t.ms.kulik.diplom.repo.*;
import javafx.print.Collation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class GreetingController {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private StaffRepo staffRepo;

    @Autowired
    private ProducerRepo producerRepo;
    @Autowired
    private CarRepo carRepo;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @PostMapping("/makeAnAppointment")
    public String addNewOrder(@RequestParam(name = "producer") String producer, @RequestParam(name = "car") String car,
                              @AuthenticationPrincipal User user) {
        System.out.println(producer);
        Order order = new Order();
        order.setProducer(producer);
        order.setCar(car);
        order.setAuthor(user);
        order.setStaff(staffRepo.findById(1L).get());
        orderRepo.save(order);
        return "redirect:/";
    }

    @GetMapping("/makeAnAppointment")
    public String addNewOrder(Model model) {

        Iterable<Producer> allProducer = producerRepo.findAll();
        model.addAttribute("allProducers", allProducer);
        return "makeAnAppointment";
    }
}
