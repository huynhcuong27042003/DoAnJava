package org.example.dajava.Controller.FunctionController;

import lombok.RequiredArgsConstructor;
import org.example.dajava.Model.TaiKhoan;
import org.example.dajava.Service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/userinfor")
@RequiredArgsConstructor
public class TaiKhoanController {
    @Autowired
    private final TaiKhoanService taiKhoanService;
    @GetMapping("/{id}")
    public String taiKhoan(@PathVariable String id, Model model) {
        TaiKhoan tk = taiKhoanService.findTKByEmail(id);
        model.addAttribute("tk", tk);
        return "Function/userinfor";
    }
}
