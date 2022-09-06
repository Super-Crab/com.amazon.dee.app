package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.ContentAggregations;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class ContentAggregationsDeserializer implements JsonDeserializer<ContentAggregations> {
    public static final JsonDeserializer<ContentAggregations> INSTANCE = new ContentAggregationsDeserializer();
    private final ContentAggregationFieldDeserializer mFieldHandler = new ContentAggregationFieldDeserializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class ContentAggregationFieldDeserializer implements JsonFieldDeserializer<ContentAggregations> {
        ContentAggregationFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((ContentAggregations) obj));
        }

        public <U extends ContentAggregations> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("albumCount".equals(str)) {
                u.setAlbumCount(SimpleDeserializers.deserializePrimitiveLong(jsonParser));
                return true;
            } else if ("photoCount".equals(str)) {
                u.setPhotoCount(SimpleDeserializers.deserializePrimitiveLong(jsonParser));
                return true;
            } else if ("videoCount".equals(str)) {
                u.setVideoCount(SimpleDeserializers.deserializePrimitiveLong(jsonParser));
                return true;
            } else if (!"reactionCount".equals(str)) {
                return false;
            } else {
                u.setReactionCount(SimpleDeserializers.deserializePrimitiveLong(jsonParser));
                return true;
            }
        }
    }

    private ContentAggregationsDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public ContentAggregations mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            ContentAggregations contentAggregations = new ContentAggregations();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (!this.mFieldHandler.handleField(jsonParser, currentName, (String) contentAggregations)) {
                            jsonParser.skipChildren();
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return contentAggregations;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
