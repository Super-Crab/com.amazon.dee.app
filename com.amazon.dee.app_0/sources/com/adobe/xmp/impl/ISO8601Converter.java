package com.adobe.xmp.impl;

import com.adobe.xmp.XMPDateTime;
import com.adobe.xmp.XMPException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;
/* loaded from: classes.dex */
public final class ISO8601Converter {
    private ISO8601Converter() {
    }

    public static XMPDateTime parse(String str) throws XMPException {
        return parse(str, new XMPDateTimeImpl());
    }

    /* JADX WARN: Removed duplicated region for block: B:136:0x0219 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:137:0x021a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.adobe.xmp.XMPDateTime parse(java.lang.String r11, com.adobe.xmp.XMPDateTime r12) throws com.adobe.xmp.XMPException {
        /*
            Method dump skipped, instructions count: 555
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.xmp.impl.ISO8601Converter.parse(java.lang.String, com.adobe.xmp.XMPDateTime):com.adobe.xmp.XMPDateTime");
    }

    public static String render(XMPDateTime xMPDateTime) {
        StringBuffer stringBuffer = new StringBuffer();
        if (xMPDateTime.hasDate()) {
            DecimalFormat decimalFormat = new DecimalFormat("0000", new DecimalFormatSymbols(Locale.ENGLISH));
            stringBuffer.append(decimalFormat.format(xMPDateTime.getYear()));
            if (xMPDateTime.getMonth() == 0) {
                return stringBuffer.toString();
            }
            decimalFormat.applyPattern("'-'00");
            stringBuffer.append(decimalFormat.format(xMPDateTime.getMonth()));
            if (xMPDateTime.getDay() == 0) {
                return stringBuffer.toString();
            }
            stringBuffer.append(decimalFormat.format(xMPDateTime.getDay()));
            if (xMPDateTime.hasTime()) {
                stringBuffer.append('T');
                decimalFormat.applyPattern("00");
                stringBuffer.append(decimalFormat.format(xMPDateTime.getHour()));
                stringBuffer.append(JsonReaderKt.COLON);
                stringBuffer.append(decimalFormat.format(xMPDateTime.getMinute()));
                if (xMPDateTime.getSecond() != 0 || xMPDateTime.getNanoSecond() != 0) {
                    double nanoSecond = xMPDateTime.getNanoSecond() / 1.0E9d;
                    decimalFormat.applyPattern(":00.#########");
                    stringBuffer.append(decimalFormat.format(nanoSecond + xMPDateTime.getSecond()));
                }
                if (xMPDateTime.hasTimeZone()) {
                    int offset = xMPDateTime.getTimeZone().getOffset(xMPDateTime.getCalendar().getTimeInMillis());
                    if (offset == 0) {
                        stringBuffer.append(Matrix.MATRIX_TYPE_ZERO);
                    } else {
                        int i = offset / 3600000;
                        int abs = Math.abs((offset % 3600000) / 60000);
                        decimalFormat.applyPattern("+00;-00");
                        stringBuffer.append(decimalFormat.format(i));
                        decimalFormat.applyPattern(":00");
                        stringBuffer.append(decimalFormat.format(abs));
                    }
                }
            }
        }
        return stringBuffer.toString();
    }
}
