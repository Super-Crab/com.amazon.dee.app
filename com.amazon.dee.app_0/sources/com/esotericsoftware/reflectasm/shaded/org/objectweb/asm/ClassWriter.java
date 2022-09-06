package com.esotericsoftware.reflectasm.shaded.org.objectweb.asm;
/* loaded from: classes2.dex */
public class ClassWriter extends ClassVisitor {
    public static final int COMPUTE_FRAMES = 2;
    public static final int COMPUTE_MAXS = 1;
    static final byte[] a;
    ByteVector A;
    FieldWriter B;
    FieldWriter C;
    MethodWriter D;
    MethodWriter E;
    private short G;
    Item[] H;
    String I;
    private final boolean J;
    private final boolean K;
    boolean L;
    ClassReader M;
    int b;
    int c;
    final ByteVector d;
    Item[] e;
    int f;
    final Item g;
    final Item h;
    final Item i;
    final Item j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;
    private int[] p;
    private int q;
    private ByteVector r;
    private int s;
    private int t;
    private AnnotationWriter u;
    private AnnotationWriter v;
    private Attribute w;
    private int x;
    private ByteVector y;
    int z;

    static {
        byte[] bArr = new byte[220];
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = (byte) ("AAAAAAAAAAAAAAAABCLMMDDDDDEEEEEEEEEEEEEEEEEEEEAAAAAAAADDDDDEEEEEEEEEEEEEEEEEEEEAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAANAAAAAAAAAAAAAAAAAAAAJJJJJJJJJJJJJJJJDOPAAAAAAGGGGGGGHIFBFAAFFAARQJJKKJJJJJJJJJJJJJJJJJJ".charAt(i) - 'A');
        }
        a = bArr;
    }

    public ClassWriter(int i) {
        super(262144);
        boolean z = true;
        this.c = 1;
        this.d = new ByteVector();
        this.e = new Item[256];
        this.f = (int) (this.e.length * 0.75d);
        this.g = new Item();
        this.h = new Item();
        this.i = new Item();
        this.j = new Item();
        this.K = (i & 1) != 0;
        this.J = (i & 2) == 0 ? false : z;
    }

    public ClassWriter(ClassReader classReader, int i) {
        this(i);
        classReader.a(this);
        this.M = classReader;
    }

    private Item a(Item item) {
        Item[] itemArr = this.e;
        Item item2 = itemArr[item.j % itemArr.length];
        while (item2 != null && (item2.b != item.b || !item.a(item2))) {
            item2 = item2.k;
        }
        return item2;
    }

    private void a(int i, int i2, int i3) {
        this.d.b(i, i2).putShort(i3);
    }

    private Item b(String str) {
        this.h.a(8, str, null, null);
        Item a2 = a(this.h);
        if (a2 == null) {
            this.d.b(8, newUTF8(str));
            int i = this.c;
            this.c = i + 1;
            Item item = new Item(i, this.h);
            b(item);
            return item;
        }
        return a2;
    }

    private void b(int i, int i2, int i3) {
        this.d.a(i, i2).putShort(i3);
    }

    private void b(Item item) {
        if (this.c + this.G > this.f) {
            int length = this.e.length;
            int i = (length * 2) + 1;
            Item[] itemArr = new Item[i];
            for (int i2 = length - 1; i2 >= 0; i2--) {
                Item item2 = this.e[i2];
                while (item2 != null) {
                    int length2 = item2.j % itemArr.length;
                    Item item3 = item2.k;
                    item2.k = itemArr[length2];
                    itemArr[length2] = item2;
                    item2 = item3;
                }
            }
            this.e = itemArr;
            this.f = (int) (i * 0.75d);
        }
        int i3 = item.j;
        Item[] itemArr2 = this.e;
        int length3 = i3 % itemArr2.length;
        item.k = itemArr2[length3];
        itemArr2[length3] = item;
    }

    private Item c(Item item) {
        this.G = (short) (this.G + 1);
        Item item2 = new Item(this.G, this.g);
        b(item2);
        if (this.H == null) {
            this.H = new Item[16];
        }
        short s = this.G;
        Item[] itemArr = this.H;
        if (s == itemArr.length) {
            Item[] itemArr2 = new Item[itemArr.length * 2];
            System.arraycopy(itemArr, 0, itemArr2, 0, itemArr.length);
            this.H = itemArr2;
        }
        this.H[this.G] = item2;
        return item2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int a(int i, int i2) {
        Item item = this.h;
        item.b = 32;
        item.d = i | (i2 << 32);
        item.j = (i + 32 + i2) & Integer.MAX_VALUE;
        Item a2 = a(item);
        if (a2 == null) {
            Item[] itemArr = this.H;
            String str = itemArr[i].g;
            String str2 = itemArr[i2].g;
            this.h.c = c(getCommonSuperClass(str, str2));
            a2 = new Item(0, this.h);
            b(a2);
        }
        return a2.c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int a(String str, int i) {
        Item item = this.g;
        item.b = 31;
        item.c = i;
        item.g = str;
        item.j = (str.hashCode() + 31 + i) & Integer.MAX_VALUE;
        Item a2 = a(this.g);
        if (a2 == null) {
            a2 = c(this.g);
        }
        return a2.a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Item a(double d) {
        this.g.a(d);
        Item a2 = a(this.g);
        if (a2 == null) {
            this.d.putByte(6).putLong(this.g.d);
            Item item = new Item(this.c, this.g);
            this.c += 2;
            b(item);
            return item;
        }
        return a2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Item a(float f) {
        this.g.a(f);
        Item a2 = a(this.g);
        if (a2 == null) {
            this.d.putByte(4).putInt(this.g.c);
            int i = this.c;
            this.c = i + 1;
            Item item = new Item(i, this.g);
            b(item);
            return item;
        }
        return a2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Item a(int i) {
        this.g.a(i);
        Item a2 = a(this.g);
        if (a2 == null) {
            this.d.putByte(3).putInt(i);
            int i2 = this.c;
            this.c = i2 + 1;
            Item item = new Item(i2, this.g);
            b(item);
            return item;
        }
        return a2;
    }

    Item a(int i, String str, String str2, String str3) {
        int newMethod;
        this.j.a(i + 20, str, str2, str3);
        Item a2 = a(this.j);
        if (a2 == null) {
            if (i <= 4) {
                newMethod = newField(str, str2, str3);
            } else {
                newMethod = newMethod(str, str2, str3, i == 9);
            }
            b(15, i, newMethod);
            int i2 = this.c;
            this.c = i2 + 1;
            Item item = new Item(i2, this.j);
            b(item);
            return item;
        }
        return a2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Item a(long j) {
        this.g.a(j);
        Item a2 = a(this.g);
        if (a2 == null) {
            this.d.putByte(5).putLong(j);
            Item item = new Item(this.c, this.g);
            this.c += 2;
            b(item);
            return item;
        }
        return a2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Item a(Object obj) {
        if (obj instanceof Integer) {
            return a(((Integer) obj).intValue());
        }
        if (obj instanceof Byte) {
            return a(((Byte) obj).intValue());
        }
        if (obj instanceof Character) {
            return a((int) ((Character) obj).charValue());
        }
        if (obj instanceof Short) {
            return a(((Short) obj).intValue());
        }
        if (obj instanceof Boolean) {
            return a(((Boolean) obj).booleanValue() ? 1 : 0);
        }
        if (obj instanceof Float) {
            return a(((Float) obj).floatValue());
        }
        if (obj instanceof Long) {
            return a(((Long) obj).longValue());
        }
        if (obj instanceof Double) {
            return a(((Double) obj).doubleValue());
        }
        if (obj instanceof String) {
            return b((String) obj);
        }
        if (obj instanceof Type) {
            Type type = (Type) obj;
            int sort = type.getSort();
            return sort == 9 ? a(type.getDescriptor()) : sort == 10 ? a(type.getInternalName()) : m6852c(type.getDescriptor());
        } else if (obj instanceof Handle) {
            Handle handle = (Handle) obj;
            return a(handle.a, handle.b, handle.c, handle.d);
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("value ");
            stringBuffer.append(obj);
            throw new IllegalArgumentException(stringBuffer.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Item a(String str) {
        this.h.a(7, str, null, null);
        Item a2 = a(this.h);
        if (a2 == null) {
            this.d.b(7, newUTF8(str));
            int i = this.c;
            this.c = i + 1;
            Item item = new Item(i, this.h);
            b(item);
            return item;
        }
        return a2;
    }

    Item a(String str, String str2) {
        this.h.a(12, str, str2, null);
        Item a2 = a(this.h);
        if (a2 == null) {
            a(12, newUTF8(str), newUTF8(str2));
            int i = this.c;
            this.c = i + 1;
            Item item = new Item(i, this.h);
            b(item);
            return item;
        }
        return a2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Item a(String str, String str2, Handle handle, Object... objArr) {
        int i;
        ByteVector byteVector = this.A;
        if (byteVector == null) {
            byteVector = new ByteVector();
            this.A = byteVector;
        }
        int i2 = byteVector.b;
        int hashCode = handle.hashCode();
        byteVector.putShort(newHandle(handle.a, handle.b, handle.c, handle.d));
        int length = objArr.length;
        byteVector.putShort(length);
        int i3 = hashCode;
        for (Object obj : objArr) {
            i3 ^= obj.hashCode();
            byteVector.putShort(newConst(obj));
        }
        byte[] bArr = byteVector.a;
        int i4 = (length + 2) << 1;
        int i5 = Integer.MAX_VALUE & i3;
        Item[] itemArr = this.e;
        Item item = itemArr[i5 % itemArr.length];
        loop1: while (item != null) {
            if (item.b == 33 && item.j == i5) {
                int i6 = item.c;
                for (int i7 = 0; i7 < i4; i7++) {
                    if (bArr[i2 + i7] != bArr[i6 + i7]) {
                        break;
                    }
                }
                break loop1;
            }
            item = item.k;
        }
        if (item != null) {
            i = item.a;
            byteVector.b = i2;
        } else {
            i = this.z;
            this.z = i + 1;
            Item item2 = new Item(i);
            item2.a(i2, i5);
            b(item2);
        }
        this.i.a(str, str2, i);
        Item a2 = a(this.i);
        if (a2 == null) {
            a(18, i, newNameType(str, str2));
            int i8 = this.c;
            this.c = i8 + 1;
            Item item3 = new Item(i8, this.i);
            b(item3);
            return item3;
        }
        return a2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Item a(String str, String str2, String str3) {
        this.i.a(9, str, str2, str3);
        Item a2 = a(this.i);
        if (a2 == null) {
            a(9, newClass(str), newNameType(str2, str3));
            int i = this.c;
            this.c = i + 1;
            Item item = new Item(i, this.i);
            b(item);
            return item;
        }
        return a2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Item a(String str, String str2, String str3, boolean z) {
        int i = z ? 11 : 10;
        this.i.a(i, str, str2, str3);
        Item a2 = a(this.i);
        if (a2 == null) {
            a(i, newClass(str), newNameType(str2, str3));
            int i2 = this.c;
            this.c = i2 + 1;
            Item item = new Item(i2, this.i);
            b(item);
            return item;
        }
        return a2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int c(String str) {
        this.g.a(30, str, null, null);
        Item a2 = a(this.g);
        if (a2 == null) {
            a2 = c(this.g);
        }
        return a2.a;
    }

    /* renamed from: c  reason: collision with other method in class */
    Item m6852c(String str) {
        this.h.a(16, str, null, null);
        Item a2 = a(this.h);
        if (a2 == null) {
            this.d.b(16, newUTF8(str));
            int i = this.c;
            this.c = i + 1;
            Item item = new Item(i, this.h);
            b(item);
            return item;
        }
        return a2;
    }

    protected String getCommonSuperClass(String str, String str2) {
        ClassLoader classLoader = ClassWriter.class.getClassLoader();
        try {
            Class<?> cls = Class.forName(str.replace('/', '.'), false, classLoader);
            Class<?> cls2 = Class.forName(str2.replace('/', '.'), false, classLoader);
            if (cls.isAssignableFrom(cls2)) {
                return str;
            }
            if (cls2.isAssignableFrom(cls)) {
                return str2;
            }
            if (cls.isInterface() || cls2.isInterface()) {
                return "java/lang/Object";
            }
            do {
                cls = cls.getSuperclass();
            } while (!cls.isAssignableFrom(cls2));
            return cls.getName().replace('.', '/');
        } catch (Exception e) {
            throw new RuntimeException(e.toString());
        }
    }

    public int newClass(String str) {
        return a(str).a;
    }

    public int newConst(Object obj) {
        return a(obj).a;
    }

    public int newField(String str, String str2, String str3) {
        return a(str, str2, str3).a;
    }

    public int newHandle(int i, String str, String str2, String str3) {
        return a(i, str, str2, str3).a;
    }

    public int newInvokeDynamic(String str, String str2, Handle handle, Object... objArr) {
        return a(str, str2, handle, objArr).a;
    }

    public int newMethod(String str, String str2, String str3, boolean z) {
        return a(str, str2, str3, z).a;
    }

    public int newMethodType(String str) {
        return m6852c(str).a;
    }

    public int newNameType(String str, String str2) {
        return a(str, str2).a;
    }

    public int newUTF8(String str) {
        this.g.a(1, str, null, null);
        Item a2 = a(this.g);
        if (a2 == null) {
            this.d.putByte(1).putUTF8(str);
            int i = this.c;
            this.c = i + 1;
            a2 = new Item(i, this.g);
            b(a2);
        }
        return a2.a;
    }

    public byte[] toByteArray() {
        int i;
        String str;
        String str2;
        ByteVector byteVector;
        if (this.c <= 32767) {
            int i2 = (this.o * 2) + 24;
            int i3 = 0;
            for (FieldWriter fieldWriter = this.B; fieldWriter != null; fieldWriter = (FieldWriter) fieldWriter.fv) {
                i3++;
                i2 += fieldWriter.a();
            }
            int i4 = 0;
            for (MethodWriter methodWriter = this.D; methodWriter != null; methodWriter = (MethodWriter) methodWriter.mv) {
                i4++;
                i2 += methodWriter.a();
            }
            ByteVector byteVector2 = this.A;
            if (byteVector2 != null) {
                i2 += byteVector2.b + 8;
                newUTF8("BootstrapMethods");
                i = 1;
            } else {
                i = 0;
            }
            if (this.m != 0) {
                i++;
                i2 += 8;
                newUTF8("Signature");
            }
            if (this.q != 0) {
                i++;
                i2 += 8;
                newUTF8("SourceFile");
            }
            ByteVector byteVector3 = this.r;
            if (byteVector3 != null) {
                i++;
                i2 += byteVector3.b + 4;
                newUTF8("SourceDebugExtension");
            }
            if (this.s != 0) {
                i++;
                i2 += 10;
                newUTF8("EnclosingMethod");
            }
            if ((this.k & 131072) != 0) {
                i++;
                i2 += 6;
                newUTF8("Deprecated");
            }
            int i5 = this.k;
            if ((i5 & 4096) != 0 && ((this.b & 65535) < 49 || (i5 & 262144) != 0)) {
                i++;
                i2 += 6;
                newUTF8("Synthetic");
            }
            ByteVector byteVector4 = this.y;
            if (byteVector4 != null) {
                i++;
                i2 += byteVector4.b + 8;
                newUTF8("InnerClasses");
            }
            AnnotationWriter annotationWriter = this.u;
            if (annotationWriter != null) {
                i++;
                i2 += annotationWriter.a() + 8;
                newUTF8("RuntimeVisibleAnnotations");
            }
            AnnotationWriter annotationWriter2 = this.v;
            if (annotationWriter2 != null) {
                i++;
                i2 += annotationWriter2.a() + 8;
                newUTF8("RuntimeInvisibleAnnotations");
            }
            int i6 = i2;
            Attribute attribute = this.w;
            if (attribute != null) {
                int a2 = i + attribute.a();
                str = "Deprecated";
                str2 = "EnclosingMethod";
                i6 += this.w.a(this, null, 0, -1, -1);
                i = a2;
            } else {
                str = "Deprecated";
                str2 = "EnclosingMethod";
            }
            ByteVector byteVector5 = new ByteVector(i6 + this.d.b);
            byteVector5.putInt(-889275714).putInt(this.b);
            ByteVector putShort = byteVector5.putShort(this.c);
            ByteVector byteVector6 = this.d;
            putShort.putByteArray(byteVector6.a, 0, byteVector6.b);
            int i7 = this.k;
            byteVector5.putShort((~(393216 | ((i7 & 262144) / 64))) & i7).putShort(this.l).putShort(this.n);
            byteVector5.putShort(this.o);
            for (int i8 = 0; i8 < this.o; i8++) {
                byteVector5.putShort(this.p[i8]);
            }
            byteVector5.putShort(i3);
            for (FieldWriter fieldWriter2 = this.B; fieldWriter2 != null; fieldWriter2 = (FieldWriter) fieldWriter2.fv) {
                fieldWriter2.a(byteVector5);
            }
            byteVector5.putShort(i4);
            for (MethodWriter methodWriter2 = this.D; methodWriter2 != null; methodWriter2 = (MethodWriter) methodWriter2.mv) {
                methodWriter2.a(byteVector5);
            }
            byteVector5.putShort(i);
            if (this.A != null) {
                byteVector5.putShort(newUTF8("BootstrapMethods"));
                byteVector5.putInt(this.A.b + 2).putShort(this.z);
                ByteVector byteVector7 = this.A;
                byteVector5.putByteArray(byteVector7.a, 0, byteVector7.b);
            }
            if (this.m != 0) {
                byteVector5.putShort(newUTF8("Signature")).putInt(2).putShort(this.m);
            }
            if (this.q != 0) {
                byteVector5.putShort(newUTF8("SourceFile")).putInt(2).putShort(this.q);
            }
            ByteVector byteVector8 = this.r;
            if (byteVector8 != null) {
                int i9 = byteVector8.b - 2;
                byteVector5.putShort(newUTF8("SourceDebugExtension")).putInt(i9);
                byteVector5.putByteArray(this.r.a, 2, i9);
            }
            if (this.s != 0) {
                byteVector5.putShort(newUTF8(str2)).putInt(4);
                byteVector5.putShort(this.s).putShort(this.t);
            }
            if ((this.k & 131072) != 0) {
                byteVector5.putShort(newUTF8(str)).putInt(0);
            }
            int i10 = this.k;
            if ((i10 & 4096) != 0 && ((this.b & 65535) < 49 || (i10 & 262144) != 0)) {
                byteVector5.putShort(newUTF8("Synthetic")).putInt(0);
            }
            if (this.y != null) {
                byteVector5.putShort(newUTF8("InnerClasses"));
                byteVector5.putInt(this.y.b + 2).putShort(this.x);
                ByteVector byteVector9 = this.y;
                byteVector5.putByteArray(byteVector9.a, 0, byteVector9.b);
            }
            if (this.u != null) {
                byteVector5.putShort(newUTF8("RuntimeVisibleAnnotations"));
                this.u.a(byteVector5);
            }
            if (this.v != null) {
                byteVector5.putShort(newUTF8("RuntimeInvisibleAnnotations"));
                this.v.a(byteVector5);
            }
            Attribute attribute2 = this.w;
            if (attribute2 != null) {
                byteVector = byteVector5;
                attribute2.a(this, null, 0, -1, -1, byteVector);
            } else {
                byteVector = byteVector5;
            }
            if (!this.L) {
                return byteVector.a;
            }
            ClassWriter classWriter = new ClassWriter(2);
            new ClassReader(byteVector.a).accept(classWriter, 4);
            return classWriter.toByteArray();
        }
        throw new RuntimeException("Class file too large!");
    }

    @Override // com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.ClassVisitor
    public final void visit(int i, int i2, String str, String str2, String str3, String[] strArr) {
        this.b = i;
        this.k = i2;
        this.l = newClass(str);
        this.I = str;
        if (str2 != null) {
            this.m = newUTF8(str2);
        }
        this.n = str3 == null ? 0 : newClass(str3);
        if (strArr == null || strArr.length <= 0) {
            return;
        }
        this.o = strArr.length;
        this.p = new int[this.o];
        for (int i3 = 0; i3 < this.o; i3++) {
            this.p[i3] = newClass(strArr[i3]);
        }
    }

    @Override // com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.ClassVisitor
    public final AnnotationVisitor visitAnnotation(String str, boolean z) {
        ByteVector byteVector = new ByteVector();
        byteVector.putShort(newUTF8(str)).putShort(0);
        AnnotationWriter annotationWriter = new AnnotationWriter(this, true, byteVector, byteVector, 2);
        if (z) {
            annotationWriter.g = this.u;
            this.u = annotationWriter;
        } else {
            annotationWriter.g = this.v;
            this.v = annotationWriter;
        }
        return annotationWriter;
    }

    @Override // com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.ClassVisitor
    public final void visitAttribute(Attribute attribute) {
        attribute.a = this.w;
        this.w = attribute;
    }

    @Override // com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.ClassVisitor
    public final void visitEnd() {
    }

    @Override // com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.ClassVisitor
    public final FieldVisitor visitField(int i, String str, String str2, String str3, Object obj) {
        return new FieldWriter(this, i, str, str2, str3, obj);
    }

    @Override // com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.ClassVisitor
    public final void visitInnerClass(String str, String str2, String str3, int i) {
        if (this.y == null) {
            this.y = new ByteVector();
        }
        this.x++;
        int i2 = 0;
        this.y.putShort(str == null ? 0 : newClass(str));
        this.y.putShort(str2 == null ? 0 : newClass(str2));
        ByteVector byteVector = this.y;
        if (str3 != null) {
            i2 = newUTF8(str3);
        }
        byteVector.putShort(i2);
        this.y.putShort(i);
    }

    @Override // com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.ClassVisitor
    public final MethodVisitor visitMethod(int i, String str, String str2, String str3, String[] strArr) {
        return new MethodWriter(this, i, str, str2, str3, strArr, this.K, this.J);
    }

    @Override // com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.ClassVisitor
    public final void visitOuterClass(String str, String str2, String str3) {
        this.s = newClass(str);
        if (str2 == null || str3 == null) {
            return;
        }
        this.t = newNameType(str2, str3);
    }

    @Override // com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.ClassVisitor
    public final void visitSource(String str, String str2) {
        if (str != null) {
            this.q = newUTF8(str);
        }
        if (str2 != null) {
            this.r = new ByteVector().putUTF8(str2);
        }
    }
}
