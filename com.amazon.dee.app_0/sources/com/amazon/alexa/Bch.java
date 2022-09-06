package com.amazon.alexa;

import com.amazon.alexa.client.core.device.PersistentStorage;
import dagger.Lazy;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
/* compiled from: AlexaNotificationPreferences.java */
@Singleton
/* loaded from: classes.dex */
public class Bch {
    public final Lazy<PersistentStorage> zZm;

    @Inject
    public Bch(@Named("AlexaNotificationPreferences") Lazy<PersistentStorage> lazy) {
        this.zZm = lazy;
    }
}
