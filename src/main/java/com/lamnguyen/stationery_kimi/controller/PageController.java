package com.lamnguyen.stationery_kimi.controller;

import com.lamnguyen.stationery_kimi.dto.ProductDTO;
import com.lamnguyen.stationery_kimi.dto.ProductSeeMoreDTO;
import com.lamnguyen.stationery_kimi.dto.UserDTO;
import com.lamnguyen.stationery_kimi.service.IProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class PageController {
    @Autowired
    private IProductService service;

    @GetMapping({"/", "/index.html", "/home"})
    public String home(Model model, HttpSession session) {
        List<ProductDTO> deals = service.findByCategory("deals");
        List<ProductDTO> papers = service.findByCategory("papers");
        List<ProductDTO> paintingColors = service.findByCategory("paintingColors");
        List<ProductDTO> learningTool = service.findByCategory("learningTool");
        List<ProductDTO> highClassGifts = service.findByCategory("highClassGifts");
        List<ProductDTO> pens = service.findByCategory("pens");
        List<ProductDTO> books = service.findByCategory("books");
        List<ProductDTO> officeTools = service.findByCategory("officeTools");
        model.addAttribute("deals", deals);
        model.addAttribute("papers", papers);
        model.addAttribute("paintingColors", paintingColors);
        model.addAttribute("learningTools", learningTool);
        model.addAttribute("pens", pens);
        model.addAttribute("highClassGifts", highClassGifts);
        model.addAttribute("officeTools", officeTools);
        model.addAttribute("books", books);
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
    public ModelAndView cart() {
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

    @GetMapping("/api/products/see-more/{id}")
    public String seeMore(@PathVariable("id") Integer id, Model model) {
        ProductSeeMoreDTO result = service.seeMore(id);
        model.addAttribute("data", result);
        return "component/see_more";
    }
}
