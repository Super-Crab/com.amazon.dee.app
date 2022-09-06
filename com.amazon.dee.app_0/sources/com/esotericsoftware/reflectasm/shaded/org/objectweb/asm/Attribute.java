package com.esotericsoftware.reflectasm.shaded.org.objectweb.asm;
/* loaded from: classes2.dex */
public class Attribute {
    Attribute a;
    byte[] b;
    public final String type;

    /* JADX INFO: Access modifiers changed from: protected */
    public Attribute(String str) {
        this.type = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int a() {
        int i = 0;
        for (Attribute attribute = this; attribute != null; attribute = attribute.a) {
            i++;
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int a(ClassWriter classWriter, byte[] bArr, int i, int i2, int i3) {
        int i4 = 0;
        for (Attribute attribute = this; attribute != null; attribute = attribute.a) {
            classWriter.newUTF8(attribute.type);
            i4 += attribute.write(classWriter, bArr, i, i2, i3).b + 6;
        }
        return i4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(ClassWriter classWriter, byte[] bArr, int i, int i2, int i3, ByteVector byteVector) {
        for (Attribute attribute = this; attribute != null; attribute = attribute.a) {
            ByteVector write = attribute.write(classWriter, bArr, i, i2, i3);
            byteVector.putShort(classWriter.newUTF8(attribute.type)).putInt(write.b);
            byteVector.putByteArray(write.a, 0, write.b);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Label[] getLabels() {
        return null;
    }

    public boolean isCodeAttribute() {
        return false;
    }

    public boolean isUnknown() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Attribute read(ClassReader classReader, int i, int i2, char[] cArr, int i3, Label[] labelArr) {
        Attribute attribute = new Attribute(this.type);
        attribute.b = new byte[i2];
        System.arraycopy(classReader.b, i, attribute.b, 0, i2);
        return attribute;
    }

    protected ByteVector write(ClassWriter classWriter, byte[] bArr, int i, int i2, int i3) {
        ByteVector byteVector = new ByteVector();
        byte[] bArr2 = this.b;
        byteVector.a = bArr2;
        byteVector.b = bArr2.length;
        return byteVector;
    }
}
