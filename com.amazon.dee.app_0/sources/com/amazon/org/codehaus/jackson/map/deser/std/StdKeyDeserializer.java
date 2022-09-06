package com.amazon.org.codehaus.jackson.map.deser.std;

import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.io.NumberInput;
import com.amazon.org.codehaus.jackson.map.DeserializationContext;
import com.amazon.org.codehaus.jackson.map.JsonMappingException;
import com.amazon.org.codehaus.jackson.map.KeyDeserializer;
import com.amazon.org.codehaus.jackson.map.introspect.AnnotatedMethod;
import com.amazon.org.codehaus.jackson.map.util.ClassUtil;
import com.amazon.org.codehaus.jackson.map.util.EnumResolver;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
/* loaded from: classes13.dex */
public abstract class StdKeyDeserializer extends KeyDeserializer {
    protected final Class<?> _keyClass;

    /* loaded from: classes13.dex */
    static final class BoolKD extends StdKeyDeserializer {
        /* JADX INFO: Access modifiers changed from: package-private */
        public BoolKD() {
            super(Boolean.class);
        }

        @Override // com.amazon.org.codehaus.jackson.map.deser.std.StdKeyDeserializer
        /* renamed from: _parse */
        public Boolean mo4193_parse(String str, DeserializationContext deserializationContext) throws JsonMappingException {
            if ("true".equals(str)) {
                return Boolean.TRUE;
            }
            if (PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE.equals(str)) {
                return Boolean.FALSE;
            }
            throw deserializationContext.weirdKeyException(this._keyClass, str, "value not 'true' or 'false'");
        }
    }

    /* loaded from: classes13.dex */
    static final class ByteKD extends StdKeyDeserializer {
        /* JADX INFO: Access modifiers changed from: package-private */
        public ByteKD() {
            super(Byte.class);
        }

        @Override // com.amazon.org.codehaus.jackson.map.deser.std.StdKeyDeserializer
        /* renamed from: _parse */
        public Byte mo4193_parse(String str, DeserializationContext deserializationContext) throws JsonMappingException {
            int _parseInt = _parseInt(str);
            if (_parseInt >= -128 && _parseInt <= 255) {
                return Byte.valueOf((byte) _parseInt);
            }
            throw deserializationContext.weirdKeyException(this._keyClass, str, "overflow, value can not be represented as 8-bit value");
        }
    }

    /* loaded from: classes13.dex */
    static final class CalendarKD extends StdKeyDeserializer {
        /* JADX INFO: Access modifiers changed from: protected */
        public CalendarKD() {
            super(Calendar.class);
        }

        @Override // com.amazon.org.codehaus.jackson.map.deser.std.StdKeyDeserializer
        /* renamed from: _parse  reason: collision with other method in class */
        public Calendar mo4193_parse(String str, DeserializationContext deserializationContext) throws IllegalArgumentException, JsonMappingException {
            Date parseDate = deserializationContext.parseDate(str);
            if (parseDate == null) {
                return null;
            }
            return deserializationContext.constructCalendar(parseDate);
        }
    }

    /* loaded from: classes13.dex */
    static final class CharKD extends StdKeyDeserializer {
        /* JADX INFO: Access modifiers changed from: package-private */
        public CharKD() {
            super(Character.class);
        }

        @Override // com.amazon.org.codehaus.jackson.map.deser.std.StdKeyDeserializer
        /* renamed from: _parse */
        public Character mo4193_parse(String str, DeserializationContext deserializationContext) throws JsonMappingException {
            if (str.length() == 1) {
                return Character.valueOf(str.charAt(0));
            }
            throw deserializationContext.weirdKeyException(this._keyClass, str, "can only convert 1-character Strings");
        }
    }

    /* loaded from: classes13.dex */
    static final class DateKD extends StdKeyDeserializer {
        /* JADX INFO: Access modifiers changed from: protected */
        public DateKD() {
            super(Date.class);
        }

        @Override // com.amazon.org.codehaus.jackson.map.deser.std.StdKeyDeserializer
        /* renamed from: _parse  reason: collision with other method in class */
        public Date mo4193_parse(String str, DeserializationContext deserializationContext) throws IllegalArgumentException, JsonMappingException {
            return deserializationContext.parseDate(str);
        }
    }

    /* loaded from: classes13.dex */
    static final class DoubleKD extends StdKeyDeserializer {
        /* JADX INFO: Access modifiers changed from: package-private */
        public DoubleKD() {
            super(Double.class);
        }

        @Override // com.amazon.org.codehaus.jackson.map.deser.std.StdKeyDeserializer
        /* renamed from: _parse */
        public Double mo4193_parse(String str, DeserializationContext deserializationContext) throws JsonMappingException {
            return Double.valueOf(_parseDouble(str));
        }
    }

    /* loaded from: classes13.dex */
    static final class EnumKD extends StdKeyDeserializer {
        protected final AnnotatedMethod _factory;
        protected final EnumResolver<?> _resolver;

        /* JADX INFO: Access modifiers changed from: protected */
        public EnumKD(EnumResolver<?> enumResolver, AnnotatedMethod annotatedMethod) {
            super(enumResolver.getEnumClass());
            this._resolver = enumResolver;
            this._factory = annotatedMethod;
        }

        @Override // com.amazon.org.codehaus.jackson.map.deser.std.StdKeyDeserializer
        /* renamed from: _parse */
        public Object mo4193_parse(String str, DeserializationContext deserializationContext) throws JsonMappingException {
            AnnotatedMethod annotatedMethod = this._factory;
            if (annotatedMethod != null) {
                try {
                    return annotatedMethod.call1(str);
                } catch (Exception e) {
                    ClassUtil.unwrapAndThrowAsIAE(e);
                }
            }
            Object findEnum = this._resolver.findEnum(str);
            if (findEnum != null) {
                return findEnum;
            }
            throw deserializationContext.weirdKeyException(this._keyClass, str, "not one of values for Enum class");
        }
    }

    /* loaded from: classes13.dex */
    static final class FloatKD extends StdKeyDeserializer {
        /* JADX INFO: Access modifiers changed from: package-private */
        public FloatKD() {
            super(Float.class);
        }

        @Override // com.amazon.org.codehaus.jackson.map.deser.std.StdKeyDeserializer
        /* renamed from: _parse */
        public Float mo4193_parse(String str, DeserializationContext deserializationContext) throws JsonMappingException {
            return Float.valueOf((float) _parseDouble(str));
        }
    }

    /* loaded from: classes13.dex */
    static final class IntKD extends StdKeyDeserializer {
        /* JADX INFO: Access modifiers changed from: package-private */
        public IntKD() {
            super(Integer.class);
        }

        @Override // com.amazon.org.codehaus.jackson.map.deser.std.StdKeyDeserializer
        /* renamed from: _parse */
        public Integer mo4193_parse(String str, DeserializationContext deserializationContext) throws JsonMappingException {
            return Integer.valueOf(_parseInt(str));
        }
    }

    /* loaded from: classes13.dex */
    static final class LongKD extends StdKeyDeserializer {
        /* JADX INFO: Access modifiers changed from: package-private */
        public LongKD() {
            super(Long.class);
        }

        @Override // com.amazon.org.codehaus.jackson.map.deser.std.StdKeyDeserializer
        /* renamed from: _parse */
        public Long mo4193_parse(String str, DeserializationContext deserializationContext) throws JsonMappingException {
            return Long.valueOf(_parseLong(str));
        }
    }

    /* loaded from: classes13.dex */
    static final class ShortKD extends StdKeyDeserializer {
        /* JADX INFO: Access modifiers changed from: package-private */
        public ShortKD() {
            super(Integer.class);
        }

        @Override // com.amazon.org.codehaus.jackson.map.deser.std.StdKeyDeserializer
        /* renamed from: _parse  reason: collision with other method in class */
        public Short mo4193_parse(String str, DeserializationContext deserializationContext) throws JsonMappingException {
            int _parseInt = _parseInt(str);
            if (_parseInt >= -32768 && _parseInt <= 32767) {
                return Short.valueOf((short) _parseInt);
            }
            throw deserializationContext.weirdKeyException(this._keyClass, str, "overflow, value can not be represented as 16-bit value");
        }
    }

    /* loaded from: classes13.dex */
    static final class StringCtorKeyDeserializer extends StdKeyDeserializer {
        protected final Constructor<?> _ctor;

        public StringCtorKeyDeserializer(Constructor<?> constructor) {
            super(constructor.getDeclaringClass());
            this._ctor = constructor;
        }

        @Override // com.amazon.org.codehaus.jackson.map.deser.std.StdKeyDeserializer
        /* renamed from: _parse */
        public Object mo4193_parse(String str, DeserializationContext deserializationContext) throws Exception {
            return this._ctor.newInstance(str);
        }
    }

    /* loaded from: classes13.dex */
    static final class StringFactoryKeyDeserializer extends StdKeyDeserializer {
        final Method _factoryMethod;

        public StringFactoryKeyDeserializer(Method method) {
            super(method.getDeclaringClass());
            this._factoryMethod = method;
        }

        @Override // com.amazon.org.codehaus.jackson.map.deser.std.StdKeyDeserializer
        /* renamed from: _parse */
        public Object mo4193_parse(String str, DeserializationContext deserializationContext) throws Exception {
            return this._factoryMethod.invoke(null, str);
        }
    }

    /* loaded from: classes13.dex */
    static final class StringKD extends StdKeyDeserializer {
        private static final StringKD sString = new StringKD(String.class);
        private static final StringKD sObject = new StringKD(Object.class);

        private StringKD(Class<?> cls) {
            super(cls);
        }

        public static StringKD forType(Class<?> cls) {
            if (cls == String.class) {
                return sString;
            }
            if (cls == Object.class) {
                return sObject;
            }
            return new StringKD(cls);
        }

        @Override // com.amazon.org.codehaus.jackson.map.deser.std.StdKeyDeserializer
        /* renamed from: _parse  reason: collision with other method in class */
        public String mo4193_parse(String str, DeserializationContext deserializationContext) throws JsonMappingException {
            return str;
        }
    }

    /* loaded from: classes13.dex */
    static final class UuidKD extends StdKeyDeserializer {
        /* JADX INFO: Access modifiers changed from: protected */
        public UuidKD() {
            super(UUID.class);
        }

        @Override // com.amazon.org.codehaus.jackson.map.deser.std.StdKeyDeserializer
        /* renamed from: _parse  reason: collision with other method in class */
        public UUID mo4193_parse(String str, DeserializationContext deserializationContext) throws IllegalArgumentException, JsonMappingException {
            return UUID.fromString(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public StdKeyDeserializer(Class<?> cls) {
        this._keyClass = cls;
    }

    /* renamed from: _parse */
    protected abstract Object mo4193_parse(String str, DeserializationContext deserializationContext) throws Exception;

    protected double _parseDouble(String str) throws IllegalArgumentException {
        return NumberInput.parseDouble(str);
    }

    protected int _parseInt(String str) throws IllegalArgumentException {
        return Integer.parseInt(str);
    }

    protected long _parseLong(String str) throws IllegalArgumentException {
        return Long.parseLong(str);
    }

    @Override // com.amazon.org.codehaus.jackson.map.KeyDeserializer
    public final Object deserializeKey(String str, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        if (str == null) {
            return null;
        }
        try {
            Object mo4193_parse = mo4193_parse(str, deserializationContext);
            if (mo4193_parse == null) {
                throw deserializationContext.weirdKeyException(this._keyClass, str, "not a valid representation");
            }
            return mo4193_parse;
        } catch (Exception e) {
            Class<?> cls = this._keyClass;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("not a valid representation: ");
            outline107.append(e.getMessage());
            throw deserializationContext.weirdKeyException(cls, str, outline107.toString());
        }
    }

    public Class<?> getKeyClass() {
        return this._keyClass;
    }
}
