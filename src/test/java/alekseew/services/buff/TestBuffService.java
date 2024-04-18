package alekseew.services.buff;

import alekseew.entity.BuffItem;
import alekseew.entity.ItemCategory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;

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
        double maxPrice=100;

    }

}
