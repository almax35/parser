package alekseew.services.csmarket;

import alekseew.entity.CsMarketItem;
import org.json.JSONArray;
import org.json.JSONObject;
import alekseew.services.IJsonParser;
import org.springframework.stereotype.Component;

@Component
public class CsMarketJsonParser implements IJsonParser {
    public CsMarketItem parseResponseToItem(String body){
        JSONObject object=new JSONObject(body);
        JSONArray items =object.getJSONArray("data");
        if (items.isEmpty()){
            return null;
        }
        JSONObject item=items.getJSONObject(0);
        String name=item.getString("market_hash_name");
        double price=item.getDouble("price");
        return new CsMarketItem(name, price/100);
    }
}
