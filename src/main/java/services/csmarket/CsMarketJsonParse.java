package services.csmarket;

import entity.CsMarketItem;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CsMarketJsonParse  {
    public  CsMarketItem parseJsonObject(JSONObject object){
        String name=object.getString("market_hash_name");
        double price=object.getDouble("price");
        int count=object.getInt("count");
        return new CsMarketItem(name, price, count);
    }

    public List<CsMarketItem> parseResponseToList(String body){
        ArrayList<CsMarketItem> list=new ArrayList<>();
        JSONObject object=new JSONObject(body);
        JSONArray items =object.getJSONArray("data");
        for (int i=0; i<items.length(); i++){
            JSONObject item=items.getJSONObject(i);
            list.add(parseJsonObject(item));
        }
        return list;

    }
}
