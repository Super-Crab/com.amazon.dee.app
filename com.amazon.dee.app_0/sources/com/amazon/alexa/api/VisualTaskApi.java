package com.amazon.alexa.api;

import com.amazon.alexa.client.annotations.NonNull;
/* loaded from: classes6.dex */
public interface VisualTaskApi {
    void schedule(@NonNull AlexaVisualTask alexaVisualTask);

    void unschedule(@NonNull AlexaVisualTask alexaVisualTask);
}
