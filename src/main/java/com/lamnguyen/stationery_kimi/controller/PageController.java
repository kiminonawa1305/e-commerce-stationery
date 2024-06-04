package com.lamnguyen.stationery_kimi.controller;

import com.lamnguyen.stationery_kimi.dto.ProductDTO;
import com.lamnguyen.stationery_kimi.dto.UserDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
public class PageController {
    @GetMapping({"/", "/index.html", "/home"})
    public String home(Model model, HttpSession session) {
        List<ProductDTO> products_1 = new ArrayList<>();
        List<ProductDTO> products_2 = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            ProductDTO productDTO = ProductDTO.builder()
                    .name("Product " + i)
                    .price(100 + i)
                    .build();
            products_1.add(productDTO);
            products_2.add(productDTO);
        }
        model.addAttribute("deals_1", products_1);
        model.addAttribute("deals_2", products_2);
        System.out.println(session.getAttribute("user"));
        return "index";
    }

    @GetMapping("/sign-up.html")
    public String signUp() {
        return "sign-up";
    }

    @GetMapping("/sign-in.html")
    public String signIn() {
        return "sign-in";
    }

    @GetMapping("/forget-password.html")
    public String forgetPassword() {
        return "forget-password";
    }

    @GetMapping("/sign-out")
    public String signOut(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/";
    }

    @GetMapping("/cart")
    public ModelAndView cart(HttpSession session) {
        Map<String, String> cart = (Map<String, String>) session.getAttribute("cart");
        if (cart == null)
            cart = new HashMap<>();

        session.setAttribute("cart", cart);
        return new ModelAndView("cart");
    }

    @GetMapping("/login/google")
    public String login(OAuth2AuthenticationToken oAuth2AuthenticationToken, HttpSession session) {
        DefaultOAuth2User oidcUser = (DefaultOAuth2User) oAuth2AuthenticationToken.getPrincipal();
        Map<String, Object> attributes = oidcUser.getAttributes();
        UserDTO userDTO = UserDTO.builder()
                .email((String) attributes.get("email"))
                .firstName((String) attributes.get("family_name"))
                .lastName((String) attributes.get("given_name"))
                .avatar((String) attributes.get("picture"))
                .build();
        session.setAttribute("user", userDTO);
        return "redirect:/";
    }
}
