package br.com.felix.horizontalbargraph.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by user on 16/01/2018.
 */

public class Util {
    public static String formatMoney(Double value) {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault());

        BigDecimal bd = new BigDecimal(value);
        bd = bd.round(new MathContext(2, RoundingMode.HALF_UP));

        return numberFormat.format(bd.doubleValue());
    }
}
