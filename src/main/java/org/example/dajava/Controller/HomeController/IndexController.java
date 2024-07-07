package org.example.dajava.Controller.HomeController;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.example.dajava.Model.TaiKhoan;
import org.example.dajava.Model.Xe;
import org.example.dajava.Service.TaiKhoanService;
import org.example.dajava.Service.XeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequiredArgsConstructor
public class IndexController {
    private final TaiKhoanService taikhoanService;
    @Autowired
    private XeService xeService;
    @GetMapping("/")
    public String Index(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        List<Xe> xeList = xeService.findAll();
        model.addAttribute("xeList", xeList);
        return "/Home/Index";
    }

    @GetMapping("/login")
    public String login() {
        return "/Home/Login";
    }

    @GetMapping("/register")
    public String register(@NotNull Model model) {
        model.addAttribute("user", new TaiKhoan());
        return "/Home/Index";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") TaiKhoan taikhoan,@NotNull BindingResult bindingResult,Model model) {
        if(bindingResult.hasErrors()) {
            var errors = bindingResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toArray(String[]::new);
            model.addAttribute("errors", errors);
            return "/Home/Index";
        }
        taikhoanService.save(taikhoan);
        taikhoanService.setDefaultChucVu(taikhoan.getEmail());
        return "redirect:/";
    }


    @GetMapping("/forgot-password")
    public String showForgotPasswordForm() {
        return "/Home/ForgotPassword";
    }

    @PostMapping("/forgot-password")
    public String processForgotPassword(@RequestParam("email") String email) {
        taikhoanService.sendPasswordReset(email);
        return "redirect:/login?forgotPasswordSuccess";
    }


}
