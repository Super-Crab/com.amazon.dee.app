package com.amazon.alexa.api.compat;

import com.amazon.device.messaging.ADMRegistrationConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: WakeWordSubcomponent.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\b\u001a\u00020\u0007H\u0016J\u0010\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\f"}, d2 = {"Lcom/amazon/alexa/api/compat/WakeWordSubcomponent;", "Lcom/amazon/alexa/api/compat/Subcomponent;", "Lcom/amazon/alexa/api/compat/WakeWordApi;", "frameworkApis", "Lcom/amazon/alexa/api/AlexaMobileFrameworkApis;", "(Lcom/amazon/alexa/api/AlexaMobileFrameworkApis;)V", "deregister", "", ADMRegistrationConstants.METHOD_REGISTER, "setWakeWordAllowed", "isAllowed", "", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class WakeWordSubcomponent extends Subcomponent implements WakeWordApi {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WakeWordSubcomponent(@NotNull com.amazon.alexa.api.AlexaMobileFrameworkApis frameworkApis) {
        super(frameworkApis);
        Intrinsics.checkParameterIsNotNull(frameworkApis, "frameworkApis");
    }

    @Override // com.amazon.alexa.api.compat.WakeWordApi
    public void deregister() {
        getFrameworkApis().getWakeWord().stopListening();
    }

    @Override // com.amazon.alexa.api.compat.WakeWordApi
    public void register() {
        getFrameworkApis().getWakeWord().startListening();
    }

    @Override // com.amazon.alexa.api.compat.WakeWordApi
    public void setWakeWordAllowed(boolean z) {
    }
}
