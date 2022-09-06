package org.apache.commons.net.telnet;
/* loaded from: classes4.dex */
public class TerminalTypeOptionHandler extends TelnetOptionHandler {
    protected static final int TERMINAL_TYPE = 24;
    protected static final int TERMINAL_TYPE_IS = 0;
    protected static final int TERMINAL_TYPE_SEND = 1;
    private String termType;

    public TerminalTypeOptionHandler(String str, boolean z, boolean z2, boolean z3, boolean z4) {
        super(TelnetOption.TERMINAL_TYPE, z, z2, z3, z4);
        this.termType = null;
        this.termType = str;
    }

    @Override // org.apache.commons.net.telnet.TelnetOptionHandler
    public int[] answerSubnegotiation(int[] iArr, int i) {
        String str;
        if (iArr == null || i <= 1 || (str = this.termType) == null) {
            return null;
        }
        if (iArr[0] != 24 || iArr[1] != 1) {
            return null;
        }
        int[] iArr2 = new int[str.length() + 2];
        iArr2[0] = 24;
        iArr2[1] = 0;
        for (int i2 = 0; i2 < this.termType.length(); i2++) {
            iArr2[i2 + 2] = this.termType.charAt(i2);
        }
        return iArr2;
    }

    @Override // org.apache.commons.net.telnet.TelnetOptionHandler
    public int[] startSubnegotiationLocal() {
        return null;
    }

    @Override // org.apache.commons.net.telnet.TelnetOptionHandler
    public int[] startSubnegotiationRemote() {
        return null;
    }

    public TerminalTypeOptionHandler(String str) {
        super(TelnetOption.TERMINAL_TYPE, false, false, false, false);
        this.termType = null;
        this.termType = str;
    }
}
