package com.amazon.org.codehaus.jackson.map.ext;

import com.amazon.org.codehaus.jackson.JsonGenerationException;
import com.amazon.org.codehaus.jackson.JsonGenerator;
import com.amazon.org.codehaus.jackson.JsonNode;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.map.JsonSerializer;
import com.amazon.org.codehaus.jackson.map.SerializationConfig;
import com.amazon.org.codehaus.jackson.map.SerializerProvider;
import com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase;
import com.amazon.org.codehaus.jackson.map.ser.std.ToStringSerializer;
import com.amazon.org.codehaus.jackson.map.util.Provider;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.Period;
import org.joda.time.ReadableInstant;
import org.joda.time.ReadablePartial;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
/* loaded from: classes13.dex */
public class JodaSerializers implements Provider<Map.Entry<Class<?>, JsonSerializer<?>>> {
    static final HashMap<Class<?>, JsonSerializer<?>> _serializers = new HashMap<>();

    /* loaded from: classes13.dex */
    public static final class DateMidnightSerializer extends JodaSerializer<DateMidnight> {
        public DateMidnightSerializer() {
            super(DateMidnight.class);
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode(serializerProvider.isEnabled(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS) ? "array" : "string", true);
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.map.JsonSerializer
        public void serialize(DateMidnight dateMidnight, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            if (serializerProvider.isEnabled(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS)) {
                jsonGenerator.writeStartArray();
                jsonGenerator.writeNumber(dateMidnight.year().get());
                jsonGenerator.writeNumber(dateMidnight.monthOfYear().get());
                jsonGenerator.writeNumber(dateMidnight.dayOfMonth().get());
                jsonGenerator.writeEndArray();
                return;
            }
            jsonGenerator.writeString(printLocalDate(dateMidnight));
        }
    }

    /* loaded from: classes13.dex */
    public static final class DateTimeSerializer extends JodaSerializer<DateTime> {
        public DateTimeSerializer() {
            super(DateTime.class);
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode(serializerProvider.isEnabled(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS) ? "number" : "string", true);
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.map.JsonSerializer
        public void serialize(DateTime dateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            if (serializerProvider.isEnabled(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS)) {
                jsonGenerator.writeNumber(dateTime.getMillis());
            } else {
                jsonGenerator.writeString(dateTime.toString());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes13.dex */
    public static abstract class JodaSerializer<T> extends SerializerBase<T> {
        static final DateTimeFormatter _localDateTimeFormat = ISODateTimeFormat.dateTime();
        static final DateTimeFormatter _localDateFormat = ISODateTimeFormat.date();

        protected JodaSerializer(Class<T> cls) {
            super(cls);
        }

        protected String printLocalDate(ReadablePartial readablePartial) throws IOException, JsonProcessingException {
            return _localDateFormat.print(readablePartial);
        }

        protected String printLocalDateTime(ReadablePartial readablePartial) throws IOException, JsonProcessingException {
            return _localDateTimeFormat.print(readablePartial);
        }

        protected String printLocalDate(ReadableInstant readableInstant) throws IOException, JsonProcessingException {
            return _localDateFormat.print(readableInstant);
        }
    }

    /* loaded from: classes13.dex */
    public static final class LocalDateSerializer extends JodaSerializer<LocalDate> {
        public LocalDateSerializer() {
            super(LocalDate.class);
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode(serializerProvider.isEnabled(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS) ? "array" : "string", true);
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.map.JsonSerializer
        public void serialize(LocalDate localDate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            if (serializerProvider.isEnabled(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS)) {
                jsonGenerator.writeStartArray();
                jsonGenerator.writeNumber(localDate.year().get());
                jsonGenerator.writeNumber(localDate.monthOfYear().get());
                jsonGenerator.writeNumber(localDate.dayOfMonth().get());
                jsonGenerator.writeEndArray();
                return;
            }
            jsonGenerator.writeString(printLocalDate(localDate));
        }
    }

    /* loaded from: classes13.dex */
    public static final class LocalDateTimeSerializer extends JodaSerializer<LocalDateTime> {
        public LocalDateTimeSerializer() {
            super(LocalDateTime.class);
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode(serializerProvider.isEnabled(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS) ? "array" : "string", true);
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.map.JsonSerializer
        public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            if (serializerProvider.isEnabled(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS)) {
                jsonGenerator.writeStartArray();
                jsonGenerator.writeNumber(localDateTime.year().get());
                jsonGenerator.writeNumber(localDateTime.monthOfYear().get());
                jsonGenerator.writeNumber(localDateTime.dayOfMonth().get());
                jsonGenerator.writeNumber(localDateTime.hourOfDay().get());
                jsonGenerator.writeNumber(localDateTime.minuteOfHour().get());
                jsonGenerator.writeNumber(localDateTime.secondOfMinute().get());
                jsonGenerator.writeNumber(localDateTime.millisOfSecond().get());
                jsonGenerator.writeEndArray();
                return;
            }
            jsonGenerator.writeString(printLocalDateTime(localDateTime));
        }
    }

    static {
        _serializers.put(DateTime.class, new DateTimeSerializer());
        _serializers.put(LocalDateTime.class, new LocalDateTimeSerializer());
        _serializers.put(LocalDate.class, new LocalDateSerializer());
        _serializers.put(DateMidnight.class, new DateMidnightSerializer());
        _serializers.put(Period.class, ToStringSerializer.instance);
    }

    @Override // com.amazon.org.codehaus.jackson.map.util.Provider
    public Collection<Map.Entry<Class<?>, JsonSerializer<?>>> provide() {
        return _serializers.entrySet();
    }
}
