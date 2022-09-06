package com.amazon.alexa.accessorykit.finishsetup;

import android.os.Bundle;
import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.internal.util.AccessoryUtils;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceGroup;
import com.amazon.alexa.accessorykit.ContainerProvider;
import com.amazon.alexa.accessorykit.ModelTransformer;
import com.amazon.alexa.accessorykit.accessories.AccessoryModelTransformer;
import com.amazon.alexa.accessorykit.accessories.MapModelTransformer;
import com.amazon.deecomms.common.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import java.text.ParseException;
import java.util.Objects;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes6.dex */
public final class FinishSetupEvent {
    private final Accessory accessory;
    private final Integer color;
    private final String deviceType;
    private final String serialNumber;

    /* loaded from: classes6.dex */
    public static final class Transformer implements MapModelTransformer<FinishSetupEvent> {
        private static final String ACCESSORY_KEY = "accessory";
        private static final String COLOR_KEY = "color";
        private static final String DEVICE_TYPE_KEY = "deviceType";
        private static final String DSN_KEY = "serialNumber";
        private final AccessoryModelTransformer accessoryMapModelTransformer;
        private final ContainerProvider containerProvider;

        public Transformer(ContainerProvider containerProvider) {
            Preconditions.notNull(containerProvider, "containerProvider");
            this.containerProvider = containerProvider;
            this.accessoryMapModelTransformer = new AccessoryModelTransformer(containerProvider);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.accessorykit.accessories.MapModelTransformer
        /* renamed from: transform */
        public FinishSetupEvent mo626transform(ReadableMap readableMap) throws ParseException {
            try {
                return new FinishSetupEvent(this.accessoryMapModelTransformer.mo626transform(readableMap.mo6945getMap("accessory")), readableMap.getString("deviceType"), readableMap.getString("serialNumber"), Integer.valueOf(readableMap.getInt("color")));
            } catch (Exception e) {
                throw new ParseException(e.toString(), 0);
            }
        }

        @Override // com.amazon.alexa.accessorykit.accessories.MapModelTransformer
        public WritableMap transformToMap(FinishSetupEvent finishSetupEvent) {
            WritableMap map = this.containerProvider.getMap();
            map.putMap("accessory", this.accessoryMapModelTransformer.transformToMap(finishSetupEvent.getAccessory()));
            map.putString("serialNumber", finishSetupEvent.getSerialNumber());
            map.putString("deviceType", finishSetupEvent.getDeviceType());
            map.putInt("color", finishSetupEvent.getColor().intValue());
            return map;
        }
    }

    public FinishSetupEvent(Accessory accessory, String str, String str2, Integer num) {
        Preconditions.notNull(accessory, ModelTransformer.KEY_ACCESSORY);
        Preconditions.notNull(str, "deviceType");
        Preconditions.notNull(str2, Constants.BUNDLE_SERIAL_NUMBER);
        Preconditions.notNull(num, "color");
        this.accessory = accessory;
        this.deviceType = str;
        this.serialNumber = str2;
        this.color = num;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || FinishSetupEvent.class != obj.getClass()) {
            return false;
        }
        FinishSetupEvent finishSetupEvent = (FinishSetupEvent) obj;
        return Objects.equals(this.accessory, finishSetupEvent.accessory) && Objects.equals(this.deviceType, finishSetupEvent.deviceType) && Objects.equals(this.serialNumber, finishSetupEvent.serialNumber) && Objects.equals(this.color, finishSetupEvent.color);
    }

    public Accessory getAccessory() {
        return this.accessory;
    }

    public Integer getColor() {
        return this.color;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public String getSerialNumber() {
        return this.serialNumber;
    }

    public int hashCode() {
        return Objects.hash(this.accessory, this.deviceType, this.serialNumber, this.color);
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(ModelTransformer.KEY_ACCESSORY, this.accessory);
        bundle.putString(Constants.BUNDLE_SERIAL_NUMBER, this.serialNumber);
        bundle.putString("deviceType", this.deviceType);
        bundle.putInt("color", this.color.intValue());
        return bundle;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("FinishSetupEvent{accessory=");
        outline107.append(this.accessory);
        outline107.append(", deviceType='");
        GeneratedOutlineSupport1.outline176(outline107, this.deviceType, Chars.QUOTE, ", serialNumber='");
        GeneratedOutlineSupport1.outline176(outline107, this.serialNumber, Chars.QUOTE, ", color=");
        outline107.append(this.color);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }

    public FinishSetupEvent(DeviceGroup deviceGroup) {
        this(AccessoryUtils.getAccessoryFromDeviceGroup(deviceGroup), deviceGroup.getDeviceWithHighestDeviceId().getType(), deviceGroup.getDeviceWithHighestDeviceId().getSerialNumber(), deviceGroup.getDeviceWithHighestDeviceId().getColor());
    }

    public FinishSetupEvent(Bundle bundle) {
        this((Accessory) bundle.getParcelable(ModelTransformer.KEY_ACCESSORY), bundle.getString("deviceType"), bundle.getString(Constants.BUNDLE_SERIAL_NUMBER), Integer.valueOf(bundle.getInt("color")));
    }
}
