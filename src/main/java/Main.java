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
        ExchangeService exchangeService=new ExchangeService(new ExchangeJsonParser());
        exchangeService.saveValuteCourse();
    }

}
