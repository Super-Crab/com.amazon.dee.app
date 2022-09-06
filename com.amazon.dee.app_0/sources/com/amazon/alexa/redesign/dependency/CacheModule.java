package com.amazon.alexa.redesign.dependency;

import android.content.Context;
import android.os.Build;
import androidx.annotation.NonNull;
import com.amazon.alexa.redesign.cache.HomeCacheService;
import com.dee.app.cachemanager.AppDataCacheEntry;
import com.dee.app.cachemanager.Cache;
import com.dee.app.cachemanager.DefaultObjectCache;
import com.dee.app.cachemanager.DiskLruByteCache;
import com.dee.app.cachemanager.Encryptor;
import com.dee.app.cachemanager.MarshmallowPlusAESEncryptor;
import com.dee.app.cachemanager.TwoTierLruMemoryCacheImpl;
import com.dee.app.metrics.CacheMetricsObserver;
import com.dee.app.metrics.MetricsServiceV2;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.inject.Singleton;
import rx.Scheduler;
import rx.schedulers.Schedulers;
@Module
/* loaded from: classes10.dex */
public class CacheModule {
    public static final String HOME_CACHE = "HomeCache";
    public static final String HOME_DISK_IO = "HomeDiskIO";

    @NonNull
    private static Encryptor getEncryptor(Context context) {
        int i = Build.VERSION.SDK_INT;
        return new MarshmallowPlusAESEncryptor(context, HOME_CACHE);
    }

    @Provides
    @Singleton
    public Cache<AppDataCacheEntry> provideHomeCache(Context context, MetricsServiceV2 metricsServiceV2, ExecutorService executorService, Scheduler scheduler) {
        DefaultObjectCache defaultObjectCache = new DefaultObjectCache(AppDataCacheEntry.class, new TwoTierLruMemoryCacheImpl(1), new DiskLruByteCache(context, HOME_CACHE, 1, 1, executorService), getEncryptor(context), executorService, scheduler);
        defaultObjectCache.getCacheEvents().observeOn(Schedulers.io()).subscribe(new CacheMetricsObserver(metricsServiceV2));
        return defaultObjectCache;
    }

    @Provides
    @Singleton
    public HomeCacheService provideHomeCacheService(Cache<AppDataCacheEntry> cache) {
        return new HomeCacheService(cache);
    }

    @Provides
    @Singleton
    public Scheduler provideHomeDiskScheduler() {
        return Schedulers.from(Executors.newFixedThreadPool(2, new ThreadFactoryBuilder().setNameFormat(HOME_DISK_IO).build()));
    }
}
