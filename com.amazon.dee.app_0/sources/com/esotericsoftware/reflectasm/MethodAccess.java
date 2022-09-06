package com.esotericsoftware.reflectasm;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.ClassWriter;
import com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Label;
import com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.MethodVisitor;
import com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Opcodes;
import com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Type;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
/* loaded from: classes2.dex */
public abstract class MethodAccess {
    private String[] methodNames;
    private Class[][] parameterTypes;
    private Class[] returnTypes;

    private static void addDeclaredMethodsToList(Class cls, ArrayList<Method> arrayList) {
        Method[] declaredMethods;
        for (Method method : cls.getDeclaredMethods()) {
            int modifiers = method.getModifiers();
            if (!Modifier.isStatic(modifiers) && !Modifier.isPrivate(modifiers)) {
                arrayList.add(method);
            }
        }
    }

    public static MethodAccess get(Class cls) {
        Class[][] clsArr;
        Class[] clsArr2;
        MethodVisitor methodVisitor;
        Class<?> defineClass;
        int i;
        StringBuilder sb;
        Label label;
        MethodVisitor methodVisitor2;
        Class[][] clsArr3;
        Class[] clsArr4;
        Class[] clsArr5;
        ArrayList arrayList = new ArrayList();
        boolean isInterface = cls.isInterface();
        if (!isInterface) {
            for (Class cls2 = cls; cls2 != Object.class; cls2 = cls2.getSuperclass()) {
                addDeclaredMethodsToList(cls2, arrayList);
            }
        } else {
            recursiveAddInterfaceMethodsToList(cls, arrayList);
        }
        int size = arrayList.size();
        String[] strArr = new String[size];
        Class[][] clsArr6 = new Class[size];
        Class[] clsArr7 = new Class[size];
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            Method method = (Method) arrayList.get(i3);
            strArr[i3] = method.getName();
            clsArr6[i3] = method.getParameterTypes();
            clsArr7[i3] = method.getReturnType();
        }
        String name = cls.getName();
        String outline72 = GeneratedOutlineSupport1.outline72(name, "MethodAccess");
        if (outline72.startsWith("java.")) {
            outline72 = GeneratedOutlineSupport1.outline72("reflectasm.", outline72);
        }
        AccessClassLoader accessClassLoader = AccessClassLoader.get(cls);
        synchronized (accessClassLoader) {
            try {
                defineClass = accessClassLoader.loadClass(outline72);
                clsArr = clsArr6;
                clsArr2 = clsArr7;
            } catch (ClassNotFoundException unused) {
                String replace = outline72.replace('.', '/');
                String replace2 = name.replace('.', '/');
                ClassWriter classWriter = new ClassWriter(1);
                classWriter.visit(Opcodes.V1_1, 33, replace, null, "com/esotericsoftware/reflectasm/MethodAccess", null);
                MethodVisitor visitMethod = classWriter.visitMethod(1, "<init>", "()V", null, null);
                visitMethod.visitCode();
                visitMethod.visitVarInsn(25, 0);
                visitMethod.visitMethodInsn(183, "com/esotericsoftware/reflectasm/MethodAccess", "<init>", "()V");
                visitMethod.visitInsn(177);
                visitMethod.visitMaxs(0, 0);
                visitMethod.visitEnd();
                MethodVisitor visitMethod2 = classWriter.visitMethod(129, "invoke", "(Ljava/lang/Object;I[Ljava/lang/Object;)Ljava/lang/Object;", null, null);
                visitMethod2.visitCode();
                if (!arrayList.isEmpty()) {
                    visitMethod2.visitVarInsn(25, 1);
                    visitMethod2.visitTypeInsn(192, replace2);
                    visitMethod2.visitVarInsn(58, 4);
                    visitMethod2.visitVarInsn(21, 2);
                    Label[] labelArr = new Label[size];
                    for (int i4 = 0; i4 < size; i4++) {
                        labelArr[i4] = new Label();
                    }
                    Label label2 = new Label();
                    visitMethod2.visitTableSwitchInsn(0, labelArr.length - 1, label2, labelArr);
                    StringBuilder sb2 = new StringBuilder(128);
                    int i5 = 0;
                    while (i2 < size) {
                        visitMethod2.visitLabel(labelArr[i2]);
                        if (i2 == 0) {
                            Object[] objArr = new Object[1];
                            objArr[i5] = replace2;
                            i = i5;
                            sb = sb2;
                            label = label2;
                            methodVisitor2 = visitMethod2;
                            visitMethod2.visitFrame(1, 1, objArr, 0, null);
                        } else {
                            i = i5;
                            sb = sb2;
                            label = label2;
                            methodVisitor2 = visitMethod2;
                            methodVisitor2.visitFrame(3, 0, null, 0, null);
                        }
                        visitMethod2 = methodVisitor2;
                        visitMethod2.visitVarInsn(25, 4);
                        int i6 = i;
                        StringBuilder sb3 = sb;
                        sb3.setLength(i6);
                        sb3.append('(');
                        String str = strArr[i2];
                        Class[] clsArr8 = clsArr6[i2];
                        Class cls3 = clsArr7[i2];
                        Label[] labelArr2 = labelArr;
                        while (i6 < clsArr8.length) {
                            int i7 = size;
                            visitMethod2.visitVarInsn(25, 3);
                            visitMethod2.visitIntInsn(16, i6);
                            visitMethod2.visitInsn(50);
                            Type type = Type.getType(clsArr8[i6]);
                            switch (type.getSort()) {
                                case 1:
                                    clsArr3 = clsArr6;
                                    clsArr4 = clsArr7;
                                    clsArr5 = clsArr8;
                                    visitMethod2.visitTypeInsn(192, "java/lang/Boolean");
                                    visitMethod2.visitMethodInsn(182, "java/lang/Boolean", "booleanValue", "()Z");
                                    break;
                                case 2:
                                    clsArr3 = clsArr6;
                                    clsArr4 = clsArr7;
                                    clsArr5 = clsArr8;
                                    visitMethod2.visitTypeInsn(192, "java/lang/Character");
                                    visitMethod2.visitMethodInsn(182, "java/lang/Character", "charValue", "()C");
                                    break;
                                case 3:
                                    clsArr3 = clsArr6;
                                    clsArr4 = clsArr7;
                                    clsArr5 = clsArr8;
                                    visitMethod2.visitTypeInsn(192, "java/lang/Byte");
                                    visitMethod2.visitMethodInsn(182, "java/lang/Byte", "byteValue", "()B");
                                    break;
                                case 4:
                                    clsArr3 = clsArr6;
                                    clsArr4 = clsArr7;
                                    clsArr5 = clsArr8;
                                    visitMethod2.visitTypeInsn(192, "java/lang/Short");
                                    visitMethod2.visitMethodInsn(182, "java/lang/Short", "shortValue", "()S");
                                    break;
                                case 5:
                                    clsArr3 = clsArr6;
                                    clsArr4 = clsArr7;
                                    clsArr5 = clsArr8;
                                    visitMethod2.visitTypeInsn(192, "java/lang/Integer");
                                    visitMethod2.visitMethodInsn(182, "java/lang/Integer", "intValue", "()I");
                                    break;
                                case 6:
                                    clsArr3 = clsArr6;
                                    clsArr4 = clsArr7;
                                    clsArr5 = clsArr8;
                                    visitMethod2.visitTypeInsn(192, "java/lang/Float");
                                    visitMethod2.visitMethodInsn(182, "java/lang/Float", "floatValue", "()F");
                                    break;
                                case 7:
                                    clsArr3 = clsArr6;
                                    clsArr4 = clsArr7;
                                    clsArr5 = clsArr8;
                                    visitMethod2.visitTypeInsn(192, "java/lang/Long");
                                    visitMethod2.visitMethodInsn(182, "java/lang/Long", "longValue", "()J");
                                    break;
                                case 8:
                                    clsArr5 = clsArr8;
                                    visitMethod2.visitTypeInsn(192, "java/lang/Double");
                                    clsArr4 = clsArr7;
                                    clsArr3 = clsArr6;
                                    visitMethod2.visitMethodInsn(182, "java/lang/Double", "doubleValue", "()D");
                                    break;
                                case 9:
                                    clsArr5 = clsArr8;
                                    visitMethod2.visitTypeInsn(192, type.getDescriptor());
                                    clsArr3 = clsArr6;
                                    clsArr4 = clsArr7;
                                    break;
                                case 10:
                                    clsArr5 = clsArr8;
                                    visitMethod2.visitTypeInsn(192, type.getInternalName());
                                    clsArr3 = clsArr6;
                                    clsArr4 = clsArr7;
                                    break;
                                default:
                                    clsArr3 = clsArr6;
                                    clsArr4 = clsArr7;
                                    clsArr5 = clsArr8;
                                    break;
                            }
                            sb3.append(type.getDescriptor());
                            i6++;
                            clsArr8 = clsArr5;
                            size = i7;
                            clsArr7 = clsArr4;
                            clsArr6 = clsArr3;
                        }
                        int i8 = size;
                        Class[][] clsArr9 = clsArr6;
                        Class[] clsArr10 = clsArr7;
                        sb3.append(')');
                        sb3.append(Type.getDescriptor(cls3));
                        visitMethod2.visitMethodInsn(isInterface ? 185 : 182, replace2, str, sb3.toString());
                        switch (Type.getType(cls3).getSort()) {
                            case 0:
                                visitMethod2.visitInsn(1);
                                break;
                            case 1:
                                visitMethod2.visitMethodInsn(184, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;");
                                break;
                            case 2:
                                visitMethod2.visitMethodInsn(184, "java/lang/Character", "valueOf", "(C)Ljava/lang/Character;");
                                break;
                            case 3:
                                visitMethod2.visitMethodInsn(184, "java/lang/Byte", "valueOf", "(B)Ljava/lang/Byte;");
                                break;
                            case 4:
                                visitMethod2.visitMethodInsn(184, "java/lang/Short", "valueOf", "(S)Ljava/lang/Short;");
                                break;
                            case 5:
                                visitMethod2.visitMethodInsn(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
                                break;
                            case 6:
                                visitMethod2.visitMethodInsn(184, "java/lang/Float", "valueOf", "(F)Ljava/lang/Float;");
                                break;
                            case 7:
                                visitMethod2.visitMethodInsn(184, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;");
                                break;
                            case 8:
                                visitMethod2.visitMethodInsn(184, "java/lang/Double", "valueOf", "(D)Ljava/lang/Double;");
                                break;
                        }
                        visitMethod2.visitInsn(176);
                        i2++;
                        i5 = 0;
                        sb2 = sb3;
                        labelArr = labelArr2;
                        size = i8;
                        clsArr7 = clsArr10;
                        clsArr6 = clsArr9;
                        label2 = label;
                    }
                    clsArr = clsArr6;
                    clsArr2 = clsArr7;
                    visitMethod2.visitLabel(label2);
                    methodVisitor = visitMethod2;
                    visitMethod2.visitFrame(3, 0, null, 0, null);
                } else {
                    clsArr = clsArr6;
                    clsArr2 = clsArr7;
                    methodVisitor = visitMethod2;
                }
                methodVisitor.visitTypeInsn(187, "java/lang/IllegalArgumentException");
                methodVisitor.visitInsn(89);
                methodVisitor.visitTypeInsn(187, "java/lang/StringBuilder");
                methodVisitor.visitInsn(89);
                methodVisitor.visitLdcInsn("Method not found: ");
                methodVisitor.visitMethodInsn(183, "java/lang/StringBuilder", "<init>", "(Ljava/lang/String;)V");
                methodVisitor.visitVarInsn(21, 2);
                methodVisitor.visitMethodInsn(182, "java/lang/StringBuilder", "append", "(I)Ljava/lang/StringBuilder;");
                methodVisitor.visitMethodInsn(182, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;");
                methodVisitor.visitMethodInsn(183, "java/lang/IllegalArgumentException", "<init>", "(Ljava/lang/String;)V");
                methodVisitor.visitInsn(191);
                methodVisitor.visitMaxs(0, 0);
                methodVisitor.visitEnd();
                classWriter.visitEnd();
                defineClass = accessClassLoader.defineClass(outline72, classWriter.toByteArray());
            }
        }
        try {
            MethodAccess methodAccess = (MethodAccess) defineClass.newInstance();
            methodAccess.methodNames = strArr;
            methodAccess.parameterTypes = clsArr;
            methodAccess.returnTypes = clsArr2;
            return methodAccess;
        } catch (Exception e) {
            throw new RuntimeException(GeneratedOutlineSupport1.outline72("Error constructing method access class: ", outline72), e);
        }
    }

    private static void recursiveAddInterfaceMethodsToList(Class cls, ArrayList<Method> arrayList) {
        addDeclaredMethodsToList(cls, arrayList);
        for (Class<?> cls2 : cls.getInterfaces()) {
            recursiveAddInterfaceMethodsToList(cls2, arrayList);
        }
    }

    public int getIndex(String str) {
        int length = this.methodNames.length;
        for (int i = 0; i < length; i++) {
            if (this.methodNames[i].equals(str)) {
                return i;
            }
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("Unable to find public method: ", str));
    }

    public String[] getMethodNames() {
        return this.methodNames;
    }

    public Class[][] getParameterTypes() {
        return this.parameterTypes;
    }

    public Class[] getReturnTypes() {
        return this.returnTypes;
    }

    public abstract Object invoke(Object obj, int i, Object... objArr);

    public Object invoke(Object obj, String str, Class[] clsArr, Object... objArr) {
        return invoke(obj, getIndex(str, clsArr), objArr);
    }

    public Object invoke(Object obj, String str, Object... objArr) {
        return invoke(obj, getIndex(str, objArr == null ? 0 : objArr.length), objArr);
    }

    public int getIndex(String str, Class... clsArr) {
        int length = this.methodNames.length;
        for (int i = 0; i < length; i++) {
            if (this.methodNames[i].equals(str) && Arrays.equals(clsArr, this.parameterTypes[i])) {
                return i;
            }
        }
        StringBuilder outline115 = GeneratedOutlineSupport1.outline115("Unable to find public method: ", str, " ");
        outline115.append(Arrays.toString(clsArr));
        throw new IllegalArgumentException(outline115.toString());
    }

    public int getIndex(String str, int i) {
        int length = this.methodNames.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (this.methodNames[i2].equals(str) && this.parameterTypes[i2].length == i) {
                return i2;
            }
        }
        throw new IllegalArgumentException("Unable to find public method: " + str + " with " + i + " params.");
    }
}
