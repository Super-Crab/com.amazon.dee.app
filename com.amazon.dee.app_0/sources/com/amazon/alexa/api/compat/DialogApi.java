package com.amazon.alexa.api.compat;

import com.amazon.alexa.api.AlexaUserInterfaceOptions;
import com.amazon.alexa.api.LaunchType;
import com.amazon.alexa.client.metrics.core.AlexaMetricsConstants;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
/* compiled from: DialogApi.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&J&\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH&¨\u0006\n"}, d2 = {"Lcom/amazon/alexa/api/compat/DialogApi;", "", "start", "", AlexaMetricsConstants.EventConstants.USER_SPEECH_INVOCATION_TYPE, "", "launchType", "Lcom/amazon/alexa/api/LaunchType;", "userInterfaceOptions", "Lcom/amazon/alexa/api/AlexaUserInterfaceOptions;", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface DialogApi {
    void start(@Nullable String str, @Nullable LaunchType launchType);

    void start(@Nullable String str, @Nullable LaunchType launchType, @Nullable AlexaUserInterfaceOptions alexaUserInterfaceOptions);
}
