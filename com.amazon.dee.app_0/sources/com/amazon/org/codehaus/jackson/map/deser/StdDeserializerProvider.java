package com.amazon.org.codehaus.jackson.map.deser;

import com.amazon.org.codehaus.jackson.JsonNode;
import com.amazon.org.codehaus.jackson.JsonParser;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.io.SerializedString;
import com.amazon.org.codehaus.jackson.map.AbstractTypeResolver;
import com.amazon.org.codehaus.jackson.map.AnnotationIntrospector;
import com.amazon.org.codehaus.jackson.map.BeanProperty;
import com.amazon.org.codehaus.jackson.map.ContextualDeserializer;
import com.amazon.org.codehaus.jackson.map.ContextualKeyDeserializer;
import com.amazon.org.codehaus.jackson.map.DeserializationConfig;
import com.amazon.org.codehaus.jackson.map.DeserializationContext;
import com.amazon.org.codehaus.jackson.map.DeserializerFactory;
import com.amazon.org.codehaus.jackson.map.DeserializerProvider;
import com.amazon.org.codehaus.jackson.map.Deserializers;
import com.amazon.org.codehaus.jackson.map.JsonDeserializer;
import com.amazon.org.codehaus.jackson.map.JsonMappingException;
import com.amazon.org.codehaus.jackson.map.KeyDeserializer;
import com.amazon.org.codehaus.jackson.map.KeyDeserializers;
import com.amazon.org.codehaus.jackson.map.ResolvableDeserializer;
import com.amazon.org.codehaus.jackson.map.TypeDeserializer;
import com.amazon.org.codehaus.jackson.map.introspect.AnnotatedClass;
import com.amazon.org.codehaus.jackson.map.type.ArrayType;
import com.amazon.org.codehaus.jackson.map.type.CollectionLikeType;
import com.amazon.org.codehaus.jackson.map.type.CollectionType;
import com.amazon.org.codehaus.jackson.map.type.MapLikeType;
import com.amazon.org.codehaus.jackson.map.type.MapType;
import com.amazon.org.codehaus.jackson.map.util.ClassUtil;
import com.amazon.org.codehaus.jackson.map.util.RootNameLookup;
import com.amazon.org.codehaus.jackson.type.JavaType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes13.dex */
public class StdDeserializerProvider extends DeserializerProvider {
    protected final ConcurrentHashMap<JavaType, JsonDeserializer<Object>> _cachedDeserializers;
    protected DeserializerFactory _factory;
    protected final HashMap<JavaType, JsonDeserializer<Object>> _incompleteDeserializers;
    protected final RootNameLookup _rootNames;

    /* loaded from: classes13.dex */
    protected static final class WrappedDeserializer extends JsonDeserializer<Object> {
        final JsonDeserializer<Object> _deserializer;
        final TypeDeserializer _typeDeserializer;

        public WrappedDeserializer(TypeDeserializer typeDeserializer, JsonDeserializer<Object> jsonDeserializer) {
            this._typeDeserializer = typeDeserializer;
            this._deserializer = jsonDeserializer;
        }

        @Override // com.amazon.org.codehaus.jackson.map.JsonDeserializer
        /* renamed from: deserialize */
        public Object mo4206deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            return this._deserializer.mo4196deserializeWithType(jsonParser, deserializationContext, this._typeDeserializer);
        }

        @Override // com.amazon.org.codehaus.jackson.map.JsonDeserializer
        /* renamed from: deserializeWithType */
        public Object mo4196deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
            throw new IllegalStateException("Type-wrapped deserializer's deserializeWithType should never get called");
        }
    }

    public StdDeserializerProvider() {
        this(BeanDeserializerFactory.instance);
    }

    protected JsonDeserializer<Object> _createAndCache2(DeserializationConfig deserializationConfig, JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        try {
            JsonDeserializer<Object> _createDeserializer = _createDeserializer(deserializationConfig, javaType, beanProperty);
            if (_createDeserializer == null) {
                return null;
            }
            boolean z = _createDeserializer instanceof ResolvableDeserializer;
            boolean z2 = _createDeserializer.getClass() == BeanDeserializer.class;
            if (!z2 && deserializationConfig.isEnabled(DeserializationConfig.Feature.USE_ANNOTATIONS)) {
                AnnotationIntrospector annotationIntrospector = deserializationConfig.getAnnotationIntrospector();
                Boolean findCachability = annotationIntrospector.findCachability(AnnotatedClass.construct(_createDeserializer.getClass(), annotationIntrospector, null));
                if (findCachability != null) {
                    z2 = findCachability.booleanValue();
                }
            }
            if (z) {
                this._incompleteDeserializers.put(javaType, _createDeserializer);
                _resolveDeserializer(deserializationConfig, (ResolvableDeserializer) _createDeserializer);
                this._incompleteDeserializers.remove(javaType);
            }
            if (z2) {
                this._cachedDeserializers.put(javaType, _createDeserializer);
            }
            return _createDeserializer;
        } catch (IllegalArgumentException e) {
            throw new JsonMappingException(e.getMessage(), null, e);
        }
    }

    protected JsonDeserializer<Object> _createAndCacheValueDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        JsonDeserializer<Object> jsonDeserializer;
        synchronized (this._incompleteDeserializers) {
            JsonDeserializer<Object> _findCachedDeserializer = _findCachedDeserializer(javaType);
            if (_findCachedDeserializer != null) {
                return _findCachedDeserializer;
            }
            int size = this._incompleteDeserializers.size();
            if (size > 0 && (jsonDeserializer = this._incompleteDeserializers.get(javaType)) != null) {
                return jsonDeserializer;
            }
            JsonDeserializer<Object> _createAndCache2 = _createAndCache2(deserializationConfig, javaType, beanProperty);
            if (size == 0 && this._incompleteDeserializers.size() > 0) {
                this._incompleteDeserializers.clear();
            }
            return _createAndCache2;
        }
    }

    protected JsonDeserializer<Object> _createDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        if (javaType.isEnumType()) {
            return this._factory.createEnumDeserializer(deserializationConfig, this, javaType, beanProperty);
        }
        if (javaType.isContainerType()) {
            if (javaType.isArrayType()) {
                return this._factory.createArrayDeserializer(deserializationConfig, this, (ArrayType) javaType, beanProperty);
            }
            if (javaType.isMapLikeType()) {
                MapLikeType mapLikeType = (MapLikeType) javaType;
                if (mapLikeType.isTrueMapType()) {
                    return this._factory.createMapDeserializer(deserializationConfig, this, (MapType) mapLikeType, beanProperty);
                }
                return this._factory.createMapLikeDeserializer(deserializationConfig, this, mapLikeType, beanProperty);
            } else if (javaType.isCollectionLikeType()) {
                CollectionLikeType collectionLikeType = (CollectionLikeType) javaType;
                if (collectionLikeType.isTrueCollectionType()) {
                    return this._factory.createCollectionDeserializer(deserializationConfig, this, (CollectionType) collectionLikeType, beanProperty);
                }
                return this._factory.createCollectionLikeDeserializer(deserializationConfig, this, collectionLikeType, beanProperty);
            }
        }
        if (JsonNode.class.isAssignableFrom(javaType.getRawClass())) {
            return this._factory.createTreeDeserializer(deserializationConfig, this, javaType, beanProperty);
        }
        return this._factory.createBeanDeserializer(deserializationConfig, this, javaType, beanProperty);
    }

    protected JsonDeserializer<Object> _findCachedDeserializer(JavaType javaType) {
        if (javaType != null) {
            return this._cachedDeserializers.get(javaType);
        }
        throw new IllegalArgumentException();
    }

    protected KeyDeserializer _handleUnknownKeyDeserializer(JavaType javaType) throws JsonMappingException {
        throw new JsonMappingException("Can not find a (Map) Key deserializer for type " + javaType);
    }

    protected JsonDeserializer<Object> _handleUnknownValueDeserializer(JavaType javaType) throws JsonMappingException {
        if (!ClassUtil.isConcrete(javaType.getRawClass())) {
            throw new JsonMappingException("Can not find a Value deserializer for abstract type " + javaType);
        }
        throw new JsonMappingException("Can not find a Value deserializer for type " + javaType);
    }

    protected void _resolveDeserializer(DeserializationConfig deserializationConfig, ResolvableDeserializer resolvableDeserializer) throws JsonMappingException {
        resolvableDeserializer.resolve(deserializationConfig, this);
    }

    @Override // com.amazon.org.codehaus.jackson.map.DeserializerProvider
    public int cachedDeserializersCount() {
        return this._cachedDeserializers.size();
    }

    @Override // com.amazon.org.codehaus.jackson.map.DeserializerProvider
    public SerializedString findExpectedRootName(DeserializationConfig deserializationConfig, JavaType javaType) throws JsonMappingException {
        return this._rootNames.findRootName(javaType, deserializationConfig);
    }

    @Override // com.amazon.org.codehaus.jackson.map.DeserializerProvider
    public KeyDeserializer findKeyDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        KeyDeserializer createKeyDeserializer = this._factory.createKeyDeserializer(deserializationConfig, javaType, beanProperty);
        if (createKeyDeserializer instanceof ContextualKeyDeserializer) {
            createKeyDeserializer = ((ContextualKeyDeserializer) createKeyDeserializer).createContextual(deserializationConfig, beanProperty);
        }
        return createKeyDeserializer == null ? _handleUnknownKeyDeserializer(javaType) : createKeyDeserializer;
    }

    @Override // com.amazon.org.codehaus.jackson.map.DeserializerProvider
    public JsonDeserializer<Object> findTypedValueDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        JsonDeserializer<Object> findValueDeserializer = findValueDeserializer(deserializationConfig, javaType, beanProperty);
        TypeDeserializer findTypeDeserializer = this._factory.findTypeDeserializer(deserializationConfig, javaType, beanProperty);
        return findTypeDeserializer != null ? new WrappedDeserializer(findTypeDeserializer, findValueDeserializer) : findValueDeserializer;
    }

    @Override // com.amazon.org.codehaus.jackson.map.DeserializerProvider
    public JsonDeserializer<Object> findValueDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        JsonDeserializer<Object> _findCachedDeserializer = _findCachedDeserializer(javaType);
        if (_findCachedDeserializer != null) {
            return _findCachedDeserializer instanceof ContextualDeserializer ? ((ContextualDeserializer) _findCachedDeserializer).createContextual(deserializationConfig, beanProperty) : _findCachedDeserializer;
        }
        JsonDeserializer<Object> _createAndCacheValueDeserializer = _createAndCacheValueDeserializer(deserializationConfig, javaType, beanProperty);
        if (_createAndCacheValueDeserializer == null) {
            _createAndCacheValueDeserializer = _handleUnknownValueDeserializer(javaType);
        }
        return _createAndCacheValueDeserializer instanceof ContextualDeserializer ? ((ContextualDeserializer) _createAndCacheValueDeserializer).createContextual(deserializationConfig, beanProperty) : _createAndCacheValueDeserializer;
    }

    @Override // com.amazon.org.codehaus.jackson.map.DeserializerProvider
    public void flushCachedDeserializers() {
        this._cachedDeserializers.clear();
    }

    @Override // com.amazon.org.codehaus.jackson.map.DeserializerProvider
    public boolean hasValueDeserializerFor(DeserializationConfig deserializationConfig, JavaType javaType) {
        JsonDeserializer<Object> _findCachedDeserializer = _findCachedDeserializer(javaType);
        if (_findCachedDeserializer == null) {
            try {
                _findCachedDeserializer = _createAndCacheValueDeserializer(deserializationConfig, javaType, null);
            } catch (Exception unused) {
                return false;
            }
        }
        return _findCachedDeserializer != null;
    }

    @Override // com.amazon.org.codehaus.jackson.map.DeserializerProvider
    public JavaType mapAbstractType(DeserializationConfig deserializationConfig, JavaType javaType) throws JsonMappingException {
        return this._factory.mapAbstractType(deserializationConfig, javaType);
    }

    @Override // com.amazon.org.codehaus.jackson.map.DeserializerProvider
    public DeserializerProvider withAbstractTypeResolver(AbstractTypeResolver abstractTypeResolver) {
        return mo4138withFactory(this._factory.withAbstractTypeResolver(abstractTypeResolver));
    }

    @Override // com.amazon.org.codehaus.jackson.map.DeserializerProvider
    public DeserializerProvider withAdditionalDeserializers(Deserializers deserializers) {
        return mo4138withFactory(this._factory.withAdditionalDeserializers(deserializers));
    }

    @Override // com.amazon.org.codehaus.jackson.map.DeserializerProvider
    public DeserializerProvider withAdditionalKeyDeserializers(KeyDeserializers keyDeserializers) {
        return mo4138withFactory(this._factory.withAdditionalKeyDeserializers(keyDeserializers));
    }

    @Override // com.amazon.org.codehaus.jackson.map.DeserializerProvider
    public DeserializerProvider withDeserializerModifier(BeanDeserializerModifier beanDeserializerModifier) {
        return mo4138withFactory(this._factory.withDeserializerModifier(beanDeserializerModifier));
    }

    @Override // com.amazon.org.codehaus.jackson.map.DeserializerProvider
    public DeserializerProvider withValueInstantiators(ValueInstantiators valueInstantiators) {
        return mo4138withFactory(this._factory.withValueInstantiators(valueInstantiators));
    }

    public StdDeserializerProvider(DeserializerFactory deserializerFactory) {
        this._cachedDeserializers = new ConcurrentHashMap<>(64, 0.75f, 2);
        this._incompleteDeserializers = new HashMap<>(8);
        this._factory = deserializerFactory;
        this._rootNames = new RootNameLookup();
    }

    @Override // com.amazon.org.codehaus.jackson.map.DeserializerProvider
    /* renamed from: withFactory  reason: collision with other method in class */
    public StdDeserializerProvider mo4138withFactory(DeserializerFactory deserializerFactory) {
        if (StdDeserializerProvider.class == StdDeserializerProvider.class) {
            return new StdDeserializerProvider(deserializerFactory);
        }
        throw new IllegalStateException(GeneratedOutlineSupport1.outline40(StdDeserializerProvider.class, GeneratedOutlineSupport1.outline107("DeserializerProvider of type "), " does not override 'withFactory()' method"));
    }
}
