package com.esotericsoftware.reflectasm.shaded.org.objectweb.asm;

import amazon.communication.connection.Channels;
import com.amazon.identity.auth.device.datastore.DatabaseHelper;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.commons.net.telnet.TelnetCommand;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;
import org.bouncycastle.tls.CipherSuite;
/* loaded from: classes2.dex */
class MethodWriter extends MethodVisitor {
    private int A;
    private Handler B;
    private Handler C;
    private int D;
    private ByteVector E;
    private int F;
    private ByteVector G;
    private int H;
    private ByteVector I;
    private Attribute J;
    private boolean K;
    private int L;
    private final int M;
    private Label N;
    private Label O;
    private Label P;
    private int Q;
    private int R;
    private int S;
    private int T;
    final ClassWriter b;
    private int c;
    private final int d;
    private final int e;
    private final String f;
    String g;
    int h;
    int i;
    int j;
    int[] k;
    private ByteVector l;
    private AnnotationWriter m;
    private AnnotationWriter n;
    private AnnotationWriter[] o;
    private AnnotationWriter[] p;
    private Attribute q;
    private ByteVector r;
    private int s;
    private int t;
    private int u;
    private ByteVector v;
    private int w;
    private int[] x;
    private int y;
    private int[] z;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MethodWriter(ClassWriter classWriter, int i, String str, String str2, String str3, String[] strArr, boolean z, boolean z2) {
        super(262144);
        this.r = new ByteVector();
        if (classWriter.D == null) {
            classWriter.D = this;
        } else {
            classWriter.E.mv = this;
        }
        classWriter.E = this;
        this.b = classWriter;
        this.c = i;
        this.d = classWriter.newUTF8(str);
        this.e = classWriter.newUTF8(str2);
        this.f = str2;
        this.g = str3;
        int i2 = 0;
        if (strArr != null && strArr.length > 0) {
            this.j = strArr.length;
            this.k = new int[this.j];
            for (int i3 = 0; i3 < this.j; i3++) {
                this.k[i3] = classWriter.newClass(strArr[i3]);
            }
        }
        this.M = !z2 ? z ? 1 : 2 : i2;
        if (z || z2) {
            if (z2 && "<init>".equals(str)) {
                this.c |= 262144;
            }
            int argumentsAndReturnSizes = Type.getArgumentsAndReturnSizes(this.f) >> 2;
            argumentsAndReturnSizes = (i & 8) != 0 ? argumentsAndReturnSizes - 1 : argumentsAndReturnSizes;
            this.t = argumentsAndReturnSizes;
            this.T = argumentsAndReturnSizes;
            this.N = new Label();
            Label label = this.N;
            label.a |= 8;
            visitLabel(label);
        }
    }

    static int a(byte[] bArr, int i) {
        return (bArr[i + 3] & 255) | ((bArr[i] & 255) << 24) | ((bArr[i + 1] & 255) << 16) | ((bArr[i + 2] & 255) << 8);
    }

    static int a(int[] iArr, int[] iArr2, int i, int i2) {
        int i3 = i2 - i;
        for (int i4 = 0; i4 < iArr.length; i4++) {
            if (i < iArr[i4] && iArr[i4] <= i2) {
                i3 += iArr2[i4];
            } else if (i2 < iArr[i4] && iArr[i4] <= i) {
                i3 -= iArr2[i4];
            }
        }
        return i3;
    }

    private void a(int i, int i2) {
        char c;
        ByteVector putByte;
        int newClass;
        while (i < i2) {
            int i3 = this.z[i];
            int i4 = (-268435456) & i3;
            if (i4 == 0) {
                int i5 = i3 & Channels.CHANNEL_FOR_ECHO_TEST;
                int i6 = i3 & 267386880;
                if (i6 == 24117248) {
                    putByte = this.v.putByte(7);
                    ClassWriter classWriter = this.b;
                    newClass = classWriter.newClass(classWriter.H[i5].g);
                } else if (i6 != 25165824) {
                    this.v.putByte(i5);
                    i++;
                } else {
                    putByte = this.v.putByte(8);
                    newClass = this.b.H[i5].c;
                }
            } else {
                StringBuffer stringBuffer = new StringBuffer();
                int i7 = i4 >> 28;
                while (true) {
                    int i8 = i7 - 1;
                    if (i7 > 0) {
                        stringBuffer.append(JsonReaderKt.BEGIN_LIST);
                        i7 = i8;
                    } else {
                        if ((i3 & 267386880) == 24117248) {
                            stringBuffer.append(Matrix.MATRIX_TYPE_RANDOM_LT);
                            stringBuffer.append(this.b.H[i3 & Channels.CHANNEL_FOR_ECHO_TEST].g);
                            c = ';';
                        } else {
                            int i9 = i3 & 15;
                            if (i9 == 1) {
                                c = 'I';
                            } else if (i9 == 2) {
                                c = 'F';
                            } else if (i9 != 3) {
                                switch (i9) {
                                    case 9:
                                        c = Matrix.MATRIX_TYPE_ZERO;
                                        break;
                                    case 10:
                                        c = 'B';
                                        break;
                                    case 11:
                                        c = 'C';
                                        break;
                                    case 12:
                                        c = 'S';
                                        break;
                                    default:
                                        c = 'J';
                                        break;
                                }
                            } else {
                                c = 'D';
                            }
                        }
                        stringBuffer.append(c);
                        putByte = this.v.putByte(7);
                        newClass = this.b.newClass(stringBuffer.toString());
                    }
                }
            }
            putByte.putShort(newClass);
            i++;
        }
    }

    private void a(int i, int i2, int i3) {
        int i4 = i2 + 3 + i3;
        int[] iArr = this.z;
        if (iArr == null || iArr.length < i4) {
            this.z = new int[i4];
        }
        int[] iArr2 = this.z;
        iArr2[0] = i;
        iArr2[1] = i2;
        iArr2[2] = i3;
        this.y = 3;
    }

    private void a(int i, Label label) {
        Edge edge = new Edge();
        edge.a = i;
        edge.b = label;
        Label label2 = this.P;
        edge.c = label2.j;
        label2.j = edge;
    }

    private void a(Label label, Label[] labelArr) {
        Label label2 = this.P;
        if (label2 != null) {
            if (this.M == 0) {
                label2.h.a(171, 0, (ClassWriter) null, (Item) null);
                a(0, label);
                label.a().a |= 16;
                for (int i = 0; i < labelArr.length; i++) {
                    a(0, labelArr[i]);
                    labelArr[i].a().a |= 16;
                }
            } else {
                this.Q--;
                a(this.Q, label);
                for (Label label3 : labelArr) {
                    a(this.Q, label3);
                }
            }
            e();
        }
    }

    private void a(Object obj) {
        ByteVector putByte;
        int i;
        if (obj instanceof String) {
            putByte = this.v.putByte(7);
            i = this.b.newClass((String) obj);
        } else if (obj instanceof Integer) {
            this.v.putByte(((Integer) obj).intValue());
            return;
        } else {
            putByte = this.v.putByte(8);
            i = ((Label) obj).c;
        }
        putByte.putShort(i);
    }

    static void a(byte[] bArr, int i, int i2) {
        bArr[i] = (byte) (i2 >>> 8);
        bArr[i + 1] = (byte) i2;
    }

    static void a(int[] iArr, int[] iArr2, Label label) {
        if ((label.a & 4) == 0) {
            label.c = a(iArr, iArr2, 0, label.c);
            label.a |= 4;
        }
    }

    static short b(byte[] bArr, int i) {
        return (short) ((bArr[i + 1] & 255) | ((bArr[i] & 255) << 8));
    }

    private void b() {
        if (this.x != null) {
            if (this.v == null) {
                this.v = new ByteVector();
            }
            c();
            this.u++;
        }
        this.x = this.z;
        this.z = null;
    }

    private void b(Frame frame) {
        int[] iArr = frame.c;
        int[] iArr2 = frame.d;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i2 < iArr.length) {
            int i5 = iArr[i2];
            i4++;
            if (i5 != 16777216) {
                i3 = i4 + i3;
                i4 = 0;
            }
            if (i5 == 16777220 || i5 == 16777219) {
                i2++;
            }
            i2++;
        }
        int i6 = 0;
        int i7 = 0;
        while (i6 < iArr2.length) {
            int i8 = iArr2[i6];
            i7++;
            if (i8 == 16777220 || i8 == 16777219) {
                i6++;
            }
            i6++;
        }
        a(frame.b.c, i3, i7);
        int i9 = 0;
        while (i3 > 0) {
            int i10 = iArr[i9];
            int[] iArr3 = this.z;
            int i11 = this.y;
            this.y = i11 + 1;
            iArr3[i11] = i10;
            if (i10 == 16777220 || i10 == 16777219) {
                i9++;
            }
            i9++;
            i3--;
        }
        while (i < iArr2.length) {
            int i12 = iArr2[i];
            int[] iArr4 = this.z;
            int i13 = this.y;
            this.y = i13 + 1;
            iArr4[i13] = i12;
            if (i12 == 16777220 || i12 == 16777219) {
                i++;
            }
            i++;
        }
        b();
    }

    static int c(byte[] bArr, int i) {
        return (bArr[i + 1] & 255) | ((bArr[i] & 255) << 8);
    }

    private void c() {
        int i;
        char c;
        ByteVector putByte;
        char c2;
        int[] iArr = this.z;
        int i2 = iArr[1];
        int i3 = iArr[2];
        int i4 = 0;
        if ((this.b.b & 65535) < 50) {
            this.v.putShort(iArr[0]).putShort(i2);
            int i5 = i2 + 3;
            a(3, i5);
            this.v.putShort(i3);
            a(i5, i3 + i5);
            return;
        }
        int[] iArr2 = this.x;
        int i6 = iArr2[1];
        int i7 = this.u == 0 ? iArr[0] : (iArr[0] - iArr2[0]) - 1;
        if (i3 == 0) {
            int i8 = i2 - i6;
            switch (i8) {
                case -3:
                case -2:
                case -1:
                    i6 = i2;
                    c2 = 248;
                    break;
                case 0:
                    if (i7 >= 64) {
                        c2 = 251;
                        break;
                    } else {
                        c2 = 0;
                        break;
                    }
                case 1:
                case 2:
                case 3:
                    c2 = 252;
                    break;
                default:
                    c2 = 255;
                    break;
            }
            char c3 = c2;
            i = i8;
            c = c3;
        } else if (i2 == i6 && i3 == 1) {
            c = i7 < 63 ? '@' : (char) 247;
            i = 0;
        } else {
            i = 0;
            c = 255;
        }
        if (c != 255) {
            int i9 = 3;
            while (true) {
                if (i4 < i6) {
                    if (this.z[i9] != this.x[i9]) {
                        c = 255;
                    } else {
                        i9++;
                        i4++;
                    }
                }
            }
        }
        if (c == 0) {
            this.v.putByte(i7);
            return;
        }
        if (c == '@') {
            this.v.putByte(i7 + 64);
        } else if (c != 247) {
            if (c == 248) {
                putByte = this.v.putByte(i + 251);
            } else if (c != 251) {
                if (c == 252) {
                    this.v.putByte(i + 251).putShort(i7);
                    a(i6 + 3, i2 + 3);
                    return;
                }
                this.v.putByte(255).putShort(i7).putShort(i2);
                int i10 = i2 + 3;
                a(3, i10);
                this.v.putShort(i3);
                a(i10, i3 + i10);
                return;
            } else {
                putByte = this.v.putByte(251);
            }
            putByte.putShort(i7);
            return;
        } else {
            this.v.putByte(TelnetCommand.EC).putShort(i7);
        }
        a(i2 + 3, i2 + 4);
    }

    private void d() {
        int b;
        int i;
        int b2;
        int i2;
        int i3;
        int a;
        ByteVector byteVector = this.r;
        byte[] bArr = byteVector.a;
        boolean[] zArr = new boolean[byteVector.b];
        int[] iArr = new int[0];
        int[] iArr2 = new int[0];
        int i4 = 3;
        while (true) {
            if (i4 == 3) {
                i4 = 2;
            }
            int[] iArr3 = iArr;
            int[] iArr4 = iArr2;
            int i5 = i4;
            int i6 = 0;
            while (true) {
                char c = 132;
                if (i6 < bArr.length) {
                    int i7 = bArr[i6] & 255;
                    switch (ClassWriter.a[i7]) {
                        case 0:
                        case 4:
                            i6++;
                            i2 = 0;
                            break;
                        case 1:
                        case 3:
                        case 11:
                            i6 += 2;
                            i2 = 0;
                            break;
                        case 2:
                        case 5:
                        case 6:
                        case 12:
                        case 13:
                            i6 += 3;
                            i2 = 0;
                            break;
                        case 7:
                        case 8:
                        case 10:
                            i6 += 5;
                            i2 = 0;
                            break;
                        case 9:
                            if (i7 > 201) {
                                i7 = i7 < 218 ? i7 - 49 : i7 - 20;
                                b2 = c(bArr, i6 + 1);
                            } else {
                                b2 = b(bArr, i6 + 1);
                            }
                            int a2 = a(iArr4, iArr3, i6, b2 + i6);
                            if ((a2 < -32768 || a2 > 32767) && !zArr[i6]) {
                                i2 = (i7 == 167 || i7 == 168) ? 2 : 5;
                                zArr[i6] = true;
                            } else {
                                i2 = 0;
                            }
                            i6 += 3;
                            break;
                        case 14:
                            if (i5 == 1) {
                                i2 = -(a(iArr4, iArr3, 0, i6) & 3);
                            } else if (!zArr[i6]) {
                                i2 = i6 & 3;
                                zArr[i6] = true;
                            } else {
                                i2 = 0;
                            }
                            i3 = (i6 + 4) - (i6 & 3);
                            a = (((a(bArr, i3 + 8) - a(bArr, i3 + 4)) + 1) * 4) + 12;
                            i6 = a + i3;
                            break;
                        case 15:
                            if (i5 == 1) {
                                i2 = -(a(iArr4, iArr3, 0, i6) & 3);
                            } else if (!zArr[i6]) {
                                i2 = i6 & 3;
                                zArr[i6] = true;
                            } else {
                                i2 = 0;
                            }
                            i3 = (i6 + 4) - (i6 & 3);
                            a = (a(bArr, i3 + 4) * 8) + 8;
                            i6 = a + i3;
                            break;
                        case 17:
                            if ((bArr[i6 + 1] & 255) == 132) {
                                i6 += 6;
                                i2 = 0;
                                break;
                            }
                        case 16:
                        default:
                            i6 += 4;
                            i2 = 0;
                            break;
                    }
                    if (i2 != 0) {
                        int[] iArr5 = new int[iArr4.length + 1];
                        int[] iArr6 = new int[iArr3.length + 1];
                        System.arraycopy(iArr4, 0, iArr5, 0, iArr4.length);
                        System.arraycopy(iArr3, 0, iArr6, 0, iArr3.length);
                        iArr5[iArr4.length] = i6;
                        iArr6[iArr3.length] = i2;
                        if (i2 > 0) {
                            i5 = 3;
                        }
                        iArr4 = iArr5;
                        iArr3 = iArr6;
                    }
                } else {
                    if (i5 < 3) {
                        i5--;
                    }
                    i4 = i5;
                    if (i4 == 0) {
                        ByteVector byteVector2 = new ByteVector(this.r.b);
                        int i8 = 0;
                        while (i8 < this.r.b) {
                            int i9 = bArr[i8] & 255;
                            switch (ClassWriter.a[i9]) {
                                case 0:
                                case 4:
                                    byteVector2.putByte(i9);
                                    i8++;
                                    break;
                                case 1:
                                case 3:
                                case 11:
                                    byteVector2.putByteArray(bArr, i8, 2);
                                    i8 += 2;
                                    break;
                                case 2:
                                case 5:
                                case 6:
                                case 12:
                                case 13:
                                    byteVector2.putByteArray(bArr, i8, 3);
                                    i8 += 3;
                                    break;
                                case 7:
                                case 8:
                                    byteVector2.putByteArray(bArr, i8, 5);
                                    i8 += 5;
                                    break;
                                case 9:
                                    if (i9 > 201) {
                                        i9 = i9 < 218 ? i9 - 49 : i9 - 20;
                                        b = c(bArr, i8 + 1);
                                    } else {
                                        b = b(bArr, i8 + 1);
                                    }
                                    int a3 = a(iArr4, iArr3, i8, b + i8);
                                    if (zArr[i8]) {
                                        if (i9 == 167) {
                                            byteVector2.putByte(200);
                                        } else if (i9 == 168) {
                                            byteVector2.putByte(201);
                                        } else {
                                            byteVector2.putByte(i9 <= 166 ? ((i9 + 1) ^ 1) - 1 : i9 ^ 1);
                                            byteVector2.putShort(8);
                                            byteVector2.putByte(200);
                                            a3 -= 3;
                                        }
                                        byteVector2.putInt(a3);
                                    } else {
                                        byteVector2.putByte(i9);
                                        byteVector2.putShort(a3);
                                    }
                                    i8 += 3;
                                    break;
                                case 10:
                                    int a4 = a(iArr4, iArr3, i8, a(bArr, i8 + 1) + i8);
                                    byteVector2.putByte(i9);
                                    byteVector2.putInt(a4);
                                    i8 += 5;
                                    break;
                                case 14:
                                    int i10 = (i8 + 4) - (i8 & 3);
                                    byteVector2.putByte(170);
                                    byteVector2.putByteArray(null, 0, (4 - (byteVector2.b % 4)) % 4);
                                    int i11 = i10 + 4;
                                    byteVector2.putInt(a(iArr4, iArr3, i8, a(bArr, i10) + i8));
                                    int a5 = a(bArr, i11);
                                    int i12 = i11 + 4;
                                    byteVector2.putInt(a5);
                                    i = i12 + 4;
                                    byteVector2.putInt(a(bArr, i - 4));
                                    for (int a6 = (a(bArr, i12) - a5) + 1; a6 > 0; a6--) {
                                        i += 4;
                                        byteVector2.putInt(a(iArr4, iArr3, i8, a(bArr, i) + i8));
                                    }
                                    i8 = i;
                                    break;
                                case 15:
                                    int i13 = (i8 + 4) - (i8 & 3);
                                    byteVector2.putByte(171);
                                    byteVector2.putByteArray(null, 0, (4 - (byteVector2.b % 4)) % 4);
                                    int i14 = i13 + 4;
                                    byteVector2.putInt(a(iArr4, iArr3, i8, a(bArr, i13) + i8));
                                    int a7 = a(bArr, i14);
                                    i = i14 + 4;
                                    byteVector2.putInt(a7);
                                    while (a7 > 0) {
                                        byteVector2.putInt(a(bArr, i));
                                        int i15 = i + 4;
                                        i = i15 + 4;
                                        byteVector2.putInt(a(iArr4, iArr3, i8, a(bArr, i15) + i8));
                                        a7--;
                                    }
                                    i8 = i;
                                    break;
                                case 17:
                                    if ((bArr[i8 + 1] & 255) == c) {
                                        byteVector2.putByteArray(bArr, i8, 6);
                                        i8 += 6;
                                        break;
                                    }
                                case 16:
                                default:
                                    byteVector2.putByteArray(bArr, i8, 4);
                                    i8 += 4;
                                    break;
                            }
                            c = 132;
                        }
                        if (this.u > 0) {
                            if (this.M == 0) {
                                this.u = 0;
                                this.v = null;
                                this.x = null;
                                this.z = null;
                                Frame frame = new Frame();
                                frame.b = this.N;
                                frame.a(this.b, this.c, Type.getArgumentTypes(this.f), this.t);
                                b(frame);
                                for (Label label = this.N; label != null; label = label.i) {
                                    int i16 = label.c - 3;
                                    if ((label.a & 32) != 0 || (i16 >= 0 && zArr[i16])) {
                                        a(iArr4, iArr3, label);
                                        b(label.h);
                                    }
                                }
                            } else {
                                this.b.L = true;
                            }
                        }
                        for (Handler handler = this.B; handler != null; handler = handler.f) {
                            a(iArr4, iArr3, handler.a);
                            a(iArr4, iArr3, handler.b);
                            a(iArr4, iArr3, handler.c);
                        }
                        int i17 = 0;
                        while (i17 < 2) {
                            ByteVector byteVector3 = i17 == 0 ? this.E : this.G;
                            if (byteVector3 != null) {
                                byte[] bArr2 = byteVector3.a;
                                for (int i18 = 0; i18 < byteVector3.b; i18 += 10) {
                                    int c2 = c(bArr2, i18);
                                    int a8 = a(iArr4, iArr3, 0, c2);
                                    a(bArr2, i18, a8);
                                    int i19 = i18 + 2;
                                    a(bArr2, i19, a(iArr4, iArr3, 0, c2 + c(bArr2, i19)) - a8);
                                }
                            }
                            i17++;
                        }
                        ByteVector byteVector4 = this.I;
                        if (byteVector4 != null) {
                            byte[] bArr3 = byteVector4.a;
                            for (int i20 = 0; i20 < this.I.b; i20 += 4) {
                                a(bArr3, i20, a(iArr4, iArr3, 0, c(bArr3, i20)));
                            }
                        }
                        for (Attribute attribute = this.J; attribute != null; attribute = attribute.a) {
                            Label[] labels = attribute.getLabels();
                            if (labels != null) {
                                for (int length = labels.length - 1; length >= 0; length--) {
                                    a(iArr4, iArr3, labels[length]);
                                }
                            }
                        }
                        this.r = byteVector2;
                        return;
                    }
                    iArr2 = iArr4;
                    iArr = iArr3;
                }
            }
        }
    }

    private void e() {
        if (this.M == 0) {
            Label label = new Label();
            label.h = new Frame();
            label.h.b = label;
            ByteVector byteVector = this.r;
            label.a(this, byteVector.b, byteVector.a);
            this.O.i = label;
            this.O = label;
        } else {
            this.P.g = this.R;
        }
        this.P = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int a() {
        int i;
        if (this.h != 0) {
            return this.i + 6;
        }
        if (this.K) {
            d();
        }
        int i2 = this.r.b;
        if (i2 <= 0) {
            i = 8;
        } else if (i2 > 65536) {
            throw new RuntimeException("Method code too large!");
        } else {
            this.b.newUTF8(DatabaseHelper.authorizationCode);
            i = GeneratedOutlineSupport1.outline3(this.A, 8, this.r.b + 18, 8);
            if (this.E != null) {
                this.b.newUTF8("LocalVariableTable");
                i += this.E.b + 8;
            }
            if (this.G != null) {
                this.b.newUTF8("LocalVariableTypeTable");
                i += this.G.b + 8;
            }
            if (this.I != null) {
                this.b.newUTF8("LineNumberTable");
                i += this.I.b + 8;
            }
            if (this.v != null) {
                this.b.newUTF8((this.b.b & 65535) >= 50 ? "StackMapTable" : "StackMap");
                i = this.v.b + 8 + i;
            }
            Attribute attribute = this.J;
            if (attribute != null) {
                ClassWriter classWriter = this.b;
                ByteVector byteVector = this.r;
                i += attribute.a(classWriter, byteVector.a, byteVector.b, this.s, this.t);
            }
        }
        if (this.j > 0) {
            this.b.newUTF8("Exceptions");
            i = GeneratedOutlineSupport1.outline3(this.j, 2, 8, i);
        }
        int i3 = this.c;
        if ((i3 & 4096) != 0 && ((65535 & this.b.b) < 49 || (262144 & i3) != 0)) {
            this.b.newUTF8("Synthetic");
            i += 6;
        }
        if ((this.c & 131072) != 0) {
            this.b.newUTF8("Deprecated");
            i += 6;
        }
        if (this.g != null) {
            this.b.newUTF8("Signature");
            this.b.newUTF8(this.g);
            i += 8;
        }
        if (this.l != null) {
            this.b.newUTF8("AnnotationDefault");
            i += this.l.b + 6;
        }
        if (this.m != null) {
            this.b.newUTF8("RuntimeVisibleAnnotations");
            i += this.m.a() + 8;
        }
        if (this.n != null) {
            this.b.newUTF8("RuntimeInvisibleAnnotations");
            i += this.n.a() + 8;
        }
        if (this.o != null) {
            this.b.newUTF8("RuntimeVisibleParameterAnnotations");
            AnnotationWriter[] annotationWriterArr = this.o;
            int length = ((annotationWriterArr.length - this.S) * 2) + 7 + i;
            for (int length2 = annotationWriterArr.length - 1; length2 >= this.S; length2--) {
                AnnotationWriter[] annotationWriterArr2 = this.o;
                length += annotationWriterArr2[length2] == null ? 0 : annotationWriterArr2[length2].a();
            }
            i = length;
        }
        if (this.p != null) {
            this.b.newUTF8("RuntimeInvisibleParameterAnnotations");
            AnnotationWriter[] annotationWriterArr3 = this.p;
            int length3 = ((annotationWriterArr3.length - this.S) * 2) + 7 + i;
            for (int length4 = annotationWriterArr3.length - 1; length4 >= this.S; length4--) {
                AnnotationWriter[] annotationWriterArr4 = this.p;
                length3 += annotationWriterArr4[length4] == null ? 0 : annotationWriterArr4[length4].a();
            }
            i = length3;
        }
        Attribute attribute2 = this.q;
        return attribute2 != null ? i + attribute2.a(this.b, null, 0, -1, -1) : i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(ByteVector byteVector) {
        int i = this.c;
        byteVector.putShort(i & (~(((i & 262144) / 64) | 393216))).putShort(this.d).putShort(this.e);
        int i2 = this.h;
        if (i2 != 0) {
            byteVector.putByteArray(this.b.M.b, i2, this.i);
            return;
        }
        boolean z = true;
        int i3 = this.r.b > 0 ? 1 : 0;
        if (this.j > 0) {
            i3++;
        }
        int i4 = this.c;
        if ((i4 & 4096) != 0 && ((this.b.b & 65535) < 49 || (i4 & 262144) != 0)) {
            i3++;
        }
        if ((this.c & 131072) != 0) {
            i3++;
        }
        if (this.g != null) {
            i3++;
        }
        if (this.l != null) {
            i3++;
        }
        if (this.m != null) {
            i3++;
        }
        if (this.n != null) {
            i3++;
        }
        if (this.o != null) {
            i3++;
        }
        if (this.p != null) {
            i3++;
        }
        Attribute attribute = this.q;
        if (attribute != null) {
            i3 += attribute.a();
        }
        byteVector.putShort(i3);
        int i5 = this.r.b;
        if (i5 > 0) {
            int i6 = (this.A * 8) + i5 + 12;
            ByteVector byteVector2 = this.E;
            if (byteVector2 != null) {
                i6 += byteVector2.b + 8;
            }
            ByteVector byteVector3 = this.G;
            if (byteVector3 != null) {
                i6 += byteVector3.b + 8;
            }
            ByteVector byteVector4 = this.I;
            if (byteVector4 != null) {
                i6 += byteVector4.b + 8;
            }
            ByteVector byteVector5 = this.v;
            if (byteVector5 != null) {
                i6 += byteVector5.b + 8;
            }
            Attribute attribute2 = this.J;
            if (attribute2 != null) {
                ClassWriter classWriter = this.b;
                ByteVector byteVector6 = this.r;
                i6 += attribute2.a(classWriter, byteVector6.a, byteVector6.b, this.s, this.t);
            }
            byteVector.putShort(this.b.newUTF8(DatabaseHelper.authorizationCode)).putInt(i6);
            byteVector.putShort(this.s).putShort(this.t);
            ByteVector putInt = byteVector.putInt(this.r.b);
            ByteVector byteVector7 = this.r;
            putInt.putByteArray(byteVector7.a, 0, byteVector7.b);
            byteVector.putShort(this.A);
            if (this.A > 0) {
                for (Handler handler = this.B; handler != null; handler = handler.f) {
                    byteVector.putShort(handler.a.c).putShort(handler.b.c).putShort(handler.c.c).putShort(handler.e);
                }
            }
            int i7 = this.E != null ? 1 : 0;
            if (this.G != null) {
                i7++;
            }
            if (this.I != null) {
                i7++;
            }
            if (this.v != null) {
                i7++;
            }
            Attribute attribute3 = this.J;
            if (attribute3 != null) {
                i7 += attribute3.a();
            }
            byteVector.putShort(i7);
            if (this.E != null) {
                byteVector.putShort(this.b.newUTF8("LocalVariableTable"));
                byteVector.putInt(this.E.b + 2).putShort(this.D);
                ByteVector byteVector8 = this.E;
                byteVector.putByteArray(byteVector8.a, 0, byteVector8.b);
            }
            if (this.G != null) {
                byteVector.putShort(this.b.newUTF8("LocalVariableTypeTable"));
                byteVector.putInt(this.G.b + 2).putShort(this.F);
                ByteVector byteVector9 = this.G;
                byteVector.putByteArray(byteVector9.a, 0, byteVector9.b);
            }
            if (this.I != null) {
                byteVector.putShort(this.b.newUTF8("LineNumberTable"));
                byteVector.putInt(this.I.b + 2).putShort(this.H);
                ByteVector byteVector10 = this.I;
                byteVector.putByteArray(byteVector10.a, 0, byteVector10.b);
            }
            if (this.v != null) {
                if ((this.b.b & 65535) < 50) {
                    z = false;
                }
                byteVector.putShort(this.b.newUTF8(z ? "StackMapTable" : "StackMap"));
                byteVector.putInt(this.v.b + 2).putShort(this.u);
                ByteVector byteVector11 = this.v;
                byteVector.putByteArray(byteVector11.a, 0, byteVector11.b);
            }
            Attribute attribute4 = this.J;
            if (attribute4 != null) {
                ClassWriter classWriter2 = this.b;
                ByteVector byteVector12 = this.r;
                attribute4.a(classWriter2, byteVector12.a, byteVector12.b, this.t, this.s, byteVector);
            }
        }
        if (this.j > 0) {
            byteVector.putShort(this.b.newUTF8("Exceptions")).putInt((this.j * 2) + 2);
            byteVector.putShort(this.j);
            for (int i8 = 0; i8 < this.j; i8++) {
                byteVector.putShort(this.k[i8]);
            }
        }
        int i9 = this.c;
        if ((i9 & 4096) != 0 && ((this.b.b & 65535) < 49 || (i9 & 262144) != 0)) {
            byteVector.putShort(this.b.newUTF8("Synthetic")).putInt(0);
        }
        if ((this.c & 131072) != 0) {
            byteVector.putShort(this.b.newUTF8("Deprecated")).putInt(0);
        }
        if (this.g != null) {
            byteVector.putShort(this.b.newUTF8("Signature")).putInt(2).putShort(this.b.newUTF8(this.g));
        }
        if (this.l != null) {
            byteVector.putShort(this.b.newUTF8("AnnotationDefault"));
            byteVector.putInt(this.l.b);
            ByteVector byteVector13 = this.l;
            byteVector.putByteArray(byteVector13.a, 0, byteVector13.b);
        }
        if (this.m != null) {
            byteVector.putShort(this.b.newUTF8("RuntimeVisibleAnnotations"));
            this.m.a(byteVector);
        }
        if (this.n != null) {
            byteVector.putShort(this.b.newUTF8("RuntimeInvisibleAnnotations"));
            this.n.a(byteVector);
        }
        if (this.o != null) {
            byteVector.putShort(this.b.newUTF8("RuntimeVisibleParameterAnnotations"));
            AnnotationWriter.a(this.o, this.S, byteVector);
        }
        if (this.p != null) {
            byteVector.putShort(this.b.newUTF8("RuntimeInvisibleParameterAnnotations"));
            AnnotationWriter.a(this.p, this.S, byteVector);
        }
        Attribute attribute5 = this.q;
        if (attribute5 == null) {
            return;
        }
        attribute5.a(this.b, null, 0, -1, -1, byteVector);
    }

    @Override // com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.MethodVisitor
    public AnnotationVisitor visitAnnotation(String str, boolean z) {
        ByteVector byteVector = new ByteVector();
        byteVector.putShort(this.b.newUTF8(str)).putShort(0);
        AnnotationWriter annotationWriter = new AnnotationWriter(this.b, true, byteVector, byteVector, 2);
        if (z) {
            annotationWriter.g = this.m;
            this.m = annotationWriter;
        } else {
            annotationWriter.g = this.n;
            this.n = annotationWriter;
        }
        return annotationWriter;
    }

    @Override // com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.MethodVisitor
    public AnnotationVisitor visitAnnotationDefault() {
        this.l = new ByteVector();
        return new AnnotationWriter(this.b, false, this.l, null, 0);
    }

    @Override // com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.MethodVisitor
    public void visitAttribute(Attribute attribute) {
        if (attribute.isCodeAttribute()) {
            attribute.a = this.J;
            this.J = attribute;
            return;
        }
        attribute.a = this.q;
        this.q = attribute;
    }

    @Override // com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.MethodVisitor
    public void visitCode() {
    }

    @Override // com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.MethodVisitor
    public void visitEnd() {
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x004c  */
    @Override // com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.MethodVisitor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void visitFieldInsn(int r5, java.lang.String r6, java.lang.String r7, java.lang.String r8) {
        /*
            r4 = this;
            com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.ClassWriter r0 = r4.b
            com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Item r6 = r0.a(r6, r7, r8)
            com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Label r7 = r4.P
            if (r7 == 0) goto L50
            int r0 = r4.M
            r1 = 0
            if (r0 != 0) goto L17
            com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Frame r7 = r7.h
            com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.ClassWriter r8 = r4.b
            r7.a(r5, r1, r8, r6)
            goto L50
        L17:
            char r7 = r8.charAt(r1)
            r8 = 1
            r0 = -2
            r2 = 74
            r3 = 68
            switch(r5) {
                case 178: goto L3d;
                case 179: goto L34;
                case 180: goto L2b;
                default: goto L24;
            }
        L24:
            int r8 = r4.Q
            if (r7 == r3) goto L46
            if (r7 != r2) goto L47
            goto L46
        L2b:
            int r0 = r4.Q
            if (r7 == r3) goto L44
            if (r7 != r2) goto L32
            goto L44
        L32:
            r8 = r1
            goto L44
        L34:
            int r8 = r4.Q
            if (r7 == r3) goto L47
            if (r7 != r2) goto L3b
            goto L47
        L3b:
            r0 = -1
            goto L47
        L3d:
            int r0 = r4.Q
            if (r7 == r3) goto L43
            if (r7 != r2) goto L44
        L43:
            r8 = 2
        L44:
            int r0 = r0 + r8
            goto L48
        L46:
            r0 = -3
        L47:
            int r0 = r0 + r8
        L48:
            int r7 = r4.R
            if (r0 <= r7) goto L4e
            r4.R = r0
        L4e:
            r4.Q = r0
        L50:
            com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.ByteVector r7 = r4.r
            int r6 = r6.a
            r7.b(r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.MethodWriter.visitFieldInsn(int, java.lang.String, java.lang.String, java.lang.String):void");
    }

    @Override // com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.MethodVisitor
    public void visitFrame(int i, int i2, Object[] objArr, int i3, Object[] objArr2) {
        int i4;
        ByteVector byteVector;
        if (this.M == 0) {
            return;
        }
        int i5 = 0;
        if (i == -1) {
            this.T = i2;
            a(this.r.b, i2, i3);
            for (int i6 = 0; i6 < i2; i6++) {
                if (objArr[i6] instanceof String) {
                    int[] iArr = this.z;
                    int i7 = this.y;
                    this.y = i7 + 1;
                    iArr[i7] = 24117248 | this.b.c((String) objArr[i6]);
                } else if (objArr[i6] instanceof Integer) {
                    int[] iArr2 = this.z;
                    int i8 = this.y;
                    this.y = i8 + 1;
                    iArr2[i8] = ((Integer) objArr[i6]).intValue();
                } else {
                    int[] iArr3 = this.z;
                    int i9 = this.y;
                    this.y = i9 + 1;
                    iArr3[i9] = this.b.a("", ((Label) objArr[i6]).c) | 25165824;
                }
            }
            while (i5 < i3) {
                if (objArr2[i5] instanceof String) {
                    int[] iArr4 = this.z;
                    int i10 = this.y;
                    this.y = i10 + 1;
                    iArr4[i10] = this.b.c((String) objArr2[i5]) | 24117248;
                } else if (objArr2[i5] instanceof Integer) {
                    int[] iArr5 = this.z;
                    int i11 = this.y;
                    this.y = i11 + 1;
                    iArr5[i11] = ((Integer) objArr2[i5]).intValue();
                } else {
                    int[] iArr6 = this.z;
                    int i12 = this.y;
                    this.y = i12 + 1;
                    iArr6[i12] = this.b.a("", ((Label) objArr2[i5]).c) | 25165824;
                }
                i5++;
            }
            b();
        } else {
            if (this.v == null) {
                this.v = new ByteVector();
                i4 = this.r.b;
            } else {
                i4 = (this.r.b - this.w) - 1;
                if (i4 < 0) {
                    if (i != 3) {
                        throw new IllegalStateException();
                    }
                    return;
                }
            }
            if (i == 0) {
                this.T = i2;
                this.v.putByte(255).putShort(i4).putShort(i2);
                for (int i13 = 0; i13 < i2; i13++) {
                    a(objArr[i13]);
                }
                this.v.putShort(i3);
                while (i5 < i3) {
                    a(objArr2[i5]);
                    i5++;
                }
            } else if (i != 1) {
                int i14 = 251;
                if (i == 2) {
                    this.T -= i2;
                    byteVector = this.v;
                    i14 = 251 - i2;
                } else if (i == 3) {
                    byteVector = this.v;
                    if (i4 < 64) {
                        byteVector.putByte(i4);
                    }
                } else if (i == 4) {
                    ByteVector byteVector2 = this.v;
                    if (i4 < 64) {
                        byteVector2.putByte(i4 + 64);
                    } else {
                        byteVector2.putByte(TelnetCommand.EC).putShort(i4);
                    }
                    a(objArr2[0]);
                }
                byteVector.putByte(i14).putShort(i4);
            } else {
                this.T += i2;
                this.v.putByte(i2 + 251).putShort(i4);
                while (i5 < i2) {
                    a(objArr[i5]);
                    i5++;
                }
            }
            this.w = this.r.b;
            this.u++;
        }
        this.s = Math.max(this.s, i3);
        this.t = Math.max(this.t, this.T);
    }

    @Override // com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.MethodVisitor
    public void visitIincInsn(int i, int i2) {
        int i3;
        Label label = this.P;
        if (label != null && this.M == 0) {
            label.h.a(132, i, (ClassWriter) null, (Item) null);
        }
        if (this.M != 2 && (i3 = i + 1) > this.t) {
            this.t = i3;
        }
        if (i > 255 || i2 > 127 || i2 < -128) {
            this.r.putByte(CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA256).b(132, i).putShort(i2);
        } else {
            this.r.putByte(132).a(i, i2);
        }
    }

    @Override // com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.MethodVisitor
    public void visitInsn(int i) {
        this.r.putByte(i);
        Label label = this.P;
        if (label != null) {
            if (this.M == 0) {
                label.h.a(i, 0, (ClassWriter) null, (Item) null);
            } else {
                int i2 = this.Q + Frame.a[i];
                if (i2 > this.R) {
                    this.R = i2;
                }
                this.Q = i2;
            }
            if ((i < 172 || i > 177) && i != 191) {
                return;
            }
            e();
        }
    }

    @Override // com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.MethodVisitor
    public void visitIntInsn(int i, int i2) {
        Label label = this.P;
        if (label != null) {
            if (this.M == 0) {
                label.h.a(i, i2, (ClassWriter) null, (Item) null);
            } else if (i != 188) {
                int i3 = this.Q + 1;
                if (i3 > this.R) {
                    this.R = i3;
                }
                this.Q = i3;
            }
        }
        if (i == 17) {
            this.r.b(i, i2);
        } else {
            this.r.a(i, i2);
        }
    }

    @Override // com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.MethodVisitor
    public void visitInvokeDynamicInsn(String str, String str2, Handle handle, Object... objArr) {
        Item a = this.b.a(str, str2, handle, objArr);
        int i = a.c;
        Label label = this.P;
        if (label != null) {
            if (this.M == 0) {
                label.h.a(186, 0, this.b, a);
            } else {
                if (i == 0) {
                    i = Type.getArgumentsAndReturnSizes(str2);
                    a.c = i;
                }
                int i2 = (this.Q - (i >> 2)) + (i & 3) + 1;
                if (i2 > this.R) {
                    this.R = i2;
                }
                this.Q = i2;
            }
        }
        this.r.b(186, a.a);
        this.r.putShort(0);
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:40:? A[RETURN, SYNTHETIC] */
    @Override // com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.MethodVisitor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void visitJumpInsn(int r9, com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Label r10) {
        /*
            r8 = this;
            com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Label r0 = r8.P
            r1 = 168(0xa8, float:2.35E-43)
            r2 = 167(0xa7, float:2.34E-43)
            r3 = 0
            r4 = 0
            r5 = 1
            if (r0 == 0) goto L5c
            int r6 = r8.M
            if (r6 != 0) goto L29
            com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Frame r0 = r0.h
            r0.a(r9, r3, r4, r4)
            com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Label r0 = r10.a()
            int r6 = r0.a
            r6 = r6 | 16
            r0.a = r6
            r8.a(r3, r10)
            if (r9 == r2) goto L5c
            com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Label r4 = new com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Label
            r4.<init>()
            goto L5c
        L29:
            if (r9 != r1) goto L4e
            int r0 = r10.a
            r4 = r0 & 512(0x200, float:7.175E-43)
            if (r4 != 0) goto L3a
            r0 = r0 | 512(0x200, float:7.175E-43)
            r10.a = r0
            int r0 = r8.L
            int r0 = r0 + r5
            r8.L = r0
        L3a:
            com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Label r0 = r8.P
            int r4 = r0.a
            r4 = r4 | 128(0x80, float:1.794E-43)
            r0.a = r4
            int r0 = r8.Q
            int r0 = r0 + r5
            r8.a(r0, r10)
            com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Label r4 = new com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Label
            r4.<init>()
            goto L5c
        L4e:
            int r0 = r8.Q
            int[] r6 = com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Frame.a
            r6 = r6[r9]
            int r0 = r0 + r6
            r8.Q = r0
            int r0 = r8.Q
            r8.a(r0, r10)
        L5c:
            int r0 = r10.a
            r0 = r0 & 2
            if (r0 == 0) goto La7
            int r0 = r10.c
            com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.ByteVector r6 = r8.r
            int r7 = r6.b
            int r0 = r0 - r7
            r7 = -32768(0xffffffffffff8000, float:NaN)
            if (r0 >= r7) goto La7
            r0 = 200(0xc8, float:2.8E-43)
            if (r9 != r2) goto L75
        L71:
            r6.putByte(r0)
            goto L9e
        L75:
            if (r9 != r1) goto L7a
            r0 = 201(0xc9, float:2.82E-43)
            goto L71
        L7a:
            if (r4 == 0) goto L82
            int r1 = r4.a
            r1 = r1 | 16
            r4.a = r1
        L82:
            com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.ByteVector r1 = r8.r
            r3 = 166(0xa6, float:2.33E-43)
            if (r9 > r3) goto L8d
            int r3 = r9 + 1
            r3 = r3 ^ r5
            int r3 = r3 - r5
            goto L8f
        L8d:
            r3 = r9 ^ 1
        L8f:
            r1.putByte(r3)
            com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.ByteVector r1 = r8.r
            r3 = 8
            r1.putShort(r3)
            com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.ByteVector r1 = r8.r
            r1.putByte(r0)
        L9e:
            com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.ByteVector r0 = r8.r
            int r1 = r0.b
            int r1 = r1 - r5
            r10.a(r8, r0, r1, r5)
            goto Lb4
        La7:
            com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.ByteVector r0 = r8.r
            r0.putByte(r9)
            com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.ByteVector r0 = r8.r
            int r1 = r0.b
            int r1 = r1 - r5
            r10.a(r8, r0, r1, r3)
        Lb4:
            com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Label r10 = r8.P
            if (r10 == 0) goto Lc2
            if (r4 == 0) goto Lbd
            r8.visitLabel(r4)
        Lbd:
            if (r9 != r2) goto Lc2
            r8.e()
        Lc2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.MethodWriter.visitJumpInsn(int, com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Label):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x0077, code lost:
        if (r0 != null) goto L23;
     */
    @Override // com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.MethodVisitor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void visitLabel(com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Label r6) {
        /*
            r5 = this;
            boolean r0 = r5.K
            com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.ByteVector r1 = r5.r
            int r2 = r1.b
            byte[] r1 = r1.a
            boolean r1 = r6.a(r5, r2, r1)
            r0 = r0 | r1
            r5.K = r0
            int r0 = r6.a
            r1 = r0 & 1
            if (r1 == 0) goto L16
            return
        L16:
            int r1 = r5.M
            r2 = 0
            if (r1 != 0) goto L5f
            com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Label r1 = r5.P
            if (r1 == 0) goto L34
            int r3 = r6.c
            int r4 = r1.c
            if (r3 != r4) goto L31
            int r2 = r1.a
            r0 = r0 & 16
            r0 = r0 | r2
            r1.a = r0
            com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Frame r0 = r1.h
            r6.h = r0
            return
        L31:
            r5.a(r2, r6)
        L34:
            r5.P = r6
            com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Frame r0 = r6.h
            if (r0 != 0) goto L45
            com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Frame r0 = new com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Frame
            r0.<init>()
            r6.h = r0
            com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Frame r0 = r6.h
            r0.b = r6
        L45:
            com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Label r0 = r5.O
            if (r0 == 0) goto L7b
            int r1 = r6.c
            int r2 = r0.c
            if (r1 != r2) goto L79
            int r1 = r0.a
            int r2 = r6.a
            r2 = r2 & 16
            r1 = r1 | r2
            r0.a = r1
            com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Frame r1 = r0.h
            r6.h = r1
            r5.P = r0
            return
        L5f:
            r0 = 1
            if (r1 != r0) goto L7d
            com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Label r0 = r5.P
            if (r0 == 0) goto L6f
            int r1 = r5.R
            r0.g = r1
            int r0 = r5.Q
            r5.a(r0, r6)
        L6f:
            r5.P = r6
            r5.Q = r2
            r5.R = r2
            com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Label r0 = r5.O
            if (r0 == 0) goto L7b
        L79:
            r0.i = r6
        L7b:
            r5.O = r6
        L7d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.MethodWriter.visitLabel(com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Label):void");
    }

    @Override // com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.MethodVisitor
    public void visitLdcInsn(Object obj) {
        ByteVector byteVector;
        int i;
        Item a = this.b.a(obj);
        Label label = this.P;
        if (label != null) {
            if (this.M == 0) {
                label.h.a(18, 0, this.b, a);
            } else {
                int i2 = a.b;
                int i3 = (i2 == 5 || i2 == 6) ? this.Q + 2 : this.Q + 1;
                if (i3 > this.R) {
                    this.R = i3;
                }
                this.Q = i3;
            }
        }
        int i4 = a.a;
        int i5 = a.b;
        if (i5 == 5 || i5 == 6) {
            byteVector = this.r;
            i = 20;
        } else if (i4 < 256) {
            this.r.a(18, i4);
            return;
        } else {
            byteVector = this.r;
            i = 19;
        }
        byteVector.b(i, i4);
    }

    @Override // com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.MethodVisitor
    public void visitLineNumber(int i, Label label) {
        if (this.I == null) {
            this.I = new ByteVector();
        }
        this.H++;
        this.I.putShort(label.c);
        this.I.putShort(i);
    }

    @Override // com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.MethodVisitor
    public void visitLocalVariable(String str, String str2, String str3, Label label, Label label2, int i) {
        if (str3 != null) {
            if (this.G == null) {
                this.G = new ByteVector();
            }
            this.F++;
            this.G.putShort(label.c).putShort(label2.c - label.c).putShort(this.b.newUTF8(str)).putShort(this.b.newUTF8(str3)).putShort(i);
        }
        if (this.E == null) {
            this.E = new ByteVector();
        }
        this.D++;
        this.E.putShort(label.c).putShort(label2.c - label.c).putShort(this.b.newUTF8(str)).putShort(this.b.newUTF8(str2)).putShort(i);
        int i2 = 2;
        if (this.M != 2) {
            char charAt = str2.charAt(0);
            if (charAt != 'J' && charAt != 'D') {
                i2 = 1;
            }
            int i3 = i + i2;
            if (i3 <= this.t) {
                return;
            }
            this.t = i3;
        }
    }

    @Override // com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.MethodVisitor
    public void visitLookupSwitchInsn(Label label, int[] iArr, Label[] labelArr) {
        ByteVector byteVector = this.r;
        int i = byteVector.b;
        byteVector.putByte(171);
        ByteVector byteVector2 = this.r;
        byteVector2.putByteArray(null, 0, (4 - (byteVector2.b % 4)) % 4);
        label.a(this, this.r, i, true);
        this.r.putInt(labelArr.length);
        for (int i2 = 0; i2 < labelArr.length; i2++) {
            this.r.putInt(iArr[i2]);
            labelArr[i2].a(this, this.r, i, true);
        }
        a(label, labelArr);
    }

    /*  JADX ERROR: JadxOverflowException in pass: LoopRegionVisitor
        jadx.core.utils.exceptions.JadxOverflowException: LoopRegionVisitor.assignOnlyInLoop endless recursion
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:56)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:30)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:18)
        */
    @Override // com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.MethodVisitor
    public void visitMaxs(int r14, int r15) {
        /*
            Method dump skipped, instructions count: 499
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.MethodWriter.visitMaxs(int, int):void");
    }

    @Override // com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.MethodVisitor
    public void visitMethodInsn(int i, String str, String str2, String str3) {
        boolean z = i == 185;
        Item a = this.b.a(str, str2, str3, z);
        int i2 = a.c;
        Label label = this.P;
        if (label != null) {
            if (this.M == 0) {
                label.h.a(i, 0, this.b, a);
            } else {
                if (i2 == 0) {
                    i2 = Type.getArgumentsAndReturnSizes(str3);
                    a.c = i2;
                }
                int i3 = i == 184 ? (this.Q - (i2 >> 2)) + (i2 & 3) + 1 : (i2 & 3) + (this.Q - (i2 >> 2));
                if (i3 > this.R) {
                    this.R = i3;
                }
                this.Q = i3;
            }
        }
        if (!z) {
            this.r.b(i, a.a);
            return;
        }
        if (i2 == 0) {
            i2 = Type.getArgumentsAndReturnSizes(str3);
            a.c = i2;
        }
        this.r.b(185, a.a).a(i2 >> 2, 0);
    }

    @Override // com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.MethodVisitor
    public void visitMultiANewArrayInsn(String str, int i) {
        Item a = this.b.a(str);
        Label label = this.P;
        if (label != null) {
            if (this.M == 0) {
                label.h.a(197, i, this.b, a);
            } else {
                this.Q = (1 - i) + this.Q;
            }
        }
        this.r.b(197, a.a).putByte(i);
    }

    @Override // com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.MethodVisitor
    public AnnotationVisitor visitParameterAnnotation(int i, String str, boolean z) {
        ByteVector byteVector = new ByteVector();
        if ("Ljava/lang/Synthetic;".equals(str)) {
            this.S = Math.max(this.S, i + 1);
            return new AnnotationWriter(this.b, false, byteVector, null, 0);
        }
        byteVector.putShort(this.b.newUTF8(str)).putShort(0);
        AnnotationWriter annotationWriter = new AnnotationWriter(this.b, true, byteVector, byteVector, 2);
        if (z) {
            if (this.o == null) {
                this.o = new AnnotationWriter[Type.getArgumentTypes(this.f).length];
            }
            AnnotationWriter[] annotationWriterArr = this.o;
            annotationWriter.g = annotationWriterArr[i];
            annotationWriterArr[i] = annotationWriter;
        } else {
            if (this.p == null) {
                this.p = new AnnotationWriter[Type.getArgumentTypes(this.f).length];
            }
            AnnotationWriter[] annotationWriterArr2 = this.p;
            annotationWriter.g = annotationWriterArr2[i];
            annotationWriterArr2[i] = annotationWriter;
        }
        return annotationWriter;
    }

    @Override // com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.MethodVisitor
    public void visitTableSwitchInsn(int i, int i2, Label label, Label... labelArr) {
        ByteVector byteVector = this.r;
        int i3 = byteVector.b;
        byteVector.putByte(170);
        ByteVector byteVector2 = this.r;
        byteVector2.putByteArray(null, 0, (4 - (byteVector2.b % 4)) % 4);
        label.a(this, this.r, i3, true);
        this.r.putInt(i).putInt(i2);
        for (Label label2 : labelArr) {
            label2.a(this, this.r, i3, true);
        }
        a(label, labelArr);
    }

    @Override // com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.MethodVisitor
    public void visitTryCatchBlock(Label label, Label label2, Label label3, String str) {
        this.A++;
        Handler handler = new Handler();
        handler.a = label;
        handler.b = label2;
        handler.c = label3;
        handler.d = str;
        handler.e = str != null ? this.b.newClass(str) : 0;
        Handler handler2 = this.C;
        if (handler2 == null) {
            this.B = handler;
        } else {
            handler2.f = handler;
        }
        this.C = handler;
    }

    @Override // com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.MethodVisitor
    public void visitTypeInsn(int i, String str) {
        Item a = this.b.a(str);
        Label label = this.P;
        if (label != null) {
            if (this.M == 0) {
                label.h.a(i, this.r.b, this.b, a);
            } else if (i == 187) {
                int i2 = this.Q + 1;
                if (i2 > this.R) {
                    this.R = i2;
                }
                this.Q = i2;
            }
        }
        this.r.b(i, a.a);
    }

    @Override // com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.MethodVisitor
    public void visitVarInsn(int i, int i2) {
        Label label = this.P;
        if (label != null) {
            if (this.M == 0) {
                label.h.a(i, i2, (ClassWriter) null, (Item) null);
            } else if (i == 169) {
                label.a |= 256;
                label.f = this.Q;
                e();
            } else {
                int i3 = this.Q + Frame.a[i];
                if (i3 > this.R) {
                    this.R = i3;
                }
                this.Q = i3;
            }
        }
        if (this.M != 2) {
            int i4 = (i == 22 || i == 24 || i == 55 || i == 57) ? i2 + 2 : i2 + 1;
            if (i4 > this.t) {
                this.t = i4;
            }
        }
        if (i2 >= 4 || i == 169) {
            ByteVector byteVector = this.r;
            if (i2 >= 256) {
                byteVector.putByte(CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA256).b(i, i2);
            } else {
                byteVector.a(i, i2);
            }
        } else {
            this.r.putByte((i < 54 ? ((i - 21) << 2) + 26 : ((i - 54) << 2) + 59) + i2);
        }
        if (i < 54 || this.M != 0 || this.A <= 0) {
            return;
        }
        visitLabel(new Label());
    }
}
