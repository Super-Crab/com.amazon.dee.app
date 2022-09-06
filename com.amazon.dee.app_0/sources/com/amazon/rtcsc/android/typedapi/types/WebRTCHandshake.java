package com.amazon.rtcsc.android.typedapi.types;

import lombok.NonNull;
/* loaded from: classes13.dex */
public class WebRTCHandshake {
    @NonNull
    private String format;
    @NonNull
    private String value;

    public WebRTCHandshake(@NonNull String str, @NonNull String str2) {
        if (str != null) {
            if (str2 == null) {
                throw new NullPointerException("value");
            }
            this.format = str;
            this.value = str2;
            return;
        }
        throw new NullPointerException("format");
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof WebRTCHandshake;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof WebRTCHandshake)) {
            return false;
        }
        WebRTCHandshake webRTCHandshake = (WebRTCHandshake) obj;
        if (!webRTCHandshake.canEqual(this)) {
            return false;
        }
        String format = getFormat();
        String format2 = webRTCHandshake.getFormat();
        if (format != null ? !format.equals(format2) : format2 != null) {
            return false;
        }
        String value = getValue();
        String value2 = webRTCHandshake.getValue();
        return value != null ? value.equals(value2) : value2 == null;
    }

    @NonNull
    public String getFormat() {
        return this.format;
    }

    @NonNull
    public String getValue() {
        return this.value;
    }

    public int hashCode() {
        String format = getFormat();
        int i = 43;
        int hashCode = format == null ? 43 : format.hashCode();
        String value = getValue();
        int i2 = (hashCode + 59) * 59;
        if (value != null) {
            i = value.hashCode();
        }
        return i2 + i;
    }
}
