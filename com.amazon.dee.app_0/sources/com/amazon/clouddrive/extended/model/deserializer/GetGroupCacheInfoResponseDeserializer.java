package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.GetGroupCacheInfoResponse;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import com.amazon.commscore.api.metrics.MobilyticsCustomEntries;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class GetGroupCacheInfoResponseDeserializer implements JsonDeserializer<GetGroupCacheInfoResponse> {
    public static final JsonDeserializer<GetGroupCacheInfoResponse> INSTANCE = new GetGroupCacheInfoResponseDeserializer();
    private final GetGroupCacheInfoFieldDeserializer mFieldHandler = new GetGroupCacheInfoFieldDeserializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class GetGroupCacheInfoFieldDeserializer implements JsonFieldDeserializer<GetGroupCacheInfoResponse> {
        GetGroupCacheInfoFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((GetGroupCacheInfoResponse) obj));
        }

        public <U extends GetGroupCacheInfoResponse> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("version".equals(str)) {
                u.setVersion(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if (MobilyticsCustomEntries.CONTENT_VERSION.equals(str)) {
                u.setContentVersion(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if (!"viewedStateVersion".equals(str)) {
                return false;
            } else {
                u.setViewedStateVersion(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            }
        }
    }

    private GetGroupCacheInfoResponseDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public GetGroupCacheInfoResponse mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            GetGroupCacheInfoResponse getGroupCacheInfoResponse = new GetGroupCacheInfoResponse();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (!this.mFieldHandler.handleField(jsonParser, currentName, (String) getGroupCacheInfoResponse)) {
                            jsonParser.skipChildren();
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return getGroupCacheInfoResponse;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
