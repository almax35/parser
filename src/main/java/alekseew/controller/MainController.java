package alekseew.controller;

import alekseew.entity.TableString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import alekseew.services.MainService;

import java.io.IOException;
import java.util.ArrayList;


@Controller

public class MainController {
    private MainService mainService;
    @Autowired
    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping("/table")
    public String initTable(Model model){
        model.addAttribute("results",new ArrayList<TableString>());
        return "table";
    }

    @PostMapping("/table")
    public String showTable(@RequestParam(required=false)  String name, @RequestParam(required=false)  double minPrice, @RequestParam(required=false)  double maxPrice, @RequestParam(required=false)  String type, Model model) throws IOException, InterruptedException {
        System.out.println(name+" "+minPrice+" "+maxPrice+" "+type);

        if (name.equals("")){
            model.addAttribute("results", mainService.searchWithParams(minPrice,maxPrice,type));
        }
        else {
            model.addAttribute("results", mainService.searchWithName(name));
        }
        return "/table";
    }
}
