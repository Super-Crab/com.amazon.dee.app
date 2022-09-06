package com.amazon.comms.models.gui;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public class TimingInfo {
    private TimestampPair guiCallRequestTime;
    private TimestampPair outboundCallRequestTime;

    /* loaded from: classes11.dex */
    public static class TimingInfoBuilder {
        private TimestampPair guiCallRequestTime;
        private TimestampPair outboundCallRequestTime;

        TimingInfoBuilder() {
        }

        public TimingInfo build() {
            return new TimingInfo(this.guiCallRequestTime, this.outboundCallRequestTime);
        }

        public TimingInfoBuilder guiCallRequestTime(TimestampPair timestampPair) {
            this.guiCallRequestTime = timestampPair;
            return this;
        }

        public TimingInfoBuilder outboundCallRequestTime(TimestampPair timestampPair) {
            this.outboundCallRequestTime = timestampPair;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("TimingInfo.TimingInfoBuilder(guiCallRequestTime=");
            outline107.append(this.guiCallRequestTime);
            outline107.append(", outboundCallRequestTime=");
            outline107.append(this.outboundCallRequestTime);
            outline107.append(")");
            return outline107.toString();
        }
    }

    TimingInfo(TimestampPair timestampPair, TimestampPair timestampPair2) {
        this.guiCallRequestTime = timestampPair;
        this.outboundCallRequestTime = timestampPair2;
    }

    public static TimingInfoBuilder builder() {
        return new TimingInfoBuilder();
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof TimingInfo;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TimingInfo)) {
            return false;
        }
        TimingInfo timingInfo = (TimingInfo) obj;
        if (!timingInfo.canEqual(this)) {
            return false;
        }
        TimestampPair guiCallRequestTime = getGuiCallRequestTime();
        TimestampPair guiCallRequestTime2 = timingInfo.getGuiCallRequestTime();
        if (guiCallRequestTime != null ? !guiCallRequestTime.equals(guiCallRequestTime2) : guiCallRequestTime2 != null) {
            return false;
        }
        TimestampPair outboundCallRequestTime = getOutboundCallRequestTime();
        TimestampPair outboundCallRequestTime2 = timingInfo.getOutboundCallRequestTime();
        return outboundCallRequestTime != null ? outboundCallRequestTime.equals(outboundCallRequestTime2) : outboundCallRequestTime2 == null;
    }

    public TimestampPair getGuiCallRequestTime() {
        return this.guiCallRequestTime;
    }

    public TimestampPair getOutboundCallRequestTime() {
        return this.outboundCallRequestTime;
    }

    public int hashCode() {
        TimestampPair guiCallRequestTime = getGuiCallRequestTime();
        int i = 43;
        int hashCode = guiCallRequestTime == null ? 43 : guiCallRequestTime.hashCode();
        TimestampPair outboundCallRequestTime = getOutboundCallRequestTime();
        int i2 = (hashCode + 59) * 59;
        if (outboundCallRequestTime != null) {
            i = outboundCallRequestTime.hashCode();
        }
        return i2 + i;
    }
}
