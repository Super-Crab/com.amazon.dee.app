package com.amazon.clouddrive.model.deserializer;

import com.amazon.clouddrive.model.ImageProperties;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class ImagePropertiesDeserializer implements JsonDeserializer<ImageProperties> {
    public static final JsonDeserializer<ImageProperties> INSTANCE = new ImagePropertiesDeserializer();
    private final ImagePropertiesFieldDeserializer mFieldHandler = new ImagePropertiesFieldDeserializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class ImagePropertiesFieldDeserializer implements JsonFieldDeserializer<ImageProperties> {
        ImagePropertiesFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((ImageProperties) obj));
        }

        public <U extends ImageProperties> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("yResolution".equals(str)) {
                u.setYResolution(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("dateTimeDigitized".equals(str)) {
                u.setDateTimeDigitized(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("whiteBalance".equals(str)) {
                u.setWhiteBalance(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("width".equals(str)) {
                u.setWidth(SimpleDeserializers.deserializeInteger(jsonParser));
                return true;
            } else if ("focalLength".equals(str)) {
                u.setFocalLength(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("meteringMode".equals(str)) {
                u.setMeteringMode(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("software".equals(str)) {
                u.setSoftware(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("isoSpeedRatings".equals(str)) {
                u.setIsoSpeedRatings(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("orientation".equals(str)) {
                u.setOrientation(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("colorSpace".equals(str)) {
                u.setColorSpace(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("dateTime".equals(str)) {
                u.setDateTime(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("sharpness".equals(str)) {
                u.setSharpness(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("flash".equals(str)) {
                u.setFlash(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("location".equals(str)) {
                u.setLocation(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("exposureMode".equals(str)) {
                u.setExposureMode(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("dateTimeOriginal".equals(str)) {
                u.setDateTimeOriginal(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("model".equals(str)) {
                u.setModel(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("make".equals(str)) {
                u.setMake(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("exposureProgram".equals(str)) {
                u.setExposureProgram(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("sensingMethod".equals(str)) {
                u.setSensingMethod(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("xResolution".equals(str)) {
                u.setXResolution(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("gpsTimeStamp".equals(str)) {
                u.setGpsTimeStamp(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("apertureValue".equals(str)) {
                u.setApertureValue(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("exposureTime".equals(str)) {
                u.setExposureTime(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("resolutionUnit".equals(str)) {
                u.setResolutionUnit(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("height".equals(str)) {
                u.setHeight(SimpleDeserializers.deserializeInteger(jsonParser));
                return true;
            } else if (!"captureMode".equals(str)) {
                return false;
            } else {
                u.setCaptureMode(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            }
        }
    }

    private ImagePropertiesDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public ImageProperties mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            ImageProperties imageProperties = new ImageProperties();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (!this.mFieldHandler.handleField(jsonParser, currentName, (String) imageProperties)) {
                            jsonParser.skipChildren();
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return imageProperties;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
