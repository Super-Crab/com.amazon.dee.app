package com.amazon.org.codehaus.jackson.map.deser.std;

import com.amazon.org.codehaus.jackson.JsonParser;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.map.DeserializationContext;
import com.amazon.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
@JacksonStdImpl
/* loaded from: classes13.dex */
public class CalendarDeserializer extends StdScalarDeserializer<Calendar> {
    protected final Class<? extends Calendar> _calendarClass;

    public CalendarDeserializer() {
        this(null);
    }

    public CalendarDeserializer(Class<? extends Calendar> cls) {
        super(Calendar.class);
        this._calendarClass = cls;
    }

    @Override // com.amazon.org.codehaus.jackson.map.JsonDeserializer
    /* renamed from: deserialize  reason: collision with other method in class */
    public Calendar mo4206deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        Date _parseDate = _parseDate(jsonParser, deserializationContext);
        if (_parseDate == null) {
            return null;
        }
        Class<? extends Calendar> cls = this._calendarClass;
        if (cls == null) {
            return deserializationContext.constructCalendar(_parseDate);
        }
        try {
            Calendar newInstance = cls.newInstance();
            newInstance.setTimeInMillis(_parseDate.getTime());
            return newInstance;
        } catch (Exception e) {
            throw deserializationContext.instantiationException(this._calendarClass, e);
        }
    }
}
