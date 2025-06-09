package org.example.BookMarket.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.BookMarket.domain.Cart;
import org.example.BookMarket.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping
    public String requestCartId(HttpServletRequest request) {
        System.out.println("aaaa");
        String sessionId = request.getSession(true).getId();
        return "redirect:/cart/"+sessionId;
    }

    @PostMapping
    public @ResponseBody Cart create(@RequestBody Cart cart) {
        System.out.println("bbb");
        return cartService.create(cart);
    }
}
