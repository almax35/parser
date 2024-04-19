package alekseew.services.csmarket;


import alekseew.entity.CsMarketItem;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;


public class TestCsMarketJsonParser {
    @Test
    void whenCsMarketJsonParserGetRightResponseThenReturnCsMarketItem(){
        try(BufferedReader reader=new BufferedReader(new FileReader("C:\\Users\\Spectra\\IdeaProjects\\TableParser\\src\\test\\resources\\csMarketJsonRightResponseWithOneItem.json"))) {
            CsMarketJsonParser csMarketJsonParser=new CsMarketJsonParser();
            CsMarketItem item=csMarketJsonParser.parseResponseToItem(reader.readLine());
            System.out.println(item);
            CsMarketItem realItem=new CsMarketItem("AK-47 | Elite Build (Well-Worn))",66.74);
            assertAll(
                    () -> assertEquals(item.getName(), realItem.getName()),
                    () -> assertEquals(item.getPrice(),realItem.getPrice()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void whenCsMarketJsonParserGetBadResponse(){
        try(BufferedReader reader=new BufferedReader(new FileReader("C:\\Users\\Spectra\\IdeaProjects\\TableParser\\src\\test\\resources\\csMarketJsonBadResponse.json"))) {
            CsMarketJsonParser csMarketJsonParser=new CsMarketJsonParser();
            CsMarketItem item=csMarketJsonParser.parseResponseToItem(reader.readLine());
            assertNull(item);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
