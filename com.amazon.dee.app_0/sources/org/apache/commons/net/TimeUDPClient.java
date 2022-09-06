package org.apache.commons.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Date;
import org.bouncycastle.asn1.cmc.BodyPartID;
/* loaded from: classes4.dex */
public final class TimeUDPClient extends DatagramSocketClient {
    public static final int DEFAULT_PORT = 37;
    public static final long SECONDS_1900_TO_1970 = 2208988800L;
    private byte[] __dummyData = new byte[1];
    private byte[] __timeData = new byte[4];

    public Date getDate(InetAddress inetAddress, int i) throws IOException {
        return new Date((getTime(inetAddress, i) - 2208988800L) * 1000);
    }

    public long getTime(InetAddress inetAddress, int i) throws IOException {
        byte[] bArr = this.__dummyData;
        DatagramPacket datagramPacket = new DatagramPacket(bArr, bArr.length, inetAddress, i);
        byte[] bArr2 = this.__timeData;
        DatagramPacket datagramPacket2 = new DatagramPacket(bArr2, bArr2.length);
        this._socket_.send(datagramPacket);
        this._socket_.receive(datagramPacket2);
        byte[] bArr3 = this.__timeData;
        return (bArr3[3] & 255 & BodyPartID.bodyIdMax) | (((bArr3[0] & 255) << 24) & BodyPartID.bodyIdMax) | 0 | (((bArr3[1] & 255) << 16) & BodyPartID.bodyIdMax) | (((bArr3[2] & 255) << 8) & BodyPartID.bodyIdMax);
    }

    public Date getDate(InetAddress inetAddress) throws IOException {
        return new Date((getTime(inetAddress, 37) - 2208988800L) * 1000);
    }

    public long getTime(InetAddress inetAddress) throws IOException {
        return getTime(inetAddress, 37);
    }
}
