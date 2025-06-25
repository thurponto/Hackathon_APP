package com.gabarito.correcao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard-professor")
    public String mostrarDashboard() {
        return "dashboard-professor";
    }
}