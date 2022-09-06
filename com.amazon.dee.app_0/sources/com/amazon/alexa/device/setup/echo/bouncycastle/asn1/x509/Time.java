package com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Choice;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Object;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Primitive;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1TaggedObject;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DERGeneralizedTime;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DERUTCTime;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;
/* loaded from: classes.dex */
public class Time extends ASN1Object implements ASN1Choice {
    ASN1Primitive time;

    public Time(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof DERUTCTime) && !(aSN1Primitive instanceof DERGeneralizedTime)) {
            throw new IllegalArgumentException("unknown object passed to Time");
        }
        this.time = aSN1Primitive;
    }

    public static Time getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(aSN1TaggedObject.getObject());
    }

    public Date getDate() {
        try {
            if (this.time instanceof DERUTCTime) {
                return ((DERUTCTime) this.time).getAdjustedDate();
            }
            return ((DERGeneralizedTime) this.time).getDate();
        } catch (ParseException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("invalid date string: ");
            outline107.append(e.getMessage());
            throw new IllegalStateException(outline107.toString());
        }
    }

    public String getTime() {
        ASN1Primitive aSN1Primitive = this.time;
        if (aSN1Primitive instanceof DERUTCTime) {
            return ((DERUTCTime) aSN1Primitive).getAdjustedTime();
        }
        return ((DERGeneralizedTime) aSN1Primitive).getTime();
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Object, com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.time;
    }

    public String toString() {
        return getTime();
    }

    public static Time getInstance(Object obj) {
        if (obj != null && !(obj instanceof Time)) {
            if (obj instanceof DERUTCTime) {
                return new Time((DERUTCTime) obj);
            }
            if (obj instanceof DERGeneralizedTime) {
                return new Time((DERGeneralizedTime) obj);
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline45(obj, GeneratedOutlineSupport1.outline107("unknown object in factory: ")));
        }
        return (Time) obj;
    }

    public Time(Date date) {
        SimpleDateFormat simpleDateFormat;
        SimpleTimeZone simpleTimeZone = new SimpleTimeZone(0, "Z");
        new SimpleDateFormat("yyyyMMddHHmmss").setTimeZone(simpleTimeZone);
        String str = simpleDateFormat.format(date) + "Z";
        int parseInt = Integer.parseInt(str.substring(0, 4));
        if (parseInt >= 1950 && parseInt <= 2049) {
            this.time = new DERUTCTime(str.substring(2));
        } else {
            this.time = new DERGeneralizedTime(str);
        }
    }
}
