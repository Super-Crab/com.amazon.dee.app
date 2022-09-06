package com.esotericsoftware.reflectasm.shaded.org.objectweb.asm;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes2.dex */
final class Frame {
    static final int[] a;
    Label b;
    int[] c;
    int[] d;
    private int[] e;
    private int[] f;
    private int g;
    private int h;
    private int[] i;

    static {
        int[] iArr = new int[202];
        for (int i = 0; i < iArr.length; i++) {
            iArr[i] = "EFFFFFFFFGGFFFGGFFFEEFGFGFEEEEEEEEEEEEEEEEEEEEDEDEDDDDDCDCDEEEEEEEEEEEEEEEEEEEEBABABBBBDCFFFGGGEDCDCDCDCDCDCDCDCDCDCEEEEDDDDDDDCDCDCEFEFDDEEFFDEDEEEBDDBBDDDDDDCCCCCCCCEFEDDDCDCDEEEEEEEEEEFEEEEEEDDEEDDEE".charAt(i) - 'E';
        }
        a = iArr;
    }

    private int a() {
        int i = this.g;
        if (i > 0) {
            int[] iArr = this.f;
            int i2 = i - 1;
            this.g = i2;
            return iArr[i2];
        }
        Label label = this.b;
        int i3 = label.f - 1;
        label.f = i3;
        return 50331648 | (-i3);
    }

    private int a(int i) {
        int[] iArr = this.e;
        if (iArr == null || i >= iArr.length) {
            return i | 33554432;
        }
        int i2 = iArr[i];
        if (i2 != 0) {
            return i2;
        }
        int i3 = i | 33554432;
        iArr[i] = i3;
        return i3;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x004d A[LOOP:0: B:10:0x0022->B:21:0x004d, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x004c A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int a(com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.ClassWriter r7, int r8) {
        /*
            r6 = this;
            r0 = 24117248(0x1700000, float:4.4081038E-38)
            r1 = 16777222(0x1000006, float:2.3509904E-38)
            if (r8 != r1) goto Lf
            java.lang.String r1 = r7.I
        L9:
            int r7 = r7.c(r1)
            r7 = r7 | r0
            goto L21
        Lf:
            r1 = -1048576(0xfffffffffff00000, float:NaN)
            r1 = r1 & r8
            r2 = 25165824(0x1800000, float:4.7019774E-38)
            if (r1 != r2) goto L50
            com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Item[] r1 = r7.H
            r2 = 1048575(0xfffff, float:1.469367E-39)
            r2 = r2 & r8
            r1 = r1[r2]
            java.lang.String r1 = r1.g
            goto L9
        L21:
            r0 = 0
        L22:
            int r1 = r6.h
            if (r0 >= r1) goto L50
            int[] r1 = r6.i
            r1 = r1[r0]
            r2 = -268435456(0xfffffffff0000000, float:-1.58456325E29)
            r2 = r2 & r1
            r3 = 251658240(0xf000000, float:6.3108872E-30)
            r3 = r3 & r1
            r4 = 33554432(0x2000000, float:9.403955E-38)
            r5 = 8388607(0x7fffff, float:1.1754942E-38)
            if (r3 != r4) goto L3e
            int[] r3 = r6.c
            r1 = r1 & r5
            r1 = r3[r1]
        L3c:
            int r1 = r1 + r2
            goto L4a
        L3e:
            r4 = 50331648(0x3000000, float:3.761582E-37)
            if (r3 != r4) goto L4a
            int[] r3 = r6.d
            int r4 = r3.length
            r1 = r1 & r5
            int r4 = r4 - r1
            r1 = r3[r4]
            goto L3c
        L4a:
            if (r8 != r1) goto L4d
            return r7
        L4d:
            int r0 = r0 + 1
            goto L22
        L50:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Frame.a(com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.ClassWriter, int):int");
    }

    private void a(int i, int i2) {
        if (this.e == null) {
            this.e = new int[10];
        }
        int length = this.e.length;
        if (i >= length) {
            int[] iArr = new int[Math.max(i + 1, length * 2)];
            System.arraycopy(this.e, 0, iArr, 0, length);
            this.e = iArr;
        }
        this.e[i] = i2;
    }

    private void a(ClassWriter classWriter, String str) {
        int b = b(classWriter, str);
        if (b != 0) {
            b(b);
            if (b != 16777220 && b != 16777219) {
                return;
            }
            b(16777216);
        }
    }

    private void a(String str) {
        char charAt = str.charAt(0);
        if (charAt == '(') {
            c((Type.getArgumentsAndReturnSizes(str) >> 2) - 1);
        } else if (charAt == 'J' || charAt == 'D') {
            c(2);
        } else {
            c(1);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x0058, code lost:
        if ((r12 & (-268435456)) == 0) goto L24;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static boolean a(com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.ClassWriter r11, int r12, int[] r13, int r14) {
        /*
            r0 = r13[r14]
            r1 = 0
            if (r0 != r12) goto L6
            return r1
        L6:
            r2 = 268435455(0xfffffff, float:2.5243547E-29)
            r2 = r2 & r12
            r3 = 16777221(0x1000005, float:2.35099E-38)
            if (r2 != r3) goto L13
            if (r0 != r3) goto L12
            return r1
        L12:
            r12 = r3
        L13:
            r2 = 1
            if (r0 != 0) goto L19
            r13[r14] = r12
            return r2
        L19:
            r4 = 267386880(0xff00000, float:2.3665827E-29)
            r5 = r0 & r4
            r6 = 16777216(0x1000000, float:2.3509887E-38)
            r7 = -268435456(0xfffffffff0000000, float:-1.58456325E29)
            r8 = 24117248(0x1700000, float:4.4081038E-38)
            if (r5 == r8) goto L36
            r9 = r0 & r7
            if (r9 == 0) goto L2a
            goto L36
        L2a:
            if (r0 != r3) goto L60
            r11 = r12 & r4
            if (r11 == r8) goto L34
            r11 = r12 & r7
            if (r11 == 0) goto L60
        L34:
            r6 = r12
            goto L60
        L36:
            if (r12 != r3) goto L39
            return r1
        L39:
            r3 = -1048576(0xfffffffffff00000, float:NaN)
            r9 = r12 & r3
            r3 = r3 & r0
            java.lang.String r10 = "java/lang/Object"
            if (r9 != r3) goto L53
            if (r5 != r8) goto L5a
            r3 = r12 & r7
            r3 = r3 | r8
            r4 = 1048575(0xfffff, float:1.469367E-39)
            r12 = r12 & r4
            r4 = r4 & r0
            int r11 = r11.a(r12, r4)
            r6 = r3 | r11
            goto L60
        L53:
            r3 = r12 & r4
            if (r3 == r8) goto L5a
            r12 = r12 & r7
            if (r12 == 0) goto L60
        L5a:
            int r11 = r11.c(r10)
            r6 = r11 | r8
        L60:
            if (r0 == r6) goto L65
            r13[r14] = r6
            return r2
        L65:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Frame.a(com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.ClassWriter, int, int[], int):boolean");
    }

    private static int b(ClassWriter classWriter, String str) {
        int indexOf = str.charAt(0) == '(' ? str.indexOf(41) + 1 : 0;
        char charAt = str.charAt(indexOf);
        int i = 16777218;
        if (charAt != 'F') {
            if (charAt == 'L') {
                return classWriter.c(GeneratedOutlineSupport1.outline50(str, -1, indexOf + 1)) | 24117248;
            }
            if (charAt != 'S') {
                if (charAt == 'V') {
                    return 0;
                }
                if (charAt != 'Z' && charAt != 'I') {
                    if (charAt == 'J') {
                        return 16777220;
                    }
                    switch (charAt) {
                        case 'B':
                        case 'C':
                            break;
                        case 'D':
                            return 16777219;
                        default:
                            int i2 = indexOf + 1;
                            while (str.charAt(i2) == '[') {
                                i2++;
                            }
                            char charAt2 = str.charAt(i2);
                            if (charAt2 != 'F') {
                                if (charAt2 == 'S') {
                                    i = 16777228;
                                } else if (charAt2 == 'Z') {
                                    i = 16777225;
                                } else if (charAt2 == 'I') {
                                    i = 16777217;
                                } else if (charAt2 != 'J') {
                                    switch (charAt2) {
                                        case 'B':
                                            i = 16777226;
                                            break;
                                        case 'C':
                                            i = 16777227;
                                            break;
                                        case 'D':
                                            i = 16777219;
                                            break;
                                        default:
                                            i = classWriter.c(GeneratedOutlineSupport1.outline50(str, -1, i2 + 1)) | 24117248;
                                            break;
                                    }
                                } else {
                                    i = 16777220;
                                }
                            }
                            return ((i2 - indexOf) << 28) | i;
                    }
                }
            }
            return 16777217;
        }
        return 16777218;
    }

    private void b(int i) {
        if (this.f == null) {
            this.f = new int[10];
        }
        int length = this.f.length;
        int i2 = this.g;
        if (i2 >= length) {
            int[] iArr = new int[Math.max(i2 + 1, length * 2)];
            System.arraycopy(this.f, 0, iArr, 0, length);
            this.f = iArr;
        }
        int[] iArr2 = this.f;
        int i3 = this.g;
        this.g = i3 + 1;
        iArr2[i3] = i;
        Label label = this.b;
        int i4 = label.f + this.g;
        if (i4 > label.g) {
            label.g = i4;
        }
    }

    private void c(int i) {
        int i2 = this.g;
        if (i2 >= i) {
            this.g = i2 - i;
            return;
        }
        this.b.f -= i - i2;
        this.g = 0;
    }

    private void d(int i) {
        if (this.i == null) {
            this.i = new int[2];
        }
        int length = this.i.length;
        int i2 = this.h;
        if (i2 >= length) {
            int[] iArr = new int[Math.max(i2 + 1, length * 2)];
            System.arraycopy(this.i, 0, iArr, 0, length);
            this.i = iArr;
        }
        int[] iArr2 = this.i;
        int i3 = this.h;
        this.h = i3 + 1;
        iArr2[i3] = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x004b, code lost:
        if (r1.charAt(0) == '[') goto L13;
     */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0227  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x022b  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x022f  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0236  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01fa  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0203  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(int r17, int r18, com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.ClassWriter r19, com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Item r20) {
        /*
            Method dump skipped, instructions count: 940
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Frame.a(int, int, com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.ClassWriter, com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Item):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(ClassWriter classWriter, int i, Type[] typeArr, int i2) {
        this.c = new int[i2];
        this.d = new int[0];
        int i3 = 1;
        if ((i & 8) != 0) {
            i3 = 0;
        } else if ((i & 262144) == 0) {
            this.c[0] = 24117248 | classWriter.c(classWriter.I);
        } else {
            this.c[0] = 16777222;
        }
        for (Type type : typeArr) {
            int b = b(classWriter, type.getDescriptor());
            int i4 = i3 + 1;
            this.c[i3] = b;
            if (b == 16777220 || b == 16777219) {
                i3 = i4 + 1;
                this.c[i4] = 16777216;
            } else {
                i3 = i4;
            }
        }
        while (i3 < i2) {
            this.c[i3] = 16777216;
            i3++;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x010a A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean a(com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.ClassWriter r19, com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Frame r20, int r21) {
        /*
            Method dump skipped, instructions count: 279
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Frame.a(com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.ClassWriter, com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Frame, int):boolean");
    }
}
