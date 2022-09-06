package com.amazon.alexa.mobilytics.session;

import androidx.annotation.Nullable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import rx.Observable;
/* loaded from: classes9.dex */
public interface MobilyticsSession {

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes9.dex */
    public @interface State {
        public static final int ERROR = 4;
        public static final int PAUSED = 2;
        public static final int RESUMED = 3;
        public static final int STARTED = 1;
        public static final int STOPPED = 0;
    }

    long elapsedTime();

    @Nullable
    String id();

    Observable<String> onIdChanged();

    long startTime();

    int state();

    long stopTime();
}
