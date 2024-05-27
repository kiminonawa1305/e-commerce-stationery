package com.lamnguyen.stationery_kimi.controller;

import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {
    @GetMapping("/")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }

    @GetMapping("/sign-up.html")
    public ModelAndView signUp() {
        ModelAndView modelAndView = new ModelAndView("sign-up");
        return modelAndView;
    }

    @GetMapping("/sign-in.html")
    public ModelAndView signIn() {
        ModelAndView modelAndView = new ModelAndView("sign-in");
        return modelAndView;
    }

    @Builder
    @Data
    private static class Datas {
        private String name;
        private int age;
    }
}
