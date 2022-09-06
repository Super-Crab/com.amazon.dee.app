package com.amazon.org.codehaus.jackson.map.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
/* loaded from: classes13.dex */
public class ISO8601DateFormat extends DateFormat {
    private static Calendar CALENDAR = new GregorianCalendar();
    private static NumberFormat NUMBER_FORMAT = new DecimalFormat();
    private static final long serialVersionUID = 1;

    public ISO8601DateFormat() {
        ((DateFormat) this).numberFormat = NUMBER_FORMAT;
        ((DateFormat) this).calendar = CALENDAR;
    }

    @Override // java.text.DateFormat, java.text.Format
    public Object clone() {
        return this;
    }

    @Override // java.text.DateFormat
    public StringBuffer format(Date date, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        stringBuffer.append(ISO8601Utils.format(date));
        return stringBuffer;
    }

    @Override // java.text.DateFormat
    public Date parse(String str, ParsePosition parsePosition) {
        parsePosition.setIndex(str.length());
        return ISO8601Utils.parse(str);
    }
}
