package com.amazon.commscore.featureflag;

import androidx.annotation.NonNull;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.commscore.api.featureflag.FeatureFlag;
import com.amazon.commscore.api.featureflag.FeaturePoolOperation;
/* loaded from: classes12.dex */
public class DefaultFeatureFlag implements FeatureFlag {
    private static String className = "com.amazon.commscore.featureflag.DefaultFeatureFlag";
    private IdentityService mIdentityService;
    private String name;

    public DefaultFeatureFlag(@NonNull IdentityService identityService, @NonNull String str) {
        this.mIdentityService = identityService;
        this.name = str;
    }

    @Override // com.amazon.commscore.api.featureflag.FeatureFlag
    @NonNull
    public String getName() {
        return this.name;
    }

    @Override // com.amazon.commscore.api.featureflag.FeatureFlag
    public boolean getValue() {
        UserIdentity user = this.mIdentityService.getUser(className);
        return user != null && user.hasFeature(this.name);
    }

    @Override // com.amazon.commscore.api.featureflag.FeatureFlag
    @NonNull
    public FeaturePoolOperation withFeaturePool(@NonNull String str) {
        return new DefaultFeaturePoolOperation(this.mIdentityService, str, this.name);
    }
}
