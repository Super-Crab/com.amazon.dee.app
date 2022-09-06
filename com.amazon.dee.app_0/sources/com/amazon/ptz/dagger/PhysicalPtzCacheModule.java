package com.amazon.ptz.dagger;

import android.os.Handler;
import com.amazon.alexa.directive.AlexaDirective;
import com.amazon.ptz.physical.communication.PhysicalPtzCommandCache;
import com.amazon.ptz.util.LogTag;
import com.amazon.ptz.util.Qualifiers;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import org.greenrobot.eventbus.EventBus;
@Module
/* loaded from: classes13.dex */
public class PhysicalPtzCacheModule {
    private static final int CACHE_CONCURRENCY_LEVEL = 1;
    public static final int CACHE_TIMEOUT_SECONDS = 5;
    private static final int MAX_PENDING_COMMANDS = 5;
    private static final String TAG = LogTag.forClass(PhysicalPtzCacheModule.class);
    private static final TimeUnit CACHE_TIMEOUT_UNIT = TimeUnit.SECONDS;

    @Provides
    @Singleton
    public PhysicalPtzCommandCache providePhysicalPtzCommandCache(Cache<String, AlexaDirective> cache, @Qualifiers.UiThread Handler handler, EventBus eventBus) {
        return new PhysicalPtzCommandCache(cache, handler, eventBus);
    }

    @Provides
    @Singleton
    public Cache<String, AlexaDirective> providePtzDirectiveCache() {
        return CacheBuilder.newBuilder().maximumSize(5L).concurrencyLevel(1).expireAfterWrite(5L, CACHE_TIMEOUT_UNIT).build();
    }
}
