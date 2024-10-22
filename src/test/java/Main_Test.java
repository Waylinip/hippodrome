import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

public class Main_Test {
    @Disabled()
    @Test
    @Timeout(value = 22)
    void mainTimeoutTest() throws Exception {
        Main.main(null);
    }
}
