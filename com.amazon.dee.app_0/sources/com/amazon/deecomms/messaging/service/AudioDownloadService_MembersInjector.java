package com.amazon.deecomms.messaging.service;

import com.amazon.deecomms.messaging.controller.AudioStateManager;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class AudioDownloadService_MembersInjector implements MembersInjector<AudioDownloadService> {
    private final Provider<AudioStateManager> audioStateManagerProvider;

    public AudioDownloadService_MembersInjector(Provider<AudioStateManager> provider) {
        this.audioStateManagerProvider = provider;
    }

    public static MembersInjector<AudioDownloadService> create(Provider<AudioStateManager> provider) {
        return new AudioDownloadService_MembersInjector(provider);
    }

    public static void injectAudioStateManager(AudioDownloadService audioDownloadService, AudioStateManager audioStateManager) {
        audioDownloadService.audioStateManager = audioStateManager;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(AudioDownloadService audioDownloadService) {
        audioDownloadService.audioStateManager = this.audioStateManagerProvider.mo10268get();
    }
}
