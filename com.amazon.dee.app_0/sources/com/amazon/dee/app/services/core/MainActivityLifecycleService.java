package com.amazon.dee.app.services.core;

import androidx.annotation.NonNull;
import com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver;
import com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserverRegistrar;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executor;
/* loaded from: classes12.dex */
public class MainActivityLifecycleService implements MainActivityLifecycleObserverRegistrar {
    private final Executor callbacksExecutor;
    private final Set<MainActivityLifecycleObserver> lifecycleObserverSet;

    public MainActivityLifecycleService(@NonNull Executor executor) {
        Objects.requireNonNull(executor, "Provided executor must be non-null.");
        this.lifecycleObserverSet = new CopyOnWriteArraySet();
        this.callbacksExecutor = executor;
    }

    @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserverRegistrar
    public void addObserver(@NonNull MainActivityLifecycleObserver mainActivityLifecycleObserver) {
        Objects.requireNonNull(mainActivityLifecycleObserver, "Observer may not be null!");
        this.lifecycleObserverSet.add(mainActivityLifecycleObserver);
    }

    public void notifyOnCreated() {
        for (final MainActivityLifecycleObserver mainActivityLifecycleObserver : this.lifecycleObserverSet) {
            this.callbacksExecutor.execute(new Runnable() { // from class: com.amazon.dee.app.services.core.-$$Lambda$MainActivityLifecycleService$f_6d6IWUQ4c7AyI0v8WIlQcDss4
                @Override // java.lang.Runnable
                public final void run() {
                    MainActivityLifecycleObserver.this.onActivityCreated();
                }
            });
        }
    }

    public void notifyOnDestroy() {
        for (final MainActivityLifecycleObserver mainActivityLifecycleObserver : this.lifecycleObserverSet) {
            this.callbacksExecutor.execute(new Runnable() { // from class: com.amazon.dee.app.services.core.-$$Lambda$MainActivityLifecycleService$Upmua-pCvHSnKAXFMlYzKUorz2U
                @Override // java.lang.Runnable
                public final void run() {
                    MainActivityLifecycleObserver.this.onActivityDestroy();
                }
            });
        }
    }

    public void notifyOnPause() {
        for (final MainActivityLifecycleObserver mainActivityLifecycleObserver : this.lifecycleObserverSet) {
            this.callbacksExecutor.execute(new Runnable() { // from class: com.amazon.dee.app.services.core.-$$Lambda$MainActivityLifecycleService$V4hiE8H6NIbx3Fvdt3x02RxQe64
                @Override // java.lang.Runnable
                public final void run() {
                    MainActivityLifecycleObserver.this.onActivityPause();
                }
            });
        }
    }

    public void notifyOnResume() {
        for (final MainActivityLifecycleObserver mainActivityLifecycleObserver : this.lifecycleObserverSet) {
            this.callbacksExecutor.execute(new Runnable() { // from class: com.amazon.dee.app.services.core.-$$Lambda$MainActivityLifecycleService$A9MgazpY1xduUUDPGHkCO6A2xCs
                @Override // java.lang.Runnable
                public final void run() {
                    MainActivityLifecycleObserver.this.onActivityResume();
                }
            });
        }
    }

    public void notifyOnStart() {
        for (final MainActivityLifecycleObserver mainActivityLifecycleObserver : this.lifecycleObserverSet) {
            this.callbacksExecutor.execute(new Runnable() { // from class: com.amazon.dee.app.services.core.-$$Lambda$MainActivityLifecycleService$jSxEftkB0ZFeIKKCR4I4U1hrIGo
                @Override // java.lang.Runnable
                public final void run() {
                    MainActivityLifecycleObserver.this.onActivityStart();
                }
            });
        }
    }

    public void notifyOnStop() {
        for (final MainActivityLifecycleObserver mainActivityLifecycleObserver : this.lifecycleObserverSet) {
            this.callbacksExecutor.execute(new Runnable() { // from class: com.amazon.dee.app.services.core.-$$Lambda$MainActivityLifecycleService$EFa9FM96dxRJgtMPZCY7rcQUNFI
                @Override // java.lang.Runnable
                public final void run() {
                    MainActivityLifecycleObserver.this.onActivityStop();
                }
            });
        }
    }

    @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserverRegistrar
    public void removeObserver(@NonNull MainActivityLifecycleObserver mainActivityLifecycleObserver) {
        Objects.requireNonNull(mainActivityLifecycleObserver, "Observer may not be null!");
        this.lifecycleObserverSet.remove(mainActivityLifecycleObserver);
    }
}
