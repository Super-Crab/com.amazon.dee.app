package com.amazon.alexa.voice.tta.typing;

import androidx.annotation.NonNull;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.voice.tta.metrics.EventTime;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public final class AutoValue_IngressParameters extends IngressParameters {
    private final String referrer;
    private final EventTime startTime;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_IngressParameters(String str, EventTime eventTime) {
        if (str != null) {
            this.referrer = str;
            if (eventTime != null) {
                this.startTime = eventTime;
                return;
            }
            throw new NullPointerException("Null startTime");
        }
        throw new NullPointerException("Null referrer");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof IngressParameters)) {
            return false;
        }
        IngressParameters ingressParameters = (IngressParameters) obj;
        return this.referrer.equals(ingressParameters.getReferrer()) && this.startTime.equals(ingressParameters.getStartTime());
    }

    @Override // com.amazon.alexa.voice.tta.typing.IngressParameters
    @NonNull
    public String getReferrer() {
        return this.referrer;
    }

    @Override // com.amazon.alexa.voice.tta.typing.IngressParameters
    @NonNull
    public EventTime getStartTime() {
        return this.startTime;
    }

    public int hashCode() {
        return ((this.referrer.hashCode() ^ 1000003) * 1000003) ^ this.startTime.hashCode();
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("IngressParameters{referrer=");
        outline107.append(this.referrer);
        outline107.append(", startTime=");
        outline107.append(this.startTime);
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }
}
