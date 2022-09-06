package com.amazon.clouddrive.model.deserializer;

import com.amazon.clouddrive.model.BatchLink;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class BatchLinkDeserializer implements JsonDeserializer<BatchLink> {
    public static final JsonDeserializer<BatchLink> INSTANCE = new BatchLinkDeserializer();
    private final BatchLinkFieldDeserializer mFieldHandler = new BatchLinkFieldDeserializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class BatchLinkFieldDeserializer implements JsonFieldDeserializer<BatchLink> {
        BatchLinkFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((BatchLink) obj));
        }

        public <U extends BatchLink> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("content".equals(str)) {
                u.setContent(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if (!"self".equals(str)) {
                return false;
            } else {
                u.setSelf(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            }
        }
    }

    private BatchLinkDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public BatchLink mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            BatchLink batchLink = new BatchLink();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (!this.mFieldHandler.handleField(jsonParser, currentName, (String) batchLink)) {
                            jsonParser.skipChildren();
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return batchLink;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of array, got ", currentToken), jsonParser.getTokenLocation());
    }
}
