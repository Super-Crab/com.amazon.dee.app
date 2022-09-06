package com.amazon.clouddrive.model.deserializer;

import com.amazon.clouddrive.model.BaseDeviceInfo;
import com.amazon.whisperjoin.deviceprovisioningservice.arcus.data.ArcusConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class BaseDeviceInfoDeserializer implements JsonDeserializer<BaseDeviceInfo> {
    public static final JsonDeserializer<BaseDeviceInfo> INSTANCE = new BaseDeviceInfoDeserializer();

    private BaseDeviceInfoDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public BaseDeviceInfo mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            BaseDeviceInfo baseDeviceInfo = new BaseDeviceInfo();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (ArcusConstants.InputAttribute.DEVICE_MODEL.equals(currentName)) {
                            baseDeviceInfo.setDeviceModel(SimpleDeserializers.deserializeString(jsonParser));
                        } else if ("creationTime".equals(currentName)) {
                            baseDeviceInfo.setCreationTime(SimpleDeserializers.deserializeString(jsonParser));
                        } else if ("deviceId".equals(currentName)) {
                            baseDeviceInfo.setDeviceId(SimpleDeserializers.deserializeString(jsonParser));
                        } else if ("deviceStatus".equals(currentName)) {
                            baseDeviceInfo.setDeviceStatus(SimpleDeserializers.deserializeString(jsonParser));
                        } else if ("devicePlatform".equals(currentName)) {
                            baseDeviceInfo.setDevicePlatform(SimpleDeserializers.deserializeString(jsonParser));
                        } else if ("deviceClass".equals(currentName)) {
                            baseDeviceInfo.setDeviceClass(SimpleDeserializers.deserializeString(jsonParser));
                        } else if ("osVersionHistory".equals(currentName)) {
                            baseDeviceInfo.setOsVersionHistory(VersionHistoryMapDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                        } else if ("deviceFriendlyName".equals(currentName)) {
                            baseDeviceInfo.setDeviceFriendlyName(SimpleDeserializers.deserializeString(jsonParser));
                        } else if ("lastModifiedTime".equals(currentName)) {
                            baseDeviceInfo.setLastModifiedTime(SimpleDeserializers.deserializeString(jsonParser));
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
            return baseDeviceInfo;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
