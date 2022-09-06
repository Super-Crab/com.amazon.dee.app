package com.amazon.comms.calling.sipclient;

import com.android.tools.r8.GeneratedOutlineSupport1;
import javax.annotation.Nonnull;
/* loaded from: classes11.dex */
public final class MediaRelayInfo {
    @Nonnull
    private final TurnEndPointInfo callee;
    @Nonnull
    private final TurnEndPointInfo caller;

    /* loaded from: classes11.dex */
    public static class MediaRelayInfoBuilder {
        private TurnEndPointInfo callee;
        private TurnEndPointInfo caller;

        MediaRelayInfoBuilder() {
        }

        public MediaRelayInfo build() {
            return new MediaRelayInfo(this.caller, this.callee);
        }

        public MediaRelayInfoBuilder callee(TurnEndPointInfo turnEndPointInfo) {
            this.callee = turnEndPointInfo;
            return this;
        }

        public MediaRelayInfoBuilder caller(TurnEndPointInfo turnEndPointInfo) {
            this.caller = turnEndPointInfo;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MediaRelayInfo.MediaRelayInfoBuilder(caller=");
            outline107.append(this.caller);
            outline107.append(", callee=");
            outline107.append(this.callee);
            outline107.append(")");
            return outline107.toString();
        }
    }

    MediaRelayInfo(@Nonnull TurnEndPointInfo turnEndPointInfo, @Nonnull TurnEndPointInfo turnEndPointInfo2) {
        if (turnEndPointInfo != null) {
            if (turnEndPointInfo2 == null) {
                throw new IllegalArgumentException("callee is null");
            }
            this.caller = turnEndPointInfo;
            this.callee = turnEndPointInfo2;
            return;
        }
        throw new IllegalArgumentException("caller is null");
    }

    public static MediaRelayInfoBuilder builder() {
        return new MediaRelayInfoBuilder();
    }

    @Nonnull
    public TurnEndPointInfo getCallee() {
        return this.callee;
    }

    @Nonnull
    public TurnEndPointInfo getCaller() {
        return this.caller;
    }
}
