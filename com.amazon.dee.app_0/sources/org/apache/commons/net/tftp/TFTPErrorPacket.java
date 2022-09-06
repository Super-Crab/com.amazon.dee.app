package org.apache.commons.net.tftp;

import java.net.DatagramPacket;
import java.net.InetAddress;
/* loaded from: classes4.dex */
public final class TFTPErrorPacket extends TFTPPacket {
    public static final int ACCESS_VIOLATION = 2;
    public static final int FILE_EXISTS = 6;
    public static final int FILE_NOT_FOUND = 1;
    public static final int ILLEGAL_OPERATION = 4;
    public static final int NO_SUCH_USER = 7;
    public static final int OUT_OF_SPACE = 3;
    public static final int UNDEFINED = 0;
    public static final int UNKNOWN_TID = 5;
    int _error;
    String _message;

    public TFTPErrorPacket(InetAddress inetAddress, int i, int i2, String str) {
        super(5, inetAddress, i);
        this._error = i2;
        this._message = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.commons.net.tftp.TFTPPacket
    public DatagramPacket _newDatagram(DatagramPacket datagramPacket, byte[] bArr) {
        int length = this._message.length();
        bArr[0] = 0;
        bArr[1] = (byte) this._type;
        int i = this._error;
        bArr[2] = (byte) ((65535 & i) >> 8);
        bArr[3] = (byte) (i & 255);
        System.arraycopy(this._message.getBytes(), 0, bArr, 4, length);
        int i2 = length + 4;
        bArr[i2] = 0;
        datagramPacket.setAddress(this._address);
        datagramPacket.setPort(this._port);
        datagramPacket.setData(bArr);
        datagramPacket.setLength(i2);
        return datagramPacket;
    }

    public int getError() {
        return this._error;
    }

    public String getMessage() {
        return this._message;
    }

    @Override // org.apache.commons.net.tftp.TFTPPacket
    public DatagramPacket newDatagram() {
        int length = this._message.length();
        byte[] bArr = new byte[length + 5];
        bArr[0] = 0;
        bArr[1] = (byte) this._type;
        int i = this._error;
        bArr[2] = (byte) ((65535 & i) >> 8);
        bArr[3] = (byte) (i & 255);
        System.arraycopy(this._message.getBytes(), 0, bArr, 4, length);
        bArr[length + 4] = 0;
        return new DatagramPacket(bArr, bArr.length, this._address, this._port);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TFTPErrorPacket(DatagramPacket datagramPacket) throws TFTPPacketException {
        super(5, datagramPacket.getAddress(), datagramPacket.getPort());
        byte[] data = datagramPacket.getData();
        int length = datagramPacket.getLength();
        if (getType() == data[1]) {
            this._error = ((data[2] & 255) << 8) | (data[3] & 255);
            if (length >= 5) {
                StringBuffer stringBuffer = new StringBuffer();
                for (int i = 4; i < length && data[i] != 0; i++) {
                    stringBuffer.append((char) data[i]);
                }
                this._message = stringBuffer.toString();
                return;
            }
            throw new TFTPPacketException("Bad error packet. No message.");
        }
        throw new TFTPPacketException("TFTP operator code does not match type.");
    }
}
