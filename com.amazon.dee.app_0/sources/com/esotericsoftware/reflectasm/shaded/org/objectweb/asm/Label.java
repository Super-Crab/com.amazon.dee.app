package com.esotericsoftware.reflectasm.shaded.org.objectweb.asm;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes2.dex */
public class Label {
    int a;
    int b;
    int c;
    private int d;
    private int[] e;
    int f;
    int g;
    Frame h;
    Label i;
    public Object info;
    Edge j;
    Label k;

    private void a(int i, int i2) {
        if (this.e == null) {
            this.e = new int[6];
        }
        int i3 = this.d;
        int[] iArr = this.e;
        if (i3 >= iArr.length) {
            int[] iArr2 = new int[iArr.length + 6];
            System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
            this.e = iArr2;
        }
        int[] iArr3 = this.e;
        int i4 = this.d;
        this.d = i4 + 1;
        iArr3[i4] = i;
        int i5 = this.d;
        this.d = i5 + 1;
        iArr3[i5] = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Label a() {
        Frame frame = this.h;
        return frame == null ? this : frame.b;
    }

    void a(long j, int i) {
        int i2 = this.a;
        if ((i2 & 1024) == 0) {
            this.a = i2 | 1024;
            this.e = new int[((i - 1) / 32) + 1];
        }
        int[] iArr = this.e;
        int i3 = (int) (j >>> 32);
        iArr[i3] = ((int) j) | iArr[i3];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x001a, code lost:
        if (r4 != false) goto L6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.MethodWriter r1, com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.ByteVector r2, int r3, boolean r4) {
        /*
            r0 = this;
            int r1 = r0.a
            r1 = r1 & 2
            if (r1 != 0) goto L17
            r1 = -1
            if (r4 == 0) goto L11
            int r3 = (-1) - r3
            int r4 = r2.b
            r0.a(r3, r4)
            goto L1c
        L11:
            int r4 = r2.b
            r0.a(r3, r4)
            goto L20
        L17:
            int r1 = r0.c
            int r1 = r1 - r3
            if (r4 == 0) goto L20
        L1c:
            r2.putInt(r1)
            goto L23
        L20:
            r2.putShort(r1)
        L23:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Label.a(com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.MethodWriter, com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.ByteVector, int, boolean):void");
    }

    boolean a(long j) {
        if ((this.a & 1024) != 0) {
            return (((int) j) & this.e[(int) (j >>> 32)]) != 0;
        }
        return false;
    }

    boolean a(Label label) {
        if ((this.a & 1024) != 0 && (label.a & 1024) != 0) {
            int i = 0;
            while (true) {
                int[] iArr = this.e;
                if (i >= iArr.length) {
                    break;
                } else if ((iArr[i] & label.e[i]) != 0) {
                    return true;
                } else {
                    i++;
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a(MethodWriter methodWriter, int i, byte[] bArr) {
        this.a |= 2;
        this.c = i;
        int i2 = 0;
        boolean z = false;
        while (i2 < this.d) {
            int[] iArr = this.e;
            int i3 = i2 + 1;
            int i4 = iArr[i2];
            int i5 = i3 + 1;
            int i6 = iArr[i3];
            if (i4 >= 0) {
                int i7 = i - i4;
                if (i7 < -32768 || i7 > 32767) {
                    int i8 = i6 - 1;
                    int i9 = bArr[i8] & 255;
                    if (i9 <= 168) {
                        bArr[i8] = (byte) (i9 + 49);
                    } else {
                        bArr[i8] = (byte) (i9 + 20);
                    }
                    z = true;
                }
                bArr[i6] = (byte) (i7 >>> 8);
                bArr[i6 + 1] = (byte) i7;
            } else {
                int i10 = i4 + i + 1;
                int i11 = i6 + 1;
                bArr[i6] = (byte) (i10 >>> 24);
                int i12 = i11 + 1;
                bArr[i11] = (byte) (i10 >>> 16);
                bArr[i12] = (byte) (i10 >>> 8);
                bArr[i12 + 1] = (byte) i10;
            }
            i2 = i5;
        }
        return z;
    }

    /*  JADX ERROR: JadxOverflowException in pass: LoopRegionVisitor
        jadx.core.utils.exceptions.JadxOverflowException: LoopRegionVisitor.assignOnlyInLoop endless recursion
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:56)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:30)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:18)
        */
    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0046  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void b(com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Label r6, long r7, int r9) {
        /*
            r5 = this;
            r0 = r5
        L1:
            if (r0 == 0) goto L5e
            com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Label r1 = r0.k
            r2 = 0
            r0.k = r2
            if (r6 == 0) goto L37
            int r2 = r0.a
            r3 = r2 & 2048(0x800, float:2.87E-42)
            if (r3 == 0) goto L11
            goto L3d
        L11:
            r2 = r2 | 2048(0x800, float:2.87E-42)
            r0.a = r2
            int r2 = r0.a
            r2 = r2 & 256(0x100, float:3.59E-43)
            if (r2 == 0) goto L42
            boolean r2 = r0.a(r6)
            if (r2 != 0) goto L42
            com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Edge r2 = new com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Edge
            r2.<init>()
            int r3 = r0.f
            r2.a = r3
            com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Edge r3 = r6.j
            com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Label r3 = r3.b
            r2.b = r3
            com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Edge r3 = r0.j
            r2.c = r3
            r0.j = r2
            goto L42
        L37:
            boolean r2 = r0.a(r7)
            if (r2 == 0) goto L3f
        L3d:
            r0 = r1
            goto L1
        L3f:
            r0.a(r7, r9)
        L42:
            com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Edge r2 = r0.j
        L44:
            if (r2 == 0) goto L3d
            int r3 = r0.a
            r3 = r3 & 128(0x80, float:1.794E-43)
            if (r3 == 0) goto L52
            com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Edge r3 = r0.j
            com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Edge r3 = r3.c
            if (r2 == r3) goto L5b
        L52:
            com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Label r3 = r2.b
            com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Label r4 = r3.k
            if (r4 != 0) goto L5b
            r3.k = r1
            r1 = r3
        L5b:
            com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Edge r2 = r2.c
            goto L44
        L5e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Label.b(com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Label, long, int):void");
    }

    public int getOffset() {
        if ((this.a & 2) != 0) {
            return this.c;
        }
        throw new IllegalStateException("Label offset position has not been resolved yet");
    }

    public String toString() {
        StringBuffer outline103 = GeneratedOutlineSupport1.outline103("L");
        outline103.append(System.identityHashCode(this));
        return outline103.toString();
    }
}
