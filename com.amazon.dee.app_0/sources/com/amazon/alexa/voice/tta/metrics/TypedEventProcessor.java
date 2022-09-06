package com.amazon.alexa.voice.tta.metrics;

import androidx.annotation.NonNull;
import com.amazon.alexa.voice.ui.tta.metrics.TtaEvent;
import java.util.Objects;
/* loaded from: classes11.dex */
public abstract class TypedEventProcessor<E extends TtaEvent> implements MetricEventProcessor {
    private final Class<E> eventClass;

    /* loaded from: classes11.dex */
    public interface Listener<E extends TtaEvent> {
        boolean onEvent(@NonNull E e, @NonNull EventTime eventTime);
    }

    public TypedEventProcessor(Class<E> cls) {
        this.eventClass = cls;
    }

    public static <E extends TtaEvent> TypedEventProcessor<E> create(Class<E> cls, final Listener<E> listener) {
        return (TypedEventProcessor<E>) new TypedEventProcessor<E>(cls) { // from class: com.amazon.alexa.voice.tta.metrics.TypedEventProcessor.1
            @Override // com.amazon.alexa.voice.tta.metrics.TypedEventProcessor
            public boolean processTypedEvent(@NonNull E e, @NonNull EventTime eventTime) {
                return listener.onEvent(e, eventTime);
            }
        };
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.amazon.alexa.voice.tta.metrics.MetricEventProcessor
    public boolean processEvent(@NonNull TtaEvent ttaEvent, @NonNull EventTime eventTime) {
        if (!this.eventClass.isInstance(ttaEvent)) {
            return false;
        }
        return processTypedEvent((TtaEvent) Objects.requireNonNull(this.eventClass.cast(ttaEvent)), eventTime);
    }

    public abstract boolean processTypedEvent(@NonNull E e, @NonNull EventTime eventTime);
}
