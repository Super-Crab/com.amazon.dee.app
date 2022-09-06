package org.apache.commons.net.tftp;

import java.net.DatagramPacket;
import java.net.InetAddress;
/* loaded from: classes4.dex */
public final class TFTPWriteRequestPacket extends TFTPRequestPacket {
    public TFTPWriteRequestPacket(InetAddress inetAddress, int i, String str, int i2) {
        super(inetAddress, i, 2, str, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TFTPWriteRequestPacket(DatagramPacket datagramPacket) throws TFTPPacketException {
        super(2, datagramPacket);
    }
}
