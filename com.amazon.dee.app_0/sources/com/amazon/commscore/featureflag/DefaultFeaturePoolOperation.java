package com.amazon.commscore.featureflag;

import androidx.annotation.NonNull;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.commscore.api.featureflag.FeaturePoolOperation;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes12.dex */
public class DefaultFeaturePoolOperation implements FeaturePoolOperation {
    private static String className = "com.amazon.commscore.featureflag.DefaultFeaturePoolOperation";
    private String featureName;
    private IdentityService mIdentityService;
    private Set<String> pools = new HashSet();

    public DefaultFeaturePoolOperation(@NonNull IdentityService identityService, @NonNull String str, @NonNull String str2) {
        this.mIdentityService = identityService;
        this.featureName = str2;
        this.pools.add(str);
    }

    @Override // com.amazon.commscore.api.featureflag.FeaturePoolOperation
    @NonNull
    public FeaturePoolOperation and(@NonNull String str) {
        this.pools.add(str);
        return this;
    }

    @Override // com.amazon.commscore.api.featureflag.FeaturePoolOperation
    public boolean getValue() {
        UserIdentity user = this.mIdentityService.getUser(className);
        if (user == null) {
            return false;
        }
        if (user.hasFeature(this.featureName)) {
            return true;
        }
        for (String str : this.pools) {
            if (user.hasFeature(str)) {
                return true;
            }
        }
        return false;
    }
}
