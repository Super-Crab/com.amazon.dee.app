package com.amazon.alexa.voice.enablement;

import java.util.Objects;
/* loaded from: classes11.dex */
final class BlacklistableDevice {
    private final String manufacturer;
    private final String model;

    private BlacklistableDevice(String str, String str2) {
        this.manufacturer = str;
        this.model = str2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BlacklistableDevice instance(String str, String str2) {
        return new BlacklistableDevice(str, str2);
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof BlacklistableDevice)) {
            return false;
        }
        return toString().equalsIgnoreCase(((BlacklistableDevice) obj).toString());
    }

    String getManufacturer() {
        return this.manufacturer;
    }

    String getModel() {
        return this.model;
    }

    public int hashCode() {
        return Objects.hash(toString());
    }

    public String toString() {
        return String.format("%s:%s", getManufacturer(), getModel());
    }
}
