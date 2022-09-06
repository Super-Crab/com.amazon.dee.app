package com.amazon.commscore.commsidentity.implementation;

import android.content.Context;
import androidx.annotation.Nullable;
import com.amazon.alexa.protocols.service.api.ComponentGetter;
import com.amazon.alexa.protocols.service.api.InitializableComponent;
import com.amazon.commscore.api.identity.AlexaCommsCoreIdentityService;
import com.amazon.commscore.api.identity.CommsCoreIdentity;
import com.amazon.commscore.api.identity.CookieProvider;
import com.amazon.commscore.dependencies.BaseComponentWrapper;
import rx.Observable;
/* loaded from: classes12.dex */
public class AlexaCommsCoreIdentityServiceWrapper extends BaseComponentWrapper<AlexaCommsCoreIdentityService> implements AlexaCommsCoreIdentityService, InitializableComponent, CookieProvider {
    public AlexaCommsCoreIdentityServiceWrapper(ComponentGetter componentGetter, Context context) {
        super(componentGetter, context);
    }

    @Override // com.amazon.commscore.api.identity.CookieProvider
    public Observable<String[]> getCookies(String str, String str2) {
        return ((CookieProvider) mo3276getImplementation()).getCookies(str, str2);
    }

    @Override // com.amazon.commscore.api.identity.AlexaCommsCoreIdentityService
    @Nullable
    public CommsCoreIdentity getIdentity() {
        return mo3276getImplementation().getIdentity();
    }

    @Override // com.amazon.alexa.protocols.service.api.InitializableComponent
    public void initializeComponent(ComponentGetter componentGetter, Context context) {
        ((DefaultAlexaCommsCoreIdentityService) mo3276getImplementation()).registerForIdentityEvents();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.commscore.dependencies.BaseComponentWrapper
    /* renamed from: getImplementation */
    public AlexaCommsCoreIdentityService mo3276getImplementation() {
        return this.commsCoreComponent.getIdentityService();
    }
}
