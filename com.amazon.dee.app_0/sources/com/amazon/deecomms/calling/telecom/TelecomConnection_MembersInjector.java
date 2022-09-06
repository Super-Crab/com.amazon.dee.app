package com.amazon.deecomms.calling.telecom;

import android.content.Context;
import android.media.AudioManager;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class TelecomConnection_MembersInjector implements MembersInjector<TelecomConnection> {
    private final Provider<AudioManager> audioManagerProvider;
    private final Provider<Context> contextProvider;

    public TelecomConnection_MembersInjector(Provider<Context> provider, Provider<AudioManager> provider2) {
        this.contextProvider = provider;
        this.audioManagerProvider = provider2;
    }

    public static MembersInjector<TelecomConnection> create(Provider<Context> provider, Provider<AudioManager> provider2) {
        return new TelecomConnection_MembersInjector(provider, provider2);
    }

    public static void injectAudioManager(TelecomConnection telecomConnection, AudioManager audioManager) {
        telecomConnection.audioManager = audioManager;
    }

    public static void injectContext(TelecomConnection telecomConnection, Context context) {
        telecomConnection.context = context;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(TelecomConnection telecomConnection) {
        telecomConnection.context = this.contextProvider.mo10268get();
        telecomConnection.audioManager = this.audioManagerProvider.mo10268get();
    }
}
