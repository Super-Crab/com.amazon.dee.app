package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.StreamReadCapability;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.ObjectBuffer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
@JacksonStdImpl
/* loaded from: classes2.dex */
public class UntypedObjectDeserializer extends StdDeserializer<Object> implements ResolvableDeserializer, ContextualDeserializer {
    protected static final Object[] NO_OBJECTS = new Object[0];
    private static final long serialVersionUID = 1;
    protected JsonDeserializer<Object> _listDeserializer;
    protected JavaType _listType;
    protected JsonDeserializer<Object> _mapDeserializer;
    protected JavaType _mapType;
    protected final boolean _nonMerging;
    protected JsonDeserializer<Object> _numberDeserializer;
    protected JsonDeserializer<Object> _stringDeserializer;

    @JacksonStdImpl
    /* loaded from: classes2.dex */
    public static class Vanilla extends StdDeserializer<Object> {
        private static final long serialVersionUID = 1;
        public static final Vanilla std = new Vanilla();
        protected final boolean _nonMerging;

        public Vanilla() {
            this(false);
        }

        private void _squashDups(Map<String, Object> map, String str, Object obj, Object obj2) {
            if (obj instanceof List) {
                ((List) obj).add(obj2);
                map.put(str, obj);
                return;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(obj);
            arrayList.add(obj2);
            map.put(str, arrayList);
        }

        public static Vanilla instance(boolean z) {
            if (z) {
                return new Vanilla(true);
            }
            return std;
        }

        protected Object _mapObjectWithDups(JsonParser jsonParser, DeserializationContext deserializationContext, Map<String, Object> map, String str, Object obj, Object obj2, String str2) throws IOException {
            boolean isEnabled = deserializationContext.isEnabled(StreamReadCapability.DUPLICATE_PROPERTIES);
            if (isEnabled) {
                _squashDups(map, str, obj, obj2);
            }
            while (str2 != null) {
                jsonParser.nextToken();
                Object mo7111deserialize = mo7111deserialize(jsonParser, deserializationContext);
                Object put = map.put(str2, mo7111deserialize);
                if (put != null && isEnabled) {
                    _squashDups(map, str2, put, mo7111deserialize);
                }
                str2 = jsonParser.nextFieldName();
            }
            return map;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        /* renamed from: deserialize */
        public Object mo7111deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            switch (jsonParser.currentTokenId()) {
                case 1:
                    if (jsonParser.nextToken() == JsonToken.END_OBJECT) {
                        return new LinkedHashMap(2);
                    }
                    break;
                case 2:
                    return new LinkedHashMap(2);
                case 3:
                    if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                        if (deserializationContext.isEnabled(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY)) {
                            return UntypedObjectDeserializer.NO_OBJECTS;
                        }
                        return new ArrayList(2);
                    } else if (deserializationContext.isEnabled(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY)) {
                        return mapArrayToArray(jsonParser, deserializationContext);
                    } else {
                        return mapArray(jsonParser, deserializationContext);
                    }
                case 4:
                default:
                    return deserializationContext.handleUnexpectedToken(Object.class, jsonParser);
                case 5:
                    break;
                case 6:
                    return jsonParser.getText();
                case 7:
                    if (deserializationContext.hasSomeOfFeatures(StdDeserializer.F_MASK_INT_COERCIONS)) {
                        return _coerceIntegral(jsonParser, deserializationContext);
                    }
                    return jsonParser.getNumberValue();
                case 8:
                    if (deserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
                        return jsonParser.getDecimalValue();
                    }
                    return jsonParser.getNumberValue();
                case 9:
                    return Boolean.TRUE;
                case 10:
                    return Boolean.FALSE;
                case 11:
                    return null;
                case 12:
                    return jsonParser.getEmbeddedObject();
            }
            return mapObject(jsonParser, deserializationContext);
        }

        @Override // com.fasterxml.jackson.databind.deser.std.StdDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
        /* renamed from: deserializeWithType */
        public Object mo7107deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException {
            int currentTokenId = jsonParser.currentTokenId();
            if (currentTokenId != 1 && currentTokenId != 3) {
                switch (currentTokenId) {
                    case 5:
                        break;
                    case 6:
                        return jsonParser.getText();
                    case 7:
                        if (deserializationContext.isEnabled(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS)) {
                            return jsonParser.getBigIntegerValue();
                        }
                        return jsonParser.getNumberValue();
                    case 8:
                        if (deserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
                            return jsonParser.getDecimalValue();
                        }
                        return jsonParser.getNumberValue();
                    case 9:
                        return Boolean.TRUE;
                    case 10:
                        return Boolean.FALSE;
                    case 11:
                        return null;
                    case 12:
                        return jsonParser.getEmbeddedObject();
                    default:
                        return deserializationContext.handleUnexpectedToken(Object.class, jsonParser);
                }
            }
            return typeDeserializer.deserializeTypedFromAny(jsonParser, deserializationContext);
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public LogicalType logicalType() {
            return LogicalType.Untyped;
        }

        protected Object mapArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            Object mo7111deserialize = mo7111deserialize(jsonParser, deserializationContext);
            int i = 2;
            if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                ArrayList arrayList = new ArrayList(2);
                arrayList.add(mo7111deserialize);
                return arrayList;
            }
            Object mo7111deserialize2 = mo7111deserialize(jsonParser, deserializationContext);
            if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                ArrayList arrayList2 = new ArrayList(2);
                arrayList2.add(mo7111deserialize);
                arrayList2.add(mo7111deserialize2);
                return arrayList2;
            }
            ObjectBuffer leaseObjectBuffer = deserializationContext.leaseObjectBuffer();
            Object[] resetAndStart = leaseObjectBuffer.resetAndStart();
            resetAndStart[0] = mo7111deserialize;
            resetAndStart[1] = mo7111deserialize2;
            int i2 = 2;
            while (true) {
                Object mo7111deserialize3 = mo7111deserialize(jsonParser, deserializationContext);
                i++;
                if (i2 >= resetAndStart.length) {
                    resetAndStart = leaseObjectBuffer.appendCompletedChunk(resetAndStart);
                    i2 = 0;
                }
                int i3 = i2 + 1;
                resetAndStart[i2] = mo7111deserialize3;
                if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                    ArrayList arrayList3 = new ArrayList(i);
                    leaseObjectBuffer.completeAndClearBuffer(resetAndStart, i3, arrayList3);
                    return arrayList3;
                }
                i2 = i3;
            }
        }

        protected Object[] mapArrayToArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            ObjectBuffer leaseObjectBuffer = deserializationContext.leaseObjectBuffer();
            Object[] resetAndStart = leaseObjectBuffer.resetAndStart();
            int i = 0;
            while (true) {
                Object mo7111deserialize = mo7111deserialize(jsonParser, deserializationContext);
                if (i >= resetAndStart.length) {
                    resetAndStart = leaseObjectBuffer.appendCompletedChunk(resetAndStart);
                    i = 0;
                }
                int i2 = i + 1;
                resetAndStart[i] = mo7111deserialize;
                if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                    return leaseObjectBuffer.completeAndClearBuffer(resetAndStart, i2);
                }
                i = i2;
            }
        }

        protected Object mapObject(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            String text = jsonParser.getText();
            jsonParser.nextToken();
            Object mo7111deserialize = mo7111deserialize(jsonParser, deserializationContext);
            String nextFieldName = jsonParser.nextFieldName();
            if (nextFieldName == null) {
                LinkedHashMap linkedHashMap = new LinkedHashMap(2);
                linkedHashMap.put(text, mo7111deserialize);
                return linkedHashMap;
            }
            jsonParser.nextToken();
            Object mo7111deserialize2 = mo7111deserialize(jsonParser, deserializationContext);
            String nextFieldName2 = jsonParser.nextFieldName();
            if (nextFieldName2 == null) {
                LinkedHashMap linkedHashMap2 = new LinkedHashMap(4);
                linkedHashMap2.put(text, mo7111deserialize);
                return linkedHashMap2.put(nextFieldName, mo7111deserialize2) != null ? _mapObjectWithDups(jsonParser, deserializationContext, linkedHashMap2, text, mo7111deserialize, mo7111deserialize2, nextFieldName2) : linkedHashMap2;
            }
            LinkedHashMap linkedHashMap3 = new LinkedHashMap();
            linkedHashMap3.put(text, mo7111deserialize);
            if (linkedHashMap3.put(nextFieldName, mo7111deserialize2) != null) {
                return _mapObjectWithDups(jsonParser, deserializationContext, linkedHashMap3, text, mo7111deserialize, mo7111deserialize2, nextFieldName2);
            }
            String str = nextFieldName2;
            do {
                jsonParser.nextToken();
                Object mo7111deserialize3 = mo7111deserialize(jsonParser, deserializationContext);
                Object put = linkedHashMap3.put(str, mo7111deserialize3);
                if (put != null) {
                    return _mapObjectWithDups(jsonParser, deserializationContext, linkedHashMap3, str, put, mo7111deserialize3, jsonParser.nextFieldName());
                }
                str = jsonParser.nextFieldName();
            } while (str != null);
            return linkedHashMap3;
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public Boolean supportsUpdate(DeserializationConfig deserializationConfig) {
            if (this._nonMerging) {
                return Boolean.FALSE;
            }
            return null;
        }

        protected Vanilla(boolean z) {
            super(Object.class);
            this._nonMerging = z;
        }

        /* JADX WARN: Code restructure failed: missing block: B:15:0x001a, code lost:
            if (r0 != 5) goto L29;
         */
        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public java.lang.Object deserialize(com.fasterxml.jackson.core.JsonParser r5, com.fasterxml.jackson.databind.DeserializationContext r6, java.lang.Object r7) throws java.io.IOException {
            /*
                r4 = this;
                boolean r0 = r4._nonMerging
                if (r0 == 0) goto L9
                java.lang.Object r5 = r4.mo7111deserialize(r5, r6)
                return r5
            L9:
                int r0 = r5.currentTokenId()
                r1 = 1
                if (r0 == r1) goto L3d
                r1 = 2
                if (r0 == r1) goto L3c
                r1 = 3
                if (r0 == r1) goto L1d
                r1 = 4
                if (r0 == r1) goto L3c
                r1 = 5
                if (r0 == r1) goto L46
                goto L6f
            L1d:
                com.fasterxml.jackson.core.JsonToken r0 = r5.nextToken()
                com.fasterxml.jackson.core.JsonToken r1 = com.fasterxml.jackson.core.JsonToken.END_ARRAY
                if (r0 != r1) goto L26
                return r7
            L26:
                boolean r0 = r7 instanceof java.util.Collection
                if (r0 == 0) goto L6f
                r0 = r7
                java.util.Collection r0 = (java.util.Collection) r0
            L2d:
                java.lang.Object r1 = r4.mo7111deserialize(r5, r6)
                r0.add(r1)
                com.fasterxml.jackson.core.JsonToken r1 = r5.nextToken()
                com.fasterxml.jackson.core.JsonToken r2 = com.fasterxml.jackson.core.JsonToken.END_ARRAY
                if (r1 != r2) goto L2d
            L3c:
                return r7
            L3d:
                com.fasterxml.jackson.core.JsonToken r0 = r5.nextToken()
                com.fasterxml.jackson.core.JsonToken r1 = com.fasterxml.jackson.core.JsonToken.END_OBJECT
                if (r0 != r1) goto L46
                return r7
            L46:
                boolean r0 = r7 instanceof java.util.Map
                if (r0 == 0) goto L6f
                r0 = r7
                java.util.Map r0 = (java.util.Map) r0
                java.lang.String r1 = r5.currentName()
            L51:
                r5.nextToken()
                java.lang.Object r2 = r0.get(r1)
                if (r2 == 0) goto L5f
                java.lang.Object r3 = r4.deserialize(r5, r6, r2)
                goto L63
            L5f:
                java.lang.Object r3 = r4.mo7111deserialize(r5, r6)
            L63:
                if (r3 == r2) goto L68
                r0.put(r1, r3)
            L68:
                java.lang.String r1 = r5.nextFieldName()
                if (r1 != 0) goto L51
                return r7
            L6f:
                java.lang.Object r5 = r4.mo7111deserialize(r5, r6)
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.UntypedObjectDeserializer.Vanilla.deserialize(com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.databind.DeserializationContext, java.lang.Object):java.lang.Object");
        }
    }

    @Deprecated
    public UntypedObjectDeserializer() {
        this((JavaType) null, (JavaType) null);
    }

    private void _squashDups(Map<String, Object> map, String str, Object obj, Object obj2) {
        if (obj instanceof List) {
            ((List) obj).add(obj2);
            map.put(str, obj);
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(obj);
        arrayList.add(obj2);
        map.put(str, arrayList);
    }

    protected JsonDeserializer<Object> _clearIfStdImpl(JsonDeserializer<Object> jsonDeserializer) {
        if (ClassUtil.isJacksonStdImpl(jsonDeserializer)) {
            return null;
        }
        return jsonDeserializer;
    }

    protected JsonDeserializer<Object> _findCustomDeser(DeserializationContext deserializationContext, JavaType javaType) throws JsonMappingException {
        return deserializationContext.findNonContextualValueDeserializer(javaType);
    }

    protected Object _mapObjectWithDups(JsonParser jsonParser, DeserializationContext deserializationContext, Map<String, Object> map, String str, Object obj, Object obj2, String str2) throws IOException {
        boolean isEnabled = deserializationContext.isEnabled(StreamReadCapability.DUPLICATE_PROPERTIES);
        if (isEnabled) {
            _squashDups(map, str, obj, obj2);
        }
        while (str2 != null) {
            jsonParser.nextToken();
            Object mo7111deserialize = mo7111deserialize(jsonParser, deserializationContext);
            Object put = map.put(str2, mo7111deserialize);
            if (put != null && isEnabled) {
                _squashDups(map, str, put, mo7111deserialize);
            }
            str2 = jsonParser.nextFieldName();
        }
        return map;
    }

    @Override // com.fasterxml.jackson.databind.deser.ContextualDeserializer
    /* renamed from: createContextual */
    public JsonDeserializer<?> mo7066createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
        boolean z = beanProperty == null && Boolean.FALSE.equals(deserializationContext.mo7051getConfig().getDefaultMergeable(Object.class));
        if (this._stringDeserializer == null && this._numberDeserializer == null && this._mapDeserializer == null && this._listDeserializer == null && UntypedObjectDeserializer.class == UntypedObjectDeserializer.class) {
            return Vanilla.instance(z);
        }
        return z != this._nonMerging ? new UntypedObjectDeserializer(this, z) : this;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    /* renamed from: deserialize */
    public Object mo7111deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        switch (jsonParser.currentTokenId()) {
            case 1:
            case 2:
            case 5:
                JsonDeserializer<Object> jsonDeserializer = this._mapDeserializer;
                if (jsonDeserializer != null) {
                    return jsonDeserializer.mo7111deserialize(jsonParser, deserializationContext);
                }
                return mapObject(jsonParser, deserializationContext);
            case 3:
                if (deserializationContext.isEnabled(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY)) {
                    return mapArrayToArray(jsonParser, deserializationContext);
                }
                JsonDeserializer<Object> jsonDeserializer2 = this._listDeserializer;
                if (jsonDeserializer2 != null) {
                    return jsonDeserializer2.mo7111deserialize(jsonParser, deserializationContext);
                }
                return mapArray(jsonParser, deserializationContext);
            case 4:
            default:
                return deserializationContext.handleUnexpectedToken(Object.class, jsonParser);
            case 6:
                JsonDeserializer<Object> jsonDeserializer3 = this._stringDeserializer;
                if (jsonDeserializer3 != null) {
                    return jsonDeserializer3.mo7111deserialize(jsonParser, deserializationContext);
                }
                return jsonParser.getText();
            case 7:
                JsonDeserializer<Object> jsonDeserializer4 = this._numberDeserializer;
                if (jsonDeserializer4 != null) {
                    return jsonDeserializer4.mo7111deserialize(jsonParser, deserializationContext);
                }
                if (deserializationContext.hasSomeOfFeatures(StdDeserializer.F_MASK_INT_COERCIONS)) {
                    return _coerceIntegral(jsonParser, deserializationContext);
                }
                return jsonParser.getNumberValue();
            case 8:
                JsonDeserializer<Object> jsonDeserializer5 = this._numberDeserializer;
                if (jsonDeserializer5 != null) {
                    return jsonDeserializer5.mo7111deserialize(jsonParser, deserializationContext);
                }
                if (deserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
                    return jsonParser.getDecimalValue();
                }
                return jsonParser.getNumberValue();
            case 9:
                return Boolean.TRUE;
            case 10:
                return Boolean.FALSE;
            case 11:
                return null;
            case 12:
                return jsonParser.getEmbeddedObject();
        }
    }

    @Override // com.fasterxml.jackson.databind.deser.std.StdDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    /* renamed from: deserializeWithType */
    public Object mo7107deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException {
        int currentTokenId = jsonParser.currentTokenId();
        if (currentTokenId != 1 && currentTokenId != 3) {
            switch (currentTokenId) {
                case 5:
                    break;
                case 6:
                    JsonDeserializer<Object> jsonDeserializer = this._stringDeserializer;
                    if (jsonDeserializer != null) {
                        return jsonDeserializer.mo7111deserialize(jsonParser, deserializationContext);
                    }
                    return jsonParser.getText();
                case 7:
                    JsonDeserializer<Object> jsonDeserializer2 = this._numberDeserializer;
                    if (jsonDeserializer2 != null) {
                        return jsonDeserializer2.mo7111deserialize(jsonParser, deserializationContext);
                    }
                    if (deserializationContext.hasSomeOfFeatures(StdDeserializer.F_MASK_INT_COERCIONS)) {
                        return _coerceIntegral(jsonParser, deserializationContext);
                    }
                    return jsonParser.getNumberValue();
                case 8:
                    JsonDeserializer<Object> jsonDeserializer3 = this._numberDeserializer;
                    if (jsonDeserializer3 != null) {
                        return jsonDeserializer3.mo7111deserialize(jsonParser, deserializationContext);
                    }
                    if (deserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
                        return jsonParser.getDecimalValue();
                    }
                    return jsonParser.getNumberValue();
                case 9:
                    return Boolean.TRUE;
                case 10:
                    return Boolean.FALSE;
                case 11:
                    return null;
                case 12:
                    return jsonParser.getEmbeddedObject();
                default:
                    return deserializationContext.handleUnexpectedToken(Object.class, jsonParser);
            }
        }
        return typeDeserializer.deserializeTypedFromAny(jsonParser, deserializationContext);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public boolean isCachable() {
        return true;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public LogicalType logicalType() {
        return LogicalType.Untyped;
    }

    protected Object mapArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        int i = 2;
        if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
            return new ArrayList(2);
        }
        Object mo7111deserialize = mo7111deserialize(jsonParser, deserializationContext);
        if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
            ArrayList arrayList = new ArrayList(2);
            arrayList.add(mo7111deserialize);
            return arrayList;
        }
        Object mo7111deserialize2 = mo7111deserialize(jsonParser, deserializationContext);
        if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
            ArrayList arrayList2 = new ArrayList(2);
            arrayList2.add(mo7111deserialize);
            arrayList2.add(mo7111deserialize2);
            return arrayList2;
        }
        ObjectBuffer leaseObjectBuffer = deserializationContext.leaseObjectBuffer();
        Object[] resetAndStart = leaseObjectBuffer.resetAndStart();
        resetAndStart[0] = mo7111deserialize;
        resetAndStart[1] = mo7111deserialize2;
        int i2 = 2;
        while (true) {
            Object mo7111deserialize3 = mo7111deserialize(jsonParser, deserializationContext);
            i++;
            if (i2 >= resetAndStart.length) {
                resetAndStart = leaseObjectBuffer.appendCompletedChunk(resetAndStart);
                i2 = 0;
            }
            int i3 = i2 + 1;
            resetAndStart[i2] = mo7111deserialize3;
            if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                ArrayList arrayList3 = new ArrayList(i);
                leaseObjectBuffer.completeAndClearBuffer(resetAndStart, i3, arrayList3);
                return arrayList3;
            }
            i2 = i3;
        }
    }

    protected Object[] mapArrayToArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
            return NO_OBJECTS;
        }
        ObjectBuffer leaseObjectBuffer = deserializationContext.leaseObjectBuffer();
        Object[] resetAndStart = leaseObjectBuffer.resetAndStart();
        int i = 0;
        while (true) {
            Object mo7111deserialize = mo7111deserialize(jsonParser, deserializationContext);
            if (i >= resetAndStart.length) {
                resetAndStart = leaseObjectBuffer.appendCompletedChunk(resetAndStart);
                i = 0;
            }
            int i2 = i + 1;
            resetAndStart[i] = mo7111deserialize;
            if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                return leaseObjectBuffer.completeAndClearBuffer(resetAndStart, i2);
            }
            i = i2;
        }
    }

    protected Object mapObject(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String str;
        JsonToken currentToken = jsonParser.currentToken();
        if (currentToken == JsonToken.START_OBJECT) {
            str = jsonParser.nextFieldName();
        } else if (currentToken == JsonToken.FIELD_NAME) {
            str = jsonParser.currentName();
        } else if (currentToken != JsonToken.END_OBJECT) {
            return deserializationContext.handleUnexpectedToken(handledType(), jsonParser);
        } else {
            str = null;
        }
        String str2 = str;
        if (str2 == null) {
            return new LinkedHashMap(2);
        }
        jsonParser.nextToken();
        Object mo7111deserialize = mo7111deserialize(jsonParser, deserializationContext);
        String nextFieldName = jsonParser.nextFieldName();
        if (nextFieldName == null) {
            LinkedHashMap linkedHashMap = new LinkedHashMap(2);
            linkedHashMap.put(str2, mo7111deserialize);
            return linkedHashMap;
        }
        jsonParser.nextToken();
        Object mo7111deserialize2 = mo7111deserialize(jsonParser, deserializationContext);
        String nextFieldName2 = jsonParser.nextFieldName();
        if (nextFieldName2 == null) {
            LinkedHashMap linkedHashMap2 = new LinkedHashMap(4);
            linkedHashMap2.put(str2, mo7111deserialize);
            return linkedHashMap2.put(nextFieldName, mo7111deserialize2) != null ? _mapObjectWithDups(jsonParser, deserializationContext, linkedHashMap2, str2, mo7111deserialize, mo7111deserialize2, nextFieldName2) : linkedHashMap2;
        }
        LinkedHashMap linkedHashMap3 = new LinkedHashMap();
        linkedHashMap3.put(str2, mo7111deserialize);
        if (linkedHashMap3.put(nextFieldName, mo7111deserialize2) != null) {
            return _mapObjectWithDups(jsonParser, deserializationContext, linkedHashMap3, str2, mo7111deserialize, mo7111deserialize2, nextFieldName2);
        }
        do {
            jsonParser.nextToken();
            Object mo7111deserialize3 = mo7111deserialize(jsonParser, deserializationContext);
            Object put = linkedHashMap3.put(nextFieldName2, mo7111deserialize3);
            if (put != null) {
                return _mapObjectWithDups(jsonParser, deserializationContext, linkedHashMap3, nextFieldName2, put, mo7111deserialize3, jsonParser.nextFieldName());
            }
            nextFieldName2 = jsonParser.nextFieldName();
        } while (nextFieldName2 != null);
        return linkedHashMap3;
    }

    @Override // com.fasterxml.jackson.databind.deser.ResolvableDeserializer
    public void resolve(DeserializationContext deserializationContext) throws JsonMappingException {
        JavaType constructType = deserializationContext.constructType(Object.class);
        JavaType constructType2 = deserializationContext.constructType(String.class);
        TypeFactory typeFactory = deserializationContext.getTypeFactory();
        JavaType javaType = this._listType;
        if (javaType == null) {
            this._listDeserializer = _clearIfStdImpl(_findCustomDeser(deserializationContext, typeFactory.constructCollectionType(List.class, constructType)));
        } else {
            this._listDeserializer = _findCustomDeser(deserializationContext, javaType);
        }
        JavaType javaType2 = this._mapType;
        if (javaType2 == null) {
            this._mapDeserializer = _clearIfStdImpl(_findCustomDeser(deserializationContext, typeFactory.constructMapType(Map.class, constructType2, constructType)));
        } else {
            this._mapDeserializer = _findCustomDeser(deserializationContext, javaType2);
        }
        this._stringDeserializer = _clearIfStdImpl(_findCustomDeser(deserializationContext, constructType2));
        this._numberDeserializer = _clearIfStdImpl(_findCustomDeser(deserializationContext, typeFactory.constructType(Number.class)));
        JavaType unknownType = TypeFactory.unknownType();
        this._mapDeserializer = deserializationContext.handleSecondaryContextualization(this._mapDeserializer, null, unknownType);
        this._listDeserializer = deserializationContext.handleSecondaryContextualization(this._listDeserializer, null, unknownType);
        this._stringDeserializer = deserializationContext.handleSecondaryContextualization(this._stringDeserializer, null, unknownType);
        this._numberDeserializer = deserializationContext.handleSecondaryContextualization(this._numberDeserializer, null, unknownType);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public Boolean supportsUpdate(DeserializationConfig deserializationConfig) {
        return null;
    }

    public UntypedObjectDeserializer(JavaType javaType, JavaType javaType2) {
        super(Object.class);
        this._listType = javaType;
        this._mapType = javaType2;
        this._nonMerging = false;
    }

    public UntypedObjectDeserializer(UntypedObjectDeserializer untypedObjectDeserializer, JsonDeserializer<?> jsonDeserializer, JsonDeserializer<?> jsonDeserializer2, JsonDeserializer<?> jsonDeserializer3, JsonDeserializer<?> jsonDeserializer4) {
        super(Object.class);
        this._mapDeserializer = jsonDeserializer;
        this._listDeserializer = jsonDeserializer2;
        this._stringDeserializer = jsonDeserializer3;
        this._numberDeserializer = jsonDeserializer4;
        this._listType = untypedObjectDeserializer._listType;
        this._mapType = untypedObjectDeserializer._mapType;
        this._nonMerging = untypedObjectDeserializer._nonMerging;
    }

    protected UntypedObjectDeserializer(UntypedObjectDeserializer untypedObjectDeserializer, boolean z) {
        super(Object.class);
        this._mapDeserializer = untypedObjectDeserializer._mapDeserializer;
        this._listDeserializer = untypedObjectDeserializer._listDeserializer;
        this._stringDeserializer = untypedObjectDeserializer._stringDeserializer;
        this._numberDeserializer = untypedObjectDeserializer._numberDeserializer;
        this._listType = untypedObjectDeserializer._listType;
        this._mapType = untypedObjectDeserializer._mapType;
        this._nonMerging = z;
    }

    protected Object mapArray(JsonParser jsonParser, DeserializationContext deserializationContext, Collection<Object> collection) throws IOException {
        while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
            collection.add(mo7111deserialize(jsonParser, deserializationContext));
        }
        return collection;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException {
        if (this._nonMerging) {
            return mo7111deserialize(jsonParser, deserializationContext);
        }
        switch (jsonParser.currentTokenId()) {
            case 1:
            case 2:
            case 5:
                JsonDeserializer<Object> jsonDeserializer = this._mapDeserializer;
                if (jsonDeserializer != null) {
                    return jsonDeserializer.deserialize(jsonParser, deserializationContext, obj);
                }
                if (obj instanceof Map) {
                    return mapObject(jsonParser, deserializationContext, (Map) obj);
                }
                return mapObject(jsonParser, deserializationContext);
            case 3:
                JsonDeserializer<Object> jsonDeserializer2 = this._listDeserializer;
                if (jsonDeserializer2 != null) {
                    return jsonDeserializer2.deserialize(jsonParser, deserializationContext, obj);
                }
                if (obj instanceof Collection) {
                    return mapArray(jsonParser, deserializationContext, (Collection) obj);
                }
                if (deserializationContext.isEnabled(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY)) {
                    return mapArrayToArray(jsonParser, deserializationContext);
                }
                return mapArray(jsonParser, deserializationContext);
            case 4:
            default:
                return mo7111deserialize(jsonParser, deserializationContext);
            case 6:
                JsonDeserializer<Object> jsonDeserializer3 = this._stringDeserializer;
                if (jsonDeserializer3 != null) {
                    return jsonDeserializer3.deserialize(jsonParser, deserializationContext, obj);
                }
                return jsonParser.getText();
            case 7:
                JsonDeserializer<Object> jsonDeserializer4 = this._numberDeserializer;
                if (jsonDeserializer4 != null) {
                    return jsonDeserializer4.deserialize(jsonParser, deserializationContext, obj);
                }
                if (deserializationContext.hasSomeOfFeatures(StdDeserializer.F_MASK_INT_COERCIONS)) {
                    return _coerceIntegral(jsonParser, deserializationContext);
                }
                return jsonParser.getNumberValue();
            case 8:
                JsonDeserializer<Object> jsonDeserializer5 = this._numberDeserializer;
                if (jsonDeserializer5 != null) {
                    return jsonDeserializer5.deserialize(jsonParser, deserializationContext, obj);
                }
                if (deserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
                    return jsonParser.getDecimalValue();
                }
                return jsonParser.getNumberValue();
            case 9:
                return Boolean.TRUE;
            case 10:
                return Boolean.FALSE;
            case 11:
                return null;
            case 12:
                return jsonParser.getEmbeddedObject();
        }
    }

    protected Object mapObject(JsonParser jsonParser, DeserializationContext deserializationContext, Map<Object, Object> map) throws IOException {
        Object mo7111deserialize;
        JsonToken currentToken = jsonParser.currentToken();
        if (currentToken == JsonToken.START_OBJECT) {
            currentToken = jsonParser.nextToken();
        }
        if (currentToken == JsonToken.END_OBJECT) {
            return map;
        }
        String currentName = jsonParser.currentName();
        do {
            jsonParser.nextToken();
            Object obj = map.get(currentName);
            if (obj != null) {
                mo7111deserialize = deserialize(jsonParser, deserializationContext, obj);
            } else {
                mo7111deserialize = mo7111deserialize(jsonParser, deserializationContext);
            }
            if (mo7111deserialize != obj) {
                map.put(currentName, mo7111deserialize);
            }
            currentName = jsonParser.nextFieldName();
        } while (currentName != null);
        return map;
    }
}
