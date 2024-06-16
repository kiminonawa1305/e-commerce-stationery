package com.lamnguyen.stationery_kimi.controller;

import com.lamnguyen.stationery_kimi.dto.CartItemDisplay;
import com.lamnguyen.stationery_kimi.dto.ProductDisplayDTO;
import com.lamnguyen.stationery_kimi.dto.ProductSeeMoreDTO;
import com.lamnguyen.stationery_kimi.dto.UserDTO;
import com.lamnguyen.stationery_kimi.request.ForgetPasswordRequest;
import com.lamnguyen.stationery_kimi.request.ResetPasswordRequest;
import com.lamnguyen.stationery_kimi.response.ApiResponse;
import com.lamnguyen.stationery_kimi.service.IAuthenticationService;
import com.lamnguyen.stationery_kimi.service.IProductService;
import com.lamnguyen.stationery_kimi.service.IShoppingCartService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;

@Controller
public class PageController {
    @Autowired
    private IProductService service;
    @Autowired
    private IShoppingCartService shoppingCartService;
    @Autowired
    private IAuthenticationService authenticationService;

    @GetMapping({"/", "/index.html", "/home"})
    public String home(Model model) {
        List<ProductDisplayDTO> deals = service.findByCategory(1L, 8, 0);
        List<ProductDisplayDTO> papers = service.findByCategory(2L, 8, 0);
        List<ProductDisplayDTO> paintingColors = service.findByCategory(3L, 8, 0);
        List<ProductDisplayDTO> learningTool = service.findByCategory(4L, 8, 0);
        List<ProductDisplayDTO> highClassGifts = service.findByCategory(5L, 8, 0);
        List<ProductDisplayDTO> pens = service.findByCategory(6L, 8, 0);
        List<ProductDisplayDTO> books = service.findByCategory(7L, 8, 0);
        List<ProductDisplayDTO> officeTools = service.findByCategory(8L, 8, 0);
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
    public String cart(HttpSession session, Model model) {
        List<CartItemDisplay> cartItemDisplays = shoppingCartService.loadCart(session);
        model.addAttribute("cartItems", cartItemDisplays);
        return "cart";
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
    public String seeMore(@PathVariable("id") Long id, Model model) {
        ProductSeeMoreDTO result = service.seeMore(id);
        model.addAttribute("data", result);
        return "component/see_more";
    }

    @GetMapping("/verify.html")
    public String verify() {
        return "verify";
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

    @GetMapping("verify-forget-password.html")
    public String verifyForgetPassword() {
        return "verify-forget-password";
    }

    @GetMapping("reset-password.html")
    public String resetPassword() {
        return "reset-password";
    }

    @PostMapping("/forget-password")
    public String forgetPassword(HttpSession session, @Valid @ModelAttribute ForgetPasswordRequest forgetPasswordRequest) {
        String email = forgetPasswordRequest.getEmail();
        session.setAttribute("email", authenticationService.forgotPassword(email));
        return "redirect:/verify-forget-password.html";
    }
}
