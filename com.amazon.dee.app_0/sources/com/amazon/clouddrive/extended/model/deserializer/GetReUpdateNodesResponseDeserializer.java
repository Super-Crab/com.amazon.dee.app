package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.GetReUpdateNodesResponse;
import com.amazon.clouddrive.extended.model.ReUpdateNode;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.ListDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.util.List;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class GetReUpdateNodesResponseDeserializer implements JsonDeserializer<GetReUpdateNodesResponse> {
    public static final JsonDeserializer<GetReUpdateNodesResponse> INSTANCE = new GetReUpdateNodesResponseDeserializer();
    private static final JsonDeserializer<List<ReUpdateNode>> NODE_DESERIALIZER = new ListDeserializer(new ReUpdateNodeDeserializer());

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public GetReUpdateNodesResponse mo3229deserialize(JsonParser jsonParser) throws IOException {
        GetReUpdateNodesResponse getReUpdateNodesResponse = new GetReUpdateNodesResponse();
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if ("data".equals(currentName)) {
                            getReUpdateNodesResponse.setData(NODE_DESERIALIZER.mo3229deserialize(jsonParser));
                        } else if ("count".equals(currentName)) {
                            getReUpdateNodesResponse.setCount(SimpleDeserializers.deserializeInteger(jsonParser).intValue());
                        } else if ("nextToken".equals(currentName)) {
                            getReUpdateNodesResponse.setNextToken(SimpleDeserializers.deserializeString(jsonParser));
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return getReUpdateNodesResponse;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
