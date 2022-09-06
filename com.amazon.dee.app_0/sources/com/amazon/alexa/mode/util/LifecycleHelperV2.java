package com.amazon.alexa.mode.util;

import android.util.Log;
import com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver;
import com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserverRegistrar;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;
/* loaded from: classes9.dex */
public class LifecycleHelperV2 implements MainActivityLifecycleObserver {
    private static final String TAG = LogTag.forClass(LifecycleHelperV2.class);
    private boolean background = true;
    PublishSubject<LifeCycleState> mLifecycleSubject = PublishSubject.create();

    /* loaded from: classes9.dex */
    public enum LifeCycleState {
        ActivityCreated,
        ActivityPause,
        ActivityResume,
        ActivityStop,
        ActivityStart,
        ActivityDestroy
    }

    private void startMainActivityLifecycleMonitor(MainActivityLifecycleObserverRegistrar mainActivityLifecycleObserverRegistrar) {
        mainActivityLifecycleObserverRegistrar.addObserver(this);
    }

    public Observable<LifeCycleState> getLifecycleState() {
        return this.mLifecycleSubject;
    }

    public void init() {
        MainActivityLifecycleObserverRegistrar mainActivityLifecycleObserverRegistrar = (MainActivityLifecycleObserverRegistrar) GeneratedOutlineSupport1.outline21(MainActivityLifecycleObserverRegistrar.class);
        if (mainActivityLifecycleObserverRegistrar != null) {
            startMainActivityLifecycleMonitor(mainActivityLifecycleObserverRegistrar);
        }
    }

    public boolean isBackground() {
        return this.background;
    }

    @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
    public void onActivityCreated() {
        this.mLifecycleSubject.onNext(LifeCycleState.ActivityCreated);
    }

    @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
    public void onActivityDestroy() {
        this.mLifecycleSubject.onNext(LifeCycleState.ActivityDestroy);
    }

    @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
    public void onActivityPause() {
        this.mLifecycleSubject.onNext(LifeCycleState.ActivityPause);
    }

    @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
    public void onActivityResume() {
        this.mLifecycleSubject.onNext(LifeCycleState.ActivityResume);
    }

    @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
    public void onActivityStart() {
        Log.i(TAG, "onActivityStart");
        this.background = false;
        this.mLifecycleSubject.onNext(LifeCycleState.ActivityStart);
    }

    @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
    public void onActivityStop() {
        this.background = true;
        this.mLifecycleSubject.onNext(LifeCycleState.ActivityStop);
    }

    public void unInitialize() {
        MainActivityLifecycleObserverRegistrar mainActivityLifecycleObserverRegistrar = (MainActivityLifecycleObserverRegistrar) GeneratedOutlineSupport1.outline21(MainActivityLifecycleObserverRegistrar.class);
        if (mainActivityLifecycleObserverRegistrar != null) {
            mainActivityLifecycleObserverRegistrar.removeObserver(this);
        }
    }
}
