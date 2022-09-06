package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.GetMetadataDatabaseStatusResponse;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class GetMetadataDatabaseStatusResponseDeserializer implements JsonDeserializer<GetMetadataDatabaseStatusResponse> {
    public static final JsonDeserializer<GetMetadataDatabaseStatusResponse> INSTANCE = new GetMetadataDatabaseStatusResponseDeserializer();
    private static final String LOCATION_KEY = "location";
    private static final String PERCENTAGE_KEY = "percentage";
    private static final String STATUS_KEY = "status";

    private GetMetadataDatabaseStatusResponseDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public GetMetadataDatabaseStatusResponse mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            GetMetadataDatabaseStatusResponse getMetadataDatabaseStatusResponse = new GetMetadataDatabaseStatusResponse();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (PERCENTAGE_KEY.equals(currentName)) {
                            getMetadataDatabaseStatusResponse.setPercentComplete(SimpleDeserializers.deserializeDouble(jsonParser).doubleValue());
                        } else if ("status".equals(currentName)) {
                            getMetadataDatabaseStatusResponse.setStatus(GetMetadataDatabaseStatusResponse.Status.fromString(SimpleDeserializers.deserializeString(jsonParser)));
                        } else if ("location".equals(currentName)) {
                            getMetadataDatabaseStatusResponse.setLocation(SimpleDeserializers.deserializeString(jsonParser));
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
            return getMetadataDatabaseStatusResponse;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
