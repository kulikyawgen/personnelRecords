package com.t.ms.kulik.diplom.controller;

import com.t.ms.kulik.diplom.domain.Order;
import com.t.ms.kulik.diplom.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {
    @Autowired
    private OrderRepo orderRepo;

    @GetMapping("/orders")
    public String allOrders(Model model){
        Iterable<Order> all = orderRepo.findAll();
        model.addAttribute("all", all);
        return "orders";
    }
}
