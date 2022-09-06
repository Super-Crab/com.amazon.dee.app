package com.amazon.org.codehaus.jackson.map;

import com.amazon.org.codehaus.jackson.annotate.JsonAutoDetect;
import com.amazon.org.codehaus.jackson.annotate.JsonMethod;
import com.amazon.org.codehaus.jackson.map.MapperConfig;
import com.amazon.org.codehaus.jackson.map.annotate.JsonSerialize;
import com.amazon.org.codehaus.jackson.map.introspect.Annotated;
import com.amazon.org.codehaus.jackson.map.introspect.AnnotatedClass;
import com.amazon.org.codehaus.jackson.map.introspect.VisibilityChecker;
import com.amazon.org.codehaus.jackson.map.jsontype.SubtypeResolver;
import com.amazon.org.codehaus.jackson.map.jsontype.TypeResolverBuilder;
import com.amazon.org.codehaus.jackson.map.ser.FilterProvider;
import com.amazon.org.codehaus.jackson.map.type.ClassKey;
import com.amazon.org.codehaus.jackson.map.type.TypeFactory;
import com.amazon.org.codehaus.jackson.map.util.ClassUtil;
import com.amazon.org.codehaus.jackson.type.JavaType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.text.DateFormat;
import java.util.HashMap;
/* loaded from: classes13.dex */
public class SerializationConfig extends MapperConfig.Impl<Feature, SerializationConfig> {
    protected FilterProvider _filterProvider;
    protected JsonSerialize.Inclusion _serializationInclusion;
    protected Class<?> _serializationView;

    /* loaded from: classes13.dex */
    public enum Feature implements MapperConfig.ConfigFeature {
        USE_ANNOTATIONS(true),
        AUTO_DETECT_GETTERS(true),
        AUTO_DETECT_IS_GETTERS(true),
        AUTO_DETECT_FIELDS(true),
        CAN_OVERRIDE_ACCESS_MODIFIERS(true),
        REQUIRE_SETTERS_FOR_GETTERS(false),
        WRITE_NULL_PROPERTIES(true),
        USE_STATIC_TYPING(false),
        DEFAULT_VIEW_INCLUSION(true),
        WRAP_ROOT_VALUE(false),
        INDENT_OUTPUT(false),
        SORT_PROPERTIES_ALPHABETICALLY(false),
        FAIL_ON_EMPTY_BEANS(true),
        WRAP_EXCEPTIONS(true),
        CLOSE_CLOSEABLE(false),
        FLUSH_AFTER_WRITE_VALUE(true),
        WRITE_DATES_AS_TIMESTAMPS(true),
        WRITE_DATE_KEYS_AS_TIMESTAMPS(false),
        WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS(false),
        WRITE_ENUMS_USING_TO_STRING(false),
        WRITE_ENUMS_USING_INDEX(false),
        WRITE_NULL_MAP_VALUES(true),
        WRITE_EMPTY_JSON_ARRAYS(true);
        
        final boolean _defaultState;

        Feature(boolean z) {
            this._defaultState = z;
        }

        @Override // com.amazon.org.codehaus.jackson.map.MapperConfig.ConfigFeature
        public boolean enabledByDefault() {
            return this._defaultState;
        }

        @Override // com.amazon.org.codehaus.jackson.map.MapperConfig.ConfigFeature
        public int getMask() {
            return 1 << ordinal();
        }
    }

    public SerializationConfig(ClassIntrospector<? extends BeanDescription> classIntrospector, AnnotationIntrospector annotationIntrospector, VisibilityChecker<?> visibilityChecker, SubtypeResolver subtypeResolver, PropertyNamingStrategy propertyNamingStrategy, TypeFactory typeFactory, HandlerInstantiator handlerInstantiator) {
        super(classIntrospector, annotationIntrospector, visibilityChecker, subtypeResolver, propertyNamingStrategy, typeFactory, handlerInstantiator, MapperConfig.Impl.collectFeatureDefaults(Feature.class));
        this._serializationInclusion = null;
        this._filterProvider = null;
    }

    @Override // com.amazon.org.codehaus.jackson.map.MapperConfig
    public boolean canOverrideAccessModifiers() {
        return isEnabled(Feature.CAN_OVERRIDE_ACCESS_MODIFIERS);
    }

    @Override // com.amazon.org.codehaus.jackson.map.MapperConfig
    @Deprecated
    public void fromAnnotations(Class<?> cls) {
        AnnotationIntrospector annotationIntrospector = getAnnotationIntrospector();
        AnnotatedClass construct = AnnotatedClass.construct(cls, annotationIntrospector, null);
        this._base = this._base.withVisibilityChecker(annotationIntrospector.findAutoDetectVisibility(construct, getDefaultVisibilityChecker()));
        JsonSerialize.Inclusion findSerializationInclusion = annotationIntrospector.findSerializationInclusion(construct, null);
        if (findSerializationInclusion != this._serializationInclusion) {
            setSerializationInclusion(findSerializationInclusion);
        }
        JsonSerialize.Typing findSerializationTyping = annotationIntrospector.findSerializationTyping(construct);
        if (findSerializationTyping != null) {
            set(Feature.USE_STATIC_TYPING, findSerializationTyping == JsonSerialize.Typing.STATIC);
        }
    }

    @Override // com.amazon.org.codehaus.jackson.map.MapperConfig
    public AnnotationIntrospector getAnnotationIntrospector() {
        if (isEnabled(Feature.USE_ANNOTATIONS)) {
            return super.getAnnotationIntrospector();
        }
        return AnnotationIntrospector.nopInstance();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v3, types: [com.amazon.org.codehaus.jackson.map.introspect.VisibilityChecker, com.amazon.org.codehaus.jackson.map.introspect.VisibilityChecker<?>] */
    @Override // com.amazon.org.codehaus.jackson.map.MapperConfig
    public VisibilityChecker<?> getDefaultVisibilityChecker() {
        VisibilityChecker<?> defaultVisibilityChecker = super.getDefaultVisibilityChecker();
        VisibilityChecker<?> visibilityChecker = defaultVisibilityChecker;
        if (!isEnabled(Feature.AUTO_DETECT_GETTERS)) {
            visibilityChecker = defaultVisibilityChecker.mo4222withGetterVisibility(JsonAutoDetect.Visibility.NONE);
        }
        VisibilityChecker<?> visibilityChecker2 = visibilityChecker;
        if (!isEnabled(Feature.AUTO_DETECT_IS_GETTERS)) {
            visibilityChecker2 = visibilityChecker.mo4223withIsGetterVisibility(JsonAutoDetect.Visibility.NONE);
        }
        return !isEnabled(Feature.AUTO_DETECT_FIELDS) ? visibilityChecker2.mo4221withFieldVisibility(JsonAutoDetect.Visibility.NONE) : visibilityChecker2;
    }

    public FilterProvider getFilterProvider() {
        return this._filterProvider;
    }

    public JsonSerialize.Inclusion getSerializationInclusion() {
        JsonSerialize.Inclusion inclusion = this._serializationInclusion;
        return inclusion != null ? inclusion : isEnabled(Feature.WRITE_NULL_PROPERTIES) ? JsonSerialize.Inclusion.ALWAYS : JsonSerialize.Inclusion.NON_NULL;
    }

    public Class<?> getSerializationView() {
        return this._serializationView;
    }

    public <T extends BeanDescription> T introspect(JavaType javaType) {
        return (T) getClassIntrospector().forSerialization(this, javaType, this);
    }

    @Override // com.amazon.org.codehaus.jackson.map.MapperConfig
    public <T extends BeanDescription> T introspectClassAnnotations(JavaType javaType) {
        return (T) getClassIntrospector().forClassAnnotations(this, javaType, this);
    }

    @Override // com.amazon.org.codehaus.jackson.map.MapperConfig
    public <T extends BeanDescription> T introspectDirectClassAnnotations(JavaType javaType) {
        return (T) getClassIntrospector().forDirectClassAnnotations(this, javaType, this);
    }

    @Override // com.amazon.org.codehaus.jackson.map.MapperConfig
    public boolean isAnnotationProcessingEnabled() {
        return isEnabled(Feature.USE_ANNOTATIONS);
    }

    @Override // com.amazon.org.codehaus.jackson.map.MapperConfig.Impl, com.amazon.org.codehaus.jackson.map.MapperConfig
    public /* bridge */ /* synthetic */ boolean isEnabled(MapperConfig.ConfigFeature configFeature) {
        return super.isEnabled(configFeature);
    }

    public JsonSerializer<Object> serializerInstance(Annotated annotated, Class<? extends JsonSerializer<?>> cls) {
        JsonSerializer<?> serializerInstance;
        HandlerInstantiator handlerInstantiator = getHandlerInstantiator();
        return (handlerInstantiator == null || (serializerInstance = handlerInstantiator.serializerInstance(this, annotated, cls)) == null) ? (JsonSerializer) ClassUtil.createInstance(cls, canOverrideAccessModifiers()) : serializerInstance;
    }

    @Override // com.amazon.org.codehaus.jackson.map.MapperConfig
    @Deprecated
    public final void setDateFormat(DateFormat dateFormat) {
        super.setDateFormat(dateFormat);
        set(Feature.WRITE_DATES_AS_TIMESTAMPS, dateFormat == null);
    }

    @Deprecated
    public void setSerializationInclusion(JsonSerialize.Inclusion inclusion) {
        this._serializationInclusion = inclusion;
        if (inclusion == JsonSerialize.Inclusion.NON_NULL) {
            disable(Feature.WRITE_NULL_PROPERTIES);
        } else {
            enable(Feature.WRITE_NULL_PROPERTIES);
        }
    }

    @Deprecated
    public void setSerializationView(Class<?> cls) {
        this._serializationView = cls;
    }

    @Override // com.amazon.org.codehaus.jackson.map.MapperConfig
    public boolean shouldSortPropertiesAlphabetically() {
        return isEnabled(Feature.SORT_PROPERTIES_ALPHABETICALLY);
    }

    public String toString() {
        return GeneratedOutlineSupport1.outline33(this._featureFlags, GeneratedOutlineSupport1.outline107("[SerializationConfig: flags=0x"), "]");
    }

    @Override // com.amazon.org.codehaus.jackson.map.MapperConfig
    /* renamed from: withClassIntrospector */
    public /* bridge */ /* synthetic */ MapperConfig mo4121withClassIntrospector(ClassIntrospector classIntrospector) {
        return mo4121withClassIntrospector((ClassIntrospector<? extends BeanDescription>) classIntrospector);
    }

    public SerializationConfig withFilters(FilterProvider filterProvider) {
        return new SerializationConfig(this, filterProvider);
    }

    public SerializationConfig withSerializationInclusion(JsonSerialize.Inclusion inclusion) {
        return new SerializationConfig(this, inclusion);
    }

    @Override // com.amazon.org.codehaus.jackson.map.MapperConfig
    /* renamed from: withTypeResolverBuilder */
    public /* bridge */ /* synthetic */ MapperConfig mo4128withTypeResolverBuilder(TypeResolverBuilder typeResolverBuilder) {
        return mo4128withTypeResolverBuilder((TypeResolverBuilder<?>) typeResolverBuilder);
    }

    public SerializationConfig withView(Class<?> cls) {
        return new SerializationConfig(this, cls);
    }

    @Override // com.amazon.org.codehaus.jackson.map.MapperConfig
    /* renamed from: withVisibilityChecker */
    public /* bridge */ /* synthetic */ MapperConfig mo4130withVisibilityChecker(VisibilityChecker visibilityChecker) {
        return mo4130withVisibilityChecker((VisibilityChecker<?>) visibilityChecker);
    }

    @Override // com.amazon.org.codehaus.jackson.map.MapperConfig
    /* renamed from: createUnshared  reason: collision with other method in class */
    public SerializationConfig mo4118createUnshared(SubtypeResolver subtypeResolver) {
        HashMap<ClassKey, Class<?>> hashMap = this._mixInAnnotations;
        this._mixInAnnotationsShared = true;
        return new SerializationConfig(this, hashMap, subtypeResolver);
    }

    @Override // com.amazon.org.codehaus.jackson.map.MapperConfig.Impl
    @Deprecated
    public void disable(Feature feature) {
        super.disable((SerializationConfig) feature);
    }

    @Override // com.amazon.org.codehaus.jackson.map.MapperConfig.Impl
    @Deprecated
    public void enable(Feature feature) {
        super.enable((SerializationConfig) feature);
    }

    public boolean isEnabled(Feature feature) {
        return (feature.getMask() & this._featureFlags) != 0;
    }

    @Override // com.amazon.org.codehaus.jackson.map.MapperConfig.Impl
    @Deprecated
    public void set(Feature feature, boolean z) {
        super.set((SerializationConfig) feature, z);
    }

    @Override // com.amazon.org.codehaus.jackson.map.MapperConfig.Impl
    public SerializationConfig with(Feature... featureArr) {
        int i = this._featureFlags;
        for (Feature feature : featureArr) {
            i |= feature.getMask();
        }
        return new SerializationConfig(this, i);
    }

    @Override // com.amazon.org.codehaus.jackson.map.MapperConfig
    /* renamed from: withAnnotationIntrospector  reason: collision with other method in class */
    public SerializationConfig mo4119withAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
        return new SerializationConfig(this, this._base.withAnnotationIntrospector(annotationIntrospector));
    }

    @Override // com.amazon.org.codehaus.jackson.map.MapperConfig
    /* renamed from: withAppendedAnnotationIntrospector  reason: collision with other method in class */
    public SerializationConfig mo4120withAppendedAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
        return new SerializationConfig(this, this._base.withAppendedAnnotationIntrospector(annotationIntrospector));
    }

    @Override // com.amazon.org.codehaus.jackson.map.MapperConfig
    /* renamed from: withClassIntrospector  reason: collision with other method in class */
    public SerializationConfig mo4121withClassIntrospector(ClassIntrospector<? extends BeanDescription> classIntrospector) {
        return new SerializationConfig(this, this._base.withClassIntrospector(classIntrospector));
    }

    @Override // com.amazon.org.codehaus.jackson.map.MapperConfig
    /* renamed from: withDateFormat  reason: collision with other method in class */
    public SerializationConfig mo4122withDateFormat(DateFormat dateFormat) {
        SerializationConfig serializationConfig = new SerializationConfig(this, this._base.withDateFormat(dateFormat));
        return dateFormat == null ? serializationConfig.with(Feature.WRITE_DATES_AS_TIMESTAMPS) : serializationConfig.without(Feature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Override // com.amazon.org.codehaus.jackson.map.MapperConfig
    /* renamed from: withHandlerInstantiator  reason: collision with other method in class */
    public SerializationConfig mo4123withHandlerInstantiator(HandlerInstantiator handlerInstantiator) {
        return new SerializationConfig(this, this._base.withHandlerInstantiator(handlerInstantiator));
    }

    @Override // com.amazon.org.codehaus.jackson.map.MapperConfig
    /* renamed from: withInsertedAnnotationIntrospector  reason: collision with other method in class */
    public SerializationConfig mo4124withInsertedAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
        return new SerializationConfig(this, this._base.withInsertedAnnotationIntrospector(annotationIntrospector));
    }

    @Override // com.amazon.org.codehaus.jackson.map.MapperConfig
    /* renamed from: withPropertyNamingStrategy  reason: collision with other method in class */
    public SerializationConfig mo4125withPropertyNamingStrategy(PropertyNamingStrategy propertyNamingStrategy) {
        return new SerializationConfig(this, this._base.withPropertyNamingStrategy(propertyNamingStrategy));
    }

    @Override // com.amazon.org.codehaus.jackson.map.MapperConfig
    /* renamed from: withSubtypeResolver  reason: collision with other method in class */
    public SerializationConfig mo4126withSubtypeResolver(SubtypeResolver subtypeResolver) {
        SerializationConfig serializationConfig = new SerializationConfig(this);
        serializationConfig._subtypeResolver = subtypeResolver;
        return serializationConfig;
    }

    @Override // com.amazon.org.codehaus.jackson.map.MapperConfig
    /* renamed from: withTypeFactory  reason: collision with other method in class */
    public SerializationConfig mo4127withTypeFactory(TypeFactory typeFactory) {
        return new SerializationConfig(this, this._base.withTypeFactory(typeFactory));
    }

    @Override // com.amazon.org.codehaus.jackson.map.MapperConfig
    /* renamed from: withTypeResolverBuilder  reason: collision with other method in class */
    public SerializationConfig mo4128withTypeResolverBuilder(TypeResolverBuilder<?> typeResolverBuilder) {
        return new SerializationConfig(this, this._base.withTypeResolverBuilder(typeResolverBuilder));
    }

    @Override // com.amazon.org.codehaus.jackson.map.MapperConfig
    /* renamed from: withVisibility  reason: collision with other method in class */
    public SerializationConfig mo4129withVisibility(JsonMethod jsonMethod, JsonAutoDetect.Visibility visibility) {
        return new SerializationConfig(this, this._base.withVisibility(jsonMethod, visibility));
    }

    @Override // com.amazon.org.codehaus.jackson.map.MapperConfig
    /* renamed from: withVisibilityChecker  reason: collision with other method in class */
    public SerializationConfig mo4130withVisibilityChecker(VisibilityChecker<?> visibilityChecker) {
        return new SerializationConfig(this, this._base.withVisibilityChecker(visibilityChecker));
    }

    @Override // com.amazon.org.codehaus.jackson.map.MapperConfig.Impl
    public SerializationConfig without(Feature... featureArr) {
        int i = this._featureFlags;
        for (Feature feature : featureArr) {
            i &= ~feature.getMask();
        }
        return new SerializationConfig(this, i);
    }

    protected SerializationConfig(SerializationConfig serializationConfig) {
        this(serializationConfig, serializationConfig._base);
    }

    protected SerializationConfig(SerializationConfig serializationConfig, HashMap<ClassKey, Class<?>> hashMap, SubtypeResolver subtypeResolver) {
        this(serializationConfig, serializationConfig._base);
        this._mixInAnnotations = hashMap;
        this._subtypeResolver = subtypeResolver;
    }

    protected SerializationConfig(SerializationConfig serializationConfig, MapperConfig.Base base) {
        super(serializationConfig, base, serializationConfig._subtypeResolver);
        this._serializationInclusion = null;
        this._serializationInclusion = serializationConfig._serializationInclusion;
        this._serializationView = serializationConfig._serializationView;
        this._filterProvider = serializationConfig._filterProvider;
    }

    protected SerializationConfig(SerializationConfig serializationConfig, FilterProvider filterProvider) {
        super(serializationConfig);
        this._serializationInclusion = null;
        this._serializationInclusion = serializationConfig._serializationInclusion;
        this._serializationView = serializationConfig._serializationView;
        this._filterProvider = filterProvider;
    }

    protected SerializationConfig(SerializationConfig serializationConfig, Class<?> cls) {
        super(serializationConfig);
        this._serializationInclusion = null;
        this._serializationInclusion = serializationConfig._serializationInclusion;
        this._serializationView = cls;
        this._filterProvider = serializationConfig._filterProvider;
    }

    protected SerializationConfig(SerializationConfig serializationConfig, JsonSerialize.Inclusion inclusion) {
        super(serializationConfig);
        this._serializationInclusion = null;
        this._serializationInclusion = inclusion;
        if (inclusion == JsonSerialize.Inclusion.NON_NULL) {
            this._featureFlags &= ~Feature.WRITE_NULL_PROPERTIES.getMask();
        } else {
            this._featureFlags |= Feature.WRITE_NULL_PROPERTIES.getMask();
        }
        this._serializationView = serializationConfig._serializationView;
        this._filterProvider = serializationConfig._filterProvider;
    }

    protected SerializationConfig(SerializationConfig serializationConfig, int i) {
        super(serializationConfig, i);
        this._serializationInclusion = null;
        this._serializationInclusion = serializationConfig._serializationInclusion;
        this._serializationView = serializationConfig._serializationView;
        this._filterProvider = serializationConfig._filterProvider;
    }
}
