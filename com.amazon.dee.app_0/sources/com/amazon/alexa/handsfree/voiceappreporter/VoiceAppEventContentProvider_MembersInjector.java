package com.amazon.alexa.handsfree.voiceappreporter;

import com.amazon.alexa.handsfree.voiceappreporter.database.VoiceAppEventReporterDatabaseHelper;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class VoiceAppEventContentProvider_MembersInjector implements MembersInjector<VoiceAppEventContentProvider> {
    private final Provider<VoiceAppEventReporterDatabaseHelper> mVoiceAppEventReporterDatabaseHelperProvider;

    public VoiceAppEventContentProvider_MembersInjector(Provider<VoiceAppEventReporterDatabaseHelper> provider) {
        this.mVoiceAppEventReporterDatabaseHelperProvider = provider;
    }

    public static MembersInjector<VoiceAppEventContentProvider> create(Provider<VoiceAppEventReporterDatabaseHelper> provider) {
        return new VoiceAppEventContentProvider_MembersInjector(provider);
    }

    public static void injectMVoiceAppEventReporterDatabaseHelper(VoiceAppEventContentProvider voiceAppEventContentProvider, VoiceAppEventReporterDatabaseHelper voiceAppEventReporterDatabaseHelper) {
        voiceAppEventContentProvider.mVoiceAppEventReporterDatabaseHelper = voiceAppEventReporterDatabaseHelper;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(VoiceAppEventContentProvider voiceAppEventContentProvider) {
        injectMVoiceAppEventReporterDatabaseHelper(voiceAppEventContentProvider, this.mVoiceAppEventReporterDatabaseHelperProvider.mo10268get());
    }
}
