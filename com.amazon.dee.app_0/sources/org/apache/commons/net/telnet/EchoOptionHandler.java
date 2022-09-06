package org.apache.commons.net.telnet;
/* loaded from: classes4.dex */
public class EchoOptionHandler extends TelnetOptionHandler {
    public EchoOptionHandler(boolean z, boolean z2, boolean z3, boolean z4) {
        super(TelnetOption.ECHO, z, z2, z3, z4);
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

    public EchoOptionHandler() {
        super(TelnetOption.ECHO, false, false, false, false);
    }
}
