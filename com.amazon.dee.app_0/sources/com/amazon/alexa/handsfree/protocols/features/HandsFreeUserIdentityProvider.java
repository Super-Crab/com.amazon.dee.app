package com.amazon.alexa.handsfree.protocols.features;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfScope;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeUserIdentity;
import java.util.HashSet;
import java.util.Set;
@AhfScope
/* loaded from: classes8.dex */
public class HandsFreeUserIdentityProvider {
    private HandsFreeUserIdentity mHandsFreeUserIdentity;
    private final Set<HandsFreeUserIdentity.Listener> mListeners = new HashSet();

    public void addListener(@NonNull HandsFreeUserIdentity.Listener listener) {
        this.mListeners.add(listener);
    }

    @Nullable
    public synchronized HandsFreeUserIdentity getHandsFreeUserIdentity() {
        return this.mHandsFreeUserIdentity;
    }

    public synchronized void setHandsFreeUserIdentity(@Nullable HandsFreeUserIdentity handsFreeUserIdentity) {
        boolean z = handsFreeUserIdentity != this.mHandsFreeUserIdentity;
        this.mHandsFreeUserIdentity = handsFreeUserIdentity;
        if (z) {
            for (HandsFreeUserIdentity.Listener listener : this.mListeners) {
                listener.onHandsFreeUserIdentityChanged(handsFreeUserIdentity);
            }
        }
    }
}
