package com.amazon.alexa.voice.ftue;

import com.amazon.alexa.protocols.storage.PersistentStorage;
import com.amazon.alexa.voice.locale.DlsFeatureEnabler;
import com.amazon.alexa.voice.locale.LocaleInteractor;
import com.amazon.alexa.voice.metrics.VoxMetricEventProcessingService;
import com.amazon.alexa.voice.model.HandsFreeSupportChecker;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes11.dex */
public final class FtueModule {
    private FtueModule() {
    }

    @Provides
    public static FtueManagerProvider provideFtueManagerProvider(HandsFreeSupportChecker handsFreeSupportChecker, FtuePreference ftuePreference, VoxMetricEventProcessingService voxMetricEventProcessingService, LocaleInteractor localeInteractor, DlsFeatureEnabler dlsFeatureEnabler) {
        return new FtueManagerProvider(handsFreeSupportChecker, ftuePreference, voxMetricEventProcessingService, localeInteractor, dlsFeatureEnabler);
    }

    @Provides
    public static FtuePreference provideFtuePreference(PersistentStorage.Factory factory) {
        return new UserFtuePreference(factory);
    }
}
