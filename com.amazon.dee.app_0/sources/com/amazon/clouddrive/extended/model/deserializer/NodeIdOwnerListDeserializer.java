package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.NodeOwnerPair;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class NodeIdOwnerListDeserializer implements JsonDeserializer<List<NodeOwnerPair>> {
    private static final String DELIMITER = ":";
    public static final JsonDeserializer<List<NodeOwnerPair>> INSTANCE = new NodeIdOwnerListDeserializer();

    private NodeIdOwnerListDeserializer() {
    }

    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize  reason: collision with other method in class */
    public List<NodeOwnerPair> mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_ARRAY) {
            ArrayList arrayList = new ArrayList();
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                if (!jsonParser.isClosed()) {
                    String deserializeString = SimpleDeserializers.deserializeString(jsonParser);
                    if (deserializeString != null) {
                        if (deserializeString.contains(":")) {
                            String[] split = deserializeString.split(":");
                            arrayList.add(new NodeOwnerPair(split[0], split[1]));
                        } else {
                            throw new JsonParseException("Expected ownerId:nodeId pair", jsonParser.getTokenLocation());
                        }
                    } else {
                        throw new JsonParseException("Unexpected null ownerId:nodeId pair", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                }
            }
            return arrayList;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of array, got ", currentToken), jsonParser.getTokenLocation());
    }
}
