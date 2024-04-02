package services.buff;

import config.ConfProperties;
import entity.BuffItem;
import entity.ItemCategory;
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
    public List<BuffItem> searchWithType(String type) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        if (category.getCategoryType().contains(type)){
            HttpRequest request=HttpRequest
                    .newBuilder()
                    .setHeader("Cookie", ConfProperties.getProperty("cookieBuff"))
                    .uri(URI.create("https://buff.163.com/api/market/goods?game=csgo&page_num=1&page_size=80&category_group="+type))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return buffJsonParser.parseResponseToList(response.body());
        }
        else {
            if (category.getCategoryWeapon().contains(type)) {
                HttpRequest request = HttpRequest
                        .newBuilder()
                        .setHeader("Cookie", ConfProperties.getProperty("cookieBuff"))
                        .uri(URI.create("https://buff.163.com/api/market/goods?game=csgo&page_num=1&page_size=80&category=" + type))
                        .GET()
                        .build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                return buffJsonParser.parseResponseToList(response.body());
            }
            else {
                return null;
            }
        }
    }
    public List<BuffItem> searchWithMinPrice(double minPrice) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request=HttpRequest
                    .newBuilder()
                    .setHeader("Cookie", ConfProperties.getProperty("cookieBuff"))
                    .uri(URI.create("https://buff.163.com/api/market/goods?game=csgo&page_num=1&page_size=80&min_price="+minPrice))
                    .GET()
                    .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return buffJsonParser.parseResponseToList(response.body());
    }
    public List<BuffItem> searchWithMaxPrice(double maxPrice) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request=HttpRequest
                .newBuilder()
                .setHeader("Cookie", ConfProperties.getProperty("cookieBuff"))
                .uri(URI.create("https://buff.163.com/api/market/goods?game=csgo&page_num=1&page_size=80&max_price="+maxPrice))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return buffJsonParser.parseResponseToList(response.body());
    }
    public List<BuffItem> searchWithMinAndMaxPrice(double minPrice, double maxPrice) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request=HttpRequest
                .newBuilder()
                .setHeader("Cookie", ConfProperties.getProperty("cookieBuff"))
                .uri(URI.create("https://buff.163.com/api/market/goods?game=csgo&page_num=1&page_size=80&min_price="+minPrice+"&max_price="+maxPrice))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return buffJsonParser.parseResponseToList(response.body());
    }
    public List<BuffItem> searchWithoutParams() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request=HttpRequest
                .newBuilder()
                .setHeader("Cookie", ConfProperties.getProperty("cookieBuff"))
                .uri(URI.create("https://buff.163.com/api/market/goods?game=csgo&page_num=1&page_size=80"))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return buffJsonParser.parseResponseToList(response.body());
    }
    public List<BuffItem> searchWithoutAllParams() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request=HttpRequest
                .newBuilder()
                .setHeader("Cookie", ConfProperties.getProperty("cookieBuff"))
                .uri(URI.create("https://buff.163.com/api/market/goods?game=csgo&page_num=1&page_size=80"))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return buffJsonParser.parseResponseToList(response.body());
    }

}
