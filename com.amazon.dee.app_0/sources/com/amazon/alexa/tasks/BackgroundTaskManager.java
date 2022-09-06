package com.amazon.alexa.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.api.MultiFilterSubscriber;
import com.amazon.alexa.protocols.service.api.ComponentGetter;
import com.amazon.alexa.tasks.BackgroundTaskManager;
import com.amazon.alexa.tasks.api.TaskManager;
import com.amazon.alexa.tasks.api.TaskType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.util.concurrent.ForwardingExecutorService;
import com.google.common.util.concurrent.SettableFuture;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class BackgroundTaskManager implements TaskManager {
    @VisibleForTesting
    static final long STARTUP_TASK_TIMEOUT_MS = 12000;
    private static final String TAG = "BackgroundTaskManager";
    private volatile ScheduledFuture<?> afterUiTaskStartTimer;
    private final Provider<CrashReporter> crashReporter;
    private final Provider<EventBus> eventBus;
    private final ExecutorService afterUiTaskSerialExecutor = getEvanescentSerialExecutor("After-UI");
    private final ScheduledExecutorService scheduledTaskExecutor = Executors.newScheduledThreadPool(0);
    private final ExecutorService defaultExecutor = new LoggingDefaultExecutorService();
    private final SettableFuture<Boolean> afterUiTaskStartSwitch = SettableFuture.create();
    private final int reportExceptionsOneIn = 4096;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public class LoggingDefaultExecutorService extends ForwardingExecutorService {
        private final ExecutorService defaultExecutorService;

        private LoggingDefaultExecutorService() {
            this.defaultExecutorService = (ExecutorService) AsyncTask.THREAD_POOL_EXECUTOR;
        }

        private boolean isChecked(Throwable th) {
            return (th instanceof Exception) && !(th instanceof RuntimeException);
        }

        public /* synthetic */ Object lambda$submit$0$BackgroundTaskManager$LoggingDefaultExecutorService(Callable callable) throws Exception {
            Thread currentThread = Thread.currentThread();
            String name = currentThread.getName();
            try {
                currentThread.setName(callable.getClass().getSimpleName());
                return callable.call();
            } finally {
            }
        }

        @Override // com.google.common.util.concurrent.ForwardingExecutorService, java.util.concurrent.ExecutorService
        /* renamed from: submit */
        public <T> Future<T> mo8278submit(@NonNull final Callable<T> callable) {
            return super.mo8278submit(new Callable() { // from class: com.amazon.alexa.tasks.-$$Lambda$BackgroundTaskManager$LoggingDefaultExecutorService$veoXbnWfM6aJBloP0V1ZwyUAvVM
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return BackgroundTaskManager.LoggingDefaultExecutorService.this.lambda$submit$0$BackgroundTaskManager$LoggingDefaultExecutorService(callable);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.util.concurrent.ForwardingExecutorService, com.google.common.collect.ForwardingObject
        /* renamed from: delegate  reason: collision with other method in class */
        public ExecutorService mo8280delegate() {
            return this.defaultExecutorService;
        }

        @Override // com.google.common.util.concurrent.ForwardingExecutorService, java.util.concurrent.ExecutorService
        /* renamed from: submit */
        public Future<?> mo8276submit(@NonNull Runnable runnable) {
            return super.mo8276submit(BackgroundTaskManager.this.instrumentRunnable(runnable));
        }

        @Override // com.google.common.util.concurrent.ForwardingExecutorService, java.util.concurrent.ExecutorService
        /* renamed from: submit */
        public <T> Future<T> mo8277submit(@NonNull Runnable runnable, @NonNull T t) {
            return super.mo8277submit(BackgroundTaskManager.this.instrumentRunnable(runnable), t);
        }
    }

    public BackgroundTaskManager(ComponentGetter componentGetter, Context context) {
        this.eventBus = componentGetter.get(EventBus.class);
        this.crashReporter = componentGetter.get(CrashReporter.class);
        observeUiLifecycle();
    }

    private ExecutorService getEvanescentSerialExecutor(String str) {
        return new ThreadPoolExecutor(0, 1, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new NamedThreadFactory(str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Runnable instrumentRunnable(@NonNull final Runnable runnable) {
        return new Runnable() { // from class: com.amazon.alexa.tasks.-$$Lambda$BackgroundTaskManager$RfQe9lh9pJwJLjVBgKOLFIPavzY
            @Override // java.lang.Runnable
            public final void run() {
                BackgroundTaskManager.this.lambda$instrumentRunnable$4$BackgroundTaskManager(runnable);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logAndReportException(Throwable th, Class<?> cls) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("In ");
        outline107.append(cls.getCanonicalName());
        Log.e(str, outline107.toString(), th);
        this.crashReporter.mo10268get().reportNonFatal(th, this.reportExceptionsOneIn);
    }

    private void observeUiLifecycle() {
        MultiFilterSubscriber subscriber = this.eventBus.mo10268get().getSubscriber();
        subscriber.subscribeFilter($$Lambda$BackgroundTaskManager$9lHySmRKQgl45Pg1r80OwC0DqTo.INSTANCE, new MessageHandler() { // from class: com.amazon.alexa.tasks.-$$Lambda$BackgroundTaskManager$K2R5aaRF02yFu_cXY3LrUlIAWb0
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                BackgroundTaskManager.this.lambda$observeUiLifecycle$1$BackgroundTaskManager(message);
            }
        });
        subscriber.subscribeFilter($$Lambda$BackgroundTaskManager$q6TdlrvWJEEbmttNMycLZXlOX1U.INSTANCE, new MessageHandler() { // from class: com.amazon.alexa.tasks.-$$Lambda$BackgroundTaskManager$tGfkOGXcrAJT-6aSYzRdSnSt1b8
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                BackgroundTaskManager.this.lambda$observeUiLifecycle$3$BackgroundTaskManager(message);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startAfterUiTasks() {
        Log.i(TAG, "Starting after-UI tasks, if not yet started...");
        this.afterUiTaskStartSwitch.set(Boolean.TRUE);
    }

    private void startAfterUiTimeoutTimer() {
        Log.i(TAG, "Starting after-UI tasks timer...");
        if (this.afterUiTaskStartTimer != null) {
            return;
        }
        this.afterUiTaskStartTimer = this.scheduledTaskExecutor.schedule(new Runnable() { // from class: com.amazon.alexa.tasks.-$$Lambda$BackgroundTaskManager$9teh6Uc8kbG19ejGgQhb2HF3R88
            @Override // java.lang.Runnable
            public final void run() {
                BackgroundTaskManager.this.startAfterUiTasks();
            }
        }, 12000L, TimeUnit.MILLISECONDS);
    }

    @Override // com.amazon.alexa.tasks.api.TaskManager
    public void addAfterUiTask(Runnable runnable) {
        this.afterUiTaskStartSwitch.addListener(instrumentRunnable(runnable), this.afterUiTaskSerialExecutor);
    }

    @Override // com.amazon.alexa.tasks.api.TaskManager
    public ExecutorService getExecutor(@TaskType int i) {
        return this.defaultExecutor;
    }

    public /* synthetic */ void lambda$instrumentRunnable$4$BackgroundTaskManager(Runnable runnable) {
        Thread currentThread = Thread.currentThread();
        String name = currentThread.getName();
        try {
            currentThread.setName(runnable.getClass().getSimpleName());
            runnable.run();
        } finally {
        }
    }

    public /* synthetic */ void lambda$observeUiLifecycle$1$BackgroundTaskManager(Message message) {
        startAfterUiTimeoutTimer();
    }

    public /* synthetic */ void lambda$observeUiLifecycle$3$BackgroundTaskManager(Message message) {
        startAfterUiTasks();
    }
}
