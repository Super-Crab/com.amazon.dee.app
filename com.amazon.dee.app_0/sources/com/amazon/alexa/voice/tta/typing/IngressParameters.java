package com.amazon.alexa.voice.tta.typing;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.voice.tta.metrics.EventTime;
import com.google.auto.value.AutoValue;
@AutoValue
/* loaded from: classes11.dex */
public abstract class IngressParameters {
    public static final String UNKNOWN_REFERRER = "UNKNOWN";

    /* loaded from: classes11.dex */
    public interface Provider {
        @NonNull
        IngressParameters getIngressParameters();

        void update(@Nullable Intent intent);
    }

    @NonNull
    public static IngressParameters create(@NonNull String str, @NonNull EventTime eventTime) {
        return new AutoValue_IngressParameters(str, eventTime);
    }

    @NonNull
    public abstract String getReferrer();

    @NonNull
    public abstract EventTime getStartTime();
}
