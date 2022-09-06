package com.amazon.alexa.api.compat;

import com.amazon.alexa.api.AlexaVisualTask;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: VisualTaskSubcomponent.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\u000b"}, d2 = {"Lcom/amazon/alexa/api/compat/VisualTaskSubcomponent;", "Lcom/amazon/alexa/api/compat/Subcomponent;", "Lcom/amazon/alexa/api/compat/VisualTaskApi;", "frameworkApis", "Lcom/amazon/alexa/api/AlexaMobileFrameworkApis;", "(Lcom/amazon/alexa/api/AlexaMobileFrameworkApis;)V", "schedule", "", "alexaVisualTask", "Lcom/amazon/alexa/api/AlexaVisualTask;", "unschedule", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class VisualTaskSubcomponent extends Subcomponent implements VisualTaskApi {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VisualTaskSubcomponent(@NotNull com.amazon.alexa.api.AlexaMobileFrameworkApis frameworkApis) {
        super(frameworkApis);
        Intrinsics.checkParameterIsNotNull(frameworkApis, "frameworkApis");
    }

    @Override // com.amazon.alexa.api.compat.VisualTaskApi
    public void schedule(@NotNull AlexaVisualTask alexaVisualTask) {
        Intrinsics.checkParameterIsNotNull(alexaVisualTask, "alexaVisualTask");
        getFrameworkApis().getVisualTask().schedule(alexaVisualTask);
    }

    @Override // com.amazon.alexa.api.compat.VisualTaskApi
    public void unschedule(@NotNull AlexaVisualTask alexaVisualTask) {
        Intrinsics.checkParameterIsNotNull(alexaVisualTask, "alexaVisualTask");
        getFrameworkApis().getVisualTask().unschedule(alexaVisualTask);
    }
}
