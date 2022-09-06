package com.amazon.alexa.device.setup.echo.bouncycastle.asn1;

import com.amazon.alexa.device.setup.echo.bouncycastle.util.Arrays;
import com.amazon.alexa.device.setup.echo.bouncycastle.util.Strings;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;
/* loaded from: classes.dex */
public class DERUTCTime extends ASN1Primitive {
    private final byte[] time;

    public DERUTCTime(String str) {
        this.time = Strings.toByteArray(str);
        try {
            getDate();
        } catch (ParseException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("invalid date string: ");
            outline107.append(e.getMessage());
            throw new IllegalArgumentException(outline107.toString());
        }
    }

    public static ASN1UTCTime getInstance(Object obj) {
        if (obj != null && !(obj instanceof ASN1UTCTime)) {
            if (obj instanceof DERUTCTime) {
                return new ASN1UTCTime(((DERUTCTime) obj).time);
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline45(obj, GeneratedOutlineSupport1.outline107("illegal object in getInstance: ")));
        }
        return (ASN1UTCTime) obj;
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Primitive
    boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof DERUTCTime)) {
            return false;
        }
        return Arrays.areEqual(this.time, ((DERUTCTime) aSN1Primitive).time);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Primitive
    public void encode(ASN1OutputStream aSN1OutputStream) throws IOException {
        aSN1OutputStream.write(23);
        int length = this.time.length;
        aSN1OutputStream.writeLength(length);
        for (int i = 0; i != length; i++) {
            aSN1OutputStream.write(this.time[i]);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Primitive
    public int encodedLength() {
        int length = this.time.length;
        return StreamUtil.calculateBodyLength(length) + 1 + length;
    }

    public Date getAdjustedDate() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssz");
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        return simpleDateFormat.parse(getAdjustedTime());
    }

    public String getAdjustedTime() {
        String time = getTime();
        if (time.charAt(0) < '5') {
            return GeneratedOutlineSupport1.outline72("20", time);
        }
        return GeneratedOutlineSupport1.outline72("19", time);
    }

    public Date getDate() throws ParseException {
        return new SimpleDateFormat("yyMMddHHmmssz").parse(getTime());
    }

    public String getTime() {
        String fromByteArray = Strings.fromByteArray(this.time);
        if (fromByteArray.indexOf(45) < 0 && fromByteArray.indexOf(43) < 0) {
            if (fromByteArray.length() == 11) {
                return fromByteArray.substring(0, 10) + "00GMT+00:00";
            }
            return fromByteArray.substring(0, 12) + "GMT+00:00";
        }
        int indexOf = fromByteArray.indexOf(45);
        if (indexOf < 0) {
            indexOf = fromByteArray.indexOf(43);
        }
        if (indexOf == fromByteArray.length() - 3) {
            fromByteArray = GeneratedOutlineSupport1.outline72(fromByteArray, "00");
        }
        if (indexOf == 10) {
            StringBuilder sb = new StringBuilder();
            GeneratedOutlineSupport1.outline150(fromByteArray, 0, 10, sb, "00GMT");
            GeneratedOutlineSupport1.outline150(fromByteArray, 10, 13, sb, ":");
            sb.append(fromByteArray.substring(13, 15));
            return sb.toString();
        }
        StringBuilder sb2 = new StringBuilder();
        GeneratedOutlineSupport1.outline150(fromByteArray, 0, 12, sb2, "GMT");
        GeneratedOutlineSupport1.outline150(fromByteArray, 12, 15, sb2, ":");
        sb2.append(fromByteArray.substring(15, 17));
        return sb2.toString();
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

    public String toString() {
        return Strings.fromByteArray(this.time);
    }

    public DERUTCTime(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMddHHmmss'Z'");
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        this.time = Strings.toByteArray(simpleDateFormat.format(date));
    }

    public static ASN1UTCTime getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        ASN1Primitive object = aSN1TaggedObject.getObject();
        if (!z && !(object instanceof ASN1UTCTime)) {
            return new ASN1UTCTime(((ASN1OctetString) object).getOctets());
        }
        return getInstance(object);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DERUTCTime(byte[] bArr) {
        this.time = bArr;
    }
}
