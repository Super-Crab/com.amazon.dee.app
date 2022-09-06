package com.fasterxml.jackson.databind.ser.std;

import amazon.speech.simclient.capability.CapabilityQueryConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonMapFormatVisitor;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.IgnorePropertiesUtil;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
@JacksonStdImpl
/* loaded from: classes2.dex */
public class MapSerializer extends ContainerSerializer<Map<?, ?>> implements ContextualSerializer {
    private static final long serialVersionUID = 1;
    protected PropertySerializerMap _dynamicValueSerializers;
    protected final Object _filterId;
    protected final Set<String> _ignoredEntries;
    protected final Set<String> _includedEntries;
    protected final IgnorePropertiesUtil.Checker _inclusionChecker;
    protected JsonSerializer<Object> _keySerializer;
    protected final JavaType _keyType;
    protected final BeanProperty _property;
    protected final boolean _sortKeys;
    protected final boolean _suppressNulls;
    protected final Object _suppressableValue;
    protected JsonSerializer<Object> _valueSerializer;
    protected final JavaType _valueType;
    protected final boolean _valueTypeIsStatic;
    protected final TypeSerializer _valueTypeSerializer;
    protected static final JavaType UNSPECIFIED_TYPE = TypeFactory.unknownType();
    public static final Object MARKER_FOR_EMPTY = JsonInclude.Include.NON_EMPTY;

    /* renamed from: com.fasterxml.jackson.databind.ser.std.MapSerializer$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include = new int[JsonInclude.Include.values().length];

        static {
            try {
                $SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include[JsonInclude.Include.NON_DEFAULT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include[JsonInclude.Include.NON_ABSENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include[JsonInclude.Include.NON_EMPTY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include[JsonInclude.Include.CUSTOM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include[JsonInclude.Include.NON_NULL.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include[JsonInclude.Include.ALWAYS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    protected MapSerializer(Set<String> set, Set<String> set2, JavaType javaType, JavaType javaType2, boolean z, TypeSerializer typeSerializer, JsonSerializer<?> jsonSerializer, JsonSerializer<?> jsonSerializer2) {
        super(Map.class, false);
        this._ignoredEntries = (set == null || set.isEmpty()) ? null : set;
        this._includedEntries = set2;
        this._keyType = javaType;
        this._valueType = javaType2;
        this._valueTypeIsStatic = z;
        this._valueTypeSerializer = typeSerializer;
        this._keySerializer = jsonSerializer;
        this._valueSerializer = jsonSerializer2;
        this._dynamicValueSerializers = PropertySerializerMap.emptyForProperties();
        this._property = null;
        this._filterId = null;
        this._sortKeys = false;
        this._suppressableValue = null;
        this._suppressNulls = false;
        this._inclusionChecker = IgnorePropertiesUtil.buildCheckerIfNeeded(this._ignoredEntries, this._includedEntries);
    }

    private final JsonSerializer<Object> _findSerializer(SerializerProvider serializerProvider, Object obj) throws JsonMappingException {
        Class<?> cls = obj.getClass();
        JsonSerializer<Object> serializerFor = this._dynamicValueSerializers.serializerFor(cls);
        if (serializerFor != null) {
            return serializerFor;
        }
        if (this._valueType.hasGenericTypes()) {
            return _findAndAddDynamic(this._dynamicValueSerializers, serializerProvider.constructSpecializedType(this._valueType, cls), serializerProvider);
        }
        return _findAndAddDynamic(this._dynamicValueSerializers, cls, serializerProvider);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.fasterxml.jackson.databind.ser.std.MapSerializer construct(java.util.Set<java.lang.String> r12, java.util.Set<java.lang.String> r13, com.fasterxml.jackson.databind.JavaType r14, boolean r15, com.fasterxml.jackson.databind.jsontype.TypeSerializer r16, com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object> r17, com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object> r18, java.lang.Object r19) {
        /*
            r0 = r14
            r1 = r19
            if (r0 != 0) goto La
            com.fasterxml.jackson.databind.JavaType r0 = com.fasterxml.jackson.databind.ser.std.MapSerializer.UNSPECIFIED_TYPE
            r6 = r0
            r7 = r6
            goto L21
        La:
            com.fasterxml.jackson.databind.JavaType r2 = r14.mo7229getKeyType()
            java.lang.Class<java.util.Properties> r3 = java.util.Properties.class
            boolean r3 = r14.hasRawClass(r3)
            if (r3 == 0) goto L1b
            com.fasterxml.jackson.databind.JavaType r0 = com.fasterxml.jackson.databind.type.TypeFactory.unknownType()
            goto L1f
        L1b:
            com.fasterxml.jackson.databind.JavaType r0 = r14.mo7243getContentType()
        L1f:
            r7 = r0
            r6 = r2
        L21:
            r0 = 0
            if (r15 != 0) goto L2e
            if (r7 == 0) goto L36
            boolean r2 = r7.isFinal()
            if (r2 == 0) goto L36
            r0 = 1
            goto L36
        L2e:
            java.lang.Class r2 = r7.getRawClass()
            java.lang.Class<java.lang.Object> r3 = java.lang.Object.class
            if (r2 != r3) goto L38
        L36:
            r8 = r0
            goto L39
        L38:
            r8 = r15
        L39:
            com.fasterxml.jackson.databind.ser.std.MapSerializer r0 = new com.fasterxml.jackson.databind.ser.std.MapSerializer
            r3 = r0
            r4 = r12
            r5 = r13
            r9 = r16
            r10 = r17
            r11 = r18
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11)
            if (r1 == 0) goto L4d
            com.fasterxml.jackson.databind.ser.std.MapSerializer r0 = r0.mo7210withFilterId(r1)
        L4d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.std.MapSerializer.construct(java.util.Set, java.util.Set, com.fasterxml.jackson.databind.JavaType, boolean, com.fasterxml.jackson.databind.jsontype.TypeSerializer, com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.JsonSerializer, java.lang.Object):com.fasterxml.jackson.databind.ser.std.MapSerializer");
    }

    protected void _ensureOverride(String str) {
        ClassUtil.verifyMustOverride(MapSerializer.class, this, str);
    }

    protected final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap propertySerializerMap, Class<?> cls, SerializerProvider serializerProvider) throws JsonMappingException {
        PropertySerializerMap.SerializerAndMapResult findAndAddSecondarySerializer = propertySerializerMap.findAndAddSecondarySerializer(cls, serializerProvider, this._property);
        PropertySerializerMap propertySerializerMap2 = findAndAddSecondarySerializer.map;
        if (propertySerializerMap != propertySerializerMap2) {
            this._dynamicValueSerializers = propertySerializerMap2;
        }
        return findAndAddSecondarySerializer.serializer;
    }

    protected boolean _hasNullKey(Map<?, ?> map) {
        return (map instanceof HashMap) && map.containsKey(null);
    }

    protected Map<?, ?> _orderEntries(Map<?, ?> map, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (map instanceof SortedMap) {
            return map;
        }
        if (_hasNullKey(map)) {
            TreeMap treeMap = new TreeMap();
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                Object key = entry.getKey();
                if (key == null) {
                    _writeNullKeyedEntry(jsonGenerator, serializerProvider, entry.getValue());
                } else {
                    treeMap.put(key, entry.getValue());
                }
            }
            return treeMap;
        }
        return new TreeMap(map);
    }

    protected void _writeNullKeyedEntry(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, Object obj) throws IOException {
        JsonSerializer<Object> jsonSerializer;
        JsonSerializer<Object> findNullKeySerializer = serializerProvider.findNullKeySerializer(this._keyType, this._property);
        if (obj == null) {
            if (this._suppressNulls) {
                return;
            }
            jsonSerializer = serializerProvider.getDefaultNullValueSerializer();
        } else {
            jsonSerializer = this._valueSerializer;
            if (jsonSerializer == null) {
                jsonSerializer = _findSerializer(serializerProvider, obj);
            }
            Object obj2 = this._suppressableValue;
            if (obj2 == MARKER_FOR_EMPTY) {
                if (jsonSerializer.isEmpty(serializerProvider, obj)) {
                    return;
                }
            } else if (obj2 != null && obj2.equals(obj)) {
                return;
            }
        }
        try {
            findNullKeySerializer.serialize(null, jsonGenerator, serializerProvider);
            jsonSerializer.serialize(obj, jsonGenerator, serializerProvider);
        } catch (Exception e) {
            wrapAndThrow(serializerProvider, e, obj, "");
        }
    }

    @Override // com.fasterxml.jackson.databind.ser.std.StdSerializer, com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable
    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
        JsonMapFormatVisitor expectMapFormat = jsonFormatVisitorWrapper.expectMapFormat(javaType);
        if (expectMapFormat != null) {
            expectMapFormat.keyFormat(this._keySerializer, this._keyType);
            JsonSerializer<Object> jsonSerializer = this._valueSerializer;
            if (jsonSerializer == null) {
                jsonSerializer = _findAndAddDynamic(this._dynamicValueSerializers, this._valueType, jsonFormatVisitorWrapper.getProvider());
            }
            expectMapFormat.valueFormat(jsonSerializer, this._valueType);
        }
    }

    @Override // com.fasterxml.jackson.databind.ser.ContextualSerializer
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        JsonSerializer<?> jsonSerializer;
        JsonSerializer<Object> jsonSerializer2;
        JsonSerializer<?> handleSecondaryContextualization;
        Set<String> set;
        Set<String> set2;
        boolean z;
        JsonInclude.Include contentInclusion;
        Object findFilterId;
        Boolean feature;
        AnnotationIntrospector annotationIntrospector = serializerProvider.getAnnotationIntrospector();
        Object obj = null;
        AnnotatedMember member = beanProperty == null ? null : beanProperty.getMember();
        if (StdSerializer._neitherNull(member, annotationIntrospector)) {
            Object findKeySerializer = annotationIntrospector.findKeySerializer(member);
            jsonSerializer = findKeySerializer != null ? serializerProvider.serializerInstance(member, findKeySerializer) : null;
            Object findContentSerializer = annotationIntrospector.findContentSerializer(member);
            jsonSerializer2 = findContentSerializer != null ? serializerProvider.serializerInstance(member, findContentSerializer) : null;
        } else {
            jsonSerializer = null;
            jsonSerializer2 = null;
        }
        if (jsonSerializer2 == null) {
            jsonSerializer2 = this._valueSerializer;
        }
        JsonSerializer<?> findContextualConvertingSerializer = findContextualConvertingSerializer(serializerProvider, beanProperty, jsonSerializer2);
        if (findContextualConvertingSerializer == null && this._valueTypeIsStatic && !this._valueType.isJavaLangObject()) {
            findContextualConvertingSerializer = serializerProvider.findContentValueSerializer(this._valueType, beanProperty);
        }
        JsonSerializer<?> jsonSerializer3 = findContextualConvertingSerializer;
        if (jsonSerializer == null) {
            jsonSerializer = this._keySerializer;
        }
        if (jsonSerializer == null) {
            handleSecondaryContextualization = serializerProvider.findKeySerializer(this._keyType, beanProperty);
        } else {
            handleSecondaryContextualization = serializerProvider.handleSecondaryContextualization(jsonSerializer, beanProperty);
        }
        JsonSerializer<?> jsonSerializer4 = handleSecondaryContextualization;
        HashSet hashSet = this._ignoredEntries;
        HashSet hashSet2 = this._includedEntries;
        boolean z2 = false;
        if (StdSerializer._neitherNull(member, annotationIntrospector)) {
            SerializationConfig mo7051getConfig = serializerProvider.mo7051getConfig();
            Set<String> findIgnoredForSerialization = annotationIntrospector.findPropertyIgnoralByName(mo7051getConfig, member).findIgnoredForSerialization();
            if (StdSerializer._nonEmpty(findIgnoredForSerialization)) {
                hashSet = hashSet == null ? new HashSet() : new HashSet(hashSet);
                for (String str : findIgnoredForSerialization) {
                    hashSet.add(str);
                }
            }
            Set<String> included = annotationIntrospector.findPropertyInclusionByName(mo7051getConfig, member).getIncluded();
            if (included != null) {
                hashSet2 = hashSet2 == null ? new HashSet() : new HashSet(hashSet2);
                for (String str2 : included) {
                    hashSet2.add(str2);
                }
            }
            z = Boolean.TRUE.equals(annotationIntrospector.findSerializationSortAlphabetically(member));
            set = hashSet;
            set2 = hashSet2;
        } else {
            set = hashSet;
            set2 = hashSet2;
            z = false;
        }
        JsonFormat.Value findFormatOverrides = findFormatOverrides(serializerProvider, beanProperty, Map.class);
        MapSerializer withResolved = withResolved(beanProperty, jsonSerializer4, jsonSerializer3, set, set2, (findFormatOverrides == null || (feature = findFormatOverrides.getFeature(JsonFormat.Feature.WRITE_SORTED_MAP_ENTRIES)) == null) ? z : feature.booleanValue());
        if (member != null && (findFilterId = annotationIntrospector.findFilterId(member)) != null) {
            withResolved = withResolved.mo7210withFilterId(findFilterId);
        }
        JsonInclude.Value findIncludeOverrides = findIncludeOverrides(serializerProvider, beanProperty, Map.class);
        if (findIncludeOverrides == null || (contentInclusion = findIncludeOverrides.getContentInclusion()) == JsonInclude.Include.USE_DEFAULTS) {
            return withResolved;
        }
        int ordinal = contentInclusion.ordinal();
        boolean z3 = true;
        if (ordinal != 1) {
            if (ordinal != 2) {
                if (ordinal == 3) {
                    obj = MARKER_FOR_EMPTY;
                } else if (ordinal != 4) {
                    if (ordinal == 5) {
                        obj = serializerProvider.includeFilterInstance(null, findIncludeOverrides.getContentFilter());
                        if (obj != null) {
                            z2 = serializerProvider.includeFilterSuppressNulls(obj);
                        }
                    }
                    z3 = z2;
                } else {
                    obj = BeanUtil.getDefaultValue(this._valueType);
                    if (obj != null && obj.getClass().isArray()) {
                        obj = ArrayBuilders.getArrayComparator(obj);
                    }
                }
            } else if (this._valueType.isReferenceType()) {
                obj = MARKER_FOR_EMPTY;
            }
        }
        return withResolved.withContentInclusion(obj, z3);
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public JsonSerializer<?> getContentSerializer() {
        return this._valueSerializer;
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public JavaType getContentType() {
        return this._valueType;
    }

    public JsonSerializer<?> getKeySerializer() {
        return this._keySerializer;
    }

    @Override // com.fasterxml.jackson.databind.ser.std.StdSerializer, com.fasterxml.jackson.databind.jsonschema.SchemaAware
    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
        return createSchemaNode("object", true);
    }

    public void serializeFields(Map<?, ?> map, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        Object obj;
        if (this._valueTypeSerializer != null) {
            serializeTypedFields(map, jsonGenerator, serializerProvider, null);
            return;
        }
        JsonSerializer<Object> jsonSerializer = this._keySerializer;
        try {
            obj = null;
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                try {
                    Object value = entry.getValue();
                    obj = entry.getKey();
                    if (obj == null) {
                        serializerProvider.findNullKeySerializer(this._keyType, this._property).serialize(null, jsonGenerator, serializerProvider);
                    } else if (this._inclusionChecker == null || !this._inclusionChecker.shouldIgnore(obj)) {
                        jsonSerializer.serialize(obj, jsonGenerator, serializerProvider);
                    }
                    if (value == null) {
                        serializerProvider.defaultSerializeNull(jsonGenerator);
                    } else {
                        JsonSerializer<Object> jsonSerializer2 = this._valueSerializer;
                        if (jsonSerializer2 == null) {
                            jsonSerializer2 = _findSerializer(serializerProvider, value);
                        }
                        jsonSerializer2.serialize(value, jsonGenerator, serializerProvider);
                    }
                } catch (Exception e) {
                    e = e;
                    wrapAndThrow(serializerProvider, e, map, String.valueOf(obj));
                    return;
                }
            }
        } catch (Exception e2) {
            e = e2;
            obj = null;
        }
    }

    public void serializeFieldsUsing(Map<?, ?> map, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, JsonSerializer<Object> jsonSerializer) throws IOException {
        JsonSerializer<Object> jsonSerializer2 = this._keySerializer;
        TypeSerializer typeSerializer = this._valueTypeSerializer;
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            Object key = entry.getKey();
            IgnorePropertiesUtil.Checker checker = this._inclusionChecker;
            if (checker == null || !checker.shouldIgnore(key)) {
                if (key == null) {
                    serializerProvider.findNullKeySerializer(this._keyType, this._property).serialize(null, jsonGenerator, serializerProvider);
                } else {
                    jsonSerializer2.serialize(key, jsonGenerator, serializerProvider);
                }
                Object value = entry.getValue();
                if (value == null) {
                    serializerProvider.defaultSerializeNull(jsonGenerator);
                } else if (typeSerializer == null) {
                    try {
                        jsonSerializer.serialize(value, jsonGenerator, serializerProvider);
                    } catch (Exception e) {
                        wrapAndThrow(serializerProvider, e, map, String.valueOf(key));
                    }
                } else {
                    jsonSerializer.serializeWithType(value, jsonGenerator, serializerProvider, typeSerializer);
                }
            }
        }
    }

    public void serializeFilteredAnyProperties(SerializerProvider serializerProvider, JsonGenerator jsonGenerator, Object obj, Map<?, ?> map, PropertyFilter propertyFilter, Object obj2) throws IOException {
        JsonSerializer<Object> jsonSerializer;
        JsonSerializer<Object> defaultNullValueSerializer;
        MapProperty mapProperty = new MapProperty(this._valueTypeSerializer, this._property);
        boolean z = MARKER_FOR_EMPTY == obj2;
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            Object key = entry.getKey();
            IgnorePropertiesUtil.Checker checker = this._inclusionChecker;
            if (checker == null || !checker.shouldIgnore(key)) {
                if (key == null) {
                    jsonSerializer = serializerProvider.findNullKeySerializer(this._keyType, this._property);
                } else {
                    jsonSerializer = this._keySerializer;
                }
                Object value = entry.getValue();
                if (value == null) {
                    if (!this._suppressNulls) {
                        defaultNullValueSerializer = serializerProvider.getDefaultNullValueSerializer();
                    }
                } else {
                    defaultNullValueSerializer = this._valueSerializer;
                    if (defaultNullValueSerializer == null) {
                        defaultNullValueSerializer = _findSerializer(serializerProvider, value);
                    }
                    if (z) {
                        if (defaultNullValueSerializer.isEmpty(serializerProvider, value)) {
                        }
                    } else if (obj2 != null && obj2.equals(value)) {
                    }
                }
                mapProperty.reset(key, value, jsonSerializer, defaultNullValueSerializer);
                try {
                    propertyFilter.serializeAsField(obj, jsonGenerator, serializerProvider, mapProperty);
                } catch (Exception e) {
                    wrapAndThrow(serializerProvider, e, map, String.valueOf(key));
                }
            }
        }
    }

    public void serializeFilteredFields(Map<?, ?> map, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, PropertyFilter propertyFilter, Object obj) throws IOException {
        JsonSerializer<Object> jsonSerializer;
        JsonSerializer<Object> defaultNullValueSerializer;
        MapProperty mapProperty = new MapProperty(this._valueTypeSerializer, this._property);
        boolean z = MARKER_FOR_EMPTY == obj;
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            Object key = entry.getKey();
            IgnorePropertiesUtil.Checker checker = this._inclusionChecker;
            if (checker == null || !checker.shouldIgnore(key)) {
                if (key == null) {
                    jsonSerializer = serializerProvider.findNullKeySerializer(this._keyType, this._property);
                } else {
                    jsonSerializer = this._keySerializer;
                }
                Object value = entry.getValue();
                if (value == null) {
                    if (!this._suppressNulls) {
                        defaultNullValueSerializer = serializerProvider.getDefaultNullValueSerializer();
                    }
                } else {
                    defaultNullValueSerializer = this._valueSerializer;
                    if (defaultNullValueSerializer == null) {
                        defaultNullValueSerializer = _findSerializer(serializerProvider, value);
                    }
                    if (z) {
                        if (defaultNullValueSerializer.isEmpty(serializerProvider, value)) {
                        }
                    } else if (obj != null && obj.equals(value)) {
                    }
                }
                mapProperty.reset(key, value, jsonSerializer, defaultNullValueSerializer);
                try {
                    propertyFilter.serializeAsField(map, jsonGenerator, serializerProvider, mapProperty);
                } catch (Exception e) {
                    wrapAndThrow(serializerProvider, e, map, String.valueOf(key));
                }
            }
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(9:12|(2:52|53)(2:14|(1:19)(2:50|32))|20|(3:44|45|(2:49|32)(2:47|48))(4:22|23|(1:25)|(3:40|41|(2:43|32))(2:27|(2:31|32)))|33|34|36|32|10) */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0070, code lost:
        r2 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0071, code lost:
        wrapAndThrow(r10, r2, r8, java.lang.String.valueOf(r3));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void serializeOptionalFields(java.util.Map<?, ?> r8, com.fasterxml.jackson.core.JsonGenerator r9, com.fasterxml.jackson.databind.SerializerProvider r10, java.lang.Object r11) throws java.io.IOException {
        /*
            r7 = this;
            com.fasterxml.jackson.databind.jsontype.TypeSerializer r0 = r7._valueTypeSerializer
            if (r0 == 0) goto L8
            r7.serializeTypedFields(r8, r9, r10, r11)
            return
        L8:
            java.lang.Object r0 = com.fasterxml.jackson.databind.ser.std.MapSerializer.MARKER_FOR_EMPTY
            if (r0 != r11) goto Le
            r0 = 1
            goto Lf
        Le:
            r0 = 0
        Lf:
            java.util.Set r1 = r8.entrySet()
            java.util.Iterator r1 = r1.iterator()
        L17:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L79
            java.lang.Object r2 = r1.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            java.lang.Object r3 = r2.getKey()
            if (r3 != 0) goto L32
            com.fasterxml.jackson.databind.JavaType r4 = r7._keyType
            com.fasterxml.jackson.databind.BeanProperty r5 = r7._property
            com.fasterxml.jackson.databind.JsonSerializer r4 = r10.findNullKeySerializer(r4, r5)
            goto L3f
        L32:
            com.fasterxml.jackson.databind.util.IgnorePropertiesUtil$Checker r4 = r7._inclusionChecker
            if (r4 == 0) goto L3d
            boolean r4 = r4.shouldIgnore(r3)
            if (r4 == 0) goto L3d
            goto L17
        L3d:
            com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object> r4 = r7._keySerializer
        L3f:
            java.lang.Object r2 = r2.getValue()
            if (r2 != 0) goto L4f
            boolean r5 = r7._suppressNulls
            if (r5 == 0) goto L4a
            goto L17
        L4a:
            com.fasterxml.jackson.databind.JsonSerializer r5 = r10.getDefaultNullValueSerializer()
            goto L69
        L4f:
            com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object> r5 = r7._valueSerializer
            if (r5 != 0) goto L57
            com.fasterxml.jackson.databind.JsonSerializer r5 = r7._findSerializer(r10, r2)
        L57:
            if (r0 == 0) goto L60
            boolean r6 = r5.isEmpty(r10, r2)
            if (r6 == 0) goto L69
            goto L17
        L60:
            if (r11 == 0) goto L69
            boolean r6 = r11.equals(r2)
            if (r6 == 0) goto L69
            goto L17
        L69:
            r4.serialize(r3, r9, r10)     // Catch: java.lang.Exception -> L70
            r5.serialize(r2, r9, r10)     // Catch: java.lang.Exception -> L70
            goto L17
        L70:
            r2 = move-exception
            java.lang.String r3 = java.lang.String.valueOf(r3)
            r7.wrapAndThrow(r10, r2, r8, r3)
            goto L17
        L79:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.std.MapSerializer.serializeOptionalFields(java.util.Map, com.fasterxml.jackson.core.JsonGenerator, com.fasterxml.jackson.databind.SerializerProvider, java.lang.Object):void");
    }

    /* JADX WARN: Can't wrap try/catch for region: R(10:7|(2:51|52)(2:9|(1:14)(2:49|32))|15|(3:43|44|(2:48|32)(2:46|47))(4:17|18|(1:20)|(3:38|39|(2:42|32)(1:41))(2:22|(2:36|32)))|27|28|29|31|32|5) */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x006a, code lost:
        r2 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x006b, code lost:
        wrapAndThrow(r10, r2, r8, java.lang.String.valueOf(r3));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void serializeTypedFields(java.util.Map<?, ?> r8, com.fasterxml.jackson.core.JsonGenerator r9, com.fasterxml.jackson.databind.SerializerProvider r10, java.lang.Object r11) throws java.io.IOException {
        /*
            r7 = this;
            java.lang.Object r0 = com.fasterxml.jackson.databind.ser.std.MapSerializer.MARKER_FOR_EMPTY
            if (r0 != r11) goto L6
            r0 = 1
            goto L7
        L6:
            r0 = 0
        L7:
            java.util.Set r1 = r8.entrySet()
            java.util.Iterator r1 = r1.iterator()
        Lf:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L73
            java.lang.Object r2 = r1.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            java.lang.Object r3 = r2.getKey()
            if (r3 != 0) goto L2a
            com.fasterxml.jackson.databind.JavaType r4 = r7._keyType
            com.fasterxml.jackson.databind.BeanProperty r5 = r7._property
            com.fasterxml.jackson.databind.JsonSerializer r4 = r10.findNullKeySerializer(r4, r5)
            goto L37
        L2a:
            com.fasterxml.jackson.databind.util.IgnorePropertiesUtil$Checker r4 = r7._inclusionChecker
            if (r4 == 0) goto L35
            boolean r4 = r4.shouldIgnore(r3)
            if (r4 == 0) goto L35
            goto Lf
        L35:
            com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object> r4 = r7._keySerializer
        L37:
            java.lang.Object r2 = r2.getValue()
            if (r2 != 0) goto L47
            boolean r5 = r7._suppressNulls
            if (r5 == 0) goto L42
            goto Lf
        L42:
            com.fasterxml.jackson.databind.JsonSerializer r5 = r10.getDefaultNullValueSerializer()
            goto L61
        L47:
            com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object> r5 = r7._valueSerializer
            if (r5 != 0) goto L4f
            com.fasterxml.jackson.databind.JsonSerializer r5 = r7._findSerializer(r10, r2)
        L4f:
            if (r0 == 0) goto L58
            boolean r6 = r5.isEmpty(r10, r2)
            if (r6 == 0) goto L61
            goto Lf
        L58:
            if (r11 == 0) goto L61
            boolean r6 = r11.equals(r2)
            if (r6 == 0) goto L61
            goto Lf
        L61:
            r4.serialize(r3, r9, r10)
            com.fasterxml.jackson.databind.jsontype.TypeSerializer r4 = r7._valueTypeSerializer     // Catch: java.lang.Exception -> L6a
            r5.serializeWithType(r2, r9, r10, r4)     // Catch: java.lang.Exception -> L6a
            goto Lf
        L6a:
            r2 = move-exception
            java.lang.String r3 = java.lang.String.valueOf(r3)
            r7.wrapAndThrow(r10, r2, r8, r3)
            goto Lf
        L73:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.std.MapSerializer.serializeTypedFields(java.util.Map, com.fasterxml.jackson.core.JsonGenerator, com.fasterxml.jackson.databind.SerializerProvider, java.lang.Object):void");
    }

    public void serializeWithoutTypeInfo(Map<?, ?> map, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        PropertyFilter findPropertyFilter;
        if (!map.isEmpty()) {
            if (this._sortKeys || serializerProvider.isEnabled(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS)) {
                map = _orderEntries(map, jsonGenerator, serializerProvider);
            }
            Map<?, ?> map2 = map;
            Object obj = this._filterId;
            if (obj != null && (findPropertyFilter = findPropertyFilter(serializerProvider, obj, map2)) != null) {
                serializeFilteredFields(map2, jsonGenerator, serializerProvider, findPropertyFilter, this._suppressableValue);
            } else if (this._suppressableValue == null && !this._suppressNulls) {
                JsonSerializer<Object> jsonSerializer = this._valueSerializer;
                if (jsonSerializer != null) {
                    serializeFieldsUsing(map2, jsonGenerator, serializerProvider, jsonSerializer);
                } else {
                    serializeFields(map2, jsonGenerator, serializerProvider);
                }
            } else {
                serializeOptionalFields(map2, jsonGenerator, serializerProvider, this._suppressableValue);
            }
        }
    }

    public MapSerializer withContentInclusion(Object obj, boolean z) {
        if (obj == this._suppressableValue && z == this._suppressNulls) {
            return this;
        }
        _ensureOverride("withContentInclusion");
        return new MapSerializer(this, this._valueTypeSerializer, obj, z);
    }

    public MapSerializer withResolved(BeanProperty beanProperty, JsonSerializer<?> jsonSerializer, JsonSerializer<?> jsonSerializer2, Set<String> set, Set<String> set2, boolean z) {
        _ensureOverride("withResolved");
        MapSerializer mapSerializer = new MapSerializer(this, beanProperty, jsonSerializer, jsonSerializer2, set, set2);
        return z != mapSerializer._sortKeys ? new MapSerializer(mapSerializer, this._filterId, z) : mapSerializer;
    }

    @Deprecated
    protected void _ensureOverride() {
        _ensureOverride(CapabilityQueryConstants.TARGET_NOT_AVAILABLE);
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    /* renamed from: _withValueTypeSerializer  reason: collision with other method in class */
    public MapSerializer mo7209_withValueTypeSerializer(TypeSerializer typeSerializer) {
        if (this._valueTypeSerializer == typeSerializer) {
            return this;
        }
        _ensureOverride("_withValueTypeSerializer");
        return new MapSerializer(this, typeSerializer, this._suppressableValue, this._suppressNulls);
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public boolean hasSingleElement(Map<?, ?> map) {
        return map.size() == 1;
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public boolean isEmpty(SerializerProvider serializerProvider, Map<?, ?> map) {
        JsonSerializer<Object> _findSerializer;
        if (map.isEmpty()) {
            return true;
        }
        Object obj = this._suppressableValue;
        if (obj == null && !this._suppressNulls) {
            return false;
        }
        JsonSerializer<Object> jsonSerializer = this._valueSerializer;
        boolean z = MARKER_FOR_EMPTY == obj;
        if (jsonSerializer != null) {
            for (Object obj2 : map.values()) {
                if (obj2 == null) {
                    if (!this._suppressNulls) {
                        return false;
                    }
                } else if (z) {
                    if (!jsonSerializer.isEmpty(serializerProvider, obj2)) {
                        return false;
                    }
                } else if (obj == null || !obj.equals(map)) {
                    return false;
                }
            }
            return true;
        }
        for (Object obj3 : map.values()) {
            if (obj3 == null) {
                if (!this._suppressNulls) {
                    return false;
                }
            } else {
                try {
                    _findSerializer = _findSerializer(serializerProvider, obj3);
                } catch (JsonMappingException unused) {
                }
                if (z) {
                    if (!_findSerializer.isEmpty(serializerProvider, obj3)) {
                        return false;
                    }
                } else {
                    if (obj != null && obj.equals(map)) {
                    }
                    return false;
                }
            }
        }
        return true;
    }

    @Override // com.fasterxml.jackson.databind.ser.std.StdSerializer, com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(Map<?, ?> map, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject(map);
        serializeWithoutTypeInfo(map, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public void serializeWithType(Map<?, ?> map, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException {
        jsonGenerator.setCurrentValue(map);
        WritableTypeId writeTypePrefix = typeSerializer.writeTypePrefix(jsonGenerator, typeSerializer.typeId(map, JsonToken.START_OBJECT));
        serializeWithoutTypeInfo(map, jsonGenerator, serializerProvider);
        typeSerializer.writeTypeSuffix(jsonGenerator, writeTypePrefix);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    /* renamed from: withFilterId  reason: collision with other method in class */
    public MapSerializer mo7210withFilterId(Object obj) {
        if (this._filterId == obj) {
            return this;
        }
        _ensureOverride("withFilterId");
        return new MapSerializer(this, obj, this._sortKeys);
    }

    @Deprecated
    public MapSerializer withContentInclusion(Object obj) {
        return new MapSerializer(this, this._valueTypeSerializer, obj, this._suppressNulls);
    }

    protected final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap propertySerializerMap, JavaType javaType, SerializerProvider serializerProvider) throws JsonMappingException {
        PropertySerializerMap.SerializerAndMapResult findAndAddSecondarySerializer = propertySerializerMap.findAndAddSecondarySerializer(javaType, serializerProvider, this._property);
        PropertySerializerMap propertySerializerMap2 = findAndAddSecondarySerializer.map;
        if (propertySerializerMap != propertySerializerMap2) {
            this._dynamicValueSerializers = propertySerializerMap2;
        }
        return findAndAddSecondarySerializer.serializer;
    }

    public MapSerializer withResolved(BeanProperty beanProperty, JsonSerializer<?> jsonSerializer, JsonSerializer<?> jsonSerializer2, Set<String> set, boolean z) {
        return withResolved(beanProperty, jsonSerializer, jsonSerializer2, set, null, z);
    }

    public static MapSerializer construct(Set<String> set, JavaType javaType, boolean z, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer, JsonSerializer<Object> jsonSerializer2, Object obj) {
        return construct(set, null, javaType, z, typeSerializer, jsonSerializer, jsonSerializer2, obj);
    }

    @Deprecated
    public static MapSerializer construct(String[] strArr, JavaType javaType, boolean z, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer, JsonSerializer<Object> jsonSerializer2, Object obj) {
        return construct(ArrayBuilders.arrayToSet(strArr), javaType, z, typeSerializer, jsonSerializer, jsonSerializer2, obj);
    }

    @Deprecated
    protected MapSerializer(Set<String> set, JavaType javaType, JavaType javaType2, boolean z, TypeSerializer typeSerializer, JsonSerializer<?> jsonSerializer, JsonSerializer<?> jsonSerializer2) {
        this(set, null, javaType, javaType2, z, typeSerializer, jsonSerializer, jsonSerializer2);
    }

    protected MapSerializer(MapSerializer mapSerializer, BeanProperty beanProperty, JsonSerializer<?> jsonSerializer, JsonSerializer<?> jsonSerializer2, Set<String> set, Set<String> set2) {
        super(Map.class, false);
        this._ignoredEntries = (set == null || set.isEmpty()) ? null : set;
        this._includedEntries = set2;
        this._keyType = mapSerializer._keyType;
        this._valueType = mapSerializer._valueType;
        this._valueTypeIsStatic = mapSerializer._valueTypeIsStatic;
        this._valueTypeSerializer = mapSerializer._valueTypeSerializer;
        this._keySerializer = jsonSerializer;
        this._valueSerializer = jsonSerializer2;
        this._dynamicValueSerializers = PropertySerializerMap.emptyForProperties();
        this._property = beanProperty;
        this._filterId = mapSerializer._filterId;
        this._sortKeys = mapSerializer._sortKeys;
        this._suppressableValue = mapSerializer._suppressableValue;
        this._suppressNulls = mapSerializer._suppressNulls;
        this._inclusionChecker = IgnorePropertiesUtil.buildCheckerIfNeeded(this._ignoredEntries, this._includedEntries);
    }

    @Deprecated
    protected MapSerializer(MapSerializer mapSerializer, BeanProperty beanProperty, JsonSerializer<?> jsonSerializer, JsonSerializer<?> jsonSerializer2, Set<String> set) {
        this(mapSerializer, beanProperty, jsonSerializer, jsonSerializer2, set, null);
    }

    protected MapSerializer(MapSerializer mapSerializer, TypeSerializer typeSerializer, Object obj, boolean z) {
        super(Map.class, false);
        this._ignoredEntries = mapSerializer._ignoredEntries;
        this._includedEntries = mapSerializer._includedEntries;
        this._keyType = mapSerializer._keyType;
        this._valueType = mapSerializer._valueType;
        this._valueTypeIsStatic = mapSerializer._valueTypeIsStatic;
        this._valueTypeSerializer = typeSerializer;
        this._keySerializer = mapSerializer._keySerializer;
        this._valueSerializer = mapSerializer._valueSerializer;
        this._dynamicValueSerializers = mapSerializer._dynamicValueSerializers;
        this._property = mapSerializer._property;
        this._filterId = mapSerializer._filterId;
        this._sortKeys = mapSerializer._sortKeys;
        this._suppressableValue = obj;
        this._suppressNulls = z;
        this._inclusionChecker = mapSerializer._inclusionChecker;
    }

    protected MapSerializer(MapSerializer mapSerializer, Object obj, boolean z) {
        super(Map.class, false);
        this._ignoredEntries = mapSerializer._ignoredEntries;
        this._includedEntries = mapSerializer._includedEntries;
        this._keyType = mapSerializer._keyType;
        this._valueType = mapSerializer._valueType;
        this._valueTypeIsStatic = mapSerializer._valueTypeIsStatic;
        this._valueTypeSerializer = mapSerializer._valueTypeSerializer;
        this._keySerializer = mapSerializer._keySerializer;
        this._valueSerializer = mapSerializer._valueSerializer;
        this._dynamicValueSerializers = PropertySerializerMap.emptyForProperties();
        this._property = mapSerializer._property;
        this._filterId = obj;
        this._sortKeys = z;
        this._suppressableValue = mapSerializer._suppressableValue;
        this._suppressNulls = mapSerializer._suppressNulls;
        this._inclusionChecker = mapSerializer._inclusionChecker;
    }

    @Deprecated
    protected MapSerializer(MapSerializer mapSerializer, TypeSerializer typeSerializer, Object obj) {
        this(mapSerializer, typeSerializer, obj, false);
    }
}
