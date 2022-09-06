package com.amazon.alexa.device.setup.echo.bouncycastle.asn1;
/* loaded from: classes.dex */
public class OIDTokenizer {
    private int index = 0;
    private final String oid;

    public OIDTokenizer(String str) {
        this.oid = str;
    }

    public boolean hasMoreTokens() {
        return this.index != -1;
    }

    public String nextToken() {
        int i = this.index;
        if (i == -1) {
            return null;
        }
        int indexOf = this.oid.indexOf(46, i);
        if (indexOf == -1) {
            String substring = this.oid.substring(this.index);
            this.index = -1;
            return substring;
        }
        String substring2 = this.oid.substring(this.index, indexOf);
        this.index = indexOf + 1;
        return substring2;
    }
}
