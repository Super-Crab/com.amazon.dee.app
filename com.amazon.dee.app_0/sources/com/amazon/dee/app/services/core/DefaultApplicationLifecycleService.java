package com.amazon.dee.app.services.core;

import androidx.annotation.NonNull;
import com.amazon.alexa.protocols.lifecycle.ApplicationLifecycleObserver;
import com.amazon.alexa.protocols.lifecycle.ApplicationLifecycleService;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executor;
/* loaded from: classes12.dex */
public final class DefaultApplicationLifecycleService implements ApplicationLifecycleService {
    private final Executor callbacksExecutor;
    private final Set<ApplicationLifecycleObserver> lifecycleObservers;

    public DefaultApplicationLifecycleService(@NonNull Executor executor) {
        Objects.requireNonNull(executor, "Must provide a non-null executor.");
        this.lifecycleObservers = new CopyOnWriteArraySet();
        this.callbacksExecutor = executor;
    }

    @Override // com.amazon.alexa.protocols.lifecycle.ApplicationLifecycleService
    public void addObserver(@NonNull ApplicationLifecycleObserver applicationLifecycleObserver) {
        Objects.requireNonNull(applicationLifecycleObserver, "Observer must be non-null.");
        this.lifecycleObservers.add(applicationLifecycleObserver);
    }

    public void notifyOnStart() {
        for (final ApplicationLifecycleObserver applicationLifecycleObserver : this.lifecycleObservers) {
            this.callbacksExecutor.execute(new Runnable() { // from class: com.amazon.dee.app.services.core.-$$Lambda$DefaultApplicationLifecycleService$E6R8lLwFGZrqIskljByFKzeh57A
                @Override // java.lang.Runnable
                public final void run() {
                    ApplicationLifecycleObserver.this.onStart();
                }
            });
        }
    }

    public void notifyOnStop() {
        for (final ApplicationLifecycleObserver applicationLifecycleObserver : this.lifecycleObservers) {
            this.callbacksExecutor.execute(new Runnable() { // from class: com.amazon.dee.app.services.core.-$$Lambda$DefaultApplicationLifecycleService$8hLYuQUWvzTkl463Fn4-EVtY5YI
                @Override // java.lang.Runnable
                public final void run() {
                    ApplicationLifecycleObserver.this.onStop();
                }
            });
        }
    }

    @Override // com.amazon.alexa.protocols.lifecycle.ApplicationLifecycleService
    public void removeObserver(@NonNull ApplicationLifecycleObserver applicationLifecycleObserver) {
        Objects.requireNonNull(applicationLifecycleObserver, "Observer must be non-null.");
        this.lifecycleObservers.remove(applicationLifecycleObserver);
    }
}
