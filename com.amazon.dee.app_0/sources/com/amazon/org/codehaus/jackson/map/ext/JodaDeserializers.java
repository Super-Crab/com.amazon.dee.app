package com.amazon.org.codehaus.jackson.map.ext;

import com.amazon.org.codehaus.jackson.JsonParser;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.JsonToken;
import com.amazon.org.codehaus.jackson.map.DeserializationContext;
import com.amazon.org.codehaus.jackson.map.deser.std.StdDeserializer;
import com.amazon.org.codehaus.jackson.map.deser.std.StdScalarDeserializer;
import com.amazon.org.codehaus.jackson.map.util.Provider;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.Period;
import org.joda.time.ReadableDateTime;
import org.joda.time.ReadableInstant;
import org.joda.time.ReadablePeriod;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
/* loaded from: classes13.dex */
public class JodaDeserializers implements Provider<StdDeserializer<?>> {

    /* renamed from: com.amazon.org.codehaus.jackson.map.ext.JodaDeserializers$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken = new int[JsonToken.values().length];

        static {
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NUMBER_INT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.VALUE_STRING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* loaded from: classes13.dex */
    public static class DateMidnightDeserializer extends JodaDeserializer<DateMidnight> {
        public DateMidnightDeserializer() {
            super(DateMidnight.class);
        }

        @Override // com.amazon.org.codehaus.jackson.map.JsonDeserializer
        /* renamed from: deserialize  reason: collision with other method in class */
        public DateMidnight mo4206deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            if (jsonParser.isExpectedStartArrayToken()) {
                jsonParser.nextToken();
                int intValue = jsonParser.getIntValue();
                jsonParser.nextToken();
                int intValue2 = jsonParser.getIntValue();
                jsonParser.nextToken();
                int intValue3 = jsonParser.getIntValue();
                JsonToken nextToken = jsonParser.nextToken();
                JsonToken jsonToken = JsonToken.END_ARRAY;
                if (nextToken == jsonToken) {
                    return new DateMidnight(intValue, intValue2, intValue3);
                }
                throw deserializationContext.wrongTokenException(jsonParser, jsonToken, "after DateMidnight ints");
            }
            int ordinal = jsonParser.getCurrentToken().ordinal();
            if (ordinal != 7) {
                if (ordinal == 8) {
                    return new DateMidnight(jsonParser.getLongValue());
                }
                throw deserializationContext.wrongTokenException(jsonParser, JsonToken.START_ARRAY, "expected JSON Array, Number or String");
            }
            DateTime parseLocal = parseLocal(jsonParser);
            if (parseLocal != null) {
                return parseLocal.toDateMidnight();
            }
            return null;
        }
    }

    /* loaded from: classes13.dex */
    public static class DateTimeDeserializer<T extends ReadableInstant> extends JodaDeserializer<T> {
        public DateTimeDeserializer(Class<T> cls) {
            super(cls);
        }

        @Override // com.amazon.org.codehaus.jackson.map.JsonDeserializer
        /* renamed from: deserialize  reason: collision with other method in class */
        public T mo4206deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.VALUE_NUMBER_INT) {
                return new DateTime(jsonParser.getLongValue(), DateTimeZone.UTC);
            }
            if (currentToken == JsonToken.VALUE_STRING) {
                String trim = jsonParser.getText().trim();
                if (trim.length() != 0) {
                    return new DateTime(trim, DateTimeZone.UTC);
                }
                return null;
            }
            throw deserializationContext.mappingException(getValueClass());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public static abstract class JodaDeserializer<T> extends StdScalarDeserializer<T> {
        static final DateTimeFormatter _localDateTimeFormat = ISODateTimeFormat.localDateOptionalTimeParser();

        protected JodaDeserializer(Class<T> cls) {
            super((Class<?>) cls);
        }

        protected DateTime parseLocal(JsonParser jsonParser) throws IOException, JsonProcessingException {
            String trim = jsonParser.getText().trim();
            if (trim.length() == 0) {
                return null;
            }
            return _localDateTimeFormat.parseDateTime(trim);
        }
    }

    /* loaded from: classes13.dex */
    public static class LocalDateDeserializer extends JodaDeserializer<LocalDate> {
        public LocalDateDeserializer() {
            super(LocalDate.class);
        }

        @Override // com.amazon.org.codehaus.jackson.map.JsonDeserializer
        /* renamed from: deserialize  reason: collision with other method in class */
        public LocalDate mo4206deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            if (jsonParser.isExpectedStartArrayToken()) {
                jsonParser.nextToken();
                int intValue = jsonParser.getIntValue();
                jsonParser.nextToken();
                int intValue2 = jsonParser.getIntValue();
                jsonParser.nextToken();
                int intValue3 = jsonParser.getIntValue();
                JsonToken nextToken = jsonParser.nextToken();
                JsonToken jsonToken = JsonToken.END_ARRAY;
                if (nextToken == jsonToken) {
                    return new LocalDate(intValue, intValue2, intValue3);
                }
                throw deserializationContext.wrongTokenException(jsonParser, jsonToken, "after LocalDate ints");
            }
            int ordinal = jsonParser.getCurrentToken().ordinal();
            if (ordinal != 7) {
                if (ordinal == 8) {
                    return new LocalDate(jsonParser.getLongValue());
                }
                throw deserializationContext.wrongTokenException(jsonParser, JsonToken.START_ARRAY, "expected JSON Array, String or Number");
            }
            DateTime parseLocal = parseLocal(jsonParser);
            if (parseLocal != null) {
                return parseLocal.toLocalDate();
            }
            return null;
        }
    }

    /* loaded from: classes13.dex */
    public static class LocalDateTimeDeserializer extends JodaDeserializer<LocalDateTime> {
        public LocalDateTimeDeserializer() {
            super(LocalDateTime.class);
        }

        @Override // com.amazon.org.codehaus.jackson.map.JsonDeserializer
        /* renamed from: deserialize  reason: collision with other method in class */
        public LocalDateTime mo4206deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            if (jsonParser.isExpectedStartArrayToken()) {
                jsonParser.nextToken();
                int intValue = jsonParser.getIntValue();
                jsonParser.nextToken();
                int intValue2 = jsonParser.getIntValue();
                jsonParser.nextToken();
                int intValue3 = jsonParser.getIntValue();
                jsonParser.nextToken();
                int intValue4 = jsonParser.getIntValue();
                jsonParser.nextToken();
                int intValue5 = jsonParser.getIntValue();
                jsonParser.nextToken();
                int intValue6 = jsonParser.getIntValue();
                int i = 0;
                if (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                    i = jsonParser.getIntValue();
                    jsonParser.nextToken();
                }
                int i2 = i;
                JsonToken currentToken = jsonParser.getCurrentToken();
                JsonToken jsonToken = JsonToken.END_ARRAY;
                if (currentToken == jsonToken) {
                    return new LocalDateTime(intValue, intValue2, intValue3, intValue4, intValue5, intValue6, i2);
                }
                throw deserializationContext.wrongTokenException(jsonParser, jsonToken, "after LocalDateTime ints");
            }
            int ordinal = jsonParser.getCurrentToken().ordinal();
            if (ordinal != 7) {
                if (ordinal == 8) {
                    return new LocalDateTime(jsonParser.getLongValue());
                }
                throw deserializationContext.wrongTokenException(jsonParser, JsonToken.START_ARRAY, "expected JSON Array or Number");
            }
            DateTime parseLocal = parseLocal(jsonParser);
            if (parseLocal != null) {
                return parseLocal.toLocalDateTime();
            }
            return null;
        }
    }

    /* loaded from: classes13.dex */
    public static class PeriodDeserializer extends JodaDeserializer<ReadablePeriod> {
        public PeriodDeserializer() {
            super(ReadablePeriod.class);
        }

        @Override // com.amazon.org.codehaus.jackson.map.JsonDeserializer
        /* renamed from: deserialize  reason: collision with other method in class */
        public ReadablePeriod mo4206deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            int ordinal = jsonParser.getCurrentToken().ordinal();
            if (ordinal != 7) {
                if (ordinal == 8) {
                    return new Period(jsonParser.getLongValue());
                }
                throw deserializationContext.wrongTokenException(jsonParser, JsonToken.START_ARRAY, "expected JSON Number or String");
            }
            return new Period(jsonParser.getText());
        }
    }

    @Override // com.amazon.org.codehaus.jackson.map.util.Provider
    public Collection<StdDeserializer<?>> provide() {
        return Arrays.asList(new DateTimeDeserializer(DateTime.class), new DateTimeDeserializer(ReadableDateTime.class), new DateTimeDeserializer(ReadableInstant.class), new LocalDateDeserializer(), new LocalDateTimeDeserializer(), new DateMidnightDeserializer(), new PeriodDeserializer());
    }
}
