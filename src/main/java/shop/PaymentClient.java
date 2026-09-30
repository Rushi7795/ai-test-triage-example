package shop;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/** Talks to the payment gateway over HTTP. */
public class PaymentClient {

    private final String baseUrl;
    private final HttpClient http = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(2))
            .build();

    public PaymentClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    /** Returns the gateway's HTTP status code for the charge. */
    public int charge(int orderId, String amount) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder(URI.create(baseUrl + "/charge"))
                .timeout(Duration.ofSeconds(5))
                .header("content-type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(
                        "{\"orderId\":" + orderId + ",\"amount\":\"" + amount + "\"}"))
                .build();
        return http.send(request, HttpResponse.BodyHandlers.discarding()).statusCode();
    }
}
