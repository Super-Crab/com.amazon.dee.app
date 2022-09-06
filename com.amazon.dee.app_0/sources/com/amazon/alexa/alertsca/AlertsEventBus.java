package com.amazon.alexa.alertsca;

import android.util.Log;
import com.amazon.alexa.alertsca.events.Event;
import com.amazon.alexa.utils.concurrent.ManagedExecutorFactory;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.ExecutorService;
import javax.inject.Inject;
import org.greenrobot.eventbus.EventBus;
/* loaded from: classes6.dex */
public class AlertsEventBus {
    private static String EXECUTOR_NAME = "alerts-event-bus";
    private static String TAG = "AlertsEventBus";
    protected final EventBus eventBus;
    private final ExecutorService executor = ManagedExecutorFactory.newSingleThreadExecutor(EXECUTOR_NAME, ManagedExecutorFactory.Group.EVENT_BUS);

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public static class PostAsyncRunnable implements Runnable {
        private final Object event;
        private final EventBus eventBus;

        public PostAsyncRunnable(EventBus eventBus, Event event) {
            this.eventBus = eventBus;
            this.event = event;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.eventBus.post(this.event);
        }
    }

    /* loaded from: classes6.dex */
    private static class PostStickyAsyncRunnable implements Runnable {
        private final Object event;
        private final EventBus eventBus;

        public PostStickyAsyncRunnable(EventBus eventBus, Event event) {
            this.eventBus = eventBus;
            this.event = event;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.eventBus.postSticky(this.event);
        }
    }

    @Inject
    public AlertsEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void post(Event event) {
        if (event != null) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Posting event: ");
            outline107.append(event.toString());
            outline107.toString();
            this.executor.submit(new PostAsyncRunnable(this.eventBus, event));
            return;
        }
        Log.w(TAG, "Event to be posted is null");
    }

    public void postSticky(Event event) {
        if (event != null) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Posting sticky event: ");
            outline107.append(event.toString());
            outline107.toString();
            this.executor.submit(new PostStickyAsyncRunnable(this.eventBus, event));
            return;
        }
        Log.w(TAG, "Event to be posted is null");
    }

    public void register(Object obj) {
        if (obj != null) {
            this.eventBus.register(obj);
        } else {
            Log.w(TAG, "Can't register a null subscriber");
        }
    }

    public void unregister(Object obj) {
        if (obj != null) {
            this.eventBus.unregister(obj);
        } else {
            Log.w(TAG, "Can't unregister a null subscriber");
        }
    }
}
