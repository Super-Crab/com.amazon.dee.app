package com.amazon.deecomms.core;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.protocols.features.FeatureFilter;
import com.amazon.deecomms.api.CommsDelegateBase;
import com.amazon.deecomms.api.CommsManager;
import com.amazon.deecomms.conversation.CommsDeviceSupport;
import com.amazon.deecomms.conversation.CommsDynamicFeatureService;
import com.amazon.deecomms.conversation.ConversationService;
import dagger.Lazy;
/* loaded from: classes12.dex */
public class CommsService implements ICommsService {
    private final CommsComponent commsComponent;

    public CommsService(@NonNull Lazy<Context> lazy, @NonNull Lazy<IdentityService> lazy2, @NonNull Lazy<CommsDelegateBase> lazy3, @NonNull String str, @NonNull Lazy<DeviceInformation> lazy4) {
        CommsDaggerWrapper.initialize(lazy, lazy2, lazy3, str, lazy4);
        this.commsComponent = CommsDaggerWrapper.getComponent();
    }

    @Override // com.amazon.deecomms.core.ICommsService
    @NonNull
    public CommsDeviceSupport getCommsDeviceSupport() {
        return this.commsComponent.getCommsDeviceSupport();
    }

    @Override // com.amazon.deecomms.core.ICommsService
    @NonNull
    public CommsDynamicFeatureService getCommsDynamicFeatureService() {
        return this.commsComponent.getCommsDynamicFeatureService();
    }

    @Override // com.amazon.deecomms.core.ICommsService
    @NonNull
    public FeatureFilter getCommsFeatureFilter() {
        return this.commsComponent.getFeatureFilter();
    }

    @Override // com.amazon.deecomms.core.ICommsService
    @NonNull
    public CommsManager getCommsManager() {
        return this.commsComponent.getCommsManager();
    }

    @Override // com.amazon.deecomms.core.ICommsService
    @NonNull
    public ConversationService getConversationService() {
        return this.commsComponent.getConversationService();
    }
}
