package services;

import entity.BuffItem;
import entity.TableString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services.buff.BuffService;
import services.csmarket.CsMarketService;
import services.csmoney.CsMoneyService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MainService {
   private BuffService buffService;
   private CsMoneyService csMoneyService;
   private CsMarketService csMarketService;

   @Autowired
   public MainService(BuffService buffService, CsMoneyService csMoneyService, CsMarketService csMarketService) {
        this.buffService = buffService;
        this.csMoneyService = csMoneyService;
        this.csMarketService = csMarketService;
    }
    public TableString searchWithName(String name) throws IOException, InterruptedException {
       TableString tableString=new TableString();
       BuffItem buffItem=buffService.searchByName(name);
       tableString.setName(buffItem.getName());
       tableString.setBuffPrice(buffItem.getBuffPrice());
       tableString.setSteamPrice(buffItem.getSteamPrice());
       tableString.setSteamHref(buffItem.getSteamHref());
       tableString.setImageHref(buffItem.getImageHref());
       tableString.setCsMarketPrice(csMarketService.searchByName(name).getPrice());
       tableString.setCsMoneyPrice(csMoneyService.searchByName(name).getPrice());
       return tableString;
    }

    public List<TableString> searchWithParams(double minPrice, double maxPrice,String type) throws IOException, InterruptedException {
       List<BuffItem> buffItems = buffService.searchWithParams(minPrice,maxPrice,type);
       List<TableString> tableStrings=new ArrayList<>();
       for (BuffItem buffItem: buffItems){
           String name=buffItem.getName();
           tableStrings.add(new TableString(buffItem.getName(), buffItem.getBuffPrice(), buffItem.getSteamPrice(),csMarketService.searchByName(name).getPrice(), csMoneyService.searchByName(name).getPrice(), buffItem.getSteamHref(), buffItem.getImageHref()));
           Thread.sleep(210);
       }
       return tableStrings;
    }

}
