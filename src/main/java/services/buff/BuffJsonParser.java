package services.buff;

import entity.BuffItem;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BuffJsonParser  {
    public BuffJsonParser() {
    }

    public static BuffItem parseJsonBuff(JSONObject object) {
        String name=object.getString("name");
        double buffPrice=object.getDouble("sell_min_price");
        JSONObject steamInfo= object.getJSONObject("goods_info");
        double steamPrice= steamInfo.getDouble("steam_price_cny");
        int quantity=object.getInt("sell_num");
        String steamHref = object.getString("steam_market_url");
        String imageHref = steamInfo.getString("original_icon_url");
        return new BuffItem(name, buffPrice, steamPrice, quantity, steamHref, imageHref);
    }

    public List<BuffItem> parseResponseToList(String body){
        ArrayList<BuffItem> list=new ArrayList<>();
        JSONObject object=new JSONObject(body);
        System.out.println(body);
        JSONObject data= object.getJSONObject("data");
        JSONArray items =data.getJSONArray("items");
        for (int i=0; i<items.length(); i++){
            JSONObject item=items.getJSONObject(i);
            list.add(parseJsonBuff(item));
        }
        return list;

    }

}
