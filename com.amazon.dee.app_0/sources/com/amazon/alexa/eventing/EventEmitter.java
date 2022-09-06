package com.amazon.alexa.eventing;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
@Deprecated
/* loaded from: classes7.dex */
public interface EventEmitter<T> {
    @CheckResult
    EventSubscription subscribe(@NonNull EventHandler<T> eventHandler);
}
