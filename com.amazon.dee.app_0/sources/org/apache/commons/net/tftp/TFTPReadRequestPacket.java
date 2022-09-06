package org.apache.commons.net.tftp;

import java.net.DatagramPacket;
import java.net.InetAddress;
/* loaded from: classes4.dex */
public final class TFTPReadRequestPacket extends TFTPRequestPacket {
    public TFTPReadRequestPacket(InetAddress inetAddress, int i, String str, int i2) {
        super(inetAddress, i, 1, str, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TFTPReadRequestPacket(DatagramPacket datagramPacket) throws TFTPPacketException {
        super(1, datagramPacket);
    }
}
