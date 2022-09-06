package com.esotericsoftware.kryo.serializers;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.minlog.Log;
import com.esotericsoftware.reflectasm.MethodAccess;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
/* loaded from: classes2.dex */
public class BeanSerializer<T> extends Serializer<T> {
    static final Object[] noArgs = new Object[0];
    Object access;
    private CachedProperty[] properties;

    /* loaded from: classes2.dex */
    class CachedProperty<X> {
        Method getMethod;
        int getterAccessIndex;
        String name;
        Serializer serializer;
        Method setMethod;
        Class setMethodType;
        int setterAccessIndex;

        CachedProperty() {
        }

        Object get(Object obj) throws IllegalAccessException, InvocationTargetException {
            Object obj2 = BeanSerializer.this.access;
            return obj2 != null ? ((MethodAccess) obj2).invoke(obj, this.getterAccessIndex, new Object[0]) : this.getMethod.invoke(obj, BeanSerializer.noArgs);
        }

        void set(Object obj, Object obj2) throws IllegalAccessException, InvocationTargetException {
            Object obj3 = BeanSerializer.this.access;
            if (obj3 != null) {
                ((MethodAccess) obj3).invoke(obj, this.setterAccessIndex, obj2);
            } else {
                this.setMethod.invoke(obj, obj2);
            }
        }

        public String toString() {
            return this.name;
        }
    }

    public BeanSerializer(Kryo kryo, Class cls) {
        try {
            PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(cls).getPropertyDescriptors();
            Arrays.sort(propertyDescriptors, new Comparator<PropertyDescriptor>() { // from class: com.esotericsoftware.kryo.serializers.BeanSerializer.1
                @Override // java.util.Comparator
                public int compare(PropertyDescriptor propertyDescriptor, PropertyDescriptor propertyDescriptor2) {
                    return propertyDescriptor.getName().compareTo(propertyDescriptor2.getName());
                }
            });
            ArrayList arrayList = new ArrayList(propertyDescriptors.length);
            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                String name = propertyDescriptor.getName();
                if (!name.equals("class")) {
                    Method readMethod = propertyDescriptor.getReadMethod();
                    Method writeMethod = propertyDescriptor.getWriteMethod();
                    if (readMethod != null && writeMethod != null) {
                        Serializer serializer = null;
                        Class<?> returnType = readMethod.getReturnType();
                        serializer = kryo.isFinal(returnType) ? kryo.getRegistration(returnType).getSerializer() : serializer;
                        CachedProperty cachedProperty = new CachedProperty();
                        cachedProperty.name = name;
                        cachedProperty.getMethod = readMethod;
                        cachedProperty.setMethod = writeMethod;
                        cachedProperty.serializer = serializer;
                        cachedProperty.setMethodType = writeMethod.getParameterTypes()[0];
                        arrayList.add(cachedProperty);
                    }
                }
            }
            this.properties = (CachedProperty[]) arrayList.toArray(new CachedProperty[arrayList.size()]);
            try {
                this.access = MethodAccess.get(cls);
                int length = this.properties.length;
                for (int i = 0; i < length; i++) {
                    CachedProperty cachedProperty2 = this.properties[i];
                    cachedProperty2.getterAccessIndex = ((MethodAccess) this.access).getIndex(cachedProperty2.getMethod.getName(), cachedProperty2.getMethod.getParameterTypes());
                    cachedProperty2.setterAccessIndex = ((MethodAccess) this.access).getIndex(cachedProperty2.setMethod.getName(), cachedProperty2.setMethod.getParameterTypes());
                }
            } catch (Throwable unused) {
            }
        } catch (IntrospectionException e) {
            throw new KryoException("Error getting bean info.", e);
        }
    }

    @Override // com.esotericsoftware.kryo.Serializer
    public T copy(Kryo kryo, T t) {
        T t2 = (T) kryo.newInstance(t.getClass());
        int length = this.properties.length;
        for (int i = 0; i < length; i++) {
            CachedProperty cachedProperty = this.properties[i];
            try {
                cachedProperty.set(t2, cachedProperty.get(t));
            } catch (KryoException e) {
                e.addTrace(cachedProperty + " (" + t2.getClass().getName() + ")");
                throw e;
            } catch (RuntimeException e2) {
                KryoException kryoException = new KryoException(e2);
                kryoException.addTrace(cachedProperty + " (" + t2.getClass().getName() + ")");
                throw kryoException;
            } catch (Exception e3) {
                StringBuilder sb = new StringBuilder();
                sb.append("Error copying bean property: ");
                sb.append(cachedProperty);
                sb.append(" (");
                throw new KryoException(GeneratedOutlineSupport1.outline46(t2, sb, ")"), e3);
            }
        }
        return t2;
    }

    @Override // com.esotericsoftware.kryo.Serializer
    /* renamed from: read */
    public T mo6848read(Kryo kryo, Input input, Class<T> cls) {
        Object readClassAndObject;
        T t = (T) kryo.newInstance(cls);
        kryo.reference(t);
        int length = this.properties.length;
        for (int i = 0; i < length; i++) {
            CachedProperty cachedProperty = this.properties[i];
            try {
                if (Log.TRACE) {
                    Log.trace("kryo", "Read property: " + cachedProperty + " (" + t.getClass() + ")");
                }
                Serializer serializer = cachedProperty.serializer;
                if (serializer != null) {
                    readClassAndObject = kryo.readObjectOrNull(input, cachedProperty.setMethodType, serializer);
                } else {
                    readClassAndObject = kryo.readClassAndObject(input);
                }
                cachedProperty.set(t, readClassAndObject);
            } catch (KryoException e) {
                e.addTrace(cachedProperty + " (" + t.getClass().getName() + ")");
                throw e;
            } catch (IllegalAccessException e2) {
                StringBuilder sb = new StringBuilder();
                sb.append("Error accessing setter method: ");
                sb.append(cachedProperty);
                sb.append(" (");
                throw new KryoException(GeneratedOutlineSupport1.outline46(t, sb, ")"), e2);
            } catch (RuntimeException e3) {
                KryoException kryoException = new KryoException(e3);
                kryoException.addTrace(cachedProperty + " (" + t.getClass().getName() + ")");
                throw kryoException;
            } catch (InvocationTargetException e4) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Error invoking setter method: ");
                sb2.append(cachedProperty);
                sb2.append(" (");
                throw new KryoException(GeneratedOutlineSupport1.outline46(t, sb2, ")"), e4);
            }
        }
        return t;
    }

    @Override // com.esotericsoftware.kryo.Serializer
    public void write(Kryo kryo, Output output, T t) {
        Class<?> cls = t.getClass();
        int length = this.properties.length;
        for (int i = 0; i < length; i++) {
            CachedProperty cachedProperty = this.properties[i];
            try {
                if (Log.TRACE) {
                    Log.trace("kryo", "Write property: " + cachedProperty + " (" + cls.getName() + ")");
                }
                Object obj = cachedProperty.get(t);
                Serializer serializer = cachedProperty.serializer;
                if (serializer != null) {
                    kryo.writeObjectOrNull(output, obj, serializer);
                } else {
                    kryo.writeClassAndObject(output, obj);
                }
            } catch (KryoException e) {
                StringBuilder sb = new StringBuilder();
                sb.append(cachedProperty);
                sb.append(" (");
                GeneratedOutlineSupport1.outline147(cls, sb, ")", e);
                throw e;
            } catch (IllegalAccessException e2) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Error accessing getter method: ");
                sb2.append(cachedProperty);
                sb2.append(" (");
                throw new KryoException(GeneratedOutlineSupport1.outline40(cls, sb2, ")"), e2);
            } catch (RuntimeException e3) {
                KryoException kryoException = new KryoException(e3);
                StringBuilder sb3 = new StringBuilder();
                sb3.append(cachedProperty);
                sb3.append(" (");
                GeneratedOutlineSupport1.outline147(cls, sb3, ")", kryoException);
                throw kryoException;
            } catch (InvocationTargetException e4) {
                StringBuilder sb4 = new StringBuilder();
                sb4.append("Error invoking getter method: ");
                sb4.append(cachedProperty);
                sb4.append(" (");
                throw new KryoException(GeneratedOutlineSupport1.outline40(cls, sb4, ")"), e4);
            }
        }
    }
}
