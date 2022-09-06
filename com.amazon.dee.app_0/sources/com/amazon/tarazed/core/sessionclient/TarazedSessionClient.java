package com.amazon.tarazed.core.sessionclient;

import com.amazon.alexa.routing.api.RouteParameter;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.sessionclient.model.createcredentials.CreateCredentialsRequest;
import com.amazon.tarazed.core.sessionclient.model.createcredentials.CreateCredentialsResponse;
import com.amazon.tarazed.core.sessionclient.sessioncache.ISessionClientCache;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.ktor.client.HttpClient;
import io.ktor.client.engine.android.AndroidClientEngine;
import io.ktor.client.engine.android.AndroidEngineConfig;
import io.ktor.client.features.BadResponseStatusException;
import io.ktor.http.HttpStatusCode;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: TarazedSessionClient.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u0000 &2\u00020\u0001:\u0001&B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u0006\u0010\r\u001a\u00020\u000eJ\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0013J!\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0003H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0017J\u001c\u0010\u0018\u001a\u00020\u000e2\n\u0010\u0019\u001a\u00060\u001aj\u0002`\u001b2\u0006\u0010\u001c\u001a\u00020\u0003H\u0002J\u0019\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0011\u001a\u00020\u001fH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010 J\u0019\u0010!\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0013J!\u0010\"\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010#\u001a\u00020$H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010%R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006'"}, d2 = {"Lcom/amazon/tarazed/core/sessionclient/TarazedSessionClient;", "", "sessionHostname", "", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "metricsHelper", "Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "sessionCacheService", "Lcom/amazon/tarazed/core/sessionclient/sessioncache/ISessionClientCache;", "httpClient", "Lio/ktor/client/HttpClient;", "(Ljava/lang/String;Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;Lcom/amazon/tarazed/core/sessionclient/sessioncache/ISessionClientCache;Lio/ktor/client/HttpClient;)V", "close", "", "createCredentials", "Lcom/amazon/tarazed/core/sessionclient/model/createcredentials/CreateCredentialsResponse;", "request", "Lcom/amazon/tarazed/core/sessionclient/model/createcredentials/CreateCredentialsRequest;", "(Lcom/amazon/tarazed/core/sessionclient/model/createcredentials/CreateCredentialsRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "doRequest", RouteParameter.PATH, "body", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "emitFailureMetric", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "metricPrefix", "endSession", "Lcom/amazon/tarazed/core/sessionclient/model/endsession/EndSessionResponse;", "Lcom/amazon/tarazed/core/sessionclient/model/endsession/EndSessionRequest;", "(Lcom/amazon/tarazed/core/sessionclient/model/endsession/EndSessionRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "refreshCredentials", "requestCredentials", "requestFromCache", "", "(Lcom/amazon/tarazed/core/sessionclient/model/createcredentials/CreateCredentialsRequest;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class TarazedSessionClient {
    @Deprecated
    public static final Companion Companion = new Companion(null);
    private static final String METRIC_AUTH_FAILED = "AuthFailed";
    private static final String METRIC_BAD_RESPONSE_RECEIVED = "BadResponseReceived";
    private static final String METRIC_CREATE_CREDENTIALS_ATTEMPTED = "CreateCredentialsAttempted";
    private static final String METRIC_CREATE_CREDENTIALS_SUCCEEDED = "CreateCredentialsSucceeded";
    private static final String METRIC_END_SESSION_ATTEMPTED = "EndSessionAttempted";
    private static final String METRIC_END_SESSION_SUCCEEDED = "EndSessionSucceeded";
    private static final String METRIC_PREFIX_CREATE_CREDENTIALS = "CreateCredentials";
    private static final String METRIC_PREFIX_END_SESSION = "EndSession";
    private static final String METRIC_SUFFIX_BAD_REQUEST = "BadRequest";
    private static final String METRIC_SUFFIX_FAILED = "Failed";
    private static final String TAG = "TarazedSessionClient";
    private final HttpClient httpClient;
    private final TarazedSessionLogger logger;
    private final TarazedMetricsHelper metricsHelper;
    private final ISessionClientCache sessionCacheService;
    private final String sessionHostname;

    /* compiled from: TarazedSessionClient.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/amazon/tarazed/core/sessionclient/TarazedSessionClient$Companion;", "", "()V", "METRIC_AUTH_FAILED", "", "METRIC_BAD_RESPONSE_RECEIVED", "METRIC_CREATE_CREDENTIALS_ATTEMPTED", "METRIC_CREATE_CREDENTIALS_SUCCEEDED", "METRIC_END_SESSION_ATTEMPTED", "METRIC_END_SESSION_SUCCEEDED", "METRIC_PREFIX_CREATE_CREDENTIALS", "METRIC_PREFIX_END_SESSION", "METRIC_SUFFIX_BAD_REQUEST", "METRIC_SUFFIX_FAILED", "TAG", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public TarazedSessionClient(@NotNull String sessionHostname, @NotNull TarazedSessionLogger logger, @NotNull TarazedMetricsHelper metricsHelper, @NotNull ISessionClientCache sessionCacheService, @NotNull HttpClient httpClient) {
        Intrinsics.checkParameterIsNotNull(sessionHostname, "sessionHostname");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metricsHelper, "metricsHelper");
        Intrinsics.checkParameterIsNotNull(sessionCacheService, "sessionCacheService");
        Intrinsics.checkParameterIsNotNull(httpClient, "httpClient");
        this.sessionHostname = sessionHostname;
        this.logger = logger;
        this.metricsHelper = metricsHelper;
        this.sessionCacheService = sessionCacheService;
        this.httpClient = httpClient;
    }

    private final void emitFailureMetric(Exception exc, String str) {
        if ((exc instanceof BadResponseStatusException) && Intrinsics.areEqual(((BadResponseStatusException) exc).getStatusCode(), HttpStatusCode.Companion.getBadRequest())) {
            this.metricsHelper.addCount(TAG, GeneratedOutlineSupport1.outline72(str, METRIC_SUFFIX_BAD_REQUEST), 1.0d);
        } else {
            this.metricsHelper.addCount(TAG, GeneratedOutlineSupport1.outline72(str, "Failed"), 1.0d);
        }
    }

    public final void close() {
        this.httpClient.close();
    }

    @Nullable
    public final Object createCredentials(@NotNull CreateCredentialsRequest createCredentialsRequest, @NotNull Continuation<? super CreateCredentialsResponse> continuation) {
        return requestCredentials(createCredentialsRequest, true, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0152 A[Catch: NoAuthenticatedUserException -> 0x00b0, TarazedAuthenticationException -> 0x00b8, BadResponseStatusException -> 0x01ab, UnknownHostException -> 0x01ae, TryCatch #17 {BadResponseStatusException -> 0x01ab, UnknownHostException -> 0x01ae, blocks: (B:57:0x019c, B:59:0x019f, B:60:0x01a6, B:26:0x00a5, B:42:0x0141, B:44:0x0152, B:46:0x0157, B:48:0x015f, B:50:0x016a, B:51:0x016d, B:53:0x0175, B:73:0x01b9, B:74:0x01c4), top: B:109:0x00a5 }] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0157 A[Catch: NoAuthenticatedUserException -> 0x00b0, TarazedAuthenticationException -> 0x00b8, BadResponseStatusException -> 0x01ab, UnknownHostException -> 0x01ae, TRY_LEAVE, TryCatch #17 {BadResponseStatusException -> 0x01ab, UnknownHostException -> 0x01ae, blocks: (B:57:0x019c, B:59:0x019f, B:60:0x01a6, B:26:0x00a5, B:42:0x0141, B:44:0x0152, B:46:0x0157, B:48:0x015f, B:50:0x016a, B:51:0x016d, B:53:0x0175, B:73:0x01b9, B:74:0x01c4), top: B:109:0x00a5 }] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x019c A[Catch: NoAuthenticatedUserException -> 0x01a7, TarazedAuthenticationException -> 0x01a9, BadResponseStatusException -> 0x01ab, UnknownHostException -> 0x01ae, TryCatch #17 {BadResponseStatusException -> 0x01ab, UnknownHostException -> 0x01ae, blocks: (B:57:0x019c, B:59:0x019f, B:60:0x01a6, B:26:0x00a5, B:42:0x0141, B:44:0x0152, B:46:0x0157, B:48:0x015f, B:50:0x016a, B:51:0x016d, B:53:0x0175, B:73:0x01b9, B:74:0x01c4), top: B:109:0x00a5 }] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x019f A[Catch: NoAuthenticatedUserException -> 0x01a7, TarazedAuthenticationException -> 0x01a9, BadResponseStatusException -> 0x01ab, UnknownHostException -> 0x01ae, TryCatch #17 {BadResponseStatusException -> 0x01ab, UnknownHostException -> 0x01ae, blocks: (B:57:0x019c, B:59:0x019f, B:60:0x01a6, B:26:0x00a5, B:42:0x0141, B:44:0x0152, B:46:0x0157, B:48:0x015f, B:50:0x016a, B:51:0x016d, B:53:0x0175, B:73:0x01b9, B:74:0x01c4), top: B:109:0x00a5 }] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x01b9 A[Catch: NoAuthenticatedUserException -> 0x01a7, TarazedAuthenticationException -> 0x01a9, BadResponseStatusException -> 0x01ab, UnknownHostException -> 0x01ae, TryCatch #17 {BadResponseStatusException -> 0x01ab, UnknownHostException -> 0x01ae, blocks: (B:57:0x019c, B:59:0x019f, B:60:0x01a6, B:26:0x00a5, B:42:0x0141, B:44:0x0152, B:46:0x0157, B:48:0x015f, B:50:0x016a, B:51:0x016d, B:53:0x0175, B:73:0x01b9, B:74:0x01c4), top: B:109:0x00a5 }] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object doRequest(@org.jetbrains.annotations.NotNull java.lang.String r22, @org.jetbrains.annotations.NotNull java.lang.String r23, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.String> r24) {
        /*
            Method dump skipped, instructions count: 597
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.tarazed.core.sessionclient.TarazedSessionClient.doRequest(java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x004b  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object endSession(@org.jetbrains.annotations.NotNull com.amazon.tarazed.core.sessionclient.model.endsession.EndSessionRequest r9, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.amazon.tarazed.core.sessionclient.model.endsession.EndSessionResponse> r10) {
        /*
            Method dump skipped, instructions count: 218
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.tarazed.core.sessionclient.TarazedSessionClient.endSession(com.amazon.tarazed.core.sessionclient.model.endsession.EndSessionRequest, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public final Object refreshCredentials(@NotNull CreateCredentialsRequest createCredentialsRequest, @NotNull Continuation<? super CreateCredentialsResponse> continuation) {
        return requestCredentials(createCredentialsRequest, false, continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x004d  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object requestCredentials(@org.jetbrains.annotations.NotNull com.amazon.tarazed.core.sessionclient.model.createcredentials.CreateCredentialsRequest r9, boolean r10, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.amazon.tarazed.core.sessionclient.model.createcredentials.CreateCredentialsResponse> r11) {
        /*
            Method dump skipped, instructions count: 258
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.tarazed.core.sessionclient.TarazedSessionClient.requestCredentials(com.amazon.tarazed.core.sessionclient.model.createcredentials.CreateCredentialsRequest, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public /* synthetic */ TarazedSessionClient(String str, TarazedSessionLogger tarazedSessionLogger, TarazedMetricsHelper tarazedMetricsHelper, ISessionClientCache iSessionClientCache, HttpClient httpClient, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, tarazedSessionLogger, tarazedMetricsHelper, iSessionClientCache, (i & 16) != 0 ? new HttpClient(new AndroidClientEngine(new AndroidEngineConfig()), null, 2, null) : httpClient);
    }
}
