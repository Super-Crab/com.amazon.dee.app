package com.amazon.alexa.mobilytics.session;

import androidx.annotation.Nullable;
/* loaded from: classes9.dex */
public interface SessionStorage {
    void clear();

    @Nullable
    Session get();

    void put(@Nullable Session session);
}
