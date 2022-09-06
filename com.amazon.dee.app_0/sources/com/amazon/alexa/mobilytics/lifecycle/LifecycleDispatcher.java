package com.amazon.alexa.mobilytics.lifecycle;

import android.app.Application;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import com.amazon.alexa.mobilytics.lifecycle.Lifecycle;
import com.amazon.alexa.mobilytics.util.Log;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.Observable;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes9.dex */
final class LifecycleDispatcher {
    private static final String TAG = Log.tag(LifecycleDispatcher.class);
    private static final AtomicBoolean INITIALIZED = new AtomicBoolean(false);
    private static final LifecycleCallbackListener callbackListener = new LifecycleCallbackListener();

    private LifecycleDispatcher() {
    }

    public static void initialize(@NonNull Context context) {
        if (!INITIALIZED.getAndSet(true)) {
            Log.i(TAG, "Initializing ActivityLifecycleCallback listener.");
            ((Application) context.getApplicationContext()).registerActivityLifecycleCallbacks(callbackListener);
        }
    }

    public static Observable<Lifecycle.Event> onEvent() {
        return callbackListener.onEvent();
    }
}
