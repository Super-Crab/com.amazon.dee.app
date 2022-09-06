package com.amazon.alexa.accessory.avsclient.bootup;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import java.util.Date;
/* loaded from: classes.dex */
public class AccessoryBootUpMetadata {
    private final String deviceType;
    private Date poweredOnTime;

    /* loaded from: classes.dex */
    public static final class Builder {
        private String deviceType;
        private Date poweredOnTime;

        public AccessoryBootUpMetadata build() {
            Preconditions.notNull(this.deviceType, "deviceType");
            Preconditions.notNull(this.poweredOnTime, "poweredOnTime");
            return new AccessoryBootUpMetadata(this);
        }

        public Builder setDeviceType(String str) {
            this.deviceType = str;
            return this;
        }

        public Builder setPoweredOnTime(Date date) {
            this.poweredOnTime = date;
            return this;
        }
    }

    public AccessoryBootUpMetadata(Builder builder) {
        this.deviceType = builder.deviceType;
        this.poweredOnTime = builder.poweredOnTime;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public Date getPoweredOnTime() {
        return this.poweredOnTime;
    }

    public void setPoweredOnTime(Date date) {
        this.poweredOnTime = date;
    }
}
