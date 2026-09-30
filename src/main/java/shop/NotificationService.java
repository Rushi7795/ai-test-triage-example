package shop;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/** Sends order confirmation emails in the background. */
public class NotificationService {

    /** The email provider usually answers in about 300 ms. */
    public CompletableFuture<String> sendConfirmation(int orderId) {
        return CompletableFuture.supplyAsync(() -> "sent:" + orderId,
                CompletableFuture.delayedExecutor(300, TimeUnit.MILLISECONDS));
    }
}
