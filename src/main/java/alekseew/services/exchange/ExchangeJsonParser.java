package alekseew.services.exchange;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class ExchangeJsonParser {
    public double getUsdToRub(String body) {
        JSONObject object = new JSONObject(body);
        return object.getJSONObject("Valute").getJSONObject("USD").getDouble("Value");
    }

    public double getCnyToRub(String body) {
        JSONObject object = new JSONObject(body);
        return object.getJSONObject("Valute").getJSONObject("CNY").getDouble("Value");
    }
}
