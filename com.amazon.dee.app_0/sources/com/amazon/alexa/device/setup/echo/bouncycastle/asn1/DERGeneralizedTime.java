package com.amazon.alexa.device.setup.echo.bouncycastle.asn1;

import com.amazon.alexa.device.setup.echo.bouncycastle.util.Arrays;
import com.amazon.alexa.device.setup.echo.bouncycastle.util.Strings;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import org.apache.logging.log4j.util.ProcessIdUtil;
/* loaded from: classes.dex */
public class DERGeneralizedTime extends ASN1Primitive {
    private final byte[] time;

    public DERGeneralizedTime(String str) {
        this.time = Strings.toByteArray(str);
        try {
            getDate();
        } catch (ParseException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("invalid date string: ");
            outline107.append(e.getMessage());
            throw new IllegalArgumentException(outline107.toString());
        }
    }

    private String calculateGMTOffset() {
        String str;
        TimeZone timeZone = TimeZone.getDefault();
        int rawOffset = timeZone.getRawOffset();
        if (rawOffset < 0) {
            rawOffset = -rawOffset;
            str = ProcessIdUtil.DEFAULT_PROCESSID;
        } else {
            str = "+";
        }
        int i = rawOffset / 3600000;
        int i2 = (rawOffset - (((i * 60) * 60) * 1000)) / 60000;
        try {
            if (timeZone.useDaylightTime() && timeZone.inDaylightTime(getDate())) {
                i += str.equals("+") ? 1 : -1;
            }
        } catch (ParseException unused) {
        }
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113("GMT", str);
        outline113.append(convert(i));
        outline113.append(":");
        outline113.append(convert(i2));
        return outline113.toString();
    }

    private String convert(int i) {
        if (i < 10) {
            return GeneratedOutlineSupport1.outline49("0", i);
        }
        return Integer.toString(i);
    }

    public static ASN1GeneralizedTime getInstance(Object obj) {
        if (obj != null && !(obj instanceof ASN1GeneralizedTime)) {
            if (obj instanceof DERGeneralizedTime) {
                return new ASN1GeneralizedTime(((DERGeneralizedTime) obj).time);
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline45(obj, GeneratedOutlineSupport1.outline107("illegal object in getInstance: ")));
        }
        return (ASN1GeneralizedTime) obj;
    }

    private boolean hasFractionalSeconds() {
        int i = 0;
        while (true) {
            byte[] bArr = this.time;
            if (i != bArr.length) {
                if (bArr[i] == 46 && i == 14) {
                    return true;
                }
                i++;
            } else {
                return false;
            }
        }
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Primitive
    boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof DERGeneralizedTime)) {
            return false;
        }
        return Arrays.areEqual(this.time, ((DERGeneralizedTime) aSN1Primitive).time);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Primitive
    public void encode(ASN1OutputStream aSN1OutputStream) throws IOException {
        aSN1OutputStream.writeEncoded(24, this.time);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Primitive
    public int encodedLength() {
        int length = this.time.length;
        return StreamUtil.calculateBodyLength(length) + 1 + length;
    }

    public Date getDate() throws ParseException {
        SimpleDateFormat simpleDateFormat;
        SimpleDateFormat simpleDateFormat2;
        char charAt;
        String fromByteArray = Strings.fromByteArray(this.time);
        if (fromByteArray.endsWith("Z")) {
            if (hasFractionalSeconds()) {
                simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss.SSS'Z'");
            } else {
                simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss'Z'");
            }
            simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        } else if (fromByteArray.indexOf(45) <= 0 && fromByteArray.indexOf(43) <= 0) {
            if (hasFractionalSeconds()) {
                simpleDateFormat2 = new SimpleDateFormat("yyyyMMddHHmmss.SSS");
            } else {
                simpleDateFormat2 = new SimpleDateFormat("yyyyMMddHHmmss");
            }
            simpleDateFormat = simpleDateFormat2;
            simpleDateFormat.setTimeZone(new SimpleTimeZone(0, TimeZone.getDefault().getID()));
        } else {
            fromByteArray = getTime();
            if (hasFractionalSeconds()) {
                simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss.SSSz");
            } else {
                simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssz");
            }
            simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        }
        if (hasFractionalSeconds()) {
            String substring = fromByteArray.substring(14);
            int i = 1;
            while (i < substring.length() && '0' <= (charAt = substring.charAt(i)) && charAt <= '9') {
                i++;
            }
            int i2 = i - 1;
            if (i2 > 3) {
                fromByteArray = fromByteArray.substring(0, 14) + (substring.substring(0, 4) + substring.substring(i));
            } else if (i2 == 1) {
                StringBuilder sb = new StringBuilder();
                GeneratedOutlineSupport1.outline150(substring, 0, i, sb, "00");
                fromByteArray = fromByteArray.substring(0, 14) + GeneratedOutlineSupport1.outline55(substring, i, sb);
            } else if (i2 == 2) {
                StringBuilder sb2 = new StringBuilder();
                GeneratedOutlineSupport1.outline150(substring, 0, i, sb2, "0");
                fromByteArray = fromByteArray.substring(0, 14) + GeneratedOutlineSupport1.outline55(substring, i, sb2);
            }
        }
        return simpleDateFormat.parse(fromByteArray);
    }

    public String getTime() {
        String fromByteArray = Strings.fromByteArray(this.time);
        if (fromByteArray.charAt(fromByteArray.length() - 1) == 'Z') {
            return fromByteArray.substring(0, fromByteArray.length() - 1) + "GMT+00:00";
        }
        int length = fromByteArray.length() - 5;
        char charAt = fromByteArray.charAt(length);
        if (charAt != '-' && charAt != '+') {
            int length2 = fromByteArray.length() - 3;
            char charAt2 = fromByteArray.charAt(length2);
            if (charAt2 != '-' && charAt2 != '+') {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107(fromByteArray);
                outline107.append(calculateGMTOffset());
                return outline107.toString();
            }
            StringBuilder sb = new StringBuilder();
            GeneratedOutlineSupport1.outline150(fromByteArray, 0, length2, sb, "GMT");
            sb.append(fromByteArray.substring(length2));
            sb.append(":00");
            return sb.toString();
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(fromByteArray.substring(0, length));
        sb2.append("GMT");
        int i = length + 3;
        GeneratedOutlineSupport1.outline150(fromByteArray, length, i, sb2, ":");
        return GeneratedOutlineSupport1.outline55(fromByteArray, i, sb2);
    }

    public String getTimeString() {
        return Strings.fromByteArray(this.time);
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Primitive, com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Object
    public int hashCode() {
        return Arrays.hashCode(this.time);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Primitive
    public boolean isConstructed() {
        return false;
    }

    public DERGeneralizedTime(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss'Z'");
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        this.time = Strings.toByteArray(simpleDateFormat.format(date));
    }

    public static ASN1GeneralizedTime getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        ASN1Primitive object = aSN1TaggedObject.getObject();
        if (!z && !(object instanceof DERGeneralizedTime)) {
            return new ASN1GeneralizedTime(((ASN1OctetString) object).getOctets());
        }
        return getInstance(object);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DERGeneralizedTime(byte[] bArr) {
        this.time = bArr;
    }
}
