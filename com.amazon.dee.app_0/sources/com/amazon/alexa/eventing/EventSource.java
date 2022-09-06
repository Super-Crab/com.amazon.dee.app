package com.amazon.alexa.eventing;

import androidx.annotation.NonNull;
@Deprecated
/* loaded from: classes7.dex */
public interface EventSource<T> {
    void fire(@NonNull EventArgs<T> eventArgs);
}
