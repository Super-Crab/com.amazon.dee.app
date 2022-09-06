package org.apache.commons.net.tftp;

import java.net.DatagramPacket;
import java.net.InetAddress;
/* loaded from: classes4.dex */
public final class TFTPAckPacket extends TFTPPacket {
    int _blockNumber;

    public TFTPAckPacket(InetAddress inetAddress, int i, int i2) {
        super(4, inetAddress, i);
        this._blockNumber = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.commons.net.tftp.TFTPPacket
    public DatagramPacket _newDatagram(DatagramPacket datagramPacket, byte[] bArr) {
        bArr[0] = 0;
        bArr[1] = (byte) this._type;
        int i = this._blockNumber;
        bArr[2] = (byte) ((65535 & i) >> 8);
        bArr[3] = (byte) (i & 255);
        datagramPacket.setAddress(this._address);
        datagramPacket.setPort(this._port);
        datagramPacket.setData(bArr);
        datagramPacket.setLength(4);
        return datagramPacket;
    }

    public int getBlockNumber() {
        return this._blockNumber;
    }

    @Override // org.apache.commons.net.tftp.TFTPPacket
    public DatagramPacket newDatagram() {
        int i = this._blockNumber;
        byte[] bArr = {0, (byte) this._type, (byte) ((65535 & i) >> 8), (byte) (i & 255)};
        return new DatagramPacket(bArr, bArr.length, this._address, this._port);
    }

    public void setBlockNumber(int i) {
        this._blockNumber = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TFTPAckPacket(DatagramPacket datagramPacket) throws TFTPPacketException {
        super(4, datagramPacket.getAddress(), datagramPacket.getPort());
        byte[] data = datagramPacket.getData();
        if (getType() == data[1]) {
            this._blockNumber = (data[3] & 255) | ((data[2] & 255) << 8);
            return;
        }
        throw new TFTPPacketException("TFTP operator code does not match type.");
    }
}
