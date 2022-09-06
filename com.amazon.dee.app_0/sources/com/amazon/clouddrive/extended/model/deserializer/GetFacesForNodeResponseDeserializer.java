package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.GetFacesForNodeResponse;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.ListDeserializer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class GetFacesForNodeResponseDeserializer implements JsonDeserializer<GetFacesForNodeResponse> {
    public static final JsonDeserializer<GetFacesForNodeResponse> INSTANCE = new GetFacesForNodeResponseDeserializer();

    private GetFacesForNodeResponseDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public GetFacesForNodeResponse mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        GetFacesForNodeResponse getFacesForNodeResponse = new GetFacesForNodeResponse();
        if (currentToken == JsonToken.START_OBJECT) {
            if (jsonParser.nextToken() == JsonToken.FIELD_NAME) {
                if ("faceList".equals(jsonParser.getCurrentName())) {
                    if (jsonParser.nextToken() != null) {
                        getFacesForNodeResponse.setFaces(new ListDeserializer(NodeFaceDeserializer.INSTANCE).mo3229deserialize(jsonParser));
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                }
                return getFacesForNodeResponse;
            }
            throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
