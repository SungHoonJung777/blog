package org.fullstack4.springboot.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Log4j2
@Controller
public class SamplceContoroller {

    @GetMapping("/ex/ex1")
    public void ex1(Model model) {
        List<String> list= Arrays.asList("AAA", "BBB","CCC");
        model.addAttribute("list",list);
    }
}
