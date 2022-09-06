package com.amazon.comms.calling.service;

import com.amazon.comms.calling.service.Call;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public class HistoricalCall {
    private final String callId;
    private final boolean dropIn;
    private final Participant localParticipant;
    private final Call.Side origin;
    private final Participant remoteParticipant;

    /* loaded from: classes11.dex */
    public static class HistoricalCallBuilder {
        private String callId;
        private boolean dropIn;
        private Participant localParticipant;
        private Call.Side origin;
        private Participant remoteParticipant;

        HistoricalCallBuilder() {
        }

        public HistoricalCall build() {
            return new HistoricalCall(this.callId, this.origin, this.localParticipant, this.remoteParticipant, this.dropIn);
        }

        public HistoricalCallBuilder callId(String str) {
            this.callId = str;
            return this;
        }

        public HistoricalCallBuilder dropIn(boolean z) {
            this.dropIn = z;
            return this;
        }

        public HistoricalCallBuilder localParticipant(Participant participant) {
            this.localParticipant = participant;
            return this;
        }

        public HistoricalCallBuilder origin(Call.Side side) {
            this.origin = side;
            return this;
        }

        public HistoricalCallBuilder remoteParticipant(Participant participant) {
            this.remoteParticipant = participant;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("HistoricalCall.HistoricalCallBuilder(callId=");
            outline107.append(this.callId);
            outline107.append(", origin=");
            outline107.append(this.origin);
            outline107.append(", localParticipant=");
            outline107.append(this.localParticipant);
            outline107.append(", remoteParticipant=");
            outline107.append(this.remoteParticipant);
            outline107.append(", dropIn=");
            return GeneratedOutlineSupport1.outline97(outline107, this.dropIn, ")");
        }

        public HistoricalCallBuilder withCall(Call call) {
            return callId(call.getCallId()).origin(call.getOrigin()).localParticipant(call.getLocalParticipant()).remoteParticipant(call.getRemoteParticipant()).dropIn(call.isDropInCall());
        }
    }

    public HistoricalCall(String str, Call.Side side, Participant participant, Participant participant2, boolean z) {
        this.callId = str;
        this.origin = side;
        this.localParticipant = participant;
        this.remoteParticipant = participant2;
        this.dropIn = z;
    }

    public static HistoricalCallBuilder builder() {
        return new HistoricalCallBuilder();
    }

    public String getCallId() {
        return this.callId;
    }

    public Participant getLocalParticipant() {
        return this.localParticipant;
    }

    public Call.Side getOrigin() {
        return this.origin;
    }

    public Participant getRemoteParticipant() {
        return this.remoteParticipant;
    }

    public boolean isDropIn() {
        return this.dropIn;
    }
}
