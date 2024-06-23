package com.lamnguyen.stationery_kimi.controller;

import com.lamnguyen.stationery_kimi.dto.*;
import com.lamnguyen.stationery_kimi.service.IAuthenticationService;
import com.lamnguyen.stationery_kimi.service.ICategoryService;
import com.lamnguyen.stationery_kimi.service.IProductService;
import com.lamnguyen.stationery_kimi.service.IShoppingCartService;
import com.lamnguyen.stationery_kimi.service.impl.ProductServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class PageController {
    @Autowired
    private IProductService productService;
    @Autowired
    private IShoppingCartService shoppingCartService;
    @Autowired
    private IAuthenticationService authenticationService;
    @Autowired
    private ICategoryService iCategoryService;
    @Autowired
    private ProductServiceImpl productServiceImpl;

    @GetMapping({"/", "/index.html", "/home"})
    public String home(Model model) {
        List<ProductDisplayDTO> deals = productService.findByCategory(1L, 8, 0);
        List<ProductDisplayDTO> papers = productService.findByCategory(2L, 8, 0);
        List<ProductDisplayDTO> paintingColors = productService.findByCategory(3L, 8, 0);
        List<ProductDisplayDTO> learningTool = productService.findByCategory(4L, 8, 0);
        List<ProductDisplayDTO> highClassGifts = productService.findByCategory(5L, 8, 0);
        List<ProductDisplayDTO> pens = productService.findByCategory(6L, 8, 0);
        List<ProductDisplayDTO> books = productService.findByCategory(7L, 8, 0);
        List<ProductDisplayDTO> officeTools = productService.findByCategory(8L, 8, 0);
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

    @GetMapping("/cart.html")
    public String cart(HttpSession session, Model model) {
        List<CartItemDisplay> cartItemDisplays = shoppingCartService.loadCart(session);
        model.addAttribute("cartItems", cartItemDisplays);
        model.addAttribute("totalPrice", cartItemDisplays.stream().mapToInt(CartItemDisplay::getTotalPrice).sum());
        model.addAttribute("totalDiscount", cartItemDisplays.stream().mapToInt(CartItemDisplay::getTotalDiscount).sum());
        model.addAttribute("totalPay", cartItemDisplays.stream().mapToInt(CartItemDisplay::getTotalPay).sum());
        model.addAttribute("payment", new PaymentRestController.PaymentRequest());
        return "cart";
    }

    @GetMapping("/login/google/success")
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
        ProductSeeMoreDTO result = productService.seeMore(id);
        model.addAttribute("data", result);
        return "component/see_more";
    }

    @GetMapping("/verify.html")
    public String verify() {
        return "verify";
    }

    @GetMapping("verify-forget-password.html")
    public String verifyForgetPassword() {
        return "verify-forget-password";
    }

    @GetMapping("reset-password.html")
    public String resetPassword() {
        return "reset-password";
    }

    @GetMapping("/booth/{categoryId}")
    public String bootByPage(Model model, @RequestParam("page") Optional<Integer> page, @PathVariable("categoryId") Long categoryId) {
        CategoryDTO category = iCategoryService.findCategoryById(categoryId);
        if (category == null) return "redirect:/";

        List<ProductDisplayDTO> products = productService.findByCategory(categoryId, 8, page.stream().findFirst().orElse(0));
        model.addAttribute("products", products);
        model.addAttribute("category", category);
        model.addAttribute("brands", productService.findBrandsByCategoryId(categoryId));
        return "booth";
    }

    @GetMapping("/profile.html")
    public String profile(HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/";
        return "profile";
    }

    @GetMapping("/admin")
    public String admin(HttpSession session) {
        UserDTO userDTO = (UserDTO) session.getAttribute("user");
        if (userDTO == null || !userDTO.getRole().equalsIgnoreCase("admin"))
            return "redirect:/";
        return "admin-page";
    }
}
