package com.amazon.org.codehaus.jackson.map.jsontype.impl;

import com.amazon.org.codehaus.jackson.annotate.JsonTypeInfo;
import com.amazon.org.codehaus.jackson.map.BeanProperty;
import com.amazon.org.codehaus.jackson.map.DeserializationConfig;
import com.amazon.org.codehaus.jackson.map.MapperConfig;
import com.amazon.org.codehaus.jackson.map.SerializationConfig;
import com.amazon.org.codehaus.jackson.map.TypeDeserializer;
import com.amazon.org.codehaus.jackson.map.TypeSerializer;
import com.amazon.org.codehaus.jackson.map.jsontype.NamedType;
import com.amazon.org.codehaus.jackson.map.jsontype.TypeIdResolver;
import com.amazon.org.codehaus.jackson.map.jsontype.TypeResolverBuilder;
import com.amazon.org.codehaus.jackson.type.JavaType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collection;
/* loaded from: classes13.dex */
public class StdTypeResolverBuilder implements TypeResolverBuilder<StdTypeResolverBuilder> {
    protected TypeIdResolver _customIdResolver;
    protected Class<?> _defaultImpl;
    protected JsonTypeInfo.Id _idType;
    protected JsonTypeInfo.As _includeAs;
    protected String _typeProperty;

    /* renamed from: com.amazon.org.codehaus.jackson.map.jsontype.impl.StdTypeResolverBuilder$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$org$codehaus$jackson$annotate$JsonTypeInfo$As;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$org$codehaus$jackson$annotate$JsonTypeInfo$Id = new int[JsonTypeInfo.Id.values().length];

        static {
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$annotate$JsonTypeInfo$Id[JsonTypeInfo.Id.CLASS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$annotate$JsonTypeInfo$Id[JsonTypeInfo.Id.MINIMAL_CLASS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$annotate$JsonTypeInfo$Id[JsonTypeInfo.Id.NAME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$annotate$JsonTypeInfo$Id[JsonTypeInfo.Id.NONE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$annotate$JsonTypeInfo$Id[JsonTypeInfo.Id.CUSTOM.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            $SwitchMap$com$amazon$org$codehaus$jackson$annotate$JsonTypeInfo$As = new int[JsonTypeInfo.As.values().length];
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$annotate$JsonTypeInfo$As[JsonTypeInfo.As.WRAPPER_ARRAY.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$annotate$JsonTypeInfo$As[JsonTypeInfo.As.PROPERTY.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$annotate$JsonTypeInfo$As[JsonTypeInfo.As.WRAPPER_OBJECT.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$annotate$JsonTypeInfo$As[JsonTypeInfo.As.EXTERNAL_PROPERTY.ordinal()] = 4;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    public static StdTypeResolverBuilder noTypeInfoBuilder() {
        return new StdTypeResolverBuilder().init(JsonTypeInfo.Id.NONE, (TypeIdResolver) null);
    }

    @Override // com.amazon.org.codehaus.jackson.map.jsontype.TypeResolverBuilder
    public TypeDeserializer buildTypeDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, Collection<NamedType> collection, BeanProperty beanProperty) {
        if (this._idType == JsonTypeInfo.Id.NONE) {
            return null;
        }
        TypeIdResolver idResolver = idResolver(deserializationConfig, javaType, collection, false, true);
        int ordinal = this._includeAs.ordinal();
        if (ordinal == 0) {
            return new AsPropertyTypeDeserializer(javaType, idResolver, beanProperty, this._defaultImpl, this._typeProperty);
        }
        if (ordinal == 1) {
            return new AsWrapperTypeDeserializer(javaType, idResolver, beanProperty, this._defaultImpl);
        }
        if (ordinal == 2) {
            return new AsArrayTypeDeserializer(javaType, idResolver, beanProperty, this._defaultImpl);
        }
        if (ordinal == 3) {
            return new AsExternalTypeDeserializer(javaType, idResolver, beanProperty, this._defaultImpl, this._typeProperty);
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Do not know how to construct standard type serializer for inclusion type: ");
        outline107.append(this._includeAs);
        throw new IllegalStateException(outline107.toString());
    }

    @Override // com.amazon.org.codehaus.jackson.map.jsontype.TypeResolverBuilder
    public TypeSerializer buildTypeSerializer(SerializationConfig serializationConfig, JavaType javaType, Collection<NamedType> collection, BeanProperty beanProperty) {
        if (this._idType == JsonTypeInfo.Id.NONE) {
            return null;
        }
        TypeIdResolver idResolver = idResolver(serializationConfig, javaType, collection, true, false);
        int ordinal = this._includeAs.ordinal();
        if (ordinal == 0) {
            return new AsPropertyTypeSerializer(idResolver, beanProperty, this._typeProperty);
        }
        if (ordinal == 1) {
            return new AsWrapperTypeSerializer(idResolver, beanProperty);
        }
        if (ordinal == 2) {
            return new AsArrayTypeSerializer(idResolver, beanProperty);
        }
        if (ordinal == 3) {
            return new AsExternalTypeSerializer(idResolver, beanProperty, this._typeProperty);
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Do not know how to construct standard type serializer for inclusion type: ");
        outline107.append(this._includeAs);
        throw new IllegalStateException(outline107.toString());
    }

    @Override // com.amazon.org.codehaus.jackson.map.jsontype.TypeResolverBuilder
    public /* bridge */ /* synthetic */ StdTypeResolverBuilder defaultImpl(Class cls) {
        return defaultImpl2((Class<?>) cls);
    }

    @Override // com.amazon.org.codehaus.jackson.map.jsontype.TypeResolverBuilder
    public Class<?> getDefaultImpl() {
        return this._defaultImpl;
    }

    public String getTypeProperty() {
        return this._typeProperty;
    }

    protected TypeIdResolver idResolver(MapperConfig<?> mapperConfig, JavaType javaType, Collection<NamedType> collection, boolean z, boolean z2) {
        TypeIdResolver typeIdResolver = this._customIdResolver;
        if (typeIdResolver != null) {
            return typeIdResolver;
        }
        JsonTypeInfo.Id id = this._idType;
        if (id != null) {
            int ordinal = id.ordinal();
            if (ordinal == 0) {
                return null;
            }
            if (ordinal == 1) {
                return new ClassNameIdResolver(javaType, mapperConfig.getTypeFactory());
            }
            if (ordinal == 2) {
                return new MinimalClassNameIdResolver(javaType, mapperConfig.getTypeFactory());
            }
            if (ordinal == 3) {
                return TypeNameIdResolver.construct(mapperConfig, javaType, collection, z, z2);
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Do not know how to construct standard type id resolver for idType: ");
            outline107.append(this._idType);
            throw new IllegalStateException(outline107.toString());
        }
        throw new IllegalStateException("Can not build, 'init()' not yet called");
    }

    @Override // com.amazon.org.codehaus.jackson.map.jsontype.TypeResolverBuilder
    /* renamed from: defaultImpl  reason: avoid collision after fix types in other method */
    public StdTypeResolverBuilder defaultImpl2(Class<?> cls) {
        this._defaultImpl = cls;
        return this;
    }

    @Override // com.amazon.org.codehaus.jackson.map.jsontype.TypeResolverBuilder
    public StdTypeResolverBuilder inclusion(JsonTypeInfo.As as) {
        if (as != null) {
            this._includeAs = as;
            return this;
        }
        throw new IllegalArgumentException("includeAs can not be null");
    }

    @Override // com.amazon.org.codehaus.jackson.map.jsontype.TypeResolverBuilder
    public StdTypeResolverBuilder init(JsonTypeInfo.Id id, TypeIdResolver typeIdResolver) {
        if (id != null) {
            this._idType = id;
            this._customIdResolver = typeIdResolver;
            this._typeProperty = id.getDefaultPropertyName();
            return this;
        }
        throw new IllegalArgumentException("idType can not be null");
    }

    @Override // com.amazon.org.codehaus.jackson.map.jsontype.TypeResolverBuilder
    public StdTypeResolverBuilder typeProperty(String str) {
        if (str == null || str.length() == 0) {
            str = this._idType.getDefaultPropertyName();
        }
        this._typeProperty = str;
        return this;
    }
}
