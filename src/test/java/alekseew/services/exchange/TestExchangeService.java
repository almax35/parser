package alekseew.services.exchange;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class TestExchangeService {
    @Test
    void whenExchangeServiceCalledThenReturnWithListOfValute() throws IOException, InterruptedException {
        ExchangeService exchangeService=new ExchangeService(new ExchangeJsonParser());
        List<Double> courses = exchangeService.saveValuteCourse();
        assertEquals(courses.size(),2);
        for (Double course: courses) {
            assertAll(() -> assertTrue(course>0));
        }
    }
}
