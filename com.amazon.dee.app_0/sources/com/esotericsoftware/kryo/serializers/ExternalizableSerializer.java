package com.esotericsoftware.kryo.serializers;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.KryoObjectInput;
import com.esotericsoftware.kryo.io.KryoObjectOutput;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.util.ObjectMap;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.reflect.Method;
/* loaded from: classes2.dex */
public class ExternalizableSerializer extends Serializer {
    private ObjectMap<Class, JavaSerializer> javaSerializerByType;
    private KryoObjectInput objectInput = null;
    private KryoObjectOutput objectOutput = null;

    private JavaSerializer getCachedSerializer(Class cls) {
        ObjectMap<Class, JavaSerializer> objectMap = this.javaSerializerByType;
        if (objectMap == null) {
            this.javaSerializerByType = new ObjectMap<>();
            return null;
        }
        return objectMap.get(cls);
    }

    private JavaSerializer getJavaSerializerIfRequired(Class cls) {
        JavaSerializer cachedSerializer = getCachedSerializer(cls);
        return (cachedSerializer != null || !isJavaSerializerRequired(cls)) ? cachedSerializer : new JavaSerializer();
    }

    private ObjectInput getObjectInput(Kryo kryo, Input input) {
        KryoObjectInput kryoObjectInput = this.objectInput;
        if (kryoObjectInput == null) {
            this.objectInput = new KryoObjectInput(kryo, input);
        } else {
            kryoObjectInput.setInput(input);
        }
        return this.objectInput;
    }

    private ObjectOutput getObjectOutput(Kryo kryo, Output output) {
        KryoObjectOutput kryoObjectOutput = this.objectOutput;
        if (kryoObjectOutput == null) {
            this.objectOutput = new KryoObjectOutput(kryo, output);
        } else {
            kryoObjectOutput.setOutput(output);
        }
        return this.objectOutput;
    }

    private static boolean hasInheritableReplaceMethod(Class cls, String str) {
        Method method;
        while (true) {
            if (cls == null) {
                method = null;
                break;
            }
            try {
                method = cls.getDeclaredMethod(str, new Class[0]);
                break;
            } catch (NoSuchMethodException unused) {
                cls = cls.getSuperclass();
            }
        }
        return method != null && method.getReturnType() == Object.class;
    }

    private boolean isJavaSerializerRequired(Class cls) {
        return hasInheritableReplaceMethod(cls, "writeReplace") || hasInheritableReplaceMethod(cls, "readResolve");
    }

    private Object readExternal(Kryo kryo, Input input, Class cls) {
        try {
            Externalizable externalizable = (Externalizable) cls.newInstance();
            externalizable.readExternal(getObjectInput(kryo, input));
            return externalizable;
        } catch (IOException e) {
            throw new KryoException(e);
        } catch (ClassCastException e2) {
            throw new KryoException(e2);
        } catch (ClassNotFoundException e3) {
            throw new KryoException(e3);
        } catch (IllegalAccessException e4) {
            throw new KryoException(e4);
        } catch (InstantiationException e5) {
            throw new KryoException(e5);
        }
    }

    private void writeExternal(Kryo kryo, Output output, Object obj) {
        try {
            ((Externalizable) obj).writeExternal(getObjectOutput(kryo, output));
        } catch (IOException e) {
            throw new KryoException(e);
        } catch (ClassCastException e2) {
            throw new KryoException(e2);
        }
    }

    @Override // com.esotericsoftware.kryo.Serializer
    /* renamed from: read */
    public Object mo6848read(Kryo kryo, Input input, Class cls) {
        JavaSerializer javaSerializerIfRequired = getJavaSerializerIfRequired(cls);
        if (javaSerializerIfRequired == null) {
            return readExternal(kryo, input, cls);
        }
        return javaSerializerIfRequired.mo6848read(kryo, input, cls);
    }

    @Override // com.esotericsoftware.kryo.Serializer
    public void write(Kryo kryo, Output output, Object obj) {
        JavaSerializer javaSerializerIfRequired = getJavaSerializerIfRequired(obj.getClass());
        if (javaSerializerIfRequired == null) {
            writeExternal(kryo, output, obj);
        } else {
            javaSerializerIfRequired.write(kryo, output, obj);
        }
    }
}
