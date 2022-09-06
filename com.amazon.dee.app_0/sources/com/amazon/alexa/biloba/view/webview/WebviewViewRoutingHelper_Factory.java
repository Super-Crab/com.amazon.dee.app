package com.amazon.alexa.biloba.view.webview;

import com.amazon.alexa.biloba.storage.CareActorsStore;
import com.amazon.alexa.identity.api.IdentityService;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class WebviewViewRoutingHelper_Factory implements Factory<WebviewViewRoutingHelper> {
    private final Provider<CareActorsStore> careActorsStoreProvider;
    private final Provider<IdentityService> identityServiceProvider;

    public WebviewViewRoutingHelper_Factory(Provider<CareActorsStore> provider, Provider<IdentityService> provider2) {
        this.careActorsStoreProvider = provider;
        this.identityServiceProvider = provider2;
    }

    public static WebviewViewRoutingHelper_Factory create(Provider<CareActorsStore> provider, Provider<IdentityService> provider2) {
        return new WebviewViewRoutingHelper_Factory(provider, provider2);
    }

    public static WebviewViewRoutingHelper newWebviewViewRoutingHelper() {
        return new WebviewViewRoutingHelper();
    }

    public static WebviewViewRoutingHelper provideInstance(Provider<CareActorsStore> provider, Provider<IdentityService> provider2) {
        WebviewViewRoutingHelper webviewViewRoutingHelper = new WebviewViewRoutingHelper();
        WebviewViewRoutingHelper_MembersInjector.injectCareActorsStore(webviewViewRoutingHelper, DoubleCheck.lazy(provider));
        WebviewViewRoutingHelper_MembersInjector.injectIdentityService(webviewViewRoutingHelper, DoubleCheck.lazy(provider2));
        return webviewViewRoutingHelper;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public WebviewViewRoutingHelper mo10268get() {
        return provideInstance(this.careActorsStoreProvider, this.identityServiceProvider);
    }
}
