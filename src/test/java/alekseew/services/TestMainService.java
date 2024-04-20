package alekseew.services;

import alekseew.entity.ItemCategory;
import alekseew.entity.Resale;
import alekseew.entity.TableString;
import alekseew.services.buff.BuffJsonParser;
import alekseew.services.buff.BuffService;
import alekseew.services.csmarket.CsMarketJsonParser;
import alekseew.services.csmarket.CsMarketService;
import alekseew.services.csmoney.CsMoneyJsonParser;
import alekseew.services.csmoney.CsMoneyService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestMainService {
    @Test
    void whenMainServiceSearchByNameThenReturnTableString() throws IOException, InterruptedException {
        MainService service = new MainService(new BuffService(new BuffJsonParser(), new ItemCategory()), new CsMoneyService(new CsMoneyJsonParser()), new CsMarketService(new CsMarketJsonParser()));
        String name = "AK-47 | Elite Build (Field-Tested)";
        List<TableString> strings = service.searchWithName(name);
        assertAll(() -> assertEquals(strings.get(0).getName(), name),
                () -> assertTrue(strings.get(0).getSteamPrice() > 0),
                () -> assertTrue(strings.get(0).getBuffPrice() > 0),
                () -> assertTrue(strings.get(0).getCsMoneyPrice() > 0),
                () -> assertTrue(strings.get(0).getCsMarketPrice() > 0),
                () -> assertNotNull(strings.get(0).getSteamHref()),
                () -> assertNotNull(strings.get(0).getImageHref()));
    }

    @Test
    void whenMainServiceSearchByBadNameThenReturnNull() throws IOException, InterruptedException {
        MainService service = new MainService(new BuffService(new BuffJsonParser(), new ItemCategory()), new CsMoneyService(new CsMoneyJsonParser()), new CsMarketService(new CsMarketJsonParser()));
        String name = "Bad name";
        assertNull(service.searchWithName(name));
    }

    @Test
    void whenMainServiceSearchWithRightParamsThenReturnList() throws IOException, InterruptedException {
        MainService service = new MainService(new BuffService(new BuffJsonParser(), new ItemCategory()), new CsMoneyService(new CsMoneyJsonParser()), new CsMarketService(new CsMarketJsonParser()));
        double minPrice = 100;
        double maxPrice = 1000;
        int size = 10;
        String type = "weapon_ak47";
        List<TableString> items = service.searchWithParams(minPrice, maxPrice, size, type);
        assertEquals(items.size(), size);
        for (TableString item : items) {
            assertAll(() -> assertTrue(item.getBuffPrice() >= 100 && item.getBuffPrice() <= 1000),
                    () -> assertTrue(item.getSteamPrice() >= 100),
                    () -> assertNotNull(item.getSteamHref()),
                    () -> assertNotNull(item.getImageHref()),
                    () -> assertTrue(item.getCsMoneyPrice() > 50),
                    () -> assertTrue(item.getCsMarketPrice() > 50),
                    () -> assertTrue(item.getName().startsWith("AK-47")));
        }
    }

    @Test
    void whenMainServiceSearchWithBadParamsThenReturnNull() throws IOException, InterruptedException {
        double minPrice = 1000;
        double maxPrice = 100;
        MainService service = new MainService(new BuffService(new BuffJsonParser(), new ItemCategory()), new CsMoneyService(new CsMoneyJsonParser()), new CsMarketService(new CsMarketJsonParser()));
        List<TableString> items = service.searchWithParams(minPrice, maxPrice, 3, "bad type");
        assertNull(items);
    }

    @Test
    void whenMainServiceSortTableThenReturnSortedTable() throws IOException, InterruptedException {
        MainService service = new MainService(new BuffService(new BuffJsonParser(), new ItemCategory()), new CsMoneyService(new CsMoneyJsonParser()), new CsMarketService(new CsMarketJsonParser()));
        double minPrice = 100;
        double maxPrice = 1000;
        int size = 10;
        String type = "weapon_ak47";
        service.searchWithParams(minPrice, maxPrice, size, type);
        service.sortTable("Buff");
        List<Double> buffPriceList = service.getStrings().stream()
                .map(TableString::getBuffPrice)
                .collect(Collectors.toList());
        assertTrue(isListSorted(buffPriceList));
        service.sortTable("CsMoney");
        List<Double> csMoneyList = service.getStrings().stream()
                .map(TableString::getCsMoneyPrice)
                .collect(Collectors.toList());
        assertTrue(isListSorted(csMoneyList));
        service.sortTable("Steam");
        List<Double> steamList = service.getStrings().stream()
                .map(TableString::getSteamPrice)
                .collect(Collectors.toList());
        assertTrue(isListSorted(steamList));
        service.sortTable("CsMarket");
        List<Double> csMarketList = service.getStrings().stream()
                .map(TableString::getCsMarketPrice)
                .collect(Collectors.toList());
        assertTrue(isListSorted(csMarketList));
    }

    @Test
    void whenMainServiceCalledFindMaxPercentageAtAllThenReturnBestResale() throws IOException, InterruptedException {
        MainService service = new MainService(new BuffService(new BuffJsonParser(), new ItemCategory()), new CsMoneyService(new CsMoneyJsonParser()), new CsMarketService(new CsMarketJsonParser()));
        TableString tableString1 = new TableString("name1", 1, 2, 3, 4, "image1", "href1");
        TableString tableString2 = new TableString("name2", 10, 20, 100, 40, "image2", "href2");
        List<TableString> tableStrings = new ArrayList<>();
        tableStrings.add(tableString1);
        tableStrings.add(tableString2);
        service.setStrings(tableStrings);
        Resale resale = service.findMaxPercentageAtAll();
        assertAll(() -> assertEquals(resale.getName(), "name2"),
                () -> assertEquals(resale.getPercent(), 900),
                () -> assertEquals(resale.getFirstMarket(), "Buff"),
                () -> assertEquals(resale.getSecondMarket(), "CsMarket"));
    }

    private boolean isListSorted(List<Double> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) > list.get(i + 1)) {
                return false;
            }
        }
        return true;
    }
}
