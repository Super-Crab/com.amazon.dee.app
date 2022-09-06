package com.amazon.alexa.voice.tta.metrics;

import androidx.annotation.NonNull;
import com.amazon.alexa.voice.ui.tta.metrics.TtaEvent;
/* loaded from: classes11.dex */
public interface MetricEventProcessor {
    boolean processEvent(@NonNull TtaEvent ttaEvent, @NonNull EventTime eventTime);
}
