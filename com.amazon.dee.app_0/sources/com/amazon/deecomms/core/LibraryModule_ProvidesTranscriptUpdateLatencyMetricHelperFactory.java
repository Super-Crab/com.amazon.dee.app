package com.amazon.deecomms.core;

import com.amazon.deecomms.notifications.TranscriptLatencyMetricHelper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesTranscriptUpdateLatencyMetricHelperFactory implements Factory<TranscriptLatencyMetricHelper> {
    private final LibraryModule module;

    public LibraryModule_ProvidesTranscriptUpdateLatencyMetricHelperFactory(LibraryModule libraryModule) {
        this.module = libraryModule;
    }

    public static LibraryModule_ProvidesTranscriptUpdateLatencyMetricHelperFactory create(LibraryModule libraryModule) {
        return new LibraryModule_ProvidesTranscriptUpdateLatencyMetricHelperFactory(libraryModule);
    }

    public static TranscriptLatencyMetricHelper provideInstance(LibraryModule libraryModule) {
        return (TranscriptLatencyMetricHelper) Preconditions.checkNotNull(libraryModule.providesTranscriptUpdateLatencyMetricHelper(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static TranscriptLatencyMetricHelper proxyProvidesTranscriptUpdateLatencyMetricHelper(LibraryModule libraryModule) {
        return (TranscriptLatencyMetricHelper) Preconditions.checkNotNull(libraryModule.providesTranscriptUpdateLatencyMetricHelper(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TranscriptLatencyMetricHelper mo10268get() {
        return provideInstance(this.module);
    }
}
