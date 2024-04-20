package alekseew.services.csmoney;

import alekseew.entity.CsMoneyItem;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCsMoneyService {
    @Test
    void whenBuffServiceSearchWithRightNameThenReturnCsMoneyItem() throws IOException, InterruptedException {
        String name = "UMP-45 | Exposure (Factory New)";
        CsMoneyService csMoneyService = new CsMoneyService(new CsMoneyJsonParser());
        CsMoneyItem item = csMoneyService.searchByName(name);
        assertAll(() -> assertTrue(item.getPrice() > 0),
                () -> assertEquals(item.getName(), name));
    }

    @Test
    void whenBuffServiceSearchWithWrongNameThenReturnNull() throws IOException, InterruptedException {
        String name = "Wrong name";
        CsMoneyService csMarketService = new CsMoneyService(new CsMoneyJsonParser());
        CsMoneyItem item = csMarketService.searchByName(name);
        assertEquals(item.getPrice(), 0.0);
    }
}
