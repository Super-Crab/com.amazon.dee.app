package com.amazon.alexa.fitness.repository;

import com.amazon.alexa.client.metrics.kinesis.session.client.AppDefaultSessionClient;
import com.amazon.alexa.fitness.api.util.DateTime;
import com.amazon.alexa.fitness.identity.IdentityManager;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.service.hds.model.SessionSummary;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.cachemanager.CacheMetadata;
import com.dee.app.cachemanager.DefaultObjectCache;
import com.dee.app.data.reactnative.bridges.HttpClientModule;
import com.google.common.base.Optional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rx.Observable;
/* compiled from: SessionSummaryCacheImpl.kt */
@Singleton
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u000e\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0016J\u001a\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u00122\b\u0010\u0015\u001a\u0004\u0018\u00010\u000fH\u0016J\u0018\u0010\u0016\u001a\u00020\u0017*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00190\u0018H\u0002R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/amazon/alexa/fitness/repository/SessionSummaryCacheImpl;", "Lcom/amazon/alexa/fitness/repository/SessionSummaryCache;", "cacheProvider", "Lcom/amazon/alexa/fitness/repository/CacheProvider;", "identityManager", "Lcom/amazon/alexa/fitness/identity/IdentityManager;", "log", "Lcom/amazon/alexa/fitness/logs/ILog;", "(Lcom/amazon/alexa/fitness/repository/CacheProvider;Lcom/amazon/alexa/fitness/identity/IdentityManager;Lcom/amazon/alexa/fitness/logs/ILog;)V", HttpClientModule.ElementsRequestKey.CACHE, "Lcom/dee/app/cachemanager/DefaultObjectCache;", "", "deletePendingSessionUpload", "", AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY, "", "getPendingSessions", "", "Lcom/amazon/alexa/fitness/service/hds/model/SessionSummary;", "savePendingSessionUpload", "sessionSummary", "lastException", "toSessionSummary", "Lcom/amazon/alexa/fitness/repository/PendingSessionSummaryMap;", "Lrx/Observable;", "Lcom/google/common/base/Optional;", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class SessionSummaryCacheImpl implements SessionSummaryCache {
    private final DefaultObjectCache<byte[]> cache;
    private final IdentityManager identityManager;
    private final ILog log;

    @Inject
    public SessionSummaryCacheImpl(@NotNull CacheProvider cacheProvider, @NotNull IdentityManager identityManager, @NotNull ILog log) {
        Intrinsics.checkParameterIsNotNull(cacheProvider, "cacheProvider");
        Intrinsics.checkParameterIsNotNull(identityManager, "identityManager");
        Intrinsics.checkParameterIsNotNull(log, "log");
        this.identityManager = identityManager;
        this.log = log;
        this.cache = cacheProvider.getSessionSummaryCache();
    }

    private final PendingSessionSummaryMap toSessionSummary(@NotNull Observable<Optional<byte[]>> observable) {
        Object or = observable.toBlocking().single().transform(SessionSummaryCacheImpl$toSessionSummary$1.INSTANCE).or((Optional<V>) new PendingSessionSummaryMap(new LinkedHashMap()));
        Intrinsics.checkExpressionValueIsNotNull(or, "this.toBlocking()\n      …mmaryMap(mutableMapOf()))");
        return (PendingSessionSummaryMap) or;
    }

    @Override // com.amazon.alexa.fitness.repository.SessionSummaryCache
    public void deletePendingSessionUpload(@NotNull String sessionId) {
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        String userProfileDirectedId = this.identityManager.getUserProfileDirectedId();
        if (userProfileDirectedId != null) {
            try {
                Observable<Optional<byte[]>> observable = this.cache.get(userProfileDirectedId, new CacheMetadata(1));
                Intrinsics.checkExpressionValueIsNotNull(observable, "cache.get(userProfileDir…ata(Cache.HIGH_PRIORITY))");
                PendingSessionSummaryMap sessionSummary = toSessionSummary(observable);
                if (sessionSummary.getSessionIdToSummaryMap().get(sessionId) == null) {
                    ILog iLog = this.log;
                    ILog.DefaultImpls.warn$default(iLog, "SessionSummaryCacheImpl", "Session with ID " + sessionId + " was not found in the cache", null, 4, null);
                    return;
                }
                sessionSummary.getSessionIdToSummaryMap().remove(sessionId);
                this.cache.put(userProfileDirectedId, sessionSummary.serialize(), new CacheMetadata(1)).toBlocking().singleOrDefault(null);
                ILog.DefaultImpls.debug$default(this.log, "SessionSummaryCacheImpl", GeneratedOutlineSupport1.outline75("Removed session summary with ID ", sessionId, " from the cache"), null, 4, null);
                return;
            } catch (Throwable th) {
                this.log.error("SessionSummaryCacheImpl", "Caught exception while locally saving pending session uploads", th);
                throw th;
            }
        }
        ILog.DefaultImpls.error$default(this.log, "SessionSummaryCacheImpl", "Profile directed ID was empty while locally saving pending session uploads", null, 4, null);
        throw new IllegalStateException("Profile directed ID was empty");
    }

    @Override // com.amazon.alexa.fitness.repository.SessionSummaryCache
    @NotNull
    public Set<SessionSummary> getPendingSessions() {
        int collectionSizeOrDefault;
        Set<SessionSummary> set;
        String userProfileDirectedId = this.identityManager.getUserProfileDirectedId();
        if (userProfileDirectedId != null) {
            try {
                Observable<Optional<byte[]>> observable = this.cache.get(userProfileDirectedId, new CacheMetadata(1));
                Intrinsics.checkExpressionValueIsNotNull(observable, "cache.get(userProfileDir…ata(Cache.HIGH_PRIORITY))");
                Collection<PendingSessionSummary> values = toSessionSummary(observable).getSessionIdToSummaryMap().values();
                collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(values, 10);
                ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
                for (PendingSessionSummary pendingSessionSummary : values) {
                    arrayList.add(pendingSessionSummary.getSessionSummary());
                }
                set = CollectionsKt___CollectionsKt.toSet(arrayList);
                return set;
            } catch (Throwable th) {
                this.log.error("SessionSummaryCacheImpl", "Caught exception while locally saving pending session uploads", th);
                throw th;
            }
        }
        ILog.DefaultImpls.error$default(this.log, "SessionSummaryCacheImpl", "Profile directed ID was empty while locally saving pending session uploads", null, 4, null);
        throw new IllegalStateException("Profile directed ID was empty");
    }

    @Override // com.amazon.alexa.fitness.repository.SessionSummaryCache
    public void savePendingSessionUpload(@NotNull SessionSummary sessionSummary, @Nullable String str) {
        PendingSessionSummary pendingSessionSummary;
        Intrinsics.checkParameterIsNotNull(sessionSummary, "sessionSummary");
        String userProfileDirectedId = this.identityManager.getUserProfileDirectedId();
        if (userProfileDirectedId != null) {
            try {
                Observable<Optional<byte[]>> observable = this.cache.get(userProfileDirectedId, new CacheMetadata(1));
                Intrinsics.checkExpressionValueIsNotNull(observable, "cache.get(userProfileDir…ata(Cache.HIGH_PRIORITY))");
                PendingSessionSummaryMap sessionSummary2 = toSessionSummary(observable);
                PendingSessionSummary pendingSessionSummary2 = sessionSummary2.getSessionIdToSummaryMap().get(sessionSummary.getSessionId().toString());
                if (pendingSessionSummary2 != null) {
                    pendingSessionSummary = new PendingSessionSummary(sessionSummary, userProfileDirectedId, pendingSessionSummary2.getFailedUploadAttempts() + 1, pendingSessionSummary2.getFirstFailedTime(), DateTime.Companion.now(), str);
                } else {
                    pendingSessionSummary = new PendingSessionSummary(sessionSummary, userProfileDirectedId, 0, null, null, null);
                }
                Map<String, PendingSessionSummary> sessionIdToSummaryMap = sessionSummary2.getSessionIdToSummaryMap();
                String uuid = sessionSummary.getSessionId().toString();
                Intrinsics.checkExpressionValueIsNotNull(uuid, "sessionSummary.sessionId.toString()");
                sessionIdToSummaryMap.put(uuid, pendingSessionSummary);
                this.cache.put(userProfileDirectedId, sessionSummary2.serialize(), new CacheMetadata(1)).toBlocking().singleOrDefault(null);
                ILog iLog = this.log;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Saved session summary with ID ");
                outline107.append(sessionSummary.getSessionId());
                ILog.DefaultImpls.debug$default(iLog, "SessionSummaryCacheImpl", outline107.toString(), null, 4, null);
                return;
            } catch (Throwable th) {
                this.log.error("SessionSummaryCacheImpl", "Caught exception while locally saving pending session uploads", th);
                throw th;
            }
        }
        ILog.DefaultImpls.error$default(this.log, "SessionSummaryCacheImpl", "Profile directed ID was empty while locally saving pending session uploads", null, 4, null);
        throw new IllegalStateException("Profile directed ID was empty");
    }
}
