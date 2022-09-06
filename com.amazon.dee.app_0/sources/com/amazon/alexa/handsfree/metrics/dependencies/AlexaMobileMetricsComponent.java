package com.amazon.alexa.handsfree.metrics.dependencies;

import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentProtocol;
import com.amazon.alexa.handsfree.voiceappreporter.VoiceAppEventContentProvider;
import com.amazon.alexa.handsfree.voiceappreporter.schedulers.VoiceAppEventReporterServiceScheduler;
import com.amazon.alexa.handsfree.voiceappreporter.services.VoiceAppEventReporterService;
import dagger.Subcomponent;
@Subcomponent
/* loaded from: classes8.dex */
public interface AlexaMobileMetricsComponent extends AhfComponentProtocol {
    void inject(VoiceAppEventContentProvider voiceAppEventContentProvider);

    void inject(VoiceAppEventReporterService voiceAppEventReporterService);

    VoiceAppEventReporterServiceScheduler voiceAppEventReporterServiceScheduler();
}
