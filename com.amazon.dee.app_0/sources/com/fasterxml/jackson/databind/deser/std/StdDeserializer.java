package com.fasterxml.jackson.databind.deser.std;

import com.adobe.xmp.XMPConst;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.StreamReadCapability;
import com.fasterxml.jackson.core.exc.InputCoercionException;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.cfg.CoercionAction;
import com.fasterxml.jackson.databind.cfg.CoercionInputShape;
import com.fasterxml.jackson.databind.deser.BeanDeserializerBase;
import com.fasterxml.jackson.databind.deser.NullValueProvider;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.impl.NullsAsEmptyProvider;
import com.fasterxml.jackson.databind.deser.impl.NullsConstantProvider;
import com.fasterxml.jackson.databind.deser.impl.NullsFailProvider;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.AccessPattern;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
/* loaded from: classes2.dex */
public abstract class StdDeserializer<T> extends JsonDeserializer<T> implements Serializable, ValueInstantiator.Gettable {
    private static final long serialVersionUID = 1;
    protected final Class<?> _valueClass;
    protected final JavaType _valueType;
    protected static final int F_MASK_INT_COERCIONS = DeserializationFeature.USE_BIG_INTEGER_FOR_INTS.getMask() | DeserializationFeature.USE_LONG_FOR_INTS.getMask();
    @Deprecated
    protected static final int F_MASK_ACCEPT_ARRAYS = DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS.getMask() | DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT.getMask();

    /* renamed from: com.fasterxml.jackson.databind.deser.std.StdDeserializer$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$fasterxml$jackson$databind$cfg$CoercionAction = new int[CoercionAction.values().length];

        static {
            try {
                $SwitchMap$com$fasterxml$jackson$databind$cfg$CoercionAction[CoercionAction.AsEmpty.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$databind$cfg$CoercionAction[CoercionAction.AsNull.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$databind$cfg$CoercionAction[CoercionAction.TryConvert.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$databind$cfg$CoercionAction[CoercionAction.Fail.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public StdDeserializer(Class<?> cls) {
        this._valueClass = cls;
        this._valueType = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static final boolean _isBlank(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (str.charAt(i) > ' ') {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static final boolean _neitherNull(Object obj, Object obj2) {
        return (obj == null || obj2 == null) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static final double _parseDouble(String str) throws NumberFormatException {
        if ("2.2250738585072012e-308".equals(str)) {
            return Double.MIN_NORMAL;
        }
        return Double.parseDouble(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean _byteOverflow(int i) {
        return i < -128 || i > 255;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CoercionAction _checkCoercionFail(DeserializationContext deserializationContext, CoercionAction coercionAction, Class<?> cls, Object obj, String str) throws IOException {
        if (coercionAction == CoercionAction.Fail) {
            deserializationContext.reportBadCoercion(this, cls, obj, "Cannot coerce %s to %s (but could if coercion was enabled using `CoercionConfig`)", str, _coercedTypeDesc());
        }
        return coercionAction;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Double _checkDoubleSpecialValue(String str) {
        if (!str.isEmpty()) {
            char charAt = str.charAt(0);
            if (charAt == '-') {
                if (!_isNegInf(str)) {
                    return null;
                }
                return Double.valueOf(Double.NEGATIVE_INFINITY);
            } else if (charAt != 'I') {
                if (charAt != 'N' || !_isNaN(str)) {
                    return null;
                }
                return Double.valueOf(Double.NaN);
            } else if (!_isPosInf(str)) {
                return null;
            } else {
                return Double.valueOf(Double.POSITIVE_INFINITY);
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Float _checkFloatSpecialValue(String str) {
        if (!str.isEmpty()) {
            char charAt = str.charAt(0);
            if (charAt == '-') {
                if (!_isNegInf(str)) {
                    return null;
                }
                return Float.valueOf(Float.NEGATIVE_INFINITY);
            } else if (charAt != 'I') {
                if (charAt != 'N' || !_isNaN(str)) {
                    return null;
                }
                return Float.valueOf(Float.NaN);
            } else if (!_isPosInf(str)) {
                return null;
            } else {
                return Float.valueOf(Float.POSITIVE_INFINITY);
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CoercionAction _checkFloatToIntCoercion(JsonParser jsonParser, DeserializationContext deserializationContext, Class<?> cls) throws IOException {
        CoercionAction findCoercionAction = deserializationContext.findCoercionAction(LogicalType.Integer, cls, CoercionInputShape.Float);
        if (findCoercionAction == CoercionAction.Fail) {
            Number numberValue = jsonParser.getNumberValue();
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Floating-point value (");
            outline107.append(jsonParser.getText());
            outline107.append(")");
            return _checkCoercionFail(deserializationContext, findCoercionAction, cls, numberValue, outline107.toString());
        }
        return findCoercionAction;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CoercionAction _checkFromStringCoercion(DeserializationContext deserializationContext, String str) throws IOException {
        return _checkFromStringCoercion(deserializationContext, str, logicalType(), handledType());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean _checkTextualNull(DeserializationContext deserializationContext, String str) throws JsonMappingException {
        if (_hasTextualNull(str)) {
            if (!deserializationContext.isEnabled(MapperFeature.ALLOW_COERCION_OF_SCALARS)) {
                _reportFailedNullCoerce(deserializationContext, true, MapperFeature.ALLOW_COERCION_OF_SCALARS, "String \"null\"");
            }
            return true;
        }
        return false;
    }

    protected Boolean _coerceBooleanFromInt(JsonParser jsonParser, DeserializationContext deserializationContext, Class<?> cls) throws IOException {
        CoercionAction findCoercionAction = deserializationContext.findCoercionAction(LogicalType.Boolean, cls, CoercionInputShape.Integer);
        int ordinal = findCoercionAction.ordinal();
        if (ordinal == 0) {
            Number numberValue = jsonParser.getNumberValue();
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Integer value (");
            outline107.append(jsonParser.getText());
            outline107.append(")");
            _checkCoercionFail(deserializationContext, findCoercionAction, cls, numberValue, outline107.toString());
            return Boolean.FALSE;
        } else if (ordinal == 2) {
            return null;
        } else {
            if (ordinal != 3) {
                boolean z = true;
                if (jsonParser.getNumberType() == JsonParser.NumberType.INT) {
                    if (jsonParser.getIntValue() == 0) {
                        z = false;
                    }
                    return Boolean.valueOf(z);
                }
                return Boolean.valueOf(!"0".equals(jsonParser.getText()));
            }
            return Boolean.FALSE;
        }
    }

    @Deprecated
    protected Object _coerceEmptyString(DeserializationContext deserializationContext, boolean z) throws JsonMappingException {
        Enum<?> r4;
        boolean z2;
        if (!deserializationContext.isEnabled(MapperFeature.ALLOW_COERCION_OF_SCALARS)) {
            r4 = MapperFeature.ALLOW_COERCION_OF_SCALARS;
            z2 = true;
        } else if (z && deserializationContext.isEnabled(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES)) {
            r4 = DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES;
            z2 = false;
        } else {
            return mo7075getNullValue(deserializationContext);
        }
        _reportFailedNullCoerce(deserializationContext, z2, r4, "empty String (\"\")");
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object _coerceIntegral(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        int deserializationFeatures = deserializationContext.getDeserializationFeatures();
        if (DeserializationFeature.USE_BIG_INTEGER_FOR_INTS.enabledIn(deserializationFeatures)) {
            return jsonParser.getBigIntegerValue();
        }
        if (DeserializationFeature.USE_LONG_FOR_INTS.enabledIn(deserializationFeatures)) {
            return Long.valueOf(jsonParser.getLongValue());
        }
        return jsonParser.getNumberValue();
    }

    @Deprecated
    protected Object _coerceNullToken(DeserializationContext deserializationContext, boolean z) throws JsonMappingException {
        if (z) {
            _verifyNullForPrimitive(deserializationContext);
        }
        return mo7075getNullValue(deserializationContext);
    }

    @Deprecated
    protected Object _coerceTextualNull(DeserializationContext deserializationContext, boolean z) throws JsonMappingException {
        if (!deserializationContext.isEnabled(MapperFeature.ALLOW_COERCION_OF_SCALARS)) {
            _reportFailedNullCoerce(deserializationContext, true, MapperFeature.ALLOW_COERCION_OF_SCALARS, "String \"null\"");
        }
        return mo7075getNullValue(deserializationContext);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String _coercedTypeDesc() {
        String classDescription;
        JavaType valueType = getValueType();
        boolean z = false;
        if (valueType != null && !valueType.isPrimitive()) {
            if (valueType.isContainerType() || valueType.isReferenceType()) {
                z = true;
            }
            classDescription = ClassUtil.getTypeDescription(valueType);
        } else {
            Class<?> handledType = handledType();
            if (handledType.isArray() || Collection.class.isAssignableFrom(handledType) || Map.class.isAssignableFrom(handledType)) {
                z = true;
            }
            classDescription = ClassUtil.getClassDescription(handledType);
        }
        if (z) {
            return GeneratedOutlineSupport1.outline72("element of ", classDescription);
        }
        return GeneratedOutlineSupport1.outline72(classDescription, " value");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public T _deserializeFromArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        CoercionAction _findCoercionFromEmptyArray = _findCoercionFromEmptyArray(deserializationContext);
        boolean isEnabled = deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS);
        if (isEnabled || _findCoercionFromEmptyArray != CoercionAction.Fail) {
            if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                int ordinal = _findCoercionFromEmptyArray.ordinal();
                if (ordinal == 1 || ordinal == 2) {
                    return mo7075getNullValue(deserializationContext);
                }
                if (ordinal == 3) {
                    return (T) getEmptyValue(deserializationContext);
                }
            } else if (isEnabled) {
                T _deserializeWrappedValue = _deserializeWrappedValue(jsonParser, deserializationContext);
                if (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                    handleMissingEndArrayForSingle(jsonParser, deserializationContext);
                }
                return _deserializeWrappedValue;
            }
        }
        return (T) deserializationContext.handleUnexpectedToken(getValueType(deserializationContext), JsonToken.START_ARRAY, jsonParser, (String) null, new Object[0]);
    }

    @Deprecated
    protected T _deserializeFromEmpty(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (jsonParser.hasToken(JsonToken.START_ARRAY) && deserializationContext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT)) {
            if (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                return (T) deserializationContext.handleUnexpectedToken(getValueType(deserializationContext), jsonParser);
            }
            return null;
        }
        return (T) deserializationContext.handleUnexpectedToken(getValueType(deserializationContext), jsonParser);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object _deserializeFromEmptyString(JsonParser jsonParser, DeserializationContext deserializationContext, CoercionAction coercionAction, Class<?> cls, String str) throws IOException {
        int ordinal = coercionAction.ordinal();
        if (ordinal == 0) {
            _checkCoercionFail(deserializationContext, coercionAction, cls, "", "empty String (\"\")");
            return null;
        } else if (ordinal == 3) {
            return getEmptyValue(deserializationContext);
        } else {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public T _deserializeFromString(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ValueInstantiator valueInstantiator = getValueInstantiator();
        Class<?> handledType = handledType();
        String valueAsString = jsonParser.getValueAsString();
        if (valueInstantiator != null && valueInstantiator.canCreateFromString()) {
            return (T) valueInstantiator.createFromString(deserializationContext, valueAsString);
        }
        if (valueAsString.isEmpty()) {
            return (T) _deserializeFromEmptyString(jsonParser, deserializationContext, deserializationContext.findCoercionAction(logicalType(), handledType, CoercionInputShape.EmptyString), handledType, "empty String (\"\")");
        }
        if (_isBlank(valueAsString)) {
            return (T) _deserializeFromEmptyString(jsonParser, deserializationContext, deserializationContext.findCoercionFromBlankString(logicalType(), handledType, CoercionAction.Fail), handledType, "blank String (all whitespace)");
        }
        if (valueInstantiator != null) {
            valueAsString = valueAsString.trim();
            if (valueInstantiator.canCreateFromInt() && deserializationContext.findCoercionAction(LogicalType.Integer, Integer.class, CoercionInputShape.String) == CoercionAction.TryConvert) {
                return (T) valueInstantiator.createFromInt(deserializationContext, _parseIntPrimitive(deserializationContext, valueAsString));
            }
            if (valueInstantiator.canCreateFromLong() && deserializationContext.findCoercionAction(LogicalType.Integer, Long.class, CoercionInputShape.String) == CoercionAction.TryConvert) {
                return (T) valueInstantiator.createFromLong(deserializationContext, _parseLongPrimitive(deserializationContext, valueAsString));
            }
            if (valueInstantiator.canCreateFromBoolean() && deserializationContext.findCoercionAction(LogicalType.Boolean, Boolean.class, CoercionInputShape.String) == CoercionAction.TryConvert) {
                String trim = valueAsString.trim();
                if ("true".equals(trim)) {
                    return (T) valueInstantiator.createFromBoolean(deserializationContext, true);
                }
                if (PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE.equals(trim)) {
                    return (T) valueInstantiator.createFromBoolean(deserializationContext, false);
                }
            }
        }
        return (T) deserializationContext.handleMissingInstantiator(handledType, valueInstantiator, deserializationContext.getParser(), "no String-argument constructor/factory method to deserialize from String value ('%s')", valueAsString);
    }

    protected T _deserializeWrappedValue(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (jsonParser.hasToken(JsonToken.START_ARRAY)) {
            return (T) deserializationContext.handleUnexpectedToken(getValueType(deserializationContext), jsonParser.currentToken(), jsonParser, String.format("Cannot deserialize instance of %s out of %s token: nested Arrays not allowed with %s", ClassUtil.nameOf(this._valueClass), JsonToken.START_ARRAY, "DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS"), new Object[0]);
        }
        return mo7111deserialize(jsonParser, deserializationContext);
    }

    @Deprecated
    protected void _failDoubleToIntCoercion(JsonParser jsonParser, DeserializationContext deserializationContext, String str) throws IOException {
        deserializationContext.reportInputMismatch(handledType(), "Cannot coerce a floating-point value ('%s') into %s (enable `DeserializationFeature.ACCEPT_FLOAT_AS_INT` to allow)", jsonParser.getValueAsString(), str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CoercionAction _findCoercionFromBlankString(DeserializationContext deserializationContext) {
        return deserializationContext.findCoercionFromBlankString(logicalType(), handledType(), CoercionAction.Fail);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CoercionAction _findCoercionFromEmptyArray(DeserializationContext deserializationContext) {
        return deserializationContext.findCoercionAction(logicalType(), handledType(), CoercionInputShape.EmptyArray);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CoercionAction _findCoercionFromEmptyString(DeserializationContext deserializationContext) {
        return deserializationContext.findCoercionAction(logicalType(), handledType(), CoercionInputShape.EmptyString);
    }

    protected final NullValueProvider _findNullProvider(DeserializationContext deserializationContext, BeanProperty beanProperty, Nulls nulls, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        if (nulls == Nulls.FAIL) {
            if (beanProperty == null) {
                return NullsFailProvider.constructForRootValue(deserializationContext.constructType(jsonDeserializer.handledType()));
            }
            return NullsFailProvider.constructForProperty(beanProperty);
        } else if (nulls != Nulls.AS_EMPTY) {
            if (nulls != Nulls.SKIP) {
                return null;
            }
            return NullsConstantProvider.skipper();
        } else if (jsonDeserializer == null) {
            return null;
        } else {
            if ((jsonDeserializer instanceof BeanDeserializerBase) && !((BeanDeserializerBase) jsonDeserializer).getValueInstantiator().canCreateUsingDefault()) {
                JavaType type = beanProperty.getType();
                deserializationContext.reportBadDefinition(type, String.format("Cannot create empty instance of %s, no default Creator", type));
            }
            AccessPattern emptyAccessPattern = jsonDeserializer.getEmptyAccessPattern();
            if (emptyAccessPattern == AccessPattern.ALWAYS_NULL) {
                return NullsConstantProvider.nuller();
            }
            if (emptyAccessPattern == AccessPattern.CONSTANT) {
                return NullsConstantProvider.forValue(jsonDeserializer.getEmptyValue(deserializationContext));
            }
            return new NullsAsEmptyProvider(jsonDeserializer);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean _hasTextualNull(String str) {
        return "null".equals(str);
    }

    protected final boolean _intOverflow(long j) {
        return j < -2147483648L || j > 2147483647L;
    }

    @Deprecated
    protected boolean _isEmptyOrTextualNull(String str) {
        return str.isEmpty() || "null".equals(str);
    }

    protected boolean _isFalse(String str) {
        char charAt = str.charAt(0);
        if (charAt == 'f') {
            return PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE.equals(str);
        }
        if (charAt != 'F') {
            return false;
        }
        return "FALSE".equals(str) || XMPConst.FALSESTR.equals(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean _isIntNumber(String str) {
        int i;
        int length = str.length();
        if (length > 0) {
            char charAt = str.charAt(0);
            if (charAt != '-' && charAt != '+') {
                i = 0;
            } else if (length == 1) {
                return false;
            } else {
                i = 1;
            }
            while (i < length) {
                char charAt2 = str.charAt(i);
                if (charAt2 > '9' || charAt2 < '0') {
                    return false;
                }
                i++;
            }
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean _isNaN(String str) {
        return "NaN".equals(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean _isNegInf(String str) {
        return "-Infinity".equals(str) || "-INF".equals(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean _isPosInf(String str) {
        return "Infinity".equals(str) || "INF".equals(str);
    }

    protected boolean _isTrue(String str) {
        char charAt = str.charAt(0);
        if (charAt == 't') {
            return "true".equals(str);
        }
        if (charAt != 'T') {
            return false;
        }
        return "TRUE".equals(str) || XMPConst.TRUESTR.equals(str);
    }

    protected Number _nonNullNumber(Number number) {
        if (number == null) {
            return 0;
        }
        return number;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Boolean _parseBoolean(JsonParser jsonParser, DeserializationContext deserializationContext, Class<?> cls) throws IOException {
        String extractScalarFromObject;
        int currentTokenId = jsonParser.currentTokenId();
        if (currentTokenId == 1) {
            extractScalarFromObject = deserializationContext.extractScalarFromObject(jsonParser, this, cls);
        } else if (currentTokenId == 3) {
            return (Boolean) _deserializeFromArray(jsonParser, deserializationContext);
        } else {
            if (currentTokenId != 6) {
                if (currentTokenId != 7) {
                    switch (currentTokenId) {
                        case 9:
                            return true;
                        case 10:
                            return false;
                        case 11:
                            return null;
                        default:
                            return (Boolean) deserializationContext.handleUnexpectedToken(cls, jsonParser);
                    }
                }
                return _coerceBooleanFromInt(jsonParser, deserializationContext, cls);
            }
            extractScalarFromObject = jsonParser.getText();
        }
        CoercionAction _checkFromStringCoercion = _checkFromStringCoercion(deserializationContext, extractScalarFromObject, LogicalType.Boolean, cls);
        if (_checkFromStringCoercion == CoercionAction.AsNull) {
            return null;
        }
        if (_checkFromStringCoercion == CoercionAction.AsEmpty) {
            return false;
        }
        String trim = extractScalarFromObject.trim();
        int length = trim.length();
        if (length == 4) {
            if (_isTrue(trim)) {
                return true;
            }
        } else if (length == 5 && _isFalse(trim)) {
            return false;
        }
        if (!_checkTextualNull(deserializationContext, trim)) {
            return (Boolean) deserializationContext.handleWeirdStringValue(cls, trim, "only \"true\" or \"false\" recognized", new Object[0]);
        }
        return null;
    }

    @Deprecated
    protected boolean _parseBooleanFromInt(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        _verifyNumberForScalarCoercion(deserializationContext, jsonParser);
        return !"0".equals(jsonParser.getText());
    }

    @Deprecated
    protected final boolean _parseBooleanPrimitive(DeserializationContext deserializationContext, JsonParser jsonParser, Class<?> cls) throws IOException {
        return _parseBooleanPrimitive(jsonParser, deserializationContext);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final byte _parseBytePrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String extractScalarFromObject;
        int currentTokenId = jsonParser.currentTokenId();
        if (currentTokenId != 1) {
            if (currentTokenId != 3) {
                if (currentTokenId == 11) {
                    _verifyNullForPrimitive(deserializationContext);
                    return (byte) 0;
                } else if (currentTokenId == 6) {
                    extractScalarFromObject = jsonParser.getText();
                } else if (currentTokenId == 7) {
                    return jsonParser.getByteValue();
                } else {
                    if (currentTokenId == 8) {
                        CoercionAction _checkFloatToIntCoercion = _checkFloatToIntCoercion(jsonParser, deserializationContext, Byte.TYPE);
                        if (_checkFloatToIntCoercion != CoercionAction.AsNull && _checkFloatToIntCoercion != CoercionAction.AsEmpty) {
                            return jsonParser.getByteValue();
                        }
                        return (byte) 0;
                    }
                }
            } else if (deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                jsonParser.nextToken();
                byte _parseBytePrimitive = _parseBytePrimitive(jsonParser, deserializationContext);
                _verifyEndArrayForSingle(jsonParser, deserializationContext);
                return _parseBytePrimitive;
            }
            return ((Byte) deserializationContext.handleUnexpectedToken(deserializationContext.constructType(Byte.TYPE), jsonParser)).byteValue();
        }
        extractScalarFromObject = deserializationContext.extractScalarFromObject(jsonParser, this, Byte.TYPE);
        CoercionAction _checkFromStringCoercion = _checkFromStringCoercion(deserializationContext, extractScalarFromObject, LogicalType.Integer, Byte.TYPE);
        if (_checkFromStringCoercion == CoercionAction.AsNull || _checkFromStringCoercion == CoercionAction.AsEmpty) {
            return (byte) 0;
        }
        String trim = extractScalarFromObject.trim();
        if (_hasTextualNull(trim)) {
            _verifyNullForPrimitiveCoercion(deserializationContext, trim);
            return (byte) 0;
        }
        try {
            int parseInt = NumberInput.parseInt(trim);
            return _byteOverflow(parseInt) ? ((Byte) deserializationContext.handleWeirdStringValue(this._valueClass, trim, "overflow, value cannot be represented as 8-bit value", new Object[0])).byteValue() : (byte) parseInt;
        } catch (IllegalArgumentException unused) {
            return ((Byte) deserializationContext.handleWeirdStringValue(this._valueClass, trim, "not a valid `byte` value", new Object[0])).byteValue();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Date _parseDate(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String extractScalarFromObject;
        long longValue;
        int currentTokenId = jsonParser.currentTokenId();
        if (currentTokenId == 1) {
            extractScalarFromObject = deserializationContext.extractScalarFromObject(jsonParser, this, this._valueClass);
        } else if (currentTokenId == 3) {
            return _parseDateFromArray(jsonParser, deserializationContext);
        } else {
            if (currentTokenId == 11) {
                return (Date) mo7075getNullValue(deserializationContext);
            }
            if (currentTokenId != 6) {
                if (currentTokenId != 7) {
                    return (Date) deserializationContext.handleUnexpectedToken(this._valueClass, jsonParser);
                }
                try {
                    longValue = jsonParser.getLongValue();
                } catch (JsonParseException | InputCoercionException unused) {
                    longValue = ((Number) deserializationContext.handleWeirdNumberValue(this._valueClass, jsonParser.getNumberValue(), "not a valid 64-bit `long` for creating `java.util.Date`", new Object[0])).longValue();
                }
                return new Date(longValue);
            }
            extractScalarFromObject = jsonParser.getText();
        }
        return _parseDate(extractScalarFromObject.trim(), deserializationContext);
    }

    protected Date _parseDateFromArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        CoercionAction _findCoercionFromEmptyArray = _findCoercionFromEmptyArray(deserializationContext);
        boolean isEnabled = deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS);
        if (isEnabled || _findCoercionFromEmptyArray != CoercionAction.Fail) {
            if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                int ordinal = _findCoercionFromEmptyArray.ordinal();
                if (ordinal == 1 || ordinal == 2) {
                    return (Date) mo7075getNullValue(deserializationContext);
                }
                if (ordinal == 3) {
                    return (Date) getEmptyValue(deserializationContext);
                }
            } else if (isEnabled) {
                Date _parseDate = _parseDate(jsonParser, deserializationContext);
                _verifyEndArrayForSingle(jsonParser, deserializationContext);
                return _parseDate;
            }
        }
        return (Date) deserializationContext.handleUnexpectedToken(this._valueClass, JsonToken.START_ARRAY, jsonParser, (String) null, new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final double _parseDoublePrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String extractScalarFromObject;
        int currentTokenId = jsonParser.currentTokenId();
        if (currentTokenId != 1) {
            if (currentTokenId != 3) {
                if (currentTokenId == 11) {
                    _verifyNullForPrimitive(deserializationContext);
                    return FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
                } else if (currentTokenId == 6) {
                    extractScalarFromObject = jsonParser.getText();
                } else if (currentTokenId == 7 || currentTokenId == 8) {
                    return jsonParser.getDoubleValue();
                }
            } else if (deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                jsonParser.nextToken();
                double _parseDoublePrimitive = _parseDoublePrimitive(jsonParser, deserializationContext);
                _verifyEndArrayForSingle(jsonParser, deserializationContext);
                return _parseDoublePrimitive;
            }
            return ((Number) deserializationContext.handleUnexpectedToken(Double.TYPE, jsonParser)).doubleValue();
        }
        extractScalarFromObject = deserializationContext.extractScalarFromObject(jsonParser, this, Double.TYPE);
        Double _checkDoubleSpecialValue = _checkDoubleSpecialValue(extractScalarFromObject);
        if (_checkDoubleSpecialValue != null) {
            return _checkDoubleSpecialValue.doubleValue();
        }
        CoercionAction _checkFromStringCoercion = _checkFromStringCoercion(deserializationContext, extractScalarFromObject, LogicalType.Integer, Double.TYPE);
        if (_checkFromStringCoercion == CoercionAction.AsNull || _checkFromStringCoercion == CoercionAction.AsEmpty) {
            return FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        }
        String trim = extractScalarFromObject.trim();
        if (_hasTextualNull(trim)) {
            _verifyNullForPrimitiveCoercion(deserializationContext, trim);
            return FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        }
        return _parseDoublePrimitive(deserializationContext, trim);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final float _parseFloatPrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String extractScalarFromObject;
        int currentTokenId = jsonParser.currentTokenId();
        if (currentTokenId != 1) {
            if (currentTokenId != 3) {
                if (currentTokenId == 11) {
                    _verifyNullForPrimitive(deserializationContext);
                    return 0.0f;
                } else if (currentTokenId == 6) {
                    extractScalarFromObject = jsonParser.getText();
                } else if (currentTokenId == 7 || currentTokenId == 8) {
                    return jsonParser.getFloatValue();
                }
            } else if (deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                jsonParser.nextToken();
                float _parseFloatPrimitive = _parseFloatPrimitive(jsonParser, deserializationContext);
                _verifyEndArrayForSingle(jsonParser, deserializationContext);
                return _parseFloatPrimitive;
            }
            return ((Number) deserializationContext.handleUnexpectedToken(Float.TYPE, jsonParser)).floatValue();
        }
        extractScalarFromObject = deserializationContext.extractScalarFromObject(jsonParser, this, Float.TYPE);
        Float _checkFloatSpecialValue = _checkFloatSpecialValue(extractScalarFromObject);
        if (_checkFloatSpecialValue != null) {
            return _checkFloatSpecialValue.floatValue();
        }
        CoercionAction _checkFromStringCoercion = _checkFromStringCoercion(deserializationContext, extractScalarFromObject, LogicalType.Integer, Float.TYPE);
        if (_checkFromStringCoercion == CoercionAction.AsNull || _checkFromStringCoercion == CoercionAction.AsEmpty) {
            return 0.0f;
        }
        String trim = extractScalarFromObject.trim();
        if (_hasTextualNull(trim)) {
            _verifyNullForPrimitiveCoercion(deserializationContext, trim);
            return 0.0f;
        }
        return _parseFloatPrimitive(deserializationContext, trim);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int _parseIntPrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String extractScalarFromObject;
        int currentTokenId = jsonParser.currentTokenId();
        if (currentTokenId != 1) {
            if (currentTokenId != 3) {
                if (currentTokenId == 11) {
                    _verifyNullForPrimitive(deserializationContext);
                    return 0;
                } else if (currentTokenId == 6) {
                    extractScalarFromObject = jsonParser.getText();
                } else if (currentTokenId == 7) {
                    return jsonParser.getIntValue();
                } else {
                    if (currentTokenId == 8) {
                        CoercionAction _checkFloatToIntCoercion = _checkFloatToIntCoercion(jsonParser, deserializationContext, Integer.TYPE);
                        if (_checkFloatToIntCoercion != CoercionAction.AsNull && _checkFloatToIntCoercion != CoercionAction.AsEmpty) {
                            return jsonParser.getValueAsInt();
                        }
                        return 0;
                    }
                }
            } else if (deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                jsonParser.nextToken();
                int _parseIntPrimitive = _parseIntPrimitive(jsonParser, deserializationContext);
                _verifyEndArrayForSingle(jsonParser, deserializationContext);
                return _parseIntPrimitive;
            }
            return ((Number) deserializationContext.handleUnexpectedToken(Integer.TYPE, jsonParser)).intValue();
        }
        extractScalarFromObject = deserializationContext.extractScalarFromObject(jsonParser, this, Integer.TYPE);
        CoercionAction _checkFromStringCoercion = _checkFromStringCoercion(deserializationContext, extractScalarFromObject, LogicalType.Integer, Integer.TYPE);
        if (_checkFromStringCoercion == CoercionAction.AsNull || _checkFromStringCoercion == CoercionAction.AsEmpty) {
            return 0;
        }
        String trim = extractScalarFromObject.trim();
        if (_hasTextualNull(trim)) {
            _verifyNullForPrimitiveCoercion(deserializationContext, trim);
            return 0;
        }
        return _parseIntPrimitive(deserializationContext, trim);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Integer _parseInteger(JsonParser jsonParser, DeserializationContext deserializationContext, Class<?> cls) throws IOException {
        String extractScalarFromObject;
        int currentTokenId = jsonParser.currentTokenId();
        if (currentTokenId == 1) {
            extractScalarFromObject = deserializationContext.extractScalarFromObject(jsonParser, this, cls);
        } else if (currentTokenId == 3) {
            return (Integer) _deserializeFromArray(jsonParser, deserializationContext);
        } else {
            if (currentTokenId == 11) {
                return (Integer) mo7075getNullValue(deserializationContext);
            }
            if (currentTokenId != 6) {
                if (currentTokenId == 7) {
                    return Integer.valueOf(jsonParser.getIntValue());
                }
                if (currentTokenId != 8) {
                    return (Integer) deserializationContext.handleUnexpectedToken(getValueType(deserializationContext), jsonParser);
                }
                CoercionAction _checkFloatToIntCoercion = _checkFloatToIntCoercion(jsonParser, deserializationContext, cls);
                if (_checkFloatToIntCoercion == CoercionAction.AsNull) {
                    return (Integer) mo7075getNullValue(deserializationContext);
                }
                if (_checkFloatToIntCoercion == CoercionAction.AsEmpty) {
                    return (Integer) getEmptyValue(deserializationContext);
                }
                return Integer.valueOf(jsonParser.getValueAsInt());
            }
            extractScalarFromObject = jsonParser.getText();
        }
        CoercionAction _checkFromStringCoercion = _checkFromStringCoercion(deserializationContext, extractScalarFromObject);
        if (_checkFromStringCoercion == CoercionAction.AsNull) {
            return (Integer) mo7075getNullValue(deserializationContext);
        }
        if (_checkFromStringCoercion == CoercionAction.AsEmpty) {
            return (Integer) getEmptyValue(deserializationContext);
        }
        String trim = extractScalarFromObject.trim();
        if (_checkTextualNull(deserializationContext, trim)) {
            return (Integer) mo7075getNullValue(deserializationContext);
        }
        return Integer.valueOf(_parseIntPrimitive(deserializationContext, trim));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Long _parseLong(JsonParser jsonParser, DeserializationContext deserializationContext, Class<?> cls) throws IOException {
        String extractScalarFromObject;
        int currentTokenId = jsonParser.currentTokenId();
        if (currentTokenId == 1) {
            extractScalarFromObject = deserializationContext.extractScalarFromObject(jsonParser, this, cls);
        } else if (currentTokenId == 3) {
            return (Long) _deserializeFromArray(jsonParser, deserializationContext);
        } else {
            if (currentTokenId == 11) {
                return (Long) mo7075getNullValue(deserializationContext);
            }
            if (currentTokenId != 6) {
                if (currentTokenId == 7) {
                    return Long.valueOf(jsonParser.getLongValue());
                }
                if (currentTokenId != 8) {
                    return (Long) deserializationContext.handleUnexpectedToken(getValueType(deserializationContext), jsonParser);
                }
                CoercionAction _checkFloatToIntCoercion = _checkFloatToIntCoercion(jsonParser, deserializationContext, cls);
                if (_checkFloatToIntCoercion == CoercionAction.AsNull) {
                    return (Long) mo7075getNullValue(deserializationContext);
                }
                if (_checkFloatToIntCoercion == CoercionAction.AsEmpty) {
                    return (Long) getEmptyValue(deserializationContext);
                }
                return Long.valueOf(jsonParser.getValueAsLong());
            }
            extractScalarFromObject = jsonParser.getText();
        }
        CoercionAction _checkFromStringCoercion = _checkFromStringCoercion(deserializationContext, extractScalarFromObject);
        if (_checkFromStringCoercion == CoercionAction.AsNull) {
            return (Long) mo7075getNullValue(deserializationContext);
        }
        if (_checkFromStringCoercion == CoercionAction.AsEmpty) {
            return (Long) getEmptyValue(deserializationContext);
        }
        String trim = extractScalarFromObject.trim();
        if (_checkTextualNull(deserializationContext, trim)) {
            return (Long) mo7075getNullValue(deserializationContext);
        }
        return Long.valueOf(_parseLongPrimitive(deserializationContext, trim));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final long _parseLongPrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String extractScalarFromObject;
        int currentTokenId = jsonParser.currentTokenId();
        if (currentTokenId != 1) {
            if (currentTokenId != 3) {
                if (currentTokenId == 11) {
                    _verifyNullForPrimitive(deserializationContext);
                    return 0L;
                } else if (currentTokenId == 6) {
                    extractScalarFromObject = jsonParser.getText();
                } else if (currentTokenId == 7) {
                    return jsonParser.getLongValue();
                } else {
                    if (currentTokenId == 8) {
                        CoercionAction _checkFloatToIntCoercion = _checkFloatToIntCoercion(jsonParser, deserializationContext, Long.TYPE);
                        if (_checkFloatToIntCoercion != CoercionAction.AsNull && _checkFloatToIntCoercion != CoercionAction.AsEmpty) {
                            return jsonParser.getValueAsLong();
                        }
                        return 0L;
                    }
                }
            } else if (deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                jsonParser.nextToken();
                long _parseLongPrimitive = _parseLongPrimitive(jsonParser, deserializationContext);
                _verifyEndArrayForSingle(jsonParser, deserializationContext);
                return _parseLongPrimitive;
            }
            return ((Number) deserializationContext.handleUnexpectedToken(Long.TYPE, jsonParser)).longValue();
        }
        extractScalarFromObject = deserializationContext.extractScalarFromObject(jsonParser, this, Long.TYPE);
        CoercionAction _checkFromStringCoercion = _checkFromStringCoercion(deserializationContext, extractScalarFromObject, LogicalType.Integer, Long.TYPE);
        if (_checkFromStringCoercion == CoercionAction.AsNull || _checkFromStringCoercion == CoercionAction.AsEmpty) {
            return 0L;
        }
        String trim = extractScalarFromObject.trim();
        if (_hasTextualNull(trim)) {
            _verifyNullForPrimitiveCoercion(deserializationContext, trim);
            return 0L;
        }
        return _parseLongPrimitive(deserializationContext, trim);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final short _parseShortPrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String extractScalarFromObject;
        int currentTokenId = jsonParser.currentTokenId();
        if (currentTokenId != 1) {
            if (currentTokenId != 3) {
                if (currentTokenId == 11) {
                    _verifyNullForPrimitive(deserializationContext);
                    return (short) 0;
                } else if (currentTokenId == 6) {
                    extractScalarFromObject = jsonParser.getText();
                } else if (currentTokenId == 7) {
                    return jsonParser.getShortValue();
                } else {
                    if (currentTokenId == 8) {
                        CoercionAction _checkFloatToIntCoercion = _checkFloatToIntCoercion(jsonParser, deserializationContext, Short.TYPE);
                        if (_checkFloatToIntCoercion != CoercionAction.AsNull && _checkFloatToIntCoercion != CoercionAction.AsEmpty) {
                            return jsonParser.getShortValue();
                        }
                        return (short) 0;
                    }
                }
            } else if (deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                jsonParser.nextToken();
                short _parseShortPrimitive = _parseShortPrimitive(jsonParser, deserializationContext);
                _verifyEndArrayForSingle(jsonParser, deserializationContext);
                return _parseShortPrimitive;
            }
            return ((Short) deserializationContext.handleUnexpectedToken(deserializationContext.constructType(Short.TYPE), jsonParser)).shortValue();
        }
        extractScalarFromObject = deserializationContext.extractScalarFromObject(jsonParser, this, Short.TYPE);
        CoercionAction _checkFromStringCoercion = _checkFromStringCoercion(deserializationContext, extractScalarFromObject, LogicalType.Integer, Short.TYPE);
        if (_checkFromStringCoercion == CoercionAction.AsNull || _checkFromStringCoercion == CoercionAction.AsEmpty) {
            return (short) 0;
        }
        String trim = extractScalarFromObject.trim();
        if (_hasTextualNull(trim)) {
            _verifyNullForPrimitiveCoercion(deserializationContext, trim);
            return (short) 0;
        }
        try {
            int parseInt = NumberInput.parseInt(trim);
            return _shortOverflow(parseInt) ? ((Short) deserializationContext.handleWeirdStringValue(Short.TYPE, trim, "overflow, value cannot be represented as 16-bit value", new Object[0])).shortValue() : (short) parseInt;
        } catch (IllegalArgumentException unused) {
            return ((Short) deserializationContext.handleWeirdStringValue(Short.TYPE, trim, "not a valid `short` value", new Object[0])).shortValue();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final String _parseString(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (jsonParser.hasToken(JsonToken.VALUE_STRING)) {
            return jsonParser.getText();
        }
        if (jsonParser.hasToken(JsonToken.VALUE_EMBEDDED_OBJECT)) {
            Object embeddedObject = jsonParser.getEmbeddedObject();
            if (embeddedObject instanceof byte[]) {
                return deserializationContext.getBase64Variant().encode((byte[]) embeddedObject, false);
            }
            if (embeddedObject != null) {
                return embeddedObject.toString();
            }
            return null;
        } else if (jsonParser.hasToken(JsonToken.START_OBJECT)) {
            return deserializationContext.extractScalarFromObject(jsonParser, this, this._valueClass);
        } else {
            String valueAsString = jsonParser.getValueAsString();
            return valueAsString != null ? valueAsString : (String) deserializationContext.handleUnexpectedToken(String.class, jsonParser);
        }
    }

    protected void _reportFailedNullCoerce(DeserializationContext deserializationContext, boolean z, Enum<?> r5, String str) throws JsonMappingException {
        deserializationContext.reportInputMismatch(this, "Cannot coerce %s to Null value as %s (%s `%s.%s` to allow)", str, _coercedTypeDesc(), z ? "enable" : "disable", r5.getDeclaringClass().getSimpleName(), r5.name());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean _shortOverflow(int i) {
        return i < -32768 || i > 32767;
    }

    protected void _verifyEndArrayForSingle(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (jsonParser.nextToken() != JsonToken.END_ARRAY) {
            handleMissingEndArrayForSingle(jsonParser, deserializationContext);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void _verifyNullForPrimitive(DeserializationContext deserializationContext) throws JsonMappingException {
        if (deserializationContext.isEnabled(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES)) {
            deserializationContext.reportInputMismatch(this, "Cannot coerce `null` to %s (disable `DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES` to allow)", _coercedTypeDesc());
        }
    }

    protected final void _verifyNullForPrimitiveCoercion(DeserializationContext deserializationContext, String str) throws JsonMappingException {
        MapperFeature mapperFeature;
        boolean z;
        if (!deserializationContext.isEnabled(MapperFeature.ALLOW_COERCION_OF_SCALARS)) {
            mapperFeature = MapperFeature.ALLOW_COERCION_OF_SCALARS;
            z = true;
        } else if (!deserializationContext.isEnabled(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES)) {
            return;
        } else {
            mapperFeature = DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES;
            z = false;
        }
        _reportFailedNullCoerce(deserializationContext, z, mapperFeature, str.isEmpty() ? "empty String (\"\")" : String.format("String \"%s\"", str));
    }

    @Deprecated
    protected final void _verifyNullForScalarCoercion(DeserializationContext deserializationContext, String str) throws JsonMappingException {
        if (!deserializationContext.isEnabled(MapperFeature.ALLOW_COERCION_OF_SCALARS)) {
            _reportFailedNullCoerce(deserializationContext, true, MapperFeature.ALLOW_COERCION_OF_SCALARS, str.isEmpty() ? "empty String (\"\")" : String.format("String \"%s\"", str));
        }
    }

    @Deprecated
    protected void _verifyNumberForScalarCoercion(DeserializationContext deserializationContext, JsonParser jsonParser) throws IOException {
        MapperFeature mapperFeature = MapperFeature.ALLOW_COERCION_OF_SCALARS;
        if (!deserializationContext.isEnabled(mapperFeature)) {
            deserializationContext.reportInputMismatch(this, "Cannot coerce Number (%s) to %s (enable `%s.%s` to allow)", jsonParser.getText(), _coercedTypeDesc(), mapperFeature.getDeclaringClass().getSimpleName(), mapperFeature.name());
        }
    }

    @Deprecated
    protected void _verifyStringForScalarCoercion(DeserializationContext deserializationContext, String str) throws JsonMappingException {
        MapperFeature mapperFeature = MapperFeature.ALLOW_COERCION_OF_SCALARS;
        if (!deserializationContext.isEnabled(mapperFeature)) {
            deserializationContext.reportInputMismatch(this, "Cannot coerce String \"%s\" to %s (enable `%s.%s` to allow)", str, _coercedTypeDesc(), mapperFeature.getDeclaringClass().getSimpleName(), mapperFeature.name());
        }
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    /* renamed from: deserializeWithType */
    public Object mo7107deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException {
        return typeDeserializer.deserializeTypedFromAny(jsonParser, deserializationContext);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public NullValueProvider findContentNullProvider(DeserializationContext deserializationContext, BeanProperty beanProperty, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        Nulls findContentNullStyle = findContentNullStyle(deserializationContext, beanProperty);
        if (findContentNullStyle == Nulls.SKIP) {
            return NullsConstantProvider.skipper();
        }
        if (findContentNullStyle != Nulls.FAIL) {
            NullValueProvider _findNullProvider = _findNullProvider(deserializationContext, beanProperty, findContentNullStyle, jsonDeserializer);
            return _findNullProvider != null ? _findNullProvider : jsonDeserializer;
        } else if (beanProperty == null) {
            JavaType constructType = deserializationContext.constructType(jsonDeserializer.handledType());
            if (constructType.isContainerType()) {
                constructType = constructType.mo7243getContentType();
            }
            return NullsFailProvider.constructForRootValue(constructType);
        } else {
            return NullsFailProvider.constructForProperty(beanProperty, beanProperty.getType().mo7243getContentType());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Nulls findContentNullStyle(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
        if (beanProperty != null) {
            return beanProperty.getMetadata().getContentNulls();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JsonDeserializer<?> findConvertingContentDeserializer(DeserializationContext deserializationContext, BeanProperty beanProperty, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        AnnotatedMember member;
        Object findDeserializationContentConverter;
        AnnotationIntrospector annotationIntrospector = deserializationContext.getAnnotationIntrospector();
        if (!_neitherNull(annotationIntrospector, beanProperty) || (member = beanProperty.getMember()) == null || (findDeserializationContentConverter = annotationIntrospector.findDeserializationContentConverter(member)) == null) {
            return jsonDeserializer;
        }
        Converter<Object, Object> converterInstance = deserializationContext.converterInstance(beanProperty.getMember(), findDeserializationContentConverter);
        JavaType inputType = converterInstance.getInputType(deserializationContext.getTypeFactory());
        if (jsonDeserializer == null) {
            jsonDeserializer = deserializationContext.findContextualValueDeserializer(inputType, beanProperty);
        }
        return new StdDelegatingDeserializer(converterInstance, inputType, jsonDeserializer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JsonDeserializer<Object> findDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        return deserializationContext.findContextualValueDeserializer(javaType, beanProperty);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Boolean findFormatFeature(DeserializationContext deserializationContext, BeanProperty beanProperty, Class<?> cls, JsonFormat.Feature feature) {
        JsonFormat.Value findFormatOverrides = findFormatOverrides(deserializationContext, beanProperty, cls);
        if (findFormatOverrides != null) {
            return findFormatOverrides.getFeature(feature);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JsonFormat.Value findFormatOverrides(DeserializationContext deserializationContext, BeanProperty beanProperty, Class<?> cls) {
        if (beanProperty != null) {
            return beanProperty.findPropertyFormat(deserializationContext.mo7051getConfig(), cls);
        }
        return deserializationContext.getDefaultPropertyFormat(cls);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final NullValueProvider findValueNullProvider(DeserializationContext deserializationContext, SettableBeanProperty settableBeanProperty, PropertyMetadata propertyMetadata) throws JsonMappingException {
        if (settableBeanProperty != null) {
            return _findNullProvider(deserializationContext, settableBeanProperty, propertyMetadata.getValueNulls(), settableBeanProperty.getValueDeserializer());
        }
        return null;
    }

    @Deprecated
    public final Class<?> getValueClass() {
        return this._valueClass;
    }

    public ValueInstantiator getValueInstantiator() {
        return null;
    }

    public JavaType getValueType() {
        return this._valueType;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void handleMissingEndArrayForSingle(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        deserializationContext.reportWrongTokenException(this, JsonToken.END_ARRAY, "Attempted to unwrap '%s' value from an array (with `DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS`) but it contains more than one value", handledType().getName());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void handleUnknownProperty(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj, String str) throws IOException {
        if (obj == null) {
            obj = handledType();
        }
        if (deserializationContext.handleUnknownProperty(jsonParser, this, obj, str)) {
            return;
        }
        jsonParser.skipChildren();
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public Class<?> handledType() {
        return this._valueClass;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isDefaultDeserializer(JsonDeserializer<?> jsonDeserializer) {
        return ClassUtil.isJacksonStdImpl(jsonDeserializer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isDefaultKeyDeserializer(KeyDeserializer keyDeserializer) {
        return ClassUtil.isJacksonStdImpl(keyDeserializer);
    }

    protected CoercionAction _checkFromStringCoercion(DeserializationContext deserializationContext, String str, LogicalType logicalType, Class<?> cls) throws IOException {
        if (str.isEmpty()) {
            return _checkCoercionFail(deserializationContext, deserializationContext.findCoercionAction(logicalType, cls, CoercionInputShape.EmptyString), cls, str, "empty String (\"\")");
        }
        if (_isBlank(str)) {
            return _checkCoercionFail(deserializationContext, deserializationContext.findCoercionFromBlankString(logicalType, cls, CoercionAction.Fail), cls, str, "blank String (all whitespace)");
        }
        if (deserializationContext.isEnabled(StreamReadCapability.UNTYPED_SCALARS)) {
            return CoercionAction.TryConvert;
        }
        CoercionAction findCoercionAction = deserializationContext.findCoercionAction(logicalType, cls, CoercionInputShape.String);
        if (findCoercionAction == CoercionAction.Fail) {
            deserializationContext.reportInputMismatch(this, "Cannot coerce String value (\"%s\") to %s (but might if coercion using `CoercionConfig` was enabled)", str, _coercedTypeDesc());
        }
        return findCoercionAction;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean _parseBooleanPrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String extractScalarFromObject;
        int currentTokenId = jsonParser.currentTokenId();
        if (currentTokenId != 1) {
            if (currentTokenId != 3) {
                if (currentTokenId != 6) {
                    if (currentTokenId != 7) {
                        switch (currentTokenId) {
                            case 9:
                                return true;
                            case 10:
                                return false;
                            case 11:
                                _verifyNullForPrimitive(deserializationContext);
                                return false;
                        }
                    }
                    return Boolean.TRUE.equals(_coerceBooleanFromInt(jsonParser, deserializationContext, Boolean.TYPE));
                }
                extractScalarFromObject = jsonParser.getText();
            } else if (deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                jsonParser.nextToken();
                boolean _parseBooleanPrimitive = _parseBooleanPrimitive(jsonParser, deserializationContext);
                _verifyEndArrayForSingle(jsonParser, deserializationContext);
                return _parseBooleanPrimitive;
            }
            return ((Boolean) deserializationContext.handleUnexpectedToken(Boolean.TYPE, jsonParser)).booleanValue();
        }
        extractScalarFromObject = deserializationContext.extractScalarFromObject(jsonParser, this, Boolean.TYPE);
        CoercionAction _checkFromStringCoercion = _checkFromStringCoercion(deserializationContext, extractScalarFromObject, LogicalType.Boolean, Boolean.TYPE);
        if (_checkFromStringCoercion == CoercionAction.AsNull) {
            _verifyNullForPrimitive(deserializationContext);
            return false;
        } else if (_checkFromStringCoercion == CoercionAction.AsEmpty) {
            return false;
        } else {
            String trim = extractScalarFromObject.trim();
            int length = trim.length();
            if (length == 4) {
                if (_isTrue(trim)) {
                    return true;
                }
            } else if (length == 5 && _isFalse(trim)) {
                return false;
            }
            if (_hasTextualNull(trim)) {
                _verifyNullForPrimitiveCoercion(deserializationContext, trim);
                return false;
            }
            return Boolean.TRUE.equals((Boolean) deserializationContext.handleWeirdStringValue(Boolean.TYPE, trim, "only \"true\"/\"True\"/\"TRUE\" or \"false\"/\"False\"/\"FALSE\" recognized", new Object[0]));
        }
    }

    public JavaType getValueType(DeserializationContext deserializationContext) {
        JavaType javaType = this._valueType;
        return javaType != null ? javaType : deserializationContext.constructType(this._valueClass);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public StdDeserializer(JavaType javaType) {
        this._valueClass = javaType == null ? Object.class : javaType.getRawClass();
        this._valueType = javaType;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public StdDeserializer(StdDeserializer<?> stdDeserializer) {
        this._valueClass = stdDeserializer._valueClass;
        this._valueType = stdDeserializer._valueType;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Date _parseDate(String str, DeserializationContext deserializationContext) throws IOException {
        try {
            if (str.isEmpty()) {
                if (_checkFromStringCoercion(deserializationContext, str).ordinal() == 3) {
                    return new Date(0L);
                }
                return null;
            } else if (!_hasTextualNull(str)) {
                return deserializationContext.parseDate(str);
            } else {
                return null;
            }
        } catch (IllegalArgumentException e) {
            return (Date) deserializationContext.handleWeirdStringValue(this._valueClass, str, "not a valid representation (error: %s)", ClassUtil.exceptionMessage(e));
        }
    }

    protected final double _parseDoublePrimitive(DeserializationContext deserializationContext, String str) throws IOException {
        try {
            return _parseDouble(str);
        } catch (IllegalArgumentException unused) {
            return _nonNullNumber((Number) deserializationContext.handleWeirdStringValue(Double.TYPE, str, "not a valid `double` value (as String to convert)", new Object[0])).doubleValue();
        }
    }

    protected final float _parseFloatPrimitive(DeserializationContext deserializationContext, String str) throws IOException {
        try {
            return Float.parseFloat(str);
        } catch (IllegalArgumentException unused) {
            return _nonNullNumber((Number) deserializationContext.handleWeirdStringValue(Float.TYPE, str, "not a valid `float` value", new Object[0])).floatValue();
        }
    }

    protected final int _parseIntPrimitive(DeserializationContext deserializationContext, String str) throws IOException {
        try {
            if (str.length() > 9) {
                long parseLong = Long.parseLong(str);
                return _intOverflow(parseLong) ? _nonNullNumber((Number) deserializationContext.handleWeirdStringValue(Integer.TYPE, str, "Overflow: numeric value (%s) out of range of int (%d -%d)", str, Integer.MIN_VALUE, Integer.MAX_VALUE)).intValue() : (int) parseLong;
            }
            return NumberInput.parseInt(str);
        } catch (IllegalArgumentException unused) {
            return _nonNullNumber((Number) deserializationContext.handleWeirdStringValue(Integer.TYPE, str, "not a valid `int` value", new Object[0])).intValue();
        }
    }

    protected final long _parseLongPrimitive(DeserializationContext deserializationContext, String str) throws IOException {
        try {
            return NumberInput.parseLong(str);
        } catch (IllegalArgumentException unused) {
            return _nonNullNumber((Number) deserializationContext.handleWeirdStringValue(Long.TYPE, str, "not a valid `long` value", new Object[0])).longValue();
        }
    }
}
