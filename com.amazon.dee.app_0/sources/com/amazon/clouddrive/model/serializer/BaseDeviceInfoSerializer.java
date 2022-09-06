package com.amazon.clouddrive.model.serializer;

import com.amazon.clouddrive.model.BaseDeviceInfo;
import com.amazon.whisperjoin.deviceprovisioningservice.arcus.data.ArcusConstants;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class BaseDeviceInfoSerializer implements JsonSerializer<BaseDeviceInfo> {
    public static final JsonSerializer<BaseDeviceInfo> INSTANCE = new BaseDeviceInfoSerializer();

    private BaseDeviceInfoSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(BaseDeviceInfo baseDeviceInfo, JsonGenerator jsonGenerator) throws IOException {
        if (baseDeviceInfo == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName(ArcusConstants.InputAttribute.DEVICE_MODEL);
        SimpleSerializers.serializeString(baseDeviceInfo.getDeviceModel(), jsonGenerator);
        jsonGenerator.writeFieldName("creationTime");
        SimpleSerializers.serializeString(baseDeviceInfo.getCreationTime(), jsonGenerator);
        jsonGenerator.writeFieldName("deviceId");
        SimpleSerializers.serializeString(baseDeviceInfo.getDeviceId(), jsonGenerator);
        jsonGenerator.writeFieldName("deviceStatus");
        SimpleSerializers.serializeString(baseDeviceInfo.getDeviceStatus(), jsonGenerator);
        jsonGenerator.writeFieldName("devicePlatform");
        SimpleSerializers.serializeString(baseDeviceInfo.getDevicePlatform(), jsonGenerator);
        jsonGenerator.writeFieldName("deviceClass");
        SimpleSerializers.serializeString(baseDeviceInfo.getDeviceClass(), jsonGenerator);
        jsonGenerator.writeFieldName("osVersionHistory");
        VersionHistoryMapSerializer.INSTANCE.serialize(baseDeviceInfo.getOsVersionHistory(), jsonGenerator);
        jsonGenerator.writeFieldName("deviceFriendlyName");
        SimpleSerializers.serializeString(baseDeviceInfo.getDeviceFriendlyName(), jsonGenerator);
        jsonGenerator.writeFieldName("lastModifiedTime");
        SimpleSerializers.serializeString(baseDeviceInfo.getLastModifiedTime(), jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
