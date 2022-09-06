package com.sun.mail.imap.protocol;

import com.sun.mail.iap.ParsingException;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import javax.mail.internet.MailDateFormat;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;
/* loaded from: classes3.dex */
public class INTERNALDATE implements Item {
    protected Date date;
    public int msgno;
    static final char[] name = {'I', 'N', 'T', 'E', Matrix.MATRIX_TYPE_RANDOM_REGULAR, 'N', 'A', Matrix.MATRIX_TYPE_RANDOM_LT, 'D', 'A', 'T', 'E'};
    private static MailDateFormat mailDateFormat = new MailDateFormat();
    private static SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss ", Locale.US);

    public INTERNALDATE(FetchResponse fetchResponse) throws ParsingException {
        this.msgno = fetchResponse.getNumber();
        fetchResponse.skipSpaces();
        String readString = fetchResponse.readString();
        if (readString != null) {
            try {
                this.date = mailDateFormat.parse(readString);
                return;
            } catch (ParseException unused) {
                throw new ParsingException("INTERNALDATE parse error");
            }
        }
        throw new ParsingException("INTERNALDATE is NIL");
    }

    public static String format(Date date) {
        StringBuffer stringBuffer = new StringBuffer();
        synchronized (df) {
            df.format(date, stringBuffer, new FieldPosition(0));
        }
        int offset = (TimeZone.getDefault().getOffset(date.getTime()) / 60) / 1000;
        if (offset < 0) {
            stringBuffer.append('-');
            offset = -offset;
        } else {
            stringBuffer.append('+');
        }
        int i = offset / 60;
        int i2 = offset % 60;
        stringBuffer.append(Character.forDigit(i / 10, 10));
        stringBuffer.append(Character.forDigit(i % 10, 10));
        stringBuffer.append(Character.forDigit(i2 / 10, 10));
        stringBuffer.append(Character.forDigit(i2 % 10, 10));
        return stringBuffer.toString();
    }

    public Date getDate() {
        return this.date;
    }
}
