package com.amazon.org.codehaus.jackson.map.ser.std;

import com.amazon.org.codehaus.jackson.JsonGenerationException;
import com.amazon.org.codehaus.jackson.JsonGenerator;
import com.amazon.org.codehaus.jackson.JsonNode;
import com.amazon.org.codehaus.jackson.map.SerializationConfig;
import com.amazon.org.codehaus.jackson.map.SerializerProvider;
import com.amazon.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Calendar;
@JacksonStdImpl
/* loaded from: classes13.dex */
public class CalendarSerializer extends ScalarSerializerBase<Calendar> {
    public static CalendarSerializer instance = new CalendarSerializer();

    public CalendarSerializer() {
        super(Calendar.class);
    }

    @Override // com.amazon.org.codehaus.jackson.map.ser.std.ScalarSerializerBase, com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.schema.SchemaAware
    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
        return createSchemaNode(serializerProvider.isEnabled(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS) ? "number" : "string", true);
    }

    @Override // com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.map.JsonSerializer
    public void serialize(Calendar calendar, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        serializerProvider.defaultSerializeDateValue(calendar.getTimeInMillis(), jsonGenerator);
    }
}
