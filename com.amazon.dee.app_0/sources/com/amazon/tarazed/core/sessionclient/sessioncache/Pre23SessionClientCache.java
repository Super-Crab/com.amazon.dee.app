package com.amazon.tarazed.core.sessionclient.sessioncache;

import com.amazon.alexa.client.metrics.kinesis.session.client.AppDefaultSessionClient;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.notification.type.TarazedLaunchRequest;
import com.amazon.tarazed.core.sessionclient.model.createcredentials.CreateCredentialsResponse;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.json.Json;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Pre23SessionClientCache.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\n\u0010\u0011\u001a\u0004\u0018\u00010\nH\u0016J\u0012\u0010\u0012\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0013\u001a\u00020\nH\u0016J\b\u0010\u0014\u001a\u00020\u000eH\u0002J\u0010\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\nH\u0016J\u0018\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\bH\u0016R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/amazon/tarazed/core/sessionclient/sessioncache/Pre23SessionClientCache;", "Lcom/amazon/tarazed/core/sessionclient/sessioncache/SessionClientCache;", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "metricsHelper", "Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "(Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;)V", "cachedCredentials", "Lcom/amazon/tarazed/core/sessionclient/model/createcredentials/CreateCredentialsResponse;", "cachedSessionId", "", Post23SessionClientCache.LAUNCH_REQUEST_CACHE, "Lcom/amazon/tarazed/core/notification/type/TarazedLaunchRequest;", "timestamp", "", "clearLaunchRequest", "", "getLaunchRequest", "getSessionCredentials", AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY, "getTimeInSeconds", "putLaunchRequest", "putSessionCredentials", Post23SessionClientCache.CACHE_DIR, "Companion", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class Pre23SessionClientCache implements SessionClientCache {
    @Deprecated
    public static final Companion Companion = new Companion(null);
    private static final String METRIC_CREDENTIALS_EXPIRED = "CredentialsExpired";
    private static final String TAG = "SessionClientCache";
    private CreateCredentialsResponse cachedCredentials;
    private String cachedSessionId;
    private TarazedLaunchRequest launchRequest;
    private final TarazedSessionLogger logger;
    private final TarazedMetricsHelper metricsHelper;
    private long timestamp;

    /* compiled from: Pre23SessionClientCache.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/amazon/tarazed/core/sessionclient/sessioncache/Pre23SessionClientCache$Companion;", "", "()V", "METRIC_CREDENTIALS_EXPIRED", "", "TAG", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public Pre23SessionClientCache(@NotNull TarazedSessionLogger logger, @NotNull TarazedMetricsHelper metricsHelper) {
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metricsHelper, "metricsHelper");
        this.logger = logger;
        this.metricsHelper = metricsHelper;
    }

    private final long getTimeInSeconds() {
        return System.currentTimeMillis() / 1000;
    }

    @Override // com.amazon.tarazed.core.sessionclient.sessioncache.SessionClientCache
    public void clearLaunchRequest() {
        this.launchRequest = null;
    }

    @Override // com.amazon.tarazed.core.sessionclient.sessioncache.SessionClientCache
    @Nullable
    public String getLaunchRequest() {
        TarazedLaunchRequest tarazedLaunchRequest = this.launchRequest;
        if (tarazedLaunchRequest == null) {
            return null;
        }
        if (tarazedLaunchRequest == null) {
            Intrinsics.throwNpe();
        }
        if (getSessionCredentials(tarazedLaunchRequest.getSessionId()) == null) {
            this.logger.w(TAG, "Cached launch request does not correspond to any active cached credentials, returning null");
            return null;
        }
        Json.Default r0 = Json.Default;
        KSerializer<TarazedLaunchRequest> serializer = TarazedLaunchRequest.Companion.serializer();
        TarazedLaunchRequest tarazedLaunchRequest2 = this.launchRequest;
        if (tarazedLaunchRequest2 == null) {
            Intrinsics.throwNpe();
        }
        return r0.stringify(serializer, tarazedLaunchRequest2);
    }

    @Override // com.amazon.tarazed.core.sessionclient.sessioncache.SessionClientCache
    @Nullable
    public CreateCredentialsResponse getSessionCredentials(@NotNull String sessionId) {
        CreateCredentialsResponse createCredentialsResponse;
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        if (!(!Intrinsics.areEqual(this.cachedSessionId, sessionId)) && (createCredentialsResponse = this.cachedCredentials) != null) {
            if (this.timestamp + createCredentialsResponse.getDurationSeconds() >= getTimeInSeconds()) {
                return createCredentialsResponse;
            }
            this.logger.w(TAG, "Cached credentials have expired, returning null");
            this.metricsHelper.addCount(TAG, METRIC_CREDENTIALS_EXPIRED, 1.0d);
            return null;
        }
        return null;
    }

    @Override // com.amazon.tarazed.core.sessionclient.sessioncache.SessionClientCache
    public void putLaunchRequest(@NotNull String launchRequest) {
        Intrinsics.checkParameterIsNotNull(launchRequest, "launchRequest");
        TarazedSessionLogger tarazedSessionLogger = this.logger;
        tarazedSessionLogger.i(TAG, "Caching launch request: " + launchRequest);
        this.launchRequest = (TarazedLaunchRequest) Json.Default.parse(TarazedLaunchRequest.Companion.serializer(), launchRequest);
    }

    @Override // com.amazon.tarazed.core.sessionclient.sessioncache.SessionClientCache
    public void putSessionCredentials(@NotNull String sessionId, @NotNull CreateCredentialsResponse sessionCredentials) {
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        Intrinsics.checkParameterIsNotNull(sessionCredentials, "sessionCredentials");
        this.cachedSessionId = sessionId;
        this.cachedCredentials = sessionCredentials;
        this.timestamp = getTimeInSeconds();
    }
}
