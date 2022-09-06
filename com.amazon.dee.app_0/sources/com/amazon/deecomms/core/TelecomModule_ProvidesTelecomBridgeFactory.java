package com.amazon.deecomms.core;

import android.telecom.Connection;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import com.amazon.deecomms.calling.telecom.TelecomBridge;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.Map;
import java.util.Queue;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class TelecomModule_ProvidesTelecomBridgeFactory implements Factory<TelecomBridge> {
    private final Provider<Map<String, Connection>> callIdByTelecomConnectionProvider;
    private final TelecomModule module;
    private final Provider<PhoneAccountHandle> phoneAccountHandleProvider;
    private final Provider<Queue<String>> telecomCallIdsProvider;
    private final Provider<Object> telecomLockProvider;
    private final Provider<TelecomManager> telecomManagerProvider;

    public TelecomModule_ProvidesTelecomBridgeFactory(TelecomModule telecomModule, Provider<TelecomManager> provider, Provider<PhoneAccountHandle> provider2, Provider<Map<String, Connection>> provider3, Provider<Queue<String>> provider4, Provider<Object> provider5) {
        this.module = telecomModule;
        this.telecomManagerProvider = provider;
        this.phoneAccountHandleProvider = provider2;
        this.callIdByTelecomConnectionProvider = provider3;
        this.telecomCallIdsProvider = provider4;
        this.telecomLockProvider = provider5;
    }

    public static TelecomModule_ProvidesTelecomBridgeFactory create(TelecomModule telecomModule, Provider<TelecomManager> provider, Provider<PhoneAccountHandle> provider2, Provider<Map<String, Connection>> provider3, Provider<Queue<String>> provider4, Provider<Object> provider5) {
        return new TelecomModule_ProvidesTelecomBridgeFactory(telecomModule, provider, provider2, provider3, provider4, provider5);
    }

    public static TelecomBridge provideInstance(TelecomModule telecomModule, Provider<TelecomManager> provider, Provider<PhoneAccountHandle> provider2, Provider<Map<String, Connection>> provider3, Provider<Queue<String>> provider4, Provider<Object> provider5) {
        return (TelecomBridge) Preconditions.checkNotNull(telecomModule.providesTelecomBridge(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static TelecomBridge proxyProvidesTelecomBridge(TelecomModule telecomModule, TelecomManager telecomManager, PhoneAccountHandle phoneAccountHandle, Map<String, Connection> map, Queue<String> queue, Object obj) {
        return (TelecomBridge) Preconditions.checkNotNull(telecomModule.providesTelecomBridge(telecomManager, phoneAccountHandle, map, queue, obj), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TelecomBridge mo10268get() {
        return provideInstance(this.module, this.telecomManagerProvider, this.phoneAccountHandleProvider, this.callIdByTelecomConnectionProvider, this.telecomCallIdsProvider, this.telecomLockProvider);
    }
}
