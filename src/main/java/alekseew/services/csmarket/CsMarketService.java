package alekseew.services.csmarket;

import alekseew.config.ConfProperties;
import alekseew.entity.CsMarketItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import alekseew.services.IService;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@Component
public class CsMarketService implements IService {
    private final CsMarketJsonParser csMarketJsonParse;

    @Autowired
    public CsMarketService(CsMarketJsonParser csMarketJsonParse) {
        this.csMarketJsonParse = csMarketJsonParse;
    }

    public CsMarketItem searchByName(String name) throws InterruptedException, IOException {
        HttpClient client = HttpClient.newHttpClient();
        String encodedString = URLEncoder.encode(name, StandardCharsets.UTF_8);
        URI uri = URI.create("https://market.csgo.com/api/v2/search-item-by-hash-name?key=" + ConfProperties.getProperty("csMarketApiKey") + "&hash_name=" + encodedString);
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(uri)
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        CsMarketItem csMarketItem = csMarketJsonParse.parseResponseToItem(response.body());
        if (csMarketItem == null) {
            return new CsMarketItem(name, 0);
        }
        return csMarketItem;
    }
}
