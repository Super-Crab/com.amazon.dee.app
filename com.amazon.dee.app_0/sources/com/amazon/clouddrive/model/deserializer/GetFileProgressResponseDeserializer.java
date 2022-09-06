package com.amazon.clouddrive.model.deserializer;

import com.amazon.clouddrive.model.GetFileProgressResponse;
import com.amazon.dee.app.ui.web.AlexaDeviceBackgroundImageBridge;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public abstract class GetFileProgressResponseDeserializer<Response extends GetFileProgressResponse> implements JsonDeserializer<Response> {
    private final UploadFileProgressFieldDeserializer mFieldHandler = new UploadFileProgressFieldDeserializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class UploadFileProgressFieldDeserializer implements JsonFieldDeserializer<GetFileProgressResponse> {
        UploadFileProgressFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public boolean handleField(JsonParser jsonParser, String str, GetFileProgressResponse getFileProgressResponse) throws IOException {
            if ("uploadState".equals(str)) {
                getFileProgressResponse.setIsProgressAvailable(true);
                getFileProgressResponse.setResumeState(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if (AlexaDeviceBackgroundImageBridge.KEY_NODE_ID.equals(str)) {
                getFileProgressResponse.setNodeId(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("contentLink".equals(str)) {
                getFileProgressResponse.setContentLink(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("receivedBytes".equals(str)) {
                getFileProgressResponse.setBytesReceived(SimpleDeserializers.deserializeLong(jsonParser).longValue());
                return true;
            } else if ("expectedMd5".equals(str)) {
                getFileProgressResponse.setExpectedMd5(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if (!"started".equals(str)) {
                return false;
            } else {
                getFileProgressResponse.setStartedTime(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            }
        }
    }

    public abstract Response newResponseInstance();

    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public Response mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            Response newResponseInstance = newResponseInstance();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (!this.mFieldHandler.handleField(jsonParser, currentName, (GetFileProgressResponse) newResponseInstance)) {
                            jsonParser.skipChildren();
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return newResponseInstance;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
