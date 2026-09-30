package shop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PaymentClientTest {

    /** Expects the payment gateway sandbox to be running on port 8089. */
    private final PaymentClient client = new PaymentClient("http://localhost:8089");

    @Test
    void chargeIsAccepted() throws Exception {
        assertEquals(201, client.charge(1001, "29.99"));
    }
}
