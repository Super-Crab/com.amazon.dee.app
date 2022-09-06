package com.amazon.alexa.voice.tta.metrics;

import androidx.annotation.NonNull;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public final class AutoValue_TtaIngressEvent extends TtaIngressEvent {
    private final String referrer;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_TtaIngressEvent(String str) {
        if (str != null) {
            this.referrer = str;
            return;
        }
        throw new NullPointerException("Null referrer");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TtaIngressEvent)) {
            return false;
        }
        return this.referrer.equals(((TtaIngressEvent) obj).getReferrer());
    }

    @Override // com.amazon.alexa.voice.tta.metrics.TtaIngressEvent
    @NonNull
    public String getReferrer() {
        return this.referrer;
    }

    public int hashCode() {
        return this.referrer.hashCode() ^ 1000003;
    }

    public String toString() {
        return GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("TtaIngressEvent{referrer="), this.referrer, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
