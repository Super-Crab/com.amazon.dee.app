package com.amazon.alexa.fitness.api;

import com.amazon.alexa.presence.utils.MetricsUtil;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.http.UrlResolver;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: EnvironmentUrlResolver.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/amazon/alexa/fitness/api/EnvironmentUrlResolver;", "Lcom/dee/app/http/UrlResolver;", "environmentService", "Lcom/amazon/alexa/protocols/environment/EnvironmentService;", "(Lcom/amazon/alexa/protocols/environment/EnvironmentService;)V", MetricsUtil.MetricsId.RESOLVE, "", "uri", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class EnvironmentUrlResolver implements UrlResolver {
    private final EnvironmentService environmentService;

    @Inject
    public EnvironmentUrlResolver(@NotNull EnvironmentService environmentService) {
        Intrinsics.checkParameterIsNotNull(environmentService, "environmentService");
        this.environmentService = environmentService;
    }

    @Override // com.dee.app.http.UrlResolver
    @NotNull
    public String resolve(@NotNull String uri) {
        boolean startsWith$default;
        boolean endsWith$default;
        boolean startsWith$default2;
        Intrinsics.checkParameterIsNotNull(uri, "uri");
        startsWith$default = StringsKt__StringsJVMKt.startsWith$default(uri, "http", false, 2, null);
        if (startsWith$default) {
            return uri;
        }
        String coralEndpoint = this.environmentService.getCoralEndpoint();
        Intrinsics.checkExpressionValueIsNotNull(coralEndpoint, "environmentService.coralEndpoint");
        endsWith$default = StringsKt__StringsJVMKt.endsWith$default(coralEndpoint, "/", false, 2, null);
        if (!endsWith$default) {
            coralEndpoint = GeneratedOutlineSupport1.outline72(coralEndpoint, "/");
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(coralEndpoint);
        startsWith$default2 = StringsKt__StringsJVMKt.startsWith$default(uri, "/", false, 2, null);
        if (startsWith$default2) {
            uri = uri.substring(1);
            Intrinsics.checkExpressionValueIsNotNull(uri, "(this as java.lang.String).substring(startIndex)");
        }
        outline107.append(uri);
        return outline107.toString();
    }
}
