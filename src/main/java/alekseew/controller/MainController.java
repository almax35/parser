package alekseew.controller;

import alekseew.entity.TableString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import alekseew.services.MainService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;


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
    public String showTable(@RequestParam(required = false) String name, @RequestParam(required = false) double minPrice, @RequestParam(required = false) double maxPrice, @RequestParam(required = false) int quantity, @RequestParam(required = false) String type, Model model ) throws IOException, InterruptedException {
        if (Objects.equals(name, "")){
            ArrayList<TableString> tableStrings= (ArrayList<TableString>) mainService.searchWithParams(minPrice,maxPrice,quantity, type);
            if (tableStrings==null){
                model.addAttribute("message","Не удалось найти предметы по заданным параметрам поиска");
            }
            model.addAttribute("results", tableStrings);
        }
        else {
            ArrayList<TableString> tableStrings= (ArrayList<TableString>) mainService.searchWithName(name);
            if (tableStrings==null){
                model.addAttribute("message","Не удалось найти предмет с заданным названием");
            }
            model.addAttribute("results", tableStrings);
        }

        return "/table";
    }
    @PostMapping ("/sort")
    public String sortTable(@RequestParam String typeSort, Model model){
        mainService.sortTable(typeSort);
        model.addAttribute("results", mainService.getStrings());
        return "/table";
    }
}
