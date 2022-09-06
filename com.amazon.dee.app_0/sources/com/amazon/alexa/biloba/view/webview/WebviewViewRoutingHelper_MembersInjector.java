package com.amazon.alexa.biloba.view.webview;

import com.amazon.alexa.biloba.storage.CareActorsStore;
import com.amazon.alexa.identity.api.IdentityService;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class WebviewViewRoutingHelper_MembersInjector implements MembersInjector<WebviewViewRoutingHelper> {
    private final Provider<CareActorsStore> careActorsStoreProvider;
    private final Provider<IdentityService> identityServiceProvider;

    public WebviewViewRoutingHelper_MembersInjector(Provider<CareActorsStore> provider, Provider<IdentityService> provider2) {
        this.careActorsStoreProvider = provider;
        this.identityServiceProvider = provider2;
    }

    public static MembersInjector<WebviewViewRoutingHelper> create(Provider<CareActorsStore> provider, Provider<IdentityService> provider2) {
        return new WebviewViewRoutingHelper_MembersInjector(provider, provider2);
    }

    public static void injectCareActorsStore(WebviewViewRoutingHelper webviewViewRoutingHelper, Lazy<CareActorsStore> lazy) {
        webviewViewRoutingHelper.careActorsStore = lazy;
    }

    public static void injectIdentityService(WebviewViewRoutingHelper webviewViewRoutingHelper, Lazy<IdentityService> lazy) {
        webviewViewRoutingHelper.identityService = lazy;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(WebviewViewRoutingHelper webviewViewRoutingHelper) {
        injectCareActorsStore(webviewViewRoutingHelper, DoubleCheck.lazy(this.careActorsStoreProvider));
        injectIdentityService(webviewViewRoutingHelper, DoubleCheck.lazy(this.identityServiceProvider));
    }
}
