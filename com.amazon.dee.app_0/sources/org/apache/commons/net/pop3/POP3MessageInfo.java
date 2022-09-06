package org.apache.commons.net.pop3;
/* loaded from: classes4.dex */
public final class POP3MessageInfo {
    public String identifier;
    public int number;
    public int size;

    public POP3MessageInfo() {
        this.size = 0;
        this.number = 0;
        this.identifier = null;
    }

    public POP3MessageInfo(int i, int i2) {
        this.number = i;
        this.size = i2;
        this.identifier = null;
    }

    public POP3MessageInfo(int i, String str) {
        this.number = i;
        this.size = -1;
        this.identifier = str;
    }
}
