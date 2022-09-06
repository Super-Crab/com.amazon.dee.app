package com.amazon.alexa.accessorykit;

import android.content.Context;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessorykit.finishsetup.presenters.FASCardPresenter;
import com.amazon.alexa.accessorykit.internal.RNEventEmitter;
import com.amazon.alexa.protocols.lifecycle.ApplicationLifecycleObserver;
/* loaded from: classes6.dex */
public class ApplicationLifecycleObserverForFASCardPresenter implements ApplicationLifecycleObserver {
    private static final String TAG = "ApplicationLifecycleObserverForFASCardPresenter:";
    private final FASCardPresenter cardPresenter;
    private final Context context;

    public ApplicationLifecycleObserverForFASCardPresenter(Context context) {
        Preconditions.notNull(context, "context");
        this.context = context;
        this.cardPresenter = new FASCardPresenter(RNEventEmitter.getInstance(), new NativeContainerProvider(), context);
    }

    @Override // com.amazon.alexa.protocols.lifecycle.ApplicationLifecycleObserver
    public void onStart() {
        Logger.d("%s onStart", TAG);
        this.cardPresenter.registerFASCardEventReceiver();
    }

    @Override // com.amazon.alexa.protocols.lifecycle.ApplicationLifecycleObserver
    public void onStop() {
        Logger.d("%s onStop", TAG);
        this.cardPresenter.unregisterFASCardEventReceiver();
    }
}
