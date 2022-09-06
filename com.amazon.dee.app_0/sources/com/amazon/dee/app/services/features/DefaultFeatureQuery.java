package com.amazon.dee.app.services.features;

import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.protocols.features.FeatureQuery;
import dagger.Lazy;
/* loaded from: classes12.dex */
public class DefaultFeatureQuery implements FeatureQuery {
    static final String TAG = "DefaultFeatureQuery";
    private final Lazy<IdentityService> identityService;

    public DefaultFeatureQuery(Lazy<IdentityService> lazy) {
        this.identityService = lazy;
    }

    @Override // com.amazon.alexa.protocols.features.FeatureQuery
    public boolean isActive(String str) {
        UserIdentity user = this.identityService.mo358get().getUser(TAG);
        return user != null && user.hasFeature(str);
    }
}
