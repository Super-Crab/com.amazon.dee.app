package org.bouncycastle.mime;

import java.io.InputStream;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public class LineReader {
    private int lastC = -1;
    private final InputStream src;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LineReader(InputStream inputStream) {
        this.src = inputStream;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0036 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0038  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:8:0x0014 -> B:9:0x001a). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String readLine() throws java.io.IOException {
        /*
            r4 = this;
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
            r0.<init>()
            int r1 = r4.lastC
            r2 = -1
            r3 = 13
            if (r1 == r2) goto L14
            if (r1 != r3) goto L11
            java.lang.String r0 = ""
            return r0
        L11:
            r4.lastC = r2
            goto L1a
        L14:
            java.io.InputStream r1 = r4.src
            int r1 = r1.read()
        L1a:
            r2 = 10
            if (r1 < 0) goto L26
            if (r1 == r3) goto L26
            if (r1 == r2) goto L26
            r0.write(r1)
            goto L14
        L26:
            if (r1 != r3) goto L34
            java.io.InputStream r3 = r4.src
            int r3 = r3.read()
            if (r3 == r2) goto L34
            if (r3 < 0) goto L34
            r4.lastC = r3
        L34:
            if (r1 >= 0) goto L38
            r0 = 0
            return r0
        L38:
            byte[] r0 = r0.toByteArray()
            java.lang.String r0 = org.bouncycastle.util.Strings.fromUTF8ByteArray(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.mime.LineReader.readLine():java.lang.String");
    }
}
