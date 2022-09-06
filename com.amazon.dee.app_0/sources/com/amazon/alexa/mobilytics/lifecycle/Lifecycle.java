package com.amazon.alexa.mobilytics.lifecycle;

import javax.inject.Singleton;
import rx.Observable;
@Singleton
/* loaded from: classes9.dex */
public class Lifecycle {

    /* loaded from: classes9.dex */
    public enum Event {
        ON_CREATE,
        ON_DESTROY,
        ON_START,
        ON_STOP,
        ON_RESUME,
        ON_PAUSE
    }

    public Observable<Event> onEvent() {
        return LifecycleDispatcher.onEvent();
    }
}
