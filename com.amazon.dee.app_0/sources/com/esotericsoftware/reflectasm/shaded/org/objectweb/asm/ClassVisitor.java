package com.esotericsoftware.reflectasm.shaded.org.objectweb.asm;
/* loaded from: classes2.dex */
public abstract class ClassVisitor {
    protected final int api;
    protected ClassVisitor cv;

    public ClassVisitor(int i) {
        this(i, null);
    }

    public ClassVisitor(int i, ClassVisitor classVisitor) {
        this.api = i;
        this.cv = classVisitor;
    }

    public void visit(int i, int i2, String str, String str2, String str3, String[] strArr) {
        ClassVisitor classVisitor = this.cv;
        if (classVisitor != null) {
            classVisitor.visit(i, i2, str, str2, str3, strArr);
        }
    }

    public AnnotationVisitor visitAnnotation(String str, boolean z) {
        ClassVisitor classVisitor = this.cv;
        if (classVisitor != null) {
            return classVisitor.visitAnnotation(str, z);
        }
        return null;
    }

    public void visitAttribute(Attribute attribute) {
        ClassVisitor classVisitor = this.cv;
        if (classVisitor != null) {
            classVisitor.visitAttribute(attribute);
        }
    }

    public void visitEnd() {
        ClassVisitor classVisitor = this.cv;
        if (classVisitor != null) {
            classVisitor.visitEnd();
        }
    }

    public FieldVisitor visitField(int i, String str, String str2, String str3, Object obj) {
        ClassVisitor classVisitor = this.cv;
        if (classVisitor != null) {
            return classVisitor.visitField(i, str, str2, str3, obj);
        }
        return null;
    }

    public void visitInnerClass(String str, String str2, String str3, int i) {
        ClassVisitor classVisitor = this.cv;
        if (classVisitor != null) {
            classVisitor.visitInnerClass(str, str2, str3, i);
        }
    }

    public MethodVisitor visitMethod(int i, String str, String str2, String str3, String[] strArr) {
        ClassVisitor classVisitor = this.cv;
        if (classVisitor != null) {
            return classVisitor.visitMethod(i, str, str2, str3, strArr);
        }
        return null;
    }

    public void visitOuterClass(String str, String str2, String str3) {
        ClassVisitor classVisitor = this.cv;
        if (classVisitor != null) {
            classVisitor.visitOuterClass(str, str2, str3);
        }
    }

    public void visitSource(String str, String str2) {
        ClassVisitor classVisitor = this.cv;
        if (classVisitor != null) {
            classVisitor.visitSource(str, str2);
        }
    }
}
