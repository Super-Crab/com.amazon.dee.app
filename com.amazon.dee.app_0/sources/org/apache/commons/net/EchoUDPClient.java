package org.apache.commons.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
/* loaded from: classes4.dex */
public final class EchoUDPClient extends DiscardUDPClient {
    public static final int DEFAULT_PORT = 7;
    private DatagramPacket __receivePacket = new DatagramPacket(new byte[0], 0);

    public int receive(byte[] bArr, int i) throws IOException {
        this.__receivePacket.setData(bArr);
        this.__receivePacket.setLength(i);
        this._socket_.receive(this.__receivePacket);
        return this.__receivePacket.getLength();
    }

    @Override // org.apache.commons.net.DiscardUDPClient
    public void send(byte[] bArr, int i, InetAddress inetAddress) throws IOException {
        send(bArr, i, inetAddress, 7);
    }

    @Override // org.apache.commons.net.DiscardUDPClient
    public void send(byte[] bArr, InetAddress inetAddress) throws IOException {
        send(bArr, bArr.length, inetAddress, 7);
    }

    public int receive(byte[] bArr) throws IOException {
        return receive(bArr, bArr.length);
    }
}
