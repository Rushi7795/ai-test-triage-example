package shop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;

class NotificationServiceTest {

    @Test
    void confirmationIsSent() throws Exception {
        String result = new NotificationService()
                .sendConfirmation(1001)
                .get(100, TimeUnit.MILLISECONDS);
        assertEquals("sent:1001", result);
    }
}
