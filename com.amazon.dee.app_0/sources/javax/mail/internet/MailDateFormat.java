package javax.mail.internet;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.sun.mail.util.MailLogger;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.logging.Level;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes3.dex */
public class MailDateFormat extends SimpleDateFormat {
    private static final long serialVersionUID = -8148227605210628779L;
    static boolean debug = false;
    private static MailLogger logger = new MailLogger(MailDateFormat.class, "DEBUG", debug, System.out);
    private static final Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("GMT"));

    public MailDateFormat() {
        super("EEE, d MMM yyyy HH:mm:ss 'XXXXX' (z)", Locale.US);
    }

    private static synchronized Date ourUTC(int i, int i2, int i3, int i4, int i5, int i6, int i7, boolean z) {
        Date time;
        synchronized (MailDateFormat.class) {
            cal.clear();
            cal.setLenient(z);
            cal.set(1, i);
            cal.set(2, i2);
            cal.set(5, i3);
            cal.set(11, i4);
            cal.set(12, i5);
            cal.add(12, i7);
            cal.set(13, i6);
            time = cal.getTime();
        }
        return time;
    }

    private static Date parseDate(char[] cArr, ParsePosition parsePosition, boolean z) {
        int i;
        try {
            MailDateParser mailDateParser = new MailDateParser(cArr, parsePosition.getIndex());
            mailDateParser.skipUntilNumber();
            int parseNumber = mailDateParser.parseNumber();
            if (!mailDateParser.skipIfChar('-')) {
                mailDateParser.skipWhiteSpace();
            }
            int parseMonth = mailDateParser.parseMonth();
            if (!mailDateParser.skipIfChar('-')) {
                mailDateParser.skipWhiteSpace();
            }
            int parseNumber2 = mailDateParser.parseNumber();
            if (parseNumber2 < 50) {
                parseNumber2 += 2000;
            } else if (parseNumber2 < 100) {
                parseNumber2 += 1900;
            }
            int i2 = parseNumber2;
            mailDateParser.skipWhiteSpace();
            int parseNumber3 = mailDateParser.parseNumber();
            mailDateParser.skipChar(JsonReaderKt.COLON);
            int parseNumber4 = mailDateParser.parseNumber();
            int parseNumber5 = mailDateParser.skipIfChar(JsonReaderKt.COLON) ? mailDateParser.parseNumber() : 0;
            try {
                mailDateParser.skipWhiteSpace();
                i = mailDateParser.parseTimeZone();
            } catch (java.text.ParseException e) {
                if (logger.isLoggable(Level.FINE)) {
                    logger.log(Level.FINE, "No timezone? : '" + new String(cArr) + "'", (Throwable) e);
                }
                i = 0;
            }
            parsePosition.setIndex(mailDateParser.getIndex());
            return ourUTC(i2, parseMonth, parseNumber, parseNumber3, parseNumber4, parseNumber5, i, z);
        } catch (Exception e2) {
            if (logger.isLoggable(Level.FINE)) {
                MailLogger mailLogger = logger;
                Level level = Level.FINE;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Bad date: '");
                outline107.append(new String(cArr));
                outline107.append("'");
                mailLogger.log(level, outline107.toString(), (Throwable) e2);
            }
            parsePosition.setIndex(1);
            return null;
        }
    }

    @Override // java.text.SimpleDateFormat, java.text.DateFormat
    public StringBuffer format(Date date, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        int i;
        int length = stringBuffer.length();
        super.format(date, stringBuffer, fieldPosition);
        int i2 = length + 25;
        while (stringBuffer.charAt(i2) != 'X') {
            i2++;
        }
        ((SimpleDateFormat) this).calendar.clear();
        ((SimpleDateFormat) this).calendar.setTime(date);
        int i3 = ((SimpleDateFormat) this).calendar.get(16) + ((SimpleDateFormat) this).calendar.get(15);
        if (i3 < 0) {
            i = i2 + 1;
            stringBuffer.setCharAt(i2, '-');
            i3 = -i3;
        } else {
            i = i2 + 1;
            stringBuffer.setCharAt(i2, '+');
        }
        int i4 = (i3 / 60) / 1000;
        int i5 = i4 / 60;
        int i6 = i4 % 60;
        int i7 = i + 1;
        stringBuffer.setCharAt(i, Character.forDigit(i5 / 10, 10));
        int i8 = i7 + 1;
        stringBuffer.setCharAt(i7, Character.forDigit(i5 % 10, 10));
        stringBuffer.setCharAt(i8, Character.forDigit(i6 / 10, 10));
        stringBuffer.setCharAt(i8 + 1, Character.forDigit(i6 % 10, 10));
        return stringBuffer;
    }

    @Override // java.text.SimpleDateFormat, java.text.DateFormat
    public Date parse(String str, ParsePosition parsePosition) {
        return parseDate(str.toCharArray(), parsePosition, isLenient());
    }

    @Override // java.text.DateFormat
    public void setCalendar(Calendar calendar) {
        throw new RuntimeException("Method setCalendar() shouldn't be called");
    }

    @Override // java.text.DateFormat
    public void setNumberFormat(NumberFormat numberFormat) {
        throw new RuntimeException("Method setNumberFormat() shouldn't be called");
    }
}
