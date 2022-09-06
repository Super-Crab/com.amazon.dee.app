package com.amazon.clouddrive.model.deserializer;

import com.amazon.clouddrive.model.CollectionProperties;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.gms.actions.SearchIntents;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class CollectionPropertiesDeserializer implements JsonDeserializer<CollectionProperties> {
    public static final JsonDeserializer<CollectionProperties> INSTANCE = new CollectionPropertiesDeserializer();

    private CollectionPropertiesDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public CollectionProperties mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            CollectionProperties collectionProperties = new CollectionProperties();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (SearchIntents.EXTRA_QUERY.equals(currentName)) {
                            collectionProperties.setQuery(QueryObjectDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                        } else if ("areCoversDesired".equals(currentName)) {
                            collectionProperties.setCoversDesired(SimpleDeserializers.deserializeBoolean(jsonParser));
                        } else if ("covers".equals(currentName)) {
                            collectionProperties.setCovers(CoverObjectListDeserializer.INSTANCE.mo3229deserialize(jsonParser));
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
            return collectionProperties;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
