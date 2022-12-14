package org.bouncycastle.bcpg;

import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
public class UserAttributeSubpacket {
    protected byte[] data;
    private boolean forceLongLength;
    int type;

    /* JADX INFO: Access modifiers changed from: protected */
    public UserAttributeSubpacket(int i, boolean z, byte[] bArr) {
        this.type = i;
        this.forceLongLength = z;
        this.data = bArr;
    }

    protected UserAttributeSubpacket(int i, byte[] bArr) {
        this(i, false, bArr);
    }

    public void encode(OutputStream outputStream) throws IOException {
        byte b;
        int length = this.data.length + 1;
        if (length >= 192 || this.forceLongLength) {
            if (length > 8383 || this.forceLongLength) {
                outputStream.write(255);
                outputStream.write((byte) (length >> 24));
                outputStream.write((byte) (length >> 16));
                b = (byte) (length >> 8);
            } else {
                length -= 192;
                b = (byte) (((length >> 8) & 255) + 192);
            }
            outputStream.write(b);
        }
        outputStream.write((byte) length);
        outputStream.write(this.type);
        outputStream.write(this.data);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof UserAttributeSubpacket)) {
            return false;
        }
        UserAttributeSubpacket userAttributeSubpacket = (UserAttributeSubpacket) obj;
        return this.type == userAttributeSubpacket.type && Arrays.areEqual(this.data, userAttributeSubpacket.data);
    }

    public byte[] getData() {
        return this.data;
    }

    public int getType() {
        return this.type;
    }

    public int hashCode() {
        return this.type ^ Arrays.hashCode(this.data);
    }
}
