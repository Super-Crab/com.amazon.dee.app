package com.amazon.alexa.smarthomecameras.ptz.directives;

import com.amazon.alexa.smarthomecameras.directives.AlexaPayload;
/* loaded from: classes10.dex */
public class AdjustRangePayload implements AlexaPayload {
    private final long rangeValueDelta;
    private final boolean rangeValueDeltaDefault;

    private AdjustRangePayload(long j, boolean z) {
        this.rangeValueDelta = j;
        this.rangeValueDeltaDefault = z;
    }

    public static AdjustRangePayload create(long j) {
        return new AdjustRangePayload(j, false);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AdjustRangePayload.class != obj.getClass()) {
            return false;
        }
        AdjustRangePayload adjustRangePayload = (AdjustRangePayload) obj;
        return this.rangeValueDelta == adjustRangePayload.rangeValueDelta && this.rangeValueDeltaDefault == adjustRangePayload.rangeValueDeltaDefault;
    }

    public long getRangeValueDelta() {
        return this.rangeValueDelta;
    }

    public boolean getRangeValueDeltaDefault() {
        return this.rangeValueDeltaDefault;
    }
}
