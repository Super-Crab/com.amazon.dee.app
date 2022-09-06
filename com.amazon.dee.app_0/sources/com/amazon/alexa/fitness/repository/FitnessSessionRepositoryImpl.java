package com.amazon.alexa.fitness.repository;

import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.sdk.AccessoryFitnessSession;
import com.amazon.alexa.fitness.util.SerializationUtilsKt;
import com.dee.app.cachemanager.CacheMetadata;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.SerializationUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: FitnessSessionRepositoryImpl.kt */
@Singleton
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\t\u001a\u00020\nH\u0016J\n\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\u0010\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\fH\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/amazon/alexa/fitness/repository/FitnessSessionRepositoryImpl;", "Lcom/amazon/alexa/fitness/repository/FitnessSessionRepository;", "cacheProvider", "Lcom/amazon/alexa/fitness/repository/CacheProvider;", "log", "Lcom/amazon/alexa/fitness/logs/ILog;", "(Lcom/amazon/alexa/fitness/repository/CacheProvider;Lcom/amazon/alexa/fitness/logs/ILog;)V", "cacheMetaData", "Lcom/dee/app/cachemanager/CacheMetadata;", "deleteAccessoryFitnessSession", "", "getAccessoryFitnessSession", "Lcom/amazon/alexa/fitness/sdk/AccessoryFitnessSession;", "saveAccessoryFitnessSession", "accessoryFitnessSession", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class FitnessSessionRepositoryImpl implements FitnessSessionRepository {
    private final CacheMetadata cacheMetaData;
    private final CacheProvider cacheProvider;
    private final ILog log;

    @Inject
    public FitnessSessionRepositoryImpl(@NotNull CacheProvider cacheProvider, @NotNull ILog log) {
        Intrinsics.checkParameterIsNotNull(cacheProvider, "cacheProvider");
        Intrinsics.checkParameterIsNotNull(log, "log");
        this.cacheProvider = cacheProvider;
        this.log = log;
        this.cacheMetaData = new CacheMetadata(1);
    }

    @Override // com.amazon.alexa.fitness.repository.FitnessSessionRepository
    public void deleteAccessoryFitnessSession() {
        this.cacheProvider.getAccessoryFitnessSessionCache().remove("FitnessSessionCacheKey", this.cacheMetaData).toBlocking().singleOrDefault(null);
        ILog.DefaultImpls.info$default(this.log, "FitnessSessionRepository", "All AccessoryFitnessSession objects have been deleted.", null, 4, null);
    }

    @Override // com.amazon.alexa.fitness.repository.FitnessSessionRepository
    @Nullable
    public AccessoryFitnessSession getAccessoryFitnessSession() {
        byte[] orNull = this.cacheProvider.getAccessoryFitnessSessionCache().get("FitnessSessionCacheKey", this.cacheMetaData).toBlocking().single().orNull();
        AccessoryFitnessSession accessoryFitnessSession = null;
        if (orNull != null) {
            try {
                Object deserialize = SerializationUtils.deserialize(orNull);
                if (deserialize == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.amazon.alexa.fitness.sdk.AccessoryFitnessSession");
                }
                accessoryFitnessSession = (AccessoryFitnessSession) deserialize;
            } catch (Exception unused) {
            }
        }
        ILog iLog = this.log;
        ILog.DefaultImpls.debug$default(iLog, "FitnessSessionRepository", "AccessoryFitnessSession retrieved from secure storage: " + accessoryFitnessSession, null, 4, null);
        return accessoryFitnessSession;
    }

    @Override // com.amazon.alexa.fitness.repository.FitnessSessionRepository
    public void saveAccessoryFitnessSession(@NotNull AccessoryFitnessSession accessoryFitnessSession) {
        Intrinsics.checkParameterIsNotNull(accessoryFitnessSession, "accessoryFitnessSession");
        this.cacheProvider.getAccessoryFitnessSessionCache().put("FitnessSessionCacheKey", SerializationUtilsKt.serialize(accessoryFitnessSession), this.cacheMetaData).toBlocking().singleOrDefault(null);
        ILog iLog = this.log;
        ILog.DefaultImpls.debug$default(iLog, "FitnessSessionRepository", "AccessoryFitnessSession saved: " + accessoryFitnessSession, null, 4, null);
    }
}
