package com.amazon.whisperjoin.softap.serializer;

import com.amazon.whisperjoin.provisioning.registration.RegistrationToken;
import com.amazon.whisperjoin.softap.pojos.DeviceDetails;
import com.amazon.whisperjoin.softap.pojos.ProvisioningData;
import com.amazon.whisperjoin.wifi.WifiConfiguration;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.HashMap;
import org.apache.commons.lang.NotImplementedException;
/* loaded from: classes13.dex */
public class SerializerFactory {
    private static final Gson GSON = new GsonBuilder().disableHtmlEscaping().create();
    private static final SerializerFactory INSTANCE = new SerializerFactory();
    private final HashMap<Class, ByteSerializer> byteSerializers = new HashMap<>();
    private final HashMap<Class, StringDeserializer> stringDeserializers = new HashMap<>();

    private SerializerFactory() {
        addSerializers();
        addDeserializers();
    }

    private void addDeserializers() {
        this.stringDeserializers.put(DeviceDetails.class, DeviceDetailsJsonDeserializer.builder().gson(GSON).build());
    }

    private void addSerializers() {
        this.byteSerializers.put(ProvisioningData.class, ProvisioningDataJsonSerializer.builder().gson(GSON).build());
        this.byteSerializers.put(WifiConfiguration.class, WifiConfigurationJsonSerializer.builder().gson(GSON).build());
        this.byteSerializers.put(RegistrationToken.class, RegistrationTokenJsonSerializer.builder().gson(GSON).build());
    }

    public static SerializerFactory getInstance() {
        return INSTANCE;
    }

    public <T> ByteSerializer<T> getByteSerializer(Class<T> cls) {
        if (this.byteSerializers.containsKey(cls)) {
            return this.byteSerializers.get(cls);
        }
        throw new NotImplementedException(String.format("No serializer found for class %s", cls.getName()));
    }

    public <T> StringDeserializer<T> getStringDeserializer(Class<T> cls) {
        if (this.stringDeserializers.containsKey(cls)) {
            return this.stringDeserializers.get(cls);
        }
        throw new NotImplementedException(String.format("No serializer found for class %s", cls.getName()));
    }
}
