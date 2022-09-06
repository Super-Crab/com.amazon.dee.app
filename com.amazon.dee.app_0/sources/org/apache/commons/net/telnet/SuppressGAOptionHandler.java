package org.apache.commons.net.telnet;
/* loaded from: classes4.dex */
public class SuppressGAOptionHandler extends TelnetOptionHandler {
    public SuppressGAOptionHandler(boolean z, boolean z2, boolean z3, boolean z4) {
        super(TelnetOption.SUPPRESS_GO_AHEAD, z, z2, z3, z4);
    }

    @Override // org.apache.commons.net.telnet.TelnetOptionHandler
    public int[] answerSubnegotiation(int[] iArr, int i) {
        return null;
    }

    @Override // org.apache.commons.net.telnet.TelnetOptionHandler
    public int[] startSubnegotiationLocal() {
        return null;
    }

    @Override // org.apache.commons.net.telnet.TelnetOptionHandler
    public int[] startSubnegotiationRemote() {
        return null;
    }

    public SuppressGAOptionHandler() {
        super(TelnetOption.SUPPRESS_GO_AHEAD, false, false, false, false);
    }
}
