package com.adobe.xmp.impl;

import com.adobe.xmp.XMPException;
/* loaded from: classes.dex */
class ParseState {
    private int pos = 0;
    private String str;

    public ParseState(String str) {
        this.str = str;
    }

    public char ch() {
        if (this.pos < this.str.length()) {
            return this.str.charAt(this.pos);
        }
        return (char) 0;
    }

    public char ch(int i) {
        if (i < this.str.length()) {
            return this.str.charAt(i);
        }
        return (char) 0;
    }

    public int gatherInt(String str, int i) throws XMPException {
        char ch = ch(this.pos);
        int i2 = 0;
        boolean z = false;
        while ('0' <= ch && ch <= '9') {
            i2 = (i2 * 10) + (ch - '0');
            this.pos++;
            ch = ch(this.pos);
            z = true;
        }
        if (z) {
            if (i2 > i) {
                return i;
            }
            if (i2 >= 0) {
                return i2;
            }
            return 0;
        }
        throw new XMPException(str, 5);
    }

    public boolean hasNext() {
        return this.pos < this.str.length();
    }

    public int length() {
        return this.str.length();
    }

    public int pos() {
        return this.pos;
    }

    public void skip() {
        this.pos++;
    }
}
