package com.esotericsoftware.kryo.serializers;

import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.esotericsoftware.kryo.Generics;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.NotNull;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.FieldSerializerUnsafeUtil;
import com.esotericsoftware.kryo.util.IntArray;
import com.esotericsoftware.kryo.util.ObjectMap;
import com.esotericsoftware.kryo.util.Util;
import com.esotericsoftware.minlog.Log;
import com.esotericsoftware.reflectasm.FieldAccess;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.security.AccessControlException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes2.dex */
public class FieldSerializer<T> extends Serializer<T> implements Comparator<CachedField> {
    static CachedFieldFactory asmFieldFactory;
    static CachedFieldFactory objectFieldFactory;
    static Method sortFieldsByOffsetMethod;
    static boolean unsafeAvailable;
    static CachedFieldFactory unsafeFieldFactory;
    static Class<?> unsafeUtilClass;
    Object access;
    private FieldSerializerAnnotationsUtil annotationsUtil;
    private boolean copyTransient;
    private CachedField[] fields;
    private boolean fieldsCanBeNull;
    private boolean fixedFieldTypes;
    private Class[] generics;
    private Generics genericsScope;
    private FieldSerializerGenericsUtil genericsUtil;
    private boolean hasObjectFields;
    private boolean ignoreSyntheticFields;
    final Kryo kryo;
    protected HashSet<CachedField> removedFields;
    private final boolean serializeTransient;
    private boolean setFieldsAsAccessible;
    private CachedField[] transientFields;
    final Class type;
    private final TypeVariable[] typeParameters;
    private FieldSerializerUnsafeUtil unsafeUtil;
    private boolean useAsmEnabled;
    private boolean useMemRegions;
    private boolean varIntsEnabled;

    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: classes2.dex */
    public @interface Bind {
        Class<? extends Serializer> value();
    }

    /* loaded from: classes2.dex */
    public interface CachedFieldFactory {
        CachedField createCachedField(Class cls, Field field, FieldSerializer fieldSerializer);
    }

    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: classes2.dex */
    public @interface Optional {
        String value();
    }

    static {
        try {
            unsafeUtilClass = FieldSerializer.class.getClassLoader().loadClass("com.esotericsoftware.kryo.util.UnsafeUtil");
            Method method = unsafeUtilClass.getMethod("unsafe", new Class[0]);
            sortFieldsByOffsetMethod = unsafeUtilClass.getMethod("sortFieldsByOffset", List.class);
            if (method.invoke(null, new Object[0]) == null) {
                return;
            }
            unsafeAvailable = true;
        } catch (Throwable unused) {
            if (!Log.TRACE) {
                return;
            }
            Log.trace("kryo", "sun.misc.Unsafe is unavailable.");
        }
    }

    public FieldSerializer(Kryo kryo, Class cls) {
        this.fields = new CachedField[0];
        this.transientFields = new CachedField[0];
        this.removedFields = new HashSet<>();
        this.fieldsCanBeNull = true;
        this.setFieldsAsAccessible = true;
        this.ignoreSyntheticFields = true;
        this.useMemRegions = false;
        this.copyTransient = true;
        this.serializeTransient = false;
        this.hasObjectFields = false;
        this.useAsmEnabled = !unsafeAvailable;
        this.varIntsEnabled = true;
        if (Log.TRACE) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Optimize ints: ");
            outline107.append(this.varIntsEnabled);
            Log.trace("kryo", outline107.toString());
        }
        this.kryo = kryo;
        this.type = cls;
        this.typeParameters = cls.getTypeParameters();
        this.useAsmEnabled = kryo.getAsmEnabled();
        if (!this.useAsmEnabled && !unsafeAvailable) {
            this.useAsmEnabled = true;
            if (Log.TRACE) {
                Log.trace("kryo", "sun.misc.Unsafe is unavailable, using ASM.");
            }
        }
        this.genericsUtil = new FieldSerializerGenericsUtil(this);
        this.unsafeUtil = FieldSerializerUnsafeUtil.Factory.getInstance(this);
        this.annotationsUtil = new FieldSerializerAnnotationsUtil(this);
        rebuildCachedFields();
    }

    private List<Field> buildValidFields(boolean z, List<Field> list, ObjectMap objectMap, IntArray intArray) {
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Field field = list.get(i);
            int modifiers = field.getModifiers();
            if (Modifier.isTransient(modifiers) == z && !Modifier.isStatic(modifiers) && (!field.isSynthetic() || !this.ignoreSyntheticFields)) {
                int i2 = 1;
                if (!field.isAccessible()) {
                    if (this.setFieldsAsAccessible) {
                        try {
                            field.setAccessible(true);
                        } catch (AccessControlException unused) {
                        }
                    }
                }
                Optional optional = (Optional) field.getAnnotation(Optional.class);
                if (optional == null || objectMap.containsKey(optional.value())) {
                    arrayList.add(field);
                    if (Modifier.isFinal(modifiers) || !Modifier.isPublic(modifiers) || !Modifier.isPublic(field.getType().getModifiers())) {
                        i2 = 0;
                    }
                    intArray.add(i2);
                }
            }
        }
        return arrayList;
    }

    private List<Field> buildValidFieldsFromCachedFields(CachedField[] cachedFieldArr, IntArray intArray) {
        ArrayList arrayList = new ArrayList(cachedFieldArr.length);
        for (CachedField cachedField : cachedFieldArr) {
            arrayList.add(cachedField.field);
            intArray.add(cachedField.accessIndex > -1 ? 1 : 0);
        }
        return arrayList;
    }

    private void createCachedFields(IntArray intArray, List<Field> list, List<CachedField> list2, int i) {
        if (!this.useAsmEnabled && this.useMemRegions) {
            this.unsafeUtil.createUnsafeCacheFieldsAndRegions(list, list2, i, intArray);
            return;
        }
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            Field field = list.get(i2);
            int i3 = -1;
            if (this.access != null && intArray.get(i + i2) == 1) {
                i3 = ((FieldAccess) this.access).getIndex(field.getName());
            }
            list2.add(newCachedField(field, list2.size(), i3));
        }
    }

    private CachedFieldFactory getAsmFieldFactory() {
        if (asmFieldFactory == null) {
            asmFieldFactory = new AsmCachedFieldFactory();
        }
        return asmFieldFactory;
    }

    private CachedFieldFactory getObjectFieldFactory() {
        if (objectFieldFactory == null) {
            objectFieldFactory = new ObjectCachedFieldFactory();
        }
        return objectFieldFactory;
    }

    private CachedFieldFactory getUnsafeFieldFactory() {
        if (unsafeFieldFactory == null) {
            try {
                unsafeFieldFactory = (CachedFieldFactory) getClass().getClassLoader().loadClass("com.esotericsoftware.kryo.serializers.UnsafeCachedFieldFactory").newInstance();
            } catch (Exception e) {
                throw new RuntimeException("Cannot create UnsafeFieldFactory", e);
            }
        }
        return unsafeFieldFactory;
    }

    @Override // com.esotericsoftware.kryo.Serializer
    public T copy(Kryo kryo, T t) {
        T createCopy = createCopy(kryo, t);
        kryo.reference(createCopy);
        if (this.copyTransient) {
            int length = this.transientFields.length;
            for (int i = 0; i < length; i++) {
                this.transientFields[i].copy(t, createCopy);
            }
        }
        int length2 = this.fields.length;
        for (int i2 = 0; i2 < length2; i2++) {
            this.fields[i2].copy(t, createCopy);
        }
        return createCopy;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public T create(Kryo kryo, Input input, Class<T> cls) {
        return (T) kryo.newInstance(cls);
    }

    protected T createCopy(Kryo kryo, T t) {
        return (T) kryo.newInstance(t.getClass());
    }

    public boolean getCopyTransient() {
        return this.copyTransient;
    }

    public CachedField getField(String str) {
        CachedField[] cachedFieldArr;
        for (CachedField cachedField : this.fields) {
            if (cachedField.field.getName().equals(str)) {
                return cachedField;
            }
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline38(this.type, GeneratedOutlineSupport1.outline115("Field \"", str, "\" not found on class: ")));
    }

    public CachedField[] getFields() {
        return this.fields;
    }

    public Class[] getGenerics() {
        return this.generics;
    }

    public final Generics getGenericsScope() {
        return this.genericsScope;
    }

    public Kryo getKryo() {
        return this.kryo;
    }

    public Class getType() {
        return this.type;
    }

    public boolean getUseAsmEnabled() {
        return this.useAsmEnabled;
    }

    public boolean getUseMemRegions() {
        return this.useMemRegions;
    }

    protected void initializeCachedFields() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CachedField newCachedField(Field field, int i, int i2) {
        CachedField newCachedFieldOfGenericType;
        boolean z = true;
        Class[] clsArr = {field.getType()};
        Type genericType = field.getGenericType();
        if (genericType == clsArr[0]) {
            if (Log.TRACE) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Field ");
                outline107.append(field.getName());
                outline107.append(RealTimeTextConstants.COLON_SPACE);
                outline107.append(clsArr[0]);
                Log.trace("kryo", outline107.toString());
            }
            newCachedFieldOfGenericType = newMatchingCachedField(field, i2, clsArr[0], genericType, null);
        } else {
            newCachedFieldOfGenericType = this.genericsUtil.newCachedFieldOfGenericType(field, i2, clsArr, genericType);
        }
        if (newCachedFieldOfGenericType instanceof ObjectField) {
            this.hasObjectFields = true;
        }
        newCachedFieldOfGenericType.field = field;
        newCachedFieldOfGenericType.varIntsEnabled = this.varIntsEnabled;
        if (!this.useAsmEnabled) {
            newCachedFieldOfGenericType.offset = this.unsafeUtil.getObjectFieldOffset(field);
        }
        newCachedFieldOfGenericType.access = (FieldAccess) this.access;
        newCachedFieldOfGenericType.accessIndex = i2;
        if (!this.fieldsCanBeNull || clsArr[0].isPrimitive() || field.isAnnotationPresent(NotNull.class)) {
            z = false;
        }
        newCachedFieldOfGenericType.canBeNull = z;
        if (this.kryo.isFinal(clsArr[0]) || this.fixedFieldTypes) {
            newCachedFieldOfGenericType.valueClass = clsArr[0];
        }
        return newCachedFieldOfGenericType;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CachedField newMatchingCachedField(Field field, int i, Class cls, Type type, Class[] clsArr) {
        if (i != -1) {
            return getAsmFieldFactory().createCachedField(cls, field, this);
        }
        if (!this.useAsmEnabled) {
            return getUnsafeFieldFactory().createCachedField(cls, field, this);
        }
        CachedField createCachedField = getObjectFieldFactory().createCachedField(cls, field, this);
        if (clsArr != null) {
            ((ObjectField) createCachedField).generics = clsArr;
            return createCachedField;
        }
        Class[] generics = FieldSerializerGenericsUtil.getGenerics(type, this.kryo);
        ((ObjectField) createCachedField).generics = generics;
        if (!Log.TRACE) {
            return createCachedField;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Field generics: ");
        outline107.append(Arrays.toString(generics));
        Log.trace("kryo", outline107.toString());
        return createCachedField;
    }

    @Override // com.esotericsoftware.kryo.Serializer
    /* renamed from: read */
    public T mo6848read(Kryo kryo, Input input, Class<T> cls) {
        try {
            if (this.typeParameters != null && this.generics != null) {
                rebuildCachedFields();
            }
            if (this.genericsScope != null) {
                kryo.pushGenericsScope(cls, this.genericsScope);
            }
            T create = create(kryo, input, cls);
            kryo.reference(create);
            for (CachedField cachedField : this.fields) {
                cachedField.read(input, create);
            }
            return create;
        } finally {
            if (this.genericsScope != null && kryo.getGenericsScope() != null) {
                kryo.popGenericsScope();
            }
        }
    }

    protected void rebuildCachedFields() {
        rebuildCachedFields(false);
    }

    public void removeField(String str) {
        CachedField[] cachedFieldArr;
        int i = 0;
        while (true) {
            CachedField[] cachedFieldArr2 = this.fields;
            if (i < cachedFieldArr2.length) {
                CachedField cachedField = cachedFieldArr2[i];
                if (cachedField.field.getName().equals(str)) {
                    CachedField[] cachedFieldArr3 = new CachedField[cachedFieldArr.length - 1];
                    System.arraycopy(this.fields, 0, cachedFieldArr3, 0, i);
                    System.arraycopy(this.fields, i + 1, cachedFieldArr3, i, cachedFieldArr3.length - i);
                    this.fields = cachedFieldArr3;
                    this.removedFields.add(cachedField);
                    return;
                }
                i++;
            } else {
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline38(this.type, GeneratedOutlineSupport1.outline115("Field \"", str, "\" not found on class: ")));
            }
        }
    }

    public void setCopyTransient(boolean z) {
        this.copyTransient = z;
    }

    public void setFieldsAsAccessible(boolean z) {
        this.setFieldsAsAccessible = z;
        if (Log.TRACE) {
            Log.trace("kryo", "setFieldsAsAccessible: " + z);
        }
        rebuildCachedFields();
    }

    public void setFieldsCanBeNull(boolean z) {
        this.fieldsCanBeNull = z;
        if (Log.TRACE) {
            Log.trace("kryo", "setFieldsCanBeNull: " + z);
        }
        rebuildCachedFields();
    }

    public void setFixedFieldTypes(boolean z) {
        this.fixedFieldTypes = z;
        if (Log.TRACE) {
            Log.trace("kryo", "setFixedFieldTypes: " + z);
        }
        rebuildCachedFields();
    }

    @Override // com.esotericsoftware.kryo.Serializer
    public void setGenerics(Kryo kryo, Class[] clsArr) {
        this.generics = clsArr;
        TypeVariable[] typeVariableArr = this.typeParameters;
        if (typeVariableArr == null || typeVariableArr.length <= 0) {
            return;
        }
        rebuildCachedFields(true);
    }

    public void setIgnoreSyntheticFields(boolean z) {
        this.ignoreSyntheticFields = z;
        if (Log.TRACE) {
            Log.trace("kryo", "setIgnoreSyntheticFields: " + z);
        }
        rebuildCachedFields();
    }

    public void setUseAsm(boolean z) {
        this.useAsmEnabled = z;
        if (!this.useAsmEnabled && !unsafeAvailable) {
            this.useAsmEnabled = true;
            if (Log.TRACE) {
                Log.trace("kryo", "sun.misc.Unsafe is unavailable, using ASM.");
            }
        }
        if (Log.TRACE) {
            Log.trace("kryo", "setUseAsm: " + z);
        }
        rebuildCachedFields();
    }

    @Override // com.esotericsoftware.kryo.Serializer
    public void write(Kryo kryo, Output output, T t) {
        if (Log.TRACE) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("FieldSerializer.write fields of class: ");
            outline107.append(t.getClass().getName());
            Log.trace("kryo", outline107.toString());
        }
        if (this.typeParameters != null && this.generics != null) {
            rebuildCachedFields();
        }
        Generics generics = this.genericsScope;
        if (generics != null) {
            kryo.pushGenericsScope(this.type, generics);
        }
        for (CachedField cachedField : this.fields) {
            cachedField.write(output, t);
        }
        if (this.genericsScope != null) {
            kryo.popGenericsScope();
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class CachedField<X> {
        FieldAccess access;
        boolean canBeNull;
        Field field;
        Serializer serializer;
        Class valueClass;
        int accessIndex = -1;
        long offset = -1;
        boolean varIntsEnabled = true;

        public abstract void copy(Object obj, Object obj2);

        public Field getField() {
            return this.field;
        }

        public Serializer getSerializer() {
            return this.serializer;
        }

        public abstract void read(Input input, Object obj);

        public void setCanBeNull(boolean z) {
            this.canBeNull = z;
        }

        public void setClass(Class cls) {
            this.valueClass = cls;
            this.serializer = null;
        }

        public void setSerializer(Serializer serializer) {
            this.serializer = serializer;
        }

        public String toString() {
            return this.field.getName();
        }

        public abstract void write(Output output, Object obj);

        public void setClass(Class cls, Serializer serializer) {
            this.valueClass = cls;
            this.serializer = serializer;
        }
    }

    @Override // java.util.Comparator
    public int compare(CachedField cachedField, CachedField cachedField2) {
        return cachedField.field.getName().compareTo(cachedField2.field.getName());
    }

    protected void rebuildCachedFields(boolean z) {
        List<Field> buildValidFieldsFromCachedFields;
        List<Field> buildValidFieldsFromCachedFields2;
        if (Log.TRACE && this.generics != null) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Generic type parameters: ");
            outline107.append(Arrays.toString(this.generics));
            Log.trace("kryo", outline107.toString());
        }
        if (this.type.isInterface()) {
            this.fields = new CachedField[0];
            return;
        }
        this.hasObjectFields = false;
        this.genericsScope = this.genericsUtil.buildGenericsScope(this.type, this.generics);
        Generics generics = this.genericsScope;
        if (generics != null) {
            this.kryo.pushGenericsScope(this.type, generics);
        }
        IntArray intArray = new IntArray();
        if (!z) {
            List<Field> arrayList = new ArrayList<>();
            for (Class cls = this.type; cls != Object.class; cls = cls.getSuperclass()) {
                Field[] declaredFields = cls.getDeclaredFields();
                if (declaredFields != null) {
                    for (Field field : declaredFields) {
                        if (!Modifier.isStatic(field.getModifiers())) {
                            arrayList.add(field);
                        }
                    }
                }
            }
            ObjectMap context = this.kryo.getContext();
            if (this.useMemRegions && !this.useAsmEnabled && unsafeAvailable) {
                try {
                    arrayList = Arrays.asList((Field[]) sortFieldsByOffsetMethod.invoke(null, arrayList));
                } catch (Exception e) {
                    throw new RuntimeException("Cannot invoke UnsafeUtil.sortFieldsByOffset()", e);
                }
            }
            buildValidFieldsFromCachedFields = buildValidFields(false, arrayList, context, intArray);
            buildValidFieldsFromCachedFields2 = buildValidFields(true, arrayList, context, intArray);
            if (this.useAsmEnabled && !Util.isAndroid && Modifier.isPublic(this.type.getModifiers()) && intArray.indexOf(1) != -1) {
                try {
                    this.access = FieldAccess.get(this.type);
                } catch (RuntimeException unused) {
                }
            }
        } else {
            buildValidFieldsFromCachedFields = buildValidFieldsFromCachedFields(this.fields, intArray);
            buildValidFieldsFromCachedFields2 = buildValidFieldsFromCachedFields(this.transientFields, intArray);
        }
        List<CachedField> arrayList2 = new ArrayList<>(buildValidFieldsFromCachedFields.size());
        List<CachedField> arrayList3 = new ArrayList<>(buildValidFieldsFromCachedFields2.size());
        createCachedFields(intArray, buildValidFieldsFromCachedFields, arrayList2, 0);
        createCachedFields(intArray, buildValidFieldsFromCachedFields2, arrayList3, buildValidFieldsFromCachedFields.size());
        Collections.sort(arrayList2, this);
        this.fields = (CachedField[]) arrayList2.toArray(new CachedField[arrayList2.size()]);
        Collections.sort(arrayList3, this);
        this.transientFields = (CachedField[]) arrayList3.toArray(new CachedField[arrayList3.size()]);
        initializeCachedFields();
        if (this.genericsScope != null) {
            this.kryo.popGenericsScope();
        }
        Iterator<CachedField> it2 = this.removedFields.iterator();
        while (it2.hasNext()) {
            removeField(it2.next());
        }
        this.annotationsUtil.processAnnotatedFields(this);
    }

    public void removeField(CachedField cachedField) {
        int i = 0;
        while (true) {
            CachedField[] cachedFieldArr = this.fields;
            if (i < cachedFieldArr.length) {
                CachedField cachedField2 = cachedFieldArr[i];
                if (cachedField2 == cachedField) {
                    CachedField[] cachedFieldArr2 = new CachedField[cachedFieldArr.length - 1];
                    System.arraycopy(cachedFieldArr, 0, cachedFieldArr2, 0, i);
                    System.arraycopy(this.fields, i + 1, cachedFieldArr2, i, cachedFieldArr2.length - i);
                    this.fields = cachedFieldArr2;
                    this.removedFields.add(cachedField2);
                    return;
                }
                i++;
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Field \"");
                sb.append(cachedField);
                sb.append("\" not found on class: ");
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline38(this.type, sb));
            }
        }
    }

    public FieldSerializer(Kryo kryo, Class cls, Class[] clsArr) {
        this.fields = new CachedField[0];
        this.transientFields = new CachedField[0];
        this.removedFields = new HashSet<>();
        this.fieldsCanBeNull = true;
        this.setFieldsAsAccessible = true;
        this.ignoreSyntheticFields = true;
        this.useMemRegions = false;
        this.copyTransient = true;
        this.serializeTransient = false;
        this.hasObjectFields = false;
        this.useAsmEnabled = !unsafeAvailable;
        this.varIntsEnabled = true;
        if (Log.TRACE) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Optimize ints: ");
            outline107.append(this.varIntsEnabled);
            Log.trace("kryo", outline107.toString());
        }
        this.kryo = kryo;
        this.type = cls;
        this.generics = clsArr;
        this.typeParameters = cls.getTypeParameters();
        this.useAsmEnabled = kryo.getAsmEnabled();
        if (!this.useAsmEnabled && !unsafeAvailable) {
            this.useAsmEnabled = true;
            if (Log.TRACE) {
                Log.trace("kryo", "sun.misc.Unsafe is unavailable, using ASM.");
            }
        }
        this.genericsUtil = new FieldSerializerGenericsUtil(this);
        this.unsafeUtil = FieldSerializerUnsafeUtil.Factory.getInstance(this);
        this.annotationsUtil = new FieldSerializerAnnotationsUtil(this);
        rebuildCachedFields();
    }
}
