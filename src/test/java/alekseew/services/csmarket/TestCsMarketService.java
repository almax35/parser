package alekseew.services.csmarket;

import alekseew.entity.CsMarketItem;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class TestCsMarketService {
    @Test
    void whenBuffServiceSearchWithRightNameThenReturnCsMarketItem() throws IOException, InterruptedException {
        String name="UMP-45 | Exposure (Factory New)";
        CsMarketService csMarketService=new CsMarketService(new CsMarketJsonParser());
        CsMarketItem item=csMarketService.searchByName(name);
        assertAll(() -> assertTrue(item.getPrice()>0),
                () -> assertEquals(item.getName(),name));
    }

    @Test
    void whenBuffServiceSearchWithWrongNameThenReturnNull() throws IOException, InterruptedException {
        String name="Wrong name";
        CsMarketService csMarketService=new CsMarketService(new CsMarketJsonParser());
        CsMarketItem item=csMarketService.searchByName(name);
        assertEquals(item.getPrice(),0.0);
    }
}
