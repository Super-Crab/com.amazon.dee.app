package com.esotericsoftware.reflectasm;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.ClassWriter;
import com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.MethodVisitor;
import com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Opcodes;
import java.lang.reflect.Modifier;
/* loaded from: classes2.dex */
public abstract class ConstructorAccess<T> {
    boolean isNonStaticMemberClass;

    public static <T> ConstructorAccess<T> get(Class<T> cls) {
        Class<?> defineClass;
        Class<?> enclosingClass = cls.getEnclosingClass();
        boolean z = enclosingClass != null && cls.isMemberClass() && !Modifier.isStatic(cls.getModifiers());
        String name = cls.getName();
        String outline72 = GeneratedOutlineSupport1.outline72(name, "ConstructorAccess");
        if (outline72.startsWith("java.")) {
            outline72 = GeneratedOutlineSupport1.outline72("reflectasm.", outline72);
        }
        AccessClassLoader accessClassLoader = AccessClassLoader.get(cls);
        synchronized (accessClassLoader) {
            try {
                defineClass = accessClassLoader.loadClass(outline72);
            } catch (ClassNotFoundException unused) {
                String replace = outline72.replace('.', '/');
                String replace2 = name.replace('.', '/');
                String str = null;
                if (!z) {
                    try {
                        if (Modifier.isPrivate(cls.getDeclaredConstructor(null).getModifiers())) {
                            throw new RuntimeException("Class cannot be created (the no-arg constructor is private): " + cls.getName());
                        }
                    } catch (Exception e) {
                        throw new RuntimeException("Class cannot be created (missing no-arg constructor): " + cls.getName(), e);
                    }
                } else {
                    str = enclosingClass.getName().replace('.', '/');
                    try {
                        if (Modifier.isPrivate(cls.getDeclaredConstructor(enclosingClass).getModifiers())) {
                            throw new RuntimeException("Non-static member class cannot be created (the enclosing class constructor is private): " + cls.getName());
                        }
                    } catch (Exception e2) {
                        throw new RuntimeException("Non-static member class cannot be created (missing enclosing class constructor): " + cls.getName(), e2);
                    }
                }
                String str2 = str;
                ClassWriter classWriter = new ClassWriter(0);
                classWriter.visit(Opcodes.V1_1, 33, replace, null, "com/esotericsoftware/reflectasm/ConstructorAccess", null);
                insertConstructor(classWriter);
                insertNewInstance(classWriter, replace2);
                insertNewInstanceInner(classWriter, replace2, str2);
                classWriter.visitEnd();
                defineClass = accessClassLoader.defineClass(outline72, classWriter.toByteArray());
            }
        }
        try {
            ConstructorAccess<T> constructorAccess = (ConstructorAccess) defineClass.newInstance();
            constructorAccess.isNonStaticMemberClass = z;
            return constructorAccess;
        } catch (Exception e3) {
            throw new RuntimeException(GeneratedOutlineSupport1.outline72("Error constructing constructor access class: ", outline72), e3);
        }
    }

    private static void insertConstructor(ClassWriter classWriter) {
        MethodVisitor visitMethod = classWriter.visitMethod(1, "<init>", "()V", null, null);
        visitMethod.visitCode();
        visitMethod.visitVarInsn(25, 0);
        visitMethod.visitMethodInsn(183, "com/esotericsoftware/reflectasm/ConstructorAccess", "<init>", "()V");
        visitMethod.visitInsn(177);
        visitMethod.visitMaxs(1, 1);
        visitMethod.visitEnd();
    }

    static void insertNewInstance(ClassWriter classWriter, String str) {
        MethodVisitor visitMethod = classWriter.visitMethod(1, "newInstance", "()Ljava/lang/Object;", null, null);
        visitMethod.visitCode();
        visitMethod.visitTypeInsn(187, str);
        visitMethod.visitInsn(89);
        visitMethod.visitMethodInsn(183, str, "<init>", "()V");
        visitMethod.visitInsn(176);
        visitMethod.visitMaxs(2, 1);
        visitMethod.visitEnd();
    }

    static void insertNewInstanceInner(ClassWriter classWriter, String str, String str2) {
        MethodVisitor visitMethod = classWriter.visitMethod(1, "newInstance", "(Ljava/lang/Object;)Ljava/lang/Object;", null, null);
        visitMethod.visitCode();
        if (str2 != null) {
            visitMethod.visitTypeInsn(187, str);
            visitMethod.visitInsn(89);
            visitMethod.visitVarInsn(25, 1);
            visitMethod.visitTypeInsn(192, str2);
            visitMethod.visitInsn(89);
            visitMethod.visitMethodInsn(182, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
            visitMethod.visitInsn(87);
            visitMethod.visitMethodInsn(183, str, "<init>", "(L" + str2 + ";)V");
            visitMethod.visitInsn(176);
            visitMethod.visitMaxs(4, 2);
        } else {
            visitMethod.visitTypeInsn(187, "java/lang/UnsupportedOperationException");
            visitMethod.visitInsn(89);
            visitMethod.visitLdcInsn("Not an inner class.");
            visitMethod.visitMethodInsn(183, "java/lang/UnsupportedOperationException", "<init>", "(Ljava/lang/String;)V");
            visitMethod.visitInsn(191);
            visitMethod.visitMaxs(3, 2);
        }
        visitMethod.visitEnd();
    }

    public boolean isNonStaticMemberClass() {
        return this.isNonStaticMemberClass;
    }

    public abstract T newInstance();

    public abstract T newInstance(Object obj);
}
