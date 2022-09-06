package com.amazon.alexa.presence.dagger;

import android.content.Context;
/* loaded from: classes9.dex */
public final class PresenceComponentSingleton {
    private static volatile PresenceComponent instance;

    private PresenceComponentSingleton() {
    }

    public static PresenceComponent getInstance(Context context) {
        if (instance == null) {
            synchronized (PresenceComponent.class) {
                if (instance == null) {
                    instance = DaggerPresenceComponent.builder().presenceModule(new PresenceModule(context.getApplicationContext())).build();
                }
            }
        }
        return instance;
    }
}
