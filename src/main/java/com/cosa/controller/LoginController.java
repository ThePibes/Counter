package com.cosa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Alessandro
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String indexForm(Model model) {
        model.addAttribute("loginError", false);
        return "login";
    }
//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public String indexLogin(Model model) {
//        return "/";
//    }
//
    @RequestMapping("/login/error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }


}
