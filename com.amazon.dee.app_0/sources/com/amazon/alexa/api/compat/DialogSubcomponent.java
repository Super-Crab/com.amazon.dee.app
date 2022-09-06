package com.amazon.alexa.api.compat;

import com.amazon.alexa.api.AlexaUserInterfaceOptions;
import com.amazon.alexa.api.LaunchType;
import com.amazon.alexa.client.metrics.core.AlexaMetricsConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: DialogSubcomponent.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u001c\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J&\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016¨\u0006\u000e"}, d2 = {"Lcom/amazon/alexa/api/compat/DialogSubcomponent;", "Lcom/amazon/alexa/api/compat/Subcomponent;", "Lcom/amazon/alexa/api/compat/DialogApi;", "frameworkApis", "Lcom/amazon/alexa/api/AlexaMobileFrameworkApis;", "(Lcom/amazon/alexa/api/AlexaMobileFrameworkApis;)V", "start", "", AlexaMetricsConstants.EventConstants.USER_SPEECH_INVOCATION_TYPE, "", "launchType", "Lcom/amazon/alexa/api/LaunchType;", "userInterfaceOptions", "Lcom/amazon/alexa/api/AlexaUserInterfaceOptions;", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class DialogSubcomponent extends Subcomponent implements DialogApi {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DialogSubcomponent(@NotNull com.amazon.alexa.api.AlexaMobileFrameworkApis frameworkApis) {
        super(frameworkApis);
        Intrinsics.checkParameterIsNotNull(frameworkApis, "frameworkApis");
    }

    @Override // com.amazon.alexa.api.compat.DialogApi
    public void start(@Nullable String str, @Nullable LaunchType launchType) {
        getFrameworkApis().getDialog().start(str, launchType);
    }

    @Override // com.amazon.alexa.api.compat.DialogApi
    public void start(@Nullable String str, @Nullable LaunchType launchType, @Nullable AlexaUserInterfaceOptions alexaUserInterfaceOptions) {
        getFrameworkApis().getDialog().start(str, launchType, alexaUserInterfaceOptions);
    }
}
