package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import services.MainService;

import java.io.IOException;

@Controller
public class MainController {
    private MainService mainService;
    @Autowired
    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @PostMapping("/table")
    public String showTable(@RequestParam  String name, @RequestParam  double minPrice, @RequestParam  double maxPrice, @RequestParam  String type, Model model) throws IOException, InterruptedException {
        System.out.println(name+" "+minPrice+" "+maxPrice+" "+type);
        if (name!=null){
            model.addAttribute("result",mainService.searchWithName(name));
        }
        else {
            model.addAttribute("result",mainService.searchWithParams(minPrice,maxPrice,type));
        }
        return "table";
    }
}
