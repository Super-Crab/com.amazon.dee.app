package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.client.alexaservice.interactions.ActivityTrackerChannelState;
import com.amazon.alexa.client.alexaservice.interactions.AutoValue_AudioActivityTrackerPayload;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.plk;
/* compiled from: $AutoValue_AudioActivityTrackerPayload.java */
/* loaded from: classes.dex */
public abstract class ezo extends plk {
    public final ActivityTrackerChannelState BIo;
    public final ActivityTrackerChannelState zQM;
    public final ActivityTrackerChannelState zZm;
    public final ActivityTrackerChannelState zyO;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: $AutoValue_AudioActivityTrackerPayload.java */
    /* loaded from: classes.dex */
    public static final class zZm extends plk.zZm {
        public ActivityTrackerChannelState BIo;
        public ActivityTrackerChannelState zQM;
        public ActivityTrackerChannelState zZm;
        public ActivityTrackerChannelState zyO;

        @Override // com.amazon.alexa.plk.zZm
        public plk.zZm BIo(@Nullable ActivityTrackerChannelState activityTrackerChannelState) {
            this.BIo = activityTrackerChannelState;
            return this;
        }

        @Override // com.amazon.alexa.plk.zZm
        public plk.zZm zQM(@Nullable ActivityTrackerChannelState activityTrackerChannelState) {
            this.zyO = activityTrackerChannelState;
            return this;
        }

        @Override // com.amazon.alexa.plk.zZm
        public plk.zZm zZm(@Nullable ActivityTrackerChannelState activityTrackerChannelState) {
            this.zQM = activityTrackerChannelState;
            return this;
        }

        @Override // com.amazon.alexa.plk.zZm
        public plk.zZm zyO(@Nullable ActivityTrackerChannelState activityTrackerChannelState) {
            this.zZm = activityTrackerChannelState;
            return this;
        }

        @Override // com.amazon.alexa.plk.zZm
        public plk zZm() {
            return new AutoValue_AudioActivityTrackerPayload(this.zZm, this.BIo, this.zQM, this.zyO);
        }
    }

    public ezo(@Nullable ActivityTrackerChannelState activityTrackerChannelState, @Nullable ActivityTrackerChannelState activityTrackerChannelState2, @Nullable ActivityTrackerChannelState activityTrackerChannelState3, @Nullable ActivityTrackerChannelState activityTrackerChannelState4) {
        this.zZm = activityTrackerChannelState;
        this.BIo = activityTrackerChannelState2;
        this.zQM = activityTrackerChannelState3;
        this.zyO = activityTrackerChannelState4;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof plk)) {
            return false;
        }
        ActivityTrackerChannelState activityTrackerChannelState = this.zZm;
        if (activityTrackerChannelState != null ? activityTrackerChannelState.equals(((ezo) obj).zZm) : ((ezo) obj).zZm == null) {
            ActivityTrackerChannelState activityTrackerChannelState2 = this.BIo;
            if (activityTrackerChannelState2 != null ? activityTrackerChannelState2.equals(((ezo) obj).BIo) : ((ezo) obj).BIo == null) {
                ActivityTrackerChannelState activityTrackerChannelState3 = this.zQM;
                if (activityTrackerChannelState3 != null ? activityTrackerChannelState3.equals(((ezo) obj).zQM) : ((ezo) obj).zQM == null) {
                    ActivityTrackerChannelState activityTrackerChannelState4 = this.zyO;
                    if (activityTrackerChannelState4 == null) {
                        if (((ezo) obj).zyO == null) {
                            return true;
                        }
                    } else if (activityTrackerChannelState4.equals(((ezo) obj).zyO)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int hashCode() {
        ActivityTrackerChannelState activityTrackerChannelState = this.zZm;
        int i = 0;
        int hashCode = ((activityTrackerChannelState == null ? 0 : activityTrackerChannelState.hashCode()) ^ 1000003) * 1000003;
        ActivityTrackerChannelState activityTrackerChannelState2 = this.BIo;
        int hashCode2 = (hashCode ^ (activityTrackerChannelState2 == null ? 0 : activityTrackerChannelState2.hashCode())) * 1000003;
        ActivityTrackerChannelState activityTrackerChannelState3 = this.zQM;
        int hashCode3 = (hashCode2 ^ (activityTrackerChannelState3 == null ? 0 : activityTrackerChannelState3.hashCode())) * 1000003;
        ActivityTrackerChannelState activityTrackerChannelState4 = this.zyO;
        if (activityTrackerChannelState4 != null) {
            i = activityTrackerChannelState4.hashCode();
        }
        return hashCode3 ^ i;
    }

    public String toString() {
        StringBuilder zZm2 = C0179Pya.zZm("AudioActivityTrackerPayload{dialog=");
        zZm2.append(this.zZm);
        zZm2.append(", communications=");
        zZm2.append(this.BIo);
        zZm2.append(", alert=");
        zZm2.append(this.zQM);
        zZm2.append(", content=");
        return C0179Pya.BIo(zZm2, this.zyO, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
