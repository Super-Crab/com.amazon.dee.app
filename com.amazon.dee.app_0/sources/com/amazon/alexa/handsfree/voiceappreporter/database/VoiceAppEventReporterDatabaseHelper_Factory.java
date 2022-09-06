package com.amazon.alexa.handsfree.voiceappreporter.database;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class VoiceAppEventReporterDatabaseHelper_Factory implements Factory<VoiceAppEventReporterDatabaseHelper> {
    private final Provider<Context> contextProvider;

    public VoiceAppEventReporterDatabaseHelper_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public static VoiceAppEventReporterDatabaseHelper_Factory create(Provider<Context> provider) {
        return new VoiceAppEventReporterDatabaseHelper_Factory(provider);
    }

    public static VoiceAppEventReporterDatabaseHelper newVoiceAppEventReporterDatabaseHelper(Context context) {
        return new VoiceAppEventReporterDatabaseHelper(context);
    }

    public static VoiceAppEventReporterDatabaseHelper provideInstance(Provider<Context> provider) {
        return new VoiceAppEventReporterDatabaseHelper(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public VoiceAppEventReporterDatabaseHelper mo10268get() {
        return provideInstance(this.contextProvider);
    }
}
