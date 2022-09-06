package com.amazon.deecomms.calling.telecom;

import android.telecom.Connection;
import com.amazon.deecomms.calling.telecom.audiomanagement.TelecomCallAudioRouteObservable;
import dagger.MembersInjector;
import java.util.Map;
import java.util.Queue;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class TelecomConnectionService_MembersInjector implements MembersInjector<TelecomConnectionService> {
    private final Provider<Map<String, Connection>> callIdToTelecomConnectionProvider;
    private final Provider<TelecomBridge> telecomBridgeProvider;
    private final Provider<TelecomCallAudioRouteObservable> telecomCallAudioRouteObservableProvider;
    private final Provider<Queue<String>> telecomCallIdsProvider;
    private final Provider<Object> telecomLockProvider;

    public TelecomConnectionService_MembersInjector(Provider<Map<String, Connection>> provider, Provider<Queue<String>> provider2, Provider<Object> provider3, Provider<TelecomBridge> provider4, Provider<TelecomCallAudioRouteObservable> provider5) {
        this.callIdToTelecomConnectionProvider = provider;
        this.telecomCallIdsProvider = provider2;
        this.telecomLockProvider = provider3;
        this.telecomBridgeProvider = provider4;
        this.telecomCallAudioRouteObservableProvider = provider5;
    }

    public static MembersInjector<TelecomConnectionService> create(Provider<Map<String, Connection>> provider, Provider<Queue<String>> provider2, Provider<Object> provider3, Provider<TelecomBridge> provider4, Provider<TelecomCallAudioRouteObservable> provider5) {
        return new TelecomConnectionService_MembersInjector(provider, provider2, provider3, provider4, provider5);
    }

    public static void injectCallIdToTelecomConnection(TelecomConnectionService telecomConnectionService, Map<String, Connection> map) {
        telecomConnectionService.callIdToTelecomConnection = map;
    }

    public static void injectTelecomBridge(TelecomConnectionService telecomConnectionService, TelecomBridge telecomBridge) {
        telecomConnectionService.telecomBridge = telecomBridge;
    }

    public static void injectTelecomCallAudioRouteObservable(TelecomConnectionService telecomConnectionService, TelecomCallAudioRouteObservable telecomCallAudioRouteObservable) {
        telecomConnectionService.telecomCallAudioRouteObservable = telecomCallAudioRouteObservable;
    }

    public static void injectTelecomCallIds(TelecomConnectionService telecomConnectionService, Queue<String> queue) {
        telecomConnectionService.telecomCallIds = queue;
    }

    public static void injectTelecomLock(TelecomConnectionService telecomConnectionService, Object obj) {
        telecomConnectionService.telecomLock = obj;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(TelecomConnectionService telecomConnectionService) {
        telecomConnectionService.callIdToTelecomConnection = this.callIdToTelecomConnectionProvider.mo10268get();
        telecomConnectionService.telecomCallIds = this.telecomCallIdsProvider.mo10268get();
        telecomConnectionService.telecomLock = this.telecomLockProvider.mo10268get();
        telecomConnectionService.telecomBridge = this.telecomBridgeProvider.mo10268get();
        telecomConnectionService.telecomCallAudioRouteObservable = this.telecomCallAudioRouteObservableProvider.mo10268get();
    }
}
