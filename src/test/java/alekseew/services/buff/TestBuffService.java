package alekseew.services.buff;

import alekseew.entity.BuffItem;
import alekseew.entity.ItemCategory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.util.List;

public class TestBuffService {
    @Test
    void whenBuffServiceSearchWithRightName() throws IOException, InterruptedException {
        String name="UMP-45 | Exposure (Factory New)";
        BuffService buffService=new BuffService(new BuffJsonParser(),new ItemCategory());
        BuffItem item=buffService.searchByName(name);
        assertAll(() -> assertTrue(item.getBuffPrice()>0),
                () -> assertTrue(item.getSteamPrice()>0),
                () -> assertEquals(item.getName(),name),
                () -> assertNotNull(item.getSteamHref()),
                () -> assertNotNull(item.getImageHref()));
    }

    @Test
    void whenBuffServiceSearchWithWrongName() throws IOException, InterruptedException {
        String name="Wrong name";
        BuffService buffService=new BuffService(new BuffJsonParser(),new ItemCategory());
        BuffItem item=buffService.searchByName(name);
        assertNull(item);
    }

    @Test
    void whenBuffServiceSearchWithRightParams() throws IOException, InterruptedException{
        double minPrice=100;
        double maxPrice=1000;
        BuffService buffService=new BuffService(new BuffJsonParser(),new ItemCategory());
        int size=10;
        String type="weapon_ak47";
        List<BuffItem> items=buffService.searchWithParams(minPrice,maxPrice,size,type);
        assertEquals(items.size(),size);
        for (BuffItem item: items) {
            assertAll(() -> assertTrue(item.getBuffPrice()>=100 && item.getBuffPrice()<=1000),
                    () -> assertTrue(item.getSteamPrice() >=100),
                    () -> assertNotNull(item.getSteamHref()),
                    () -> assertNotNull(item.getImageHref()));
        }
    }
    @Test
    void whenBuffServiceSearchWithBadParams() throws IOException, InterruptedException{
        double minPrice=1000;
        double maxPrice=100;
        BuffService buffService=new BuffService(new BuffJsonParser(),new ItemCategory());
        List<BuffItem> items=buffService.searchWithParams(minPrice,maxPrice,3,"bad type");
        assertNull(items);
    }
}
