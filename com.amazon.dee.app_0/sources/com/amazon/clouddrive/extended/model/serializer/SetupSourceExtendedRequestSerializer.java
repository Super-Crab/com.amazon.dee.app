package com.amazon.clouddrive.extended.model.serializer;

import com.amazon.client.metrics.thirdparty.configuration.MetricsConfiguration;
import com.amazon.clouddrive.extended.model.SetupSourceExtendedRequest;
import com.amazon.clouddrive.model.serializer.JsonSerializer;
import com.amazon.clouddrive.model.serializer.SimpleSerializers;
import com.amazon.whisperjoin.deviceprovisioningservice.arcus.data.ArcusConstants;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class SetupSourceExtendedRequestSerializer implements JsonSerializer<SetupSourceExtendedRequest> {
    public static final JsonSerializer<SetupSourceExtendedRequest> INSTANCE = new SetupSourceExtendedRequestSerializer();

    private SetupSourceExtendedRequestSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(SetupSourceExtendedRequest setupSourceExtendedRequest, JsonGenerator jsonGenerator) throws IOException {
        if (setupSourceExtendedRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName("sourceVersion");
        SimpleSerializers.serializeString(setupSourceExtendedRequest.getSourceVersion(), jsonGenerator);
        jsonGenerator.writeFieldName("deviceFriendlyName");
        SimpleSerializers.serializeString(setupSourceExtendedRequest.getDeviceFriendlyName(), jsonGenerator);
        jsonGenerator.writeFieldName("deviceSerialNumber");
        SimpleSerializers.serializeString(setupSourceExtendedRequest.getDeviceSerialNumber(), jsonGenerator);
        jsonGenerator.writeFieldName("osVersion");
        SimpleSerializers.serializeString(setupSourceExtendedRequest.getOsVersion(), jsonGenerator);
        jsonGenerator.writeFieldName(ArcusConstants.InputAttribute.DEVICE_MODEL);
        SimpleSerializers.serializeString(setupSourceExtendedRequest.getDeviceModel(), jsonGenerator);
        jsonGenerator.writeFieldName("deviceClass");
        SimpleSerializers.serializeString(setupSourceExtendedRequest.getDeviceClass(), jsonGenerator);
        jsonGenerator.writeFieldName("devicePlatform");
        SimpleSerializers.serializeString(setupSourceExtendedRequest.getDevicePlatform(), jsonGenerator);
        jsonGenerator.writeFieldName("sourceApplicationName");
        SimpleSerializers.serializeString(setupSourceExtendedRequest.getSourceApplicationName(), jsonGenerator);
        jsonGenerator.writeFieldName(MetricsConfiguration.DEVICE_LANGUAGE);
        SimpleSerializers.serializeString(setupSourceExtendedRequest.getDeviceLanguage(), jsonGenerator);
        jsonGenerator.writeFieldName("pushProvider");
        SimpleSerializers.serializeString(setupSourceExtendedRequest.getPushProvider(), jsonGenerator);
        jsonGenerator.writeFieldName("registrationToken");
        SimpleSerializers.serializeString(setupSourceExtendedRequest.getRegistrationToken(), jsonGenerator);
        jsonGenerator.writeFieldName("timeZone");
        SimpleSerializers.serializeString(setupSourceExtendedRequest.getTimeZone(), jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
