package services.csmoney;

import entity.BuffItem;
import entity.CsMoneyItem;
import org.json.JSONArray;
import org.json.JSONObject;
import services.IJsonParser;

import java.util.ArrayList;
import java.util.List;

public class CsMoneyJsonParser implements IJsonParser {
    public  CsMoneyItem parseJsonCsMoney(JSONObject object) {
        String name= object.getJSONObject("asset").getJSONObject("names").getString("full");
        double price=object.getJSONObject("pricing").getDouble("computed");
        return new CsMoneyItem(name, price);
    }

    public List<CsMoneyItem> parseResponseToList(String body){
        ArrayList<CsMoneyItem> list=new ArrayList<>();
        JSONObject object=new JSONObject(body);
        JSONArray items =object.getJSONArray("items");
        for (int i=0; i<items.length(); i++){
            JSONObject item=items.getJSONObject(i);
            list.add(parseJsonCsMoney(item));
        }
        return list;

    }
}
