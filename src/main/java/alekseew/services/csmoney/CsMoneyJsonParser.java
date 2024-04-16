package alekseew.services.csmoney;

import alekseew.entity.CsMoneyItem;
import org.json.JSONArray;
import org.json.JSONObject;
import alekseew.services.IJsonParser;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class CsMoneyJsonParser implements IJsonParser {
    public  CsMoneyItem parseJsonCsMoney(JSONObject object) {
        String name= object.getJSONObject("asset").getJSONObject("names").getString("full");
        double price=object.getJSONObject("pricing").getDouble("computed");
        return new CsMoneyItem(name, price);
    }

    public List<CsMoneyItem> parseResponseToList(String body){
        ArrayList<CsMoneyItem> list=new ArrayList<>();
        JSONObject object=new JSONObject(body);
        if (object.keySet().contains("errors")){
            return list;
        }
        JSONArray items =object.getJSONArray("items");
        for (int i=0; i<items.length(); i++){
            JSONObject item=items.getJSONObject(i);
            list.add(parseJsonCsMoney(item));
        }
        return list;
    }
}
