package com.fasterxml.jackson.databind.deser;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.deser.impl.ReadableObjectId;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes2.dex */
public abstract class DefaultDeserializationContext extends DeserializationContext implements Serializable {
    private static final long serialVersionUID = 1;
    private List<ObjectIdResolver> _objectIdResolvers;
    protected transient LinkedHashMap<ObjectIdGenerator.IdKey, ReadableObjectId> _objectIds;

    /* loaded from: classes2.dex */
    public static final class Impl extends DefaultDeserializationContext {
        private static final long serialVersionUID = 1;

        public Impl(DeserializerFactory deserializerFactory) {
            super(deserializerFactory, (DeserializerCache) null);
        }

        @Override // com.fasterxml.jackson.databind.deser.DefaultDeserializationContext
        public DefaultDeserializationContext copy() {
            ClassUtil.verifyMustOverride(Impl.class, this, "copy");
            return new Impl(this);
        }

        @Override // com.fasterxml.jackson.databind.deser.DefaultDeserializationContext
        public DefaultDeserializationContext createDummyInstance(DeserializationConfig deserializationConfig) {
            return new Impl(this, deserializationConfig);
        }

        @Override // com.fasterxml.jackson.databind.deser.DefaultDeserializationContext
        public DefaultDeserializationContext createInstance(DeserializationConfig deserializationConfig, JsonParser jsonParser, InjectableValues injectableValues) {
            return new Impl(this, deserializationConfig, jsonParser, injectableValues);
        }

        @Override // com.fasterxml.jackson.databind.deser.DefaultDeserializationContext
        public DefaultDeserializationContext with(DeserializerFactory deserializerFactory) {
            return new Impl(this, deserializerFactory);
        }

        private Impl(Impl impl, DeserializationConfig deserializationConfig, JsonParser jsonParser, InjectableValues injectableValues) {
            super(impl, deserializationConfig, jsonParser, injectableValues);
        }

        private Impl(Impl impl) {
            super(impl);
        }

        private Impl(Impl impl, DeserializerFactory deserializerFactory) {
            super(impl, deserializerFactory);
        }

        private Impl(Impl impl, DeserializationConfig deserializationConfig) {
            super(impl, deserializationConfig);
        }
    }

    protected DefaultDeserializationContext(DeserializerFactory deserializerFactory, DeserializerCache deserializerCache) {
        super(deserializerFactory, deserializerCache);
    }

    protected Object _unwrapAndDeserialize(JsonParser jsonParser, JavaType javaType, JsonDeserializer<Object> jsonDeserializer, Object obj) throws IOException {
        Object deserialize;
        String simpleName = this._config.findRootName(javaType).getSimpleName();
        JsonToken currentToken = jsonParser.currentToken();
        JsonToken jsonToken = JsonToken.START_OBJECT;
        if (currentToken != jsonToken) {
            reportWrongTokenException(javaType, jsonToken, "Current token not START_OBJECT (needed to unwrap root name %s), but %s", ClassUtil.name(simpleName), jsonParser.currentToken());
        }
        JsonToken nextToken = jsonParser.nextToken();
        JsonToken jsonToken2 = JsonToken.FIELD_NAME;
        if (nextToken != jsonToken2) {
            reportWrongTokenException(javaType, jsonToken2, "Current token not FIELD_NAME (to contain expected root name %s), but %s", ClassUtil.name(simpleName), jsonParser.currentToken());
        }
        String currentName = jsonParser.currentName();
        if (!simpleName.equals(currentName)) {
            reportPropertyInputMismatch(javaType, currentName, "Root name (%s) does not match expected (%s) for type %s", ClassUtil.name(currentName), ClassUtil.name(simpleName), ClassUtil.getTypeDescription(javaType));
        }
        jsonParser.nextToken();
        if (obj == null) {
            deserialize = jsonDeserializer.mo7111deserialize(jsonParser, this);
        } else {
            deserialize = jsonDeserializer.deserialize(jsonParser, this, obj);
        }
        JsonToken nextToken2 = jsonParser.nextToken();
        JsonToken jsonToken3 = JsonToken.END_OBJECT;
        if (nextToken2 != jsonToken3) {
            reportWrongTokenException(javaType, jsonToken3, "Current token not END_OBJECT (to match wrapper object with root name %s), but %s", ClassUtil.name(simpleName), jsonParser.currentToken());
        }
        return deserialize;
    }

    @Override // com.fasterxml.jackson.databind.DeserializationContext
    public void checkUnresolvedObjectId() throws UnresolvedForwardReference {
        if (this._objectIds != null && isEnabled(DeserializationFeature.FAIL_ON_UNRESOLVED_OBJECT_IDS)) {
            UnresolvedForwardReference unresolvedForwardReference = null;
            for (Map.Entry<ObjectIdGenerator.IdKey, ReadableObjectId> entry : this._objectIds.entrySet()) {
                ReadableObjectId value = entry.getValue();
                if (value.hasReferringProperties() && !tryToResolveUnresolvedObjectId(value)) {
                    if (unresolvedForwardReference == null) {
                        unresolvedForwardReference = new UnresolvedForwardReference(getParser(), "Unresolved forward references for: ");
                    }
                    Object obj = value.getKey().key;
                    Iterator<ReadableObjectId.Referring> referringProperties = value.referringProperties();
                    while (referringProperties.hasNext()) {
                        ReadableObjectId.Referring next = referringProperties.next();
                        unresolvedForwardReference.addUnresolvedId(obj, next.getBeanType(), next.getLocation());
                    }
                }
            }
            if (unresolvedForwardReference != null) {
                throw unresolvedForwardReference;
            }
        }
    }

    public DefaultDeserializationContext copy() {
        throw new IllegalStateException("DefaultDeserializationContext sub-class not overriding copy()");
    }

    public abstract DefaultDeserializationContext createDummyInstance(DeserializationConfig deserializationConfig);

    public abstract DefaultDeserializationContext createInstance(DeserializationConfig deserializationConfig, JsonParser jsonParser, InjectableValues injectableValues);

    protected ReadableObjectId createReadableObjectId(ObjectIdGenerator.IdKey idKey) {
        return new ReadableObjectId(idKey);
    }

    @Override // com.fasterxml.jackson.databind.DeserializationContext
    public JsonDeserializer<Object> deserializerInstance(Annotated annotated, Object obj) throws JsonMappingException {
        JsonDeserializer<?> jsonDeserializer;
        JsonDeserializer<?> jsonDeserializer2 = null;
        if (obj == null) {
            return null;
        }
        if (obj instanceof JsonDeserializer) {
            jsonDeserializer = (JsonDeserializer) obj;
        } else if (obj instanceof Class) {
            Class<?> cls = (Class) obj;
            if (cls == JsonDeserializer.None.class || ClassUtil.isBogusClass(cls)) {
                return null;
            }
            if (JsonDeserializer.class.isAssignableFrom(cls)) {
                HandlerInstantiator handlerInstantiator = this._config.getHandlerInstantiator();
                if (handlerInstantiator != null) {
                    jsonDeserializer2 = handlerInstantiator.deserializerInstance(this._config, annotated, cls);
                }
                jsonDeserializer = jsonDeserializer2 == null ? (JsonDeserializer) ClassUtil.createInstance(cls, this._config.canOverrideAccessModifiers()) : jsonDeserializer2;
            } else {
                throw new IllegalStateException(GeneratedOutlineSupport1.outline40(cls, GeneratedOutlineSupport1.outline107("AnnotationIntrospector returned Class "), "; expected Class<JsonDeserializer>"));
            }
        } else {
            throw new IllegalStateException(GeneratedOutlineSupport1.outline46(obj, GeneratedOutlineSupport1.outline107("AnnotationIntrospector returned deserializer definition of type "), "; expected type JsonDeserializer or Class<JsonDeserializer> instead"));
        }
        if (jsonDeserializer instanceof ResolvableDeserializer) {
            ((ResolvableDeserializer) jsonDeserializer).resolve(this);
        }
        return jsonDeserializer;
    }

    @Override // com.fasterxml.jackson.databind.DeserializationContext
    public ReadableObjectId findObjectId(Object obj, ObjectIdGenerator<?> objectIdGenerator, ObjectIdResolver objectIdResolver) {
        ObjectIdResolver objectIdResolver2 = null;
        if (obj == null) {
            return null;
        }
        ObjectIdGenerator.IdKey key = objectIdGenerator.key(obj);
        LinkedHashMap<ObjectIdGenerator.IdKey, ReadableObjectId> linkedHashMap = this._objectIds;
        if (linkedHashMap == null) {
            this._objectIds = new LinkedHashMap<>();
        } else {
            ReadableObjectId readableObjectId = linkedHashMap.get(key);
            if (readableObjectId != null) {
                return readableObjectId;
            }
        }
        List<ObjectIdResolver> list = this._objectIdResolvers;
        if (list == null) {
            this._objectIdResolvers = new ArrayList(8);
        } else {
            Iterator<ObjectIdResolver> it2 = list.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                ObjectIdResolver next = it2.next();
                if (next.canUseFor(objectIdResolver)) {
                    objectIdResolver2 = next;
                    break;
                }
            }
        }
        if (objectIdResolver2 == null) {
            objectIdResolver2 = objectIdResolver.newForDeserialization(this);
            this._objectIdResolvers.add(objectIdResolver2);
        }
        ReadableObjectId createReadableObjectId = createReadableObjectId(key);
        createReadableObjectId.setResolver(objectIdResolver2);
        this._objectIds.put(key, createReadableObjectId);
        return createReadableObjectId;
    }

    @Override // com.fasterxml.jackson.databind.DeserializationContext
    public final KeyDeserializer keyDeserializerInstance(Annotated annotated, Object obj) throws JsonMappingException {
        KeyDeserializer keyDeserializer;
        KeyDeserializer keyDeserializer2 = null;
        if (obj == null) {
            return null;
        }
        if (obj instanceof KeyDeserializer) {
            keyDeserializer = (KeyDeserializer) obj;
        } else if (obj instanceof Class) {
            Class<?> cls = (Class) obj;
            if (cls == KeyDeserializer.None.class || ClassUtil.isBogusClass(cls)) {
                return null;
            }
            if (KeyDeserializer.class.isAssignableFrom(cls)) {
                HandlerInstantiator handlerInstantiator = this._config.getHandlerInstantiator();
                if (handlerInstantiator != null) {
                    keyDeserializer2 = handlerInstantiator.keyDeserializerInstance(this._config, annotated, cls);
                }
                keyDeserializer = keyDeserializer2 == null ? (KeyDeserializer) ClassUtil.createInstance(cls, this._config.canOverrideAccessModifiers()) : keyDeserializer2;
            } else {
                throw new IllegalStateException(GeneratedOutlineSupport1.outline40(cls, GeneratedOutlineSupport1.outline107("AnnotationIntrospector returned Class "), "; expected Class<KeyDeserializer>"));
            }
        } else {
            throw new IllegalStateException(GeneratedOutlineSupport1.outline46(obj, GeneratedOutlineSupport1.outline107("AnnotationIntrospector returned key deserializer definition of type "), "; expected type KeyDeserializer or Class<KeyDeserializer> instead"));
        }
        if (keyDeserializer instanceof ResolvableDeserializer) {
            ((ResolvableDeserializer) keyDeserializer).resolve(this);
        }
        return keyDeserializer;
    }

    public Object readRootValue(JsonParser jsonParser, JavaType javaType, JsonDeserializer<Object> jsonDeserializer, Object obj) throws IOException {
        if (this._config.useRootWrapping()) {
            return _unwrapAndDeserialize(jsonParser, javaType, jsonDeserializer, obj);
        }
        if (obj == null) {
            return jsonDeserializer.mo7111deserialize(jsonParser, this);
        }
        return jsonDeserializer.deserialize(jsonParser, this, obj);
    }

    protected boolean tryToResolveUnresolvedObjectId(ReadableObjectId readableObjectId) {
        return readableObjectId.tryToResolveUnresolved(this);
    }

    public abstract DefaultDeserializationContext with(DeserializerFactory deserializerFactory);

    protected DefaultDeserializationContext(DefaultDeserializationContext defaultDeserializationContext, DeserializationConfig deserializationConfig, JsonParser jsonParser, InjectableValues injectableValues) {
        super(defaultDeserializationContext, deserializationConfig, jsonParser, injectableValues);
    }

    protected DefaultDeserializationContext(DefaultDeserializationContext defaultDeserializationContext, DeserializationConfig deserializationConfig) {
        super(defaultDeserializationContext, deserializationConfig);
    }

    protected DefaultDeserializationContext(DefaultDeserializationContext defaultDeserializationContext, DeserializerFactory deserializerFactory) {
        super(defaultDeserializationContext, deserializerFactory);
    }

    protected DefaultDeserializationContext(DefaultDeserializationContext defaultDeserializationContext) {
        super(defaultDeserializationContext);
    }
}
