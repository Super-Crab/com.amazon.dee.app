package com.amazon.alexa.alertsca;

import com.amazon.alexa.api.AlexaAudioTask;
import com.amazon.alexa.api.compat.AlexaMobileFrameworkApis;
import com.amazon.alexa.utils.validation.Preconditions;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes6.dex */
public class AlexaInteractionScheduler {
    private final AlexaMobileFrameworkApis alexaMobileFrameworkApis;

    @Inject
    public AlexaInteractionScheduler(AlexaMobileFrameworkApis alexaMobileFrameworkApis) {
        Preconditions.notNull(alexaMobileFrameworkApis, "alexaMobileFrameworkApis is null");
        this.alexaMobileFrameworkApis = alexaMobileFrameworkApis;
    }

    public void schedule(AlexaAudioTask alexaAudioTask) {
        this.alexaMobileFrameworkApis.getAudioTaskScheduler().scheduleAudioTask(alexaAudioTask);
    }

    public void unschedule(AlexaAudioTask alexaAudioTask) {
        this.alexaMobileFrameworkApis.getAudioTaskScheduler().unscheduleAudioTask(alexaAudioTask);
    }
}
