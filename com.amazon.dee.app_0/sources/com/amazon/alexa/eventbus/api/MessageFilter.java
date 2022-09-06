package com.amazon.alexa.eventbus.api;

import androidx.annotation.NonNull;
@FunctionalInterface
/* loaded from: classes7.dex */
public interface MessageFilter {
    boolean isMatch(@NonNull Message message);
}
