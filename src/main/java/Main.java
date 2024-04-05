import entity.ItemCategory;
import services.MainService;
import services.buff.BuffJsonParser;
import services.buff.BuffService;
import services.csmarket.CsMarketJsonParser;
import services.csmarket.CsMarketService;
import services.csmoney.CsMoneyJsonParser;
import services.csmoney.CsMoneyService;
import services.exchange.ExchangeJsonParser;
import services.exchange.ExchangeService;

import java.io.*;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class Main {
    public static void main(String[] args) throws IOException, InterruptedException{

        BuffService buffService=new BuffService(new BuffJsonParser(), new ItemCategory());
        MainService mainService=new MainService(buffService, new CsMoneyService(new CsMoneyJsonParser()), new CsMarketService(new CsMarketJsonParser()));
        System.out.println(mainService.searchWithParams(100,150,"weapon_aug"));
    }


}
