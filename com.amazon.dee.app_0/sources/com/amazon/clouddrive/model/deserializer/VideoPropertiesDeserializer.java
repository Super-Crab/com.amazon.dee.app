package com.amazon.clouddrive.model.deserializer;

import com.amazon.clouddrive.model.VideoProperties;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class VideoPropertiesDeserializer implements JsonDeserializer<VideoProperties> {
    public static final JsonDeserializer<VideoProperties> INSTANCE = new VideoPropertiesDeserializer();
    private final VideoPropertiesFieldDeserializer mFieldHandler = new VideoPropertiesFieldDeserializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class VideoPropertiesFieldDeserializer implements JsonFieldDeserializer<VideoProperties> {
        VideoPropertiesFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((VideoProperties) obj));
        }

        public <U extends VideoProperties> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("title".equals(str)) {
                u.setTitle(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("bitrate".equals(str)) {
                u.setBitrate(SimpleDeserializers.deserializeDouble(jsonParser));
                return true;
            } else if ("videoFrameRate".equals(str)) {
                u.setVideoFrameRate(SimpleDeserializers.deserializeDouble(jsonParser));
                return true;
            } else if ("creationDate".equals(str)) {
                u.setCreationDate(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("model".equals(str)) {
                u.setModel(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("videoBitrate".equals(str)) {
                u.setVideoBitrate(SimpleDeserializers.deserializeDouble(jsonParser));
                return true;
            } else if ("audioCodec".equals(str)) {
                u.setAudioCodec(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("rotate".equals(str)) {
                u.setRotate(SimpleDeserializers.deserializeInteger(jsonParser));
                return true;
            } else if ("duration".equals(str)) {
                u.setDuration(SimpleDeserializers.deserializeDouble(jsonParser));
                return true;
            } else if ("encoder".equals(str)) {
                u.setEncoder(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("location".equals(str)) {
                u.setLocation(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("audioBitrate".equals(str)) {
                u.setAudioBitrate(SimpleDeserializers.deserializeDouble(jsonParser));
                return true;
            } else if ("audioSampleRate".equals(str)) {
                u.setAudioSampleRate(SimpleDeserializers.deserializeDouble(jsonParser));
                return true;
            } else if ("make".equals(str)) {
                u.setMake(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("videoCodec".equals(str)) {
                u.setVideoCodec(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("height".equals(str)) {
                u.setHeight(SimpleDeserializers.deserializeInteger(jsonParser));
                return true;
            } else if ("audioChannels".equals(str)) {
                u.setAudioChannels(SimpleDeserializers.deserializeInteger(jsonParser));
                return true;
            } else if ("width".equals(str)) {
                u.setWidth(SimpleDeserializers.deserializeInteger(jsonParser));
                return true;
            } else if (!"audioChannelLayout".equals(str)) {
                return false;
            } else {
                u.setAudioChannelLayout(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            }
        }
    }

    private VideoPropertiesDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public VideoProperties mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            VideoProperties videoProperties = new VideoProperties();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (!this.mFieldHandler.handleField(jsonParser, currentName, (String) videoProperties)) {
                            jsonParser.skipChildren();
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return videoProperties;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
