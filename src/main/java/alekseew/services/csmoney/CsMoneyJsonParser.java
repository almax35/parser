package alekseew.services.csmoney;

import alekseew.entity.CsMoneyItem;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class CsMoneyJsonParser {
    public CsMoneyItem parseResponseToItem(String body) {
        JSONObject object = new JSONObject(body);
        if (object.keySet().contains("errors")) {
            return null;
        }
        JSONArray items = object.getJSONArray("items");
        JSONObject item = items.getJSONObject(0);
        String name = item.getJSONObject("asset").getJSONObject("names").getString("full");
        double price = item.getJSONObject("pricing").getDouble("computed");
        return new CsMoneyItem(name, price);
    }
}
