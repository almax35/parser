package alekseew.services.exchange;

import alekseew.config.ConfProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class ExchangeService {
    private ExchangeJsonParser exchangeJsonParser;
    @Autowired
    public ExchangeService(ExchangeJsonParser exchangeJsonParser) {
        this.exchangeJsonParser = exchangeJsonParser;
    }

    public void saveValuteCourse() throws InterruptedException, IOException {
        HttpClient client = HttpClient.newHttpClient();
        URI uri = URI.create("https://www.cbr-xml-daily.ru/daily_json.js");
        HttpRequest request=HttpRequest
                .newBuilder()
                .uri(uri)
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(exchangeJsonParser.getCnyToRub(response.body()));
        System.out.println(exchangeJsonParser.getUsdToRub(response.body()));
    }
}
