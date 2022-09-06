package com.drew.metadata.mp4.boxes;
/* loaded from: classes2.dex */
public class Box {
    public long size;
    public String type;
    public String usertype;

    /* JADX WARN: Removed duplicated region for block: B:11:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:13:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public Box(com.drew.lang.SequentialReader r5) throws java.io.IOException {
        /*
            r4 = this;
            r4.<init>()
            long r0 = r5.getUInt32()
            r4.size = r0
            r0 = 4
            java.lang.String r0 = r5.getString(r0)
            r4.type = r0
            long r0 = r4.size
            r2 = 1
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 != 0) goto L1f
            long r0 = r5.getInt64()
        L1c:
            r4.size = r0
            goto L28
        L1f:
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 != 0) goto L28
            r0 = -1
            goto L1c
        L28:
            java.lang.String r0 = r4.type
            java.lang.String r1 = "uuid"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L3b
            r0 = 16
            java.lang.String r5 = r5.getString(r0)
            r4.usertype = r5
        L3b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.drew.metadata.mp4.boxes.Box.<init>(com.drew.lang.SequentialReader):void");
    }

    public Box(Box box) {
        this.size = box.size;
        this.type = box.type;
        this.usertype = box.usertype;
    }
}
