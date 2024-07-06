package org.example.dajava.Controller.FunctionController;

import lombok.RequiredArgsConstructor;
import org.example.dajava.Model.Xe;
import org.example.dajava.Service.XeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/xeinfor")
public class MotorInforController {
    @Autowired
    private final XeService xeService;
    @GetMapping("/{id}")
    public String Xeinfor(@PathVariable("id") String id, Model model){
        Xe xe = xeService.findXeByBienSoXe(id);
        model.addAttribute("xe", xe);
        return "Function/xeinfor";
    }
}
