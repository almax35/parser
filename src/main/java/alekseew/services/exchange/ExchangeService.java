package alekseew.services.exchange;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Component
public class ExchangeService {
    private final ExchangeJsonParser exchangeJsonParser;

    @Autowired
    public ExchangeService(ExchangeJsonParser exchangeJsonParser) {
        this.exchangeJsonParser = exchangeJsonParser;
    }

    public List<Double> saveValuteCourse() throws InterruptedException, IOException {
        HttpClient client = HttpClient.newHttpClient();
        URI uri = URI.create("https://www.cbr-xml-daily.ru/daily_json.js");
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(uri)
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        ArrayList<Double> course = new ArrayList<>();
        course.add(exchangeJsonParser.getUsdToRub(response.body()));
        course.add(exchangeJsonParser.getCnyToRub(response.body()));
        return course;
    }
}
