package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.dee.app.ui.web.JavaScriptPlayer;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class WebModule_ProvideJavaScriptPlayerFactory implements Factory<JavaScriptPlayer> {
    private final Provider<Context> contextProvider;
    private final WebModule module;

    public WebModule_ProvideJavaScriptPlayerFactory(WebModule webModule, Provider<Context> provider) {
        this.module = webModule;
        this.contextProvider = provider;
    }

    public static WebModule_ProvideJavaScriptPlayerFactory create(WebModule webModule, Provider<Context> provider) {
        return new WebModule_ProvideJavaScriptPlayerFactory(webModule, provider);
    }

    public static JavaScriptPlayer provideInstance(WebModule webModule, Provider<Context> provider) {
        return proxyProvideJavaScriptPlayer(webModule, provider.mo10268get());
    }

    public static JavaScriptPlayer proxyProvideJavaScriptPlayer(WebModule webModule, Context context) {
        return (JavaScriptPlayer) Preconditions.checkNotNull(webModule.provideJavaScriptPlayer(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public JavaScriptPlayer mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
