package com.amazon.alexa.api;

import com.amazon.alexa.client.annotations.NonNull;
/* loaded from: classes6.dex */
public interface AudioTaskSchedulerApi {
    void scheduleAudioTask(@NonNull AlexaAudioTask alexaAudioTask);

    void unscheduleAudioTask(@NonNull AlexaAudioTask alexaAudioTask);
}
