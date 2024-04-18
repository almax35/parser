package alekseew.services.buff;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

public class TestBuffJsonParser {
    @Test
    public void shouldWork() throws Exception {
        String json = IOUtils.toString(
                this.getClass().getResourceAsStream("abc.xml"),
                StandardCharsets.UTF_8
        );
    }
}
