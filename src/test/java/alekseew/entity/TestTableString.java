package alekseew.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestTableString {

    @Test
    void whenFindMarketsWithMaxPercentCalledThenReturnResale() {
        TableString tableString = new TableString("name", 1, 2, 3, 4, "image", "href");
        Resale resale = tableString.findMarketsWithMaxPercent();
        assertAll(() -> assertEquals(resale.getFirstMarket(), "Buff"),
                () -> assertEquals(resale.getSecondMarket(), "CsMoney"),
                () -> assertEquals(resale.getName(), "name"),
                () -> assertEquals(resale.getPercent(), 300));
    }

}
