package alekseew.services.buff;

import alekseew.config.ConfProperties;
import alekseew.entity.BuffItem;
import alekseew.entity.ItemCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import alekseew.services.IService;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Component
public class BuffService implements IService {

    private  BuffJsonParser buffJsonParser;
    private ItemCategory category;
    @Autowired
    public BuffService(BuffJsonParser buffJsonParser, ItemCategory category) {
        this.buffJsonParser = buffJsonParser;
        this.category = category;
    }

    public BuffItem searchByName(String name) throws InterruptedException, IOException {

        HttpClient client = HttpClient.newHttpClient();
        String encodedString = URLEncoder.encode(name, "UTF-8");
        HttpRequest request1=HttpRequest
                .newBuilder()
                .setHeader("Cookie", ConfProperties.getProperty("cookieBuff"))
                .uri(URI.create("https://buff.163.com/api/market/goods?game=csgo&page_num=1&search="+encodedString))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request1, HttpResponse.BodyHandlers.ofString());
        List<BuffItem> buffItems = buffJsonParser.parseResponseToList(response.body());
        return buffItems.get(0);
    }

    public List<BuffItem> searchWithParams(double minPrice, double maxPrice, String type) throws IOException, InterruptedException {
        StringBuilder uri=new StringBuilder("https://buff.163.com/api/market/goods?game=csgo&page_num=1&page_size=10");
        if (minPrice!=-1d){
            uri.append("&min_price=").append(minPrice);
        }
        if (maxPrice!=-1d){
            uri.append("&max_price=").append(maxPrice);
        }
        if (category.getCategoryWeapon().contains(type)){
            uri.append("&category=").append(type);
        }
        if (category.getCategoryType().contains(type)){
            uri.append("&category_group=").append(type);
        }
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request=HttpRequest
                .newBuilder()
                .setHeader("Cookie", ConfProperties.getProperty("cookieBuff"))
                .uri(URI.create(uri.toString()))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return buffJsonParser.parseResponseToList(response.body());
    }

}
