package com.amazon.org.codehaus.jackson.map.ext;

import com.amazon.org.codehaus.jackson.JsonParser;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.map.DeserializationContext;
import com.amazon.org.codehaus.jackson.map.deser.std.FromStringDeserializer;
import com.amazon.org.codehaus.jackson.map.deser.std.StdDeserializer;
import com.amazon.org.codehaus.jackson.map.deser.std.StdScalarDeserializer;
import com.amazon.org.codehaus.jackson.map.util.Provider;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
/* loaded from: classes13.dex */
public class CoreXMLDeserializers implements Provider<StdDeserializer<?>> {
    static final DatatypeFactory _dataTypeFactory;

    /* loaded from: classes13.dex */
    public static class DurationDeserializer extends FromStringDeserializer<Duration> {
        public DurationDeserializer() {
            super(Duration.class);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.amazon.org.codehaus.jackson.map.deser.std.FromStringDeserializer
        /* renamed from: _deserialize  reason: avoid collision after fix types in other method */
        public Duration mo4201_deserialize(String str, DeserializationContext deserializationContext) throws IllegalArgumentException {
            return CoreXMLDeserializers._dataTypeFactory.newDuration(str);
        }
    }

    /* loaded from: classes13.dex */
    public static class GregorianCalendarDeserializer extends StdScalarDeserializer<XMLGregorianCalendar> {
        public GregorianCalendarDeserializer() {
            super(XMLGregorianCalendar.class);
        }

        @Override // com.amazon.org.codehaus.jackson.map.JsonDeserializer
        /* renamed from: deserialize  reason: collision with other method in class */
        public XMLGregorianCalendar mo4206deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            Date _parseDate = _parseDate(jsonParser, deserializationContext);
            if (_parseDate == null) {
                return null;
            }
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTime(_parseDate);
            return CoreXMLDeserializers._dataTypeFactory.newXMLGregorianCalendar(gregorianCalendar);
        }
    }

    /* loaded from: classes13.dex */
    public static class QNameDeserializer extends FromStringDeserializer<QName> {
        public QNameDeserializer() {
            super(QName.class);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.amazon.org.codehaus.jackson.map.deser.std.FromStringDeserializer
        /* renamed from: _deserialize  reason: avoid collision after fix types in other method */
        public QName mo4201_deserialize(String str, DeserializationContext deserializationContext) throws IllegalArgumentException {
            return QName.valueOf(str);
        }
    }

    static {
        try {
            _dataTypeFactory = DatatypeFactory.newInstance();
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // com.amazon.org.codehaus.jackson.map.util.Provider
    public Collection<StdDeserializer<?>> provide() {
        return Arrays.asList(new DurationDeserializer(), new GregorianCalendarDeserializer(), new QNameDeserializer());
    }
}
