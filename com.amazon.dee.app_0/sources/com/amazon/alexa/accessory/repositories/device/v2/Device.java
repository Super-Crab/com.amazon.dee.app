package com.amazon.alexa.accessory.repositories.device.v2;

import android.os.Bundle;
import androidx.annotation.NonNull;
import com.amazon.alexa.accessory.internal.util.DeviceDatabaseUtils;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Objects;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes6.dex */
public final class Device {
    private static final String COLOR_KEY = "color";
    private static final String DEVICE_ID_KEY = "deviceId";
    private static final String NAME_KEY = "name";
    private static final String SERIAL_NUMBER_KEY = "serialNumber";
    private static final String TYPE_KEY = "type";
    private final Integer color;
    private final Integer deviceId;
    private final String name;
    private final String serialNumber;
    private final String type;

    public static Device fromBundle(Bundle bundle) {
        return newBuilder().name(bundle.getString("name")).serialNumber(bundle.getString("serialNumber")).type(bundle.getString("type")).deviceId(Integer.valueOf(bundle.getInt("deviceId"))).color(Integer.valueOf(bundle.getInt("color"))).build();
    }

    @NonNull
    public static Builder newBuilder() {
        return new Builder();
    }

    public static Bundle toBundle(Device device) {
        Bundle bundle = new Bundle();
        bundle.putString("name", device.name);
        bundle.putString("serialNumber", device.serialNumber);
        bundle.putString("type", device.type);
        bundle.putInt("deviceId", device.deviceId.intValue());
        bundle.putInt("color", device.color.intValue());
        return bundle;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Device.class != obj.getClass()) {
            return false;
        }
        Device device = (Device) obj;
        return Objects.equals(this.name, device.name) && Objects.equals(this.serialNumber, device.serialNumber) && Objects.equals(this.type, device.type) && Objects.equals(this.deviceId, device.deviceId) && Objects.equals(this.color, device.color);
    }

    @NonNull
    public Integer getColor() {
        return this.color;
    }

    @NonNull
    public Integer getDeviceId() {
        return this.deviceId;
    }

    @NonNull
    public String getName() {
        return this.name;
    }

    @NonNull
    public String getSerialNumber() {
        return this.serialNumber;
    }

    @NonNull
    public String getType() {
        return this.type;
    }

    public int hashCode() {
        return Objects.hash(this.name, this.serialNumber, this.type, this.deviceId, this.color);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Device{name='");
        GeneratedOutlineSupport1.outline176(outline107, this.name, Chars.QUOTE, ", serialNumber='");
        GeneratedOutlineSupport1.outline176(outline107, this.serialNumber, Chars.QUOTE, ", type='");
        GeneratedOutlineSupport1.outline176(outline107, this.type, Chars.QUOTE, ", deviceId=");
        outline107.append(this.deviceId);
        outline107.append(", color=");
        outline107.append(this.color);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }

    private Device(@NonNull Builder builder) {
        Preconditions.notNull(builder, "builder");
        Preconditions.notNull(builder.name, "name");
        Preconditions.notNull(builder.serialNumber, "serialNumber");
        Preconditions.notNull(builder.type, "type");
        Preconditions.notNull(builder.deviceId, "deviceId");
        Preconditions.notNull(builder.color, "color");
        this.name = DeviceDatabaseUtils.sanitize(builder.name);
        this.serialNumber = DeviceDatabaseUtils.sanitize(builder.serialNumber);
        this.type = DeviceDatabaseUtils.sanitize(builder.type);
        this.deviceId = builder.deviceId;
        this.color = builder.color;
    }

    @NonNull
    public static Builder newBuilder(@NonNull Device device) {
        Preconditions.notNull(device, "device");
        return new Builder(device);
    }

    /* loaded from: classes6.dex */
    public static final class Builder {
        private Integer color;
        private Integer deviceId;
        private String name;
        private String serialNumber;
        private String type;

        public Builder(@NonNull Device device) {
            Preconditions.notNull(device, "device");
            this.name = device.getName();
            this.serialNumber = device.getSerialNumber();
            this.type = device.getType();
            this.deviceId = device.getDeviceId();
            this.color = device.getColor();
        }

        @NonNull
        public Device build() {
            return new Device(this);
        }

        public Builder color(Integer num) {
            this.color = num;
            return this;
        }

        public Builder deviceId(Integer num) {
            this.deviceId = num;
            return this;
        }

        public Builder name(String str) {
            this.name = str;
            return this;
        }

        public Builder serialNumber(String str) {
            this.serialNumber = str;
            return this;
        }

        public Builder type(String str) {
            this.type = str;
            return this;
        }

        public Builder() {
        }
    }
}
