package alekseew.services;

import alekseew.entity.BuffItem;
import alekseew.entity.TableString;
import alekseew.entity.ValuteCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import alekseew.services.buff.BuffService;
import alekseew.services.csmarket.CsMarketService;
import alekseew.services.csmoney.CsMoneyService;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class MainService {
   private BuffService buffService;
   private CsMoneyService csMoneyService;
   private CsMarketService csMarketService;
   private ValuteCourse valuteCourse;



   @Autowired
   public MainService(BuffService buffService, CsMoneyService csMoneyService, CsMarketService csMarketService) throws IOException, InterruptedException {
        this.buffService = buffService;
        this.csMoneyService = csMoneyService;
        this.csMarketService = csMarketService;
        valuteCourse=new ValuteCourse();
    }
    public List<TableString> searchWithName(String name) throws IOException, InterruptedException {
       List<TableString> tableStrings=new ArrayList<>();
       TableString tableString=new TableString();
       BuffItem buffItem=buffService.searchByName(name);
       double roundBuffPrice= Math.round( buffItem.getBuffPrice()* valuteCourse.getUah()* 100.0) / 100.0;
       double roundSteamPrice= Math.round( buffItem.getSteamPrice()* valuteCourse.getUah()* 100.0) / 100.0;
       double roundCsMoneyPrice= Math.round( csMoneyService.searchByName(name).getPrice()* valuteCourse.getUsd()* 100.0) / 100.0;
       tableString.setName(buffItem.getName());
       tableString.setBuffPrice(roundBuffPrice);
       tableString.setSteamPrice(roundSteamPrice);
       tableString.setSteamHref(buffItem.getSteamHref());
       tableString.setImageHref(buffItem.getImageHref());
       tableString.setCsMarketPrice(csMarketService.searchByName(name).getPrice());
       tableString.setCsMoneyPrice(roundCsMoneyPrice);
       tableStrings.add(tableString);
       return tableStrings;
    }

    public List<TableString> searchWithParams(double minPrice, double maxPrice,String type) throws IOException, InterruptedException {
        double minRubPrice = Math.round( minPrice/valuteCourse.getUah()* 100.0) / 100.0;
        double maxRubPrice= Math.round( maxPrice/valuteCourse.getUah()* 100.0) / 100.0;
       List<BuffItem> buffItems = buffService.searchWithParams(minRubPrice,maxRubPrice,type);
        System.out.println(buffItems);
       List<TableString> tableStrings=new ArrayList<>();
       for (BuffItem buffItem: buffItems){
           String name=buffItem.getName();
           double roundBuffPrice= Math.round( buffItem.getBuffPrice()* valuteCourse.getUah()* 100.0) / 100.0;
           double roundSteamPrice= Math.round( buffItem.getSteamPrice()* valuteCourse.getUah()* 100.0) / 100.0;
           double roundCsMoneyPrice= Math.round( csMoneyService.searchByName(name).getPrice()* valuteCourse.getUsd()* 100.0) / 100.0;
           TableString tableString=new TableString(buffItem.getName(),roundBuffPrice , roundSteamPrice,csMarketService.searchByName(name).getPrice(), roundCsMoneyPrice, buffItem.getSteamHref(), buffItem.getImageHref());
           tableStrings.add(tableString);
           Thread.sleep(210);
       }
       return tableStrings;
    }

}
