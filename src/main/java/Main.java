import config.ConfProperties;

import java.io.*;
import java.net.*;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {



        System.out.println(items);
        String name=items.get(0).getName();
        String encodedString = URLEncoder.encode(name, "UTF-8");
        System.out.println(encodedString);
        URI uri = URI.create("https://market.csgo.com/api/v2/search-item-by-hash-name?key="+ConfProperties.getProperty("csMarketApiKey")+"&hash_name="+encodedString);
        HttpRequest request=HttpRequest
                .newBuilder()
                .uri(uri)
                .GET()
                .build();
        HttpResponse<String> response1 = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response1.body());
        System.out.println();
        URI uri1 = URI.create("https://cs.money/1.0/market/sell-orders?limit=1&name="+encodedString+"&order=asc&sort=price");
        HttpRequest request2=HttpRequest
                .newBuilder()
                .uri(uri1)
                .GET()
                .build();
        HttpResponse<String> response2 = client.send(request2, HttpResponse.BodyHandlers.ofString());
        System.out.println(response2.body());
    }

}
