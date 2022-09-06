package com.amazon.alexa.device.setup.echo.bouncycastle.asn1;

import java.util.Vector;
/* loaded from: classes.dex */
public class ASN1EncodableVector {
    Vector v = new Vector();

    public void add(ASN1Encodable aSN1Encodable) {
        this.v.addElement(aSN1Encodable);
    }

    public ASN1Encodable get(int i) {
        return (ASN1Encodable) this.v.elementAt(i);
    }

    public int size() {
        return this.v.size();
    }
}
