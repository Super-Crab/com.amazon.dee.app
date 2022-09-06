package com.amazon.clouddrive.model.deserializer;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class KindObjectIdListMapDeserializer implements JsonDeserializer<Map<String, List<String>>> {
    public static final JsonDeserializer<Map<String, List<String>>> INSTANCE = new KindObjectIdListMapDeserializer();

    private KindObjectIdListMapDeserializer() {
    }

    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize  reason: collision with other method in class */
    public Map<String, List<String>> mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            HashMap hashMap = new HashMap();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        hashMap.put(currentName, NodeIdListDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return hashMap;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of map, got ", currentToken), jsonParser.getTokenLocation());
    }
}
