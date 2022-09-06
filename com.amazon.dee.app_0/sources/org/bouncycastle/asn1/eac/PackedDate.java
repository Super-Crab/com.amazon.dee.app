package org.bouncycastle.asn1.eac;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
public class PackedDate {
    private byte[] time;

    public PackedDate(String str) {
        this.time = convert(str);
    }

    public PackedDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd'Z'");
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        this.time = convert(simpleDateFormat.format(date));
    }

    public PackedDate(Date date, Locale locale) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd'Z'", locale);
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        this.time = convert(simpleDateFormat.format(date));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PackedDate(byte[] bArr) {
        this.time = bArr;
    }

    private byte[] convert(String str) {
        char[] charArray = str.toCharArray();
        byte[] bArr = new byte[6];
        for (int i = 0; i != 6; i++) {
            bArr[i] = (byte) (charArray[i] - '0');
        }
        return bArr;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PackedDate)) {
            return false;
        }
        return Arrays.areEqual(this.time, ((PackedDate) obj).time);
    }

    public Date getDate() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("20");
        outline107.append(toString());
        return simpleDateFormat.parse(outline107.toString());
    }

    public byte[] getEncoding() {
        return Arrays.clone(this.time);
    }

    public int hashCode() {
        return Arrays.hashCode(this.time);
    }

    public String toString() {
        char[] cArr = new char[this.time.length];
        for (int i = 0; i != cArr.length; i++) {
            cArr[i] = (char) ((this.time[i] & 255) + 48);
        }
        return new String(cArr);
    }
}
