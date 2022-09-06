package com.esotericsoftware.reflectasm.shaded.org.objectweb.asm;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class FieldWriter extends FieldVisitor {
    private final ClassWriter b;
    private final int c;
    private final int d;
    private final int e;
    private int f;
    private int g;
    private AnnotationWriter h;
    private AnnotationWriter i;
    private Attribute j;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FieldWriter(ClassWriter classWriter, int i, String str, String str2, String str3, Object obj) {
        super(262144);
        if (classWriter.B == null) {
            classWriter.B = this;
        } else {
            classWriter.C.fv = this;
        }
        classWriter.C = this;
        this.b = classWriter;
        this.c = i;
        this.d = classWriter.newUTF8(str);
        this.e = classWriter.newUTF8(str2);
        if (str3 != null) {
            this.f = classWriter.newUTF8(str3);
        }
        if (obj != null) {
            this.g = classWriter.a(obj).a;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int a() {
        int i;
        if (this.g != 0) {
            this.b.newUTF8("ConstantValue");
            i = 16;
        } else {
            i = 8;
        }
        int i2 = this.c;
        if ((i2 & 4096) != 0 && ((this.b.b & 65535) < 49 || (i2 & 262144) != 0)) {
            this.b.newUTF8("Synthetic");
            i += 6;
        }
        if ((this.c & 131072) != 0) {
            this.b.newUTF8("Deprecated");
            i += 6;
        }
        if (this.f != 0) {
            this.b.newUTF8("Signature");
            i += 8;
        }
        if (this.h != null) {
            this.b.newUTF8("RuntimeVisibleAnnotations");
            i += this.h.a() + 8;
        }
        if (this.i != null) {
            this.b.newUTF8("RuntimeInvisibleAnnotations");
            i += this.i.a() + 8;
        }
        Attribute attribute = this.j;
        return attribute != null ? i + attribute.a(this.b, null, 0, -1, -1) : i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(ByteVector byteVector) {
        int i = this.c;
        byteVector.putShort(i & (~(((i & 262144) / 64) | 393216))).putShort(this.d).putShort(this.e);
        int i2 = this.g != 0 ? 1 : 0;
        int i3 = this.c;
        if ((i3 & 4096) != 0 && ((this.b.b & 65535) < 49 || (i3 & 262144) != 0)) {
            i2++;
        }
        if ((this.c & 131072) != 0) {
            i2++;
        }
        if (this.f != 0) {
            i2++;
        }
        if (this.h != null) {
            i2++;
        }
        if (this.i != null) {
            i2++;
        }
        Attribute attribute = this.j;
        if (attribute != null) {
            i2 += attribute.a();
        }
        byteVector.putShort(i2);
        if (this.g != 0) {
            byteVector.putShort(this.b.newUTF8("ConstantValue"));
            byteVector.putInt(2).putShort(this.g);
        }
        int i4 = this.c;
        if ((i4 & 4096) != 0 && ((65535 & this.b.b) < 49 || (i4 & 262144) != 0)) {
            byteVector.putShort(this.b.newUTF8("Synthetic")).putInt(0);
        }
        if ((this.c & 131072) != 0) {
            byteVector.putShort(this.b.newUTF8("Deprecated")).putInt(0);
        }
        if (this.f != 0) {
            byteVector.putShort(this.b.newUTF8("Signature"));
            byteVector.putInt(2).putShort(this.f);
        }
        if (this.h != null) {
            byteVector.putShort(this.b.newUTF8("RuntimeVisibleAnnotations"));
            this.h.a(byteVector);
        }
        if (this.i != null) {
            byteVector.putShort(this.b.newUTF8("RuntimeInvisibleAnnotations"));
            this.i.a(byteVector);
        }
        Attribute attribute2 = this.j;
        if (attribute2 != null) {
            attribute2.a(this.b, null, 0, -1, -1, byteVector);
        }
    }

    @Override // com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.FieldVisitor
    public AnnotationVisitor visitAnnotation(String str, boolean z) {
        ByteVector byteVector = new ByteVector();
        byteVector.putShort(this.b.newUTF8(str)).putShort(0);
        AnnotationWriter annotationWriter = new AnnotationWriter(this.b, true, byteVector, byteVector, 2);
        if (z) {
            annotationWriter.g = this.h;
            this.h = annotationWriter;
        } else {
            annotationWriter.g = this.i;
            this.i = annotationWriter;
        }
        return annotationWriter;
    }

    @Override // com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.FieldVisitor
    public void visitAttribute(Attribute attribute) {
        attribute.a = this.j;
        this.j = attribute;
    }

    @Override // com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.FieldVisitor
    public void visitEnd() {
    }
}
