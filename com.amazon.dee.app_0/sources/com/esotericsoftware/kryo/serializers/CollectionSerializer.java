package com.esotericsoftware.kryo.serializers;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collection;
/* loaded from: classes2.dex */
public class CollectionSerializer extends Serializer<Collection> {
    private Class elementClass;
    private boolean elementsCanBeNull;
    private Class genericType;
    private Serializer serializer;

    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: classes2.dex */
    public @interface BindCollection {
        Class<?> elementClass() default Object.class;

        Class<? extends Serializer> elementSerializer() default Serializer.class;

        boolean elementsCanBeNull() default true;
    }

    public CollectionSerializer() {
        this.elementsCanBeNull = true;
    }

    /* renamed from: create */
    protected Collection mo6846create(Kryo kryo, Input input, Class<Collection> cls) {
        return (Collection) kryo.newInstance(cls);
    }

    /* renamed from: createCopy */
    protected Collection mo6847createCopy(Kryo kryo, Collection collection) {
        return (Collection) kryo.newInstance(collection.getClass());
    }

    public void setElementClass(Class cls, Serializer serializer) {
        this.elementClass = cls;
        this.serializer = serializer;
    }

    public void setElementsCanBeNull(boolean z) {
        this.elementsCanBeNull = z;
    }

    @Override // com.esotericsoftware.kryo.Serializer
    public void setGenerics(Kryo kryo, Class[] clsArr) {
        this.genericType = null;
        if (clsArr == null || clsArr.length <= 0 || !kryo.isFinal(clsArr[0])) {
            return;
        }
        this.genericType = clsArr[0];
    }

    @Override // com.esotericsoftware.kryo.Serializer
    public Collection copy(Kryo kryo, Collection collection) {
        Collection mo6847createCopy = mo6847createCopy(kryo, collection);
        kryo.reference(mo6847createCopy);
        for (Object obj : collection) {
            mo6847createCopy.add(kryo.copy(obj));
        }
        return mo6847createCopy;
    }

    @Override // com.esotericsoftware.kryo.Serializer
    /* renamed from: read  reason: avoid collision after fix types in other method */
    public Collection mo6848read(Kryo kryo, Input input, Class<Collection> cls) {
        Collection mo6846create = mo6846create(kryo, input, cls);
        kryo.reference(mo6846create);
        int readVarInt = input.readVarInt(true);
        if (mo6846create instanceof ArrayList) {
            ((ArrayList) mo6846create).ensureCapacity(readVarInt);
        }
        Class cls2 = this.elementClass;
        Serializer serializer = this.serializer;
        Class cls3 = this.genericType;
        if (cls3 != null) {
            if (serializer == null) {
                serializer = kryo.getSerializer(cls3);
                cls2 = cls3;
            }
            this.genericType = null;
        }
        int i = 0;
        if (serializer == null) {
            while (i < readVarInt) {
                mo6846create.add(kryo.readClassAndObject(input));
                i++;
            }
        } else if (this.elementsCanBeNull) {
            while (i < readVarInt) {
                mo6846create.add(kryo.readObjectOrNull(input, cls2, serializer));
                i++;
            }
        } else {
            while (i < readVarInt) {
                mo6846create.add(kryo.readObject(input, cls2, serializer));
                i++;
            }
        }
        return mo6846create;
    }

    @Override // com.esotericsoftware.kryo.Serializer
    public void write(Kryo kryo, Output output, Collection collection) {
        output.writeVarInt(collection.size(), true);
        Serializer serializer = this.serializer;
        Class cls = this.genericType;
        if (cls != null) {
            if (serializer == null) {
                serializer = kryo.getSerializer(cls);
            }
            this.genericType = null;
        }
        if (serializer != null) {
            if (this.elementsCanBeNull) {
                for (Object obj : collection) {
                    kryo.writeObjectOrNull(output, obj, serializer);
                }
                return;
            }
            for (Object obj2 : collection) {
                kryo.writeObject(output, obj2, serializer);
            }
            return;
        }
        for (Object obj3 : collection) {
            kryo.writeClassAndObject(output, obj3);
        }
    }

    public CollectionSerializer(Class cls, Serializer serializer) {
        this.elementsCanBeNull = true;
        setElementClass(cls, serializer);
    }

    public CollectionSerializer(Class cls, Serializer serializer, boolean z) {
        this.elementsCanBeNull = true;
        setElementClass(cls, serializer);
        this.elementsCanBeNull = z;
    }
}
