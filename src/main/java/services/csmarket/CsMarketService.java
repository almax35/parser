package services.csmarket;

import config.ConfProperties;
import entity.CsMarketItem;
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
public class CsMarketService implements IService {
    private CsMarketJsonParser csMarketJsonParse;
    @Autowired
    public CsMarketService(CsMarketJsonParser csMarketJsonParse) {
        this.csMarketJsonParse = csMarketJsonParse;
    }

    public CsMarketItem searchByName(String name) throws InterruptedException, IOException {
        HttpClient client = HttpClient.newHttpClient();
        String encodedString = URLEncoder.encode(name, "UTF-8");
        URI uri = URI.create("https://market.csgo.com/api/v2/search-item-by-hash-name?key="+ConfProperties.getProperty("csMarketApiKey")+"&hash_name="+encodedString);
        HttpRequest request=HttpRequest
                .newBuilder()
                .uri(uri)
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        List<CsMarketItem> csMarketItems = csMarketJsonParse.parseResponseToList(response.body());
        return csMarketItems.get(0);
    }


}
