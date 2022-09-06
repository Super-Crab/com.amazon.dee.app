package com.amazon.alexa.accessorykit.registraton;

import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.registration.DeviceRegistration;
import com.amazon.alexa.accessorykit.ContainerProvider;
import com.amazon.alexa.accessorykit.accessories.MapModelTransformer;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import java.text.ParseException;
import org.json.JSONException;
/* loaded from: classes6.dex */
public class DeviceRegistrationTransformer implements MapModelTransformer<DeviceRegistration> {
    private static final String DEVICE_REGISTRATION_KEY = "deviceRegistration";
    private static final String TAG = "DeviceRegistrationTransformer";
    private final ContainerProvider containerProvider;

    public DeviceRegistrationTransformer(ContainerProvider containerProvider) {
        Preconditions.notNull(containerProvider, "containerProvider");
        this.containerProvider = containerProvider;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.accessorykit.accessories.MapModelTransformer
    /* renamed from: transform */
    public DeviceRegistration mo626transform(ReadableMap readableMap) throws ParseException {
        throw new ParseException("Not supported", 0);
    }

    @Override // com.amazon.alexa.accessorykit.accessories.MapModelTransformer
    public WritableMap transformToMap(DeviceRegistration deviceRegistration) {
        WritableMap map = this.containerProvider.getMap();
        try {
            map.putString(DEVICE_REGISTRATION_KEY, deviceRegistration.toJsonObject().toString());
        } catch (JSONException e) {
            Logger.e("%s: Exception converting device registration to JSON, ", e, TAG);
        }
        return map;
    }
}
