package com.esotericsoftware.reflectasm;

import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.MetricsConstants;
import com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.ClassWriter;
import com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Label;
import com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.MethodVisitor;
import com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Opcodes;
import com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Type;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
/* loaded from: classes2.dex */
public abstract class FieldAccess {
    private String[] fieldNames;
    private Class[] fieldTypes;

    private static void insertConstructor(ClassWriter classWriter) {
        MethodVisitor visitMethod = classWriter.visitMethod(1, "<init>", "()V", null, null);
        visitMethod.visitCode();
        visitMethod.visitVarInsn(25, 0);
        visitMethod.visitMethodInsn(183, "com/esotericsoftware/reflectasm/FieldAccess", "<init>", "()V");
        visitMethod.visitInsn(177);
        visitMethod.visitMaxs(1, 1);
        visitMethod.visitEnd();
    }

    private static void insertGetObject(ClassWriter classWriter, String str, ArrayList<Field> arrayList) {
        int i;
        MethodVisitor visitMethod = classWriter.visitMethod(1, MetricsConstants.Method.CACHE_GET, "(Ljava/lang/Object;I)Ljava/lang/Object;", null, null);
        visitMethod.visitCode();
        visitMethod.visitVarInsn(21, 2);
        if (!arrayList.isEmpty()) {
            i = 5;
            Label[] labelArr = new Label[arrayList.size()];
            int length = labelArr.length;
            for (int i2 = 0; i2 < length; i2++) {
                labelArr[i2] = new Label();
            }
            Label label = new Label();
            visitMethod.visitTableSwitchInsn(0, labelArr.length - 1, label, labelArr);
            int length2 = labelArr.length;
            for (int i3 = 0; i3 < length2; i3++) {
                Field field = arrayList.get(i3);
                visitMethod.visitLabel(labelArr[i3]);
                visitMethod.visitFrame(3, 0, null, 0, null);
                visitMethod.visitVarInsn(25, 1);
                visitMethod.visitTypeInsn(192, str);
                visitMethod.visitFieldInsn(180, str, field.getName(), Type.getDescriptor(field.getType()));
                switch (Type.getType(field.getType()).getSort()) {
                    case 1:
                        visitMethod.visitMethodInsn(184, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;");
                        break;
                    case 2:
                        visitMethod.visitMethodInsn(184, "java/lang/Character", "valueOf", "(C)Ljava/lang/Character;");
                        break;
                    case 3:
                        visitMethod.visitMethodInsn(184, "java/lang/Byte", "valueOf", "(B)Ljava/lang/Byte;");
                        break;
                    case 4:
                        visitMethod.visitMethodInsn(184, "java/lang/Short", "valueOf", "(S)Ljava/lang/Short;");
                        break;
                    case 5:
                        visitMethod.visitMethodInsn(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
                        break;
                    case 6:
                        visitMethod.visitMethodInsn(184, "java/lang/Float", "valueOf", "(F)Ljava/lang/Float;");
                        break;
                    case 7:
                        visitMethod.visitMethodInsn(184, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;");
                        break;
                    case 8:
                        visitMethod.visitMethodInsn(184, "java/lang/Double", "valueOf", "(D)Ljava/lang/Double;");
                        break;
                }
                visitMethod.visitInsn(176);
            }
            visitMethod.visitLabel(label);
            visitMethod.visitFrame(3, 0, null, 0, null);
        } else {
            i = 6;
        }
        insertThrowExceptionForFieldNotFound(visitMethod);
        visitMethod.visitMaxs(i, 3);
        visitMethod.visitEnd();
    }

    private static void insertGetPrimitive(ClassWriter classWriter, String str, ArrayList<Field> arrayList, Type type) {
        String str2;
        int i;
        boolean z;
        String descriptor = type.getDescriptor();
        int i2 = 172;
        switch (type.getSort()) {
            case 1:
                str2 = "getBoolean";
                break;
            case 2:
                str2 = "getChar";
                break;
            case 3:
                str2 = "getByte";
                break;
            case 4:
                str2 = "getShort";
                break;
            case 5:
                str2 = "getInt";
                break;
            case 6:
                i2 = 174;
                str2 = "getFloat";
                break;
            case 7:
                i2 = 173;
                str2 = "getLong";
                break;
            case 8:
                i2 = 175;
                str2 = "getDouble";
                break;
            default:
                i2 = 176;
                str2 = MetricsConstants.Method.CACHE_GET;
                break;
        }
        MethodVisitor visitMethod = classWriter.visitMethod(1, str2, GeneratedOutlineSupport1.outline72("(Ljava/lang/Object;I)", descriptor), null, null);
        visitMethod.visitCode();
        visitMethod.visitVarInsn(21, 2);
        if (!arrayList.isEmpty()) {
            Label[] labelArr = new Label[arrayList.size()];
            Label label = new Label();
            int length = labelArr.length;
            int i3 = 0;
            int i4 = 0;
            boolean z2 = false;
            while (true) {
                boolean z3 = true;
                if (i4 < length) {
                    if (Type.getType(arrayList.get(i4).getType()).equals(type)) {
                        labelArr[i4] = new Label();
                    } else {
                        labelArr[i4] = label;
                        z2 = true;
                    }
                    i4++;
                } else {
                    Label label2 = new Label();
                    visitMethod.visitTableSwitchInsn(0, labelArr.length - 1, label2, labelArr);
                    int length2 = labelArr.length;
                    while (i3 < length2) {
                        Field field = arrayList.get(i3);
                        if (!labelArr[i3].equals(label)) {
                            visitMethod.visitLabel(labelArr[i3]);
                            visitMethod.visitFrame(3, 0, null, 0, null);
                            z = true;
                            visitMethod.visitVarInsn(25, 1);
                            visitMethod.visitTypeInsn(192, str);
                            visitMethod.visitFieldInsn(180, str, field.getName(), descriptor);
                            visitMethod.visitInsn(i2);
                        } else {
                            z = z3;
                        }
                        i3++;
                        z3 = z;
                    }
                    if (z2) {
                        visitMethod.visitLabel(label);
                        visitMethod.visitFrame(3, 0, null, 0, null);
                        insertThrowExceptionForFieldType(visitMethod, type.getClassName());
                    }
                    visitMethod.visitLabel(label2);
                    visitMethod.visitFrame(3, 0, null, 0, null);
                    i = 5;
                }
            }
        } else {
            i = 6;
        }
        MethodVisitor insertThrowExceptionForFieldNotFound = insertThrowExceptionForFieldNotFound(visitMethod);
        insertThrowExceptionForFieldNotFound.visitMaxs(i, 3);
        insertThrowExceptionForFieldNotFound.visitEnd();
    }

    private static void insertGetString(ClassWriter classWriter, String str, ArrayList<Field> arrayList) {
        int i;
        int i2;
        MethodVisitor visitMethod = classWriter.visitMethod(1, "getString", "(Ljava/lang/Object;I)Ljava/lang/String;", null, null);
        visitMethod.visitCode();
        visitMethod.visitVarInsn(21, 2);
        if (!arrayList.isEmpty()) {
            i = 5;
            Label[] labelArr = new Label[arrayList.size()];
            Label label = new Label();
            int length = labelArr.length;
            int i3 = 0;
            boolean z = false;
            for (int i4 = 0; i4 < length; i4++) {
                if (arrayList.get(i4).getType().equals(String.class)) {
                    labelArr[i4] = new Label();
                } else {
                    labelArr[i4] = label;
                    z = true;
                }
            }
            Label label2 = new Label();
            visitMethod.visitTableSwitchInsn(0, labelArr.length - 1, label2, labelArr);
            int length2 = labelArr.length;
            while (i3 < length2) {
                if (!labelArr[i3].equals(label)) {
                    visitMethod.visitLabel(labelArr[i3]);
                    i2 = length2;
                    visitMethod.visitFrame(3, 0, null, 0, null);
                    visitMethod.visitVarInsn(25, 1);
                    visitMethod.visitTypeInsn(192, str);
                    visitMethod.visitFieldInsn(180, str, arrayList.get(i3).getName(), "Ljava/lang/String;");
                    visitMethod.visitInsn(176);
                } else {
                    i2 = length2;
                }
                i3++;
                length2 = i2;
            }
            if (z) {
                visitMethod.visitLabel(label);
                visitMethod.visitFrame(3, 0, null, 0, null);
                insertThrowExceptionForFieldType(visitMethod, "String");
            }
            visitMethod.visitLabel(label2);
            visitMethod.visitFrame(3, 0, null, 0, null);
        } else {
            i = 6;
        }
        insertThrowExceptionForFieldNotFound(visitMethod);
        visitMethod.visitMaxs(i, 3);
        visitMethod.visitEnd();
    }

    private static void insertSetObject(ClassWriter classWriter, String str, ArrayList<Field> arrayList) {
        int i;
        MethodVisitor visitMethod = classWriter.visitMethod(1, "set", "(Ljava/lang/Object;ILjava/lang/Object;)V", null, null);
        visitMethod.visitCode();
        visitMethod.visitVarInsn(21, 2);
        if (!arrayList.isEmpty()) {
            i = 5;
            Label[] labelArr = new Label[arrayList.size()];
            int length = labelArr.length;
            for (int i2 = 0; i2 < length; i2++) {
                labelArr[i2] = new Label();
            }
            Label label = new Label();
            visitMethod.visitTableSwitchInsn(0, labelArr.length - 1, label, labelArr);
            int length2 = labelArr.length;
            for (int i3 = 0; i3 < length2; i3++) {
                Field field = arrayList.get(i3);
                Type type = Type.getType(field.getType());
                visitMethod.visitLabel(labelArr[i3]);
                visitMethod.visitFrame(3, 0, null, 0, null);
                visitMethod.visitVarInsn(25, 1);
                visitMethod.visitTypeInsn(192, str);
                visitMethod.visitVarInsn(25, 3);
                switch (type.getSort()) {
                    case 1:
                        visitMethod.visitTypeInsn(192, "java/lang/Boolean");
                        visitMethod.visitMethodInsn(182, "java/lang/Boolean", "booleanValue", "()Z");
                        break;
                    case 2:
                        visitMethod.visitTypeInsn(192, "java/lang/Character");
                        visitMethod.visitMethodInsn(182, "java/lang/Character", "charValue", "()C");
                        break;
                    case 3:
                        visitMethod.visitTypeInsn(192, "java/lang/Byte");
                        visitMethod.visitMethodInsn(182, "java/lang/Byte", "byteValue", "()B");
                        break;
                    case 4:
                        visitMethod.visitTypeInsn(192, "java/lang/Short");
                        visitMethod.visitMethodInsn(182, "java/lang/Short", "shortValue", "()S");
                        break;
                    case 5:
                        visitMethod.visitTypeInsn(192, "java/lang/Integer");
                        visitMethod.visitMethodInsn(182, "java/lang/Integer", "intValue", "()I");
                        break;
                    case 6:
                        visitMethod.visitTypeInsn(192, "java/lang/Float");
                        visitMethod.visitMethodInsn(182, "java/lang/Float", "floatValue", "()F");
                        break;
                    case 7:
                        visitMethod.visitTypeInsn(192, "java/lang/Long");
                        visitMethod.visitMethodInsn(182, "java/lang/Long", "longValue", "()J");
                        break;
                    case 8:
                        visitMethod.visitTypeInsn(192, "java/lang/Double");
                        visitMethod.visitMethodInsn(182, "java/lang/Double", "doubleValue", "()D");
                        break;
                    case 9:
                        visitMethod.visitTypeInsn(192, type.getDescriptor());
                        break;
                    case 10:
                        visitMethod.visitTypeInsn(192, type.getInternalName());
                        break;
                }
                visitMethod.visitFieldInsn(181, str, field.getName(), type.getDescriptor());
                visitMethod.visitInsn(177);
            }
            visitMethod.visitLabel(label);
            visitMethod.visitFrame(3, 0, null, 0, null);
        } else {
            i = 6;
        }
        MethodVisitor insertThrowExceptionForFieldNotFound = insertThrowExceptionForFieldNotFound(visitMethod);
        insertThrowExceptionForFieldNotFound.visitMaxs(i, 4);
        insertThrowExceptionForFieldNotFound.visitEnd();
    }

    private static void insertSetPrimitive(ClassWriter classWriter, String str, ArrayList<Field> arrayList, Type type) {
        String str2;
        String str3;
        int i;
        String str4;
        int i2;
        String descriptor = type.getDescriptor();
        int i3 = 4;
        switch (type.getSort()) {
            case 1:
                str2 = "setBoolean";
                str3 = str2;
                i = 21;
                break;
            case 2:
                str2 = "setChar";
                str3 = str2;
                i = 21;
                break;
            case 3:
                str2 = "setByte";
                str3 = str2;
                i = 21;
                break;
            case 4:
                str2 = "setShort";
                str3 = str2;
                i = 21;
                break;
            case 5:
                str2 = "setInt";
                str3 = str2;
                i = 21;
                break;
            case 6:
                i = 23;
                str3 = "setFloat";
                break;
            case 7:
                i = 22;
                str4 = "setLong";
                str3 = str4;
                i3 = 5;
                break;
            case 8:
                i = 24;
                str4 = "setDouble";
                str3 = str4;
                i3 = 5;
                break;
            default:
                str3 = "set";
                i = 25;
                break;
        }
        MethodVisitor visitMethod = classWriter.visitMethod(1, str3, GeneratedOutlineSupport1.outline75("(Ljava/lang/Object;I", descriptor, ")V"), null, null);
        visitMethod.visitCode();
        visitMethod.visitVarInsn(21, 2);
        if (!arrayList.isEmpty()) {
            Label[] labelArr = new Label[arrayList.size()];
            Label label = new Label();
            int length = labelArr.length;
            int i4 = 0;
            int i5 = 0;
            boolean z = false;
            while (true) {
                int i6 = 1;
                if (i5 < length) {
                    if (Type.getType(arrayList.get(i5).getType()).equals(type)) {
                        labelArr[i5] = new Label();
                    } else {
                        labelArr[i5] = label;
                        z = true;
                    }
                    i5++;
                } else {
                    Label label2 = new Label();
                    visitMethod.visitTableSwitchInsn(0, labelArr.length - 1, label2, labelArr);
                    int length2 = labelArr.length;
                    while (i4 < length2) {
                        if (!labelArr[i4].equals(label)) {
                            visitMethod.visitLabel(labelArr[i4]);
                            visitMethod.visitFrame(3, 0, null, 0, null);
                            visitMethod.visitVarInsn(25, i6);
                            visitMethod.visitTypeInsn(192, str);
                            visitMethod.visitVarInsn(i, 3);
                            visitMethod.visitFieldInsn(181, str, arrayList.get(i4).getName(), descriptor);
                            visitMethod.visitInsn(177);
                        }
                        i4++;
                        i6 = 1;
                    }
                    if (z) {
                        visitMethod.visitLabel(label);
                        visitMethod.visitFrame(3, 0, null, 0, null);
                        insertThrowExceptionForFieldType(visitMethod, type.getClassName());
                    }
                    visitMethod.visitLabel(label2);
                    visitMethod.visitFrame(3, 0, null, 0, null);
                    i2 = 5;
                }
            }
        } else {
            i2 = 6;
        }
        MethodVisitor insertThrowExceptionForFieldNotFound = insertThrowExceptionForFieldNotFound(visitMethod);
        insertThrowExceptionForFieldNotFound.visitMaxs(i2, i3);
        insertThrowExceptionForFieldNotFound.visitEnd();
    }

    private static MethodVisitor insertThrowExceptionForFieldNotFound(MethodVisitor methodVisitor) {
        methodVisitor.visitTypeInsn(187, "java/lang/IllegalArgumentException");
        methodVisitor.visitInsn(89);
        methodVisitor.visitTypeInsn(187, "java/lang/StringBuilder");
        methodVisitor.visitInsn(89);
        methodVisitor.visitLdcInsn("Field not found: ");
        methodVisitor.visitMethodInsn(183, "java/lang/StringBuilder", "<init>", "(Ljava/lang/String;)V");
        methodVisitor.visitVarInsn(21, 2);
        methodVisitor.visitMethodInsn(182, "java/lang/StringBuilder", "append", "(I)Ljava/lang/StringBuilder;");
        methodVisitor.visitMethodInsn(182, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;");
        methodVisitor.visitMethodInsn(183, "java/lang/IllegalArgumentException", "<init>", "(Ljava/lang/String;)V");
        methodVisitor.visitInsn(191);
        return methodVisitor;
    }

    private static MethodVisitor insertThrowExceptionForFieldType(MethodVisitor methodVisitor, String str) {
        methodVisitor.visitTypeInsn(187, "java/lang/IllegalArgumentException");
        methodVisitor.visitInsn(89);
        methodVisitor.visitTypeInsn(187, "java/lang/StringBuilder");
        methodVisitor.visitInsn(89);
        methodVisitor.visitLdcInsn("Field not declared as " + str + RealTimeTextConstants.COLON_SPACE);
        methodVisitor.visitMethodInsn(183, "java/lang/StringBuilder", "<init>", "(Ljava/lang/String;)V");
        methodVisitor.visitVarInsn(21, 2);
        methodVisitor.visitMethodInsn(182, "java/lang/StringBuilder", "append", "(I)Ljava/lang/StringBuilder;");
        methodVisitor.visitMethodInsn(182, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;");
        methodVisitor.visitMethodInsn(183, "java/lang/IllegalArgumentException", "<init>", "(Ljava/lang/String;)V");
        methodVisitor.visitInsn(191);
        return methodVisitor;
    }

    public abstract Object get(Object obj, int i);

    public Object get(Object obj, String str) {
        return get(obj, getIndex(str));
    }

    public abstract boolean getBoolean(Object obj, int i);

    public abstract byte getByte(Object obj, int i);

    public abstract char getChar(Object obj, int i);

    public abstract double getDouble(Object obj, int i);

    public int getFieldCount() {
        return this.fieldTypes.length;
    }

    public String[] getFieldNames() {
        return this.fieldNames;
    }

    public Class[] getFieldTypes() {
        return this.fieldTypes;
    }

    public abstract float getFloat(Object obj, int i);

    public int getIndex(String str) {
        int length = this.fieldNames.length;
        for (int i = 0; i < length; i++) {
            if (this.fieldNames[i].equals(str)) {
                return i;
            }
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("Unable to find public field: ", str));
    }

    public abstract int getInt(Object obj, int i);

    public abstract long getLong(Object obj, int i);

    public abstract short getShort(Object obj, int i);

    public abstract String getString(Object obj, int i);

    public abstract void set(Object obj, int i, Object obj2);

    public void set(Object obj, String str, Object obj2) {
        set(obj, getIndex(str), obj2);
    }

    public abstract void setBoolean(Object obj, int i, boolean z);

    public abstract void setByte(Object obj, int i, byte b);

    public abstract void setChar(Object obj, int i, char c);

    public abstract void setDouble(Object obj, int i, double d);

    public abstract void setFloat(Object obj, int i, float f);

    public abstract void setInt(Object obj, int i, int i2);

    public abstract void setLong(Object obj, int i, long j);

    public abstract void setShort(Object obj, int i, short s);

    public static FieldAccess get(Class cls) {
        Class<?> defineClass;
        Field[] declaredFields;
        ArrayList arrayList = new ArrayList();
        Class cls2 = cls;
        while (true) {
            if (cls2 == Object.class) {
                break;
            }
            for (Field field : cls2.getDeclaredFields()) {
                int modifiers = field.getModifiers();
                if (!Modifier.isStatic(modifiers) && !Modifier.isPrivate(modifiers)) {
                    arrayList.add(field);
                }
            }
            cls2 = cls2.getSuperclass();
        }
        String[] strArr = new String[arrayList.size()];
        Class[] clsArr = new Class[arrayList.size()];
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            strArr[i] = ((Field) arrayList.get(i)).getName();
            clsArr[i] = ((Field) arrayList.get(i)).getType();
        }
        String name = cls.getName();
        String outline72 = GeneratedOutlineSupport1.outline72(name, "FieldAccess");
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
                ClassWriter classWriter = new ClassWriter(0);
                classWriter.visit(Opcodes.V1_1, 33, replace, null, "com/esotericsoftware/reflectasm/FieldAccess", null);
                insertConstructor(classWriter);
                insertGetObject(classWriter, replace2, arrayList);
                insertSetObject(classWriter, replace2, arrayList);
                insertGetPrimitive(classWriter, replace2, arrayList, Type.BOOLEAN_TYPE);
                insertSetPrimitive(classWriter, replace2, arrayList, Type.BOOLEAN_TYPE);
                insertGetPrimitive(classWriter, replace2, arrayList, Type.BYTE_TYPE);
                insertSetPrimitive(classWriter, replace2, arrayList, Type.BYTE_TYPE);
                insertGetPrimitive(classWriter, replace2, arrayList, Type.SHORT_TYPE);
                insertSetPrimitive(classWriter, replace2, arrayList, Type.SHORT_TYPE);
                insertGetPrimitive(classWriter, replace2, arrayList, Type.INT_TYPE);
                insertSetPrimitive(classWriter, replace2, arrayList, Type.INT_TYPE);
                insertGetPrimitive(classWriter, replace2, arrayList, Type.LONG_TYPE);
                insertSetPrimitive(classWriter, replace2, arrayList, Type.LONG_TYPE);
                insertGetPrimitive(classWriter, replace2, arrayList, Type.DOUBLE_TYPE);
                insertSetPrimitive(classWriter, replace2, arrayList, Type.DOUBLE_TYPE);
                insertGetPrimitive(classWriter, replace2, arrayList, Type.FLOAT_TYPE);
                insertSetPrimitive(classWriter, replace2, arrayList, Type.FLOAT_TYPE);
                insertGetPrimitive(classWriter, replace2, arrayList, Type.CHAR_TYPE);
                insertSetPrimitive(classWriter, replace2, arrayList, Type.CHAR_TYPE);
                insertGetString(classWriter, replace2, arrayList);
                classWriter.visitEnd();
                defineClass = accessClassLoader.defineClass(outline72, classWriter.toByteArray());
            }
        }
        try {
            FieldAccess fieldAccess = (FieldAccess) defineClass.newInstance();
            fieldAccess.fieldNames = strArr;
            fieldAccess.fieldTypes = clsArr;
            return fieldAccess;
        } catch (Exception e) {
            throw new RuntimeException(GeneratedOutlineSupport1.outline72("Error constructing field access class: ", outline72), e);
        }
    }
}
