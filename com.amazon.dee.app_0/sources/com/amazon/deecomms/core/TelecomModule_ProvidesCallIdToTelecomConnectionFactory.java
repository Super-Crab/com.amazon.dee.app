package com.amazon.deecomms.core;

import android.telecom.Connection;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.Map;
/* loaded from: classes12.dex */
public final class TelecomModule_ProvidesCallIdToTelecomConnectionFactory implements Factory<Map<String, Connection>> {
    private final TelecomModule module;

    public TelecomModule_ProvidesCallIdToTelecomConnectionFactory(TelecomModule telecomModule) {
        this.module = telecomModule;
    }

    public static TelecomModule_ProvidesCallIdToTelecomConnectionFactory create(TelecomModule telecomModule) {
        return new TelecomModule_ProvidesCallIdToTelecomConnectionFactory(telecomModule);
    }

    public static Map<String, Connection> provideInstance(TelecomModule telecomModule) {
        return (Map) Preconditions.checkNotNull(telecomModule.providesCallIdToTelecomConnection(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Map<String, Connection> proxyProvidesCallIdToTelecomConnection(TelecomModule telecomModule) {
        return (Map) Preconditions.checkNotNull(telecomModule.providesCallIdToTelecomConnection(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get  reason: collision with other method in class */
    public Map<String, Connection> mo10268get() {
        return provideInstance(this.module);
    }
}
