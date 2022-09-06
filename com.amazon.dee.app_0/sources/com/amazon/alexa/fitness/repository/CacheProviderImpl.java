package com.amazon.alexa.fitness.repository;

import android.content.Context;
import com.amazon.alexa.fitness.configuration.CacheProviderConfiguration;
import com.amazon.alexa.fitness.configuration.CacheProviderConfigurationProvider;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.dee.app.cachemanager.DefaultObjectCache;
import com.dee.app.cachemanager.DiskLruByteCache;
import com.dee.app.cachemanager.MarshmallowPlusAESEncryptor;
import com.dee.app.cachemanager.TwoTierLruMemoryCacheImpl;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import rx.schedulers.Schedulers;
/* compiled from: CacheProviderImpl.kt */
@Singleton
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\"\u0010\u0007\u001a\u0010\u0012\f\u0012\n \n*\u0004\u0018\u00010\t0\t0\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\u00020\u00108BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\"\u0010\u0013\u001a\u0010\u0012\f\u0012\n \n*\u0004\u0018\u00010\t0\t0\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\fR\"\u0010\u0015\u001a\u0010\u0012\f\u0012\n \n*\u0004\u0018\u00010\t0\t0\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\fR\u000e\u0010\u0017\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0019\u001a\u0010\u0012\f\u0012\n \n*\u0004\u0018\u00010\t0\t0\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\fR\u000e\u0010\u001b\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u001c\u001a\u0010\u0012\f\u0012\n \n*\u0004\u0018\u00010\t0\t0\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\fR\u000e\u0010\u001e\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/amazon/alexa/fitness/repository/CacheProviderImpl;", "Lcom/amazon/alexa/fitness/repository/CacheProvider;", "applicationContext", "Landroid/content/Context;", "cacheProviderConfigurationProvider", "Lcom/amazon/alexa/fitness/configuration/CacheProviderConfigurationProvider;", "(Landroid/content/Context;Lcom/amazon/alexa/fitness/configuration/CacheProviderConfigurationProvider;)V", "accessoryFitnessSessionCache", "Lcom/dee/app/cachemanager/DefaultObjectCache;", "", "kotlin.jvm.PlatformType", "getAccessoryFitnessSessionCache", "()Lcom/dee/app/cachemanager/DefaultObjectCache;", "accessoryFitnessSessionCacheExecutor", "Ljava/util/concurrent/ScheduledThreadPoolExecutor;", PCCConstants.PHONE_CALL_CONTROLLER_CONFIGURATION_KEY, "Lcom/amazon/alexa/fitness/configuration/CacheProviderConfiguration;", "getConfiguration", "()Lcom/amazon/alexa/fitness/configuration/CacheProviderConfiguration;", "recoveryCache", "getRecoveryCache", "sampleCache", "getSampleCache", "sampleCacheExecutor", "sessionRecoveryCacheExecutor", "sessionSummaryCache", "getSessionSummaryCache", "sessionSummaryCacheExecutor", "userProfileCache", "getUserProfileCache", "userProfileCacheExecutor", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class CacheProviderImpl implements CacheProvider {
    @NotNull
    private final DefaultObjectCache<byte[]> accessoryFitnessSessionCache;
    private final ScheduledThreadPoolExecutor accessoryFitnessSessionCacheExecutor;
    private final CacheProviderConfigurationProvider cacheProviderConfigurationProvider;
    @NotNull
    private final DefaultObjectCache<byte[]> recoveryCache;
    @NotNull
    private final DefaultObjectCache<byte[]> sampleCache;
    private final ScheduledThreadPoolExecutor sampleCacheExecutor;
    private final ScheduledThreadPoolExecutor sessionRecoveryCacheExecutor;
    @NotNull
    private final DefaultObjectCache<byte[]> sessionSummaryCache;
    private final ScheduledThreadPoolExecutor sessionSummaryCacheExecutor;
    @NotNull
    private final DefaultObjectCache<byte[]> userProfileCache;
    private final ScheduledThreadPoolExecutor userProfileCacheExecutor;

    @Inject
    public CacheProviderImpl(@NotNull Context applicationContext, @NotNull CacheProviderConfigurationProvider cacheProviderConfigurationProvider) {
        Intrinsics.checkParameterIsNotNull(applicationContext, "applicationContext");
        Intrinsics.checkParameterIsNotNull(cacheProviderConfigurationProvider, "cacheProviderConfigurationProvider");
        this.cacheProviderConfigurationProvider = cacheProviderConfigurationProvider;
        this.accessoryFitnessSessionCacheExecutor = new ScheduledThreadPoolExecutor(1);
        this.sampleCacheExecutor = new ScheduledThreadPoolExecutor(1);
        this.sessionSummaryCacheExecutor = new ScheduledThreadPoolExecutor(1);
        this.sessionRecoveryCacheExecutor = new ScheduledThreadPoolExecutor(1);
        this.userProfileCacheExecutor = new ScheduledThreadPoolExecutor(1);
        this.accessoryFitnessSessionCache = new DefaultObjectCache<>(byte[].class, new TwoTierLruMemoryCacheImpl(getConfiguration().getAlexaFitnessSessionStorageSizeInMb()), new DiskLruByteCache(applicationContext, "AccessoryFitnessSession", -1, getConfiguration().getAlexaFitnessSessionStorageSizeInMb(), this.accessoryFitnessSessionCacheExecutor), new MarshmallowPlusAESEncryptor(applicationContext, "AccessoryFitnessSession"), this.accessoryFitnessSessionCacheExecutor, Schedulers.from(Executors.newFixedThreadPool(2, new ThreadFactoryBuilder().setNameFormat("accessory-fitness-session-cache-%d").build())));
        this.sessionSummaryCache = new DefaultObjectCache<>(byte[].class, new TwoTierLruMemoryCacheImpl(getConfiguration().getSessionSummaryStorageSizeInMb()), new DiskLruByteCache(applicationContext, "fitnessSession", -1, getConfiguration().getSessionSummaryStorageSizeInMb(), this.sessionSummaryCacheExecutor), new MarshmallowPlusAESEncryptor(applicationContext, "fitnessSession"), this.sessionSummaryCacheExecutor, Schedulers.from(Executors.newFixedThreadPool(2, new ThreadFactoryBuilder().setNameFormat("session-summary-cache-%d").build())));
        this.userProfileCache = new DefaultObjectCache<>(byte[].class, new TwoTierLruMemoryCacheImpl(getConfiguration().getUserProfileStorageSizeInMb()), new DiskLruByteCache(applicationContext, "userProfile", -1, getConfiguration().getUserProfileStorageSizeInMb(), this.userProfileCacheExecutor), new MarshmallowPlusAESEncryptor(applicationContext, "userProfile"), this.userProfileCacheExecutor, Schedulers.from(Executors.newFixedThreadPool(2, new ThreadFactoryBuilder().setNameFormat("user-profile-cache-%d").build())));
        this.recoveryCache = new DefaultObjectCache<>(byte[].class, new TwoTierLruMemoryCacheImpl(getConfiguration().getFitnessSessionEventStorageSizeInMb()), new DiskLruByteCache(applicationContext, "sessionRecovery", -1, getConfiguration().getFitnessSessionEventStorageSizeInMb(), this.sessionRecoveryCacheExecutor), new MarshmallowPlusAESEncryptor(applicationContext, "sessionRecovery"), this.sessionRecoveryCacheExecutor, Schedulers.from(Executors.newFixedThreadPool(2, new ThreadFactoryBuilder().setNameFormat("session-recovery-cache-%d").build())));
        this.sampleCache = new DefaultObjectCache<>(byte[].class, new TwoTierLruMemoryCacheImpl(getConfiguration().getFitnessSessionEventStorageSizeInMb()), new DiskLruByteCache(applicationContext, "samples", -1, getConfiguration().getFitnessSessionEventStorageSizeInMb(), this.sampleCacheExecutor), new MarshmallowPlusAESEncryptor(applicationContext, "samples"), this.sampleCacheExecutor, Schedulers.from(Executors.newFixedThreadPool(2, new ThreadFactoryBuilder().setNameFormat("sample-cache-%d").build())));
    }

    private final CacheProviderConfiguration getConfiguration() {
        return this.cacheProviderConfigurationProvider.provideCacheProviderConfiguration();
    }

    @Override // com.amazon.alexa.fitness.repository.CacheProvider
    @NotNull
    public DefaultObjectCache<byte[]> getAccessoryFitnessSessionCache() {
        return this.accessoryFitnessSessionCache;
    }

    @Override // com.amazon.alexa.fitness.repository.CacheProvider
    @NotNull
    public DefaultObjectCache<byte[]> getRecoveryCache() {
        return this.recoveryCache;
    }

    @Override // com.amazon.alexa.fitness.repository.CacheProvider
    @NotNull
    public DefaultObjectCache<byte[]> getSampleCache() {
        return this.sampleCache;
    }

    @Override // com.amazon.alexa.fitness.repository.CacheProvider
    @NotNull
    public DefaultObjectCache<byte[]> getSessionSummaryCache() {
        return this.sessionSummaryCache;
    }

    @Override // com.amazon.alexa.fitness.repository.CacheProvider
    @NotNull
    public DefaultObjectCache<byte[]> getUserProfileCache() {
        return this.userProfileCache;
    }
}
