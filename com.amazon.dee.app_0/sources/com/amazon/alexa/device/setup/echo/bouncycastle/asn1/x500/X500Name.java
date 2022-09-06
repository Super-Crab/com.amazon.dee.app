package com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x500;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Choice;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Encodable;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Object;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1ObjectIdentifier;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Primitive;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Sequence;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1TaggedObject;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DERSequence;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x500.style.BCStyle;
import java.util.Enumeration;
/* loaded from: classes.dex */
public class X500Name extends ASN1Object implements ASN1Choice {
    private static X500NameStyle defaultStyle = BCStyle.INSTANCE;
    private int hashCodeValue;
    private boolean isHashCodeCalculated;
    private final RDN[] rdns;
    private X500NameStyle style;

    public X500Name(X500NameStyle x500NameStyle, X500Name x500Name) {
        this.rdns = x500Name.rdns;
        this.style = x500NameStyle;
    }

    public static X500NameStyle getDefaultStyle() {
        return defaultStyle;
    }

    public static X500Name getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, true));
    }

    public static void setDefaultStyle(X500NameStyle x500NameStyle) {
        if (x500NameStyle != null) {
            defaultStyle = x500NameStyle;
            return;
        }
        throw new NullPointerException("cannot set style to null");
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Object
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof X500Name) && !(obj instanceof ASN1Sequence)) {
            return false;
        }
        if (toASN1Primitive().equals(((ASN1Encodable) obj).toASN1Primitive())) {
            return true;
        }
        try {
            return this.style.areEqual(this, new X500Name(ASN1Sequence.getInstance(((ASN1Encodable) obj).toASN1Primitive())));
        } catch (Exception unused) {
            return false;
        }
    }

    public ASN1ObjectIdentifier[] getAttributeTypes() {
        int i;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            RDN[] rdnArr = this.rdns;
            if (i2 == rdnArr.length) {
                break;
            }
            i3 += rdnArr[i2].size();
            i2++;
        }
        ASN1ObjectIdentifier[] aSN1ObjectIdentifierArr = new ASN1ObjectIdentifier[i3];
        int i4 = 0;
        int i5 = 0;
        while (true) {
            RDN[] rdnArr2 = this.rdns;
            if (i4 != rdnArr2.length) {
                RDN rdn = rdnArr2[i4];
                if (rdn.isMultiValued()) {
                    AttributeTypeAndValue[] typesAndValues = rdn.getTypesAndValues();
                    i = i5;
                    int i6 = 0;
                    while (i6 != typesAndValues.length) {
                        aSN1ObjectIdentifierArr[i] = typesAndValues[i6].getType();
                        i6++;
                        i++;
                    }
                } else if (rdn.size() != 0) {
                    i = i5 + 1;
                    aSN1ObjectIdentifierArr[i5] = rdn.getFirst().getType();
                } else {
                    i4++;
                }
                i5 = i;
                i4++;
            } else {
                return aSN1ObjectIdentifierArr;
            }
        }
    }

    public RDN[] getRDNs() {
        RDN[] rdnArr = this.rdns;
        RDN[] rdnArr2 = new RDN[rdnArr.length];
        System.arraycopy(rdnArr, 0, rdnArr2, 0, rdnArr2.length);
        return rdnArr2;
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Object
    public int hashCode() {
        if (this.isHashCodeCalculated) {
            return this.hashCodeValue;
        }
        this.isHashCodeCalculated = true;
        this.hashCodeValue = this.style.calculateHashCode(this);
        return this.hashCodeValue;
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Object, com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return new DERSequence(this.rdns);
    }

    public String toString() {
        return this.style.toString(this);
    }

    public static X500Name getInstance(Object obj) {
        if (obj instanceof X500Name) {
            return (X500Name) obj;
        }
        if (obj == null) {
            return null;
        }
        return new X500Name(ASN1Sequence.getInstance(obj));
    }

    public RDN[] getRDNs(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        int i;
        RDN[] rdnArr = new RDN[this.rdns.length];
        int i2 = 0;
        int i3 = 0;
        while (true) {
            RDN[] rdnArr2 = this.rdns;
            if (i2 != rdnArr2.length) {
                RDN rdn = rdnArr2[i2];
                if (rdn.isMultiValued()) {
                    AttributeTypeAndValue[] typesAndValues = rdn.getTypesAndValues();
                    for (int i4 = 0; i4 != typesAndValues.length; i4++) {
                        if (typesAndValues[i4].getType().equals(aSN1ObjectIdentifier)) {
                            i = i3 + 1;
                            rdnArr[i3] = rdn;
                            i3 = i;
                            break;
                        }
                    }
                    i2++;
                } else if (rdn.getFirst().getType().equals(aSN1ObjectIdentifier)) {
                    i = i3 + 1;
                    rdnArr[i3] = rdn;
                    i3 = i;
                    break;
                    i2++;
                } else {
                    i2++;
                }
            } else {
                RDN[] rdnArr3 = new RDN[i3];
                System.arraycopy(rdnArr, 0, rdnArr3, 0, rdnArr3.length);
                return rdnArr3;
            }
        }
    }

    private X500Name(ASN1Sequence aSN1Sequence) {
        this(defaultStyle, aSN1Sequence);
    }

    private X500Name(X500NameStyle x500NameStyle, ASN1Sequence aSN1Sequence) {
        this.style = x500NameStyle;
        this.rdns = new RDN[aSN1Sequence.size()];
        Enumeration objects = aSN1Sequence.getObjects();
        int i = 0;
        while (objects.hasMoreElements()) {
            this.rdns[i] = RDN.getInstance(objects.nextElement());
            i++;
        }
    }

    public static X500Name getInstance(X500NameStyle x500NameStyle, Object obj) {
        if (obj instanceof X500Name) {
            return getInstance(x500NameStyle, ((X500Name) obj).toASN1Primitive());
        }
        if (obj == null) {
            return null;
        }
        return new X500Name(x500NameStyle, ASN1Sequence.getInstance(obj));
    }

    public X500Name(RDN[] rdnArr) {
        this(defaultStyle, rdnArr);
    }

    public X500Name(X500NameStyle x500NameStyle, RDN[] rdnArr) {
        this.rdns = rdnArr;
        this.style = x500NameStyle;
    }

    public X500Name(String str) {
        this(defaultStyle, str);
    }

    public X500Name(X500NameStyle x500NameStyle, String str) {
        this(x500NameStyle.fromString(str));
        this.style = x500NameStyle;
    }
}
