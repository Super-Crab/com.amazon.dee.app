package com.amazon.commscore.featureflag.dependencies;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.protocols.service.api.ComponentGetter;
import com.amazon.commscore.api.featureflag.AlexaCommsCoreFeatureService;
import com.amazon.commscore.api.featureflag.FeatureFlag;
import com.amazon.commscore.dependencies.BaseComponentWrapper;
/* loaded from: classes12.dex */
public class AlexaCommsCoreFeatureServiceWrapper extends BaseComponentWrapper<AlexaCommsCoreFeatureService> implements AlexaCommsCoreFeatureService {
    public AlexaCommsCoreFeatureServiceWrapper(ComponentGetter componentGetter, Context context) {
        super(componentGetter, context);
    }

    @Override // com.amazon.commscore.api.featureflag.AlexaCommsCoreFeatureService
    @NonNull
    public FeatureFlag isFeatureEnabled(@NonNull String str) {
        return mo3276getImplementation().isFeatureEnabled(str);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.commscore.dependencies.BaseComponentWrapper
    /* renamed from: getImplementation */
    public AlexaCommsCoreFeatureService mo3276getImplementation() {
        return this.commsCoreComponent.getFeatureService();
    }
}
