package com.amazon.rtcmedia.webrtc;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public final class RTCDataChannelParams {
    private final String label;
    private final boolean ordered;

    /* loaded from: classes13.dex */
    public static class RTCDataChannelParamsBuilder {
        private String label;
        private boolean ordered;

        RTCDataChannelParamsBuilder() {
        }

        public RTCDataChannelParams build() {
            return new RTCDataChannelParams(this.label, this.ordered);
        }

        public RTCDataChannelParamsBuilder label(String str) {
            this.label = str;
            return this;
        }

        public RTCDataChannelParamsBuilder ordered(boolean z) {
            this.ordered = z;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("RTCDataChannelParams.RTCDataChannelParamsBuilder(label=");
            outline107.append(this.label);
            outline107.append(", ordered=");
            return GeneratedOutlineSupport1.outline97(outline107, this.ordered, ")");
        }
    }

    RTCDataChannelParams(String str, boolean z) {
        this.label = str;
        this.ordered = z;
    }

    public static RTCDataChannelParamsBuilder builder() {
        return new RTCDataChannelParamsBuilder();
    }

    public String getLabel() {
        return this.label;
    }

    public boolean isOrdered() {
        return this.ordered;
    }
}
