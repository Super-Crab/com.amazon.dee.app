package com.amazon.alexa.api.compat;

import com.amazon.alexa.api.AlexaContext;
import com.amazon.alexa.api.AlexaContextsProvider;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: ContextProviderSubcomponent.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0016\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0016J\b\u0010\u000b\u001a\u00020\u0007H\u0016J\u0016\u0010\u000b\u001a\u00020\u00072\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\tH\u0016J\u0010\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u001e\u0010\u0012\u001a\u00020\u00072\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\t2\u0006\u0010\u0013\u001a\u00020\u0014H\u0016¨\u0006\u0015"}, d2 = {"Lcom/amazon/alexa/api/compat/ContextProviderSubcomponent;", "Lcom/amazon/alexa/api/compat/Subcomponent;", "Lcom/amazon/alexa/api/compat/ContextProviderApi;", "frameworkApis", "Lcom/amazon/alexa/api/AlexaMobileFrameworkApis;", "(Lcom/amazon/alexa/api/AlexaMobileFrameworkApis;)V", "cacheContexts", "", "alexaContexts", "", "Lcom/amazon/alexa/api/AlexaContext;", "clearContextCache", "namespaces", "", "deregisterContextsProvider", "alexaContextsProvider", "Lcom/amazon/alexa/api/AlexaContextsProvider;", "registerContextsProvider", "setContextCachingEnabled", "enableContextCaching", "", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class ContextProviderSubcomponent extends Subcomponent implements ContextProviderApi {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ContextProviderSubcomponent(@NotNull com.amazon.alexa.api.AlexaMobileFrameworkApis frameworkApis) {
        super(frameworkApis);
        Intrinsics.checkParameterIsNotNull(frameworkApis, "frameworkApis");
    }

    @Override // com.amazon.alexa.api.compat.ContextProviderApi
    public void cacheContexts(@NotNull Set<? extends AlexaContext> alexaContexts) {
        Intrinsics.checkParameterIsNotNull(alexaContexts, "alexaContexts");
        getFrameworkApis().getCapabilities().cacheContexts(alexaContexts);
    }

    @Override // com.amazon.alexa.api.compat.ContextProviderApi
    public void clearContextCache() {
        getFrameworkApis().getCapabilities().clearContextCache();
    }

    @Override // com.amazon.alexa.api.compat.ContextProviderApi
    public void deregisterContextsProvider(@NotNull AlexaContextsProvider alexaContextsProvider) {
        Intrinsics.checkParameterIsNotNull(alexaContextsProvider, "alexaContextsProvider");
        getFrameworkApis().getCapabilities().deregisterContextsProvider(alexaContextsProvider);
    }

    @Override // com.amazon.alexa.api.compat.ContextProviderApi
    public void registerContextsProvider(@NotNull AlexaContextsProvider alexaContextsProvider) {
        Intrinsics.checkParameterIsNotNull(alexaContextsProvider, "alexaContextsProvider");
        getFrameworkApis().getCapabilities().registerContextsProvider(alexaContextsProvider);
    }

    @Override // com.amazon.alexa.api.compat.ContextProviderApi
    public void setContextCachingEnabled(boolean z) {
        getFrameworkApis().getCapabilities().setContextCachingEnabled(z);
    }

    @Override // com.amazon.alexa.api.compat.ContextProviderApi
    public void clearContextCache(@NotNull Set<String> namespaces) {
        Intrinsics.checkParameterIsNotNull(namespaces, "namespaces");
        getFrameworkApis().getCapabilities().clearContextCache(namespaces);
    }

    @Override // com.amazon.alexa.api.compat.ContextProviderApi
    public void setContextCachingEnabled(@NotNull Set<String> namespaces, boolean z) {
        Intrinsics.checkParameterIsNotNull(namespaces, "namespaces");
        getFrameworkApis().getCapabilities().setContextCachingEnabled(namespaces, z);
    }
}
