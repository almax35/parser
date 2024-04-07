package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import services.MainService;

import java.io.IOException;

@Controller
public class MainController {
    private MainService mainService;
    @Autowired
    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping("/table")
    public String showTable(@PathVariable String name, @PathVariable double minPrice, @PathVariable double maxPrice, @PathVariable String type, Model model) throws IOException, InterruptedException {
        if (name!=null){
            model.addAttribute("result",mainService.searchWithName(name));
        }
        else {
            model.addAttribute("result",mainService.searchWithParams(minPrice,maxPrice,type));
        }
        return "table";
    }
}
