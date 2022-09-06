package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.GetFacesForPersonResponse;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.ListDeserializer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class GetFacesForPersonResponseDeserializer implements JsonDeserializer<GetFacesForPersonResponse> {
    public static final JsonDeserializer<GetFacesForPersonResponse> INSTANCE = new GetFacesForPersonResponseDeserializer();

    private GetFacesForPersonResponseDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public GetFacesForPersonResponse mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        GetFacesForPersonResponse getFacesForPersonResponse = new GetFacesForPersonResponse();
        if (currentToken == JsonToken.START_OBJECT) {
            if (jsonParser.nextToken() == JsonToken.FIELD_NAME) {
                if ("faceList".equals(jsonParser.getCurrentName())) {
                    if (jsonParser.nextToken() != null) {
                        getFacesForPersonResponse.setFaces(new ListDeserializer(NodeFaceDeserializer.INSTANCE).mo3229deserialize(jsonParser));
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                }
                return getFacesForPersonResponse;
            }
            throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
