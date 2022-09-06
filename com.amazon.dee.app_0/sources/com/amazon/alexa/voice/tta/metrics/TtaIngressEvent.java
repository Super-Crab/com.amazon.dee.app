package com.amazon.alexa.voice.tta.metrics;

import androidx.annotation.NonNull;
import com.amazon.alexa.voice.ui.tta.metrics.TtaEvent;
import com.google.auto.value.AutoValue;
@AutoValue
/* loaded from: classes11.dex */
public abstract class TtaIngressEvent implements TtaEvent {
    public static final TtaIngressEvent UNKNOWN = create("UNKNOWN");

    @NonNull
    public static TtaIngressEvent create(@NonNull String str) {
        return new AutoValue_TtaIngressEvent(str);
    }

    @Override // com.amazon.alexa.voice.ui.tta.metrics.TtaEvent
    @NonNull
    public String getName() {
        return String.format("TTA_INGRESS:%s", getReferrer());
    }

    @NonNull
    public abstract String getReferrer();
}
