package alekseew.services.csmarket;

import alekseew.entity.BuffItem;
import alekseew.entity.CsMarketItem;
import alekseew.entity.ItemCategory;
import alekseew.services.buff.BuffJsonParser;
import alekseew.services.buff.BuffService;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TestCsMarketService {
    @Test
    void whenBuffServiceSearchWithRightName() throws IOException, InterruptedException {
        String name="UMP-45 | Exposure (Factory New)";
        CsMarketService csMarketService=new CsMarketService(new CsMarketJsonParser());
        CsMarketItem item=csMarketService.searchByName(name);
        assertAll(() -> assertTrue(item.getPrice()>0),
                () -> assertEquals(item.getName(),name));
    }

    @Test
    void whenBuffServiceSearchWithWrongName() throws IOException, InterruptedException {
        String name="Wrong name";
        CsMarketService csMarketService=new CsMarketService(new CsMarketJsonParser());
        CsMarketItem item=csMarketService.searchByName(name);
        assertEquals(item.getPrice(),0.0);
    }
}
