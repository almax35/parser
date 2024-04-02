import entity.ItemCategory;
import services.buff.BuffJsonParser;
import services.buff.BuffService;
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
        System.out.println(buffService.searchWithMinAndMaxPrice(23,100).size());
    }

}
