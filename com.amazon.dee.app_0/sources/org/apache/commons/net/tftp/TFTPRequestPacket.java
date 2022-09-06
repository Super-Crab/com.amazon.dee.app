package org.apache.commons.net.tftp;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.net.DatagramPacket;
import java.net.InetAddress;
/* loaded from: classes4.dex */
public abstract class TFTPRequestPacket extends TFTPPacket {
    String _filename;
    int _mode;
    static final String[] _modeStrings = {"netascii", "octet"};
    static final byte[][] _modeBytes = {new byte[]{110, 101, 116, 97, 115, 99, 105, 105, 0}, new byte[]{111, 99, 116, 101, 116, 0}};

    /* JADX INFO: Access modifiers changed from: package-private */
    public TFTPRequestPacket(InetAddress inetAddress, int i, int i2, String str, int i3) {
        super(i2, inetAddress, i);
        this._filename = str;
        this._mode = i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.commons.net.tftp.TFTPPacket
    public final DatagramPacket _newDatagram(DatagramPacket datagramPacket, byte[] bArr) {
        int length = this._filename.length();
        int length2 = _modeBytes[this._mode].length;
        bArr[0] = 0;
        bArr[1] = (byte) this._type;
        System.arraycopy(this._filename.getBytes(), 0, bArr, 2, length);
        bArr[length + 2] = 0;
        System.arraycopy(_modeBytes[this._mode], 0, bArr, length + 3, length2);
        datagramPacket.setAddress(this._address);
        datagramPacket.setPort(this._port);
        datagramPacket.setData(bArr);
        datagramPacket.setLength(length + length2 + 3);
        return datagramPacket;
    }

    public final String getFilename() {
        return this._filename;
    }

    public final int getMode() {
        return this._mode;
    }

    @Override // org.apache.commons.net.tftp.TFTPPacket
    public final DatagramPacket newDatagram() {
        int length = this._filename.length();
        int length2 = _modeBytes[this._mode].length;
        byte[] bArr = new byte[length + length2 + 4];
        bArr[0] = 0;
        bArr[1] = (byte) this._type;
        System.arraycopy(this._filename.getBytes(), 0, bArr, 2, length);
        bArr[length + 2] = 0;
        System.arraycopy(_modeBytes[this._mode], 0, bArr, length + 3, length2);
        return new DatagramPacket(bArr, bArr.length, this._address, this._port);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TFTPRequestPacket(int i, DatagramPacket datagramPacket) throws TFTPPacketException {
        super(i, datagramPacket.getAddress(), datagramPacket.getPort());
        byte[] data = datagramPacket.getData();
        if (getType() == data[1]) {
            StringBuffer stringBuffer = new StringBuffer();
            int i2 = 2;
            int length = datagramPacket.getLength();
            while (i2 < length && data[i2] != 0) {
                stringBuffer.append((char) data[i2]);
                i2++;
            }
            this._filename = stringBuffer.toString();
            if (i2 < length) {
                int i3 = 0;
                stringBuffer.setLength(0);
                for (int i4 = i2 + 1; i4 < length && data[i4] != 0; i4++) {
                    stringBuffer.append((char) data[i4]);
                }
                String lowerCase = stringBuffer.toString().toLowerCase();
                int length2 = _modeStrings.length;
                while (true) {
                    if (i3 >= length2) {
                        break;
                    } else if (lowerCase.equals(_modeStrings[i3])) {
                        this._mode = i3;
                        break;
                    } else {
                        i3++;
                    }
                }
                if (i3 >= length2) {
                    throw new TFTPPacketException(GeneratedOutlineSupport1.outline71("Unrecognized TFTP transfer mode: ", lowerCase));
                }
                return;
            }
            throw new TFTPPacketException("Bad filename and mode format.");
        }
        throw new TFTPPacketException("TFTP operator code does not match type.");
    }
}
