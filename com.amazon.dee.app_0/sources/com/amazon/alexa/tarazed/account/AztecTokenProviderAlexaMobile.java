package com.amazon.alexa.tarazed.account;

import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.tarazed.dagger.scope.TarazedIntegrationScope;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.registry.component.AztecTokenProvider;
import com.amazon.tarazed.core.registry.component.exception.NoAuthenticatedUserException;
import com.amazon.tarazed.core.registry.component.exception.TarazedAuthenticationException;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: AztecTokenProviderAlexaMobile.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0007\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/amazon/alexa/tarazed/account/AztecTokenProviderAlexaMobile;", "Lcom/amazon/tarazed/core/registry/component/AztecTokenProvider;", "identityService", "Lcom/amazon/alexa/identity/api/IdentityService;", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "metricsHelper", "Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "(Lcom/amazon/alexa/identity/api/IdentityService;Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;)V", "provideAztecToken", "", "isSessionActive", "", "Companion", "AlexaMobileAndroidTarazedIntegration_release"}, k = 1, mv = {1, 1, 16})
@TarazedIntegrationScope
/* loaded from: classes10.dex */
public final class AztecTokenProviderAlexaMobile implements AztecTokenProvider {
    public static final Companion Companion = new Companion(null);
    private static final String METRIC_AZTEC_REQUEST_FAILED = "AztecRequestFailed";
    private static final String METRIC_AZTEC_REQUEST_NOT_REGISTERED = "AztecRequestNotRegistered";
    private static final String METRIC_AZTEC_TOKEN_REQUESTED = "AztecTokenRequested";
    private static final String TAG = "AMTokenProvider";
    private final IdentityService identityService;
    private final TarazedSessionLogger logger;
    private final TarazedMetricsHelper metricsHelper;

    /* compiled from: AztecTokenProviderAlexaMobile.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/amazon/alexa/tarazed/account/AztecTokenProviderAlexaMobile$Companion;", "", "()V", "METRIC_AZTEC_REQUEST_FAILED", "", "METRIC_AZTEC_REQUEST_NOT_REGISTERED", "METRIC_AZTEC_TOKEN_REQUESTED", "TAG", "AlexaMobileAndroidTarazedIntegration_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes10.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Inject
    public AztecTokenProviderAlexaMobile(@NotNull IdentityService identityService, @NotNull TarazedSessionLogger logger, @NotNull TarazedMetricsHelper metricsHelper) {
        Intrinsics.checkParameterIsNotNull(identityService, "identityService");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metricsHelper, "metricsHelper");
        this.identityService = identityService;
        this.logger = logger;
        this.metricsHelper = metricsHelper;
    }

    @Override // com.amazon.tarazed.core.registry.component.AztecTokenProvider
    @NotNull
    public String provideAztecToken(boolean z) {
        this.logger.i(TAG, "Retrieving Aztec token");
        this.metricsHelper.addCount(TAG, METRIC_AZTEC_TOKEN_REQUESTED, 1.0d);
        String accessToken = this.identityService.getAccessToken(TAG);
        if (Intrinsics.areEqual(accessToken, "")) {
            this.logger.e(TAG, "Failed to authenticate current user - No User Access Token");
            this.metricsHelper.addCountHighPriority(TAG, METRIC_AZTEC_REQUEST_NOT_REGISTERED, 1.0d);
            throw new NoAuthenticatedUserException("Failed to authenticate current user - No User Access Token");
        } else if (accessToken != null) {
            this.logger.i(TAG, "Retrieving Aztec token succeeded.");
            return accessToken;
        } else {
            this.logger.e(TAG, "Aztec token was null");
            this.metricsHelper.addCountHighPriority(TAG, METRIC_AZTEC_REQUEST_FAILED, 1.0d);
            throw new TarazedAuthenticationException("Aztec token was null");
        }
    }
}
