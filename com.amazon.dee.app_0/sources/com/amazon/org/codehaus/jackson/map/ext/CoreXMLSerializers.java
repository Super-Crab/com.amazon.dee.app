package com.amazon.org.codehaus.jackson.map.ext;

import com.amazon.org.codehaus.jackson.JsonGenerationException;
import com.amazon.org.codehaus.jackson.JsonGenerator;
import com.amazon.org.codehaus.jackson.JsonNode;
import com.amazon.org.codehaus.jackson.map.JsonMappingException;
import com.amazon.org.codehaus.jackson.map.JsonSerializer;
import com.amazon.org.codehaus.jackson.map.SerializerProvider;
import com.amazon.org.codehaus.jackson.map.ser.std.CalendarSerializer;
import com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase;
import com.amazon.org.codehaus.jackson.map.ser.std.ToStringSerializer;
import com.amazon.org.codehaus.jackson.map.util.Provider;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
/* loaded from: classes13.dex */
public class CoreXMLSerializers implements Provider<Map.Entry<Class<?>, JsonSerializer<?>>> {
    static final HashMap<Class<?>, JsonSerializer<?>> _serializers = new HashMap<>();

    /* loaded from: classes13.dex */
    public static class XMLGregorianCalendarSerializer extends SerializerBase<XMLGregorianCalendar> {
        public XMLGregorianCalendarSerializer() {
            super(XMLGregorianCalendar.class);
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) throws JsonMappingException {
            return CalendarSerializer.instance.getSchema(serializerProvider, type);
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.map.JsonSerializer
        public void serialize(XMLGregorianCalendar xMLGregorianCalendar, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            CalendarSerializer.instance.serialize((Calendar) xMLGregorianCalendar.toGregorianCalendar(), jsonGenerator, serializerProvider);
        }
    }

    static {
        ToStringSerializer toStringSerializer = ToStringSerializer.instance;
        _serializers.put(Duration.class, toStringSerializer);
        _serializers.put(XMLGregorianCalendar.class, new XMLGregorianCalendarSerializer());
        _serializers.put(QName.class, toStringSerializer);
    }

    @Override // com.amazon.org.codehaus.jackson.map.util.Provider
    public Collection<Map.Entry<Class<?>, JsonSerializer<?>>> provide() {
        return _serializers.entrySet();
    }
}
