package org.bouncycastle.bcpg;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
public class SignatureSubpacketInputStream extends InputStream implements SignatureSubpacketTags {
    private final InputStream in;
    private final int limit;

    public SignatureSubpacketInputStream(InputStream inputStream) {
        this(inputStream, StreamUtil.findLimit(inputStream));
    }

    public SignatureSubpacketInputStream(InputStream inputStream, int i) {
        this.in = inputStream;
        this.limit = i;
    }

    private byte[] checkData(byte[] bArr, int i, int i2, String str) throws EOFException {
        if (i2 == i) {
            return Arrays.copyOfRange(bArr, 0, i);
        }
        throw new EOFException(GeneratedOutlineSupport1.outline75("truncated ", str, " subpacket data."));
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        return this.in.available();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        return this.in.read();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x012e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public org.bouncycastle.bcpg.SignatureSubpacket readPacket() throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 350
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.bcpg.SignatureSubpacketInputStream.readPacket():org.bouncycastle.bcpg.SignatureSubpacket");
    }
}
