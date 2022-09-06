package com.amazon.tarazed.sessionmanager;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.amazon.alexa.client.metrics.kinesis.session.client.AppDefaultSessionClient;
import com.amazon.tarazed.core.sessionclient.model.createcredentials.CreateCredentialsResponse;
import com.amazon.tarazed.core.sessionclient.sessioncache.ISessionClientCache;
import com.amazon.tarazed.core.sessionclient.sessioncache.Post23SessionClientCache;
import com.amazon.tarazed.core.sessionclient.sessioncache.SessionClientCache;
import com.amazon.tarazed.dagger.injectors.LibraryInjector;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.json.Json;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SessionClientCacheService.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001:\u0001\u000fB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u0010"}, d2 = {"Lcom/amazon/tarazed/sessionmanager/SessionClientCacheService;", "Landroid/app/Service;", "()V", "sessionClientCache", "Lcom/amazon/tarazed/core/sessionclient/sessioncache/SessionClientCache;", "getSessionClientCache", "()Lcom/amazon/tarazed/core/sessionclient/sessioncache/SessionClientCache;", "setSessionClientCache", "(Lcom/amazon/tarazed/core/sessionclient/sessioncache/SessionClientCache;)V", "onBind", "Landroid/os/IBinder;", "p0", "Landroid/content/Intent;", "onCreate", "", "LocalBinder", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class SessionClientCacheService extends Service {
    @Inject
    @NotNull
    public SessionClientCache sessionClientCache;

    /* compiled from: SessionClientCacheService.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\n\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\u0012\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\n\u001a\u00020\bH\u0016J\u0010\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\bH\u0016J\u0018\u0010\r\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/amazon/tarazed/sessionmanager/SessionClientCacheService$LocalBinder;", "Lcom/amazon/tarazed/core/sessionclient/sessioncache/ISessionClientCache$Stub;", "sessionClientCache", "Lcom/amazon/tarazed/core/sessionclient/sessioncache/SessionClientCache;", "(Lcom/amazon/tarazed/core/sessionclient/sessioncache/SessionClientCache;)V", "clearLaunchRequest", "", "getLaunchRequest", "", "getSessionCredentials", AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY, "putLaunchRequest", Post23SessionClientCache.LAUNCH_REQUEST_CACHE, "putSessionCredentials", "createCredentialsResponse", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    private static final class LocalBinder extends ISessionClientCache.Stub {
        private final SessionClientCache sessionClientCache;

        public LocalBinder(@NotNull SessionClientCache sessionClientCache) {
            Intrinsics.checkParameterIsNotNull(sessionClientCache, "sessionClientCache");
            this.sessionClientCache = sessionClientCache;
        }

        @Override // com.amazon.tarazed.core.sessionclient.sessioncache.ISessionClientCache
        public void clearLaunchRequest() {
            this.sessionClientCache.clearLaunchRequest();
        }

        @Override // com.amazon.tarazed.core.sessionclient.sessioncache.ISessionClientCache
        @Nullable
        public String getLaunchRequest() {
            return this.sessionClientCache.getLaunchRequest();
        }

        @Override // com.amazon.tarazed.core.sessionclient.sessioncache.ISessionClientCache
        @Nullable
        public String getSessionCredentials(@NotNull String sessionId) {
            Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
            CreateCredentialsResponse sessionCredentials = this.sessionClientCache.getSessionCredentials(sessionId);
            if (sessionCredentials != null) {
                Json.Default r0 = Json.Default;
                KSerializer<CreateCredentialsResponse> serializer = CreateCredentialsResponse.Companion.serializer();
                if (serializer == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlinx.serialization.SerializationStrategy<com.amazon.tarazed.core.sessionclient.model.createcredentials.CreateCredentialsResponse?>");
                }
                return r0.stringify(serializer, sessionCredentials);
            }
            return null;
        }

        @Override // com.amazon.tarazed.core.sessionclient.sessioncache.ISessionClientCache
        public void putLaunchRequest(@NotNull String launchRequest) {
            Intrinsics.checkParameterIsNotNull(launchRequest, "launchRequest");
            this.sessionClientCache.putLaunchRequest(launchRequest);
        }

        @Override // com.amazon.tarazed.core.sessionclient.sessioncache.ISessionClientCache
        public void putSessionCredentials(@NotNull String sessionId, @NotNull String createCredentialsResponse) {
            Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
            Intrinsics.checkParameterIsNotNull(createCredentialsResponse, "createCredentialsResponse");
            this.sessionClientCache.putSessionCredentials(sessionId, (CreateCredentialsResponse) Json.Default.getNonstrict().parse(CreateCredentialsResponse.Companion.serializer(), createCredentialsResponse));
        }
    }

    @NotNull
    public final SessionClientCache getSessionClientCache() {
        SessionClientCache sessionClientCache = this.sessionClientCache;
        if (sessionClientCache == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sessionClientCache");
        }
        return sessionClientCache;
    }

    @Override // android.app.Service
    @NotNull
    public IBinder onBind(@NotNull Intent p0) {
        Intrinsics.checkParameterIsNotNull(p0, "p0");
        SessionClientCache sessionClientCache = this.sessionClientCache;
        if (sessionClientCache == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sessionClientCache");
        }
        return new LocalBinder(sessionClientCache);
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        LibraryInjector.getComponent().inject(this);
    }

    public final void setSessionClientCache(@NotNull SessionClientCache sessionClientCache) {
        Intrinsics.checkParameterIsNotNull(sessionClientCache, "<set-?>");
        this.sessionClientCache = sessionClientCache;
    }
}
