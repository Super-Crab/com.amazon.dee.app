package com.amazon.alexa.accessory.avsclient.context;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.core.Emitter;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.functions.Cancellable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.subjects.PublishSubject;
/* loaded from: classes.dex */
public class PhoneSecurityStateSupplier implements SecurityStateSupplier {
    private static final int POLL_LOCK_STATE_INTERVAL = 30000;
    private static final String TAG = "PhoneSecurityStateSupplier: ";
    private boolean active;
    private final Context context;
    private boolean isLocked;
    private final KeyguardManager keyguardManager;
    private final IntentFilter securityIntentFilter = getSecurityIntentFilter();
    private final PublishSubject<Boolean> isLockedPollingSubject = PublishSubject.create();
    private final Handler handler = new Handler(Looper.myLooper());
    private final Object lock = new Object();
    private final Runnable pollLockStateRunnable = createPollLockStateRunnable();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public final class SecurityStateReceiver extends BroadcastReceiver {
        private final Emitter<Boolean> emitter;

        public SecurityStateReceiver(Emitter<Boolean> emitter) {
            this.emitter = emitter;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            boolean isDeviceLocked = PhoneSecurityStateSupplier.this.isDeviceLocked();
            Logger.d("%s determined current lock state triggered by action %s: isLocked=%b ", PhoneSecurityStateSupplier.TAG, intent.getAction(), Boolean.valueOf(isDeviceLocked));
            this.emitter.onNext(Boolean.valueOf(isDeviceLocked));
        }
    }

    public PhoneSecurityStateSupplier(Context context) {
        this.context = context;
        this.keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
    }

    private Runnable createPollLockStateRunnable() {
        return new Runnable() { // from class: com.amazon.alexa.accessory.avsclient.context.-$$Lambda$PhoneSecurityStateSupplier$Q1dr5sPvd8zBfPd65WjkV_NJe50
            @Override // java.lang.Runnable
            public final void run() {
                PhoneSecurityStateSupplier.this.lambda$createPollLockStateRunnable$3$PhoneSecurityStateSupplier();
            }
        };
    }

    private static IntentFilter getSecurityIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.USER_BACKGROUND");
        intentFilter.addAction("android.intent.action.USER_FOREGROUND");
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        return intentFilter;
    }

    private void pollLockStateWhileUnlocked() {
        if (this.isLocked) {
            Logger.d("%s pollLockStateWhileUnlocked called, but phone is locked, halting pollLockStateWhileUnlocked", TAG);
        } else {
            this.handler.postDelayed(this.pollLockStateRunnable, 30000L);
        }
    }

    @Override // com.amazon.alexa.accessory.avsclient.context.SecurityStateSupplier
    public void activate() {
        synchronized (this.lock) {
            if (this.active) {
                return;
            }
            Logger.d("%s activate", TAG);
            this.active = true;
        }
    }

    @Override // com.amazon.alexa.accessory.avsclient.context.SecurityStateSupplier
    public void deactivate() {
        synchronized (this.lock) {
            if (!this.active) {
                return;
            }
            Logger.d("%s deactivate", TAG);
            this.active = false;
        }
    }

    @Override // com.amazon.alexa.accessory.avsclient.context.SecurityStateSupplier
    public boolean isDeviceLocked() {
        return this.keyguardManager.isDeviceLocked();
    }

    @Override // com.amazon.alexa.accessory.avsclient.context.SecurityStateSupplier
    public boolean isDeviceSecure() {
        return this.keyguardManager.isKeyguardSecure();
    }

    public /* synthetic */ void lambda$createPollLockStateRunnable$3$PhoneSecurityStateSupplier() {
        synchronized (this.lock) {
            if (!this.active) {
                return;
            }
            boolean isDeviceLocked = isDeviceLocked();
            Logger.d("%s pollLockStateWhileUnlocked activated after %d milliseconds. isLocked=%b", TAG, 30000, Boolean.valueOf(isDeviceLocked));
            this.isLockedPollingSubject.onNext(Boolean.valueOf(isDeviceLocked));
            this.handler.removeCallbacks(this.pollLockStateRunnable);
            pollLockStateWhileUnlocked();
        }
    }

    public /* synthetic */ void lambda$null$0$PhoneSecurityStateSupplier(SecurityStateReceiver securityStateReceiver) throws Throwable {
        this.context.unregisterReceiver(securityStateReceiver);
    }

    public /* synthetic */ void lambda$queryDeviceLockedState$1$PhoneSecurityStateSupplier(ObservableEmitter observableEmitter) throws Throwable {
        observableEmitter.onNext(Boolean.valueOf(isDeviceLocked()));
        final SecurityStateReceiver securityStateReceiver = new SecurityStateReceiver(observableEmitter);
        this.context.registerReceiver(securityStateReceiver, this.securityIntentFilter);
        observableEmitter.setCancellable(new Cancellable() { // from class: com.amazon.alexa.accessory.avsclient.context.-$$Lambda$PhoneSecurityStateSupplier$3S7YpcRqXY5XPUcEbDElwik8JVQ
            @Override // io.reactivex.rxjava3.functions.Cancellable
            public final void cancel() {
                PhoneSecurityStateSupplier.this.lambda$null$0$PhoneSecurityStateSupplier(securityStateReceiver);
            }
        });
    }

    public /* synthetic */ Boolean lambda$queryDeviceLockedState$2$PhoneSecurityStateSupplier(Boolean bool) throws Throwable {
        synchronized (this.lock) {
            this.isLocked = bool.booleanValue();
            if (!bool.booleanValue()) {
                pollLockStateWhileUnlocked();
            } else {
                this.handler.removeCallbacks(this.pollLockStateRunnable);
            }
        }
        return bool;
    }

    @Override // com.amazon.alexa.accessory.avsclient.context.SecurityStateSupplier
    public Observable<Boolean> queryDeviceLockedState() {
        synchronized (this.lock) {
            if (!this.active) {
                return Observable.error(new IllegalStateException("PhoneSecurityStateSupplier:  not active"));
            }
            return Observable.create(new ObservableOnSubscribe() { // from class: com.amazon.alexa.accessory.avsclient.context.-$$Lambda$PhoneSecurityStateSupplier$GWPRxMy3q78Tnl6PMQo44bibF58
                @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
                public final void subscribe(ObservableEmitter observableEmitter) {
                    PhoneSecurityStateSupplier.this.lambda$queryDeviceLockedState$1$PhoneSecurityStateSupplier(observableEmitter);
                }
            }).mergeWith(this.isLockedPollingSubject).distinctUntilChanged().map(new Function() { // from class: com.amazon.alexa.accessory.avsclient.context.-$$Lambda$PhoneSecurityStateSupplier$sv5Yz_0NNjTT5lPiTGnVQAo00ic
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply */
                public final Object mo10358apply(Object obj) {
                    return PhoneSecurityStateSupplier.this.lambda$queryDeviceLockedState$2$PhoneSecurityStateSupplier((Boolean) obj);
                }
            });
        }
    }
}
