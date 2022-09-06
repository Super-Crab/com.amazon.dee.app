package com.amazon.org.codehaus.jackson.map;

import com.amazon.org.codehaus.jackson.annotate.JsonAutoDetect;
import com.amazon.org.codehaus.jackson.annotate.JsonMethod;
import com.amazon.org.codehaus.jackson.map.AnnotationIntrospector;
import com.amazon.org.codehaus.jackson.map.ClassIntrospector;
import com.amazon.org.codehaus.jackson.map.MapperConfig;
import com.amazon.org.codehaus.jackson.map.introspect.Annotated;
import com.amazon.org.codehaus.jackson.map.introspect.VisibilityChecker;
import com.amazon.org.codehaus.jackson.map.jsontype.SubtypeResolver;
import com.amazon.org.codehaus.jackson.map.jsontype.TypeIdResolver;
import com.amazon.org.codehaus.jackson.map.jsontype.TypeResolverBuilder;
import com.amazon.org.codehaus.jackson.map.jsontype.impl.StdSubtypeResolver;
import com.amazon.org.codehaus.jackson.map.type.ClassKey;
import com.amazon.org.codehaus.jackson.map.type.TypeBindings;
import com.amazon.org.codehaus.jackson.map.type.TypeFactory;
import com.amazon.org.codehaus.jackson.map.util.ClassUtil;
import com.amazon.org.codehaus.jackson.map.util.StdDateFormat;
import com.amazon.org.codehaus.jackson.type.JavaType;
import com.amazon.org.codehaus.jackson.type.TypeReference;
import java.text.DateFormat;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public abstract class MapperConfig<T extends MapperConfig<T>> implements ClassIntrospector.MixInResolver {
    protected static final DateFormat DEFAULT_DATE_FORMAT = StdDateFormat.instance;
    protected Base _base;
    protected HashMap<ClassKey, Class<?>> _mixInAnnotations;
    protected boolean _mixInAnnotationsShared;
    protected SubtypeResolver _subtypeResolver;

    /* loaded from: classes13.dex */
    public static class Base {
        protected final AnnotationIntrospector _annotationIntrospector;
        protected final ClassIntrospector<? extends BeanDescription> _classIntrospector;
        protected final DateFormat _dateFormat;
        protected final HandlerInstantiator _handlerInstantiator;
        protected final PropertyNamingStrategy _propertyNamingStrategy;
        protected final TypeFactory _typeFactory;
        protected final TypeResolverBuilder<?> _typeResolverBuilder;
        protected final VisibilityChecker<?> _visibilityChecker;

        public Base(ClassIntrospector<? extends BeanDescription> classIntrospector, AnnotationIntrospector annotationIntrospector, VisibilityChecker<?> visibilityChecker, PropertyNamingStrategy propertyNamingStrategy, TypeFactory typeFactory, TypeResolverBuilder<?> typeResolverBuilder, DateFormat dateFormat, HandlerInstantiator handlerInstantiator) {
            this._classIntrospector = classIntrospector;
            this._annotationIntrospector = annotationIntrospector;
            this._visibilityChecker = visibilityChecker;
            this._propertyNamingStrategy = propertyNamingStrategy;
            this._typeFactory = typeFactory;
            this._typeResolverBuilder = typeResolverBuilder;
            this._dateFormat = dateFormat;
            this._handlerInstantiator = handlerInstantiator;
        }

        public AnnotationIntrospector getAnnotationIntrospector() {
            return this._annotationIntrospector;
        }

        public ClassIntrospector<? extends BeanDescription> getClassIntrospector() {
            return this._classIntrospector;
        }

        public DateFormat getDateFormat() {
            return this._dateFormat;
        }

        public HandlerInstantiator getHandlerInstantiator() {
            return this._handlerInstantiator;
        }

        public PropertyNamingStrategy getPropertyNamingStrategy() {
            return this._propertyNamingStrategy;
        }

        public TypeFactory getTypeFactory() {
            return this._typeFactory;
        }

        public TypeResolverBuilder<?> getTypeResolverBuilder() {
            return this._typeResolverBuilder;
        }

        public VisibilityChecker<?> getVisibilityChecker() {
            return this._visibilityChecker;
        }

        public Base withAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
            return new Base(this._classIntrospector, annotationIntrospector, this._visibilityChecker, this._propertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, this._dateFormat, this._handlerInstantiator);
        }

        public Base withAppendedAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
            return withAnnotationIntrospector(AnnotationIntrospector.Pair.create(this._annotationIntrospector, annotationIntrospector));
        }

        public Base withClassIntrospector(ClassIntrospector<? extends BeanDescription> classIntrospector) {
            return new Base(classIntrospector, this._annotationIntrospector, this._visibilityChecker, this._propertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, this._dateFormat, this._handlerInstantiator);
        }

        public Base withDateFormat(DateFormat dateFormat) {
            return new Base(this._classIntrospector, this._annotationIntrospector, this._visibilityChecker, this._propertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, dateFormat, this._handlerInstantiator);
        }

        public Base withHandlerInstantiator(HandlerInstantiator handlerInstantiator) {
            return new Base(this._classIntrospector, this._annotationIntrospector, this._visibilityChecker, this._propertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, this._dateFormat, handlerInstantiator);
        }

        public Base withInsertedAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
            return withAnnotationIntrospector(AnnotationIntrospector.Pair.create(annotationIntrospector, this._annotationIntrospector));
        }

        public Base withPropertyNamingStrategy(PropertyNamingStrategy propertyNamingStrategy) {
            return new Base(this._classIntrospector, this._annotationIntrospector, this._visibilityChecker, propertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, this._dateFormat, this._handlerInstantiator);
        }

        public Base withTypeFactory(TypeFactory typeFactory) {
            return new Base(this._classIntrospector, this._annotationIntrospector, this._visibilityChecker, this._propertyNamingStrategy, typeFactory, this._typeResolverBuilder, this._dateFormat, this._handlerInstantiator);
        }

        public Base withTypeResolverBuilder(TypeResolverBuilder<?> typeResolverBuilder) {
            return new Base(this._classIntrospector, this._annotationIntrospector, this._visibilityChecker, this._propertyNamingStrategy, this._typeFactory, typeResolverBuilder, this._dateFormat, this._handlerInstantiator);
        }

        /* JADX WARN: Type inference failed for: r3v0, types: [com.amazon.org.codehaus.jackson.map.introspect.VisibilityChecker] */
        public Base withVisibility(JsonMethod jsonMethod, JsonAutoDetect.Visibility visibility) {
            return new Base(this._classIntrospector, this._annotationIntrospector, this._visibilityChecker.mo4225withVisibility(jsonMethod, visibility), this._propertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, this._dateFormat, this._handlerInstantiator);
        }

        public Base withVisibilityChecker(VisibilityChecker<?> visibilityChecker) {
            return new Base(this._classIntrospector, this._annotationIntrospector, visibilityChecker, this._propertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, this._dateFormat, this._handlerInstantiator);
        }
    }

    /* loaded from: classes13.dex */
    public interface ConfigFeature {
        boolean enabledByDefault();

        int getMask();
    }

    protected MapperConfig(ClassIntrospector<? extends BeanDescription> classIntrospector, AnnotationIntrospector annotationIntrospector, VisibilityChecker<?> visibilityChecker, SubtypeResolver subtypeResolver, PropertyNamingStrategy propertyNamingStrategy, TypeFactory typeFactory, HandlerInstantiator handlerInstantiator) {
        this._base = new Base(classIntrospector, annotationIntrospector, visibilityChecker, propertyNamingStrategy, typeFactory, null, DEFAULT_DATE_FORMAT, handlerInstantiator);
        this._subtypeResolver = subtypeResolver;
        this._mixInAnnotationsShared = true;
    }

    public final void addMixInAnnotations(Class<?> cls, Class<?> cls2) {
        HashMap<ClassKey, Class<?>> hashMap = this._mixInAnnotations;
        if (hashMap == null) {
            this._mixInAnnotationsShared = false;
            this._mixInAnnotations = new HashMap<>();
        } else if (this._mixInAnnotationsShared) {
            this._mixInAnnotationsShared = false;
            this._mixInAnnotations = new HashMap<>(hashMap);
        }
        this._mixInAnnotations.put(new ClassKey(cls), cls2);
    }

    @Deprecated
    public final void appendAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
        this._base = this._base.withAnnotationIntrospector(AnnotationIntrospector.Pair.create(getAnnotationIntrospector(), annotationIntrospector));
    }

    public abstract boolean canOverrideAccessModifiers();

    public JavaType constructSpecializedType(JavaType javaType, Class<?> cls) {
        return getTypeFactory().constructSpecializedType(javaType, cls);
    }

    public final JavaType constructType(Class<?> cls) {
        return getTypeFactory().constructType(cls, (TypeBindings) null);
    }

    /* renamed from: createUnshared */
    public abstract T mo4118createUnshared(SubtypeResolver subtypeResolver);

    @Override // com.amazon.org.codehaus.jackson.map.ClassIntrospector.MixInResolver
    public final Class<?> findMixInClassFor(Class<?> cls) {
        HashMap<ClassKey, Class<?>> hashMap = this._mixInAnnotations;
        if (hashMap == null) {
            return null;
        }
        return hashMap.get(new ClassKey(cls));
    }

    @Deprecated
    public abstract void fromAnnotations(Class<?> cls);

    public AnnotationIntrospector getAnnotationIntrospector() {
        return this._base.getAnnotationIntrospector();
    }

    public ClassIntrospector<? extends BeanDescription> getClassIntrospector() {
        return this._base.getClassIntrospector();
    }

    public final DateFormat getDateFormat() {
        return this._base.getDateFormat();
    }

    public final TypeResolverBuilder<?> getDefaultTyper(JavaType javaType) {
        return this._base.getTypeResolverBuilder();
    }

    public VisibilityChecker<?> getDefaultVisibilityChecker() {
        return this._base.getVisibilityChecker();
    }

    public final HandlerInstantiator getHandlerInstantiator() {
        return this._base.getHandlerInstantiator();
    }

    public final PropertyNamingStrategy getPropertyNamingStrategy() {
        return this._base.getPropertyNamingStrategy();
    }

    public final SubtypeResolver getSubtypeResolver() {
        if (this._subtypeResolver == null) {
            this._subtypeResolver = new StdSubtypeResolver();
        }
        return this._subtypeResolver;
    }

    public final TypeFactory getTypeFactory() {
        return this._base.getTypeFactory();
    }

    @Deprecated
    public final void insertAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
        this._base = this._base.withAnnotationIntrospector(AnnotationIntrospector.Pair.create(annotationIntrospector, getAnnotationIntrospector()));
    }

    public abstract <DESC extends BeanDescription> DESC introspectClassAnnotations(JavaType javaType);

    public <DESC extends BeanDescription> DESC introspectClassAnnotations(Class<?> cls) {
        return (DESC) introspectClassAnnotations(constructType(cls));
    }

    public abstract <DESC extends BeanDescription> DESC introspectDirectClassAnnotations(JavaType javaType);

    public <DESC extends BeanDescription> DESC introspectDirectClassAnnotations(Class<?> cls) {
        return (DESC) introspectDirectClassAnnotations(constructType(cls));
    }

    public abstract boolean isAnnotationProcessingEnabled();

    public abstract boolean isEnabled(ConfigFeature configFeature);

    public final int mixInCount() {
        HashMap<ClassKey, Class<?>> hashMap = this._mixInAnnotations;
        if (hashMap == null) {
            return 0;
        }
        return hashMap.size();
    }

    @Deprecated
    public final void setAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
        this._base = this._base.withAnnotationIntrospector(annotationIntrospector);
    }

    @Deprecated
    public void setDateFormat(DateFormat dateFormat) {
        if (dateFormat == null) {
            dateFormat = DEFAULT_DATE_FORMAT;
        }
        this._base = this._base.withDateFormat(dateFormat);
    }

    public final void setMixInAnnotations(Map<Class<?>, Class<?>> map) {
        HashMap<ClassKey, Class<?>> hashMap;
        if (map == null || map.size() <= 0) {
            hashMap = null;
        } else {
            hashMap = new HashMap<>(map.size());
            for (Map.Entry<Class<?>, Class<?>> entry : map.entrySet()) {
                hashMap.put(new ClassKey(entry.getKey()), entry.getValue());
            }
        }
        this._mixInAnnotationsShared = false;
        this._mixInAnnotations = hashMap;
    }

    public abstract boolean shouldSortPropertiesAlphabetically();

    public TypeIdResolver typeIdResolverInstance(Annotated annotated, Class<? extends TypeIdResolver> cls) {
        TypeIdResolver typeIdResolverInstance;
        HandlerInstantiator handlerInstantiator = getHandlerInstantiator();
        return (handlerInstantiator == null || (typeIdResolverInstance = handlerInstantiator.typeIdResolverInstance(this, annotated, cls)) == null) ? (TypeIdResolver) ClassUtil.createInstance(cls, canOverrideAccessModifiers()) : typeIdResolverInstance;
    }

    public TypeResolverBuilder<?> typeResolverBuilderInstance(Annotated annotated, Class<? extends TypeResolverBuilder<?>> cls) {
        TypeResolverBuilder<?> typeResolverBuilderInstance;
        HandlerInstantiator handlerInstantiator = getHandlerInstantiator();
        return (handlerInstantiator == null || (typeResolverBuilderInstance = handlerInstantiator.typeResolverBuilderInstance(this, annotated, cls)) == null) ? (TypeResolverBuilder) ClassUtil.createInstance(cls, canOverrideAccessModifiers()) : typeResolverBuilderInstance;
    }

    /* renamed from: withAnnotationIntrospector */
    public abstract T mo4119withAnnotationIntrospector(AnnotationIntrospector annotationIntrospector);

    /* renamed from: withAppendedAnnotationIntrospector */
    public abstract T mo4120withAppendedAnnotationIntrospector(AnnotationIntrospector annotationIntrospector);

    /* renamed from: withClassIntrospector */
    public abstract T mo4121withClassIntrospector(ClassIntrospector<? extends BeanDescription> classIntrospector);

    /* renamed from: withDateFormat */
    public abstract T mo4122withDateFormat(DateFormat dateFormat);

    /* renamed from: withHandlerInstantiator */
    public abstract T mo4123withHandlerInstantiator(HandlerInstantiator handlerInstantiator);

    /* renamed from: withInsertedAnnotationIntrospector */
    public abstract T mo4124withInsertedAnnotationIntrospector(AnnotationIntrospector annotationIntrospector);

    /* renamed from: withPropertyNamingStrategy */
    public abstract T mo4125withPropertyNamingStrategy(PropertyNamingStrategy propertyNamingStrategy);

    /* renamed from: withSubtypeResolver */
    public abstract T mo4126withSubtypeResolver(SubtypeResolver subtypeResolver);

    /* renamed from: withTypeFactory */
    public abstract T mo4127withTypeFactory(TypeFactory typeFactory);

    /* renamed from: withTypeResolverBuilder */
    public abstract T mo4128withTypeResolverBuilder(TypeResolverBuilder<?> typeResolverBuilder);

    /* renamed from: withVisibility */
    public abstract T mo4129withVisibility(JsonMethod jsonMethod, JsonAutoDetect.Visibility visibility);

    /* renamed from: withVisibilityChecker */
    public abstract T mo4130withVisibilityChecker(VisibilityChecker<?> visibilityChecker);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public static abstract class Impl<CFG extends ConfigFeature, T extends Impl<CFG, T>> extends MapperConfig<T> {
        protected int _featureFlags;

        /* JADX INFO: Access modifiers changed from: protected */
        public Impl(ClassIntrospector<? extends BeanDescription> classIntrospector, AnnotationIntrospector annotationIntrospector, VisibilityChecker<?> visibilityChecker, SubtypeResolver subtypeResolver, PropertyNamingStrategy propertyNamingStrategy, TypeFactory typeFactory, HandlerInstantiator handlerInstantiator, int i) {
            super(classIntrospector, annotationIntrospector, visibilityChecker, subtypeResolver, propertyNamingStrategy, typeFactory, handlerInstantiator);
            this._featureFlags = i;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static <F extends Enum<F> & ConfigFeature> int collectFeatureDefaults(Class<F> cls) {
            int i = 0;
            for (Enum r3 : (Enum[]) cls.getEnumConstants()) {
                ConfigFeature configFeature = (ConfigFeature) r3;
                if (configFeature.enabledByDefault()) {
                    i |= configFeature.getMask();
                }
            }
            return i;
        }

        @Deprecated
        public void disable(CFG cfg) {
            this._featureFlags = (~cfg.getMask()) & this._featureFlags;
        }

        @Deprecated
        public void enable(CFG cfg) {
            this._featureFlags = cfg.getMask() | this._featureFlags;
        }

        @Override // com.amazon.org.codehaus.jackson.map.MapperConfig
        public boolean isEnabled(ConfigFeature configFeature) {
            return (configFeature.getMask() & this._featureFlags) != 0;
        }

        @Deprecated
        public void set(CFG cfg, boolean z) {
            if (z) {
                enable(cfg);
            } else {
                disable(cfg);
            }
        }

        public abstract T with(CFG... cfgArr);

        public abstract T without(CFG... cfgArr);

        /* JADX INFO: Access modifiers changed from: protected */
        public Impl(Impl<CFG, T> impl) {
            super(impl);
            this._featureFlags = impl._featureFlags;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Impl(Impl<CFG, T> impl, int i) {
            super(impl);
            this._featureFlags = i;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Impl(Impl<CFG, T> impl, Base base, SubtypeResolver subtypeResolver) {
            super(impl, base, subtypeResolver);
            this._featureFlags = impl._featureFlags;
        }
    }

    public final JavaType constructType(TypeReference<?> typeReference) {
        return getTypeFactory().constructType(typeReference.getType(), (TypeBindings) null);
    }

    protected MapperConfig(MapperConfig<T> mapperConfig) {
        this(mapperConfig, mapperConfig._base, mapperConfig._subtypeResolver);
    }

    protected MapperConfig(MapperConfig<T> mapperConfig, Base base, SubtypeResolver subtypeResolver) {
        this._base = base;
        this._subtypeResolver = subtypeResolver;
        this._mixInAnnotationsShared = true;
        this._mixInAnnotations = mapperConfig._mixInAnnotations;
    }
}
