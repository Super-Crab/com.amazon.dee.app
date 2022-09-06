package com.amazon.deecomms.conversation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.deecomms.api.CommsManager;
/* loaded from: classes12.dex */
public class DefaultCommsDynamicFeatureService implements CommsDynamicFeatureService {
    private CommsManager manager;

    public DefaultCommsDynamicFeatureService(@NonNull CommsManager commsManager) {
        this.manager = commsManager;
    }

    @Override // com.amazon.deecomms.conversation.CommsDynamicFeatureService
    public void pushFeatures(@Nullable UserIdentity userIdentity) {
    }
}
