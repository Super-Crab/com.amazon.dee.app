package com.amazon.alexa.handsfree.protocols.features;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes8.dex */
public interface HandsFreeUserIdentity {

    /* loaded from: classes8.dex */
    public interface Listener {
        void onHandsFreeUserIdentityChanged(@Nullable HandsFreeUserIdentity handsFreeUserIdentity);
    }

    boolean hasComponent(@NonNull HandsFreeComponent handsFreeComponent);
}
