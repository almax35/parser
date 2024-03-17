package services.buff;

import config.ConfProperties;
import entity.BuffItem;
import services.IService;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class BuffService implements IService {
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
        List<BuffItem> buffItems = BuffJsonParser.parseBuffResponseToList(response.body());
        return buffItems.get(0);
    }
}
