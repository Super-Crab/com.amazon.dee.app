package com.amazon.alexa.fitness.repository;

import com.dee.app.cachemanager.DefaultObjectCache;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: CacheProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\b\u000b\bf\u0018\u00002\u00020\u0001R\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0018\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\u0006R\u0018\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u0006R\u0018\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\u0006R\u0018\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0006¨\u0006\u000f"}, d2 = {"Lcom/amazon/alexa/fitness/repository/CacheProvider;", "", "accessoryFitnessSessionCache", "Lcom/dee/app/cachemanager/DefaultObjectCache;", "", "getAccessoryFitnessSessionCache", "()Lcom/dee/app/cachemanager/DefaultObjectCache;", "recoveryCache", "getRecoveryCache", "sampleCache", "getSampleCache", "sessionSummaryCache", "getSessionSummaryCache", "userProfileCache", "getUserProfileCache", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public interface CacheProvider {
    @NotNull
    DefaultObjectCache<byte[]> getAccessoryFitnessSessionCache();

    @NotNull
    DefaultObjectCache<byte[]> getRecoveryCache();

    @NotNull
    DefaultObjectCache<byte[]> getSampleCache();

    @NotNull
    DefaultObjectCache<byte[]> getSessionSummaryCache();

    @NotNull
    DefaultObjectCache<byte[]> getUserProfileCache();
}
