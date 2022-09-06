package com.amazon.deecomms.commscore;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.protocols.service.api.ComponentGetter;
import com.amazon.commscore.api.identity.AlexaCommsCoreIdentityService;
import com.amazon.commscore.api.identity.CommsCoreIdentity;
import com.amazon.commscore.api.identity.CookieProvider;
import com.amazon.commscore.commsidentity.implementation.AlexaCommsCoreIdentityServiceWrapper;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.core.FeatureFlagManager;
import rx.Observable;
/* loaded from: classes12.dex */
public class AlexaCommsCoreIdentityServiceImpl implements AlexaCommsCoreIdentityService, CookieProvider {
    private AlexaCommsCoreIdentityService alexaCommsCoreIdentityService;
    private AlexaCommsCoreIdentityServiceWrapper alexaCommsCoreIdentityServiceWrapper;
    private CommsIdentityManager commsIdentityManager;
    private FeatureFlagManager featureFlagManager;
    private IdentityService identityService;

    public AlexaCommsCoreIdentityServiceImpl(ComponentGetter componentGetter, Context context) {
        if (CommsDaggerWrapper.getComponent() != null) {
            this.commsIdentityManager = CommsDaggerWrapper.getComponent().getCommsIdentityManager();
            this.featureFlagManager = CommsDaggerWrapper.getComponent().getFeatureFlagManager();
            this.identityService = CommsDaggerWrapper.getComponent().getIdentityService();
            this.alexaCommsCoreIdentityServiceWrapper = new AlexaCommsCoreIdentityServiceWrapper(componentGetter, context);
            this.alexaCommsCoreIdentityServiceWrapper.initializeComponent(componentGetter, context);
            initialize();
        }
    }

    private void initialize() {
        this.alexaCommsCoreIdentityService = this.alexaCommsCoreIdentityServiceWrapper;
    }

    @VisibleForTesting
    AlexaCommsCoreIdentityService getCommsCoreIdentityServiceImplementation() {
        return this.alexaCommsCoreIdentityService;
    }

    @Override // com.amazon.commscore.api.identity.CookieProvider
    public Observable<String[]> getCookies(String str, String str2) {
        return this.identityService.getCookiesFromDirectedId(str, str2);
    }

    @Override // com.amazon.commscore.api.identity.AlexaCommsCoreIdentityService
    @Nullable
    public CommsCoreIdentity getIdentity() {
        return this.alexaCommsCoreIdentityService.getIdentity();
    }

    @VisibleForTesting
    AlexaCommsCoreIdentityServiceImpl(CommsIdentityManager commsIdentityManager, AlexaCommsCoreIdentityServiceWrapper alexaCommsCoreIdentityServiceWrapper, FeatureFlagManager featureFlagManager, IdentityService identityService) {
        this.commsIdentityManager = commsIdentityManager;
        this.alexaCommsCoreIdentityServiceWrapper = alexaCommsCoreIdentityServiceWrapper;
        this.featureFlagManager = featureFlagManager;
        this.identityService = identityService;
        initialize();
    }
}
