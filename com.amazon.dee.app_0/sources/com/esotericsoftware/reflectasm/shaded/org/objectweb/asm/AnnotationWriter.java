package com.esotericsoftware.reflectasm.shaded.org.objectweb.asm;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class AnnotationWriter extends AnnotationVisitor {
    private final ClassWriter a;
    private int b;
    private final boolean c;
    private final ByteVector d;
    private final ByteVector e;
    private final int f;
    AnnotationWriter g;
    AnnotationWriter h;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AnnotationWriter(ClassWriter classWriter, boolean z, ByteVector byteVector, ByteVector byteVector2, int i) {
        super(262144);
        this.a = classWriter;
        this.c = z;
        this.d = byteVector;
        this.e = byteVector2;
        this.f = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(AnnotationWriter[] annotationWriterArr, int i, ByteVector byteVector) {
        int length = ((annotationWriterArr.length - i) * 2) + 1;
        int i2 = i;
        while (true) {
            int i3 = 0;
            if (i2 >= annotationWriterArr.length) {
                break;
            }
            if (annotationWriterArr[i2] != null) {
                i3 = annotationWriterArr[i2].a();
            }
            length += i3;
            i2++;
        }
        byteVector.putInt(length).putByte(annotationWriterArr.length - i);
        while (i < annotationWriterArr.length) {
            AnnotationWriter annotationWriter = null;
            int i4 = 0;
            for (AnnotationWriter annotationWriter2 = annotationWriterArr[i]; annotationWriter2 != null; annotationWriter2 = annotationWriter2.g) {
                i4++;
                annotationWriter2.visitEnd();
                annotationWriter2.h = annotationWriter;
                annotationWriter = annotationWriter2;
            }
            byteVector.putShort(i4);
            while (annotationWriter != null) {
                ByteVector byteVector2 = annotationWriter.d;
                byteVector.putByteArray(byteVector2.a, 0, byteVector2.b);
                annotationWriter = annotationWriter.h;
            }
            i++;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int a() {
        int i = 0;
        for (AnnotationWriter annotationWriter = this; annotationWriter != null; annotationWriter = annotationWriter.g) {
            i += annotationWriter.d.b;
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(ByteVector byteVector) {
        int i = 2;
        int i2 = 0;
        AnnotationWriter annotationWriter = null;
        for (AnnotationWriter annotationWriter2 = this; annotationWriter2 != null; annotationWriter2 = annotationWriter2.g) {
            i2++;
            i += annotationWriter2.d.b;
            annotationWriter2.visitEnd();
            annotationWriter2.h = annotationWriter;
            annotationWriter = annotationWriter2;
        }
        byteVector.putInt(i);
        byteVector.putShort(i2);
        while (annotationWriter != null) {
            ByteVector byteVector2 = annotationWriter.d;
            byteVector.putByteArray(byteVector2.a, 0, byteVector2.b);
            annotationWriter = annotationWriter.h;
        }
    }

    @Override // com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.AnnotationVisitor
    public void visit(String str, Object obj) {
        int i;
        ByteVector byteVector;
        ClassWriter classWriter;
        String descriptor;
        int i2;
        this.b++;
        if (this.c) {
            this.d.putShort(this.a.newUTF8(str));
        }
        if (obj instanceof String) {
            byteVector = this.d;
            i = 115;
            classWriter = this.a;
            descriptor = (String) obj;
        } else {
            i = 66;
            if (obj instanceof Byte) {
                byteVector = this.d;
                i2 = this.a.a((int) ((Byte) obj).byteValue()).a;
                byteVector.b(i, i2);
            } else if (obj instanceof Boolean) {
                this.d.b(90, this.a.a(((Boolean) obj).booleanValue() ? 1 : 0).a);
                return;
            } else if (obj instanceof Character) {
                this.d.b(67, this.a.a((int) ((Character) obj).charValue()).a);
                return;
            } else if (obj instanceof Short) {
                this.d.b(83, this.a.a((int) ((Short) obj).shortValue()).a);
                return;
            } else if (!(obj instanceof Type)) {
                int i3 = 0;
                if (obj instanceof byte[]) {
                    byte[] bArr = (byte[]) obj;
                    this.d.b(91, bArr.length);
                    while (i3 < bArr.length) {
                        this.d.b(66, this.a.a((int) bArr[i3]).a);
                        i3++;
                    }
                    return;
                } else if (obj instanceof boolean[]) {
                    boolean[] zArr = (boolean[]) obj;
                    this.d.b(91, zArr.length);
                    while (i3 < zArr.length) {
                        this.d.b(90, this.a.a(zArr[i3] ? 1 : 0).a);
                        i3++;
                    }
                    return;
                } else if (obj instanceof short[]) {
                    short[] sArr = (short[]) obj;
                    this.d.b(91, sArr.length);
                    while (i3 < sArr.length) {
                        this.d.b(83, this.a.a((int) sArr[i3]).a);
                        i3++;
                    }
                    return;
                } else if (obj instanceof char[]) {
                    char[] cArr = (char[]) obj;
                    this.d.b(91, cArr.length);
                    while (i3 < cArr.length) {
                        this.d.b(67, this.a.a((int) cArr[i3]).a);
                        i3++;
                    }
                    return;
                } else if (obj instanceof int[]) {
                    int[] iArr = (int[]) obj;
                    this.d.b(91, iArr.length);
                    while (i3 < iArr.length) {
                        this.d.b(73, this.a.a(iArr[i3]).a);
                        i3++;
                    }
                    return;
                } else if (obj instanceof long[]) {
                    long[] jArr = (long[]) obj;
                    this.d.b(91, jArr.length);
                    while (i3 < jArr.length) {
                        this.d.b(74, this.a.a(jArr[i3]).a);
                        i3++;
                    }
                    return;
                } else if (obj instanceof float[]) {
                    float[] fArr = (float[]) obj;
                    this.d.b(91, fArr.length);
                    while (i3 < fArr.length) {
                        this.d.b(70, this.a.a(fArr[i3]).a);
                        i3++;
                    }
                    return;
                } else if (!(obj instanceof double[])) {
                    Item a = this.a.a(obj);
                    this.d.b(".s.IFJDCS".charAt(a.b), a.a);
                    return;
                } else {
                    double[] dArr = (double[]) obj;
                    this.d.b(91, dArr.length);
                    while (i3 < dArr.length) {
                        this.d.b(68, this.a.a(dArr[i3]).a);
                        i3++;
                    }
                    return;
                }
            } else {
                byteVector = this.d;
                i = 99;
                classWriter = this.a;
                descriptor = ((Type) obj).getDescriptor();
            }
        }
        i2 = classWriter.newUTF8(descriptor);
        byteVector.b(i, i2);
    }

    @Override // com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.AnnotationVisitor
    public AnnotationVisitor visitAnnotation(String str, String str2) {
        this.b++;
        if (this.c) {
            this.d.putShort(this.a.newUTF8(str));
        }
        this.d.b(64, this.a.newUTF8(str2)).putShort(0);
        ClassWriter classWriter = this.a;
        ByteVector byteVector = this.d;
        return new AnnotationWriter(classWriter, true, byteVector, byteVector, byteVector.b - 2);
    }

    @Override // com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.AnnotationVisitor
    public AnnotationVisitor visitArray(String str) {
        this.b++;
        if (this.c) {
            this.d.putShort(this.a.newUTF8(str));
        }
        this.d.b(91, 0);
        ClassWriter classWriter = this.a;
        ByteVector byteVector = this.d;
        return new AnnotationWriter(classWriter, false, byteVector, byteVector, byteVector.b - 2);
    }

    @Override // com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.AnnotationVisitor
    public void visitEnd() {
        ByteVector byteVector = this.e;
        if (byteVector != null) {
            byte[] bArr = byteVector.a;
            int i = this.f;
            int i2 = this.b;
            bArr[i] = (byte) (i2 >>> 8);
            bArr[i + 1] = (byte) i2;
        }
    }

    @Override // com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.AnnotationVisitor
    public void visitEnum(String str, String str2, String str3) {
        this.b++;
        if (this.c) {
            this.d.putShort(this.a.newUTF8(str));
        }
        this.d.b(101, this.a.newUTF8(str2)).putShort(this.a.newUTF8(str3));
    }
}
