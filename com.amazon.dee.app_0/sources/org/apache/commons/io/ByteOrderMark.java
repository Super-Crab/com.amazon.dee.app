package org.apache.commons.io;

import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import java.io.Serializable;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.commons.net.telnet.TelnetCommand;
/* loaded from: classes4.dex */
public class ByteOrderMark implements Serializable {
    private static final long serialVersionUID = 1;
    private final int[] bytes;
    private final String charsetName;
    public static final ByteOrderMark UTF_8 = new ByteOrderMark("UTF-8", TelnetCommand.EOR, 187, 191);
    public static final ByteOrderMark UTF_16BE = new ByteOrderMark("UTF-16BE", 254, 255);
    public static final ByteOrderMark UTF_16LE = new ByteOrderMark("UTF-16LE", 255, 254);
    public static final ByteOrderMark UTF_32BE = new ByteOrderMark("UTF-32BE", 0, 0, 254, 255);
    public static final ByteOrderMark UTF_32LE = new ByteOrderMark("UTF-32LE", 254, 255, 0, 0);

    public ByteOrderMark(String str, int... iArr) {
        if (str != null && str.length() != 0) {
            if (iArr != null && iArr.length != 0) {
                this.charsetName = str;
                this.bytes = new int[iArr.length];
                System.arraycopy(iArr, 0, this.bytes, 0, iArr.length);
                return;
            }
            throw new IllegalArgumentException("No bytes specified");
        }
        throw new IllegalArgumentException("No charsetName specified");
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ByteOrderMark)) {
            return false;
        }
        ByteOrderMark byteOrderMark = (ByteOrderMark) obj;
        if (this.bytes.length != byteOrderMark.length()) {
            return false;
        }
        int i = 0;
        while (true) {
            int[] iArr = this.bytes;
            if (i >= iArr.length) {
                return true;
            }
            if (iArr[i] != byteOrderMark.get(i)) {
                return false;
            }
            i++;
        }
    }

    public int get(int i) {
        return this.bytes[i];
    }

    public byte[] getBytes() {
        byte[] bArr = new byte[this.bytes.length];
        int i = 0;
        while (true) {
            int[] iArr = this.bytes;
            if (i < iArr.length) {
                bArr[i] = (byte) iArr[i];
                i++;
            } else {
                return bArr;
            }
        }
    }

    public String getCharsetName() {
        return this.charsetName;
    }

    public int hashCode() {
        int hashCode = ByteOrderMark.class.hashCode();
        for (int i : this.bytes) {
            hashCode += i;
        }
        return hashCode;
    }

    public int length() {
        return this.bytes.length;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ByteOrderMark.class.getSimpleName());
        sb.append(JsonReaderKt.BEGIN_LIST);
        sb.append(this.charsetName);
        sb.append(RealTimeTextConstants.COLON_SPACE);
        for (int i = 0; i < this.bytes.length; i++) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append("0x");
            sb.append(Integer.toHexString(this.bytes[i] & 255).toUpperCase());
        }
        sb.append(JsonReaderKt.END_LIST);
        return sb.toString();
    }
}
