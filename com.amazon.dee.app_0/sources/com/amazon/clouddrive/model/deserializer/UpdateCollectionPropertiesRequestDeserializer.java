package com.amazon.clouddrive.model.deserializer;

import com.amazon.clouddrive.model.UpdateCollectionPropertiesRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class UpdateCollectionPropertiesRequestDeserializer implements JsonDeserializer<UpdateCollectionPropertiesRequest> {
    public static final JsonDeserializer<UpdateCollectionPropertiesRequest> INSTANCE = new UpdateCollectionPropertiesRequestDeserializer();

    private UpdateCollectionPropertiesRequestDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public UpdateCollectionPropertiesRequest mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            UpdateCollectionPropertiesRequest updateCollectionPropertiesRequest = new UpdateCollectionPropertiesRequest();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if ("id".equals(currentName)) {
                            updateCollectionPropertiesRequest.setId(SimpleDeserializers.deserializeString(jsonParser));
                        } else if ("operations".equals(currentName)) {
                            updateCollectionPropertiesRequest.setOperations(CollectionPropertiesUpdateObjectListDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                        } else {
                            jsonParser.skipChildren();
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return updateCollectionPropertiesRequest;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
