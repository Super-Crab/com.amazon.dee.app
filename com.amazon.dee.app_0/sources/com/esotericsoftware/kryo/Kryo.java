package com.esotericsoftware.kryo;

import com.amazon.alexa.externalnotifications.capability.ExternalNotificationsCapabilityAgentConstants;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.esotericsoftware.kryo.factories.PseudoSerializerFactory;
import com.esotericsoftware.kryo.factories.ReflectionSerializerFactory;
import com.esotericsoftware.kryo.factories.SerializerFactory;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.CollectionSerializer;
import com.esotericsoftware.kryo.serializers.DefaultArraySerializers;
import com.esotericsoftware.kryo.serializers.DefaultSerializers;
import com.esotericsoftware.kryo.serializers.FieldSerializer;
import com.esotericsoftware.kryo.serializers.MapSerializer;
import com.esotericsoftware.kryo.util.DefaultClassResolver;
import com.esotericsoftware.kryo.util.DefaultStreamFactory;
import com.esotericsoftware.kryo.util.IdentityMap;
import com.esotericsoftware.kryo.util.IntArray;
import com.esotericsoftware.kryo.util.MapReferenceResolver;
import com.esotericsoftware.kryo.util.ObjectMap;
import com.esotericsoftware.kryo.util.Util;
import com.esotericsoftware.minlog.Log;
import com.esotericsoftware.reflectasm.ConstructorAccess;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Currency;
import java.util.Date;
import java.util.EnumSet;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.TreeSet;
import org.objenesis.instantiator.ObjectInstantiator;
import org.objenesis.strategy.InstantiatorStrategy;
/* loaded from: classes2.dex */
public class Kryo {
    public static final byte NOT_NULL = 1;
    private static final int NO_REF = -2;
    public static final byte NULL = 0;
    private static final int REF = -1;
    private boolean asmEnabled;
    private boolean autoReset;
    private ClassLoader classLoader;
    private final ClassResolver classResolver;
    private ObjectMap context;
    private int copyDepth;
    private boolean copyReferences;
    private boolean copyShallow;
    private SerializerFactory defaultSerializer;
    private final ArrayList<DefaultSerializerEntry> defaultSerializers;
    private int depth;
    private Generics genericsScope;
    private ObjectMap graphContext;
    private final int lowPriorityDefaultSerializerCount;
    private int maxDepth;
    private Object needsCopyReference;
    private int nextRegisterID;
    private IdentityMap originalToCopy;
    private Object readObject;
    private final IntArray readReferenceIds;
    private ReferenceResolver referenceResolver;
    private boolean references;
    private boolean registrationRequired;
    private InstantiatorStrategy strategy;
    private StreamFactory streamFactory;
    private volatile Thread thread;

    /* loaded from: classes2.dex */
    public static class DefaultInstantiatorStrategy implements InstantiatorStrategy {
        private InstantiatorStrategy fallbackStrategy;

        public InstantiatorStrategy getFallbackInstantiatorStrategy() {
            return this.fallbackStrategy;
        }

        @Override // org.objenesis.strategy.InstantiatorStrategy
        public ObjectInstantiator newInstantiatorOf(final Class cls) {
            final Constructor declaredConstructor;
            if (!Util.isAndroid) {
                if (!(cls.getEnclosingClass() != null && cls.isMemberClass() && !Modifier.isStatic(cls.getModifiers()))) {
                    try {
                        final ConstructorAccess constructorAccess = ConstructorAccess.get(cls);
                        return new ObjectInstantiator() { // from class: com.esotericsoftware.kryo.Kryo.DefaultInstantiatorStrategy.1
                            @Override // org.objenesis.instantiator.ObjectInstantiator
                            public Object newInstance() {
                                try {
                                    return constructorAccess.newInstance();
                                } catch (Exception e) {
                                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Error constructing instance of class: ");
                                    outline107.append(Util.className(cls));
                                    throw new KryoException(outline107.toString(), e);
                                }
                            }
                        };
                    } catch (Exception unused) {
                    }
                }
            }
            try {
                try {
                    declaredConstructor = cls.getConstructor(null);
                } catch (Exception unused2) {
                    declaredConstructor = cls.getDeclaredConstructor(null);
                    declaredConstructor.setAccessible(true);
                }
                return new ObjectInstantiator() { // from class: com.esotericsoftware.kryo.Kryo.DefaultInstantiatorStrategy.2
                    @Override // org.objenesis.instantiator.ObjectInstantiator
                    public Object newInstance() {
                        try {
                            return declaredConstructor.newInstance(new Object[0]);
                        } catch (Exception e) {
                            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Error constructing instance of class: ");
                            outline107.append(Util.className(cls));
                            throw new KryoException(outline107.toString(), e);
                        }
                    }
                };
            } catch (Exception unused3) {
                InstantiatorStrategy instantiatorStrategy = this.fallbackStrategy;
                if (instantiatorStrategy == null) {
                    if (cls.isMemberClass() && !Modifier.isStatic(cls.getModifiers())) {
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Class cannot be created (non-static member class): ");
                        outline107.append(Util.className(cls));
                        throw new KryoException(outline107.toString());
                    }
                    StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Class cannot be created (missing no-arg constructor): ");
                    outline1072.append(Util.className(cls));
                    throw new KryoException(outline1072.toString());
                }
                return instantiatorStrategy.newInstantiatorOf(cls);
            }
        }

        public void setFallbackInstantiatorStrategy(InstantiatorStrategy instantiatorStrategy) {
            this.fallbackStrategy = instantiatorStrategy;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class DefaultSerializerEntry {
        final SerializerFactory serializerFactory;
        final Class type;

        DefaultSerializerEntry(Class cls, SerializerFactory serializerFactory) {
            this.type = cls;
            this.serializerFactory = serializerFactory;
        }
    }

    public Kryo() {
        this(new DefaultClassResolver(), new MapReferenceResolver(), new DefaultStreamFactory());
    }

    private void beginObject() {
        if (Log.DEBUG) {
            if (this.depth == 0) {
                this.thread = Thread.currentThread();
            } else if (this.thread != Thread.currentThread()) {
                throw new ConcurrentModificationException("Kryo must not be accessed concurrently by multiple threads.");
            }
        }
        int i = this.depth;
        if (i != this.maxDepth) {
            this.depth = i + 1;
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Max depth exceeded: ");
        outline107.append(this.depth);
        throw new KryoException(outline107.toString());
    }

    public void addDefaultSerializer(Class cls, Serializer serializer) {
        if (cls != null) {
            if (serializer != null) {
                DefaultSerializerEntry defaultSerializerEntry = new DefaultSerializerEntry(cls, new PseudoSerializerFactory(serializer));
                ArrayList<DefaultSerializerEntry> arrayList = this.defaultSerializers;
                arrayList.add(arrayList.size() - this.lowPriorityDefaultSerializerCount, defaultSerializerEntry);
                return;
            }
            throw new IllegalArgumentException("serializer cannot be null.");
        }
        throw new IllegalArgumentException("type cannot be null.");
    }

    public <T> T copy(T t) {
        T t2;
        if (t == null) {
            return null;
        }
        if (this.copyShallow) {
            return t;
        }
        this.copyDepth++;
        try {
            if (this.originalToCopy == null) {
                this.originalToCopy = new IdentityMap();
            }
            T t3 = (T) this.originalToCopy.get(t);
            if (t3 != null) {
                return t3;
            }
            if (this.copyReferences) {
                this.needsCopyReference = t;
            }
            if (t instanceof KryoCopyable) {
                t2 = (T) ((KryoCopyable) t).copy(this);
            } else {
                t2 = (T) getSerializer(t.getClass()).copy(this, t);
            }
            if (this.needsCopyReference != null) {
                reference(t2);
            }
            if (Log.TRACE || (Log.DEBUG && this.copyDepth == 1)) {
                Util.log("Copy", t2);
            }
            int i = this.copyDepth - 1;
            this.copyDepth = i;
            if (i == 0) {
                reset();
            }
            return t2;
        } finally {
            int i2 = this.copyDepth - 1;
            this.copyDepth = i2;
            if (i2 == 0) {
                reset();
            }
        }
    }

    public <T> T copyShallow(T t) {
        T t2;
        if (t == null) {
            return null;
        }
        this.copyDepth++;
        this.copyShallow = true;
        try {
            if (this.originalToCopy == null) {
                this.originalToCopy = new IdentityMap();
            }
            T t3 = (T) this.originalToCopy.get(t);
            if (t3 != null) {
                return t3;
            }
            if (this.copyReferences) {
                this.needsCopyReference = t;
            }
            if (t instanceof KryoCopyable) {
                t2 = (T) ((KryoCopyable) t).copy(this);
            } else {
                t2 = (T) getSerializer(t.getClass()).copy(this, t);
            }
            if (this.needsCopyReference != null) {
                reference(t2);
            }
            if (Log.TRACE || (Log.DEBUG && this.copyDepth == 1)) {
                Util.log("Shallow copy", t2);
            }
            this.copyShallow = false;
            int i = this.copyDepth - 1;
            this.copyDepth = i;
            if (i == 0) {
                reset();
            }
            return t2;
        } finally {
            this.copyShallow = false;
            int i2 = this.copyDepth - 1;
            this.copyDepth = i2;
            if (i2 == 0) {
                reset();
            }
        }
    }

    public boolean getAsmEnabled() {
        return this.asmEnabled;
    }

    public ClassLoader getClassLoader() {
        return this.classLoader;
    }

    public ClassResolver getClassResolver() {
        return this.classResolver;
    }

    public ObjectMap getContext() {
        if (this.context == null) {
            this.context = new ObjectMap();
        }
        return this.context;
    }

    public Serializer getDefaultSerializer(Class cls) {
        if (cls != null) {
            if (cls.isAnnotationPresent(DefaultSerializer.class)) {
                return ReflectionSerializerFactory.makeSerializer(this, ((DefaultSerializer) cls.getAnnotation(DefaultSerializer.class)).value(), cls);
            }
            int size = this.defaultSerializers.size();
            for (int i = 0; i < size; i++) {
                DefaultSerializerEntry defaultSerializerEntry = this.defaultSerializers.get(i);
                if (defaultSerializerEntry.type.isAssignableFrom(cls)) {
                    return defaultSerializerEntry.serializerFactory.makeSerializer(this, cls);
                }
            }
            return newDefaultSerializer(cls);
        }
        throw new IllegalArgumentException("type cannot be null.");
    }

    public int getDepth() {
        return this.depth;
    }

    public Generics getGenericsScope() {
        return this.genericsScope;
    }

    public ObjectMap getGraphContext() {
        if (this.graphContext == null) {
            this.graphContext = new ObjectMap();
        }
        return this.graphContext;
    }

    public InstantiatorStrategy getInstantiatorStrategy() {
        return this.strategy;
    }

    public int getNextRegistrationId() {
        while (true) {
            int i = this.nextRegisterID;
            if (i != -2) {
                if (this.classResolver.getRegistration(i) == null) {
                    return this.nextRegisterID;
                }
                this.nextRegisterID++;
            } else {
                throw new KryoException("No registration IDs are available.");
            }
        }
    }

    public IdentityMap getOriginalToCopyMap() {
        return this.originalToCopy;
    }

    public ReferenceResolver getReferenceResolver() {
        return this.referenceResolver;
    }

    public boolean getReferences() {
        return this.references;
    }

    public Registration getRegistration(Class cls) {
        if (cls != null) {
            Registration registration = this.classResolver.getRegistration(cls);
            if (registration != null) {
                return registration;
            }
            if (Proxy.isProxyClass(cls)) {
                registration = getRegistration(InvocationHandler.class);
            } else if (!cls.isEnum() && Enum.class.isAssignableFrom(cls)) {
                registration = getRegistration(cls.getEnclosingClass());
            } else if (EnumSet.class.isAssignableFrom(cls)) {
                registration = this.classResolver.getRegistration(EnumSet.class);
            }
            if (registration != null) {
                return registration;
            }
            if (!this.registrationRequired) {
                return this.classResolver.registerImplicit(cls);
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Class is not registered: ");
            outline107.append(Util.className(cls));
            outline107.append("\nNote: To register this class use: kryo.register(");
            outline107.append(Util.className(cls));
            outline107.append(".class);");
            throw new IllegalArgumentException(outline107.toString());
        }
        throw new IllegalArgumentException("type cannot be null.");
    }

    public Serializer getSerializer(Class cls) {
        return getRegistration(cls).getSerializer();
    }

    public StreamFactory getStreamFactory() {
        return this.streamFactory;
    }

    public boolean isFinal(Class cls) {
        if (cls != null) {
            return cls.isArray() ? Modifier.isFinal(Util.getElementClass(cls).getModifiers()) : Modifier.isFinal(cls.getModifiers());
        }
        throw new IllegalArgumentException("type cannot be null.");
    }

    public boolean isRegistrationRequired() {
        return this.registrationRequired;
    }

    protected Serializer newDefaultSerializer(Class cls) {
        return this.defaultSerializer.makeSerializer(this, cls);
    }

    public <T> T newInstance(Class<T> cls) {
        Registration registration = getRegistration(cls);
        ObjectInstantiator instantiator = registration.getInstantiator();
        if (instantiator == null) {
            instantiator = newInstantiator(cls);
            registration.setInstantiator(instantiator);
        }
        return (T) instantiator.newInstance();
    }

    protected ObjectInstantiator newInstantiator(Class cls) {
        return this.strategy.newInstantiatorOf(cls);
    }

    public void popGenericsScope() {
        Generics generics = this.genericsScope;
        if (generics != null) {
            this.genericsScope = generics.getParentScope();
        }
        if (generics != null) {
            generics.resetParentScope();
        }
    }

    public void pushGenericsScope(Class cls, Generics generics) {
        if (Log.TRACE) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Settting a new generics scope for class ");
            outline107.append(cls.getName());
            outline107.append(RealTimeTextConstants.COLON_SPACE);
            outline107.append(generics);
            Log.trace("kryo", outline107.toString());
        }
        Generics generics2 = this.genericsScope;
        if (generics.getParentScope() != null) {
            generics = new Generics(generics.getMappings());
        }
        this.genericsScope = generics;
        this.genericsScope.setParentScope(generics2);
    }

    public Registration readClass(Input input) {
        if (input != null) {
            try {
                return this.classResolver.readClass(input);
            } finally {
                if (this.depth == 0 && this.autoReset) {
                    reset();
                }
            }
        }
        throw new IllegalArgumentException("input cannot be null.");
    }

    public Object readClassAndObject(Input input) {
        Object mo6848read;
        if (input != null) {
            beginObject();
            try {
                Registration readClass = readClass(input);
                if (readClass == null) {
                    return null;
                }
                Class type = readClass.getType();
                if (this.references) {
                    readClass.getSerializer().setGenerics(this, null);
                    int readReferenceOrNull = readReferenceOrNull(input, type, false);
                    if (readReferenceOrNull == -1) {
                        Object obj = this.readObject;
                        int i = this.depth - 1;
                        this.depth = i;
                        if (i == 0 && this.autoReset) {
                            reset();
                        }
                        return obj;
                    }
                    mo6848read = readClass.getSerializer().mo6848read(this, input, type);
                    if (readReferenceOrNull == this.readReferenceIds.size) {
                        reference(mo6848read);
                    }
                } else {
                    mo6848read = readClass.getSerializer().mo6848read(this, input, type);
                }
                if (Log.TRACE || (Log.DEBUG && this.depth == 1)) {
                    Util.log(ExternalNotificationsCapabilityAgentConstants.READ_EVENT_NAME, mo6848read);
                }
                int i2 = this.depth - 1;
                this.depth = i2;
                if (i2 == 0 && this.autoReset) {
                    reset();
                }
                return mo6848read;
            } finally {
                int i3 = this.depth - 1;
                this.depth = i3;
                if (i3 == 0 && this.autoReset) {
                    reset();
                }
            }
        }
        throw new IllegalArgumentException("input cannot be null.");
    }

    public <T> T readObject(Input input, Class<T> cls) {
        T t;
        if (input != null) {
            if (cls != null) {
                beginObject();
                try {
                    if (this.references) {
                        int readReferenceOrNull = readReferenceOrNull(input, cls, false);
                        if (readReferenceOrNull == -1) {
                            return (T) this.readObject;
                        }
                        t = (T) getRegistration(cls).getSerializer().mo6848read(this, input, cls);
                        if (readReferenceOrNull == this.readReferenceIds.size) {
                            reference(t);
                        }
                    } else {
                        t = (T) getRegistration(cls).getSerializer().mo6848read(this, input, cls);
                    }
                    if (Log.TRACE || (Log.DEBUG && this.depth == 1)) {
                        Util.log(ExternalNotificationsCapabilityAgentConstants.READ_EVENT_NAME, t);
                    }
                    int i = this.depth - 1;
                    this.depth = i;
                    if (i == 0 && this.autoReset) {
                        reset();
                    }
                    return t;
                } finally {
                    int i2 = this.depth - 1;
                    this.depth = i2;
                    if (i2 == 0 && this.autoReset) {
                        reset();
                    }
                }
            }
            throw new IllegalArgumentException("type cannot be null.");
        }
        throw new IllegalArgumentException("input cannot be null.");
    }

    public <T> T readObjectOrNull(Input input, Class<T> cls) {
        T t;
        if (input != null) {
            if (cls != null) {
                beginObject();
                try {
                    if (this.references) {
                        int readReferenceOrNull = readReferenceOrNull(input, cls, true);
                        if (readReferenceOrNull == -1) {
                            return (T) this.readObject;
                        }
                        t = (T) getRegistration(cls).getSerializer().mo6848read(this, input, cls);
                        if (readReferenceOrNull == this.readReferenceIds.size) {
                            reference(t);
                        }
                    } else {
                        Serializer serializer = getRegistration(cls).getSerializer();
                        if (!serializer.getAcceptsNull() && input.readByte() == 0) {
                            if (Log.TRACE || (Log.DEBUG && this.depth == 1)) {
                                Util.log(ExternalNotificationsCapabilityAgentConstants.READ_EVENT_NAME, null);
                            }
                            int i = this.depth - 1;
                            this.depth = i;
                            if (i == 0 && this.autoReset) {
                                reset();
                            }
                            return null;
                        }
                        t = (T) serializer.mo6848read(this, input, cls);
                    }
                    if (Log.TRACE || (Log.DEBUG && this.depth == 1)) {
                        Util.log(ExternalNotificationsCapabilityAgentConstants.READ_EVENT_NAME, t);
                    }
                    int i2 = this.depth - 1;
                    this.depth = i2;
                    if (i2 == 0 && this.autoReset) {
                        reset();
                    }
                    return t;
                } finally {
                    int i3 = this.depth - 1;
                    this.depth = i3;
                    if (i3 == 0 && this.autoReset) {
                        reset();
                    }
                }
            }
            throw new IllegalArgumentException("type cannot be null.");
        }
        throw new IllegalArgumentException("input cannot be null.");
    }

    int readReferenceOrNull(Input input, Class cls, boolean z) {
        int readVarInt;
        if (cls.isPrimitive()) {
            cls = Util.getWrapperClass(cls);
        }
        boolean useReferences = this.referenceResolver.useReferences(cls);
        if (z) {
            readVarInt = input.readVarInt(true);
            if (readVarInt == 0) {
                if (Log.TRACE || (Log.DEBUG && this.depth == 1)) {
                    Util.log(ExternalNotificationsCapabilityAgentConstants.READ_EVENT_NAME, null);
                }
                this.readObject = null;
                return -1;
            } else if (!useReferences) {
                this.readReferenceIds.add(-2);
                return this.readReferenceIds.size;
            }
        } else if (!useReferences) {
            this.readReferenceIds.add(-2);
            return this.readReferenceIds.size;
        } else {
            readVarInt = input.readVarInt(true);
        }
        if (readVarInt == 1) {
            int nextReadId = this.referenceResolver.nextReadId(cls);
            if (Log.TRACE) {
                StringBuilder outline109 = GeneratedOutlineSupport1.outline109("Read initial object reference ", nextReadId, RealTimeTextConstants.COLON_SPACE);
                outline109.append(Util.className(cls));
                Log.trace("kryo", outline109.toString());
            }
            this.readReferenceIds.add(nextReadId);
            return this.readReferenceIds.size;
        }
        int i = readVarInt - 2;
        this.readObject = this.referenceResolver.getReadObject(cls, i);
        if (Log.DEBUG) {
            StringBuilder outline1092 = GeneratedOutlineSupport1.outline109("Read object reference ", i, RealTimeTextConstants.COLON_SPACE);
            outline1092.append(Util.string(this.readObject));
            Log.debug("kryo", outline1092.toString());
        }
        return -1;
    }

    public void reference(Object obj) {
        int pop;
        if (this.copyDepth > 0) {
            Object obj2 = this.needsCopyReference;
            if (obj2 == null) {
                return;
            }
            if (obj != null) {
                this.originalToCopy.put(obj2, obj);
                this.needsCopyReference = null;
                return;
            }
            throw new IllegalArgumentException("object cannot be null.");
        } else if (!this.references || obj == null || (pop = this.readReferenceIds.pop()) == -2) {
        } else {
            this.referenceResolver.setReadObject(pop, obj);
        }
    }

    public Registration register(Class cls) {
        Registration registration = this.classResolver.getRegistration(cls);
        return registration != null ? registration : register(cls, getDefaultSerializer(cls));
    }

    public void reset() {
        this.depth = 0;
        ObjectMap objectMap = this.graphContext;
        if (objectMap != null) {
            objectMap.clear();
        }
        this.classResolver.reset();
        if (this.references) {
            this.referenceResolver.reset();
            this.readObject = null;
        }
        this.copyDepth = 0;
        IdentityMap identityMap = this.originalToCopy;
        if (identityMap != null) {
            identityMap.clear(2048);
        }
        if (Log.TRACE) {
            Log.trace("kryo", "Object graph complete.");
        }
    }

    public void setAsmEnabled(boolean z) {
        this.asmEnabled = z;
    }

    public void setAutoReset(boolean z) {
        this.autoReset = z;
    }

    public void setClassLoader(ClassLoader classLoader) {
        if (classLoader != null) {
            this.classLoader = classLoader;
            return;
        }
        throw new IllegalArgumentException("classLoader cannot be null.");
    }

    public void setCopyReferences(boolean z) {
        this.copyReferences = z;
    }

    public void setDefaultSerializer(SerializerFactory serializerFactory) {
        if (serializerFactory != null) {
            this.defaultSerializer = serializerFactory;
            return;
        }
        throw new IllegalArgumentException("serializer cannot be null.");
    }

    public void setInstantiatorStrategy(InstantiatorStrategy instantiatorStrategy) {
        this.strategy = instantiatorStrategy;
    }

    public void setMaxDepth(int i) {
        if (i > 0) {
            this.maxDepth = i;
            return;
        }
        throw new IllegalArgumentException("maxDepth must be > 0.");
    }

    public void setReferenceResolver(ReferenceResolver referenceResolver) {
        if (referenceResolver != null) {
            this.references = true;
            this.referenceResolver = referenceResolver;
            if (!Log.TRACE) {
                return;
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Reference resolver: ");
            outline107.append(referenceResolver.getClass().getName());
            Log.trace("kryo", outline107.toString());
            return;
        }
        throw new IllegalArgumentException("referenceResolver cannot be null.");
    }

    public boolean setReferences(boolean z) {
        if (z == this.references) {
            return z;
        }
        this.references = z;
        if (z && this.referenceResolver == null) {
            this.referenceResolver = new MapReferenceResolver();
        }
        if (Log.TRACE) {
            Log.trace("kryo", "References: " + z);
        }
        return !z;
    }

    public void setRegistrationRequired(boolean z) {
        this.registrationRequired = z;
        if (Log.TRACE) {
            Log.trace("kryo", "Registration required: " + z);
        }
    }

    public void setStreamFactory(StreamFactory streamFactory) {
        this.streamFactory = streamFactory;
    }

    public Registration writeClass(Output output, Class cls) {
        if (output != null) {
            try {
                return this.classResolver.writeClass(output, cls);
            } finally {
                if (this.depth == 0 && this.autoReset) {
                    reset();
                }
            }
        }
        throw new IllegalArgumentException("output cannot be null.");
    }

    public void writeClassAndObject(Output output, Object obj) {
        int i;
        boolean z;
        if (output != null) {
            beginObject();
            try {
                if (obj == null) {
                    writeClass(output, null);
                    if (i != 0) {
                        return;
                    }
                    if (!z) {
                        return;
                    }
                    return;
                }
                Registration writeClass = writeClass(output, obj.getClass());
                if (this.references && writeReferenceOrNull(output, obj, false)) {
                    writeClass.getSerializer().setGenerics(this, null);
                    int i2 = this.depth - 1;
                    this.depth = i2;
                    if (i2 != 0 || !this.autoReset) {
                        return;
                    }
                    reset();
                    return;
                }
                if (Log.TRACE || (Log.DEBUG && this.depth == 1)) {
                    Util.log("Write", obj);
                }
                writeClass.getSerializer().write(this, output, obj);
                int i3 = this.depth - 1;
                this.depth = i3;
                if (i3 != 0 || !this.autoReset) {
                    return;
                }
                reset();
                return;
            } finally {
                i = this.depth - 1;
                this.depth = i;
                if (i == 0 && this.autoReset) {
                    reset();
                }
            }
        }
        throw new IllegalArgumentException("output cannot be null.");
    }

    public void writeObject(Output output, Object obj) {
        int i;
        boolean z;
        if (output != null) {
            if (obj != null) {
                beginObject();
                try {
                    if (this.references && writeReferenceOrNull(output, obj, false)) {
                        getRegistration(obj.getClass()).getSerializer().setGenerics(this, null);
                        if (i != 0) {
                            return;
                        }
                        if (!z) {
                            return;
                        }
                        return;
                    }
                    if (Log.TRACE || (Log.DEBUG && this.depth == 1)) {
                        Util.log("Write", obj);
                    }
                    getRegistration(obj.getClass()).getSerializer().write(this, output, obj);
                    int i2 = this.depth - 1;
                    this.depth = i2;
                    if (i2 != 0 || !this.autoReset) {
                        return;
                    }
                    reset();
                    return;
                } finally {
                    i = this.depth - 1;
                    this.depth = i;
                    if (i == 0 && this.autoReset) {
                        reset();
                    }
                }
            }
            throw new IllegalArgumentException("object cannot be null.");
        }
        throw new IllegalArgumentException("output cannot be null.");
    }

    public void writeObjectOrNull(Output output, Object obj, Class cls) {
        int i;
        boolean z;
        if (output != null) {
            beginObject();
            try {
                Serializer serializer = getRegistration(cls).getSerializer();
                if (this.references) {
                    if (writeReferenceOrNull(output, obj, true)) {
                        serializer.setGenerics(this, null);
                        if (i != 0) {
                            return;
                        }
                        if (!z) {
                            return;
                        }
                        return;
                    }
                } else if (!serializer.getAcceptsNull()) {
                    if (obj == null) {
                        if (Log.TRACE || (Log.DEBUG && this.depth == 1)) {
                            Util.log("Write", obj);
                        }
                        output.writeByte((byte) 0);
                        int i2 = this.depth - 1;
                        this.depth = i2;
                        if (i2 != 0 || !this.autoReset) {
                            return;
                        }
                        reset();
                        return;
                    }
                    output.writeByte((byte) 1);
                }
                if (Log.TRACE || (Log.DEBUG && this.depth == 1)) {
                    Util.log("Write", obj);
                }
                serializer.write(this, output, obj);
                int i3 = this.depth - 1;
                this.depth = i3;
                if (i3 != 0 || !this.autoReset) {
                    return;
                }
                reset();
                return;
            } finally {
                i = this.depth - 1;
                this.depth = i;
                if (i == 0 && this.autoReset) {
                    reset();
                }
            }
        }
        throw new IllegalArgumentException("output cannot be null.");
    }

    boolean writeReferenceOrNull(Output output, Object obj, boolean z) {
        if (obj == null) {
            if (Log.TRACE || (Log.DEBUG && this.depth == 1)) {
                Util.log("Write", null);
            }
            output.writeVarInt(0, true);
            return true;
        } else if (!this.referenceResolver.useReferences(obj.getClass())) {
            if (z) {
                output.writeVarInt(1, true);
            }
            return false;
        } else {
            int writtenId = this.referenceResolver.getWrittenId(obj);
            if (writtenId != -1) {
                if (Log.DEBUG) {
                    StringBuilder outline109 = GeneratedOutlineSupport1.outline109("Write object reference ", writtenId, RealTimeTextConstants.COLON_SPACE);
                    outline109.append(Util.string(obj));
                    Log.debug("kryo", outline109.toString());
                }
                output.writeVarInt(writtenId + 2, true);
                return true;
            }
            int addWrittenObject = this.referenceResolver.addWrittenObject(obj);
            output.writeVarInt(1, true);
            if (Log.TRACE) {
                StringBuilder outline1092 = GeneratedOutlineSupport1.outline109("Write initial object reference ", addWrittenObject, RealTimeTextConstants.COLON_SPACE);
                outline1092.append(Util.string(obj));
                Log.trace("kryo", outline1092.toString());
            }
            return false;
        }
    }

    public Kryo(ReferenceResolver referenceResolver) {
        this(new DefaultClassResolver(), referenceResolver, new DefaultStreamFactory());
    }

    public Kryo(ClassResolver classResolver, ReferenceResolver referenceResolver) {
        this(classResolver, referenceResolver, new DefaultStreamFactory());
    }

    public Registration register(Class cls, int i) {
        Registration registration = this.classResolver.getRegistration(cls);
        return registration != null ? registration : register(cls, getDefaultSerializer(cls), i);
    }

    public void setDefaultSerializer(Class<? extends Serializer> cls) {
        if (cls != null) {
            this.defaultSerializer = new ReflectionSerializerFactory(cls);
            return;
        }
        throw new IllegalArgumentException("serializer cannot be null.");
    }

    public Kryo(ClassResolver classResolver, ReferenceResolver referenceResolver, StreamFactory streamFactory) {
        this.defaultSerializer = new ReflectionSerializerFactory(FieldSerializer.class);
        this.defaultSerializers = new ArrayList<>(32);
        this.classLoader = Kryo.class.getClassLoader();
        this.strategy = new DefaultInstantiatorStrategy();
        this.maxDepth = Integer.MAX_VALUE;
        this.autoReset = true;
        this.readReferenceIds = new IntArray(0);
        this.copyReferences = true;
        this.asmEnabled = false;
        if (classResolver != null) {
            this.classResolver = classResolver;
            classResolver.setKryo(this);
            this.streamFactory = streamFactory;
            streamFactory.setKryo(this);
            this.referenceResolver = referenceResolver;
            if (referenceResolver != null) {
                referenceResolver.setKryo(this);
                this.references = true;
            }
            addDefaultSerializer(byte[].class, DefaultArraySerializers.ByteArraySerializer.class);
            addDefaultSerializer(char[].class, DefaultArraySerializers.CharArraySerializer.class);
            addDefaultSerializer(short[].class, DefaultArraySerializers.ShortArraySerializer.class);
            addDefaultSerializer(int[].class, DefaultArraySerializers.IntArraySerializer.class);
            addDefaultSerializer(long[].class, DefaultArraySerializers.LongArraySerializer.class);
            addDefaultSerializer(float[].class, DefaultArraySerializers.FloatArraySerializer.class);
            addDefaultSerializer(double[].class, DefaultArraySerializers.DoubleArraySerializer.class);
            addDefaultSerializer(boolean[].class, DefaultArraySerializers.BooleanArraySerializer.class);
            addDefaultSerializer(String[].class, DefaultArraySerializers.StringArraySerializer.class);
            addDefaultSerializer(Object[].class, DefaultArraySerializers.ObjectArraySerializer.class);
            addDefaultSerializer(KryoSerializable.class, DefaultSerializers.KryoSerializableSerializer.class);
            addDefaultSerializer(BigInteger.class, DefaultSerializers.BigIntegerSerializer.class);
            addDefaultSerializer(BigDecimal.class, DefaultSerializers.BigDecimalSerializer.class);
            addDefaultSerializer(Class.class, DefaultSerializers.ClassSerializer.class);
            addDefaultSerializer(Date.class, DefaultSerializers.DateSerializer.class);
            addDefaultSerializer(Enum.class, DefaultSerializers.EnumSerializer.class);
            addDefaultSerializer(EnumSet.class, DefaultSerializers.EnumSetSerializer.class);
            addDefaultSerializer(Currency.class, DefaultSerializers.CurrencySerializer.class);
            addDefaultSerializer(StringBuffer.class, DefaultSerializers.StringBufferSerializer.class);
            addDefaultSerializer(StringBuilder.class, DefaultSerializers.StringBuilderSerializer.class);
            addDefaultSerializer(Collections.EMPTY_LIST.getClass(), DefaultSerializers.CollectionsEmptyListSerializer.class);
            addDefaultSerializer(Collections.EMPTY_MAP.getClass(), DefaultSerializers.CollectionsEmptyMapSerializer.class);
            addDefaultSerializer(Collections.EMPTY_SET.getClass(), DefaultSerializers.CollectionsEmptySetSerializer.class);
            addDefaultSerializer(Collections.singletonList(null).getClass(), DefaultSerializers.CollectionsSingletonListSerializer.class);
            addDefaultSerializer(Collections.singletonMap(null, null).getClass(), DefaultSerializers.CollectionsSingletonMapSerializer.class);
            addDefaultSerializer(Collections.singleton(null).getClass(), DefaultSerializers.CollectionsSingletonSetSerializer.class);
            addDefaultSerializer(TreeSet.class, DefaultSerializers.TreeSetSerializer.class);
            addDefaultSerializer(Collection.class, CollectionSerializer.class);
            addDefaultSerializer(TreeMap.class, DefaultSerializers.TreeMapSerializer.class);
            addDefaultSerializer(Map.class, MapSerializer.class);
            addDefaultSerializer(TimeZone.class, DefaultSerializers.TimeZoneSerializer.class);
            addDefaultSerializer(Calendar.class, DefaultSerializers.CalendarSerializer.class);
            addDefaultSerializer(Locale.class, DefaultSerializers.LocaleSerializer.class);
            this.lowPriorityDefaultSerializerCount = this.defaultSerializers.size();
            register(Integer.TYPE, new DefaultSerializers.IntSerializer());
            register(String.class, new DefaultSerializers.StringSerializer());
            register(Float.TYPE, new DefaultSerializers.FloatSerializer());
            register(Boolean.TYPE, new DefaultSerializers.BooleanSerializer());
            register(Byte.TYPE, new DefaultSerializers.ByteSerializer());
            register(Character.TYPE, new DefaultSerializers.CharSerializer());
            register(Short.TYPE, new DefaultSerializers.ShortSerializer());
            register(Long.TYPE, new DefaultSerializers.LongSerializer());
            register(Double.TYPE, new DefaultSerializers.DoubleSerializer());
            register(Void.TYPE, new DefaultSerializers.VoidSerializer());
            return;
        }
        throw new IllegalArgumentException("classResolver cannot be null.");
    }

    public void addDefaultSerializer(Class cls, SerializerFactory serializerFactory) {
        if (cls != null) {
            if (serializerFactory != null) {
                DefaultSerializerEntry defaultSerializerEntry = new DefaultSerializerEntry(cls, serializerFactory);
                ArrayList<DefaultSerializerEntry> arrayList = this.defaultSerializers;
                arrayList.add(arrayList.size() - this.lowPriorityDefaultSerializerCount, defaultSerializerEntry);
                return;
            }
            throw new IllegalArgumentException("serializerFactory cannot be null.");
        }
        throw new IllegalArgumentException("type cannot be null.");
    }

    public Registration register(Class cls, Serializer serializer) {
        Registration registration = this.classResolver.getRegistration(cls);
        if (registration != null) {
            registration.setSerializer(serializer);
            return registration;
        }
        return this.classResolver.register(new Registration(cls, serializer, getNextRegistrationId()));
    }

    public Registration register(Class cls, Serializer serializer, int i) {
        if (i >= 0) {
            return register(new Registration(cls, serializer, i));
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("id must be >= 0: ", i));
    }

    public void addDefaultSerializer(Class cls, Class<? extends Serializer> cls2) {
        if (cls != null) {
            if (cls2 != null) {
                DefaultSerializerEntry defaultSerializerEntry = new DefaultSerializerEntry(cls, new ReflectionSerializerFactory(cls2));
                ArrayList<DefaultSerializerEntry> arrayList = this.defaultSerializers;
                arrayList.add(arrayList.size() - this.lowPriorityDefaultSerializerCount, defaultSerializerEntry);
                return;
            }
            throw new IllegalArgumentException("serializerClass cannot be null.");
        }
        throw new IllegalArgumentException("type cannot be null.");
    }

    public Registration register(Registration registration) {
        int id = registration.getId();
        if (id >= 0) {
            Registration registration2 = getRegistration(registration.getId());
            if (Log.DEBUG && registration2 != null && registration2.getType() != registration.getType()) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("An existing registration with a different type already uses ID: ");
                outline107.append(registration.getId());
                outline107.append("\nExisting registration: ");
                outline107.append(registration2);
                outline107.append("\nUnable to set registration: ");
                outline107.append(registration);
                Log.debug(outline107.toString());
            }
            return this.classResolver.register(registration);
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("id must be > 0: ", id));
    }

    public void writeObject(Output output, Object obj, Serializer serializer) {
        int i;
        boolean z;
        if (output != null) {
            if (obj == null) {
                throw new IllegalArgumentException("object cannot be null.");
            }
            if (serializer != null) {
                beginObject();
                try {
                    if (this.references && writeReferenceOrNull(output, obj, false)) {
                        serializer.setGenerics(this, null);
                        if (i != 0) {
                            return;
                        }
                        if (!z) {
                            return;
                        }
                        return;
                    }
                    if (Log.TRACE || (Log.DEBUG && this.depth == 1)) {
                        Util.log("Write", obj);
                    }
                    serializer.write(this, output, obj);
                    int i2 = this.depth - 1;
                    this.depth = i2;
                    if (i2 != 0 || !this.autoReset) {
                        return;
                    }
                    reset();
                    return;
                } finally {
                    i = this.depth - 1;
                    this.depth = i;
                    if (i == 0 && this.autoReset) {
                        reset();
                    }
                }
            }
            throw new IllegalArgumentException("serializer cannot be null.");
        }
        throw new IllegalArgumentException("output cannot be null.");
    }

    public Registration getRegistration(int i) {
        return this.classResolver.getRegistration(i);
    }

    public <T> T copy(T t, Serializer serializer) {
        T t2;
        if (t == null) {
            return null;
        }
        if (this.copyShallow) {
            return t;
        }
        this.copyDepth++;
        try {
            if (this.originalToCopy == null) {
                this.originalToCopy = new IdentityMap();
            }
            T t3 = (T) this.originalToCopy.get(t);
            if (t3 != null) {
                return t3;
            }
            if (this.copyReferences) {
                this.needsCopyReference = t;
            }
            if (t instanceof KryoCopyable) {
                t2 = (T) ((KryoCopyable) t).copy(this);
            } else {
                t2 = (T) serializer.copy(this, t);
            }
            if (this.needsCopyReference != null) {
                reference(t2);
            }
            if (Log.TRACE || (Log.DEBUG && this.copyDepth == 1)) {
                Util.log("Copy", t2);
            }
            int i = this.copyDepth - 1;
            this.copyDepth = i;
            if (i == 0) {
                reset();
            }
            return t2;
        } finally {
            int i2 = this.copyDepth - 1;
            this.copyDepth = i2;
            if (i2 == 0) {
                reset();
            }
        }
    }

    public <T> T readObject(Input input, Class<T> cls, Serializer serializer) {
        T t;
        if (input != null) {
            if (cls == null) {
                throw new IllegalArgumentException("type cannot be null.");
            }
            if (serializer != null) {
                beginObject();
                try {
                    if (this.references) {
                        int readReferenceOrNull = readReferenceOrNull(input, cls, false);
                        if (readReferenceOrNull == -1) {
                            return (T) this.readObject;
                        }
                        t = (T) serializer.mo6848read(this, input, cls);
                        if (readReferenceOrNull == this.readReferenceIds.size) {
                            reference(t);
                        }
                    } else {
                        t = (T) serializer.mo6848read(this, input, cls);
                    }
                    if (Log.TRACE || (Log.DEBUG && this.depth == 1)) {
                        Util.log(ExternalNotificationsCapabilityAgentConstants.READ_EVENT_NAME, t);
                    }
                    int i = this.depth - 1;
                    this.depth = i;
                    if (i == 0 && this.autoReset) {
                        reset();
                    }
                    return t;
                } finally {
                    int i2 = this.depth - 1;
                    this.depth = i2;
                    if (i2 == 0 && this.autoReset) {
                        reset();
                    }
                }
            }
            throw new IllegalArgumentException("serializer cannot be null.");
        }
        throw new IllegalArgumentException("input cannot be null.");
    }

    public void writeObjectOrNull(Output output, Object obj, Serializer serializer) {
        int i;
        boolean z;
        if (output != null) {
            if (serializer != null) {
                beginObject();
                try {
                    if (this.references) {
                        if (writeReferenceOrNull(output, obj, true)) {
                            serializer.setGenerics(this, null);
                            if (i != 0) {
                                return;
                            }
                            if (!z) {
                                return;
                            }
                            return;
                        }
                    } else if (!serializer.getAcceptsNull()) {
                        if (obj == null) {
                            if (Log.TRACE || (Log.DEBUG && this.depth == 1)) {
                                Util.log("Write", null);
                            }
                            output.writeByte((byte) 0);
                            int i2 = this.depth - 1;
                            this.depth = i2;
                            if (i2 != 0 || !this.autoReset) {
                                return;
                            }
                            reset();
                            return;
                        }
                        output.writeByte((byte) 1);
                    }
                    if (Log.TRACE || (Log.DEBUG && this.depth == 1)) {
                        Util.log("Write", obj);
                    }
                    serializer.write(this, output, obj);
                    int i3 = this.depth - 1;
                    this.depth = i3;
                    if (i3 != 0 || !this.autoReset) {
                        return;
                    }
                    reset();
                    return;
                } finally {
                    i = this.depth - 1;
                    this.depth = i;
                    if (i == 0 && this.autoReset) {
                        reset();
                    }
                }
            }
            throw new IllegalArgumentException("serializer cannot be null.");
        }
        throw new IllegalArgumentException("output cannot be null.");
    }

    public <T> T copyShallow(T t, Serializer serializer) {
        T t2;
        if (t == null) {
            return null;
        }
        this.copyDepth++;
        this.copyShallow = true;
        try {
            if (this.originalToCopy == null) {
                this.originalToCopy = new IdentityMap();
            }
            T t3 = (T) this.originalToCopy.get(t);
            if (t3 != null) {
                return t3;
            }
            if (this.copyReferences) {
                this.needsCopyReference = t;
            }
            if (t instanceof KryoCopyable) {
                t2 = (T) ((KryoCopyable) t).copy(this);
            } else {
                t2 = (T) serializer.copy(this, t);
            }
            if (this.needsCopyReference != null) {
                reference(t2);
            }
            if (Log.TRACE || (Log.DEBUG && this.copyDepth == 1)) {
                Util.log("Shallow copy", t2);
            }
            this.copyShallow = false;
            int i = this.copyDepth - 1;
            this.copyDepth = i;
            if (i == 0) {
                reset();
            }
            return t2;
        } finally {
            this.copyShallow = false;
            int i2 = this.copyDepth - 1;
            this.copyDepth = i2;
            if (i2 == 0) {
                reset();
            }
        }
    }

    public <T> T readObjectOrNull(Input input, Class<T> cls, Serializer serializer) {
        T t;
        if (input != null) {
            if (cls == null) {
                throw new IllegalArgumentException("type cannot be null.");
            }
            if (serializer != null) {
                beginObject();
                try {
                    if (this.references) {
                        int readReferenceOrNull = readReferenceOrNull(input, cls, true);
                        if (readReferenceOrNull == -1) {
                            return (T) this.readObject;
                        }
                        t = (T) serializer.mo6848read(this, input, cls);
                        if (readReferenceOrNull == this.readReferenceIds.size) {
                            reference(t);
                        }
                    } else if (!serializer.getAcceptsNull() && input.readByte() == 0) {
                        if (Log.TRACE || (Log.DEBUG && this.depth == 1)) {
                            Util.log(ExternalNotificationsCapabilityAgentConstants.READ_EVENT_NAME, null);
                        }
                        int i = this.depth - 1;
                        this.depth = i;
                        if (i == 0 && this.autoReset) {
                            reset();
                        }
                        return null;
                    } else {
                        t = (T) serializer.mo6848read(this, input, cls);
                    }
                    if (Log.TRACE || (Log.DEBUG && this.depth == 1)) {
                        Util.log(ExternalNotificationsCapabilityAgentConstants.READ_EVENT_NAME, t);
                    }
                    int i2 = this.depth - 1;
                    this.depth = i2;
                    if (i2 == 0 && this.autoReset) {
                        reset();
                    }
                    return t;
                } finally {
                    int i3 = this.depth - 1;
                    this.depth = i3;
                    if (i3 == 0 && this.autoReset) {
                        reset();
                    }
                }
            }
            throw new IllegalArgumentException("serializer cannot be null.");
        }
        throw new IllegalArgumentException("input cannot be null.");
    }
}
