package alekseew.services.csmoney;

import alekseew.entity.CsMoneyItem;
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
public class CsMoneyService implements IService {
    private final CsMoneyJsonParser csMoneyJsonParser ;
    @Autowired
    public CsMoneyService(CsMoneyJsonParser csMoneyJsonParser) {
        this.csMoneyJsonParser = csMoneyJsonParser;
    }
    @Override
    public CsMoneyItem searchByName(String name) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        String encodedString = URLEncoder.encode(name, StandardCharsets.UTF_8);
        URI uri = URI.create("https://cs.money/1.0/market/sell-orders?limit=1&name="+encodedString+"&order=asc&sort=price");
        HttpRequest request=HttpRequest
                .newBuilder()
                .uri(uri)
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        CsMoneyItem csMoneyItem = csMoneyJsonParser.parseResponseToItem(response.body());
        if(csMoneyItem==null){
            return new CsMoneyItem(name,0);
        }
        return csMoneyItem;
    }

}
