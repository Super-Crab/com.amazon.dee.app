package com.amazon.dee.app.ui.util;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.api.MultiFilterSubscriber;
import com.amazon.alexa.eventbus.message.EventTypeMessageFilter;
import com.amazon.alexa.identity.api.IdentityEvent;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.tasks.api.TaskManager;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.util.Utils;
import com.dee.app.cachemanager.Cache;
import com.dee.app.cachemanager.CacheMetadata;
/* loaded from: classes12.dex */
public class CacheClearOperations {
    private static final String LOG_TAG = Log.tag(CacheClearOperations.class);
    private static final String TAG = Utils.safeTag(CacheClearOperations.class.getSimpleName());
    private final Cache[] caches;
    private final EventBus eventBus;
    private final MultiFilterSubscriber eventBusSubscriber;
    private final IdentityService identityService;
    private final TaskManager taskManager;
    private MultiFilterSubscriber.FilterUuid userChangedUuid;

    public CacheClearOperations(TaskManager taskManager, IdentityService identityService, EventBus eventBus, Cache... cacheArr) {
        this.taskManager = taskManager;
        this.identityService = identityService;
        this.eventBus = eventBus;
        this.eventBusSubscriber = eventBus.getSubscriber();
        this.caches = cacheArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$clearCache$3(Cache[] cacheArr) {
        for (Cache cache : cacheArr) {
            try {
                cache.clear(CacheMetadata.EMPTY).subscribe($$Lambda$CacheClearOperations$wTC28_OKB2B21lUaeuUa0llGz4.INSTANCE, $$Lambda$CacheClearOperations$3lpAgljFb82b7sbDEpyebzWkIzk.INSTANCE);
            } catch (Exception e) {
                Log.e(LOG_TAG, "Failed to clear cache on logout.", e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$null$1(Object obj) {
    }

    public void clearCache(final Cache[] cacheArr) {
        this.taskManager.getExecutor(1).execute(new Runnable() { // from class: com.amazon.dee.app.ui.util.-$$Lambda$CacheClearOperations$Bg8FGEgTUN4hU4EWYEMUNKV1KTg
            @Override // java.lang.Runnable
            public final void run() {
                CacheClearOperations.lambda$clearCache$3(cacheArr);
            }
        });
    }

    public /* synthetic */ void lambda$start$0$CacheClearOperations(Message message) {
        userChanged(this.identityService.getUser(TAG));
    }

    public void start() {
        if (this.userChangedUuid == null) {
            this.userChangedUuid = this.eventBusSubscriber.subscribeFilter(new EventTypeMessageFilter(IdentityEvent.IDENTITY_CHANGED), new MessageHandler() { // from class: com.amazon.dee.app.ui.util.-$$Lambda$CacheClearOperations$os3F4PqP7fZaO-u84t5RCP2araE
                @Override // com.amazon.alexa.eventbus.api.MessageHandler
                public final void handle(Message message) {
                    CacheClearOperations.this.lambda$start$0$CacheClearOperations(message);
                }
            });
        }
    }

    public void stop() {
        MultiFilterSubscriber.FilterUuid filterUuid = this.userChangedUuid;
        if (filterUuid != null) {
            this.eventBusSubscriber.unsubscribeFilter(filterUuid);
            this.userChangedUuid = null;
        }
    }

    @VisibleForTesting
    void userChanged(UserIdentity userIdentity) {
        if (userIdentity == null) {
            clearCache(this.caches);
        }
    }
}
