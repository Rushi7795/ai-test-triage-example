package shop;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/** Formats prices for display, for example "Rs 1,499.00". */
public final class PriceFormatter {

    private PriceFormatter() {}

    public static String format(BigDecimal amount) {
        DecimalFormat df = new DecimalFormat("#,##0.00", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        return "Rs " + df.format(amount.setScale(2, RoundingMode.HALF_UP));
    }
}
