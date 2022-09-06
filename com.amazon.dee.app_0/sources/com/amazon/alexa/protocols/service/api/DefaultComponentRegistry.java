package com.amazon.alexa.protocols.service.api;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.protocols.service.api.DefaultComponentRegistry;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
/* JADX INFO: Access modifiers changed from: package-private */
@RequiresApi(21)
/* loaded from: classes9.dex */
public final class DefaultComponentRegistry extends ComponentRegistry {
    private final Context applicationContext;
    private final ComponentGetter getter;
    private static final String TAG = ComponentRegistry.class.getSimpleName();
    private static final long CREATION_TIME_WARNING_NANOS = TimeUnit.MILLISECONDS.toNanos(100);
    private final Map<Class<?>, ProviderOfConstructionResult<?>> bindings = new ConcurrentHashMap();
    private final Map<Class<?>, ConstructionResult<?>> implementations = new ConcurrentHashMap();
    private final Map<Class<?>, Object> constructionLocks = new ConcurrentHashMap();
    private final Set<Class<?>> underConstruction = new HashSet();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static class ConstructionResult<T> {
        protected final T reference;
        protected final Throwable throwable;

        protected ConstructionResult(T t, Throwable th) {
            boolean z = true;
            Preconditions.checkArgument((th == null ? false : z) ^ (t != null));
            this.reference = t;
            this.throwable = th;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T> ConstructionResult<T> fromThrowable(Throwable th) {
            return new ConstructionResult<>(null, th);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T> ConstructionResult<T> fromType(T t) {
            return new ConstructionResult<>(t, null);
        }

        protected boolean isPresent() {
            return this.reference != null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static class ConstructionResultList<T> extends ConstructionResult<ImmutableList<T>> {
        protected ConstructionResultList(ImmutableList<T> immutableList, Throwable th) {
            super(immutableList, th);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T> ConstructionResultList<T> fromList(ImmutableList<T> immutableList) {
            return new ConstructionResultList<>(immutableList, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T> ConstructionResultList<T> fromListThrowable(Throwable th) {
            return new ConstructionResultList<>(null, th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public interface ProviderOfConstructionResult<T> {
        @NonNull
        /* renamed from: provide */
        ConstructionResult<T> mo2345provide();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public interface ProviderOfList<T> extends ProviderOfConstructionResult<ImmutableList<T>> {
        @Override // com.amazon.alexa.protocols.service.api.DefaultComponentRegistry.ProviderOfConstructionResult
        @NonNull
        /* renamed from: provide  reason: collision with other method in class */
        ConstructionResultList<T> mo2345provide();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DefaultComponentRegistry(Context context) {
        Preconditions.checkNotNull(context);
        this.applicationContext = context.getApplicationContext();
        this.getter = new DefaultComponentGetter(this);
    }

    private <T> void checkInterfaceArgument(@NonNull Class<T> cls) {
        Preconditions.checkNotNull(cls, "interface was null");
        boolean isInterface = cls.isInterface();
        Preconditions.checkArgument(isInterface, cls + " is not an interface");
        Preconditions.checkState(!this.bindings.containsKey(cls), cls + " was already bound");
        Preconditions.checkState(!this.constructionLocks.containsKey(cls), cls + " is already in constructionLocks");
    }

    @NonNull
    private static ComponentRegistry.ComponentFactory<?> createFactory(String str) throws ReflectiveOperationException {
        return (ComponentRegistry.ComponentFactory) nonNullCast(ComponentRegistry.ComponentFactory.class, findClass(ComponentRegistry.ComponentFactory.class, str).getConstructor(new Class[0]).newInstance(new Object[0]));
    }

    @NonNull
    private <T> T createImplementation(Class<T> cls, String str) throws ReflectiveOperationException {
        Class<?> findClass = findClass(cls, str);
        try {
            return (T) nonNullCast(cls, findClass.getConstructor(Context.class).newInstance(this.applicationContext));
        } catch (NoSuchMethodException unused) {
            try {
                try {
                    return (T) nonNullCast(cls, findClass.getConstructor(ComponentGetter.class, Context.class).newInstance(this.getter, this.applicationContext));
                } catch (NoSuchMethodException unused2) {
                    return (T) nonNullCast(cls, findClass.getConstructor(new Class[0]).newInstance(new Object[0]));
                }
            } catch (NoSuchMethodException unused3) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("No constructor with allowed argument list found for ");
                outline107.append(cls.getCanonicalName());
                throw new NoSuchMethodException(outline107.toString());
            }
        }
    }

    @NonNull
    private static <T> Class<?> findClass(Class<T> cls, String str) throws ClassNotFoundException {
        if (str != null) {
            return Class.forName(str);
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("No binding for ");
        outline107.append(cls.getCanonicalName());
        throw new ClassNotFoundException(outline107.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    @NonNull
    /* renamed from: getOrThrow */
    public <T> T lambda$getLazy$4$DefaultComponentRegistry(@NonNull Class<T> cls) {
        if (!isNotBoundAsSingle(cls)) {
            ConstructionResult<?> constructionResult = this.implementations.get(cls);
            if (constructionResult == null) {
                constructionResult = instantiate(cls);
            }
            if (constructionResult.isPresent()) {
                return (T) nonNullCast(cls, constructionResult.reference);
            }
            throw new IllegalStateException(GeneratedOutlineSupport1.outline66("Unconstructable api:", cls), constructionResult.throwable);
        }
        throw new IllegalStateException(GeneratedOutlineSupport1.outline66("Unbound api:", cls));
    }

    private <T> boolean isBoundAsList(Class<T> cls) {
        return cls != null && (this.bindings.get(cls) instanceof ProviderOfList);
    }

    private <T> boolean isNotBoundAsSingle(Class<T> cls) {
        ProviderOfConstructionResult<?> providerOfConstructionResult;
        return cls == null || (providerOfConstructionResult = this.bindings.get(cls)) == null || (providerOfConstructionResult instanceof ProviderOfList);
    }

    @NonNull
    private static <T> T nonNullCast(@NonNull Class<T> cls, @NonNull Object obj) {
        return cls.cast(obj);
    }

    private <T> void provideInstance(@NonNull Class<T> cls) {
        ProviderOfConstructionResult<?> providerOfConstructionResult = this.bindings.get(cls);
        if (providerOfConstructionResult == null) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("No provider was registered for ");
            outline107.append(cls.getCanonicalName());
            Log.e(str, outline107.toString(), new Throwable());
            return;
        }
        long nanoTime = System.nanoTime();
        ConstructionResult<?> mo2345provide = providerOfConstructionResult.mo2345provide();
        Preconditions.checkState(mo2345provide != null);
        if (mo2345provide != null && mo2345provide.isPresent()) {
            UnmodifiableIterator mo8029iterator = (mo2345provide instanceof ConstructionResultList ? (ImmutableList) ((ConstructionResultList) mo2345provide).reference : ImmutableList.of(mo2345provide.reference)).mo8029iterator();
            while (mo8029iterator.hasNext()) {
                E next = mo8029iterator.next();
                cls.cast(next);
                if (next instanceof InitializableComponent) {
                    ((InitializableComponent) next).initializeComponent(this.getter, this.applicationContext);
                }
            }
        }
        this.implementations.put(cls, mo2345provide);
        if (System.nanoTime() - nanoTime <= CREATION_TIME_WARNING_NANOS) {
            return;
        }
        String str2 = TAG;
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Exceeded nanos: ");
        outline1072.append(CREATION_TIME_WARNING_NANOS);
        outline1072.append(" creating:");
        outline1072.append(cls.getCanonicalName());
        Log.w(str2, outline1072.toString());
    }

    @Override // com.amazon.alexa.protocols.service.api.ComponentRegistry
    @NonNull
    public synchronized <T> ComponentRegistry bind(@NonNull final Class<T> cls, @NonNull final String str) throws IllegalArgumentException, IllegalStateException {
        checkInterfaceArgument(cls);
        Preconditions.checkNotNull(str, "class name was null");
        Preconditions.checkArgument(!str.isEmpty(), "class name was empty");
        this.constructionLocks.put(cls, new Object());
        this.bindings.put(cls, new ProviderOfConstructionResult() { // from class: com.amazon.alexa.protocols.service.api.-$$Lambda$DefaultComponentRegistry$2TiThXPNsZgxyIJcf5vtJ557hTc
            @Override // com.amazon.alexa.protocols.service.api.DefaultComponentRegistry.ProviderOfConstructionResult
            /* renamed from: provide */
            public final DefaultComponentRegistry.ConstructionResult mo2345provide() {
                return DefaultComponentRegistry.this.lambda$bind$0$DefaultComponentRegistry(cls, str);
            }
        });
        return this;
    }

    @Override // com.amazon.alexa.protocols.service.api.ComponentRegistry
    @NonNull
    public synchronized <T> ComponentRegistry bindConcreteFactory(@NonNull Class<T> cls, @NonNull final ComponentRegistry.ConcreteComponentFactory<? extends T> concreteComponentFactory) {
        checkInterfaceArgument(cls);
        Preconditions.checkNotNull(concreteComponentFactory, cls + " was attempted to be bound with a null factory");
        this.constructionLocks.put(cls, new Object());
        this.bindings.put(cls, new ProviderOfConstructionResult() { // from class: com.amazon.alexa.protocols.service.api.-$$Lambda$DefaultComponentRegistry$C19tIwn9djE1lYIqBuTrQT3Vk4o
            @Override // com.amazon.alexa.protocols.service.api.DefaultComponentRegistry.ProviderOfConstructionResult
            /* renamed from: provide */
            public final DefaultComponentRegistry.ConstructionResult mo2345provide() {
                return DefaultComponentRegistry.this.lambda$bindConcreteFactory$3$DefaultComponentRegistry(concreteComponentFactory);
            }
        });
        return this;
    }

    @Override // com.amazon.alexa.protocols.service.api.ComponentRegistry
    @NonNull
    public synchronized <T> ComponentRegistry bindFactory(@NonNull Class<T> cls, @NonNull final String str) {
        checkInterfaceArgument(cls);
        Preconditions.checkNotNull(str, "factory class name was null");
        Preconditions.checkArgument(!str.isEmpty(), "factory class name was empty");
        this.constructionLocks.put(cls, new Object());
        this.bindings.put(cls, new ProviderOfConstructionResult() { // from class: com.amazon.alexa.protocols.service.api.-$$Lambda$DefaultComponentRegistry$a57oAgk1zNVjJI698LJnfmWudVo
            @Override // com.amazon.alexa.protocols.service.api.DefaultComponentRegistry.ProviderOfConstructionResult
            /* renamed from: provide */
            public final DefaultComponentRegistry.ConstructionResult mo2345provide() {
                return DefaultComponentRegistry.this.lambda$bindFactory$2$DefaultComponentRegistry(str);
            }
        });
        return this;
    }

    @Override // com.amazon.alexa.protocols.service.api.ComponentRegistry
    @NonNull
    public synchronized <T> ComponentRegistry bindList(@NonNull final Class<T> cls, @NonNull String... strArr) throws IllegalArgumentException, IllegalStateException {
        checkInterfaceArgument(cls);
        boolean z = true;
        Preconditions.checkArgument(strArr.length > 0);
        HashSet hashSet = new HashSet(strArr.length);
        for (String str : strArr) {
            Preconditions.checkArgument(!TextUtils.isEmpty(str));
            hashSet.add(str);
        }
        if (hashSet.size() != strArr.length) {
            z = false;
        }
        Preconditions.checkArgument(z);
        final String[] strArr2 = (String[]) Arrays.copyOf(strArr, strArr.length);
        this.constructionLocks.put(cls, new Object());
        this.bindings.put(cls, new ProviderOfList() { // from class: com.amazon.alexa.protocols.service.api.-$$Lambda$DefaultComponentRegistry$zRQEw5tWU3iYoSj90z8E5-dr-OA
            @Override // com.amazon.alexa.protocols.service.api.DefaultComponentRegistry.ProviderOfList, com.amazon.alexa.protocols.service.api.DefaultComponentRegistry.ProviderOfConstructionResult
            /* renamed from: provide */
            public final DefaultComponentRegistry.ConstructionResultList mo2345provide() {
                return DefaultComponentRegistry.this.lambda$bindList$1$DefaultComponentRegistry(strArr2, cls);
            }
        });
        return this;
    }

    @Override // com.amazon.alexa.protocols.service.api.ComponentRegistry
    @NonNull
    public <T> Optional<T> get(@NonNull Class<T> cls) {
        try {
            return Optional.of(lambda$getLazy$4$DefaultComponentRegistry(cls));
        } catch (IllegalStateException e) {
            Log.e(TAG, "Optional will be absent", e);
            return Optional.absent();
        }
    }

    @Override // com.amazon.alexa.protocols.service.api.ComponentRegistry
    @NonNull
    public <T> LazyComponent<T> getLazy(@NonNull final Class<T> cls) {
        if (!isNotBoundAsSingle(cls)) {
            return new LazyComponent() { // from class: com.amazon.alexa.protocols.service.api.-$$Lambda$DefaultComponentRegistry$0nmzbjIl3hxoaMcQR5nr11_5s9M
                @Override // com.amazon.alexa.protocols.service.api.LazyComponent, javax.inject.Provider
                /* renamed from: get */
                public final Object mo10268get() {
                    return DefaultComponentRegistry.this.lambda$getLazy$4$DefaultComponentRegistry(cls);
                }
            };
        }
        throw new IllegalStateException(GeneratedOutlineSupport1.outline66("Unbound api:", cls));
    }

    @Override // com.amazon.alexa.protocols.service.api.ComponentRegistry
    @NonNull
    public <T> LazyComponent<List<T>> getLazyList(@NonNull final Class<T> cls) {
        if (isBoundAsList(cls)) {
            return new LazyComponent() { // from class: com.amazon.alexa.protocols.service.api.-$$Lambda$DefaultComponentRegistry$ic_VJ6KIqE8WepHZMr3i4SWZONU
                @Override // com.amazon.alexa.protocols.service.api.LazyComponent, javax.inject.Provider
                /* renamed from: get */
                public final Object mo10268get() {
                    return DefaultComponentRegistry.this.lambda$getLazyList$5$DefaultComponentRegistry(cls);
                }
            };
        }
        throw new IllegalStateException(GeneratedOutlineSupport1.outline66("Unbound as List api:", cls));
    }

    @NonNull
    <T> ConstructionResult<?> instantiate(@NonNull Class<T> cls) {
        ConstructionResult<?> constructionResult;
        Object obj = this.constructionLocks.get(cls);
        if (this.bindings.containsKey(cls) && obj != null) {
            synchronized (obj) {
                try {
                    try {
                        if (!this.implementations.containsKey(cls)) {
                            if (this.underConstruction.add(cls)) {
                                try {
                                    provideInstance(cls);
                                    this.underConstruction.remove(cls);
                                } catch (Throwable th) {
                                    this.underConstruction.remove(cls);
                                    throw th;
                                }
                            } else {
                                throw new IllegalStateException("Circularity error constructing " + cls);
                            }
                        }
                        constructionResult = this.implementations.get(cls);
                    } catch (ClassCastException e) {
                        Log.e(TAG, "Unable to create instance", e);
                        return ConstructionResult.fromThrowable(e);
                    }
                } catch (Throwable th2) {
                    throw th2;
                }
            }
            return constructionResult;
        }
        String outline66 = GeneratedOutlineSupport1.outline66("No binding for ", cls);
        Log.e(TAG, outline66);
        return ConstructionResult.fromThrowable(new IllegalStateException(outline66));
    }

    public /* synthetic */ ConstructionResult lambda$bind$0$DefaultComponentRegistry(Class cls, String str) {
        try {
            return ConstructionResult.fromType(createImplementation(cls, str));
        } catch (ClassCastException | ReflectiveOperationException e) {
            String str2 = TAG;
            Log.e(str2, "Unable to create instance of:" + str, e);
            return ConstructionResult.fromThrowable(e);
        }
    }

    public /* synthetic */ ConstructionResult lambda$bindConcreteFactory$3$DefaultComponentRegistry(ComponentRegistry.ConcreteComponentFactory concreteComponentFactory) {
        return ConstructionResult.fromType(concreteComponentFactory.create(this.applicationContext));
    }

    public /* synthetic */ ConstructionResult lambda$bindFactory$2$DefaultComponentRegistry(String str) {
        try {
            ComponentRegistry.ComponentFactory<?> createFactory = createFactory(str);
            Object create = createFactory.create(this.getter, this.applicationContext);
            if (create == null) {
                create = createFactory.create(this.applicationContext);
            }
            return ConstructionResult.fromType(create);
        } catch (ClassCastException | ReflectiveOperationException e) {
            Log.e(TAG, "Unable to create instance", e);
            return ConstructionResult.fromThrowable(e);
        }
    }

    public /* synthetic */ ConstructionResultList lambda$bindList$1$DefaultComponentRegistry(String[] strArr, Class cls) {
        r0 = "";
        try {
            ImmutableList.Builder builder = ImmutableList.builder();
            for (String str : strArr) {
                builder.mo7849add((ImmutableList.Builder) createImplementation(cls, str));
            }
            return ConstructionResultList.fromList(builder.mo7852build());
        } catch (ClassCastException | ReflectiveOperationException e) {
            Log.e(TAG, "Unable to create instance of:" + str, e);
            return ConstructionResultList.fromListThrowable(e);
        }
    }

    public /* synthetic */ List lambda$getLazyList$5$DefaultComponentRegistry(Class cls) {
        ConstructionResult<?> constructionResult = this.implementations.get(cls);
        if (constructionResult == null) {
            constructionResult = instantiate(cls);
        }
        if (constructionResult instanceof ConstructionResultList) {
            if (constructionResult.isPresent()) {
                return (ImmutableList) constructionResult.reference;
            }
            throw new IllegalStateException("Construction Failed", constructionResult.throwable);
        }
        throw new IllegalStateException(GeneratedOutlineSupport1.outline66("Expected to get a List of implementations of:", cls));
    }
}
