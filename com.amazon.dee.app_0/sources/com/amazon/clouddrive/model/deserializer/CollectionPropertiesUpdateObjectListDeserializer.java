package com.amazon.clouddrive.model.deserializer;

import com.amazon.clouddrive.model.CollectionPropertiesUpdateObject;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class CollectionPropertiesUpdateObjectListDeserializer implements JsonDeserializer<List<CollectionPropertiesUpdateObject>> {
    public static final JsonDeserializer<List<CollectionPropertiesUpdateObject>> INSTANCE = new CollectionPropertiesUpdateObjectListDeserializer();

    private CollectionPropertiesUpdateObjectListDeserializer() {
    }

    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize  reason: collision with other method in class */
    public List<CollectionPropertiesUpdateObject> mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_ARRAY) {
            ArrayList arrayList = new ArrayList();
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                if (!jsonParser.isClosed()) {
                    arrayList.add(CollectionPropertiesUpdateObjectDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                } else {
                    throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                }
            }
            return arrayList;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of array, got ", currentToken), jsonParser.getTokenLocation());
    }
}
