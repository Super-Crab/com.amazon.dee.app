package org.bouncycastle.gpg.keybox;

import java.io.IOException;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
public class KeyInformation {
    private final byte[] filler;
    private final byte[] fingerprint;
    private final int keyFlags;
    private final byte[] keyID;
    private final long offsetToKeyID;

    KeyInformation(byte[] bArr, long j, int i, byte[] bArr2, byte[] bArr3) {
        this.fingerprint = Arrays.clone(bArr);
        this.offsetToKeyID = j;
        this.keyFlags = i;
        this.filler = Arrays.clone(bArr2);
        this.keyID = Arrays.clone(bArr3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static KeyInformation getInstance(Object obj, int i, int i2) throws IOException {
        byte[] bArr;
        if (obj instanceof KeyInformation) {
            return (KeyInformation) obj;
        }
        KeyBoxByteBuffer wrap = KeyBoxByteBuffer.wrap(obj);
        int position = wrap.position();
        byte[] bN = wrap.bN(20);
        long u32 = wrap.u32();
        if (u32 > 0) {
            long j = i2 + u32;
            bArr = wrap.rangeOf((int) j, (int) (j + 8));
        } else {
            bArr = null;
        }
        int u16 = wrap.u16();
        wrap.u16();
        return new KeyInformation(bN, u32, u16, wrap.bN(i - (wrap.position() - position)), bArr);
    }

    public byte[] getFiller() {
        return Arrays.clone(this.filler);
    }

    public byte[] getFingerprint() {
        return Arrays.clone(this.fingerprint);
    }

    public int getKeyFlags() {
        return this.keyFlags;
    }

    public byte[] getKeyID() {
        return Arrays.clone(this.keyID);
    }
}
