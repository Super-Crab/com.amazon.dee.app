package com.amazon.org.codehaus.jackson.map.deser.std;

import com.amazon.org.codehaus.jackson.JsonParser;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.map.DeserializationContext;
import java.io.IOException;
import java.util.Date;
/* loaded from: classes13.dex */
public class DateDeserializer extends StdScalarDeserializer<Date> {
    public DateDeserializer() {
        super(Date.class);
    }

    @Override // com.amazon.org.codehaus.jackson.map.JsonDeserializer
    /* renamed from: deserialize  reason: collision with other method in class */
    public Date mo4206deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return _parseDate(jsonParser, deserializationContext);
    }
}
