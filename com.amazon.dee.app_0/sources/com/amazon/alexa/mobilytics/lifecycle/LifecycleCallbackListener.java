package com.amazon.alexa.mobilytics.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import androidx.annotation.RestrictTo;
import com.amazon.alexa.mobilytics.lifecycle.Lifecycle;
import com.amazon.alexa.mobilytics.util.Log;
import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes9.dex */
public class LifecycleCallbackListener implements Application.ActivityLifecycleCallbacks {
    private static final String TAG = Log.tag(LifecycleCallbackListener.class);
    private int createdCount = 0;
    private int startedCount = 0;
    private int resumedCount = 0;
    private boolean destroySent = true;
    private boolean stopSent = true;
    private boolean pauseSent = true;
    private SerializedSubject<Lifecycle.Event, Lifecycle.Event> subject = new SerializedSubject<>(PublishSubject.create());

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        this.createdCount++;
        if (this.createdCount != 1 || !this.destroySent) {
            return;
        }
        this.destroySent = false;
        this.subject.onNext(Lifecycle.Event.ON_CREATE);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
        this.createdCount--;
        if (this.createdCount == 0) {
            this.destroySent = true;
            this.subject.onNext(Lifecycle.Event.ON_DESTROY);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        this.resumedCount--;
        if (this.resumedCount == 0) {
            this.pauseSent = true;
            this.subject.onNext(Lifecycle.Event.ON_PAUSE);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        this.resumedCount++;
        if (this.resumedCount != 1 || !this.pauseSent) {
            return;
        }
        this.pauseSent = false;
        this.subject.onNext(Lifecycle.Event.ON_RESUME);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        this.startedCount++;
        if (this.startedCount != 1 || !this.stopSent) {
            return;
        }
        this.stopSent = false;
        this.subject.onNext(Lifecycle.Event.ON_START);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        this.startedCount--;
        if (this.startedCount == 0) {
            this.stopSent = true;
            this.subject.onNext(Lifecycle.Event.ON_STOP);
        }
    }

    public Observable<Lifecycle.Event> onEvent() {
        return this.subject.asObservable();
    }
}
