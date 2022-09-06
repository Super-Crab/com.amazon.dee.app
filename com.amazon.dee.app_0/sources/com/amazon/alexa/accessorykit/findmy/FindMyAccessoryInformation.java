package com.amazon.alexa.accessorykit.findmy;

import com.amazon.alexa.accessorykit.findmy.AutoValue_FindMyAccessoryInformation;
import com.google.auto.value.AutoValue;
@AutoValue
/* loaded from: classes6.dex */
public abstract class FindMyAccessoryInformation {

    @AutoValue.Builder
    /* loaded from: classes6.dex */
    public static abstract class Builder {
        public abstract FindMyAccessoryInformation build();

        public abstract Builder setDeviceSerialNumber(String str);

        public abstract Builder setDeviceType(String str);

        public abstract Builder setEventCause(EventCause eventCause);

        public abstract Builder setIdentifier(String str);
    }

    /* loaded from: classes6.dex */
    public enum EventCause {
        BLUETOOTH_LINK("BlueToothLink"),
        BLUETOOTH_UNLINK("BlueToothUnLink"),
        PRESENCE("Presence"),
        BATTERY("Battery");
        
        public String description;

        EventCause(String str) {
            this.description = str;
        }

        public String getDescription() {
            return this.description;
        }
    }

    public static Builder builder() {
        return new AutoValue_FindMyAccessoryInformation.Builder();
    }

    public abstract String getDeviceSerialNumber();

    public abstract String getDeviceType();

    public abstract EventCause getEventCause();

    public abstract String getIdentifier();
}
