package org.apache.commons.net.ftp.parser;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import org.apache.commons.net.ftp.Configurable;
import org.apache.commons.net.ftp.FTPClientConfig;
/* loaded from: classes4.dex */
public class FTPTimestampParserImpl implements FTPTimestampParser, Configurable {
    private SimpleDateFormat defaultDateFormat;
    private SimpleDateFormat recentDateFormat;

    public FTPTimestampParserImpl() {
        setDefaultDateFormat(FTPTimestampParser.DEFAULT_SDF);
        setRecentDateFormat(FTPTimestampParser.DEFAULT_RECENT_SDF);
    }

    private void setDefaultDateFormat(String str) {
        if (str != null) {
            this.defaultDateFormat = new SimpleDateFormat(str);
            this.defaultDateFormat.setLenient(false);
        }
    }

    private void setRecentDateFormat(String str) {
        if (str != null) {
            this.recentDateFormat = new SimpleDateFormat(str);
            this.recentDateFormat.setLenient(false);
        }
    }

    private void setServerTimeZone(String str) {
        TimeZone timeZone = TimeZone.getDefault();
        if (str != null) {
            timeZone = TimeZone.getTimeZone(str);
        }
        this.defaultDateFormat.setTimeZone(timeZone);
        SimpleDateFormat simpleDateFormat = this.recentDateFormat;
        if (simpleDateFormat != null) {
            simpleDateFormat.setTimeZone(timeZone);
        }
    }

    @Override // org.apache.commons.net.ftp.Configurable
    public void configure(FTPClientConfig fTPClientConfig) {
        DateFormatSymbols lookupDateFormatSymbols;
        String serverLanguageCode = fTPClientConfig.getServerLanguageCode();
        String shortMonthNames = fTPClientConfig.getShortMonthNames();
        if (shortMonthNames != null) {
            lookupDateFormatSymbols = FTPClientConfig.getDateFormatSymbols(shortMonthNames);
        } else if (serverLanguageCode != null) {
            lookupDateFormatSymbols = FTPClientConfig.lookupDateFormatSymbols(serverLanguageCode);
        } else {
            lookupDateFormatSymbols = FTPClientConfig.lookupDateFormatSymbols("en");
        }
        String recentDateFormatStr = fTPClientConfig.getRecentDateFormatStr();
        if (recentDateFormatStr == null) {
            this.recentDateFormat = null;
        } else {
            this.recentDateFormat = new SimpleDateFormat(recentDateFormatStr, lookupDateFormatSymbols);
            this.recentDateFormat.setLenient(false);
        }
        String defaultDateFormatStr = fTPClientConfig.getDefaultDateFormatStr();
        if (defaultDateFormatStr != null) {
            this.defaultDateFormat = new SimpleDateFormat(defaultDateFormatStr, lookupDateFormatSymbols);
            this.defaultDateFormat.setLenient(false);
            setServerTimeZone(fTPClientConfig.getServerTimeZoneId());
            return;
        }
        throw new IllegalArgumentException("defaultFormatString cannot be null");
    }

    public SimpleDateFormat getDefaultDateFormat() {
        return this.defaultDateFormat;
    }

    public String getDefaultDateFormatString() {
        return this.defaultDateFormat.toPattern();
    }

    public SimpleDateFormat getRecentDateFormat() {
        return this.recentDateFormat;
    }

    public String getRecentDateFormatString() {
        return this.recentDateFormat.toPattern();
    }

    public TimeZone getServerTimeZone() {
        return this.defaultDateFormat.getTimeZone();
    }

    public String[] getShortMonths() {
        return this.defaultDateFormat.getDateFormatSymbols().getShortMonths();
    }

    @Override // org.apache.commons.net.ftp.parser.FTPTimestampParser
    public Calendar parseTimestamp(String str) throws ParseException {
        Date date;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(getServerTimeZone());
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeZone(getServerTimeZone());
        ParsePosition parsePosition = new ParsePosition(0);
        if (this.recentDateFormat != null) {
            String format = new SimpleDateFormat("yyyy").format(calendar.getTime());
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(str);
            stringBuffer.append(" ");
            stringBuffer.append(format);
            String stringBuffer2 = stringBuffer.toString();
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append(this.recentDateFormat.toPattern());
            stringBuffer3.append(" yyyy");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(stringBuffer3.toString(), this.recentDateFormat.getDateFormatSymbols());
            simpleDateFormat.setLenient(false);
            simpleDateFormat.setTimeZone(this.recentDateFormat.getTimeZone());
            date = simpleDateFormat.parse(stringBuffer2, parsePosition);
        } else {
            date = null;
        }
        if (date != null && parsePosition.getIndex() == str.length() + 5) {
            calendar2.setTime(date);
            if (calendar2.after(calendar)) {
                calendar2.add(1, -1);
            }
        } else {
            ParsePosition parsePosition2 = new ParsePosition(0);
            Date parse = this.defaultDateFormat.parse(str, parsePosition2);
            if (parse != null && parsePosition2.getIndex() == str.length()) {
                calendar2.setTime(parse);
            } else {
                throw new ParseException("Timestamp could not be parsed with older or recent DateFormat", parsePosition2.getIndex());
            }
        }
        return calendar2;
    }
}
