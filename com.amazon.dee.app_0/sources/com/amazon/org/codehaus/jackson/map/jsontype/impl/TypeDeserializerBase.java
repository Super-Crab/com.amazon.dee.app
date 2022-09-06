package com.amazon.org.codehaus.jackson.map.jsontype.impl;

import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.annotate.JsonTypeInfo;
import com.amazon.org.codehaus.jackson.map.BeanProperty;
import com.amazon.org.codehaus.jackson.map.DeserializationContext;
import com.amazon.org.codehaus.jackson.map.JsonDeserializer;
import com.amazon.org.codehaus.jackson.map.TypeDeserializer;
import com.amazon.org.codehaus.jackson.map.jsontype.TypeIdResolver;
import com.amazon.org.codehaus.jackson.type.JavaType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.util.HashMap;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes13.dex */
public abstract class TypeDeserializerBase extends TypeDeserializer {
    protected final JavaType _baseType;
    protected final JavaType _defaultImpl;
    protected JsonDeserializer<Object> _defaultImplDeserializer;
    protected final HashMap<String, JsonDeserializer<Object>> _deserializers;
    protected final TypeIdResolver _idResolver;
    protected final BeanProperty _property;

    @Deprecated
    protected TypeDeserializerBase(JavaType javaType, TypeIdResolver typeIdResolver, BeanProperty beanProperty) {
        this(javaType, typeIdResolver, beanProperty, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final JsonDeserializer<Object> _findDefaultImplDeserializer(DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonDeserializer<Object> jsonDeserializer;
        JavaType javaType = this._defaultImpl;
        if (javaType == null) {
            return null;
        }
        synchronized (javaType) {
            if (this._defaultImplDeserializer == null) {
                this._defaultImplDeserializer = deserializationContext.getDeserializerProvider().findValueDeserializer(deserializationContext.getConfig(), this._defaultImpl, this._property);
            }
            jsonDeserializer = this._defaultImplDeserializer;
        }
        return jsonDeserializer;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final JsonDeserializer<Object> _findDeserializer(DeserializationContext deserializationContext, String str) throws IOException, JsonProcessingException {
        JsonDeserializer<Object> jsonDeserializer;
        JsonDeserializer<Object> findValueDeserializer;
        synchronized (this._deserializers) {
            jsonDeserializer = this._deserializers.get(str);
            if (jsonDeserializer == null) {
                JavaType typeFromId = this._idResolver.typeFromId(str);
                if (typeFromId == null) {
                    if (this._defaultImpl != null) {
                        findValueDeserializer = _findDefaultImplDeserializer(deserializationContext);
                    } else {
                        throw deserializationContext.unknownTypeException(this._baseType, str);
                    }
                } else {
                    if (this._baseType != null && this._baseType.getClass() == typeFromId.getClass()) {
                        typeFromId = this._baseType.narrowBy(typeFromId.getRawClass());
                    }
                    findValueDeserializer = deserializationContext.getDeserializerProvider().findValueDeserializer(deserializationContext.getConfig(), typeFromId, this._property);
                }
                jsonDeserializer = findValueDeserializer;
                this._deserializers.put(str, jsonDeserializer);
            }
        }
        return jsonDeserializer;
    }

    public String baseTypeName() {
        return this._baseType.getRawClass().getName();
    }

    @Override // com.amazon.org.codehaus.jackson.map.TypeDeserializer
    public Class<?> getDefaultImpl() {
        JavaType javaType = this._defaultImpl;
        if (javaType == null) {
            return null;
        }
        return javaType.getRawClass();
    }

    @Override // com.amazon.org.codehaus.jackson.map.TypeDeserializer
    public String getPropertyName() {
        return null;
    }

    @Override // com.amazon.org.codehaus.jackson.map.TypeDeserializer
    public TypeIdResolver getTypeIdResolver() {
        return this._idResolver;
    }

    @Override // com.amazon.org.codehaus.jackson.map.TypeDeserializer
    public abstract JsonTypeInfo.As getTypeInclusion();

    public String toString() {
        StringBuilder outline104 = GeneratedOutlineSupport1.outline104(JsonReaderKt.BEGIN_LIST);
        outline104.append(getClass().getName());
        outline104.append("; base-type:");
        outline104.append(this._baseType);
        outline104.append("; id-resolver: ");
        outline104.append(this._idResolver);
        outline104.append(JsonReaderKt.END_LIST);
        return outline104.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public TypeDeserializerBase(JavaType javaType, TypeIdResolver typeIdResolver, BeanProperty beanProperty, Class<?> cls) {
        this._baseType = javaType;
        this._idResolver = typeIdResolver;
        this._property = beanProperty;
        this._deserializers = new HashMap<>();
        if (cls == null) {
            this._defaultImpl = null;
        } else {
            this._defaultImpl = javaType.forcedNarrowBy(cls);
        }
    }
}
