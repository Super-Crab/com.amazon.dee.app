package com.amazon.alexa.mode.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.functions.Cancellable;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes9.dex */
public class ScreenDisplayHelper {
    private static final IntentFilter INTENT_FILTER = new IntentFilter();
    private static final String TAG = "ScreenDisplayHelper";
    private Context context;
    private final PowerManager powerManager;
    private BroadcastReceiver screenDisplayStateReceiver;
    private Handler mainThreadhandler = new Handler(Looper.getMainLooper());
    private AtomicBoolean isRegistered = new AtomicBoolean(false);

    static {
        INTENT_FILTER.addAction("android.intent.action.SCREEN_ON");
        INTENT_FILTER.addAction("android.intent.action.SCREEN_OFF");
    }

    public ScreenDisplayHelper(Context context, PowerManager powerManager) {
        this.context = context;
        this.powerManager = powerManager;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* renamed from: deregisterForDisplayStateUpdates */
    public void lambda$null$1$ScreenDisplayHelper() {
        if (this.isRegistered.compareAndSet(true, false)) {
            this.context.unregisterReceiver(this.screenDisplayStateReceiver);
        }
    }

    @VisibleForTesting
    BroadcastReceiver getScreenDisplayStateReceiver() {
        return this.screenDisplayStateReceiver;
    }

    public boolean isScreenOn() {
        return this.powerManager.isInteractive();
    }

    public /* synthetic */ void lambda$null$2$ScreenDisplayHelper() throws Throwable {
        this.mainThreadhandler.post(new Runnable() { // from class: com.amazon.alexa.mode.util.-$$Lambda$ScreenDisplayHelper$1HGt-lZKbd8etdsqqSckmAwXJ_Y
            @Override // java.lang.Runnable
            public final void run() {
                ScreenDisplayHelper.this.lambda$null$1$ScreenDisplayHelper();
            }
        });
    }

    public /* synthetic */ void lambda$subscribeToScreenOn$3$ScreenDisplayHelper(final ObservableEmitter observableEmitter) throws Throwable {
        this.screenDisplayStateReceiver = new BroadcastReceiver() { // from class: com.amazon.alexa.mode.util.ScreenDisplayHelper.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if ("android.intent.action.SCREEN_OFF".equals(action)) {
                    Log.i(ScreenDisplayHelper.TAG, "Screen is off");
                    observableEmitter.onNext(false);
                } else if (!"android.intent.action.SCREEN_ON".equals(action)) {
                } else {
                    Log.i(ScreenDisplayHelper.TAG, "Screen is on");
                    observableEmitter.onNext(true);
                }
            }
        };
        this.mainThreadhandler.post(new Runnable() { // from class: com.amazon.alexa.mode.util.-$$Lambda$ScreenDisplayHelper$L7I_4u5IIBBAX9iXnDFAuE7Mscg
            @Override // java.lang.Runnable
            public final void run() {
                ScreenDisplayHelper.this.lambda$null$0$ScreenDisplayHelper();
            }
        });
        observableEmitter.onNext(Boolean.valueOf(isScreenOn()));
        observableEmitter.setCancellable(new Cancellable() { // from class: com.amazon.alexa.mode.util.-$$Lambda$ScreenDisplayHelper$I-8b18YmximhloQTfcHATeHVdvg
            @Override // io.reactivex.rxjava3.functions.Cancellable
            public final void cancel() {
                ScreenDisplayHelper.this.lambda$null$2$ScreenDisplayHelper();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* renamed from: registerForDisplayStateUpdates */
    public void lambda$null$0$ScreenDisplayHelper() {
        if (this.isRegistered.compareAndSet(false, true)) {
            this.context.registerReceiver(this.screenDisplayStateReceiver, INTENT_FILTER);
        }
    }

    public Observable<Boolean> subscribeToScreenOn() {
        return Observable.create(new ObservableOnSubscribe() { // from class: com.amazon.alexa.mode.util.-$$Lambda$ScreenDisplayHelper$AgJKakRPlEyCnQZzk9E6mbOu4PA
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                ScreenDisplayHelper.this.lambda$subscribeToScreenOn$3$ScreenDisplayHelper(observableEmitter);
            }
        });
    }
}
