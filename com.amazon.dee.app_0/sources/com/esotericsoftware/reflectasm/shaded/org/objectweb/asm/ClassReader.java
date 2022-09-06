package com.esotericsoftware.reflectasm.shaded.org.objectweb.asm;

import java.io.IOException;
import java.io.InputStream;
import okio.Utf8;
import org.bouncycastle.asn1.cmc.BodyPartID;
/* loaded from: classes2.dex */
public class ClassReader {
    public static final int EXPAND_FRAMES = 8;
    public static final int SKIP_CODE = 1;
    public static final int SKIP_DEBUG = 2;
    public static final int SKIP_FRAMES = 4;
    private final int[] a;
    public final byte[] b;
    private final String[] c;
    private final int d;
    public final int header;

    public ClassReader(InputStream inputStream) throws IOException {
        this(a(inputStream, false));
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public ClassReader(java.lang.String r4) throws java.io.IOException {
        /*
            r3 = this;
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            r1 = 46
            r2 = 47
            java.lang.String r4 = r4.replace(r1, r2)
            r0.append(r4)
            java.lang.String r4 = ".class"
            r0.append(r4)
            java.lang.String r4 = r0.toString()
            java.io.InputStream r4 = java.lang.ClassLoader.getSystemResourceAsStream(r4)
            r0 = 1
            byte[] r4 = a(r4, r0)
            r3.<init>(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.ClassReader.<init>(java.lang.String):void");
    }

    public ClassReader(byte[] bArr) {
        this(bArr, 0, bArr.length);
    }

    public ClassReader(byte[] bArr, int i, int i2) {
        this.b = bArr;
        if (readShort(6) <= 51) {
            this.a = new int[readUnsignedShort(i + 8)];
            int length = this.a.length;
            this.c = new String[length];
            int i3 = 0;
            int i4 = i + 10;
            int i5 = 1;
            while (i5 < length) {
                int i6 = i4 + 1;
                this.a[i5] = i6;
                byte b = bArr[i4];
                int i7 = 5;
                if (b == 1) {
                    i7 = readUnsignedShort(i6) + 3;
                    if (i7 > i3) {
                        i3 = i7;
                    }
                } else if (b == 15) {
                    i7 = 4;
                } else if (b != 18 && b != 3 && b != 4) {
                    if (b == 5 || b == 6) {
                        i7 = 9;
                        i5++;
                    } else {
                        switch (b) {
                            case 9:
                            case 10:
                            case 11:
                            case 12:
                                break;
                            default:
                                i7 = 3;
                                continue;
                        }
                    }
                }
                i4 += i7;
                i5++;
            }
            this.d = i3;
            this.header = i4;
            return;
        }
        throw new IllegalArgumentException();
    }

    private int a(int i, char[] cArr, String str, AnnotationVisitor annotationVisitor) {
        Object readConst;
        int i2 = 0;
        if (annotationVisitor == null) {
            int i3 = this.b[i] & 255;
            return i3 != 64 ? i3 != 91 ? i3 != 101 ? i + 3 : i + 5 : a(i + 1, cArr, false, (AnnotationVisitor) null) : a(i + 3, cArr, true, (AnnotationVisitor) null);
        }
        int i4 = i + 1;
        int i5 = this.b[i] & 255;
        if (i5 == 64) {
            return a(i4 + 2, cArr, true, annotationVisitor.visitAnnotation(str, readUTF8(i4, cArr)));
        }
        if (i5 != 70) {
            if (i5 == 83) {
                readConst = new Short((short) readInt(this.a[readUnsignedShort(i4)]));
            } else if (i5 == 99) {
                readConst = Type.getType(readUTF8(i4, cArr));
            } else if (i5 == 101) {
                annotationVisitor.visitEnum(str, readUTF8(i4, cArr), readUTF8(i4 + 2, cArr));
                return i4 + 4;
            } else if (i5 == 115) {
                readConst = readUTF8(i4, cArr);
            } else if (i5 != 73 && i5 != 74) {
                if (i5 == 90) {
                    readConst = readInt(this.a[readUnsignedShort(i4)]) == 0 ? Boolean.FALSE : Boolean.TRUE;
                } else if (i5 == 91) {
                    int readUnsignedShort = readUnsignedShort(i4);
                    int i6 = i4 + 2;
                    if (readUnsignedShort == 0) {
                        return a(i6 - 2, cArr, false, annotationVisitor.visitArray(str));
                    }
                    int i7 = i6 + 1;
                    int i8 = this.b[i6] & 255;
                    if (i8 == 70) {
                        float[] fArr = new float[readUnsignedShort];
                        while (i2 < readUnsignedShort) {
                            fArr[i2] = Float.intBitsToFloat(readInt(this.a[readUnsignedShort(i7)]));
                            i7 += 3;
                            i2++;
                        }
                        annotationVisitor.visit(str, fArr);
                    } else if (i8 == 83) {
                        short[] sArr = new short[readUnsignedShort];
                        while (i2 < readUnsignedShort) {
                            sArr[i2] = (short) readInt(this.a[readUnsignedShort(i7)]);
                            i7 += 3;
                            i2++;
                        }
                        annotationVisitor.visit(str, sArr);
                    } else if (i8 == 90) {
                        boolean[] zArr = new boolean[readUnsignedShort];
                        for (int i9 = 0; i9 < readUnsignedShort; i9++) {
                            zArr[i9] = readInt(this.a[readUnsignedShort(i7)]) != 0;
                            i7 += 3;
                        }
                        annotationVisitor.visit(str, zArr);
                    } else if (i8 == 73) {
                        int[] iArr = new int[readUnsignedShort];
                        while (i2 < readUnsignedShort) {
                            iArr[i2] = readInt(this.a[readUnsignedShort(i7)]);
                            i7 += 3;
                            i2++;
                        }
                        annotationVisitor.visit(str, iArr);
                    } else if (i8 != 74) {
                        switch (i8) {
                            case 66:
                                byte[] bArr = new byte[readUnsignedShort];
                                while (i2 < readUnsignedShort) {
                                    bArr[i2] = (byte) readInt(this.a[readUnsignedShort(i7)]);
                                    i7 += 3;
                                    i2++;
                                }
                                annotationVisitor.visit(str, bArr);
                                break;
                            case 67:
                                char[] cArr2 = new char[readUnsignedShort];
                                while (i2 < readUnsignedShort) {
                                    cArr2[i2] = (char) readInt(this.a[readUnsignedShort(i7)]);
                                    i7 += 3;
                                    i2++;
                                }
                                annotationVisitor.visit(str, cArr2);
                                break;
                            case 68:
                                double[] dArr = new double[readUnsignedShort];
                                while (i2 < readUnsignedShort) {
                                    dArr[i2] = Double.longBitsToDouble(readLong(this.a[readUnsignedShort(i7)]));
                                    i7 += 3;
                                    i2++;
                                }
                                annotationVisitor.visit(str, dArr);
                                break;
                            default:
                                return a(i7 - 3, cArr, false, annotationVisitor.visitArray(str));
                        }
                    } else {
                        long[] jArr = new long[readUnsignedShort];
                        while (i2 < readUnsignedShort) {
                            jArr[i2] = readLong(this.a[readUnsignedShort(i7)]);
                            i7 += 3;
                            i2++;
                        }
                        annotationVisitor.visit(str, jArr);
                    }
                    return i7 - 1;
                } else {
                    switch (i5) {
                        case 66:
                            readConst = new Byte((byte) readInt(this.a[readUnsignedShort(i4)]));
                            break;
                        case 67:
                            readConst = new Character((char) readInt(this.a[readUnsignedShort(i4)]));
                            break;
                        case 68:
                            break;
                        default:
                            return i4;
                    }
                }
            }
            annotationVisitor.visit(str, readConst);
            return i4 + 2;
        }
        readConst = readConst(readUnsignedShort(i4), cArr);
        annotationVisitor.visit(str, readConst);
        return i4 + 2;
    }

    private int a(int i, char[] cArr, boolean z, AnnotationVisitor annotationVisitor) {
        int readUnsignedShort = readUnsignedShort(i);
        int i2 = i + 2;
        if (z) {
            while (readUnsignedShort > 0) {
                i2 = a(i2 + 2, cArr, readUTF8(i2, cArr), annotationVisitor);
                readUnsignedShort--;
            }
        } else {
            while (readUnsignedShort > 0) {
                i2 = a(i2, cArr, (String) null, annotationVisitor);
                readUnsignedShort--;
            }
        }
        if (annotationVisitor != null) {
            annotationVisitor.visitEnd();
        }
        return i2;
    }

    private int a(Object[] objArr, int i, int i2, char[] cArr, Label[] labelArr) {
        int i3 = i2 + 1;
        switch (this.b[i2] & 255) {
            case 0:
                objArr[i] = Opcodes.TOP;
                return i3;
            case 1:
                objArr[i] = Opcodes.INTEGER;
                return i3;
            case 2:
                objArr[i] = Opcodes.FLOAT;
                return i3;
            case 3:
                objArr[i] = Opcodes.DOUBLE;
                return i3;
            case 4:
                objArr[i] = Opcodes.LONG;
                return i3;
            case 5:
                objArr[i] = Opcodes.NULL;
                return i3;
            case 6:
                objArr[i] = Opcodes.UNINITIALIZED_THIS;
                return i3;
            case 7:
                objArr[i] = readClass(i3, cArr);
                break;
            default:
                objArr[i] = readLabel(readUnsignedShort(i3), labelArr);
                break;
        }
        return i3 + 2;
    }

    private Attribute a(Attribute[] attributeArr, String str, int i, int i2, char[] cArr, int i3, Label[] labelArr) {
        for (int i4 = 0; i4 < attributeArr.length; i4++) {
            if (attributeArr[i4].type.equals(str)) {
                return attributeArr[i4].read(this, i, i2, cArr, i3, labelArr);
            }
        }
        return new Attribute(str).read(this, i, i2, null, -1, null);
    }

    private String a(int i, int i2, char[] cArr) {
        int i3;
        int i4 = i2 + i;
        byte[] bArr = this.b;
        int i5 = 0;
        boolean z = false;
        char c = 0;
        while (i < i4) {
            int i6 = i + 1;
            byte b = bArr[i];
            if (z) {
                if (z) {
                    cArr[i5] = (char) ((b & Utf8.REPLACEMENT_BYTE) | (c << 6));
                    i5++;
                    z = false;
                } else if (z) {
                    i3 = (b & Utf8.REPLACEMENT_BYTE) | (c << 6);
                    c = (char) i3;
                    z = true;
                }
                i = i6;
            } else {
                int i7 = b & 255;
                if (i7 < 128) {
                    cArr[i5] = (char) i7;
                    i5++;
                } else if (i7 >= 224 || i7 <= 191) {
                    c = (char) (i7 & 15);
                    z = true;
                } else {
                    i3 = i7 & 31;
                    c = (char) i3;
                    z = true;
                }
                i = i6;
            }
        }
        return new String(cArr, 0, i5);
    }

    private void a(int i, String str, char[] cArr, boolean z, MethodVisitor methodVisitor) {
        int i2 = i + 1;
        int i3 = this.b[i] & 255;
        int length = Type.getArgumentTypes(str).length - i3;
        int i4 = 0;
        while (i4 < length) {
            AnnotationVisitor visitParameterAnnotation = methodVisitor.visitParameterAnnotation(i4, "Ljava/lang/Synthetic;", false);
            if (visitParameterAnnotation != null) {
                visitParameterAnnotation.visitEnd();
            }
            i4++;
        }
        while (i4 < i3 + length) {
            i2 += 2;
            for (int readUnsignedShort = readUnsignedShort(i2); readUnsignedShort > 0; readUnsignedShort--) {
                i2 = a(i2 + 2, cArr, true, methodVisitor.visitParameterAnnotation(i4, readUTF8(i2, cArr), z));
            }
            i4++;
        }
    }

    private void a(ClassWriter classWriter, Item[] itemArr, char[] cArr) {
        int i = this.header;
        int readUnsignedShort = (readUnsignedShort(i + 6) << 1) + 8 + i;
        int i2 = readUnsignedShort + 2;
        for (int readUnsignedShort2 = readUnsignedShort(readUnsignedShort); readUnsignedShort2 > 0; readUnsignedShort2--) {
            i2 += 8;
            for (int readUnsignedShort3 = readUnsignedShort(i2 + 6); readUnsignedShort3 > 0; readUnsignedShort3--) {
                i2 += readInt(i2 + 2) + 6;
            }
        }
        int i3 = i2 + 2;
        for (int readUnsignedShort4 = readUnsignedShort(i2); readUnsignedShort4 > 0; readUnsignedShort4--) {
            i3 += 8;
            for (int readUnsignedShort5 = readUnsignedShort(i3 + 6); readUnsignedShort5 > 0; readUnsignedShort5--) {
                i3 += readInt(i3 + 2) + 6;
            }
        }
        int i4 = i3 + 2;
        for (int readUnsignedShort6 = readUnsignedShort(i3); readUnsignedShort6 > 0; readUnsignedShort6--) {
            String readUTF8 = readUTF8(i4, cArr);
            int readInt = readInt(i4 + 2);
            if ("BootstrapMethods".equals(readUTF8)) {
                int readUnsignedShort7 = readUnsignedShort(i4 + 6);
                int i5 = i4 + 8;
                int i6 = 0;
                int i7 = i5;
                while (i6 < readUnsignedShort7) {
                    int hashCode = readConst(readUnsignedShort(i7), cArr).hashCode();
                    int i8 = i7 + 4;
                    for (int readUnsignedShort8 = readUnsignedShort(i7 + 2); readUnsignedShort8 > 0; readUnsignedShort8--) {
                        hashCode ^= readConst(readUnsignedShort(i8), cArr).hashCode();
                        i8 += 2;
                    }
                    Item item = new Item(i6);
                    item.a((i7 - i4) - 8, hashCode & Integer.MAX_VALUE);
                    int length = item.j % itemArr.length;
                    item.k = itemArr[length];
                    itemArr[length] = item;
                    i6++;
                    i7 = i8;
                }
                classWriter.z = readUnsignedShort7;
                ByteVector byteVector = new ByteVector(readInt + 62);
                byteVector.putByteArray(this.b, i5, readInt - 2);
                classWriter.A = byteVector;
                return;
            }
            i4 += readInt + 6;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0021, code lost:
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0014, code lost:
        if (r2 >= r0.length) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0016, code lost:
        r3 = new byte[r2];
        java.lang.System.arraycopy(r0, 0, r3, 0, r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x001b, code lost:
        r0 = r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static byte[] a(java.io.InputStream r5, boolean r6) throws java.io.IOException {
        /*
            if (r5 == 0) goto L49
            int r0 = r5.available()     // Catch: java.lang.Throwable -> L42
            byte[] r0 = new byte[r0]     // Catch: java.lang.Throwable -> L42
            r1 = 0
            r2 = r1
        La:
            int r3 = r0.length     // Catch: java.lang.Throwable -> L42
            int r3 = r3 - r2
            int r3 = r5.read(r0, r2, r3)     // Catch: java.lang.Throwable -> L42
            r4 = -1
            if (r3 != r4) goto L22
            int r3 = r0.length     // Catch: java.lang.Throwable -> L42
            if (r2 >= r3) goto L1c
            byte[] r3 = new byte[r2]     // Catch: java.lang.Throwable -> L42
            java.lang.System.arraycopy(r0, r1, r3, r1, r2)     // Catch: java.lang.Throwable -> L42
            r0 = r3
        L1c:
            if (r6 == 0) goto L21
            r5.close()
        L21:
            return r0
        L22:
            int r2 = r2 + r3
            int r3 = r0.length     // Catch: java.lang.Throwable -> L42
            if (r2 != r3) goto La
            int r3 = r5.read()     // Catch: java.lang.Throwable -> L42
            if (r3 >= 0) goto L32
            if (r6 == 0) goto L31
            r5.close()
        L31:
            return r0
        L32:
            int r4 = r0.length     // Catch: java.lang.Throwable -> L42
            int r4 = r4 + 1000
            byte[] r4 = new byte[r4]     // Catch: java.lang.Throwable -> L42
            java.lang.System.arraycopy(r0, r1, r4, r1, r2)     // Catch: java.lang.Throwable -> L42
            int r0 = r2 + 1
            byte r3 = (byte) r3     // Catch: java.lang.Throwable -> L42
            r4[r2] = r3     // Catch: java.lang.Throwable -> L42
            r2 = r0
            r0 = r4
            goto La
        L42:
            r0 = move-exception
            if (r6 == 0) goto L48
            r5.close()
        L48:
            throw r0
        L49:
            java.io.IOException r5 = new java.io.IOException
            java.lang.String r6 = "Class not found"
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.ClassReader.a(java.io.InputStream, boolean):byte[]");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(ClassWriter classWriter) {
        char[] cArr = new char[this.d];
        int length = this.a.length;
        Item[] itemArr = new Item[length];
        int i = 1;
        while (i < length) {
            int i2 = this.a[i];
            byte b = this.b[i2 - 1];
            Item item = new Item(i);
            if (b == 1) {
                String[] strArr = this.c;
                String str = strArr[i];
                if (str == null) {
                    int i3 = this.a[i];
                    str = a(i3 + 2, readUnsignedShort(i3), cArr);
                    strArr[i] = str;
                }
                item.a(b, str, null, null);
            } else if (b == 15) {
                int i4 = this.a[readUnsignedShort(i2 + 1)];
                int i5 = this.a[readUnsignedShort(i4 + 2)];
                item.a(readByte(i2) + 20, readClass(i4, cArr), readUTF8(i5, cArr), readUTF8(i5 + 2, cArr));
            } else if (b == 18) {
                if (classWriter.A == null) {
                    a(classWriter, itemArr, cArr);
                }
                int i6 = this.a[readUnsignedShort(i2 + 2)];
                item.a(readUTF8(i6, cArr), readUTF8(i6 + 2, cArr), readUnsignedShort(i2));
            } else if (b == 3) {
                item.a(readInt(i2));
            } else if (b != 4) {
                if (b == 5) {
                    item.a(readLong(i2));
                } else if (b != 6) {
                    switch (b) {
                        case 9:
                        case 10:
                        case 11:
                            int i7 = this.a[readUnsignedShort(i2 + 2)];
                            item.a(b, readClass(i2, cArr), readUTF8(i7, cArr), readUTF8(i7 + 2, cArr));
                            continue;
                        case 12:
                            item.a(b, readUTF8(i2, cArr), readUTF8(i2 + 2, cArr), null);
                            continue;
                        default:
                            item.a(b, readUTF8(i2, cArr), null, null);
                            continue;
                    }
                } else {
                    item.a(Double.longBitsToDouble(readLong(i2)));
                }
                i++;
            } else {
                item.a(Float.intBitsToFloat(readInt(i2)));
            }
            int length2 = item.j % itemArr.length;
            item.k = itemArr[length2];
            itemArr[length2] = item;
            i++;
        }
        int i8 = this.a[1] - 1;
        classWriter.d.putByteArray(this.b, i8, this.header - i8);
        classWriter.e = itemArr;
        classWriter.f = (int) (length * 0.75d);
        classWriter.c = length;
    }

    public void accept(ClassVisitor classVisitor, int i) {
        accept(classVisitor, new Attribute[0], i);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:242:0x0566, code lost:
        if (r0.j == 0) goto L272;
     */
    /* JADX WARN: Code restructure failed: missing block: B:531:0x0ced, code lost:
        if (r0 == 185) goto L543;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void accept(com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.ClassVisitor r57, com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Attribute[] r58, int r59) {
        /*
            Method dump skipped, instructions count: 3696
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.ClassReader.accept(com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.ClassVisitor, com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Attribute[], int):void");
    }

    public int getAccess() {
        return readUnsignedShort(this.header);
    }

    public String getClassName() {
        return readClass(this.header + 2, new char[this.d]);
    }

    public String[] getInterfaces() {
        int i = this.header + 6;
        int readUnsignedShort = readUnsignedShort(i);
        String[] strArr = new String[readUnsignedShort];
        if (readUnsignedShort > 0) {
            char[] cArr = new char[this.d];
            for (int i2 = 0; i2 < readUnsignedShort; i2++) {
                i += 2;
                strArr[i2] = readClass(i, cArr);
            }
        }
        return strArr;
    }

    public int getItem(int i) {
        return this.a[i];
    }

    public int getItemCount() {
        return this.a.length;
    }

    public int getMaxStringLength() {
        return this.d;
    }

    public String getSuperName() {
        int i = this.a[readUnsignedShort(this.header + 4)];
        if (i == 0) {
            return null;
        }
        return readUTF8(i, new char[this.d]);
    }

    public int readByte(int i) {
        return this.b[i] & 255;
    }

    public String readClass(int i, char[] cArr) {
        return readUTF8(this.a[readUnsignedShort(i)], cArr);
    }

    public Object readConst(int i, char[] cArr) {
        int i2 = this.a[i];
        byte b = this.b[i2 - 1];
        if (b != 16) {
            switch (b) {
                case 3:
                    return new Integer(readInt(i2));
                case 4:
                    return new Float(Float.intBitsToFloat(readInt(i2)));
                case 5:
                    return new Long(readLong(i2));
                case 6:
                    return new Double(Double.longBitsToDouble(readLong(i2)));
                case 7:
                    return Type.getObjectType(readUTF8(i2, cArr));
                case 8:
                    return readUTF8(i2, cArr);
                default:
                    int readByte = readByte(i2);
                    int[] iArr = this.a;
                    int i3 = iArr[readUnsignedShort(i2 + 1)];
                    String readClass = readClass(i3, cArr);
                    int i4 = iArr[readUnsignedShort(i3 + 2)];
                    return new Handle(readByte, readClass, readUTF8(i4, cArr), readUTF8(i4 + 2, cArr));
            }
        }
        return Type.getMethodType(readUTF8(i2, cArr));
    }

    public int readInt(int i) {
        byte[] bArr = this.b;
        return (bArr[i + 3] & 255) | ((bArr[i] & 255) << 24) | ((bArr[i + 1] & 255) << 16) | ((bArr[i + 2] & 255) << 8);
    }

    protected Label readLabel(int i, Label[] labelArr) {
        if (labelArr[i] == null) {
            labelArr[i] = new Label();
        }
        return labelArr[i];
    }

    public long readLong(int i) {
        return (readInt(i) << 32) | (readInt(i + 4) & BodyPartID.bodyIdMax);
    }

    public short readShort(int i) {
        byte[] bArr = this.b;
        return (short) ((bArr[i + 1] & 255) | ((bArr[i] & 255) << 8));
    }

    public String readUTF8(int i, char[] cArr) {
        int readUnsignedShort = readUnsignedShort(i);
        String[] strArr = this.c;
        String str = strArr[readUnsignedShort];
        if (str != null) {
            return str;
        }
        int i2 = this.a[readUnsignedShort];
        String a = a(i2 + 2, readUnsignedShort(i2), cArr);
        strArr[readUnsignedShort] = a;
        return a;
    }

    public int readUnsignedShort(int i) {
        byte[] bArr = this.b;
        return (bArr[i + 1] & 255) | ((bArr[i] & 255) << 8);
    }
}
