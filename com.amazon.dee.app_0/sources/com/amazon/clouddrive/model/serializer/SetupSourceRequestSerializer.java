package com.amazon.clouddrive.model.serializer;

import com.amazon.clouddrive.model.SetupSourceRequest;
import com.amazon.whisperjoin.deviceprovisioningservice.arcus.data.ArcusConstants;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class SetupSourceRequestSerializer implements JsonSerializer<SetupSourceRequest> {
    public static final JsonSerializer<SetupSourceRequest> INSTANCE = new SetupSourceRequestSerializer();

    private SetupSourceRequestSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(SetupSourceRequest setupSourceRequest, JsonGenerator jsonGenerator) throws IOException {
        if (setupSourceRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName("sourceVersion");
        SimpleSerializers.serializeString(setupSourceRequest.getSourceVersion(), jsonGenerator);
        jsonGenerator.writeFieldName("deviceFriendlyName");
        SimpleSerializers.serializeString(setupSourceRequest.getDeviceFriendlyName(), jsonGenerator);
        jsonGenerator.writeFieldName("deviceSerialNumber");
        SimpleSerializers.serializeString(setupSourceRequest.getDeviceSerialNumber(), jsonGenerator);
        jsonGenerator.writeFieldName("osVersion");
        SimpleSerializers.serializeString(setupSourceRequest.getOsVersion(), jsonGenerator);
        jsonGenerator.writeFieldName(ArcusConstants.InputAttribute.DEVICE_MODEL);
        SimpleSerializers.serializeString(setupSourceRequest.getDeviceModel(), jsonGenerator);
        jsonGenerator.writeFieldName("deviceClass");
        SimpleSerializers.serializeString(setupSourceRequest.getDeviceClass(), jsonGenerator);
        jsonGenerator.writeFieldName("devicePlatform");
        SimpleSerializers.serializeString(setupSourceRequest.getDevicePlatform(), jsonGenerator);
        jsonGenerator.writeFieldName("sourceApplicationName");
        SimpleSerializers.serializeString(setupSourceRequest.getSourceApplicationName(), jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
