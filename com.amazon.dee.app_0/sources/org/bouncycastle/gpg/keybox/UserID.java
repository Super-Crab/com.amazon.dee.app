package org.bouncycastle.gpg.keybox;

import java.io.IOException;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;
/* loaded from: classes4.dex */
public class UserID {
    private final long lengthOfUserId;
    private final long offsetToUserId;
    private final int reserved;
    private final byte[] userID;
    private final int userIdFlags;
    private final int validity;

    private UserID(long j, long j2, int i, int i2, int i3, byte[] bArr) {
        this.offsetToUserId = j;
        this.lengthOfUserId = j2;
        this.userIdFlags = i;
        this.validity = i2;
        this.reserved = i3;
        this.userID = bArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static UserID getInstance(Object obj, int i) throws IOException {
        if (obj instanceof UserID) {
            return (UserID) obj;
        }
        KeyBoxByteBuffer wrap = KeyBoxByteBuffer.wrap(obj);
        long u32 = wrap.u32();
        long u322 = wrap.u32();
        long j = i + u32;
        return new UserID(u32, u322, wrap.u16(), wrap.u8(), wrap.u8(), wrap.rangeOf((int) j, (int) (j + u322)));
    }

    public long getLengthOfUserId() {
        return this.lengthOfUserId;
    }

    public long getOffsetToUserId() {
        return this.offsetToUserId;
    }

    public int getReserved() {
        return this.reserved;
    }

    public byte[] getUserID() {
        return Arrays.clone(this.userID);
    }

    public String getUserIDAsString() {
        return Strings.fromUTF8ByteArray(this.userID);
    }

    public long getUserIdFlags() {
        return this.userIdFlags;
    }

    public int getValidity() {
        return this.validity;
    }
}
