package com.amazon.alexa.voice.ftue;

import com.amazon.alexa.voice.locale.DlsFeatureEnabler;
import com.amazon.alexa.voice.locale.LocaleInteractor;
import com.amazon.alexa.voice.metrics.VoxMetricEventProcessingService;
import com.amazon.alexa.voice.model.HandsFreeSupportChecker;
/* loaded from: classes11.dex */
public class FtueManagerProvider {
    private final DlsFeatureEnabler dlsFeatureEnabler;
    private final FtuePreference ftuePreference;
    private final HandsFreeSupportChecker handsFreeSupportChecker;
    private final LocaleInteractor localeInteractor;
    private final VoxMetricEventProcessingService voxMetricEventProcessingService;

    public FtueManagerProvider(HandsFreeSupportChecker handsFreeSupportChecker, FtuePreference ftuePreference, VoxMetricEventProcessingService voxMetricEventProcessingService, LocaleInteractor localeInteractor, DlsFeatureEnabler dlsFeatureEnabler) {
        this.handsFreeSupportChecker = handsFreeSupportChecker;
        this.ftuePreference = ftuePreference;
        this.voxMetricEventProcessingService = voxMetricEventProcessingService;
        this.localeInteractor = localeInteractor;
        this.dlsFeatureEnabler = dlsFeatureEnabler;
    }

    public FtueManager getFtueManager() {
        return this.handsFreeSupportChecker.isHandsfreeSupported() ? new HandsFreeFtueManager(this.ftuePreference, this.voxMetricEventProcessingService, this.localeInteractor, this.dlsFeatureEnabler) : new LegacyFtueManager(this.ftuePreference, this.voxMetricEventProcessingService, this.localeInteractor, this.dlsFeatureEnabler);
    }
}
