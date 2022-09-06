package com.amazon.alexa.voice.handsfree.features;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfScope;
import com.amazon.alexa.identity.api.IdentityService;
import com.android.tools.r8.GeneratedOutlineSupport1;
@AhfScope
/* loaded from: classes11.dex */
public class IdentityServiceProvider {
    @NonNull
    public EventBus provideEventBus() {
        return (EventBus) GeneratedOutlineSupport1.outline20(EventBus.class);
    }

    @Nullable
    public IdentityService provideIdentityService() {
        return (IdentityService) GeneratedOutlineSupport1.outline21(IdentityService.class);
    }
}
