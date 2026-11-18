package com.internship.mybignumber.web;

import com.internship.mybignumber.MyBigNumber;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdditionController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("num1", "");
        model.addAttribute("num2", "");
        return "index";
    }

    @PostMapping("/calculate")
    public String calculate(
            @RequestParam(name = "num1", required = false, defaultValue = "") String num1,
            @RequestParam(name = "num2", required = false, defaultValue = "") String num2,
            Model model) {

        String trimmedNum1 = num1.trim();
        String trimmedNum2 = num2.trim();

        model.addAttribute("num1", trimmedNum1);
        model.addAttribute("num2", trimmedNum2);

        // Validation for user convenience
        if (!trimmedNum1.matches("\\d*") || !trimmedNum2.matches("\\d*")) {
            model.addAttribute("error", "Dữ liệu chỉ được chứa các ký tự số từ 0 đến 9!");
            return "index";
        }

        List<String> steps = new ArrayList<>();
        // Instantiate MyBigNumber with custom listener to capture progress for Web UI
        MyBigNumber myBigNumber = new MyBigNumber((stepNum, msg) -> steps.add("Bước " + stepNum + ": " + msg));

        String result = myBigNumber.sum(trimmedNum1, trimmedNum2);

        model.addAttribute("result", result);
        model.addAttribute("steps", steps);

        return "index";
    }
}
