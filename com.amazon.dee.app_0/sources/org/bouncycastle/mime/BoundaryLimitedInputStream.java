package org.bouncycastle.mime;

import java.io.InputStream;
import org.bouncycastle.util.Strings;
/* loaded from: classes5.dex */
public class BoundaryLimitedInputStream extends InputStream {
    private final byte[] boundary;
    private final byte[] buf;
    private int bufOff;
    private int lastI;
    private final InputStream src;
    private int index = 0;
    private boolean ended = false;

    public BoundaryLimitedInputStream(InputStream inputStream, String str) {
        this.bufOff = 0;
        this.src = inputStream;
        this.boundary = Strings.toByteArray(str);
        this.buf = new byte[str.length() + 3];
        this.bufOff = 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00a8  */
    @Override // java.io.InputStream
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int read() throws java.io.IOException {
        /*
            r8 = this;
            boolean r0 = r8.ended
            r1 = -1
            if (r0 == 0) goto L6
            return r1
        L6:
            int r0 = r8.index
            int r2 = r8.bufOff
            r3 = 0
            if (r0 >= r2) goto L21
            byte[] r4 = r8.buf
            int r5 = r0 + 1
            r8.index = r5
            r0 = r4[r0]
            r0 = r0 & 255(0xff, float:3.57E-43)
            int r4 = r8.index
            if (r4 >= r2) goto L1c
            return r0
        L1c:
            r8.bufOff = r3
            r8.index = r3
            goto L27
        L21:
            java.io.InputStream r0 = r8.src
            int r0 = r0.read()
        L27:
            r8.lastI = r0
            if (r0 >= 0) goto L2c
            return r1
        L2c:
            r2 = 13
            r4 = 10
            if (r0 == r2) goto L34
            if (r0 != r4) goto Lb5
        L34:
            r8.index = r3
            if (r0 != r2) goto L4a
            java.io.InputStream r2 = r8.src
            int r2 = r2.read()
            if (r2 != r4) goto L50
            byte[] r2 = r8.buf
            int r3 = r8.bufOff
            int r5 = r3 + 1
            r8.bufOff = r5
            r2[r3] = r4
        L4a:
            java.io.InputStream r2 = r8.src
            int r2 = r2.read()
        L50:
            r3 = 45
            if (r2 != r3) goto L64
            byte[] r2 = r8.buf
            int r4 = r8.bufOff
            int r5 = r4 + 1
            r8.bufOff = r5
            r2[r4] = r3
            java.io.InputStream r2 = r8.src
            int r2 = r2.read()
        L64:
            if (r2 != r3) goto La8
            byte[] r2 = r8.buf
            int r4 = r8.bufOff
            int r5 = r4 + 1
            r8.bufOff = r5
            r2[r4] = r3
            int r2 = r8.bufOff
        L72:
            int r3 = r8.bufOff
            int r3 = r3 - r2
            byte[] r4 = r8.boundary
            int r4 = r4.length
            r5 = 1
            if (r3 == r4) goto L9d
            java.io.InputStream r3 = r8.src
            int r3 = r3.read()
            if (r3 < 0) goto L9d
            byte[] r4 = r8.buf
            int r6 = r8.bufOff
            byte r3 = (byte) r3
            r4[r6] = r3
            r3 = r4[r6]
            byte[] r4 = r8.boundary
            int r7 = r6 - r2
            r4 = r4[r7]
            if (r3 == r4) goto L98
            int r6 = r6 + r5
            r8.bufOff = r6
            goto L9d
        L98:
            int r6 = r6 + 1
            r8.bufOff = r6
            goto L72
        L9d:
            int r3 = r8.bufOff
            int r3 = r3 - r2
            byte[] r2 = r8.boundary
            int r2 = r2.length
            if (r3 != r2) goto Lb5
            r8.ended = r5
            return r1
        La8:
            if (r2 < 0) goto Lb5
            byte[] r1 = r8.buf
            int r3 = r8.bufOff
            int r4 = r3 + 1
            r8.bufOff = r4
            byte r2 = (byte) r2
            r1[r3] = r2
        Lb5:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.mime.BoundaryLimitedInputStream.read():int");
    }
}
