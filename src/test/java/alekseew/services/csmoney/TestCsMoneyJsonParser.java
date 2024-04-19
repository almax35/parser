package alekseew.services.csmoney;

import alekseew.entity.CsMoneyItem;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class TestCsMoneyJsonParser {
    @Test
    void whenCsMarketJsonParserGetRightResponseThenReturnCsMarketItem(){
        try(BufferedReader reader=new BufferedReader(new FileReader("C:\\Users\\Spectra\\IdeaProjects\\TableParser\\src\\test\\resources\\csMoneyJsonRightResponseWithOneItem.json"))) {
            CsMoneyJsonParser csMoneyJsonParser=new CsMoneyJsonParser();
            CsMoneyItem item=csMoneyJsonParser.parseResponseToItem(reader.readLine());
            CsMoneyItem realItem=new CsMoneyItem("AK-47 | Uncharted (Minimal Wear)",0.36);
            assertAll(
                    () -> assertEquals(item.getName(), realItem.getName()),
                    () -> assertEquals(item.getPrice(),realItem.getPrice()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void whenCsMarketJsonParserGetBadResponse(){
        try(BufferedReader reader=new BufferedReader(new FileReader("C:\\Users\\Spectra\\IdeaProjects\\TableParser\\src\\test\\resources\\csMoneyJsonBadResponse.json"))) {
            CsMoneyJsonParser csMoneyJsonParser=new CsMoneyJsonParser();
            CsMoneyItem item=csMoneyJsonParser.parseResponseToItem(reader.readLine());
            assertNull(item);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
