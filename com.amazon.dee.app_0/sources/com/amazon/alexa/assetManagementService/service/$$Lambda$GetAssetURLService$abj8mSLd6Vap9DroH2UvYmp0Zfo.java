package com.amazon.alexa.assetManagementService.service;

import com.amazon.alexa.assetManagementService.model.constants.GetAssetURLServiceConstants;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.assetManagementService.service.-$$Lambda$GetAssetURLService$abj8mSLd6Vap9DroH2UvYmp0Zfo  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$GetAssetURLService$abj8mSLd6Vap9DroH2UvYmp0Zfo implements MessageFilter {
    public static final /* synthetic */ $$Lambda$GetAssetURLService$abj8mSLd6Vap9DroH2UvYmp0Zfo INSTANCE = new $$Lambda$GetAssetURLService$abj8mSLd6Vap9DroH2UvYmp0Zfo();

    private /* synthetic */ $$Lambda$GetAssetURLService$abj8mSLd6Vap9DroH2UvYmp0Zfo() {
    }

    @Override // com.amazon.alexa.eventbus.api.MessageFilter
    public final boolean isMatch(Message message) {
        return GetAssetURLServiceConstants.ELEMENTS_READY_EVENT.equals(message.getEventType());
    }
}
