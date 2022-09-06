package com.amazon.alexa.eventbus.core;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.component.api.ServiceLifecycle;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.Subscriber;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes7.dex */
public class LocalDispatcher implements Dispatcher, ServiceLifecycle {
    @VisibleForTesting
    private static final int INTAKE_QUEUE_SIZE = 10000;
    private static final int INTAKE_THREAD_KEEPLIVE_TIME_SECONDS = 10;
    private static final int SHUTDOWN_GRACE_TIME_SECONDS = 5;
    private ExecutorService callbackExecutorService;
    private ExecutorService intakeExecutorService;
    private BlockingQueue<Runnable> intakeQueue;
    private Registry registry;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LocalDispatcher(@NonNull Registry registry) {
        this.registry = registry;
    }

    @Override // com.amazon.alexa.eventbus.core.Dispatcher
    public synchronized void dispatch(@NonNull final Message message) {
        if (this.intakeExecutorService != null && !this.intakeExecutorService.isShutdown()) {
            this.intakeExecutorService.execute(new Runnable() { // from class: com.amazon.alexa.eventbus.core.-$$Lambda$LocalDispatcher$J9CsECQmyw32uoPiUh6LGPxMv9M
                @Override // java.lang.Runnable
                public final void run() {
                    LocalDispatcher.this.lambda$dispatch$1$LocalDispatcher(message);
                }
            });
        } else {
            throw new RuntimeException("LocalDispatcher service not running");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void flush() {
        this.intakeQueue.drainTo(new ArrayList(10000));
    }

    public /* synthetic */ void lambda$dispatch$1$LocalDispatcher(final Message message) {
        synchronized (this) {
            if (this.callbackExecutorService != null && !this.callbackExecutorService.isShutdown()) {
                for (final Subscriber subscriber : this.registry.getSubscribersForMessage(message)) {
                    try {
                        this.callbackExecutorService.execute(new Runnable() { // from class: com.amazon.alexa.eventbus.core.-$$Lambda$LocalDispatcher$YHb91WwFpwGtnhjXGRCjIS0Okyw
                            @Override // java.lang.Runnable
                            public final void run() {
                                Subscriber.this.onMessageReceived(message);
                            }
                        });
                    } catch (Exception e) {
                        Thread currentThread = Thread.currentThread();
                        currentThread.getUncaughtExceptionHandler().uncaughtException(currentThread, e);
                    }
                }
            } else {
                throw new RuntimeException("LocalDispatcher service not running");
            }
        }
    }

    public /* synthetic */ void lambda$stop$2$LocalDispatcher() {
        synchronized (this) {
            try {
                if (!this.intakeExecutorService.awaitTermination(5L, TimeUnit.SECONDS)) {
                    this.intakeExecutorService.shutdownNow();
                }
                if (!this.callbackExecutorService.awaitTermination(5L, TimeUnit.SECONDS)) {
                    this.callbackExecutorService.shutdownNow();
                    Thread.currentThread().interrupt();
                }
            } catch (InterruptedException unused) {
                this.intakeExecutorService.shutdownNow();
                this.callbackExecutorService.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override // com.amazon.alexa.component.api.ServiceLifecycle
    public synchronized void start() {
        this.intakeQueue = new ArrayBlockingQueue(10000);
        this.intakeExecutorService = new ThreadPoolExecutor(1, 1, 10L, TimeUnit.SECONDS, this.intakeQueue);
        this.callbackExecutorService = Executors.newSingleThreadExecutor();
    }

    @Override // com.amazon.alexa.component.api.ServiceLifecycle
    public synchronized void stop() {
        this.intakeExecutorService.shutdown();
        this.callbackExecutorService.shutdown();
        Executors.newSingleThreadExecutor().execute(new Runnable() { // from class: com.amazon.alexa.eventbus.core.-$$Lambda$LocalDispatcher$oNJfvD5Rv4-ZbgHH9EbcMAfYg6s
            @Override // java.lang.Runnable
            public final void run() {
                LocalDispatcher.this.lambda$stop$2$LocalDispatcher();
            }
        });
    }
}
