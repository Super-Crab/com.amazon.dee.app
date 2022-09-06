package com.esotericsoftware.kryo.serializers;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;
/* loaded from: classes2.dex */
public class MapSerializer extends Serializer<Map> {
    private Class keyClass;
    private Class keyGenericType;
    private Serializer keySerializer;
    private Class valueClass;
    private Class valueGenericType;
    private Serializer valueSerializer;
    private boolean keysCanBeNull = true;
    private boolean valuesCanBeNull = true;

    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: classes2.dex */
    public @interface BindMap {
        Class<?> keyClass() default Object.class;

        Class<? extends Serializer> keySerializer() default Serializer.class;

        boolean keysCanBeNull() default true;

        Class<?> valueClass() default Object.class;

        Class<? extends Serializer> valueSerializer() default Serializer.class;

        boolean valuesCanBeNull() default true;
    }

    protected Map create(Kryo kryo, Input input, Class<Map> cls) {
        return (Map) kryo.newInstance(cls);
    }

    protected Map createCopy(Kryo kryo, Map map) {
        return (Map) kryo.newInstance(map.getClass());
    }

    @Override // com.esotericsoftware.kryo.Serializer
    public void setGenerics(Kryo kryo, Class[] clsArr) {
        this.keyGenericType = null;
        this.valueGenericType = null;
        if (clsArr == null || clsArr.length <= 0) {
            return;
        }
        if (clsArr[0] != null && kryo.isFinal(clsArr[0])) {
            this.keyGenericType = clsArr[0];
        }
        if (clsArr.length <= 1 || clsArr[1] == null || !kryo.isFinal(clsArr[1])) {
            return;
        }
        this.valueGenericType = clsArr[1];
    }

    public void setKeyClass(Class cls, Serializer serializer) {
        this.keyClass = cls;
        this.keySerializer = serializer;
    }

    public void setKeysCanBeNull(boolean z) {
        this.keysCanBeNull = z;
    }

    public void setValueClass(Class cls, Serializer serializer) {
        this.valueClass = cls;
        this.valueSerializer = serializer;
    }

    public void setValuesCanBeNull(boolean z) {
        this.valuesCanBeNull = z;
    }

    @Override // com.esotericsoftware.kryo.Serializer
    public Map copy(Kryo kryo, Map map) {
        Map createCopy = createCopy(kryo, map);
        for (Map.Entry entry : map.entrySet()) {
            createCopy.put(kryo.copy(entry.getKey()), kryo.copy(entry.getValue()));
        }
        return createCopy;
    }

    @Override // com.esotericsoftware.kryo.Serializer
    /* renamed from: read  reason: collision with other method in class */
    public Map mo6848read(Kryo kryo, Input input, Class<Map> cls) {
        Object readClassAndObject;
        Object readClassAndObject2;
        Map create = create(kryo, input, cls);
        int readInt = input.readInt(true);
        Class cls2 = this.keyClass;
        Class cls3 = this.valueClass;
        Serializer serializer = this.keySerializer;
        Class cls4 = this.keyGenericType;
        if (cls4 != null) {
            if (serializer == null) {
                serializer = kryo.getSerializer(cls4);
            }
            this.keyGenericType = null;
            cls2 = cls4;
        }
        Serializer serializer2 = this.valueSerializer;
        Class cls5 = this.valueGenericType;
        if (cls5 != null) {
            if (serializer2 == null) {
                serializer2 = kryo.getSerializer(cls5);
            }
            this.valueGenericType = null;
            cls3 = cls5;
        }
        kryo.reference(create);
        for (int i = 0; i < readInt; i++) {
            if (serializer != null) {
                if (this.keysCanBeNull) {
                    readClassAndObject = kryo.readObjectOrNull(input, cls2, serializer);
                } else {
                    readClassAndObject = kryo.readObject(input, cls2, serializer);
                }
            } else {
                readClassAndObject = kryo.readClassAndObject(input);
            }
            if (serializer2 != null) {
                if (this.valuesCanBeNull) {
                    readClassAndObject2 = kryo.readObjectOrNull(input, cls3, serializer2);
                } else {
                    readClassAndObject2 = kryo.readObject(input, cls3, serializer2);
                }
            } else {
                readClassAndObject2 = kryo.readClassAndObject(input);
            }
            create.put(readClassAndObject, readClassAndObject2);
        }
        return create;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.esotericsoftware.kryo.Serializer
    public void write(Kryo kryo, Output output, Map map) {
        output.writeInt(map.size(), true);
        Serializer serializer = this.keySerializer;
        Class cls = this.keyGenericType;
        if (cls != null) {
            if (serializer == null) {
                serializer = kryo.getSerializer(cls);
            }
            this.keyGenericType = null;
        }
        Serializer serializer2 = this.valueSerializer;
        Class cls2 = this.valueGenericType;
        if (cls2 != null) {
            if (serializer2 == null) {
                serializer2 = kryo.getSerializer(cls2);
            }
            this.valueGenericType = null;
        }
        for (Map.Entry entry : map.entrySet()) {
            if (serializer != null) {
                if (this.keysCanBeNull) {
                    kryo.writeObjectOrNull(output, entry.getKey(), serializer);
                } else {
                    kryo.writeObject(output, entry.getKey(), serializer);
                }
            } else {
                kryo.writeClassAndObject(output, entry.getKey());
            }
            if (serializer2 != null) {
                if (this.valuesCanBeNull) {
                    kryo.writeObjectOrNull(output, entry.getValue(), serializer2);
                } else {
                    kryo.writeObject(output, entry.getValue(), serializer2);
                }
            } else {
                kryo.writeClassAndObject(output, entry.getValue());
            }
        }
    }
}
