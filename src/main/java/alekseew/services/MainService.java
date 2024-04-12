package alekseew.services;

import alekseew.entity.BuffItem;
import alekseew.entity.TableString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import alekseew.services.buff.BuffService;
import alekseew.services.csmarket.CsMarketService;
import alekseew.services.csmoney.CsMoneyService;
import alekseew.services.exchange.ExchangeService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MainService {
   private BuffService buffService;
   private CsMoneyService csMoneyService;
   private CsMarketService csMarketService;
   private ExchangeService exchangeService;


   @Autowired
   public MainService(BuffService buffService, CsMoneyService csMoneyService, CsMarketService csMarketService, ExchangeService exchangeService) {
        this.buffService = buffService;
        this.csMoneyService = csMoneyService;
        this.csMarketService = csMarketService;
        this.exchangeService=exchangeService;
    }
    public List<TableString> searchWithName(String name) throws IOException, InterruptedException {
       List<TableString> tableStrings=new ArrayList<>();
       TableString tableString=new TableString();
       BuffItem buffItem=buffService.searchByName(name);
       tableString.setName(buffItem.getName());
       tableString.setBuffPrice(buffItem.getBuffPrice());
       tableString.setSteamPrice(buffItem.getSteamPrice());
       tableString.setSteamHref(buffItem.getSteamHref());
       tableString.setImageHref(buffItem.getImageHref());
       tableString.setCsMarketPrice(csMarketService.searchByName(name).getPrice());
       tableString.setCsMoneyPrice(csMoneyService.searchByName(name).getPrice());
       tableStrings.add(tableString);
       return tableStrings;
    }

    public List<TableString> searchWithParams(double minPrice, double maxPrice,String type) throws IOException, InterruptedException {
       List<BuffItem> buffItems = buffService.searchWithParams(minPrice,maxPrice,type);
        System.out.println(buffItems);
       List<TableString> tableStrings=new ArrayList<>();
       for (BuffItem buffItem: buffItems){
           String name=buffItem.getName();
           TableString tableString=new TableString(buffItem.getName(), buffItem.getBuffPrice(), buffItem.getSteamPrice(),csMarketService.searchByName(name).getPrice(), csMoneyService.searchByName(name).getPrice(), buffItem.getSteamHref(), buffItem.getImageHref());
           tableStrings.add(tableString);
           Thread.sleep(210);
       }
       return tableStrings;
    }

}
