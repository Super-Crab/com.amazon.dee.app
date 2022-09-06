package com.amazon.alexa.eventing;

import androidx.annotation.NonNull;
@Deprecated
/* loaded from: classes7.dex */
public interface EventHandler<T> {
    void onEvent(@NonNull EventArgs<T> eventArgs);
}
