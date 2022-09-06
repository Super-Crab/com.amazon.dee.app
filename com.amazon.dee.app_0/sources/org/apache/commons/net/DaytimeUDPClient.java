package org.apache.commons.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
/* loaded from: classes4.dex */
public final class DaytimeUDPClient extends DatagramSocketClient {
    public static final int DEFAULT_PORT = 13;
    private byte[] __dummyData = new byte[1];
    private byte[] __timeData = new byte[256];

    public String getTime(InetAddress inetAddress, int i) throws IOException {
        byte[] bArr = this.__dummyData;
        DatagramPacket datagramPacket = new DatagramPacket(bArr, bArr.length, inetAddress, i);
        byte[] bArr2 = this.__timeData;
        DatagramPacket datagramPacket2 = new DatagramPacket(bArr2, bArr2.length);
        this._socket_.send(datagramPacket);
        this._socket_.receive(datagramPacket2);
        return new String(datagramPacket2.getData(), 0, datagramPacket2.getLength());
    }

    public String getTime(InetAddress inetAddress) throws IOException {
        return getTime(inetAddress, 13);
    }
}
