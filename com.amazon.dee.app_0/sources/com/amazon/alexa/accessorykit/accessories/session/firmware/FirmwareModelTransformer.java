package com.amazon.alexa.accessorykit.accessories.session.firmware;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.Firmware;
import com.amazon.alexa.accessory.repositories.firmware.FirmwareUpdateStatus;
import com.amazon.alexa.accessorykit.ContainerProvider;
import com.amazon.alexa.accessorykit.accessories.ArrayModelTransformer;
import com.amazon.alexa.accessorykit.accessories.MapModelTransformer;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import java.text.ParseException;
import java.util.Set;
/* loaded from: classes6.dex */
public class FirmwareModelTransformer implements ArrayModelTransformer<Set<Firmware.FirmwareInformation>>, MapModelTransformer<FirmwareUpdateStatus> {
    private static final String CAUSE_KEY = "cause";
    private static final String DEVICE_ID_KEY = "device_id";
    private static final String IS_COMPLETED_KEY = "isCompleted";
    private static final String IS_COMPLETED_WITH_ERROR_KEY = "isCompletedWithError";
    private static final String IS_IDLE_KEY = "isIdle";
    private static final String IS_IN_PROGRESS_KEY = "isInProgress";
    private static final String LOCALE_KEY = "locale";
    private static final String NAME_KEY = "name";
    private static final String PROGRESS_VALUE_KEY = "progress";
    private static final String VERSION_KEY = "version";
    private static final String VERSION_NAME_KEY = "version_name";
    private final ContainerProvider containerProvider;

    public FirmwareModelTransformer(ContainerProvider containerProvider) {
        Preconditions.notNull(containerProvider, "containerProvider");
        this.containerProvider = containerProvider;
    }

    @Override // com.amazon.alexa.accessorykit.accessories.ArrayModelTransformer
    public WritableArray transformToArray(Set<Firmware.FirmwareInformation> set) {
        WritableArray array = this.containerProvider.getArray();
        for (Firmware.FirmwareInformation firmwareInformation : set) {
            WritableMap map = this.containerProvider.getMap();
            map.putInt("version", firmwareInformation.getVersion());
            map.putString("name", firmwareInformation.getName());
            map.putString("locale", firmwareInformation.getLocale());
            map.putString(VERSION_NAME_KEY, firmwareInformation.getVersionName());
            map.putInt("device_id", firmwareInformation.getDeviceId());
            array.pushMap(map);
        }
        return array;
    }

    @Override // com.amazon.alexa.accessorykit.accessories.MapModelTransformer
    public WritableMap transformToMap(FirmwareUpdateStatus firmwareUpdateStatus) {
        WritableMap map = this.containerProvider.getMap();
        map.putInt("device_id", firmwareUpdateStatus.getDeviceId());
        map.putInt("progress", (int) (firmwareUpdateStatus.getProgress() * 100.0f));
        map.putBoolean(IS_IDLE_KEY, firmwareUpdateStatus.isIdle());
        map.putBoolean(IS_IN_PROGRESS_KEY, firmwareUpdateStatus.isInProgress());
        map.putBoolean(IS_COMPLETED_KEY, firmwareUpdateStatus.isCompleted());
        map.putBoolean(IS_COMPLETED_WITH_ERROR_KEY, firmwareUpdateStatus.isCompletedWithError());
        Throwable cause = firmwareUpdateStatus.getCause();
        if (cause != null) {
            map.putString(CAUSE_KEY, cause.getMessage());
        }
        return map;
    }

    @Override // com.amazon.alexa.accessorykit.accessories.ArrayModelTransformer
    /* renamed from: transform  reason: collision with other method in class */
    public Set<Firmware.FirmwareInformation> mo597transform(ReadableArray readableArray) throws ParseException {
        throw new ParseException("Not supported", 0);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.accessorykit.accessories.MapModelTransformer
    /* renamed from: transform */
    public FirmwareUpdateStatus mo626transform(ReadableMap readableMap) throws ParseException {
        throw new ParseException("Not supported", 0);
    }
}
