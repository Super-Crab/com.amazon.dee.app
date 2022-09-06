package com.amazon.tarazed.core.sessionclient.sessioncache;

import android.content.Context;
import com.amazon.alexa.client.metrics.kinesis.session.client.AppDefaultSessionClient;
import com.amazon.tarazed.core.Cache;
import com.amazon.tarazed.core.SingleEntryCache;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.notification.type.TarazedLaunchRequest;
import com.amazon.tarazed.core.sessionclient.model.createcredentials.CreateCredentialsResponse;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.json.Json;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Post23SessionClientCache.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\u0011\u001a\u00020\u0012H\u0016J\n\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u0014H\u0016J\b\u0010\u0018\u001a\u00020\u0019H\u0002J\u0010\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u0014H\u0016J\u0018\u0010\u001c\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u001d\u001a\u00020\u0016H\u0016R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/amazon/tarazed/core/sessionclient/sessioncache/Post23SessionClientCache;", "Lcom/amazon/tarazed/core/sessionclient/sessioncache/SessionClientCache;", "context", "Landroid/content/Context;", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "metricsHelper", "Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "cipher", "Lcom/amazon/tarazed/core/sessionclient/sessioncache/SessionCredentialsCipher;", "(Landroid/content/Context;Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;Lcom/amazon/tarazed/core/sessionclient/sessioncache/SessionCredentialsCipher;)V", "credentialsCache", "Lcom/amazon/tarazed/core/Cache;", "Lcom/amazon/tarazed/core/sessionclient/sessioncache/CachedSessionCredentials;", "launchRequestCache", "Lcom/amazon/tarazed/core/SingleEntryCache;", "Lcom/amazon/tarazed/core/notification/type/TarazedLaunchRequest;", "clearLaunchRequest", "", "getLaunchRequest", "", "getSessionCredentials", "Lcom/amazon/tarazed/core/sessionclient/model/createcredentials/CreateCredentialsResponse;", AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY, "getTimeInSeconds", "", "putLaunchRequest", Post23SessionClientCache.LAUNCH_REQUEST_CACHE, "putSessionCredentials", Post23SessionClientCache.CACHE_DIR, "Companion", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class Post23SessionClientCache implements SessionClientCache {
    @NotNull
    public static final String CACHE_DIR = "sessionCredentials";
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String LAUNCH_REQUEST_CACHE = "launchRequest";
    private static final String METRIC_CREDENTIALS_EXPIRED = "CredentialsExpired";
    private static final String METRIC_DECRYPTION_FAILED = "CredentialsDecryptionFailed";
    private static final String TAG = "SessionClientCache";
    private final SessionCredentialsCipher cipher;
    private final Context context;
    private Cache<CachedSessionCredentials> credentialsCache;
    private SingleEntryCache<TarazedLaunchRequest> launchRequestCache;
    private final TarazedSessionLogger logger;
    private final TarazedMetricsHelper metricsHelper;

    /* compiled from: Post23SessionClientCache.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/amazon/tarazed/core/sessionclient/sessioncache/Post23SessionClientCache$Companion;", "", "()V", "CACHE_DIR", "", "LAUNCH_REQUEST_CACHE", "METRIC_CREDENTIALS_EXPIRED", "METRIC_DECRYPTION_FAILED", "TAG", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public Post23SessionClientCache(@NotNull Context context, @NotNull TarazedSessionLogger logger, @NotNull TarazedMetricsHelper metricsHelper, @NotNull SessionCredentialsCipher cipher) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metricsHelper, "metricsHelper");
        Intrinsics.checkParameterIsNotNull(cipher, "cipher");
        this.context = context;
        this.logger = logger;
        this.metricsHelper = metricsHelper;
        this.cipher = cipher;
        this.credentialsCache = new Cache<>(this.context, this.logger, this.metricsHelper, CachedSessionCredentials.Companion.serializer(), CACHE_DIR);
        this.launchRequestCache = new SingleEntryCache<>(this.context, this.logger, this.metricsHelper, TarazedLaunchRequest.Companion.serializer(), LAUNCH_REQUEST_CACHE, LAUNCH_REQUEST_CACHE);
    }

    private final long getTimeInSeconds() {
        return System.currentTimeMillis() / 1000;
    }

    @Override // com.amazon.tarazed.core.sessionclient.sessioncache.SessionClientCache
    public void clearLaunchRequest() {
        this.launchRequestCache.clearCache();
    }

    @Override // com.amazon.tarazed.core.sessionclient.sessioncache.SessionClientCache
    @Nullable
    public String getLaunchRequest() {
        TarazedLaunchRequest tarazedLaunchRequest = this.launchRequestCache.get();
        if (tarazedLaunchRequest != null) {
            if (getSessionCredentials(tarazedLaunchRequest.getSessionId()) == null) {
                this.logger.w(TAG, "Cached launch request does not correspond to any active cached credentials, returning null");
                return null;
            }
            Json.Default r1 = Json.Default;
            KSerializer<TarazedLaunchRequest> serializer = TarazedLaunchRequest.Companion.serializer();
            if (serializer != null) {
                return r1.stringify(serializer, tarazedLaunchRequest);
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlinx.serialization.SerializationStrategy<com.amazon.tarazed.core.notification.type.TarazedLaunchRequest?>");
        }
        return null;
    }

    @Override // com.amazon.tarazed.core.sessionclient.sessioncache.SessionClientCache
    @Nullable
    public CreateCredentialsResponse getSessionCredentials(@NotNull String sessionId) {
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        CachedSessionCredentials cachedSessionCredentials = this.credentialsCache.get(sessionId);
        if (cachedSessionCredentials != null) {
            try {
                CreateCredentialsResponse decryptCredentials = this.cipher.decryptCredentials(cachedSessionCredentials);
                if (cachedSessionCredentials.getTimestamp() + decryptCredentials.getDurationSeconds() >= getTimeInSeconds()) {
                    return decryptCredentials;
                }
                this.logger.w(TAG, "Cached credentials have expired, returning null");
                this.metricsHelper.addCount(TAG, METRIC_CREDENTIALS_EXPIRED, 1.0d);
                return null;
            } catch (DecryptCredentialsException e) {
                this.logger.w(TAG, "Exception occurred while decrypting credentials", e);
                this.metricsHelper.addCount(TAG, METRIC_DECRYPTION_FAILED, 1.0d);
            }
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.amazon.tarazed.core.sessionclient.sessioncache.SessionClientCache
    public void putLaunchRequest(@NotNull String launchRequest) {
        Intrinsics.checkParameterIsNotNull(launchRequest, "launchRequest");
        this.launchRequestCache.put(Json.Default.parse(TarazedLaunchRequest.Companion.serializer(), launchRequest));
    }

    @Override // com.amazon.tarazed.core.sessionclient.sessioncache.SessionClientCache
    public void putSessionCredentials(@NotNull String sessionId, @NotNull CreateCredentialsResponse sessionCredentials) {
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        Intrinsics.checkParameterIsNotNull(sessionCredentials, "sessionCredentials");
        this.cipher.deleteKeyIfExists();
        EncryptedCredentialsData encryptCredentials = this.cipher.encryptCredentials(sessionCredentials);
        this.credentialsCache.put(sessionId, new CachedSessionCredentials(getTimeInSeconds(), encryptCredentials.component1(), encryptCredentials.component2(), encryptCredentials.component3()));
    }
}
