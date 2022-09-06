package com.amazon.ptz.physical.communication;

import android.os.Handler;
import com.amazon.alexa.directive.AlexaDirective;
import com.amazon.ptz.physical.events.PhysicalPtzEvent;
import com.amazon.ptz.util.LogTag;
import com.amazon.ptz.util.Qualifiers;
import com.google.common.cache.Cache;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import lombok.Generated;
import org.greenrobot.eventbus.EventBus;
/* loaded from: classes13.dex */
public class PhysicalPtzCommandCache {
    private static final String TAG = LogTag.forClass(PhysicalPtzCommandCache.class);
    @Nonnull
    private EventBus eventBus;
    @Nonnull
    @Qualifiers.UiThread
    private Handler handler;
    @Nonnull
    private Cache<String, AlexaDirective> pendingPtzCommandsCache;

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public PhysicalPtzCommandCache(@Nonnull Cache<String, AlexaDirective> cache, @Nonnull Handler handler, @Nonnull EventBus eventBus) {
        if (cache != null) {
            if (handler == null) {
                throw new IllegalArgumentException("handler is null");
            }
            if (eventBus == null) {
                throw new IllegalArgumentException("eventBus is null");
            }
            this.pendingPtzCommandsCache = cache;
            this.handler = handler;
            this.eventBus = eventBus;
            return;
        }
        throw new IllegalArgumentException("pendingPtzCommandsCache is null");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cleanUpCache() {
        this.pendingPtzCommandsCache.cleanUp();
        if (this.pendingPtzCommandsCache.size() == 0) {
            this.eventBus.post(PhysicalPtzEvent.MOTION_ENDED);
        } else {
            String.format("Cache still contain [%s] pending commands. Ignoring stop animation.", Long.valueOf(this.pendingPtzCommandsCache.size()));
        }
    }

    public void onNewCommandSent(String str, AlexaDirective alexaDirective) {
        String.format("Adding correlationToken[%s] to cache", str);
        this.pendingPtzCommandsCache.put(str, alexaDirective);
        this.eventBus.post(PhysicalPtzEvent.DIRECTIVE_SENT);
        this.handler.postDelayed(new Runnable() { // from class: com.amazon.ptz.physical.communication.PhysicalPtzCommandCache.1
            @Override // java.lang.Runnable
            public void run() {
                PhysicalPtzCommandCache.this.cleanUpCache();
            }
        }, TimeUnit.SECONDS.toMillis(5L));
    }

    public void onResponseReceived(String str) {
        String.format("Removing correlationToken[%s] from cache", str);
        if (this.pendingPtzCommandsCache.getIfPresent(str) == null) {
            String.format("correlationToken[%s] not found in the cache.", str);
            return;
        }
        this.pendingPtzCommandsCache.invalidate(str);
        cleanUpCache();
    }
}
