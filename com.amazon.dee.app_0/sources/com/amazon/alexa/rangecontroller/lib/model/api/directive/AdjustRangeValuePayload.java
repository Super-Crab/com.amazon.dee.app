package com.amazon.alexa.rangecontroller.lib.model.api.directive;

import com.android.tools.r8.GeneratedOutlineSupport1;
import edu.umd.cs.findbugs.annotations.CheckForNull;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Generated;
/* loaded from: classes9.dex */
public final class AdjustRangeValuePayload {
    @CheckForNull
    private final Double rangeValueDelta;
    @CheckForNull
    private final Boolean rangeValueDeltaDefault;

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    /* loaded from: classes9.dex */
    public static class AdjustRangeValuePayloadBuilder {
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private Double rangeValueDelta;
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private Boolean rangeValueDeltaDefault;

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        AdjustRangeValuePayloadBuilder() {
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public AdjustRangeValuePayload build() {
            return new AdjustRangeValuePayload(this.rangeValueDelta, this.rangeValueDeltaDefault);
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public AdjustRangeValuePayloadBuilder rangeValueDelta(Double d) {
            this.rangeValueDelta = d;
            return this;
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public AdjustRangeValuePayloadBuilder rangeValueDeltaDefault(Boolean bool) {
            this.rangeValueDeltaDefault = bool;
            return this;
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AdjustRangeValuePayload.AdjustRangeValuePayloadBuilder(rangeValueDelta=");
            outline107.append(this.rangeValueDelta);
            outline107.append(", rangeValueDeltaDefault=");
            outline107.append(this.rangeValueDeltaDefault);
            outline107.append(")");
            return outline107.toString();
        }
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    AdjustRangeValuePayload(Double d, Boolean bool) {
        this.rangeValueDelta = d;
        this.rangeValueDeltaDefault = bool;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public static AdjustRangeValuePayloadBuilder builder() {
        return new AdjustRangeValuePayloadBuilder();
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AdjustRangeValuePayload)) {
            return false;
        }
        AdjustRangeValuePayload adjustRangeValuePayload = (AdjustRangeValuePayload) obj;
        Double rangeValueDelta = getRangeValueDelta();
        Double rangeValueDelta2 = adjustRangeValuePayload.getRangeValueDelta();
        if (rangeValueDelta != null ? !rangeValueDelta.equals(rangeValueDelta2) : rangeValueDelta2 != null) {
            return false;
        }
        Boolean rangeValueDeltaDefault = getRangeValueDeltaDefault();
        Boolean rangeValueDeltaDefault2 = adjustRangeValuePayload.getRangeValueDeltaDefault();
        return rangeValueDeltaDefault != null ? rangeValueDeltaDefault.equals(rangeValueDeltaDefault2) : rangeValueDeltaDefault2 == null;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public Double getRangeValueDelta() {
        return this.rangeValueDelta;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public Boolean getRangeValueDeltaDefault() {
        return this.rangeValueDeltaDefault;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public int hashCode() {
        Double rangeValueDelta = getRangeValueDelta();
        int i = 43;
        int hashCode = rangeValueDelta == null ? 43 : rangeValueDelta.hashCode();
        Boolean rangeValueDeltaDefault = getRangeValueDeltaDefault();
        int i2 = (hashCode + 59) * 59;
        if (rangeValueDeltaDefault != null) {
            i = rangeValueDeltaDefault.hashCode();
        }
        return i2 + i;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AdjustRangeValuePayload(rangeValueDelta=");
        outline107.append(getRangeValueDelta());
        outline107.append(", rangeValueDeltaDefault=");
        outline107.append(getRangeValueDeltaDefault());
        outline107.append(")");
        return outline107.toString();
    }
}
