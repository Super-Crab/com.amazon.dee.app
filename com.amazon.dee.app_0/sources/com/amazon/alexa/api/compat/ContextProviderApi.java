package com.amazon.alexa.api.compat;

import com.amazon.alexa.api.AlexaContext;
import com.amazon.alexa.api.AlexaContextsProvider;
import java.util.Set;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: ContextProviderApi.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\b\u0010\u0007\u001a\u00020\u0003H&J\u0016\u0010\u0007\u001a\u00020\u00032\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0005H&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH&J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH&J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H&J\u001e\u0010\u000e\u001a\u00020\u00032\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00052\u0006\u0010\u000f\u001a\u00020\u0010H&¨\u0006\u0011"}, d2 = {"Lcom/amazon/alexa/api/compat/ContextProviderApi;", "", "cacheContexts", "", "alexaContexts", "", "Lcom/amazon/alexa/api/AlexaContext;", "clearContextCache", "namespaces", "", "deregisterContextsProvider", "alexaContextsProvider", "Lcom/amazon/alexa/api/AlexaContextsProvider;", "registerContextsProvider", "setContextCachingEnabled", "enableContextCaching", "", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface ContextProviderApi {
    void cacheContexts(@NotNull Set<? extends AlexaContext> set);

    void clearContextCache();

    void clearContextCache(@NotNull Set<String> set);

    void deregisterContextsProvider(@NotNull AlexaContextsProvider alexaContextsProvider);

    void registerContextsProvider(@NotNull AlexaContextsProvider alexaContextsProvider);

    void setContextCachingEnabled(@NotNull Set<String> set, boolean z);

    void setContextCachingEnabled(boolean z);
}
