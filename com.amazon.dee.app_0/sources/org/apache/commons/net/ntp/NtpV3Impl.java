package org.apache.commons.net.ntp;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.net.DatagramPacket;
import okio.Utf8;
/* loaded from: classes4.dex */
public class NtpV3Impl implements NtpV3Packet {
    private static final int KEY_IDENTIFIER_INDEX = 48;
    private static final int LI_INDEX = 0;
    private static final int LI_SHIFT = 6;
    private static final int MESSAGE_DIGEST = 54;
    private static final int MODE_INDEX = 0;
    private static final int MODE_SHIFT = 0;
    private static final int ORIGINATE_TIMESTAMP_INDEX = 24;
    private static final int POLL_INDEX = 2;
    private static final int PRECISION_INDEX = 3;
    private static final int RECEIVE_TIMESTAMP_INDEX = 32;
    private static final int REFERENCE_ID_INDEX = 12;
    private static final int REFERENCE_TIMESTAMP_INDEX = 16;
    private static final int ROOT_DELAY_INDEX = 4;
    private static final int ROOT_DISPERSION_INDEX = 8;
    private static final int STRATUM_INDEX = 1;
    private static final int TRANSMIT_TIMESTAMP_INDEX = 40;
    private static final int VERSION_INDEX = 0;
    private static final int VERSION_SHIFT = 3;
    private byte[] buf = new byte[48];
    private DatagramPacket dp;

    private int getInt(int i) {
        return ui(this.buf[i + 3]) | (ui(this.buf[i]) << 24) | (ui(this.buf[i + 1]) << 16) | (ui(this.buf[i + 2]) << 8);
    }

    private long getLong(int i) {
        return (ul(this.buf[i]) << 56) | (ul(this.buf[i + 1]) << 48) | (ul(this.buf[i + 2]) << 40) | (ul(this.buf[i + 3]) << 32) | (ul(this.buf[i + 4]) << 24) | (ul(this.buf[i + 5]) << 16) | (ul(this.buf[i + 6]) << 8) | ul(this.buf[i + 7]);
    }

    private TimeStamp getTimestamp(int i) {
        return new TimeStamp(getLong(i));
    }

    private String idAsHex() {
        return Integer.toHexString(getReferenceId());
    }

    private String idAsIPAddress() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(ui(this.buf[12]));
        stringBuffer.append(".");
        stringBuffer.append(ui(this.buf[13]));
        stringBuffer.append(".");
        stringBuffer.append(ui(this.buf[14]));
        stringBuffer.append(".");
        stringBuffer.append(ui(this.buf[15]));
        return stringBuffer.toString();
    }

    private String idAsString() {
        char c;
        String str = "";
        for (int i = 0; i <= 3 && (c = (char) this.buf[i + 12]) != 0; i++) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(str);
            stringBuffer.append(c);
            str = stringBuffer.toString();
        }
        return str;
    }

    private void setTimestamp(int i, TimeStamp timeStamp) {
        long ntpValue = timeStamp == null ? 0L : timeStamp.ntpValue();
        for (int i2 = 7; i2 >= 0; i2--) {
            this.buf[i + i2] = (byte) (255 & ntpValue);
            ntpValue >>>= 8;
        }
    }

    protected static final int ui(byte b) {
        return b & 255;
    }

    protected static final long ul(byte b) {
        return b & 255;
    }

    @Override // org.apache.commons.net.ntp.NtpV3Packet
    public DatagramPacket getDatagramPacket() {
        if (this.dp == null) {
            synchronized (this) {
                if (this.dp == null) {
                    this.dp = new DatagramPacket(this.buf, this.buf.length);
                    this.dp.setPort(123);
                }
            }
        }
        return this.dp;
    }

    @Override // org.apache.commons.net.ntp.NtpV3Packet
    public int getLeapIndicator() {
        return (ui(this.buf[0]) >> 6) & 3;
    }

    @Override // org.apache.commons.net.ntp.NtpV3Packet
    public int getMode() {
        return (ui(this.buf[0]) >> 0) & 7;
    }

    @Override // org.apache.commons.net.ntp.NtpV3Packet
    public String getModeName() {
        return NtpUtils.getModeName(getMode());
    }

    @Override // org.apache.commons.net.ntp.NtpV3Packet
    public TimeStamp getOriginateTimeStamp() {
        return getTimestamp(24);
    }

    @Override // org.apache.commons.net.ntp.NtpV3Packet
    public int getPoll() {
        return this.buf[2];
    }

    @Override // org.apache.commons.net.ntp.NtpV3Packet
    public int getPrecision() {
        return this.buf[3];
    }

    @Override // org.apache.commons.net.ntp.NtpV3Packet
    public TimeStamp getReceiveTimeStamp() {
        return getTimestamp(32);
    }

    @Override // org.apache.commons.net.ntp.NtpV3Packet
    public int getReferenceId() {
        return getInt(12);
    }

    @Override // org.apache.commons.net.ntp.NtpV3Packet
    public String getReferenceIdString() {
        int version = getVersion();
        int stratum = getStratum();
        if (version == 3 || version == 4) {
            if (stratum == 0 || stratum == 1) {
                return idAsString();
            }
            if (version == 4) {
                return idAsHex();
            }
        }
        if (stratum >= 2) {
            return idAsIPAddress();
        }
        return idAsHex();
    }

    @Override // org.apache.commons.net.ntp.NtpV3Packet
    public TimeStamp getReferenceTimeStamp() {
        return getTimestamp(16);
    }

    @Override // org.apache.commons.net.ntp.NtpV3Packet
    public int getRootDelay() {
        return getInt(4);
    }

    @Override // org.apache.commons.net.ntp.NtpV3Packet
    public double getRootDelayInMillisDouble() {
        return getRootDelay() / 65.536d;
    }

    @Override // org.apache.commons.net.ntp.NtpV3Packet
    public int getRootDispersion() {
        return getInt(8);
    }

    @Override // org.apache.commons.net.ntp.NtpV3Packet
    public long getRootDispersionInMillis() {
        return (getRootDispersion() * 1000) / 65536;
    }

    @Override // org.apache.commons.net.ntp.NtpV3Packet
    public double getRootDispersionInMillisDouble() {
        return getRootDispersion() / 65.536d;
    }

    @Override // org.apache.commons.net.ntp.NtpV3Packet
    public int getStratum() {
        return ui(this.buf[1]);
    }

    @Override // org.apache.commons.net.ntp.NtpV3Packet
    public TimeStamp getTransmitTimeStamp() {
        return getTimestamp(40);
    }

    @Override // org.apache.commons.net.ntp.NtpV3Packet
    public String getType() {
        return NtpV3Packet.TYPE_NTP;
    }

    @Override // org.apache.commons.net.ntp.NtpV3Packet
    public int getVersion() {
        return (ui(this.buf[0]) >> 3) & 7;
    }

    @Override // org.apache.commons.net.ntp.NtpV3Packet
    public void setDatagramPacket(DatagramPacket datagramPacket) {
        byte[] data = datagramPacket.getData();
        int length = datagramPacket.getLength();
        byte[] bArr = this.buf;
        if (length > bArr.length) {
            length = bArr.length;
        }
        System.arraycopy(data, 0, this.buf, 0, length);
    }

    @Override // org.apache.commons.net.ntp.NtpV3Packet
    public void setLeapIndicator(int i) {
        byte[] bArr = this.buf;
        bArr[0] = (byte) (((i & 3) << 6) | (bArr[0] & Utf8.REPLACEMENT_BYTE));
    }

    @Override // org.apache.commons.net.ntp.NtpV3Packet
    public void setMode(int i) {
        byte[] bArr = this.buf;
        bArr[0] = (byte) ((i & 7) | (bArr[0] & 248));
    }

    @Override // org.apache.commons.net.ntp.NtpV3Packet
    public void setOriginateTimeStamp(TimeStamp timeStamp) {
        setTimestamp(24, timeStamp);
    }

    @Override // org.apache.commons.net.ntp.NtpV3Packet
    public void setPoll(int i) {
        this.buf[2] = (byte) (i & 255);
    }

    public void setPrecision(int i) {
        this.buf[3] = (byte) (i & 255);
    }

    @Override // org.apache.commons.net.ntp.NtpV3Packet
    public void setReceiveTimeStamp(TimeStamp timeStamp) {
        setTimestamp(32, timeStamp);
    }

    @Override // org.apache.commons.net.ntp.NtpV3Packet
    public void setReferenceId(int i) {
        for (int i2 = 3; i2 >= 0; i2--) {
            this.buf[i2 + 12] = (byte) (i & 255);
            i >>>= 8;
        }
    }

    @Override // org.apache.commons.net.ntp.NtpV3Packet
    public void setReferenceTime(TimeStamp timeStamp) {
        setTimestamp(16, timeStamp);
    }

    @Override // org.apache.commons.net.ntp.NtpV3Packet
    public void setStratum(int i) {
        this.buf[1] = (byte) (i & 255);
    }

    @Override // org.apache.commons.net.ntp.NtpV3Packet
    public void setTransmitTime(TimeStamp timeStamp) {
        setTimestamp(40, timeStamp);
    }

    @Override // org.apache.commons.net.ntp.NtpV3Packet
    public void setVersion(int i) {
        byte[] bArr = this.buf;
        bArr[0] = (byte) (((i & 7) << 3) | (bArr[0] & 199));
    }

    public String toString() {
        StringBuffer outline103 = GeneratedOutlineSupport1.outline103("[version:");
        outline103.append(getVersion());
        outline103.append(", mode:");
        outline103.append(getMode());
        outline103.append(", poll:");
        outline103.append(getPoll());
        outline103.append(", precision:");
        outline103.append(getPrecision());
        outline103.append(", delay:");
        outline103.append(getRootDelay());
        outline103.append(", dispersion(ms):");
        outline103.append(getRootDispersionInMillisDouble());
        outline103.append(", id:");
        outline103.append(getReferenceIdString());
        outline103.append(", xmitTime:");
        outline103.append(getTransmitTimeStamp().toDateString());
        outline103.append(" ]");
        return outline103.toString();
    }
}
