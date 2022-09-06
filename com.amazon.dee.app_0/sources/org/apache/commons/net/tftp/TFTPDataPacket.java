package org.apache.commons.net.tftp;

import java.net.DatagramPacket;
import java.net.InetAddress;
/* loaded from: classes4.dex */
public final class TFTPDataPacket extends TFTPPacket {
    public static final int MAX_DATA_LENGTH = 512;
    public static final int MIN_DATA_LENGTH = 0;
    int _blockNumber;
    byte[] _data;
    int _length;
    int _offset;

    public TFTPDataPacket(InetAddress inetAddress, int i, int i2, byte[] bArr, int i3, int i4) {
        super(3, inetAddress, i);
        this._blockNumber = i2;
        this._data = bArr;
        this._offset = i3;
        if (i4 > 512) {
            this._length = 512;
        } else {
            this._length = i4;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.commons.net.tftp.TFTPPacket
    public DatagramPacket _newDatagram(DatagramPacket datagramPacket, byte[] bArr) {
        bArr[0] = 0;
        bArr[1] = (byte) this._type;
        int i = this._blockNumber;
        bArr[2] = (byte) ((65535 & i) >> 8);
        bArr[3] = (byte) (i & 255);
        byte[] bArr2 = this._data;
        if (bArr != bArr2) {
            System.arraycopy(bArr2, this._offset, bArr, 4, this._length);
        }
        datagramPacket.setAddress(this._address);
        datagramPacket.setPort(this._port);
        datagramPacket.setData(bArr);
        datagramPacket.setLength(this._length + 4);
        return datagramPacket;
    }

    public int getBlockNumber() {
        return this._blockNumber;
    }

    public byte[] getData() {
        return this._data;
    }

    public int getDataLength() {
        return this._length;
    }

    public int getDataOffset() {
        return this._offset;
    }

    @Override // org.apache.commons.net.tftp.TFTPPacket
    public DatagramPacket newDatagram() {
        int i = this._length;
        byte[] bArr = new byte[i + 4];
        bArr[0] = 0;
        bArr[1] = (byte) this._type;
        int i2 = this._blockNumber;
        bArr[2] = (byte) ((65535 & i2) >> 8);
        bArr[3] = (byte) (i2 & 255);
        System.arraycopy(this._data, this._offset, bArr, 4, i);
        return new DatagramPacket(bArr, this._length + 4, this._address, this._port);
    }

    public void setBlockNumber(int i) {
        this._blockNumber = i;
    }

    public void setData(byte[] bArr, int i, int i2) {
        this._data = bArr;
        this._offset = i;
        this._length = i2;
        if (i2 > 512) {
            this._length = 512;
        } else {
            this._length = i2;
        }
    }

    public TFTPDataPacket(InetAddress inetAddress, int i, int i2, byte[] bArr) {
        this(inetAddress, i, i2, bArr, 0, bArr.length);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TFTPDataPacket(DatagramPacket datagramPacket) throws TFTPPacketException {
        super(3, datagramPacket.getAddress(), datagramPacket.getPort());
        this._data = datagramPacket.getData();
        this._offset = 4;
        int type = getType();
        byte[] bArr = this._data;
        if (type == bArr[1]) {
            this._blockNumber = ((bArr[2] & 255) << 8) | (bArr[3] & 255);
            this._length = datagramPacket.getLength() - 4;
            if (this._length <= 512) {
                return;
            }
            this._length = 512;
            return;
        }
        throw new TFTPPacketException("TFTP operator code does not match type.");
    }
}
