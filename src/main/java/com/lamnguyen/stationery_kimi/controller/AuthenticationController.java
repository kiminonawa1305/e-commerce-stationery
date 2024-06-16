package com.lamnguyen.stationery_kimi.controller;

import com.lamnguyen.stationery_kimi.request.ForgetPasswordRequest;
import com.lamnguyen.stationery_kimi.request.ResetPasswordRequest;
import com.lamnguyen.stationery_kimi.service.IAuthenticationService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthenticationController {
    @Autowired
    private IAuthenticationService authenticationService;

    @PostMapping("/forget-password")
    public String forgetPassword(HttpSession session, @Valid @ModelAttribute ForgetPasswordRequest forgetPasswordRequest) {
        String email = forgetPasswordRequest.getEmail();
        session.setAttribute("email", authenticationService.forgotPassword(email));
        return "redirect:/verify-forget-password.html";
    }


    @PostMapping("/reset-password")
    public String changePassword(Model model, @Valid @ModelAttribute ResetPasswordRequest resetPasswordRequest) {
        String password = resetPasswordRequest.getPassword();
        Long id = resetPasswordRequest.getId();
        try {
            authenticationService.resetPassword(id, password);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "reset-password";
        }

        return "sign-in";
    }
}
