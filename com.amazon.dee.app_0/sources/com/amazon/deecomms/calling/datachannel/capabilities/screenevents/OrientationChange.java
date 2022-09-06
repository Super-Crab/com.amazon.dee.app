package com.amazon.deecomms.calling.datachannel.capabilities.screenevents;

import com.amazon.deecomms.calling.datachannel.InCallOrientation;
import com.amazon.device.messaging.ADMConstants;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes12.dex */
public final class OrientationChange {
    private Orientation orientation;

    /* loaded from: classes12.dex */
    public static final class Builder {
        private Orientation orientation;

        public OrientationChange build() {
            return new OrientationChange(this.orientation, null);
        }

        public Builder with(InCallOrientation inCallOrientation, InCallOrientation inCallOrientation2) {
            this.orientation = new Orientation(inCallOrientation, inCallOrientation2);
            return this;
        }
    }

    /* loaded from: classes12.dex */
    public static class Orientation {
        @SerializedName(ADMConstants.EXTRA_FROM)
        private InCallOrientation from;
        @SerializedName("to")
        private InCallOrientation to;

        public Orientation(InCallOrientation inCallOrientation, InCallOrientation inCallOrientation2) {
            this.from = inCallOrientation;
            this.to = inCallOrientation2;
        }

        public InCallOrientation getFrom() {
            return this.from;
        }

        public InCallOrientation getTo() {
            return this.to;
        }
    }

    private OrientationChange(Orientation orientation) {
        this.orientation = orientation;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Orientation getOrientation() {
        return this.orientation;
    }

    /* synthetic */ OrientationChange(Orientation orientation, AnonymousClass1 anonymousClass1) {
        this.orientation = orientation;
    }
}
