package alekseew.entity;

import alekseew.services.exchange.ExchangeJsonParser;
import alekseew.services.exchange.ExchangeService;

import java.io.IOException;

public class ValuteCourse {
    private double usd;
    private double uah;

    public ValuteCourse() throws IOException, InterruptedException {
        ExchangeService service=new ExchangeService(new ExchangeJsonParser());
        this.usd =service.saveValuteCourse().get(0);
        this.uah =service.saveValuteCourse().get(1);
    }

    public double getUsd() {
        return usd;
    }

    public double getUah() {
        return uah;
    }
}
