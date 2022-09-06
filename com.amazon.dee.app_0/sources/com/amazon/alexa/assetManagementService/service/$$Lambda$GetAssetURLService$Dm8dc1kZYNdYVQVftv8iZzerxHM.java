package com.amazon.alexa.assetManagementService.service;

import com.amazon.alexa.assetManagementService.model.constants.GetAssetURLServiceConstants;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.assetManagementService.service.-$$Lambda$GetAssetURLService$Dm8dc1kZYNdYVQVftv8iZzerxHM  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$GetAssetURLService$Dm8dc1kZYNdYVQVftv8iZzerxHM implements MessageFilter {
    public static final /* synthetic */ $$Lambda$GetAssetURLService$Dm8dc1kZYNdYVQVftv8iZzerxHM INSTANCE = new $$Lambda$GetAssetURLService$Dm8dc1kZYNdYVQVftv8iZzerxHM();

    private /* synthetic */ $$Lambda$GetAssetURLService$Dm8dc1kZYNdYVQVftv8iZzerxHM() {
    }

    @Override // com.amazon.alexa.eventbus.api.MessageFilter
    public final boolean isMatch(Message message) {
        return GetAssetURLServiceConstants.APP_UPDATED_EVENT.equals(message.getEventType());
    }
}
