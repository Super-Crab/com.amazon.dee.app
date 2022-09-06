package com.amazon.clouddrive.model.deserializer;

import com.amazon.alexa.routing.api.RouteParameter;
import com.amazon.clouddrive.model.CollectionPropertiesUpdateObject;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class CollectionPropertiesUpdateObjectDeserializer implements JsonDeserializer<CollectionPropertiesUpdateObject> {
    public static final JsonDeserializer<CollectionPropertiesUpdateObject> INSTANCE = new CollectionPropertiesUpdateObjectDeserializer();

    private CollectionPropertiesUpdateObjectDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public CollectionPropertiesUpdateObject mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            CollectionPropertiesUpdateObject collectionPropertiesUpdateObject = new CollectionPropertiesUpdateObject();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if ("collectionProperties".equals(currentName)) {
                            collectionPropertiesUpdateObject.setCollectionProperties(CollectionPropertiesDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                        } else if ("op".equals(currentName)) {
                            collectionPropertiesUpdateObject.setOp(SimpleDeserializers.deserializeString(jsonParser));
                        } else if (RouteParameter.PATH.equals(currentName)) {
                            collectionPropertiesUpdateObject.setPath(SimpleDeserializers.deserializeString(jsonParser));
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
            return collectionPropertiesUpdateObject;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
