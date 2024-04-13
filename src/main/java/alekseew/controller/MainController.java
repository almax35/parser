package alekseew.controller;

import alekseew.entity.TableString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String showTable(@Valid @ModelAttribute FormModel formModel, BindingResult bindingResult Model model) throws IOException, InterruptedException {

        if (name.equals("")){
            model.addAttribute("results", mainService.searchWithParams(minPrice,maxPrice,type));
        }
        else {
            System.out.println(name);
            model.addAttribute("results", mainService.searchWithName(name));
        }
        return "/table";
    }
}
