package services.csmoney;

import entity.CsMoneyItem;
import entity.ItemInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services.IService;


import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Component
public class CsMoneyService implements IService {
    private CsMoneyJsonParser csMoneyJsonParser ;
    @Autowired
    public CsMoneyService(CsMoneyJsonParser csMoneyJsonParser) {
        this.csMoneyJsonParser = csMoneyJsonParser;
    }
    @Override
    public ItemInterface searchByName(String name) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        String encodedString = URLEncoder.encode(name, "UTF-8");
        URI uri = URI.create("https://cs.money/1.0/market/sell-orders?limit=1&name="+encodedString+"&order=asc&sort=price");
        HttpRequest request=HttpRequest
                .newBuilder()
                .uri(uri)
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        List<CsMoneyItem> csMoneyItems = csMoneyJsonParser.parseResponseToList(response.body());
        return csMoneyItems.get(0);
    }
}
