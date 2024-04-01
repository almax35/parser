package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services.buff.BuffService;
import services.csmarket.CsMarketService;
import services.csmoney.CsMoneyService;

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
    public void searchWithParams(double minPrice, double maxPrice, int type){

    }

}
